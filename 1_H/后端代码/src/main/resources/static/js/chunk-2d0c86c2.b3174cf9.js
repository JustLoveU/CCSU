(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["chunk-2d0c86c2"],{"558d":function(e,t,l){"use strict";l.r(t);var a=function(){var e=this,t=e.$createElement,l=e._self._c||t;return l("d2-container",[l("template",{slot:"header"},[l("el-row",{staticStyle:{"margin-bottom":"10px"},attrs:{gutter:20}},[l("el-col",{attrs:{span:12}},[l("el-input",{attrs:{placeholder:"请输入标题",clearable:""},model:{value:e.article.title,callback:function(t){e.$set(e.article,"title",t)},expression:"article.title"}})],1),l("el-col",{attrs:{span:6}},[l("el-cascader",{attrs:{placeholder:"请选择菜单",options:e.options,props:{expandTrigger:"hover"}},on:{change:e.handleChange},model:{value:e.article.navId,callback:function(t){e.$set(e.article,"navId",t)},expression:"article.navId"}})],1),l("el-col",{attrs:{span:6}},[[e._v(" 是否设为轮播图 "),l("el-radio-group",{model:{value:e.article.ischart,callback:function(t){e.$set(e.article,"ischart",t)},expression:"article.ischart"}},[l("el-radio",{attrs:{label:0}},[e._v("否")]),l("el-radio",{attrs:{label:1}},[e._v("是")])],1)]],2)],1),l("el-row",{attrs:{gutter:20}},[l("el-col",{attrs:{span:12}},[l("el-input",{attrs:{placeholder:"请输入图片链接",clearable:""},model:{value:e.article.img,callback:function(t){e.$set(e.article,"img",t)},expression:"article.img"}})],1),l("el-col",{attrs:{span:12}},[l("el-input",{attrs:{placeholder:"请输入外部链接",clearable:""},model:{value:e.article.url,callback:function(t){e.$set(e.article,"url",t)},expression:"article.url"}})],1)],1)],1),[l("mavon-editor",{ref:"md",on:{imgAdd:e.imgAdd,save:e.saveDoc,change:e.updateDoc},model:{value:e.article.content,callback:function(t){e.$set(e.article,"content",t)},expression:"article.content"}})]],2)},i=[],r=l("b2d8"),n=(l("64e1"),{name:"page2",components:{mavonEditor:r["mavonEditor"]},data:function(){return{fullscreenLoading:!1,article:[{id:"",title:"",img:"",url:"",content:"",navId:"",ischart:0}],options:[{value:2,label:"实验室概况",children:[{value:3,label:"实验室简介"},{value:4,label:"现任领导"},{value:26,label:"联系我们"},{value:32,label:"组织结构"}]},{value:5,label:"动态新闻",children:[{value:6,label:"实验室新闻"},{value:7,label:"通知公告"}]},{value:8,label:"科学研究",children:[{value:9,label:"论文著作"},{value:10,label:"科研项目"},{value:11,label:"研究成果"},{value:12,label:"学术交流"}]},{value:13,label:"学术阶梯",children:[{value:14,label:"科研队伍"},{value:15,label:"招聘信息"}]},{value:27,label:"研究生教育",children:[{value:28,label:"毕业校友"},{value:29,label:"在校学生"},{value:30,label:"招生信息"}]}]}},created:function(){null!==this.$route.query.id&&-1!==this.$route.query.id?(this.getArticle(this.$route.query.id),this.article.id=this.$route.query.id):-1===this.$route.query.id||(this.article=localStorage.article)},methods:{updateDoc:function(e,t){localStorage.article=this.article},saveDoc:function(e,t){var l=this;localStorage.article=this.article,this.$confirm("是否保存文章?","提示",{confirmButtonText:"确定",cancelButtonText:"取消",type:"warning"}).then((function(){l.$axios.post("/admin/article/add",{id:l.article.id,title:l.article.title,img:l.article.img,url:l.article.url,content:l.article.content,navId:l.article.navId,ischart:l.article.ischart}).then((function(e){l.$notify({title:"成功",message:"添加成功",type:"success"}),l.$router.push({path:"/page1"}),console.log(e.data)})).catch((function(e){l.$notify({title:"失败",message:"添加失败",type:"danger"}),console.log(e)}))}))},getArticle:function(e){var t=this;this.$axios.get("/admin/article/one/"+e).then((function(e){t.article=e.data})).catch((function(e){console.log(e)}))},handleChange:function(e){this.article.navId=e[e.length-1]},imgAdd:function(e,t){var l=this,a=new FormData;a.append("image",t),console.log(a),this.$axios({url:"/admin/article/img",method:"post",data:a,headers:{"Content-Type":"multipart/form-data"}}).then((function(t){console.log(t),l.$refs.md.$img2Url(e,t.data.url)}))}}}),c=n,o=l("2877"),s=Object(o["a"])(c,a,i,!1,null,null,null);t["default"]=s.exports}}]);