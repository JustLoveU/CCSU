<template>
	<view>
		<u-navbar :is-back="false" title="　" :border-bottom="false">
			<view class="u-flex u-row-right" style="width: 100%;">
				<view class="camera u-flex u-row-center">
					<!-- <u-icon name="camera-fill" color="#000000" size="48"></u-icon> -->
				</view>
			</view>
		</u-navbar>
		<view class="u-flex user-box u-p-l-30 u-p-r-20 u-p-b-30">
			<view class="u-m-r-10">
				<u-avatar :src="user.img" size="140"></u-avatar>
			</view>
			<view class="u-flex-1">
				<view class="u-font-18 u-p-b-20">{{user.username}}</view>
				<view class="u-font-14 u-tips-color">邮箱:{{user.email}}</view>
			</view>
			<!-- 			<view class="u-m-l-10 u-p-10">
				<u-icon name="scan" color="#969799" size="28"></u-icon>
			</view> -->
			<view class="u-m-l-10 u-p-10" @click="toPage('/pages/my/register/register?id='+user.id)">
				<u-icon name="arrow-right" color="#969799" size="28"></u-icon>
			</view>
		</view>

		<view class="card">
			<u-grid :col="4" :border="false">
				<u-grid-item @tap="toPage('/pages/my/collection/collection?current=0')">
					<u-icon name="chat" :size="35"></u-icon>
					<view class="grid-text">评价</view>
				</u-grid-item>
				<u-grid-item @tap="toPage('/pages/my/collection/collection?current=1')">
					<u-icon name="star" :size="35"></u-icon>
					<view class="grid-text">收藏</view>
				</u-grid-item>
				<u-grid-item @tap="toPage('/pages/my/collection/collection?current=2')">
					<u-icon name="clock" :size="35"></u-icon>
					<view class="grid-text">历史</view>
				</u-grid-item>
				<u-grid-item @tap="toPage('/pages/my/collection/collection?current=3')">
					<u-icon name="play-right" :size="35"></u-icon>
					<view class="grid-text">进度</view>
				</u-grid-item>
			</u-grid>
		</view>


		<view class="u-m-t-20 card">
			<u-cell-group>
				<!-- <button style="background:#ffffff;text-align:left;" open-type="contact">
					<u-cell-item icon="bag" title="联系客服"></u-cell-item>
				</button> -->
				<button open-type='feedback'>
					<u-cell-item icon="edit-pen" title="意见反馈"></u-cell-item>
				</button>

				<button open-type='share'>
					<u-cell-item icon="share" title="分享"></u-cell-item>
				</button>
				<button @click="toPage('/pages/my/about/about')">
					<u-cell-item icon="zhuanfa" title="关于"></u-cell-item>
				</button>

				<button @click="logout">
					<u-cell-item icon="person-delete-fill" title="注销" @tap="logout"></u-cell-item>
				</button>
			</u-cell-group>
		</view>

	</view>
</template>

<script>
	export default {
		data() {
			return {
				// pic: 'https://uviewui.com/common/logo.png',
				show: true,
				user: {
					id: '',
					username: '',
					img: '',
					email: ''
				}
			}
		},
		onLoad() {
			this.check()
		},
		methods: {
			toPage(url) {
				uni.navigateTo({
					url: url
				})
			},
			logout() {
				uni.clearStorage()
				uni.showToast({
					title: '注销成功！',
					icon: "none"
				});
				var _this = this
				setTimeout(function() {
					_this.check()
				}, 1000)
			},
			check() {
				var user = this.checkLogin('/pages/my/index/index', 1); //调用全局封装的函数,参数在上面已经介绍了
				if (!user) { //如果没有登录,则会提示
					uni.showToast({
						title: '请先登录！',
						icon: "none"
					});
					return false; //不走下面的,也就是不触发下面的函数
				} else {
					this.user.id = user.id
					this.user.username = user.username;
					this.user.img = user.img;
					this.user.email = user.email;
				}
			}
		}
	}
</script>

<style lang="scss">
	page {
		background-color: #f7f7f7;
	}

	.card {
		margin: 15rpx;
		border-radius: 12px;
	}

	button::after {
		border: none;
	}

	button {
		background-color: #fff;
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
</style>
