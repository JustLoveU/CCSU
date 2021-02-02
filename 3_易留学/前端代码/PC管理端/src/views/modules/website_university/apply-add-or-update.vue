<template>
  <el-dialog :title="!dataForm.id ? '新增' : '修改'" :close-on-click-modal="false" :visible.sync="visible">
    <el-form :model="dataForm" :rules="dataRule" ref="dataForm" @keyup.enter.native="dataFormSubmit()" label-width="80px">
      <el-form-item label="用户编号" prop="userId">
        <el-input v-model="dataForm.userId" placeholder="用户编号"></el-input>
      </el-form-item>
      <el-form-item label="留学申请预选区域" prop="address">
        <el-input v-model="dataForm.address" placeholder="留学申请预选区域"></el-input>
      </el-form-item>
      <el-form-item label="留学申请计划攻读学位" prop="degree">
        <el-input v-model="dataForm.degree" placeholder="留学申请计划攻读学位"></el-input>
      </el-form-item>
      <el-form-item label="留学申请已学习年限" prop="years">
        <el-input v-model="dataForm.years" placeholder="留学申请已学习年限"></el-input>
      </el-form-item>
      <el-form-item label="留学申请在读专业" prop="major">
        <el-input v-model="dataForm.major" placeholder="留学申请在读专业"></el-input>
      </el-form-item>
      <el-form-item label="留学申请出国时间" prop="outsideDate">
        <el-date-picker v-model="dataForm.outsideDate" align="right" type="datetime" placeholder="留学申请出国时间">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="留学申请就读院校" prop="university">
        <el-input v-model="dataForm.university" placeholder="留学申请就读院校"></el-input>
      </el-form-item>
      <el-form-item label="留学申请成绩" prop="score">
        <el-input v-model="dataForm.score" placeholder="留学申请成绩"></el-input>
      </el-form-item>
      <el-form-item label="留学申请手机号码" prop="tel">
        <el-input v-model="dataForm.tel" placeholder="留学申请手机号码"></el-input>
      </el-form-item>
      <el-form-item label="申请时间" prop="applyDate">
        <el-date-picker v-model="dataForm.applyDate" align="right" type="datetime" placeholder="申请时间">
        </el-date-picker>
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
    data() {
      return {
        visible: false,
        dataForm: {
          id: 0,
          userId: '',
          address: '',
          degree: '',
          years: '',
          major: '',
          outsideDate: '',
          university: '',
          score: '',
          tel: '',
          applyDate: ''
        },
        dataRule: {
          userId: [{
            required: true,
            message: '用户编号不能为空',
            trigger: 'blur'
          }],
          address: [{
            required: true,
            message: '留学申请预选区域不能为空',
            trigger: 'blur'
          }],
          degree: [{
            required: true,
            message: '留学申请计划攻读学位不能为空',
            trigger: 'blur'
          }],
          years: [{
            required: true,
            message: '留学申请已学习年限不能为空',
            trigger: 'blur'
          }],
          major: [{
            required: true,
            message: '留学申请在读专业不能为空',
            trigger: 'blur'
          }],
          outsideDate: [{
            required: true,
            message: '留学申请出国时间不能为空',
            trigger: 'blur'
          }],
          university: [{
            required: true,
            message: '留学申请就读院校不能为空',
            trigger: 'blur'
          }],
          score: [{
            required: true,
            message: '留学申请成绩不能为空',
            trigger: 'blur'
          }],
          tel: [{
            required: true,
            message: '留学申请手机号码不能为空',
            trigger: 'blur'
          }],
          applyDate: [{
            required: true,
            message: '申请时间不能为空',
            trigger: 'blur'
          }]
        }
      }
    },
    methods: {
      init(id) {
        this.dataForm.id = id || 0
        this.visible = true
        this.$nextTick(() => {
          this.$refs['dataForm'].resetFields()
          if (this.dataForm.id) {
            this.$http({
              url: this.$http.adornUrl(`/website_university/apply/info/${this.dataForm.id}`),
              method: 'get',
              params: this.$http.adornParams()
            }).then(({
              data
            }) => {
              if (data && data.code === 0) {
                this.dataForm.userId = data.apply.userId
                this.dataForm.address = data.apply.address
                this.dataForm.degree = data.apply.degree
                this.dataForm.years = data.apply.years
                this.dataForm.major = data.apply.major
                this.dataForm.outsideDate = data.apply.outsideDate
                this.dataForm.university = data.apply.university
                this.dataForm.score = data.apply.score
                this.dataForm.tel = data.apply.tel
                this.dataForm.applyDate = data.apply.applyDate
              }
            })
          }
        })
      },
      // 表单提交
      dataFormSubmit() {
        this.$refs['dataForm'].validate((valid) => {
          if (valid) {
            this.$http({
              url: this.$http.adornUrl(`/website_university/apply/${!this.dataForm.id ? 'save' : 'update'}`),
              method: 'post',
              data: this.$http.adornData({
                'id': this.dataForm.id || undefined,
                'userId': this.dataForm.userId,
                'address': this.dataForm.address,
                'degree': this.dataForm.degree,
                'years': this.dataForm.years,
                'major': this.dataForm.major,
                'outsideDate': this.dataForm.outsideDate,
                'university': this.dataForm.university,
                'score': this.dataForm.score,
                'tel': this.dataForm.tel,
                'applyDate': this.dataForm.applyDate
              })
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
