(global["webpackJsonp"]=global["webpackJsonp"]||[]).push([["pages/info/index/index"],{"32fd":function(n,t,e){},"63b6":function(n,t,e){"use strict";(function(n){e("78ab");u(e("66fd"));var t=u(e("d2ac"));function u(n){return n&&n.__esModule?n:{default:n}}n(t.default)}).call(this,e("543d")["createPage"])},be8e:function(n,t,e){"use strict";e.r(t);var u=e("f001"),i=e.n(u);for(var o in u)"default"!==o&&function(n){e.d(t,n,(function(){return u[n]}))}(o);t["default"]=i.a},c8bd:function(n,t,e){"use strict";var u=e("32fd"),i=e.n(u);i.a},d18f:function(n,t,e){"use strict";var u={uNavbar:function(){return e.e("node-modules/uview-ui/components/u-navbar/u-navbar").then(e.bind(null,"7a66"))},uDivider:function(){return e.e("node-modules/uview-ui/components/u-divider/u-divider").then(e.bind(null,"1261"))},uLink:function(){return e.e("node-modules/uview-ui/components/u-link/u-link").then(e.bind(null,"45dc"))},uImage:function(){return e.e("node-modules/uview-ui/components/u-image/u-image").then(e.bind(null,"3d7c"))}},i=function(){var n=this,t=n.$createElement;n._self._c},o=[];e.d(t,"b",(function(){return i})),e.d(t,"c",(function(){return o})),e.d(t,"a",(function(){return u}))},d2ac:function(n,t,e){"use strict";e.r(t);var u=e("d18f"),i=e("be8e");for(var o in i)"default"!==o&&function(n){e.d(t,n,(function(){return i[n]}))}(o);e("c8bd");var a,r=e("f0c5"),c=Object(r["a"])(i["default"],u["b"],u["c"],!1,null,null,null,!1,u["a"],a);t["default"]=c.exports},f001:function(n,t,e){"use strict";(function(n){Object.defineProperty(t,"__esModule",{value:!0}),t.default=void 0;var u=e("a857"),i={data:function(){return{bottom:u.HOUSEINTERFACES.root+"img/bottom.png",universityLIst:[]}},onLoad:function(){this.getInfoList1()},methods:{goUniversity:function(t){n.navigateTo({url:"/pages/info/comment/comment?id="+t})},getInfoList1:function(){var t=this;n.request({url:u.INTERFACES.universityList,data:{},method:"GET",success:function(n){200===n.data.code&&(t.universityLIst=n.data.list)},fail:function(n,t){console.log("fail"+JSON.stringify(n))}})}}};t.default=i}).call(this,e("543d")["default"])}},[["63b6","common/runtime","common/vendor"]]]);