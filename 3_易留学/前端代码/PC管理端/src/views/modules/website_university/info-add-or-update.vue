<template>
  <el-dialog
    :title="!dataForm.id ? '新增' : '修改'"
    :close-on-click-modal="false"
    :visible.sync="visible">
    <el-form :model="dataForm" :rules="dataRule" ref="dataForm" @keyup.enter.native="dataFormSubmit()" label-width="80px">
    <el-form-item label="资讯分类编号" prop="typeId">
      <el-input v-model="dataForm.typeId" placeholder="资讯分类编号"></el-input>
    </el-form-item>
    <el-form-item label="资讯标题" prop="title">
      <el-input v-model="dataForm.title" placeholder="资讯标题"></el-input>
    </el-form-item>
    <el-form-item label="资讯发布时间" prop="time">
      <el-input v-model="dataForm.time" placeholder="资讯发布时间"></el-input>
    </el-form-item>
    <el-form-item label="资讯内容" prop="content">
      <el-input v-model="dataForm.content" placeholder="资讯内容"></el-input>
    </el-form-item>
    <el-form-item label="资讯浏览次数" prop="view">
      <el-input v-model="dataForm.view" placeholder="资讯浏览次数"></el-input>
    </el-form-item>
    <el-form-item label="资讯链接" prop="url">
      <el-input v-model="dataForm.url" placeholder="资讯链接"></el-input>
    </el-form-item>
    <el-form-item label="大学编号" prop="universityId">
      <el-input v-model="dataForm.universityId" placeholder="大学编号"></el-input>
    </el-form-item>
    <el-form-item label="" prop="source">
      <el-input v-model="dataForm.source" placeholder=""></el-input>
    </el-form-item>
    </el-form>
    <span slot="footer" class="dialog-footer">
      <el-button @click="visible = false">取消</el-button>
      <el-button type="primary" @click="dataFormSubmit()">确定</el-button>
    </span>
  </el-dialog>
</template>

<script>
  export default {
    data () {
      return {
        visible: false,
        dataForm: {
          id: 0,
          typeId: '',
          title: '',
          time: '',
          content: '',
          view: '',
          url: '',
          universityId: '',
          source: ''
        },
        dataRule: {
          typeId: [
            { required: true, message: '资讯分类编号不能为空', trigger: 'blur' }
          ],
          title: [
            { required: true, message: '资讯标题不能为空', trigger: 'blur' }
          ],
          time: [
            { required: true, message: '资讯发布时间不能为空', trigger: 'blur' }
          ],
          content: [
            { required: true, message: '资讯内容不能为空', trigger: 'blur' }
          ],
          view: [
            { required: true, message: '资讯浏览次数不能为空', trigger: 'blur' }
          ],
          url: [
            { required: true, message: '资讯链接不能为空', trigger: 'blur' }
          ],
          universityId: [
            { required: true, message: '大学编号不能为空', trigger: 'blur' }
          ],
          source: [
            { required: true, message: '不能为空', trigger: 'blur' }
          ]
        }
      }
    },
    methods: {
      init (id) {
        this.dataForm.id = id || 0
        this.visible = true
        this.$nextTick(() => {
          this.$refs['dataForm'].resetFields()
          if (this.dataForm.id) {
            this.$http({
              url: this.$http.adornUrl(`/website_university/info/info/${this.dataForm.id}`),
              method: 'get',
              params: this.$http.adornParams()
            }).then(({data}) => {
              if (data && data.code === 0) {
                this.dataForm.typeId = data.info.typeId
                this.dataForm.title = data.info.title
                this.dataForm.time = data.info.time
                this.dataForm.content = data.info.content
                this.dataForm.view = data.info.view
                this.dataForm.url = data.info.url
                this.dataForm.universityId = data.info.universityId
                this.dataForm.source = data.info.source
              }
            })
          }
        })
      },
      // 表单提交
      dataFormSubmit () {
        this.$refs['dataForm'].validate((valid) => {
          if (valid) {
            this.$http({
              url: this.$http.adornUrl(`/website_university/info/${!this.dataForm.id ? 'save' : 'update'}`),
              method: 'post',
              data: this.$http.adornData({
                'id': this.dataForm.id || undefined,
                'typeId': this.dataForm.typeId,
                'title': this.dataForm.title,
                'time': this.dataForm.time,
                'content': this.dataForm.content,
                'view': this.dataForm.view,
                'url': this.dataForm.url,
                'universityId': this.dataForm.universityId,
                'source': this.dataForm.source
              })
            }).then(({data}) => {
              if (data && data.code === 0) {
                this.$message({
                  message: '操作成功',
                  type: 'success',
                  duration: 1500,
                  onClose: () => {
                    this.visible = false
                    this.$emit('refreshDataList')
                  }
                })
              } else {
                this.$message.error(data.msg)
              }
            })
          }
        })
      }
    }
  }
</script>
