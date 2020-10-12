// var amapFile = require('D:/Documents/HBuilderProjects/website_2_Stu/static/js/amap-wx.js');
// var that = this;
// var myAmapFun = new amapFile.AMapWX({key:'f4dd35f116ab9639aa678d08eeace8e9'});  
<template>
	<view>
		<u-navbar :is-back="false" title="易留学" :border-bottom="false">
				<view style="width: 100%;">
					<view style="padding-left: 40rpx;"><u-image height="120rpx" width="120rpx" :src="logo"></u-image></view>
				</view>
			</u-navbar>
		<view>
			<view style="margin: 10rpx;">
				<navigator url="/pages/index/search/search" navigateTo>
					<!-- <u-image width="100%" height="88rpx" :border-radius="4" :src="search" style="background-color: #FFFFFF;"></u-image> -->
					<u-search placeholder="搜索大学"  @change="toPage('/pages/index/search/search')" @click="toPage('/pages/index/search/search')" :disabled="true" :show-action="false"></u-search>
				</navigator>
			</view>
		</view>
		<view>
			<view style="padding: 30rpx;font-size: 30rpx;">
				热门院校：
			</view>
		</view>
		<view>
			<!--水平滚动-->
			<scroll-view scroll-x="true" style=" white-space: nowrap; display: flex">
				<!--  display: inline-block-->
				<view :style="'margin: 20rpx; width: 125px; height:175px; display: inline-block;background-image: url('+item.pic+');background-size: 100% 100%;border-radius:12rpx'"
				 v-for="(item,index) in inner" :key="index"  @click="toPage('/pages/info/comment/comment?id=' + item.id,2)">
					<!-- <u-image width="100%" height="175px" :src="item" :border-radius="12"></u-image> -->
					<text style="padding: 16rpx; color: #FFFFFF;font-size: 35rpx;">{{item.name}}</text>
				</view>
			</scroll-view>
		</view>
		<view>
			<view style="padding: 30rpx;font-size: 30rpx;">
				智能定制：
			</view>
		</view>
		<view>
			<view class="margin" @tap="toPage('/pages/diy/index/index',1)">
				<u-image width="100%" height="40px" :src="diy" :border-radius="12"></u-image>
			</view>
		</view>
		<view>
			<view style="padding: 30rpx;font-size: 30rpx;">
				我们的优势：
			</view>
		</view>
		<u-row gutter="16">
			<u-col span="6" v-for="(item,index) in img" :key="index">
				<view class="margin">
					<u-image width="100%" height="120px" :src="item" :border-radius="12"></u-image>
				</view>
			</u-col>
		</u-row>
		<view style="padding-bottom:30rpx"><u-image width="100%" height="150rpx" :border-radius="4" :src="bottom" style="background-color: #FFFFFF;"></u-image>
		<!-- <image src="../../../static/img/japan_university.png" style="width: 100%;"></image> -->
		</view>
	</view>
</template>

<script>
	const api = require('static/js/api');
	export default {
		data() {
			return {
				logo: api.HOUSEINTERFACES.root+ 'img/logo.png',
				bottom: api.HOUSEINTERFACES.root+ 'img/bottom.png',
				search: api.HOUSEINTERFACES.root + 'img/search.png',
				// inner: [{
				// 	url: 'https://ou.lomtom.top/country/US.png',
				// 	name: '美国'
				// }, {
				// 	url: 'https://ou.lomtom.top/country/UK.png',
				// 	name: '英国'
				// }, {
				// 	url: 'https://ou.lomtom.top/country/Australia.png',
				// 	name: '澳大利亚'
				// }, {
				// 	url: 'https://ou.lomtom.top/country/French.png',
				// 	name: '法国'
				// }, {
				// 	url: 'https://ou.lomtom.top/country/Japan.png',
				// 	name: '日本'
				// }],
				inner: [],
				img: [
					'https://ou.lomtom.top/img/lx1.jpg',
					'https://ou.lomtom.top/img/lx2.jpg',
					'https://ou.lomtom.top/img/lx3.jpg',
					'https://ou.lomtom.top/img/lx6.jpg'
				],
				diy: 'https://ou.lomtom.top/img/diy.png'

			}
		},
		onLoad() {
			this.getinner()
		},
		methods: {
			toPage(url, flag) {
				if (flag == 1) {
					uni.switchTab({
						url: url
					})
				} else {
					uni.navigateTo({
						url: url
					})
				}
			},
			getinner() {
				uni.request({
					url: api.INTERFACES.universityList2,
					data: {},
					method: 'GET',
					success: res => {
						this.inner = res.data.list
					},
					fail: (res, code) => {
						console.log('fail' + JSON.stringify(res));
					}
				});
			}
		}
	}
</script>

<style>
	.margin {
		padding: 15rpx;
	}
</style>
