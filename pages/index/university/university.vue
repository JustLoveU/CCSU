<template>
	<view>
		<u-navbar :is-back="true" title="　" :border-bottom="false">
			<view class="u-flex u-row-right" style="width: 100%;">
				<view class="camera u-flex u-row-center">
					<label name="camera-fill" color="#000000" size="48">{{name}}</label>
				</view>
			</view>
		</u-navbar>
		<view style="margin: 10px;"></view>
		<view class="comment">
			<view class="top">
				<view class="left">
					<view class="heart-photo">
						<image src="https://cdn.uviewui.com/uview/template/SmilingDog.jpg" mode=""></image>
					</view>
					<view class=".user-info">
						<view class="name">{{name}}</view>
						<view class="comment">
							<u-rate count="count" v-model="value" :disabled="true"></u-rate>评价
						</view>
					</view>
				</view>
			</view>
			<view class="content u-font-10 u-line-3">今天吃什么，明天吃什么，晚上吃什么，我只是一只小猫咪为什么要烦恼这么多</view>
		</view>
		<view style="margin: 10px;"></view>
		<view class="comment">
			<label>专业</label>
			<view style="padding-top: 10rpx;">
				<u-tag text="计算机科学与技术" type="info" mode="plain" class="padding" shape="circleRight" />
			</view>
			<view style="padding-top: 10rpx;">
				<u-tag text="经融管理" type="info" mode="plain" class="padding" shape="circleRight" />
			</view>
			<view style="padding-top: 10rpx;">
				<u-tag text="物联网" type="info" mode="plain" class="padding" shape="circleRight" />
			</view>
			<view style="padding-top: 10rpx;">
				<u-tag text="英语" type="info" mode="plain" class="padding" shape="circleRight" />
			</view>
			<view style="padding-top: 10rpx;">
				<u-tag text="汉语" type="info" mode="plain" class="padding" shape="circleRight" />
			</view>
		</view>
		<view style="margin: 10px;"></view>
		<view class="comment">
			<label>学费：$10000-2000</label>
		</view>
		<view style="margin: 10px;"></view>
		<view class="comment">
			<label>热门攻略</label>
		</view>
		<view style="margin: 10px;"></view>
		<view class="comment">
			<label>精选点评</label>
			<view style="padding-top: 10rpx;">
				
			</view>
		</view>
	</view>
</template>

<script>
	export default {
		data() {
			return {
				count: 5,
				value: 4,
				name: ''
			}
		},
		onLoad(e) {
			console.log(e)
			this.name = e.keywords
		},
		methods: {
			// 评论列表
			getComment() {
				
			}
		}
	}
</script>

<style lang="scss">
	page {
		background-color: #ededed;
	}

	.comment {
		margin-bottom: 15rpx;
		padding: 30rpx;
		font-size: 32rpx;
		background-color: #ffffff;

		.top {
			display: flex;
			justify-content: space-between;
		}

		.left {
			display: flex;

			.heart-photo {
				image {
					width: 128rpx;
					height: 128rpx;
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

				.comment {
					font-size: 22rpx;
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

		.top {
			display: flex;
			justify-content: space-between;
		}

		.left {
			display: flex;

			.heart-photo {
				image {
					width: 128rpx;
					height: 128rpx;
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

				.comment {
					font-size: 22rpx;
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

	.margin {
		margin: 5rpx;
	}

	.padding {
		padding: 5rpx;
	}
</style>
