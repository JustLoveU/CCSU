import Vue from 'vue'
import App from './App.vue'
import ViewUI from 'view-design';
import 'view-design/dist/styles/iview.css';
import router from './config/router/router.js'
import url from './config/api/url.js'
import api from './config/api/request.js'
import global from './config/global/global.js'

Vue.prototype.$url = url
Vue.prototype.$api = api
Vue.prototype.$global = global

Vue.use(ViewUI);
Vue.config.productionTip = false

new Vue({
	router,
  render: h => h(App),
}).$mount('#app')


// 1、解决跨域问题：https://blog.csdn.net/Mint6/article/details/104726325
// 2、封转axios请求：https://blog.csdn.net/qq_34322905/article/details/83149266
// 3、vue-router基本使用：https://blog.csdn.net/qq_40741855/article/details/85261051
// 4、vue-router登录控制：https://blog.csdn.net/blackhehaha/article/details/93631138
// 5、vue-router传参：https://www.jianshu.com/p/ed6f2d4b2d0e
// 6、去掉url中的#：https://www.jianshu.com/p/444476c37c01
// 7、只显示一行：https://blog.csdn.net/eileen823/article/details/57417976
// 8、多色图标：https://www.iconfont.cn/collections/detail?spm=a313x.7781069.1998910419.d9df05512&cid=23866
// 9、使用!important来覆盖之前的css样式：https://developer.mozilla.org/zh-CN/docs/Web/CSS/Specificity
// 10、vue-router 如何在新窗口打开页面：https://www.jianshu.com/p/a36a417527d2



//部署：
// 1、安装nginx：https://segmentfault.com/a/1190000018109309
// 2、修改nginx默认端口：https://blog.csdn.net/Anasta198110/article/details/80736119
// 3、解决权限禁止问题：https://blog.csdn.net/guizishou00/article/details/81894168
// 4、部署静态网页：https://blog.csdn.net/weixin_42565137/article/details/90577950
// 5、nginx配置ssl证书：https://help.aliyun.com/document_detail/98728.html