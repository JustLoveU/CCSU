<template>
  <el-dialog
    :title="!dataForm.id ? '新增' : '修改'"
    :close-on-click-modal="false"
    :visible.sync="visible">
    <el-form :model="dataForm" :rules="dataRule" ref="dataForm" @keyup.enter.native="dataFormSubmit()" label-width="80px">
    <el-form-item label="用户名" prop="username">
      <el-input v-model="dataForm.username" placeholder="用户名"></el-input>
    </el-form-item>
    <el-form-item label="密码" prop="password">
      <el-input v-model="dataForm.password" placeholder="密码"></el-input>
    </el-form-item>
    <el-form-item label="头像" prop="img">
      <el-input v-model="dataForm.img" placeholder="头像"></el-input>
    </el-form-item>
    <el-form-item label="简介" prop="intro">
      <el-input v-model="dataForm.intro" placeholder="简介"></el-input>
    </el-form-item>
    <el-form-item label="邮箱" prop="email">
      <el-input v-model="dataForm.email" placeholder="邮箱"></el-input>
    </el-form-item>
    <el-form-item label="注册时间" prop="date">
      <el-input v-model="dataForm.date" placeholder="注册时间"></el-input>
    </el-form-item>
    <el-form-item label="是否可用" prop="status">
      <el-input v-model="dataForm.status" placeholder="是否可用"></el-input>
    </el-form-item>
    <el-form-item label="角色" prop="role">
      <el-input v-model="dataForm.role" placeholder="角色"></el-input>
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
          username: '',
          password: '',
          img: '',
          intro: '',
          email: '',
          date: '',
          status: '',
          role: ''
        },
        dataRule: {
          username: [
            { required: true, message: '用户名不能为空', trigger: 'blur' }
          ],
          password: [
            { required: true, message: '密码不能为空', trigger: 'blur' }
          ],
          img: [
            { required: true, message: '头像不能为空', trigger: 'blur' }
          ],
          intro: [
            { required: true, message: '简介不能为空', trigger: 'blur' }
          ],
          email: [
            { required: true, message: '邮箱不能为空', trigger: 'blur' }
          ],
          date: [
            { required: true, message: '注册时间不能为空', trigger: 'blur' }
          ],
          status: [
            { required: true, message: '是否可用不能为空', trigger: 'blur' }
          ],
          role: [
            { required: true, message: '角色不能为空', trigger: 'blur' }
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
              url: this.$http.adornUrl(`/website_university/user/info/${this.dataForm.id}`),
              method: 'get',
              params: this.$http.adornParams()
            }).then(({data}) => {
              if (data && data.code === 0) {
                this.dataForm.username = data.user.username
                this.dataForm.password = data.user.password
                this.dataForm.img = data.user.img
                this.dataForm.intro = data.user.intro
                this.dataForm.email = data.user.email
                this.dataForm.date = data.user.date
                this.dataForm.status = data.user.status
                this.dataForm.role = data.user.role
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
              url: this.$http.adornUrl(`/website_university/user/${!this.dataForm.id ? 'save' : 'update'}`),
              method: 'post',
              data: this.$http.adornData({
                'id': this.dataForm.id || undefined,
                'username': this.dataForm.username,
                'password': this.dataForm.password,
                'img': this.dataForm.img,
                'intro': this.dataForm.intro,
                'email': this.dataForm.email,
                'date': this.dataForm.date,
                'status': this.dataForm.status,
                'role': this.dataForm.role
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
