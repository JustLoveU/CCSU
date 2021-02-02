<template>
  <el-dialog :title="!dataForm.id ? '新增' : '修改'" :close-on-click-modal="false" :visible.sync="visible">
    <el-form :model="dataForm" :rules="dataRule" ref="dataForm" @keyup.enter.native="dataFormSubmit()" label-width="80px">
      <el-form-item label="链接" prop="url">
        <el-input v-model="dataForm.url" placeholder="链接"></el-input>
      </el-form-item>
      <el-form-item label="名字" prop="name">
        <el-input v-model="dataForm.name" placeholder="名字"></el-input>
      </el-form-item>
      <el-form-item label="是否显示" prop="status">
        <!-- <el-input v-model="dataForm.status" placeholder="是否显示"></el-input> -->
        <el-switch v-model='dataForm.status' active-color="#13ce66" inactive-color="#ff4949" :active-value='true'
          :inactive-value='false'>
        </el-switch>
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
  name: 'friend-add-or-update',
  data () {
    return {
      visible: false,
      dataForm: {
        id: 0,
        url: '',
        name: '',
        status: 1
      },
      dataRule: {
        url: [{
          required: true,
          message: '链接不能为空',
          trigger: 'blur'
        }],
        name: [{
          required: true,
          message: '名字不能为空',
          trigger: 'blur'
        }],
        status: [{
          required: true,
          message: '是否显示不能为空',
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
        this.$axios.get(`/admin/friend/info/${this.dataForm.id}`).then(({
          data
        }) => {
          if (data && data.code === 0) {
            this.dataForm.url = data.friend.url
            this.dataForm.name = data.friend.name
            this.dataForm.status = data.friend.status
          }
        })
      } else {
        this.dataForm.id = 0
        this.dataForm.url = ''
        this.dataForm.name = ''
        this.dataForm.status = 1
      }
    },
    // 表单提交
    dataFormSubmit () {
      this.$refs.dataForm.validate((valid) => {
        if (valid) {
          this.$axios.post(`/admin/friend/${!this.dataForm.id ? 'save' : 'update'}`, {
            id: this.dataForm.id || undefined,
            url: this.dataForm.url,
            name: this.dataForm.name,
            status: this.dataForm.status
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
