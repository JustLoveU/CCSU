(global["webpackJsonp"]=global["webpackJsonp"]||[]).push([["pages/my/collection/collection"],{"0ac8":function(t,e,n){},5044:function(t,e,n){"use strict";n.r(e);var i=n("90c7"),o=n("8ea8");for(var r in o)"default"!==r&&function(t){n.d(e,t,(function(){return o[t]}))}(r);n("5f0d"),n("b5e7");var s,u=n("f0c5"),a=Object(u["a"])(o["default"],i["b"],i["c"],!1,null,"553be414",null,!1,i["a"],s);e["default"]=a.exports},"5f0d":function(t,e,n){"use strict";var i=n("0ac8"),o=n.n(i);o.a},"7d21":function(t,e,n){},"7e21":function(t,e,n){"use strict";(function(t){Object.defineProperty(e,"__esModule",{value:!0}),e.default=void 0;var i=n("18ae"),o={data:function(){return{time:i.HOUSEINTERFACES.root+"img/time.png",ing:i.HOUSEINTERFACES.root+"img/ing.png",universityimg:i.HOUSEINTERFACES.root+"img/university.png",user:{username:"",img:""},commentList:[],universityList:[],list:[{name:"评论"},{name:"收藏"},{name:"历史"},{name:"进度"}],current:0,tabsHeight:0,loadStatus:["loadmore","loadmore","loadmore","loadmore"],contentStatus:[!1,!1,!1,!1],page:[{current:1,hitCount:"",orders:[],pages:"",records:[],searchCount:"",size:6,total:""},{current:1,hitCount:"",orders:[],pages:"",records:[],searchCount:"",size:6,total:""},{current:1,hitCount:"",orders:[],pages:"",records:[],searchCount:"",size:6,total:""},{current:1,hitCount:"",orders:[],pages:"",records:[],searchCount:"",size:6,total:""}]}},onLoad:function(e){console.log(e),this.$refs.tabs.setFinishCurrent(e.current),this.current=e.current,this.select(e.current),this.user.img=t.getStorageSync("user").img,this.user.username=t.getStorageSync("user").username},methods:{goUniversity:function(e){t.navigateTo({url:"/pages/info/comment/comment?id="+e})},toPage:function(e){t.switchTab({url:e})},loadmore:function(t){this.page[t].current>=this.page[t].pages?this.loadStatus[t]="nomore":(this.page[t].current=this.page[t].current+1,0==t&&this.getCommentList(),1==t&&this.getCollectionList())},select:function(t){0==t&&this.getCommentList(),1==t&&this.getCollectionList()},getCommentList:function(){var e=this;this.loadStatus[0]="loading",t.request({url:i.INTERFACES.commentListByUser+t.getStorageSync("user").id,data:{page:this.page[0].current,limit:this.page[0].size},method:"GET",success:function(t){e.page[0]=t.data.page,e.commentList=e.commentList.concat(t.data.page.records),0==e.page[0].total?e.contentStatus[0]=!1:e.contentStatus[0]=!0,e.page[0].current>=e.page[0].pages?e.loadStatus[0]="nomore":e.loadStatus[0]="loadmore"},fail:function(t,e){console.log("fail"+JSON.stringify(t))}})},getCollectionList:function(){var e=this;this.loadStatus[1]="loading",t.request({url:i.INTERFACES.universityListByUser+t.getStorageSync("user").id,data:{page:this.page[1].current,limit:this.page[1].size},method:"GET",success:function(t){e.page[1]=t.data.page,e.universityList=e.universityList.concat(t.data.page.records),0==e.page[1].total?e.contentStatus[1]=!1:e.contentStatus[1]=!0,e.page[1].current>=e.page[1].pages?e.loadStatus[1]="nomore":e.loadStatus[1]="loadmore"},fail:function(t,e){console.log("fail"+JSON.stringify(t))}})},reachBottom:function(){var t=this;2!=this.current&&(this.loadStatus.splice(this.current,1,"loading"),setTimeout((function(){t.getOrderList(t.current)}),1200))},change:function(t){this.current=t,this.reset(),this.select(t)},reset:function(){this.commentList=[],this.universityList=[];for(var t=0;t<4;t++)this.page[t]={current:1,hitCount:"",orders:[],pages:"",records:[],searchCount:"",size:6,total:""}},transition:function(t){var e=t.detail.dx;this.$refs.tabs.setDx(e)},animationfinish:function(t){var e=t.detail.current;this.$refs.tabs.setFinishCurrent(e),this.current=e}}};e.default=o}).call(this,n("543d")["default"])},8564:function(t,e,n){"use strict";(function(t){n("e523");i(n("66fd"));var e=i(n("5044"));function i(t){return t&&t.__esModule?t:{default:t}}t(e.default)}).call(this,n("543d")["createPage"])},"8ea8":function(t,e,n){"use strict";n.r(e);var i=n("7e21"),o=n.n(i);for(var r in i)"default"!==r&&function(t){n.d(e,t,(function(){return i[t]}))}(r);e["default"]=o.a},"90c7":function(t,e,n){"use strict";var i={uTabsSwiper:function(){return Promise.all([n.e("common/vendor"),n.e("node-modules/uview-ui/components/u-tabs-swiper/u-tabs-swiper")]).then(n.bind(null,"c9e1"))},uLoadmore:function(){return n.e("node-modules/uview-ui/components/u-loadmore/u-loadmore").then(n.bind(null,"7e44"))},uDivider:function(){return n.e("node-modules/uview-ui/components/u-divider/u-divider").then(n.bind(null,"5124"))},uRow:function(){return n.e("node-modules/uview-ui/components/u-row/u-row").then(n.bind(null,"65dc"))},uCol:function(){return n.e("node-modules/uview-ui/components/u-col/u-col").then(n.bind(null,"29e0"))},uImage:function(){return n.e("node-modules/uview-ui/components/u-image/u-image").then(n.bind(null,"5294"))}},o=function(){var t=this,e=t.$createElement;t._self._c},r=[];n.d(e,"b",(function(){return o})),n.d(e,"c",(function(){return r})),n.d(e,"a",(function(){return i}))},b5e7:function(t,e,n){"use strict";var i=n("7d21"),o=n.n(i);o.a}},[["8564","common/runtime","common/vendor"]]]);