<template>
  <el-dialog
    :title="!dataForm.id ? '新增' : '修改'"
    :close-on-click-modal="false"
    :visible.sync="visible">
    <el-form :model="dataForm" :rules="dataRule" ref="dataForm" @keyup.enter.native="dataFormSubmit()" label-width="80px">
    <el-form-item label="大学简介" prop="intro">
      <el-input v-model="dataForm.intro" placeholder="大学简介"></el-input>
    </el-form-item>
    <el-form-item label="大学名字" prop="name">
      <el-input v-model="dataForm.name" placeholder="大学名字"></el-input>
    </el-form-item>
    <el-form-item label="大学评价星级" prop="star">
      <el-input v-model="dataForm.star" placeholder="大学评价星级"></el-input>
    </el-form-item>
    <el-form-item label="大学学费" prop="tuition">
      <el-input v-model="dataForm.tuition" placeholder="大学学费"></el-input>
    </el-form-item>
    <el-form-item label="大学浏览次数" prop="view">
      <el-input v-model="dataForm.view" placeholder="大学浏览次数"></el-input>
    </el-form-item>
    <el-form-item label="大学头像" prop="img">
      <el-input v-model="dataForm.img" placeholder="大学头像"></el-input>
    </el-form-item>
    <el-form-item label="地区" prop="infoTypeId">
      <el-input v-model="dataForm.infoTypeId" placeholder="地区"></el-input>
    </el-form-item>
    <el-form-item label="成立时间" prop="setData">
      <el-input v-model="dataForm.setData" placeholder="成立时间"></el-input>
    </el-form-item>
    <el-form-item label="坐标" prop="location">
      <el-input v-model="dataForm.location" placeholder="坐标"></el-input>
    </el-form-item>
    <el-form-item label="电话" prop="tel">
      <el-input v-model="dataForm.tel" placeholder="电话"></el-input>
    </el-form-item>
    <el-form-item label="图片" prop="pic">
      <el-input v-model="dataForm.pic" placeholder="图片"></el-input>
    </el-form-item>
    <el-form-item label="网站链接" prop="url">
      <el-input v-model="dataForm.url" placeholder="网站链接"></el-input>
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
          intro: '',
          name: '',
          star: '',
          tuition: '',
          view: '',
          img: '',
          infoTypeId: '',
          setData: '',
          location: '',
          tel: '',
          pic: '',
          url: ''
        },
        dataRule: {
          intro: [
            { required: true, message: '大学简介不能为空', trigger: 'blur' }
          ],
          name: [
            { required: true, message: '大学名字不能为空', trigger: 'blur' }
          ],
          star: [
            { required: true, message: '大学评价星级不能为空', trigger: 'blur' }
          ],
          tuition: [
            { required: true, message: '大学学费不能为空', trigger: 'blur' }
          ],
          view: [
            { required: true, message: '大学浏览次数不能为空', trigger: 'blur' }
          ],
          img: [
            { required: true, message: '大学头像不能为空', trigger: 'blur' }
          ],
          infoTypeId: [
            { required: true, message: '地区不能为空', trigger: 'blur' }
          ],
          setData: [
            { required: true, message: '成立时间不能为空', trigger: 'blur' }
          ],
          location: [
            { required: true, message: '坐标不能为空', trigger: 'blur' }
          ],
          tel: [
            { required: true, message: '电话不能为空', trigger: 'blur' }
          ],
          pic: [
            { required: true, message: '图片不能为空', trigger: 'blur' }
          ],
          url: [
            { required: true, message: '网站链接不能为空', trigger: 'blur' }
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
              url: this.$http.adornUrl(`/website_university/university/info/${this.dataForm.id}`),
              method: 'get',
              params: this.$http.adornParams()
            }).then(({data}) => {
              if (data && data.code === 0) {
                this.dataForm.intro = data.university.intro
                this.dataForm.name = data.university.name
                this.dataForm.star = data.university.star
                this.dataForm.tuition = data.university.tuition
                this.dataForm.view = data.university.view
                this.dataForm.img = data.university.img
                this.dataForm.infoTypeId = data.university.infoTypeId
                this.dataForm.setData = data.university.setData
                this.dataForm.location = data.university.location
                this.dataForm.tel = data.university.tel
                this.dataForm.pic = data.university.pic
                this.dataForm.url = data.university.url
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
              url: this.$http.adornUrl(`/website_university/university/${!this.dataForm.id ? 'save' : 'update'}`),
              method: 'post',
              data: this.$http.adornData({
                'id': this.dataForm.id || undefined,
                'intro': this.dataForm.intro,
                'name': this.dataForm.name,
                'star': this.dataForm.star,
                'tuition': this.dataForm.tuition,
                'view': this.dataForm.view,
                'img': this.dataForm.img,
                'infoTypeId': this.dataForm.infoTypeId,
                'setData': this.dataForm.setData,
                'location': this.dataForm.location,
                'tel': this.dataForm.tel,
                'pic': this.dataForm.pic,
                'url': this.dataForm.url
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
