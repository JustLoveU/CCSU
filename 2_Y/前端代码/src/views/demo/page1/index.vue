<template>
  <d2-container>
    <template slot="header">
      <el-row :gutter="20">
        <el-col :span="1">
        <el-col :span="16"></el-col>
            <el-button round @click="handleAdd()" >新增</el-button></el-col>
        <el-col :span="6" style="float: right;">
            <el-input v-model="search" suffix-icon="el-icon-search" @input="changeSearch"  placeholder="输入关键字搜索" /></el-col>
      </el-row>
    </template>
    <template>
      <el-table v-loading="loading" element-loading-text="拼命加载中" element-loading-spinner="el-icon-loading" :data="pageData"
        style="width: 100%">
        <el-table-column prop="id" label="ID" width="80">
        </el-table-column>
        <el-table-column prop="data" :formatter="dateFormat" label="日期" sortable width="200">
        </el-table-column>
        <el-table-column prop="title" label="标题" width="420">
        </el-table-column>
        <el-table-column prop="nav" label="菜单" :filters="nav" :filter-method="filterTag">
        </el-table-column>
        <el-table-column prop="ischart" label="轮播图"  :formatter="formatChart" :filters="[{text: '是',value: 1}, {text: '否', value: 0}]" :filter-method="filterChart">
        </el-table-column>
        <el-table-column fixed="right" label="操作" width="250">
          <template slot-scope="scope">
            <el-button @click="handleClick(scope.row)" size="small">查看</el-button>
            <el-button @click="handleEdit(scope.row)" size="small">编辑</el-button>
            <el-button @click="handledelete(scope.row)" type="danger" size="small">删除</el-button>
            <!-- <el-button @click="handleChart(scope.row)"  size="small">设为轮播图</el-button> -->
          </template>
        </el-table-column>
      </el-table>
      <div style="float: right;" v-show="search == ''">
        <el-pagination @size-change="handleSizeChange1" @current-change="handleCurrentChange1" :current-page="currentPage1"
          :page-sizes="[10,15,20,50,100,1000]" :page-size="pageSize" layout="total, sizes, prev, pager, next, jumper" :total="total1">
        </el-pagination>
      </div>
    </template>
  </d2-container>
</template>

<script>
import moment from 'moment'
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
      search: '',
      nav: [
        {
          text: '国家政策',
          value: '国家政策'
        },
        {
          text: '学者新论',
          value: '学者新论'
        },
        {
          text: '机构简介',
          value: '机构简介'
        },
        {
          text: '联系方式',
          value: '联系方式'
        },
        {
          text: '工作动态',
          value: '工作动态'
        },
        {
          text: '通知公告',
          value: '通知公告'
        },
        {
          text: '学术活动',
          value: '学术活动'
        },
        {
          text: '科研项目',
          value: '科研项目'
        },
        {
          text: '社会服务',
          value: '社会服务'
        },
        {
          text: '平台建设',
          value: '平台建设'
        }
      ]
    }
  },
  created () {
    this.getArticle()
  },
  methods: {
    handleClick (row) {
      if (row.nav == null) {
        window.location.href = row.url
      } else {
        this.$router.push({
          path: '/page3',
          query: {
            id: row.id
          }
        })
      }
    },
    handleEdit (row) {
      this.$router.push({
        path: '/page2',
        query: {
          id: row.id
        }
      })
    },
    handledelete (row) {
      this.$confirm('此操作将永久删除该文章, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        this.$axios.get('/admin/article/delete/' + row.id).then(res => {
          this.$message({
            type: 'success',
            message: '删除成功!'
          })
          this.getArticle()
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
    handleChart (row) {
      if (row.img == null || row.img === '') {
        this.$confirm('如果设为轮播图需要一张图片，先去设置图片链接').then(({ value }) => {
          this.$router.push({
            path: '/page2',
            query: {
              id: row.id
            }
          })
        }).catch(() => {
          this.$message({
            type: 'info',
            message: '取消设置'
          })
        })
      } else {
        // 设置为轮播图
      }
    },
    handleAdd () {
      this.$router.push({
        path: '/page2',
        query: {
          id: -1
        }
      })
    },
    filterTag (value, row) {
      return row.nav === value
    },

    filterChart (value, row) {
      return row.ischart === value
    },
    formatChart (row, column, cellValue) {
      if (row.ischart === 1) {
        return '是'
      } else {
        return '否'
      }
    },
    formatTag (row, column, cellValue) {
      var s = ''
      this.nav.forEach(item => {
        if (item.value === row.navId) {
          s = item.text
        }
      })
      return s
    },
    getArticle () {
      this.$axios.get('/admin/article/list').then(res => {
        this.tableData = res.data
        this.loading = false
        this.currentChangePage(this.tableData, 1)
        this.total1 = res.data.length
      }).catch(err => {
        console.log(err)
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
    },
    dateFormat (row, column, cellValue, index) {
      const daterc = row[column.property]
      if (daterc != null) {
        return moment(daterc).format('YYYY-MM-DD')
      }
    }
  }
}
</script>
