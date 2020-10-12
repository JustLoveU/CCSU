<template>
	<view>
		<u-navbar :is-back="false" title="　" :border-bottom="false">
			<view style="width: 100%;padding-left: 20rpx;">
				<p>
					<u-icon name="search" style="padding-left: 20rpx;" @tap="toPage('/pages/index/search/search')" />
				</p>
			</view>
		</u-navbar>
		<view style="margin: 10px;"></view>

<!-- 		<view style="margin: 10rpx;">
			<navigator url="/pages/index/search/search" navigateTo>
				<u-search placeholder="搜索大学" v-model="keyword" @change="toPage('/pages/index/search/search')" @click="toPage('/pages/index/search/search')"
				 :disabled="true" :show-action="false"></u-search>
			</navigator>
		</view> -->
		<view class="all-reply">
			<view class="item">
				<view class="comment" v-for="(item,index) in universityLIst" :key="index">
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
					<view class="content u-font-12 u-line-3">{{item.intro}}</view>
				</view>

			</view>
		</view>
		<u-divider @tap="toPage('/pages/diy/index/index',1)">
			<view style="font-size: 18rpx;color: #a1a1a1;">* 免费评估+DIY方案推荐+前辈推荐 *</view>
		</u-divider>
		<view style="margin: 20rpx;"></view>
		<u-image width="100%" height="150rpx" :border-radius="4" :src="bottom" style="background-color: #FFFFFF;" @tap="toPage('/pages/diy/index/index',1)"></u-image>
	</view>
</template>

<script>
	const api = require('static/js/api');
	export default {
		data() {
			return {
				bottom: api.HOUSEINTERFACES.root + 'img/clickme.png',
				universityLIst: []
			}
		},
		onLoad() {
			this.getInfoList1()
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
			goUniversity(id) {
				uni.navigateTo({
					url: '/pages/info/comment/comment?id=' + id
				})
			},
			getInfoList1() {
				uni.request({
					url: api.INTERFACES.universitylistLimit + 4,
					data: {},
					method: 'GET',
					success: res => {
						if (res.data.code === 200) {
							this.universityLIst = res.data.list
						}
					},
					fail: (res, code) => {
						console.log('fail' + JSON.stringify(res));
					}
				});
			},
		}
	}
</script>

<style lang="scss">
	.comment {
		margin: 30rpx;
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
</style>
