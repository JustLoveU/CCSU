<template>
  <el-dialog :title="!dataForm.id ? '新增' : '修改'" :close-on-click-modal="false" :visible.sync="visible">
    <el-form :model="dataForm" :rules="dataRule" ref="dataForm" @keyup.enter.native="dataFormSubmit()" label-width="80px">
      <el-form-item label="登录名" prop="username">
        <el-input v-model="dataForm.username" placeholder=""></el-input>
      </el-form-item>
      <el-form-item label="密码" prop="password">
        <el-input v-model="dataForm.password" placeholder=""></el-input>
      </el-form-item>
      <el-form-item label="身份" prop="uuid">
        <el-input v-model="dataForm.uuid" placeholder=""></el-input>
      </el-form-item>
      <el-form-item label="名字" prop="name">
        <el-input v-model="dataForm.name" placeholder=""></el-input>
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
        username: '',
        password: '',
        uuid: '',
        name: '',
        id: 0
      },
      dataRule: {
        username: [{
          required: true,
          message: '不能为空',
          trigger: 'blur'
        }],
        password: [{
          required: true,
          message: '不能为空',
          trigger: 'blur'
        }],
        uuid: [{
          required: true,
          message: '不能为空',
          trigger: 'blur'
        }],
        name: [{
          required: true,
          message: '不能为空',
          trigger: 'blur'
        }]
      }
    }
  },
  methods: {
    init (id) {
      this.dataForm.id = id || 0
      this.visible = true
      if (this.dataForm.id) {
        this.$axios.get(`/admin/user/info/${this.dataForm.id}`).then(({
          data
        }) => {
          if (data && data.code === 0) {
            this.dataForm.username = data.user.username
            this.dataForm.password = data.user.password
            this.dataForm.uuid = data.user.uuid
            this.dataForm.name = data.user.name
          }
        })
      } else {
        this.dataForm.username = ''
        this.dataForm.password = ''
        this.dataForm.uuid = ''
        this.dataForm.name = ''
        this.dataForm.id = 0
      }
    },
    // 表单提交
    dataFormSubmit () {
      this.$refs.dataForm.validate((valid) => {
        if (valid) {
          this.$axios.post(`/admin/user/${!this.dataForm.id ? 'save' : 'update'}`, {
            username: this.dataForm.username,
            password: this.dataForm.password,
            uuid: this.dataForm.uuid,
            name: this.dataForm.name,
            id: this.dataForm.id || undefined
          }).then(({
            data
          }) => {
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
