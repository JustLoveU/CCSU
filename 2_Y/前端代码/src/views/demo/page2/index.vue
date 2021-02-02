<template>
  <d2-container>
    <template slot="header">
      <el-row :gutter="20" style="margin-bottom: 10px;">
        <el-col :span="12">
          <el-input
            placeholder="请输入标题"
            v-model="article.title"
            clearable>
          </el-input>
        </el-col>
        <el-col :span="6">
          <el-cascader
            placeholder="请选择菜单"
            v-model="article.nav"
            :options="options"
            :props="{ expandTrigger: 'hover' }"
            @change="handleChange"></el-cascader>
        </el-col>
        <el-col :span="6">
          <template>
            是否设为轮播图
            <el-radio-group v-model="article.ischart">
              <el-radio :label="0">否</el-radio>
              <el-radio :label="1">是</el-radio>
            </el-radio-group>
          </template>
        </el-col>
      </el-row>
      <el-row :gutter="20">
        <el-col :span="12">
          <el-input
            placeholder="请输入图片链接"
            v-model="article.img"
            clearable>
          </el-input>
        </el-col>
       <el-col :span="12">
            <el-input
              placeholder="请输入外部链接"
              v-model="article.url"
              clearable>
            </el-input>
        </el-col>
      </el-row>
    </template>
    <template>
      <mavon-editor
        v-model="article.content"
        ref="md"
        :toolbars="toolbars"
        @imgAdd="imgAdd"
        @save="saveDoc"
        @change="updateDoc"/>
    </template>
  </d2-container>
</template>

<script>
import { mavonEditor } from 'mavon-editor'
import 'mavon-editor/dist/css/index.css'
export default {
  name: 'page2',
  components: { mavonEditor },
  data () {
    return {
      toolbars: {
        bold: true,
        italic: true,
        header: true,
        underline: false,
        strikethrough: false,
        mark: false,
        superscript: false,
        subscript: false,
        quote: true,
        ol: true,
        ul: true,
        link: true,
        imagelink: true,
        code: true,
        table: true,
        fullscreen: true,
        readmodel: true,
        htmlcode: true,
        help: false,
        undo: true,
        redo: true,
        trash: true,
        save: true,
        navigation: true,
        alignleft: false,
        aligncenter: false,
        alignright: false,
        subfield: true,
        preview: true
      },
      fullscreenLoading: false,
      article: [
        {
          id: '',
          title: '',
          img: '',
          url: '',
          view: '',
          content: '',
          navId: '',
          ischart: 0
        }
      ],
      options: [
        {
          value: '政策&新论',
          label: '政策&新论',
          children: [{
            value: '国家政策',
            label: '国家政策'
          },
          {
            value: '学者新论',
            label: '学者新论'
          }]
        },
        {
          value: '机构概况',
          label: '机构概况',
          children: [{
            value: '机构简介',
            label: '机构简介'
          },
          {
            value: '联系方式',
            label: '联系方式'
          }]
        },
        {
          value: '动态新闻',
          label: '动态新闻',
          children: [{
            value: '工作动态',
            label: '工作动态'
          },
          {
            value: '通知公告',
            label: '通知公告'
          }]
        },
        {
          value: '学术活动',
          label: '学术活动'
        },
        {
          value: '科研&服务',
          label: '科研&服务',
          children: [{
            value: '科研项目',
            label: '科研项目'
          },
          {
            value: '社会服务',
            label: '社会服务'
          }]
        },
        {
          value: '平台建设',
          label: '平台建设'
        }]
    }
  },
  created () {
    if (this.$route.query.id !== null && this.$route.query.id !== -1) {
      this.getArticle(this.$route.query.id)
      this.article.id = this.$route.query.id
    } else if (this.$route.query.id === -1) {
    } else {
      this.article = localStorage.article
    }
  },
  methods: {
    updateDoc (markdown, html) {
      localStorage.article = this.article
    },
    saveDoc (markdown, html) {
      localStorage.article = this.article
      this.$confirm('是否保存文章?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        this.$axios.post('/admin/article/add', {
          id: this.article.id,
          title: this.article.title,
          img: this.article.img,
          url: this.article.url,
          content: this.article.content,
          nav: this.article.nav,
          ischart: this.article.ischart,
          view: this.article.view
        }).then(res => {
          this.$notify({
            title: '成功',
            message: '添加成功',
            type: 'success'
          })
          this.$router.push({
            path: '/page1'
          })
          console.log(res.data)
        }).catch(err => {
          this.$notify({
            title: '失败',
            message: '添加失败',
            type: 'danger'
          })
          console.log(err)
        })
      })
    },
    getArticle (id) {
      this.$axios.get('/admin/article/one/' + id).then(res => {
        this.article = res.data
      }).catch(err => {
        console.log(err)
      })
    },
    handleChange (value) {
      this.article.nav = value[value.length - 1]
    },
    // 绑定@imgAdd event
    imgAdd (pos, $file) {
      // 第一步.将图片上传到服务器.
      var formdata = new FormData()
      formdata.append('image', $file)
      console.log(formdata)
      this.$axios({
        url: '/admin/article/img',
        method: 'post',
        data: formdata,
        headers: { 'Content-Type': 'multipart/form-data' }
      }).then((url) => {
        console.log(url)
        this.$refs.md.$img2Url(pos, url.data.url)
      })
    }
  }
}
</script>
