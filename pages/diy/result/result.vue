<template>
	<view>
		<u-navbar :is-back="true" title="　" :border-bottom="false">
			<view class="u-flex u-row-right" style="width: 100%;">
				<view class="camera u-flex u-row-center">
					<label name="camera-fill" color="#000000" size="48">{{name}}</label>
				</view>
			</view>
		</u-navbar>
		<view class="u-text-center" style="padding: 30rpx;background-color: #FFFFFF;">
			<view style="padding-bottom: 8rpx;">成功率</view>
			<u-divider>
				<view style="font-size: 18rpx;color: #a1a1a1;">* RATE OF SUCCESS *</view>
			</u-divider>
		</view>
		<view class="u-text-center" style="padding-bottom: 30rpx;font-size: 35rpx;background-color: #FFFFFF;">{{result.rate}}%</view>

		<view style="margin: 10rpx;"></view>
		<view class="u-text-center" style="padding-top: 30rpx;background-color: #FFFFFF;">
			<view style="padding-bottom: 8rpx;">基本信息</view>
			<u-divider>
				<view style="font-size: 18rpx;color: #a1a1a1;">* INFOMATION *</view>
			</u-divider>
		</view>
		<view class="comment1">
			<view class="all-reply1">
				<view class="item1">
					<view class="comment1">
						<view class="top1">
							<view class="left1">
								<view class="heart-photo1">
									<image :src="user.img" mode=""></image>
								</view>
								<view class="user-info1">
									<view class="name1">{{user.username}}</view>
									<view class="data1">{{user.intro}}</view>
								</view>
							</view>
						</view>
					</view>
				</view>
			</view>
		</view>
		<view style="margin: 10rpx;"></view>
		<view class="u-text-center" style="padding: 30rpx;background-color: #FFFFFF;">
			<view style="padding-bottom: 8rpx;">推荐院校</view>
			<u-divider>
				<view style="font-size: 18rpx;color: #a1a1a1;">* UNIVERSITY *</view>
			</u-divider>
		</view>
		<view style="background-color: #FFFFFF;padding: 30rpx;">
			<view class="comment" v-for="(item,index) in universityList" :key="index">
				<view class="top" @tap="goUniversity(item.id)">
					<view class="left">
						<view class="heart-photo">
							<image :src="item.img" mode=""></image>
						</view>
						<view class="user-info">
							<view class="name">{{item.name}}</view>
						</view>
					</view>
				</view>
				<view class="content u-font-10 u-line-3">{{item.intro}}</view>
			</view>
		</view>

		<view style="margin: 10rpx;"></view>
		<view class="u-text-center" style="padding-top: 30rpx;background-color: #FFFFFF;">
			<view style="padding-bottom: 8rpx;">推荐指数</view>
			<u-divider>
				<view style="font-size: 18rpx;color: #a1a1a1;">* COMPOSITION INDEX *</view>
			</u-divider>
		</view>
		<view style="padding-top: 15rpx;padding-left: 30rpx;padding-right: 30rpx;padding-bottom: 20rpx; background-color: #FFFFFF;">
			<view style="padding-top: 10rpx;">
				语言：<u-rate count="count" v-model="result.language" :disabled="true"></u-rate>
			</view>
			<view style="padding-top: 10rpx;">
				名额：<u-rate count="count" v-model="result.number" :disabled="true"></u-rate>
			</view>
			<view style="padding-top: 10rpx;">
				费用：<u-rate count="count" v-model="result.fee" :disabled="true"></u-rate>
			</view>
			<view style="padding-top: 10rpx;">
				时间：<u-rate count="count" v-model="result.time" :disabled="true"></u-rate>
			</view>
			<view style="padding-top: 10rpx;">
				专业：<u-rate count="count" v-model="result.major" :disabled="true"></u-rate>
			</view>
		</view>

		<view style="margin: 10rpx;"></view>
		<view class="u-text-center" style="padding: 30rpx;background-color: #FFFFFF;">
			<view style="padding-bottom: 8rpx;">推荐咨询</view>
			<u-divider>
				<view style="font-size: 18rpx;color: #a1a1a1;">* COMPOSITION *</view>
			</u-divider>
		</view>
		<view style="padding-top: 15rpx;padding-left: 30rpx;padding-right: 30rpx;padding-bottom: 20rpx; background-color: #FFFFFF;">
			<view>
				<u-tag text="教授" shape="circle"  style="margin: 15rpx;" />
				<u-tag text="副教授" shape="circle"  style="margin: 15rpx;" />
				<u-tag text="专家" shape="circle" style="margin: 15rpx;" />
				<u-tag text="副专家" shape="circle" style="margin: 15rpx;" />
				<button style="background:#ffffff;text-align:left;" open-type="contact">
					<u-tag text="留学经验者" shape="circle" style="margin: 15rpx;" />
				</button>
			</view>
		</view>
	</view>
</template>

<script>
	const api = require('static/js/api');
	export default {
		data() {
			return {
				applyid: '',
				apply: {},
				result: {},
				count: 5,
				value: 4,
				value1: 5,
				value2: 3,
				img: '',
				user: {
					id: '',
					username: '',
					img: '',
					email: '',
					intro: ''
				},
				universityList: []
			}
		},
		onLoad(e) {
			this.applyid = e.id;
			this.check()
			this.getresult()
			// this.getInfoList1()
		},
		methods: {
			goUniversity(id){
				uni.navigateTo({
					url: '/pages/info/comment/comment?id='+id
				})
			},
			getresult(){
				uni.request({
					url: api.INTERFACES.ResultOneByApply +　this.applyid,
					data: {},
					method: 'GET',
					success: res => {
						console.log(res.data)
						if (res.data.code === 200) {
							this.universityList[0] = res.data.university1;
							this.universityList[1] = res.data.university2;
							this.universityList[2] = res.data.university3;
							this.apply = res.data.apply;
							this.result = res.data.result;
						}
					},
					fail: (res, code) => {
						console.log('fail' + JSON.stringify(res));
					}
				});
			},
			getInfoList1() {
				uni.request({
					url: api.INTERFACES.universityList,
					data: {},
					method: 'GET',
					success: res => {
						if (res.data.code === 200) {
							this.universityList = res.data.list
						}
					},
					fail: (res, code) => {
						console.log('fail' + JSON.stringify(res));
					}
				});
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
					this.user.intro = user.intro
				}
			}
		}
	}
</script>

<style lang="scss">
	page {
		background-color: #f7f7f7;
	}

	button::after {
		border: none;
	}

	button {
		background-color: #fff;
	}


	.comment {
		margin-bottom: 15px;
		border-radius: 12rpx;
		padding: 30rpx;
		font-size: 32rpx;
		background-color: #ffffff;
		border: 2rpx solid #c6c6c6;
		box-shadow: 2rpx 2rpx 5rpx #e3e3e3;

		.top {
			display: flex;
			justify-content: space-between;
		}

		.left {
			display: flex;

			.heart-photo {
				image {
					width: 64rpx;
					height: 64rpx;
					border-radius: 50%;
					background-color: #f2f2f2;
				}
			}

			.user-info {
				margin-left: 10rpx;

				.name {
					color: #5677fc;
					font-size: 28rpx;
					margin-bottom: 4rpx;
				}

				.date {
					font-size: 20rpx;
					color: $u-light-color;
				}
			}
		}

		.right {
			display: flex;
			font-size: 20rpx;
			align-items: center;
			color: #9a9a9a;

			.like {
				margin-left: 6rpx;
			}

			.num {
				font-size: 26rpx;
				color: #9a9a9a;
			}
		}

		.highlight {
			color: #5677fc;

			.num {
				color: #5677fc;
			}
		}
	}

	.comment1 {
		margin-bottom: 15rpx;
		padding: 30rpx;
		font-size: 32rpx;
		background-color: #ffffff;

		.top1 {
			display: flex;
			justify-content: space-between;
		}

		.left1 {
			display: flex;

			.heart-photo1 {
				image {
					width: 128rpx;
					height: 128rpx;
					border-radius: 50%;
					background-color: #f2f2f2;
				}
			}

			.user-info1 {
				margin-left: 10rpx;

				.name1 {
					padding-left: 30rpx;
					color: #5677fc;
					font-size: 28rpx;
					margin-bottom: 4rpx;
				}

				.date1 {
					padding-left: 30rpx;
					font-size: 20rpx;
					color: $u-light-color;
				}
			}
		}



		.highlight1 {
			color: #5677fc;

			.num1 {
				color: #5677fc;
			}
		}
	}
</style>
