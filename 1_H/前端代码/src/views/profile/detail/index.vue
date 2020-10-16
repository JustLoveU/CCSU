<template>
  <d2-container>
    <template slot="header">
    <el-button type="primary" @click="edit">修改</el-button>
    </template>
    <el-form :model="profile" :rules="rules" ref="ruleForm" label-width="100px" class="demo-ruleForm" disabled>
      <el-form-item label="名字" prop="profileCnname">
        <el-input v-model="profile.profileCnname" disabled></el-input>
      </el-form-item>
      <el-form-item label="头像" prop="profilePicpath">
        <el-upload>
          <img v-if="profile.profilePicpath" :src="profile.profilePicpath" class="avatar" disabled>
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
    </el-form>
  </d2-container>
</template>

<script>
export default {
  name: 'detail',
  data () {
    return {
      profile: {
        profilePicpath: '',
        profileId: '',
        profileIntro: '',
        profileCnname: '',
        profileEducation: '',
        profileTelephone: '',
        profileEmail: '',
        profileStudydir: '',
        profileAddress: '',
        profileWorkexp: '',
        profileEdubackground: ''
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
    edit () {
      this.$router.push({
        path: '/edit',
        query: {
          id: this.profile.profileId
        }
      })
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
