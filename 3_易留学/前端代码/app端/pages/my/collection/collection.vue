<template>
	<view>
		<view class="wrap">
			<view class="u-tabs-box">
				<u-tabs-swiper ref="tabs" :list="list" :current="current-1" @change="change" :is-scroll="false" swiperWidth="750"></u-tabs-swiper>
			</view>
			<swiper class="swiper-box" :current="current" @transition="transition" @animationfinish="animationfinish">
				<swiper-item class="swiper-item">
					<scroll-view scroll-y style="height: 100%;width: 100%;">
						<view class="page-box" v-if="contentStatus[0] == false">
							<view>
								<view class="centre">
									<image src="https://cdn.uviewui.com/uview/template/taobao-order.png" mode=""></image>
									<view class="explain">
										您还没有任何评论噢
									</view>
									<view class="btn" @click="toPage('/pages/comment/index/index')">随便逛逛</view>
								</view>
							</view>
						</view>
						<view class="page-box" v-if="contentStatus[0]">
							<view class="comment" v-for="(item,index) in commentList" :key="index">
								<view class="top" @tap="goUniversity(item.universityId)">
									<view class="left">
										<view class="heart-photo">
											<image :src="user.img" mode=""></image>
										</view>
										<view class="user-info">
											<view class="name">{{item.time}}</view>
										</view>
									</view>
								</view>
								<view class="content u-font-10 u-line-3">{{item.content}}</view>
							</view>
							<u-loadmore :status="loadStatus[0]" bgColor="#f7f7f7" @tap="loadmore(0)"></u-loadmore>
						</view>
					</scroll-view>
				</swiper-item>


				<swiper-item class="swiper-item">
					<scroll-view scroll-y style="height: 100%;width: 100%;">
						<view class="page-box" v-if="contentStatus[1] == false">
							<view>
								<view class="centre">
									<image src="https://cdn.uviewui.com/uview/template/taobao-order.png" mode=""></image>
									<view class="explain">
										您还没有任何收藏噢
									</view>
									<view class="btn" @click="toPage('/pages/comment/index/index')">随便逛逛</view>
								</view>
							</view>
						</view>
						<view class="page-box" v-if="contentStatus[1]">
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
							<u-loadmore :status="loadStatus[1]" bgColor="#f7f7f7" @tap="loadmore(1)"></u-loadmore>
						</view>
					</scroll-view>
				</swiper-item>
				<swiper-item class="swiper-item">
					<scroll-view scroll-y style="height: 100%;width: 100%;">
						<view class="page-box">
							<view>
								<view class="centre">
									<image src="https://cdn.uviewui.com/uview/template/taobao-order.png" mode=""></image>
									<view class="explain">
										您还没有浏览历史
									</view>
									<view class="btn" @click="toPage('/pages/comment/index/index')">随便逛逛</view>
								</view>
							</view>
						</view>
					</scroll-view>
				</swiper-item>
				<swiper-item class="swiper-item">
					<scroll-view scroll-y style="height: 100%;width: 100%;" @scrolltolower="reachBottom">
						<view class="page-box">
							<view style="margin: 20rpx;"></view>
							<view class="u-text-center" style="padding: 30rpx;background-color: #FFFFFF;">
								<view style="padding-bottom: 8rpx;">留学申请一</view>
								<u-divider>
									<view style="font-size: 18rpx;color: #a1a1a1;">* APPLY *</view>
								</u-divider>
							</view>
							<view style="background-color: #FFFFFF;padding-bottom:30rpx ;">
								<u-row gutter="16">
									<u-col span="4">
										<view class="card">
											<u-image width="80rpx" height="80rpx" :src="universityimg"></u-image>
											<view style="font-size: 30rpx;margin-top: 20rpx;">大阪大学</view>
											<view style="font-size: 22rpx;color: #a7a7a7;margin-top: 20rpx;">申请学校</view>
										</view>
									</u-col>
									<u-col span="4">
										<view class="card">
											<u-image width="80rpx" height="80rpx"  :src="ing"></u-image>
											<view style="font-size: 30rpx;margin-top: 20rpx;">60%</view>
											<view style="font-size: 22rpx;color: #a7a7a7;margin-top: 20rpx;">申请进度</view>
										</view>
									</u-col>
									<u-col span="4">
										<view class="card">
											<u-image width="80rpx" height="80rpx" :src="time"></u-image>
											<view style="font-size: 30rpx;margin-top: 20rpx;">2020.03.25</view>
											<view style="font-size: 22rpx;color: #a7a7a7;margin-top: 20rpx;">申请时间</view>
										</view>
									</u-col>
								</u-row>
								<navigator url="/pages/my/ing/ing" navigateTo><view class="card" style="padding-top: 20rpx;text-align: center;font-size: 28rpx;">
									查看进度
								</view>
								</navigator>
							</view>
							<!-- <u-loadmore :status="loadStatus[3]" bgColor="#f7f7f7"></u-loadmore> -->
						</view>
					</scroll-view>
				</swiper-item>
			</swiper>
		</view>
	</view>
</template>

<script>
	const api = require('static/js/api');
	export default {
		data() {
			return {
				time: api.HOUSEINTERFACES.root + 'img/time.png',
				ing: api.HOUSEINTERFACES.root + 'img/ing.png',
				universityimg: api.HOUSEINTERFACES.root + 'img/university.png',
				user: {
					username: '',
					img: ''
				},
				commentList: [],
				universityList: [],
				list: [{
						name: '评论'
					},
					{
						name: '收藏'
					},
					{
						name: '历史'
					},
					{
						name: '进度'
					}
				],
				current: 0,
				tabsHeight: 0,
				loadStatus: ['loadmore', 'loadmore', 'loadmore', 'loadmore'],
				contentStatus: [false, false, false, false],
				page: [{
					current: 1,
					hitCount: '',
					orders: [],
					pages: '',
					records: [],
					searchCount: '',
					size: 6,
					total: ''
				}, {
					current: 1,
					hitCount: '',
					orders: [],
					pages: '',
					records: [],
					searchCount: '',
					size: 6,
					total: ''
				}, {
					current: 1,
					hitCount: '',
					orders: [],
					pages: '',
					records: [],
					searchCount: '',
					size: 6,
					total: ''
				}, {
					current: 1,
					hitCount: '',
					orders: [],
					pages: '',
					records: [],
					searchCount: '',
					size: 6,
					total: ''
				}]
			};
		},
		onLoad(e) {
			console.log(e)
			this.$refs.tabs.setFinishCurrent(e.current);
			this.current = e.current;
			this.select(e.current)
			this.user.img = uni.getStorageSync('user').img
			this.user.username = uni.getStorageSync('user').username
		},
		methods: {
			goUniversity(id) {
				uni.navigateTo({
					url: '/pages/info/comment/comment?id=' + id
				})
			},
			toPage(url) {
				uni.switchTab({
					url: url
				})
			},
			loadmore(id) {
				if (this.page[id].current >= this.page[id].pages) {
					this.loadStatus[id] = "nomore"
				} else {
					this.page[id].current = this.page[id].current + 1
					if (id == 0) {
						this.getCommentList()
					}
					if (id == 1) {
						this.getCollectionList()
					}
				}
			},
			select(current) {
				if (current == 0) {
					this.getCommentList()
				}
				if (current == 1) {
					this.getCollectionList()
				}
			},
			getCommentList() {
				this.loadStatus[0] = "loading"
				uni.request({
					url: api.INTERFACES.commentListByUser + uni.getStorageSync("user").id,
					data: {
						page: this.page[0].current,
						limit: this.page[0].size
					},
					method: 'GET',
					success: res => {
						this.page[0] = res.data.page
						this.commentList = this.commentList.concat(res.data.page.records)

						if (this.page[0].total == 0) {
							this.contentStatus[0] = false
						} else {
							this.contentStatus[0] = true
						}
						if (this.page[0].current >= this.page[0].pages) {
							this.loadStatus[0] = "nomore"
						} else {
							this.loadStatus[0] = "loadmore"
						}
					},
					fail: (res, code) => {
						console.log('fail' + JSON.stringify(res));
					}
				});
			},
			getCollectionList() {
				this.loadStatus[1] = "loading"
				uni.request({
					url: api.INTERFACES.universityListByUser + uni.getStorageSync("user").id,
					data: {
						page: this.page[1].current,
						limit: this.page[1].size
					},
					method: 'GET',
					success: res => {
						this.page[1] = res.data.page
						this.universityList = this.universityList.concat(res.data.page.records)

						if (this.page[1].total == 0) {
							this.contentStatus[1] = false
						} else {
							this.contentStatus[1] = true
						}
						if (this.page[1].current >= this.page[1].pages) {
							this.loadStatus[1] = "nomore"
						} else {
							this.loadStatus[1] = "loadmore"
						}
					},
					fail: (res, code) => {
						console.log('fail' + JSON.stringify(res));
					}
				});
			},
			reachBottom() {
				// 此tab为空数据
				if (this.current != 2) {
					this.loadStatus.splice(this.current, 1, "loading")
					setTimeout(() => {
						this.getOrderList(this.current);
					}, 1200);
				}
			},
			// tab栏切换
			change(index) {
				// Object.assign(this.$data, this.$options.data());
				this.current = index;
				this.reset()
				this.select(index);
			},
			reset() {
				this.commentList = []
				this.universityList = [];
				for (var i = 0; i < 4; i++) {
					this.page[i] = {
						current: 1,
						hitCount: '',
						orders: [],
						pages: '',
						records: [],
						searchCount: '',
						size: 6,
						total: ''
					}
				}
			},
			transition({
				detail: {
					dx
				}
			}) {
				this.$refs.tabs.setDx(dx);
			},
			animationfinish({
				detail: {
					current
				}
			}) {
				this.$refs.tabs.setFinishCurrent(current);
				this.current = current;
			}
		}
	};
</script>

<style>
	/* #ifndef H5 */
	page {
		height: 100%;
		background-color: #f7f7f7;
	}

	/* #endif */
</style>

<style lang="scss" scoped>
	.card {
		margin: 15rpx;
		padding: 25rpx;
		border: 8rpx;
		border-radius: 12rpx;
		box-shadow: 0 1px 11px 4px rgba(0,0,0,0.1), 0 2px 4px 0px rgba(0,0,0,0.1), 0 4px 8px 0px rgba(0,0,0,0.1);
		background-color: #FFFFFF;
	}

	.comment {
		margin: 15rpx;
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

	.centre {
		text-align: center;
		margin: 200rpx auto;
		font-size: 32rpx;

		image {
			width: 164rpx;
			height: 164rpx;
			border-radius: 50%;
			margin-bottom: 20rpx;
		}

		.tips {
			font-size: 24rpx;
			color: #999999;
			margin-top: 20rpx;
		}

		.btn {
			margin: 80rpx auto;
			width: 200rpx;
			border-radius: 32rpx;
			line-height: 64rpx;
			color: #ffffff;
			font-size: 26rpx;
			background: linear-gradient(270deg, rgba(249, 116, 90, 1) 0%, rgba(255, 158, 1, 1) 100%);
		}
	}

	.wrap {
		display: flex;
		flex-direction: column;
		height: calc(100vh - var(--window-top));
		width: 100%;
	}

	.swiper-box {
		flex: 1;
	}

	.swiper-item {
		height: 100%;
	}
</style>
