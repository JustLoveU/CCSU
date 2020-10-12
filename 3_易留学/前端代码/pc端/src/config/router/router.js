import Vue from "vue";
import VueRouter from "vue-router";
import ViewUI from 'view-design';
Vue.use(ViewUI);

// 1、基本使用：https://blog.csdn.net/qq_40741855/article/details/85261051
// 2、登录控制：https://blog.csdn.net/blackhehaha/article/details/93631138

// 引入组件
import about from "../../view/about.vue";

import index from "../../view/index/index.vue";

// 我的
import login from "../../view/my/login.vue";

//资讯
import info_index from "../../view/info/index.vue"
import info_detail from "../../view/info/detail.vue"
import info_list from "../../view/info/list.vue"

//学校
import university_index from "../../view/university/index.vue"
import university_detail from "../../view/university/detail.vue"
import university_list from "../../view/university/list.vue"

//diy
import diy_index from "../../view/diy/index.vue"
import diy_result from "../../view/diy/result.vue"

// 要告诉 vue 使用 vueRouter
Vue.use(VueRouter);

const routes = [{
		path: "/about",
		component: about,
		meta: {
			needLogin: true //需要加校检判断的路由
		}
	},
	{
		name: 'login',
		path: "/login",
		component: login
	},
	{
		name: 'main',
		path: "/index",
		component: index
	},
	{
		name: 'index',
		path: "/",
		component: index
	},
	{
		name: 'info_index',
		path: "/info_index",
		component: info_index
	},
	{
		name: 'info_list',
		path: "/info_list",
		component: info_list
	},
	{
		name: 'info_detail',
		path: "/info_detail",
		component: info_detail
	},
	{
		name: 'university_index',
		path: "/university_index",
		component: university_index
	},
	{
		name: 'university_detail',
		path: "/university_detail",
		component: university_detail
	},
	{
		name: 'university_list',
		path: "/university_list",
		component: university_list
	},
	{
		name: 'diy_index',
		path: "/diy_index",
		component: diy_index
	},
	{
		name: 'diy_result',
		path: "/diy_result",
		component: diy_result
	}
]

var router = new VueRouter({
	mode: 'history',
	routes
})

//登录权限的控制
router.beforeEach((to, from, next) => {
	ViewUI.LoadingBar.start();
	//需要登录
	if (to.meta.needLogin) {
		if (!localStorage.getItem("user")) {
			localStorage.setItem("to", to.path)
			next({
				name: 'login'
			})
		} else next()
	} else {
		//表示不需要登录
		next(); //继续往后走
	}
})

router.afterEach(route => {
    ViewUI.LoadingBar.finish();
});

export default router;
