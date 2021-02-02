<template>
  <d2-container>
    <el-row>
      <el-input
        type="textarea"
        autosize
        placeholder="请输入内容"
        v-model="value">
      </el-input>
    </el-row>
    <div style="height: 30px;"></div>
    <el-row>
      <el-button @click="insert">修改</el-button>
    </el-row>
  </d2-container>
</template>

<script>
export default {
  name: 'page4',
  data () {
    return {
      value: '',
      id: 0
    }
  },
  created () {
    this.getImfo()
  },
  methods: {
    getImfo () {
      this.$axios.get('/admin/other/get/' + 1).then(res => {
        console.log(res.data)
        this.value = res.data.content
        this.id = res.data.id
      }).catch(err => {
        console.log(err)
      })
    },
    insert () {
      this.$axios.post('/admin/other/add', {
        content: this.value,
        id: this.id
      }).then(({
        data
      }) => {
        if (data.code === 0) {
          this.$message({
            message: '操作成功',
            type: 'success',
            duration: 1500,
            onClose: () => {
              this.getImfo()
            }
          })
        }
      })
    }
  }
}
</script>

<style>
</style>
