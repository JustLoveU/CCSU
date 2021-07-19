import Vue from 'vue'
import App from './App'

import uView from "uview-ui";
Vue.use(uView);

Vue.prototype.towxml = require('./static/towxml/index.js')
Vue.config.productionTip = false

Vue.prototype.checkLogin = function(backpage, backtype){	//定义一个全局函数
	var user  = uni.getStorageSync('user');//用户 id
	if(user == '' ){
		uni.redirectTo({url:'/pages/my/login/login?backpage='+backpage+'&backtype='+backtype});
		return false;
	}
	return user;		//登录成功会返回这些登录成功的信息
}

App.mpType = 'app'

const app = new Vue({
    ...App
})
app.$mount()
