(global["webpackJsonp"]=global["webpackJsonp"]||[]).push([["pages/info/comment/comment"],{"0ef5":function(t,n,e){"use strict";var i={uNavbar:function(){return e.e("node-modules/uview-ui/components/u-navbar/u-navbar").then(e.bind(null,"331b"))},uIcon:function(){return e.e("node-modules/uview-ui/components/u-icon/u-icon").then(e.bind(null,"bb3f"))},uRate:function(){return e.e("node-modules/uview-ui/components/u-rate/u-rate").then(e.bind(null,"2010"))},uDivider:function(){return e.e("node-modules/uview-ui/components/u-divider/u-divider").then(e.bind(null,"5124"))},uGrid:function(){return e.e("node-modules/uview-ui/components/u-grid/u-grid").then(e.bind(null,"0166"))},uGridItem:function(){return e.e("node-modules/uview-ui/components/u-grid-item/u-grid-item").then(e.bind(null,"4f53"))},uImage:function(){return e.e("node-modules/uview-ui/components/u-image/u-image").then(e.bind(null,"5294"))},uRow:function(){return e.e("node-modules/uview-ui/components/u-row/u-row").then(e.bind(null,"65dc"))},uCol:function(){return e.e("node-modules/uview-ui/components/u-col/u-col").then(e.bind(null,"29e0"))},uLine:function(){return e.e("node-modules/uview-ui/components/u-line/u-line").then(e.bind(null,"3c7a"))},uLoadmore:function(){return e.e("node-modules/uview-ui/components/u-loadmore/u-loadmore").then(e.bind(null,"7e44"))},uModal:function(){return e.e("node-modules/uview-ui/components/u-modal/u-modal").then(e.bind(null,"1576"))},uInput:function(){return Promise.all([e.e("common/vendor"),e.e("node-modules/uview-ui/components/u-input/u-input")]).then(e.bind(null,"5ef8"))}},o=function(){var t=this,n=t.$createElement;t._self._c},u=[];e.d(n,"b",(function(){return o})),e.d(n,"c",(function(){return u})),e.d(n,"a",(function(){return i}))},5301:function(t,n,e){"use strict";e.r(n);var i=e("c9fc"),o=e.n(i);for(var u in i)"default"!==u&&function(t){e.d(n,t,(function(){return i[t]}))}(u);n["default"]=o.a},ad0c:function(t,n,e){"use strict";e.r(n);var i=e("0ef5"),o=e("5301");for(var u in o)"default"!==u&&function(t){e.d(n,t,(function(){return o[t]}))}(u);e("e8cd");var s,c=e("f0c5"),a=Object(c["a"])(o["default"],i["b"],i["c"],!1,null,null,null,!1,i["a"],s);n["default"]=a.exports},bf44:function(t,n,e){},c9fc:function(t,n,e){"use strict";(function(t){Object.defineProperty(n,"__esModule",{value:!0}),n.default=void 0;var i=e("18ae"),o={data:function(){return{src:[i.HOUSEINTERFACES.root+"university_detail/u1.png",i.HOUSEINTERFACES.root+"university_detail/u2.png",i.HOUSEINTERFACES.root+"university_detail/u3.png",i.HOUSEINTERFACES.root+"university_detail/u4.png",i.HOUSEINTERFACES.root+"university_detail/u5.png",i.HOUSEINTERFACES.root+"university_detail/u6.png"],src1:[i.HOUSEINTERFACES.root+"university_detail/1.png",i.HOUSEINTERFACES.root+"university_detail/2.png",i.HOUSEINTERFACES.root+"university_detail/3.png",i.HOUSEINTERFACES.root+"university_detail/4.png",i.HOUSEINTERFACES.root+"university_detail/1.png",i.HOUSEINTERFACES.root+"university_detail/3.png"],CommentInput:"",status:"loadmore",inputShow:!1,height:200,id:"",total:0,pages:"",page:1,limit:6,university:{id:"",img:"",infoTypeId:"",intro:"",name:"",star:"",tuition:"",view:""},infoList:[],commentList:[],collection:!1,collection_id:""}},onLoad:function(t){this.id=t.id,this.getuniversity(),this.getInfoList(),this.getCommentList(),this.getcollection()},onReachBottom:function(){this.loadmore()},methods:{open:function(){var n=this.checkLogin("/pages/info/comment/comment?id="+this.id,2);if(!n)return t.showToast({title:"请先登录！",icon:"none"}),!1;this.inputShow=!0},toDetail:function(n){t.navigateTo({url:"/pages/info/detail/detail?id="+n})},comment:function(){var n=this;""===this.CommentInput?t.showToast({title:"请输入！",icon:"none"}):(t.request({url:i.INTERFACES.commentSave,data:{userId:t.getStorageSync("user").id,universityId:this.id,content:this.CommentInput},method:"POST",success:function(e){t.showToast({title:"评论成功！",icon:"none"}),t.redirectTo({url:"/pages/info/comment/comment?id="+n.id})},fail:function(t,n){console.log("fail"+JSON.stringify(t))}}),this.CommentInput="")},loadmore:function(){this.page>=this.totalPage?this.status="nomore":(this.page=this.page+1,this.getCommentList())},getuniversity:function(){var n=this;t.request({url:i.INTERFACES.universityOne+this.id,data:{},method:"GET",success:function(t){n.university=t.data.university},fail:function(t,n){console.log("fail"+JSON.stringify(t))}})},getInfoList:function(){var n=this;t.request({url:i.INTERFACES.InfoListByUniversity+this.id,data:{},method:"GET",success:function(t){n.infoList=t.data.list},fail:function(t,n){console.log("fail"+JSON.stringify(t))}})},getCommentList:function(){var n=this;this.status="loading",t.request({url:i.INTERFACES.commentListByUniversity+this.id,data:{page:this.page,limit:this.limit},method:"GET",success:function(t){n.commentList=n.commentList.concat(t.data.page.records),n.total=t.data.page.total,n.pages=t.data.page.pages,t.data.page.current>=t.data.page.pages?n.status="nomore":n.status="loadmore"},fail:function(t,n){console.log("fail"+JSON.stringify(t))}})},getcollection:function(){var n=this;t.request({url:i.INTERFACES.collectionOne,data:{userId:t.getStorageSync("user").id,universityId:this.id},method:"POST",success:function(t){200===t.data.code&&(n.collection=!0,n.collection_id=t.data.collection.id)},fail:function(t,n){console.log("fail"+JSON.stringify(t))}})},setcollection:function(){var n=this;if(1==this.collection)t.request({url:i.INTERFACES.collectionDeleteOne+this.collection_id,method:"GET",data:{},success:function(e){200===e.data.code&&(n.collection=!1,t.showToast({title:"取消收藏成功！",icon:"none"}))},fail:function(t,n){console.log("fail"+JSON.stringify(t))}});else{var e=this.checkLogin("/pages/info/comment/comment?id="+this.id,2);if(!e)return t.showToast({title:"请先登录！",icon:"none"}),!1;t.request({url:i.INTERFACES.collectionSave,data:{userId:t.getStorageSync("user").id,universityId:this.id},method:"POST",success:function(e){200===e.data.code&&(t.showToast({title:"收藏成功！",icon:"none"}),n.getcollection())},fail:function(t,n){console.log("fail"+JSON.stringify(t))}})}}}};n.default=o}).call(this,e("543d")["default"])},e8cd:function(t,n,e){"use strict";var i=e("bf44"),o=e.n(i);o.a},ed61:function(t,n,e){"use strict";(function(t){e("e523");i(e("66fd"));var n=i(e("ad0c"));function i(t){return t&&t.__esModule?t:{default:t}}t(n.default)}).call(this,e("543d")["createPage"])}},[["ed61","common/runtime","common/vendor"]]]);