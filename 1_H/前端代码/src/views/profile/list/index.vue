<template>
  <d2-container>
    <template slot="header">
      <el-row :gutter="20">
        <el-col :span="1">
            <el-button round @click="handleAdd()" >新增</el-button></el-col>
        <el-col :span="6" style="float: right;">
            <el-input v-model="search" suffix-icon="el-icon-search" @input="changeSearch"  placeholder="输入关键字搜索" /></el-col>
        <el-col :span="16"></el-col>
      </el-row>
    </template>
    <template>
      <el-table v-loading="loading" element-loading-text="拼命加载中" element-loading-spinner="el-icon-loading" :data="pageData"
        style="width: 100%">
        <el-table-column prop="profileId" label="ID" width="80">
        </el-table-column>
        <el-table-column prop="profileCnname"  label="名字" sortable width="200">
        </el-table-column>
        <el-table-column prop="profileEducation" label="学历" width="200">
        </el-table-column>
        <el-table-column prop="profilePosition" label="职称" width="200">
        </el-table-column>
        <el-table-column prop="profileEmail" label="邮箱">
        </el-table-column>
        <el-table-column prop="profileTelephone" label="手机">
        </el-table-column>
        <el-table-column prop="profileStatus" header-align="center" align="center" label="是否显示">
        <template slot-scope="scope">
            <el-switch
              v-model="scope.row.profileStatus"
              active-color="#13ce66"
              inactive-color="#ff4949"
              :active-value='true'
              :inactive-value='false'
              @change="changeStatus(scope.row,$event)">
            </el-switch>
           </template>
        </el-table-column>
        <el-table-column fixed="right" label="操作" width="250">
          <template slot-scope="scope">
            <!-- <el-button @click="handleClick(scope.row)" size="small">查看</el-button> -->
            <el-button @click="handleEdit(scope.row)" size="small">编辑</el-button>
            <el-button @click="handledelete(scope.row)" type="danger" size="small">删除</el-button>
            <!-- <el-button @click="handleChart(scope.row)"  size="small">设为轮播图</el-button> -->
          </template>
        </el-table-column>
      </el-table>
      <div style="float: right;" v-show="search == ''">
        <el-pagination @size-change="handleSizeChange1" @current-change="handleCurrentChange1" :current-page="currentPage1"
          :page-sizes="[10,30,50,100,1000]" :page-size="pageSize" layout="total, sizes, prev, pager, next, jumper" :total="total1">
        </el-pagination>
      </div>
    </template>
  </d2-container>
</template>

<script>
export default {
  name: 'page1',
  data () {
    return {
      total1: 1,
      currentPage1: 1,
      pageSize: 10,
      tableData: [],
      pageData: [],
      loading: true,
      search: ''
    }
  },
  created () {
    this.getProfile()
  },
  methods: {
    getProfile () {
      this.$axios.get('/admin/profile/list').then(res => {
        this.tableData = res.data
        this.loading = false
        this.currentChangePage(this.tableData, 1)
        this.total1 = res.data.length
      }).catch(err => {
        console.log(err)
      })
    },
    handleEdit (row) {
      console.log(row)
      this.$router.push({
        path: '/edit',
        query: {
          id: row.profileId
        }
      })
    },
    handleAdd () {
      this.$router.push({
        path: '/edit'
      })
    },
    handleClick (row) {
      this.$router.push({
        path: '/detail',
        query: {
          id: row.profileId
        }
      })
    },
    changeStatus (data, $event) {
      console.log(data + $event)
      this.$axios.post('/admin/profile/update', {
        profileId: data.profileId,
        profileStatus: $event
      }).then(({
        data
      }) => {
        if (data && data.code === 0) {
          this.$message({
            message: '操作成功',
            type: 'success'
          })
        } else {
          this.$message.error(data.msg)
        }
      })
    },
    handledelete (row) {
      this.$confirm('此操作将永久删除该用户, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        this.$axios.delete('/admin/profile/one/' + row.profileId).then(res => {
          this.$message({
            type: 'success',
            message: '删除成功!'
          })
          this.getProfile()
        }).catch(() => {
          this.$message({
            type: 'info',
            message: '删除失败'
          })
        })
      }).catch(() => {
        this.$message({
          type: 'info',
          message: '已取消删除'
        })
      })
    },
    // 每页条数切换
    handleSizeChange1: function (pageSize) {
      this.pageSize = pageSize
      this.handleCurrentChange1(this.currentPage1)
    },
    // 页码切换
    handleCurrentChange1: function (currentPage) {
      this.currentPage1 = currentPage
      this.currentChangePage(this.tableData, currentPage)
    },
    // 分页方法（重点）
    currentChangePage (list, currentPage) {
      let from = (currentPage - 1) * this.pageSize
      const to = currentPage * this.pageSize
      this.pageData = []
      for (; from < to; from++) {
        if (list[from]) {
          this.pageData.push(list[from])
        }
      }
    },
    changeSearch (string) {
      this.pageData = this.tableData.filter(function (dataNews) {
        return Object.keys(dataNews).some(function (key) {
          return String(dataNews[key]).toLowerCase().indexOf(string) > -1
        })
      })
    }
  }
}
</script>

<style>
</style>
