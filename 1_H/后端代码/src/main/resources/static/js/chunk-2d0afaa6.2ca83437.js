(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["chunk-2d0afaa6"],{"0efa":function(e,t,a){"use strict";a.r(t);var n=function(){var e=this,t=e.$createElement,a=e._self._c||t;return a("d2-container",[a("template",{slot:"header"},[a("el-row",{attrs:{gutter:20}},[a("el-col",{attrs:{span:1}},[a("el-button",{attrs:{round:""},on:{click:function(t){return e.handleAdd()}}},[e._v("新增")])],1),a("el-col",{staticStyle:{float:"right"},attrs:{span:6}},[a("el-input",{attrs:{"suffix-icon":"el-icon-search",placeholder:"输入关键字搜索"},on:{input:e.changeSearch},model:{value:e.search,callback:function(t){e.search=t},expression:"search"}})],1),a("el-col",{attrs:{span:16}})],1)],1),[a("el-table",{directives:[{name:"loading",rawName:"v-loading",value:e.loading,expression:"loading"}],staticStyle:{width:"100%"},attrs:{"element-loading-text":"拼命加载中","element-loading-spinner":"el-icon-loading",data:e.pageData}},[a("el-table-column",{attrs:{prop:"profileId",label:"ID",width:"80"}}),a("el-table-column",{attrs:{prop:"profileCnname",label:"名字",sortable:"",width:"200"}}),a("el-table-column",{attrs:{prop:"profileEducation",label:"学历",width:"200"}}),a("el-table-column",{attrs:{prop:"profilePosition",label:"职称",width:"200"}}),a("el-table-column",{attrs:{prop:"profileEmail",label:"邮箱"}}),a("el-table-column",{attrs:{prop:"profileTelephone",label:"手机"}}),a("el-table-column",{attrs:{prop:"profileStatus","header-align":"center",align:"center",label:"是否显示"},scopedSlots:e._u([{key:"default",fn:function(t){return[a("el-switch",{attrs:{"active-color":"#13ce66","inactive-color":"#ff4949","active-value":!0,"inactive-value":!1},on:{change:function(a){return e.changeStatus(t.row,a)}},model:{value:t.row.profileStatus,callback:function(a){e.$set(t.row,"profileStatus",a)},expression:"scope.row.profileStatus"}})]}}])}),a("el-table-column",{attrs:{fixed:"right",label:"操作",width:"250"},scopedSlots:e._u([{key:"default",fn:function(t){return[a("el-button",{attrs:{size:"small"},on:{click:function(a){return e.handleEdit(t.row)}}},[e._v("编辑")]),a("el-button",{attrs:{type:"danger",size:"small"},on:{click:function(a){return e.handledelete(t.row)}}},[e._v("删除")])]}}])})],1),a("div",{directives:[{name:"show",rawName:"v-show",value:""==e.search,expression:"search == ''"}],staticStyle:{float:"right"}},[a("el-pagination",{attrs:{"current-page":e.currentPage1,"page-sizes":[10,30,50,100,1e3],"page-size":e.pageSize,layout:"total, sizes, prev, pager, next, jumper",total:e.total1},on:{"size-change":e.handleSizeChange1,"current-change":e.handleCurrentChange1}})],1)]],2)},l=[],i=(a("4de4"),a("c975"),a("45fc"),a("b64b"),{name:"page1",data:function(){return{total1:1,currentPage1:1,pageSize:10,tableData:[],pageData:[],loading:!0,search:""}},created:function(){this.getProfile()},methods:{getProfile:function(){var e=this;this.$axios.get("/admin/profile/list").then((function(t){e.tableData=t.data,e.loading=!1,e.currentChangePage(e.tableData,1),e.total1=t.data.length})).catch((function(e){console.log(e)}))},handleEdit:function(e){console.log(e),this.$router.push({path:"/edit",query:{id:e.profileId}})},handleAdd:function(){this.$router.push({path:"/edit"})},handleClick:function(e){this.$router.push({path:"/detail",query:{id:e.profileId}})},changeStatus:function(e,t){var a=this;console.log(e+t),this.$axios.post("/admin/profile/update",{profileId:e.profileId,profileStatus:t}).then((function(e){var t=e.data;t&&0===t.code?a.$message({message:"操作成功",type:"success"}):a.$message.error(t.msg)}))},handledelete:function(e){var t=this;this.$confirm("此操作将永久删除该用户, 是否继续?","提示",{confirmButtonText:"确定",cancelButtonText:"取消",type:"warning"}).then((function(){t.$axios.delete("/admin/profile/one/"+e.profileId).then((function(e){t.$message({type:"success",message:"删除成功!"}),t.getProfile()})).catch((function(){t.$message({type:"info",message:"删除失败"})}))})).catch((function(){t.$message({type:"info",message:"已取消删除"})}))},handleSizeChange1:function(e){this.pageSize=e,this.handleCurrentChange1(this.currentPage1)},handleCurrentChange1:function(e){this.currentPage1=e,this.currentChangePage(this.tableData,e)},currentChangePage:function(e,t){var a=(t-1)*this.pageSize,n=t*this.pageSize;for(this.pageData=[];a<n;a++)e[a]&&this.pageData.push(e[a])},changeSearch:function(e){this.pageData=this.tableData.filter((function(t){return Object.keys(t).some((function(a){return String(t[a]).toLowerCase().indexOf(e)>-1}))}))}}}),o=i,r=a("2877"),s=Object(r["a"])(o,n,l,!1,null,null,null);t["default"]=s.exports}}]);