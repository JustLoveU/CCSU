(global["webpackJsonp"]=global["webpackJsonp"]||[]).push([["node-modules/uview-ui/components/u-link/u-link"],{"45dc":function(t,n,e){"use strict";e.r(n);var u=e("d6bb"),i=e("c73f");for(var a in i)"default"!==a&&function(t){e.d(n,t,(function(){return i[t]}))}(a);e("8197");var r,c=e("f0c5"),o=Object(c["a"])(i["default"],u["b"],u["c"],!1,null,"6cc42047",null,!1,u["a"],r);n["default"]=o.exports},8197:function(t,n,e){"use strict";var u=e("a932"),i=e.n(u);i.a},"9c86":function(t,n,e){"use strict";(function(t){Object.defineProperty(n,"__esModule",{value:!0}),n.default=void 0;var e={name:"u-link",props:{color:{type:String,default:"#2979ff"},fontSize:{type:[String,Number],default:28},underLine:{type:Boolean,default:!1},href:{type:String,default:""},mpTips:{type:String,default:"链接已复制，请在浏览器打开"},lineColor:{type:String,default:""}},methods:{openLink:function(){var n=this;t.setClipboardData({data:this.href,success:function(){t.hideToast()}}),this.$nextTick((function(){n.$u.toast(n.mpTips)}))}}};n.default=e}).call(this,e("543d")["default"])},a932:function(t,n,e){},c73f:function(t,n,e){"use strict";e.r(n);var u=e("9c86"),i=e.n(u);for(var a in u)"default"!==a&&function(t){e.d(n,t,(function(){return u[t]}))}(a);n["default"]=i.a},d6bb:function(t,n,e){"use strict";var u,i=function(){var t=this,n=t.$createElement;t._self._c},a=[];e.d(n,"b",(function(){return i})),e.d(n,"c",(function(){return a})),e.d(n,"a",(function(){return u}))}}]);
;(global["webpackJsonp"] = global["webpackJsonp"] || []).push([
    'node-modules/uview-ui/components/u-link/u-link-create-component',
    {
        'node-modules/uview-ui/components/u-link/u-link-create-component':(function(module, exports, __webpack_require__){
            __webpack_require__('543d')['createComponent'](__webpack_require__("45dc"))
        })
    },
    [['node-modules/uview-ui/components/u-link/u-link-create-component']]
]);
