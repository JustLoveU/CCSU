(global["webpackJsonp"]=global["webpackJsonp"]||[]).push([["pages/info/detail/detail"],{"0f8c":function(n,e,t){"use strict";var u={uNavbar:function(){return t.e("node-modules/uview-ui/components/u-navbar/u-navbar").then(t.bind(null,"331b"))},uLine:function(){return t.e("node-modules/uview-ui/components/u-line/u-line").then(t.bind(null,"3c7a"))},uRow:function(){return t.e("node-modules/uview-ui/components/u-row/u-row").then(t.bind(null,"65dc"))},uCol:function(){return t.e("node-modules/uview-ui/components/u-col/u-col").then(t.bind(null,"29e0"))},uIcon:function(){return t.e("node-modules/uview-ui/components/u-icon/u-icon").then(t.bind(null,"bb3f"))},uReadMore:function(){return t.e("node-modules/uview-ui/components/u-read-more/u-read-more").then(t.bind(null,"05df"))}},o=function(){var n=this,e=n.$createElement;n._self._c},i=[];t.d(e,"b",(function(){return o})),t.d(e,"c",(function(){return i})),t.d(e,"a",(function(){return u}))},"30f2":function(n,e,t){"use strict";t.r(e);var u=t("c7c5"),o=t.n(u);for(var i in u)"default"!==i&&function(n){t.d(e,n,(function(){return u[n]}))}(i);e["default"]=o.a},"506c":function(n,e,t){"use strict";var u=t("c22d"),o=t.n(u);o.a},7980:function(n,e,t){"use strict";t.r(e);var u=t("0f8c"),o=t("30f2");for(var i in o)"default"!==i&&function(n){t.d(e,n,(function(){return o[n]}))}(i);t("506c");var c,r=t("f0c5"),f=Object(r["a"])(o["default"],u["b"],u["c"],!1,null,null,null,!1,u["a"],c);e["default"]=f.exports},c22d:function(n,e,t){},c7c5:function(n,e,t){"use strict";(function(n){Object.defineProperty(e,"__esModule",{value:!0}),e.default=void 0;var u=function(){t.e("common/vendor").then(function(){return resolve(t("50c4"))}.bind(null,t)).catch(t.oe)},o=t("18ae"),i={components:{towxml:u},data:function(){return{id:"",towxmlFunc:t("06fc"),info:{content:"",id:"",time:"",title:"",typeId:"",universityId:"",url:"",view:""}}},onLoad:function(n){this.id=n.id,this.getInfo()},methods:{getInfo:function(){var e=this;n.request({url:o.INTERFACES.infoOne+this.id,data:{},method:"GET",success:function(n){e.info=n.data.info,e.info.content=e.towxmlFunc(n.data.info.content,"markdown")},fail:function(n,e){console.log("fail"+JSON.stringify(n))}})}}};e.default=i}).call(this,t("543d")["default"])},f396:function(n,e,t){"use strict";(function(n){t("e523");u(t("66fd"));var e=u(t("7980"));function u(n){return n&&n.__esModule?n:{default:n}}n(e.default)}).call(this,t("543d")["createPage"])}},[["f396","common/runtime","common/vendor"]]]);