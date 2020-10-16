<template>
  <el-dialog
    :title="!dataForm.id ? '新增' : '修改'"
    :close-on-click-modal="false"
    :visible.sync="visible">
    <el-form :model="dataForm" :rules="dataRule" ref="dataForm" @keyup.enter.native="dataFormSubmit()" label-width="80px">
    <el-form-item label="用户编号" prop="userId">
      <el-input v-model="dataForm.userId" placeholder="用户编号"></el-input>
    </el-form-item>
    <el-form-item label="分类" prop="type">
      <el-input v-model="dataForm.type" placeholder="分类"></el-input>
    </el-form-item>
    <el-form-item label="大学或者咨询编号" prop="universityInfoId">
      <el-input v-model="dataForm.universityInfoId" placeholder="大学或者咨询编号"></el-input>
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
          userId: '',
          type: '',
          universityInfoId: ''
        },
        dataRule: {
          userId: [
            { required: true, message: '用户编号不能为空', trigger: 'blur' }
          ],
          type: [
            { required: true, message: '分类不能为空', trigger: 'blur' }
          ],
          universityInfoId: [
            { required: true, message: '大学或者咨询编号不能为空', trigger: 'blur' }
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
              url: this.$http.adornUrl(`/website_university/history/info/${this.dataForm.id}`),
              method: 'get',
              params: this.$http.adornParams()
            }).then(({data}) => {
              if (data && data.code === 0) {
                this.dataForm.userId = data.history.userId
                this.dataForm.type = data.history.type
                this.dataForm.universityInfoId = data.history.universityInfoId
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
              url: this.$http.adornUrl(`/website_university/history/${!this.dataForm.id ? 'save' : 'update'}`),
              method: 'post',
              data: this.$http.adornData({
                'id': this.dataForm.id || undefined,
                'userId': this.dataForm.userId,
                'type': this.dataForm.type,
                'universityInfoId': this.dataForm.universityInfoId
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
