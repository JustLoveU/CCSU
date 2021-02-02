<template>
	<view>
		<view class="wrap">
			<u-subsection :list="list" :current="current" bg-color="#ffffff" button-color="#f7f7f7" @change="sectionChange"></u-subsection>
			<swiper class="swiper-box" :current="current">
				<swiper-item class="swiper-item">
					<scroll-view scroll-y style="height: 100%;width: 100%;">
						<view style="padding: 35rpx;">
							<view style="background-color: #f7f7f7;border-radius: 12rpx;height: 100%;width: 100%;min-height: 500px;">
								<view style="padding: 30rpx;">
									<view style="padding-top: 30rpx;padding-bottom: 20rpx;font-size: 45rpx;">学校简介</view>
									<u-image width="100%" height="240rpx" :src="src[0]"></u-image>
									<view style="padding-top: 30rpx;padding-bottom: 20rpx;font-size: 26rpx;line-height: 44rpx;">{{university.intro}}</view>
								</view>
							</view>
						</view>
					</scroll-view>
				</swiper-item>
				<swiper-item class="swiper-item">
					<scroll-view scroll-y style="height: 100%;width: 100%;">
						<view style="padding: 35rpx;">
							<view style="background-color: #f7f7f7;border-radius: 12rpx;height: 100%;width: 100%;min-height: 500px;">
								<view style="padding: 30rpx;">
									<view style="padding-top: 30rpx;padding-bottom: 20rpx;font-size: 45rpx;">地理位置</view>
									<u-image width="100%" height="240rpx" :src="src[1]"></u-image>
									<view style="padding-top: 30rpx;padding-bottom: 20rpx;font-size: 26rpx;line-height: 44rpx;">{{university.name}}位于{{university.location}}</view>
								</view>
							</view>
						</view>
					</scroll-view>
				</swiper-item>
				<swiper-item class="swiper-item">
					<scroll-view scroll-y style="height: 100%;width: 100%;">
						<view style="padding: 35rpx;">
							<view style="background-color: #f7f7f7;border-radius: 12rpx;height: 100%;width: 100%;min-height: 500px;">
								<view style="padding: 30rpx;">
									<view style="padding-top: 30rpx;padding-bottom: 20rpx;font-size: 45rpx;">学位费用</view>
									<u-image width="100%" height="240rpx" :src="src[2]"></u-image>
									<view style="padding-top: 30rpx;padding-bottom: 20rpx;font-size: 26rpx;line-height: 44rpx;">暂无</view>
								</view>
							</view>
						</view>
					</scroll-view>
				</swiper-item>
				<swiper-item class="swiper-item">
					<scroll-view scroll-y style="height: 100%;width: 100%;">
						<view style="padding: 35rpx;">
							<view style="background-color: #f7f7f7;border-radius: 12rpx;height: 100%;width: 100%;min-height: 500px;">
								<view style="padding: 30rpx;">
									<view style="padding-top: 30rpx;padding-bottom: 20rpx;font-size: 45rpx;">专业特色</view>
									<u-image width="100%" height="240rpx" :src="src[3]"></u-image>
									<view style="padding-top: 30rpx;padding-bottom: 20rpx;font-size: 26rpx;line-height: 44rpx;">暂无</view>
								</view>
							</view>
						</view>
					</scroll-view>
				</swiper-item>
				<swiper-item class="swiper-item">
					<scroll-view scroll-y style="height: 100%;width: 100%;">
						<view style="padding: 35rpx;">
							<view style="background-color: #f7f7f7;border-radius: 12rpx;height: 100%;width: 100%;min-height: 500px;">
								<view style="padding: 30rpx;">
									<view style="padding-top: 30rpx;padding-bottom: 20rpx;font-size: 45rpx;">联系方式</view>
									<u-image width="100%" height="240rpx" :src="src[0]"></u-image>
									<view style="padding-top: 30rpx;padding-bottom: 20rpx;font-size: 26rpx;line-height: 66rpx;">
										<view>
											地址：{{university.location}}
										</view>
										<view>
											电话：{{university.tel}}
										</view>
										<view>
											官网：{{university.url}}
										</view>
									</view>
								</view>
							</view>
						</view>
					</scroll-view>
				</swiper-item>
				<swiper-item class="swiper-item">
					<scroll-view scroll-y style="height: 100%;width: 100%;">
						<view style="padding: 35rpx;">
							<view style="background-color: #f7f7f7;border-radius: 12rpx;height: 100%;width: 100%;min-height: 500px;">
								<view style="padding: 30rpx;">
									<view style="padding-top: 30rpx;padding-bottom: 20rpx;font-size: 45rpx;">用户评论</view>
									<u-image width="100%" height="240rpx" :src="src[2]"></u-image>
									<view style="padding-top: 30rpx;padding-bottom: 20rpx;font-size: 26rpx;line-height: 44rpx;">
										<label style="float: right;font-size: 24rpx;">浏览：{{university.view}}</label>
										<label @click="open" style="float: right;font-size: 24rpx;padding-right: 20rpx;">
											我要评论
										</label>
										<view style="padding-top: 10rpx;">
											<view class="comment1" v-for="(item, index) in commentList" :key="index">
												<view class="left1">
													<image :src="item.img" mode="aspectFill"></image>
												</view>
												<view class="right1">
													<view class="top1">
														<view class="name1">{{ item.username }}</view>
													</view>
													<view class="reply-box1">
														<view class="item1">
															<view class="text1">{{ item.content }}</view>
														</view>
													</view>
													<view class="bottom1">
														{{ item.time }}
													</view>
												</view>
											</view>
										</view>
										<view style="margin: 10px;"></view>
										<u-loadmore :status="status" @tap="loadmore" bg-color="#f7f7f7" />
									</view>
								</view>
							</view>
						</view>
					</scroll-view>
				</swiper-item>
			</swiper>
		</view>
		<u-modal v-model="inputShow" :show-title="false" :mask-close-able="true" @confirm="comment">
			<view class="slot-content">
				<u-input v-model="CommentInput" type="textarea" border="true" height="100" auto-height="true" border-color="#313131" />
			</view>
		</u-modal>
	</view>
</template>

<script>
	const api = require('static/js/api');
	export default {
		data() {
			return {
				src: [
					api.HOUSEINTERFACES.root+ 'university_detail/1.png',
					api.HOUSEINTERFACES.root+ 'university_detail/2.png',
					api.HOUSEINTERFACES.root+ 'university_detail/3.png',
					api.HOUSEINTERFACES.root+ 'university_detail/4.png',
					api.HOUSEINTERFACES.root+ 'university_detail/1.png',
					api.HOUSEINTERFACES.root+ 'university_detail/3.png',
				],
				list: [{
					name: '简介'
				}, {
					name: '位置'
				}, {
					name: '学费'
				}, {
					name: '专业'
				}, {
					name: '联系'
				}, {
					name: '评论'
				}],
				current: 1,
				id: '',
				university: {
					id: '',
					img: '',
					infoTypeId: '',
					intro: '',
					name: '',
					star: '',
					tuition: '',
					view: ''
					
				},
				commentList: [],
				CommentInput: '',
				status: 'loadmore',
				inputShow: false,
				height: 200,
				total: 0,
				pages: '',
				page: 1,
				limit: 6,
			}
		},
		onLoad(e) {
			this.id = e.id;
			this.current = e.current;
			this.getuniversity()
			this.getCommentList()
		},
		onReachBottom() {
			this.loadmore()
		},
		methods: {
			open() {
				var login = this.checkLogin('/pages/info/comment/comment?id=' + this.id, 2); //调用全局封装的函数,参数在上面已经介绍了
				if (!login) { //如果没有登录,则会提示
					uni.showToast({
						title: '请先登录！',
						icon: "none"
					});
					return false; //不走下面的,也就是不触发下面的函数
				} else {
					this.inputShow = true;
				}
			},
			comment() {
				if (this.CommentInput === '') {
					uni.showToast({
						title: '请输入！',
						icon: "none"
					});
				} else {
					uni.request({
						url: api.INTERFACES.commentSave,
						data: {
							userId: uni.getStorageSync('user').id,
							universityId: this.id,
							content: this.CommentInput
						},
						method: 'POST',
						success: res => {
							uni.showToast({
								title: '评论成功！',
								icon: "none"
							});
							uni.redirectTo({
								url: '/pages/info/comment/comment?id=' + this.id
							})
						},
						fail: (res, code) => {
							console.log('fail' + JSON.stringify(res));
						}
					});
					this.CommentInput = ''
				}
			},
			loadmore() {
				if (this.page >= this.totalPage) {
					this.status = 'nomore';
				} else {
					this.page = this.page + 1
					this.getCommentList()
				}
			},
			getCommentList() {
				this.status = 'loading';
				uni.request({
					url: api.INTERFACES.commentListByUniversity + this.id,
					data: {
						page: this.page,
						limit: this.limit
					},
					method: 'GET',
					success: res => {
						this.commentList = this.commentList.concat(res.data.page.records)
						this.total = res.data.page.total
						this.pages = res.data.page.pages
						if (res.data.page.current >= res.data.page.pages) {
							this.status = 'nomore';
						} else {
							this.status = 'loadmore';
						}
					},
					fail: (res, code) => {
						console.log('fail' + JSON.stringify(res));
					}
				});
			},
			sectionChange(index) {
				this.current = index;
			},
			getuniversity() {
				uni.request({
					url: api.INTERFACES.universityOne + this.id,
					data: {},
					method: 'GET',
					success: res => {
						this.university = res.data.university
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

	.comment1 {
		display: flex;

		.left1 {
			image {
				width: 64rpx;
				height: 64rpx;
				border-radius: 50%;
				background-color: #f2f2f2;
			}
		}

		.right1 {
			flex: 1;
			padding-left: 20rpx;
			font-size: 30rpx;

			.top1 {
				display: flex;
				justify-content: space-between;
				align-items: center;
				margin-bottom: 10rpx;

				.name1 {
					color: #5677fc;
				}

				.like1 {
					display: flex;
					align-items: center;
					color: #9a9a9a;
					font-size: 26rpx;

					.num1 {
						margin-right: 4rpx;
						color: #9a9a9a;
					}
				}

				.highlight1 {
					color: #5677fc;

					.num1 {
						color: #5677fc;
					}
				}
			}

			.content1 {
				margin-bottom: 10rpx;
			}

			.reply-box1 {
				// background-color: rgb(242, 242, 242);
				border-radius: 12rpx;

				.item1 {
					padding: 20rpx;
					border-bottom: solid 2rpx $u-border-color;

					.username1 {
						font-size: 24rpx;
						color: #999999;
					}
				}

				.all-reply1 {
					padding: 20rpx;
					display: flex;
					color: #5677fc;
					align-items: center;

					.more1 {
						margin-left: 6rpx;
					}
				}
			}

			.bottom1 {
				margin-top: 20rpx;
				display: flex;
				font-size: 24rpx;
				color: #9a9a9a;

				.reply1 {
					color: #5677fc;
					margin-left: 10rpx;
				}
			}
		}
	}
</style>
