(global["webpackJsonp"]=global["webpackJsonp"]||[]).push([["common/main"],{"12b8":function(e,t,n){},"65f0":function(e,t,n){"use strict";var o=n("12b8"),r=n.n(o);r.a},"6c4f":function(e,t,n){"use strict";Object.defineProperty(t,"__esModule",{value:!0}),t.default=void 0;var o={onLaunch:function(){console.log("App Launch")},onShow:function(){console.log("App Show")},onHide:function(){console.log("App Hide")}};t.default=o},9775:function(e,t,n){"use strict";n.r(t);var o=n("6c4f"),r=n.n(o);for(var u in o)"default"!==u&&function(e){n.d(t,e,(function(){return o[e]}))}(u);t["default"]=r.a},"983e":function(e,t,n){"use strict";(function(e,t){n("e523");var o=c(n("66fd")),r=c(n("bd74")),u=c(n("35eb"));function c(e){return e&&e.__esModule?e:{default:e}}function f(e,t){var n=Object.keys(e);if(Object.getOwnPropertySymbols){var o=Object.getOwnPropertySymbols(e);t&&(o=o.filter((function(t){return Object.getOwnPropertyDescriptor(e,t).enumerable}))),n.push.apply(n,o)}return n}function a(e){for(var t=1;t<arguments.length;t++){var n=null!=arguments[t]?arguments[t]:{};t%2?f(Object(n),!0).forEach((function(t){i(e,t,n[t])})):Object.getOwnPropertyDescriptors?Object.defineProperties(e,Object.getOwnPropertyDescriptors(n)):f(Object(n)).forEach((function(t){Object.defineProperty(e,t,Object.getOwnPropertyDescriptor(n,t))}))}return e}function i(e,t,n){return t in e?Object.defineProperty(e,t,{value:n,enumerable:!0,configurable:!0,writable:!0}):e[t]=n,e}o.default.use(u.default),o.default.prototype.towxml=n("06fc"),o.default.config.productionTip=!1,o.default.prototype.checkLogin=function(t,n){var o=e.getStorageSync("user");return""==o?(e.redirectTo({url:"/pages/my/login/login?backpage="+t+"&backtype="+n}),!1):o},r.default.mpType="app";var l=new o.default(a({},r.default));t(l).$mount()}).call(this,n("543d")["default"],n("543d")["createApp"])},bd74:function(e,t,n){"use strict";n.r(t);var o=n("9775");for(var r in o)"default"!==r&&function(e){n.d(t,e,(function(){return o[e]}))}(r);n("65f0");var u,c,f,a,i=n("f0c5"),l=Object(i["a"])(o["default"],u,c,!1,null,null,null,!1,f,a);t["default"]=l.exports}},[["983e","common/runtime","common/vendor"]]]);