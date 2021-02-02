<template>
	<view>
		<view class="weui-search-bar" style="margin: 20rpx;">
			<!-- <view class="weui-search-bar__form"> -->
				<!-- <view class="weui-search-bar__box"> -->
					<!-- <icon class="weui-icon-search_in-box" type="search" size="14"></icon> -->
					<u-search placeholder="搜索大学" v-model="keyword" @change="bindInput" @seach="search" @custom="search" ></u-search>
					<!-- <input @input="bindInput" class="weui-search-bar__input" placeholder="搜索"></input> -->
				<!-- </view> -->
			<!-- </view> -->
		</view>

		<view style="margin: 20rpx;" v-if="flag">
			<view>
				<text style="margin: 15rpx;">热门：</text>
				<u-tag text="大阪大学" shape="circle" @click="bindSearch(509)" style="margin: 15rpx;" />
				<u-tag text="东京大学" shape="circle" @click="bindSearch(1)" style="margin: 15rpx;" />
				<u-tag text="京都大学" shape="circle" @click="bindSearch(646)" style="margin: 15rpx;" />
			</view>
			<view v-for="(item,index)  in tips" :key="index" @click="bindSearch(item.id)" :data-keywords="item.name" style="padding-top: 5px;padding-bottom: 5px;padding-left: 30px;">
				{{item.name}}
			</view>
		</view>
		<view class="all-reply" v-else>
			<view class="item">
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
		</view>
	</view>
</template>

<script>
	const api = require('static/js/api');
	export default {
		data() {
			return {
				tips: {},
				keyword: '',
				universityList: [],
				flag: true
			};
		},
		methods: {
			goUniversity(id){
				uni.navigateTo({
					url: '/pages/info/comment/comment?id='+id
				})
			},
			bindInput() {
				this.universityList = []
				this.flag = true
				uni.request({
					url: api.INTERFACES.universitySearch,
					data: {
						search: this.keyword
					},
					method: 'GET',
					success: res => {
						this.tips = res.data.list
					},
					fail: (res, code) => {
						console.log('fail' + JSON.stringify(res));
					}
				});
			},
			search() {
				this.tips = [];
				this.flag = false
				uni.request({
					url: api.INTERFACES.universitySearch1,
					data: {
						search: this.keyword
					},
					method: 'GET',
					success: res => {
						this.universityList = res.data.list
					},
					fail: (res, code) => {
						console.log('fail' + JSON.stringify(res));
					}
				});
			},
			bindSearch(e) {
				uni.navigateTo({
					url: '/pages/info/comment/comment?id=' + e
				});
			}
		}
	}
</script>

<style lang="scss">
	.map {
		width: 100%;
	}

	.container {
		height: 100%;
		width: 100%;
	}

	//map的class
	.map {
		height: 100%;
		width: 100%;
	}


	.map_container {
		position: absolute;
		top: 0;
		bottom: 0;
		left: 0;
		right: 0;
	}

	.weui-icon-search_in-box {
		position: absolute;
		left: 10px;
		top: 7px;
	}

	.weui-search-bar__form {
		position: relative;
		-webkit-box-flex: 1;
		-webkit-flex: auto;
		flex: auto;
		border-radius: 5px;
		background: #FFFFFF;
		border: 1rpx solid #E6E6EA;
	}

	.weui-search-bar__box {
		position: relative;
		padding-left: 30px;
		padding-right: 30px;
		width: 100%;
		box-sizing: border-box;
		z-index: 1;
	}

	.weui-search-bar__input {
		height: 28px;
		line-height: 28px;
		font-size: 14px;
	}

	.weui-search-bar__cancel-btn {
		margin-left: 10px;
		line-height: 28px;
		color: #09BB07;
		white-space: nowrap;
	}
	
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
