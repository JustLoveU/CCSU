<template>
  <d2-container>
    <el-form :model="profile" :rules="rules" ref="ruleForm" label-width="100px" class="demo-ruleForm">
      <el-form-item label="名字" prop="profileCnname">
        <el-input v-model="profile.profileCnname"></el-input>
      </el-form-item>
      <el-form-item label="头像" prop="profilePicpath">
        <el-upload
          class="avatar-uploader"
          action="https://jsonplaceholder.typicode.com/posts/"
          :http-request="imgAdd"
          :show-file-list="false"
          :on-success="handleAvatarSuccess"
          :before-upload="beforeAvatarUpload">
          <img v-if="profile.profilePicpath" :src="profile.profilePicpath" class="avatar">
          <i v-else class="el-icon-plus avatar-uploader-icon"></i>
        </el-upload>
      </el-form-item>
      <el-form-item label="学历" prop="profileEducation">
        <el-input v-model="profile.profileEducation"></el-input>
      </el-form-item>
      <el-form-item label="职称" prop="profilePosition">
          <el-input type="textarea" v-model="profile.profilePosition" autosize></el-input>
      </el-form-item>
      <el-form-item label="电话" prop="profileTelephone">
        <el-input v-model="profile.profileTelephone"></el-input>
      </el-form-item>
      <el-form-item label="邮箱" prop="profileEmail">
        <el-input v-model="profile.profileEmail"></el-input>
      </el-form-item>
      <el-form-item label="简介" prop="profileIntro">
          <el-input type="textarea" v-model="profile.profileIntro" autosize></el-input>
      </el-form-item>
      <el-form-item label="工作领域" prop="profileStudydir">
          <el-input type="textarea" v-model="profile.profileStudydir" autosize></el-input>
      </el-form-item>
      <el-form-item label="地址" prop="profileAddress">
          <el-input type="textarea" v-model="profile.profileAddress" autosize></el-input>
      </el-form-item>
      <el-form-item label="工作经历" prop="profileWorkexp">
          <el-input type="textarea" v-model="profile.profileWorkexp" autosize></el-input>
      </el-form-item>
      <el-form-item label="教育背景" prop="profileEdubackground">
          <el-input type="textarea" v-model="profile.profileEdubackground" autosize></el-input>
      </el-form-item>
      <el-form-item label="是否显示" prop="profileStatus">
          <el-switch
            v-model='profile.profileStatus'
            active-color="#13ce66"
            inactive-color="#ff4949"
            :active-value='true'
            :inactive-value='false'>
          </el-switch>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="submitForm('ruleForm')">立即创建/修改</el-button>
        <el-button @click="resetForm('ruleForm')">重置</el-button>
      </el-form-item>
    </el-form>
  </d2-container>
</template>

<script>
export default {
  name: 'edit',
  data () {
    return {
      profile: {
        profilePicpath: '',
        profileId: '',
        profileIntro: '',
        profileCnname: '',
        profileEducation: '',
        profilePosition: '',
        profileTelephone: '',
        profileEmail: '',
        profileStudydir: '',
        profileAddress: '',
        profileWorkexp: '',
        profileEdubackground: '',
        profileStatus: 0
      },
      imageUrl: '',
      rules: {
        profileCnname: [
          { required: true, message: '请输入名字', trigger: 'blur' }
        ]
      }
    }
  },
  created () {
    if (this.$route.query.id !== null && this.$route.query.id !== undefined) {
      this.getProfile(this.$route.query.id)
    } else {
    }
  },
  methods: {
    getProfile (id) {
      this.$axios.get('/admin/profile/one/' + id).then(res => {
        this.profile = res.data
      }).catch(err => {
        console.log(err)
      })
    },
    handleAvatarSuccess (res, file) {
      this.imageUrl = URL.createObjectURL(file.raw)
    },
    beforeAvatarUpload (file) {
      const isJPG = file.type === 'image/jpeg'
      const ispng = file.type === 'image/png'
      const isLt2M = file.size / 1024 / 1024 < 10

      if (!isJPG && (!ispng)) {
        this.$message.error('上传头像图片只能是 png 或 jpg格式!')
      }
      if (!isLt2M) {
        this.$message.error('上传头像图片大小不能超过 10MB!')
      }
      return (isJPG || ispng) && isLt2M
    },
    imgAdd (file) {
      // 第一步.将图片上传到服务器.
      var formdata = new FormData()
      formdata.append('image', file.file)
      console.log(formdata)
      this.$axios({
        url: '/admin/profile/img',
        method: 'post',
        data: formdata,
        headers: { 'Content-Type': 'multipart/form-data' }
      }).then((res) => {
        console.log(res)
        this.profile.profilePicpath = res.data.url
      })
    },
    submitForm (formName) {
      var formdata = new FormData()
      formdata.append('profileId', this.profile.profileId)
      formdata.append('profilePicpath', this.profile.profilePicpath)
      formdata.append('profileIntro', this.profile.profileIntro)
      formdata.append('profileCnname', this.profile.profileCnname)
      formdata.append('profileEducation', this.profile.profileEducation)
      formdata.append('profilePosition', this.profile.profilePosition)
      formdata.append('profileTelephone', this.profile.profileTelephone)
      formdata.append('profileEmail', this.profile.profileEmail)
      formdata.append('profileStudydir', this.profile.profileStudydir)
      formdata.append('profileAddress', this.profile.profileAddress)
      formdata.append('profileWorkexp', this.profile.profileWorkexp)
      formdata.append('profileEdubackground', this.profile.profileEdubackground)
      formdata.append('profileStatus', this.profile.profileStatus)
      this.$refs[formName].validate((valid) => {
        if (valid) {
          this.$confirm('是否保存该人员?', '提示', {
            confirmButtonText: '确定',
            cancelButtonText: '取消',
            type: 'warning'
          }).then(() => {
            this.$axios({
              url: '/admin/profile/add',
              method: 'post',
              data: formdata
            }).then((res) => {
              this.$notify({
                title: '成功',
                message: '添加/修改成功',
                type: 'success'
              })
              this.$router.push({
                path: '/list'
              })
              console.log(res)
            })
          })
        } else {
          console.log('error submit!!')
          return false
        }
      })
    },
    resetForm (formName) {
      this.$refs[formName].resetFields()
    }
  }
}
</script>

<style>
 .avatar-uploader .el-upload {
     border: 1px dashed #d9d9d9;
     border-radius: 6px;
     cursor: pointer;
     position: relative;
     overflow: hidden;
   }
   .avatar-uploader .el-upload:hover {
     border-color: #409EFF;
   }
   .avatar-uploader-icon {
     font-size: 28px;
     color: #8c939d;
     width: 130px;
     height: 178px;
     line-height: 178px;
     text-align: center;
   }
  .avatar {
    width: 120px;
    height: 178px;
    display: block;
  }
</style>
