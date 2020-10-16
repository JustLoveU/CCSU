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
            v-model="article.navId"
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
          content: '',
          navId: '',
          view: '',
          ischart: 0
        }
      ],
      options: [{
        value: 2,
        label: '实验室概况',
        children: [{
          value: 3,
          label: '实验室简介'
        },
        {
          value: 4,
          label: '现任领导'
        }, {
          value: 26,
          label: '联系我们'
        },
        {
          value: 32,
          label: '组织结构'
        }]
      },
      {
        value: 5,
        label: '动态新闻',
        children: [{
          value: 6,
          label: '实验室新闻'
        },
        {
          value: 7,
          label: '通知公告'
        }]
      },
      {
        value: 8,
        label: '科学研究',
        children: [{
          value: 9,
          label: '论文著作'
        },
        {
          value: 10,
          label: '科研项目'
        }, {
          value: 11,
          label: '研究成果'
        },
        {
          value: 12,
          label: '学术交流'
        }]
      },
      {
        value: 13,
        label: '学术阶梯',
        children: [{
          value: 14,
          label: '科研队伍'
        },
        {
          value: 15,
          label: '招聘信息'
        }]
      },
      {
        value: 27,
        label: '研究生教育',
        children: [{
          value: 28,
          label: '毕业校友'
        },
        {
          value: 29,
          label: '在校学生'
        },
        {
          value: 30,
          label: '招生信息'
        }]
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
          navId: this.article.navId,
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
        }).catch(err => {
          this.$notify({
            title: '添加失败',
            message: err,
            type: 'danger'
          })
        })
      })
    },
    getArticle (id) {
      this.$axios.get('/admin/article/one/' + id).then(res => {
        this.article = res.data
      })
    },
    handleChange (value) {
      this.article.navId = value[value.length - 1]
    },
    // 绑定@imgAdd event
    imgAdd (pos, $file) {
      // 第一步.将图片上传到服务器.
      var formdata = new FormData()
      formdata.append('image', $file)
      this.$axios({
        url: '/admin/article/img',
        method: 'post',
        data: formdata,
        headers: { 'Content-Type': 'multipart/form-data' }
      }).then((url) => {
        this.$refs.md.$img2Url(pos, url.data.url)
        this.$notify({
          title: '成功',
          message: '添加成功',
          type: 'success'
        })
      }).catch(() => {
        this.$message({
          type: 'info',
          message: '上传失败'
        })
      })
    }
  }
}
</script>
