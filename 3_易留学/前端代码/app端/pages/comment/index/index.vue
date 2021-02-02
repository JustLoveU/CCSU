<template>
	<view>
		<u-navbar :is-back="false" title="　" :border-bottom="false">
			<view style="width: 100%;">
				<u-search placeholder="搜索资讯" v-model="search" @search="getSearch" @custom="getSearch" :show-action="false"></u-search>
			</view>
		</u-navbar>
		<view class="wrap">
			<u-swiper :list="list" indicator-pos="bottomLeft" :effect3d="true"  @click="swiper"></u-swiper>
		</view>
		<u-row gutter="16" style="padding: 20rpx 50rpx;border-bottom: 2rpx #a1a1a1;text-align: center;background-color: #FFFFFF;">
			<u-col span="3">
				<navigator :url="'/pages/info/list/list?id=1&type=热点'" navigateTo>
					<u-icon name="photo" :size="46"></u-icon>
					<view class="grid-text">热点</view>
				</navigator>
			</u-col>
			<u-col span="3">
				<navigator :url="'/pages/info/list/list?id=2&type=政策'" navigateTo>
					<u-icon name="lock" :size="46"></u-icon>
					<view class="grid-text">政策</view>
				</navigator>
			</u-col>
			<u-col span="3">
				<navigator :url="'/pages/info/list/list?id=3&type=专题'" navigateTo>
					<u-icon name="hourglass" :size="46"></u-icon>
					<view class="grid-text">专题</view>
				</navigator>
			</u-col>
			<u-col span="3">
				<navigator :url="'/pages/info/list/list?id=4&type=小知识'" navigateTo>
					<u-icon name="photo" :size="46"></u-icon>
					<view class="grid-text">小知识</view>
				</navigator>
			</u-col>
		</u-row>

		<p style="height: 20rpx;background-color: #f7f7f7;"></p>

		<u-row gutter="16" style="padding: 20rpx 50rpx;border-bottom: 2rpx #a1a1a1;text-align: center;background-color: #FFFFFF;">
			<u-col span="2" v-for="item in addressList" :key="item.id">
				<navigator :url="'/pages/info/list/list?id=' + item.id +'&type=' + item.name" navigateTo>
					<view class="grid-text" style="line-height: 70rpx;">{{item.name}}</view>
				</navigator>
			</u-col>
			<u-col span="2" v-for="item in linkList" :key="item.id">
				<navigator :url="'/pages/info/list/list?id=' + item.id +'&type=' + item.name" navigateTo>
					<view class="grid-text" style="line-height: 70rpx;">{{item.name}}</view>
				</navigator>
			</u-col>
		</u-row>


		<view style="margin: 10rpx;"></view>
		<view class="u-text-left" style="padding-top: 30rpx;padding-left: 30rpx;background-color: #FFFFFF;">
			<view style="padding-bottom: 8rpx;font-size: 36rpx;">热点导读</view>
			<u-line />
		</view>
		<view style="background-color: #FFFFFF;padding-left:30rpx ;padding-right: 30rpx;padding-bottom:30rpx ;">
			<u-row gutter="16" style="padding: 10rpx;border-bottom: 2rpx #a1a1a1;" v-for="(item,index) in InfoList" :key="index">
				<u-col span="8">
					<view @tap="toDetail(item.id)">
						<view class="u-line-2">{{item.title}}</view>
						<view style="color: #a1a1a1;font-size: 18rpx;padding-top: 15rpx;">
							<u-icon name="clock" />{{item.time}}</view>
					</view>
				</u-col>
				<u-col span="4">
					<u-image width="100%" height="120rpx" :border-radius="4" :src="src1[index]"></u-image>
				</u-col>
			</u-row>
			<u-image width="100%" height="150rpx" :border-radius="4" :src="bottom" style="background-color: #FFFFFF;"></u-image>
		</view>
	</view>
</template>

<script>
	const api = require('static/js/api');
	export default {
		data() {
			return {
				bottom: api.HOUSEINTERFACES.root + 'img/bottom.png',
				search: '',
				addressList: [],
				linkList: [],
				InfoList: [],
				list: [{
						image: api.HOUSEINTERFACES.root+ 'university_detail/1.png',
						title: '昨夜星辰昨夜风，画楼西畔桂堂东',
						url: "/pages/info/detail/detail?id=307"
					},
					{
						image: api.HOUSEINTERFACES.root+ 'university_detail/2.png',
						title: '身无彩凤双飞翼，心有灵犀一点通',
						url: "/pages/info/detail/detail?id=308"
					},
					{
						image: api.HOUSEINTERFACES.root+ 'university_detail/3.png',
						title: '谁念西风独自凉，萧萧黄叶闭疏窗，沉思往事立残阳',
						url: "/pages/info/detail/detail?id=304"
					}
				],
				src1: [
					api.HOUSEINTERFACES.root + 'university_detail/1.png',
					api.HOUSEINTERFACES.root + 'university_detail/2.png',
					api.HOUSEINTERFACES.root + 'university_detail/3.png',
					api.HOUSEINTERFACES.root + 'university_detail/4.png',
					api.HOUSEINTERFACES.root + 'university_detail/1.png',
					api.HOUSEINTERFACES.root + 'university_detail/3.png',
				],
			}
		},
		onPullDownRefresh() {
			this.getAddressList();
			this.getLinkList();
			this.getInfoList();
		},
		onLoad() {
			this.getAddressList();
			this.getLinkList();
			this.getInfoList();
		},
		methods: {
			getAddressList() {
				uni.request({
					url: api.INTERFACES.infoTypeList,
					data: {
						status: "true",
						type: "地区"
					},
					method: 'POST',
					success: res => {
						if (res.data.code === 200) {
							this.addressList = res.data.list
						} else {

						}
					},
					fail: (res, code) => {
						console.log('fail' + JSON.stringify(res));
					}
				});
			},
			getLinkList() {
				uni.request({
					url: api.INTERFACES.infoTypeList,
					data: {
						status: "true",
						type: "环节"
					},
					method: 'POST',
					success: res => {
						if (res.data.code === 200) {
							this.linkList = res.data.list
						} else {

						}
					},
					fail: (res, code) => {
						console.log('fail' + JSON.stringify(res));
					}
				});
			},
			getInfoList() {
				uni.request({
					url: api.INTERFACES.infoList,
					data: {},
					method: 'POST',
					success: res => {
						if (res.data.code === 200) {
							this.InfoList = res.data.list
						} else {

						}
					},
					fail: (res, code) => {
						console.log('fail' + JSON.stringify(res));
					}
				});
			},
			getSearch() {
				uni.navigateTo({
					url: '/pages/info/list/list?search=' + this.search
				})
			},
			toDetail(e) {
				uni.navigateTo({
					url: '/pages/info/detail/detail?id=' + e
				})
			}

		}
	}
</script>

<style lang="scss">
	page {
		background-color: #f7f7f7;
	}

	.camera {
		width: 54px;
		height: 44px;

		&:active {
			background-color: #ededed;
		}
	}

	.user-box {
		background-color: #fff;
	}


	.u-card-wrap {
		background-color: $u-bg-color;
		padding: 1px;
	}

	.u-body-item {
		font-size: 32rpx;
		color: #333;
		padding: 20rpx 10rpx;
	}

	.u-body-item image {
		width: 120rpx;
		flex: 0 0 120rpx;
		height: 120rpx;
		border-radius: 8rpx;
		margin-left: 12rpx;
	}
</style>
