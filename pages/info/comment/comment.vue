<template>
	<view>
		<u-navbar :is-back="true" back-text="返回" title=" " :border-bottom="false">
			<view class="u-flex u-row-right" style="width: 100%;">
				<view class="camera u-flex u-row-center" style="padding-right: 30rpx;">
					<u-icon :name="collection?'star-fill':'star'" color="#2979ff" size="32" @click="setcollection"></u-icon>
				</view>
			</view>
		</u-navbar>
		<view class="comment">
			<view class="top">
				<view class="left">
					<view class="heart-photo">
						<image :src="university.img" mode=""></image>
					</view>
					<view class=".user-info">
						<view class="name">{{university.name}}</view>
						<view class="comment">
							<u-rate count="count" v-model="university.star" :disabled="true"></u-rate>评价
						</view>
					</view>
				</view>
			</view>
			<view class="content u-font-10 u-line-3">{{university.intro}}</view>
		</view>
		<view style="margin: 10px;"></view>


		<view class="u-text-center" style="padding: 30rpx;background-color: #FFFFFF;">
			<view style="padding-bottom: 8rpx;">基本信息</view>
			<u-divider>
				<view style="font-size: 18rpx;color: #a1a1a1;">* INFOMATION *</view>
			</u-divider>
		</view>
		<u-grid :col="3" :border="false">
			<navigator :url="'/pages/info/university_detail/university_detail?id=' + id + '&current=0'" navigateTo>
				<u-grid-item>
					<u-image width="50rpx" height="50rpx" :src="src[0]"></u-image>
					<view class="grid-text">院校简介</view>
				</u-grid-item>
			</navigator>
			<navigator :url="'/pages/info/university_detail/university_detail?id=' + id + '&current=1'" navigateTo>
				<u-grid-item>
					<u-image width="50rpx" height="50rpx" :src="src[1]"></u-image>
					<view class="grid-text">地理位置</view>
				</u-grid-item>
			</navigator>
			<navigator :url="'/pages/info/university_detail/university_detail?id=' + id + '&current=2'" navigateTo>
				<u-grid-item>
					<u-image width="50rpx" height="50rpx" :src="src[2]"></u-image>
					<view class="grid-text">学费费用</view>
				</u-grid-item>
			</navigator>
			<navigator :url="'/pages/info/university_detail/university_detail?id=' + id + '&current=3'" navigateTo>
				<u-grid-item>
					<u-image width="50rpx" height="50rpx" :src="src[3]"></u-image>
					<view class="grid-text">专业特色</view>
				</u-grid-item>
			</navigator>
			<navigator :url="'/pages/info/university_detail/university_detail?id=' + id + '&current=4'" navigateTo>
				<u-grid-item>
					<u-image width="50rpx" height="50rpx" :src="src[4]"></u-image>
					<view class="grid-text">联系方式</view>
				</u-grid-item>
			</navigator>
			<navigator :url="'/pages/info/university_detail/university_detail?id=' + id + '&current=5'" navigateTo>
				<u-grid-item>
					<u-image width="50rpx" height="50rpx" :src="src[5]"></u-image>
					<view class="grid-text">用户评论</view>
				</u-grid-item>
			</navigator>
		</u-grid>


		<view style="margin: 10px;"></view>

		<view class="u-text-center" style="padding: 30rpx;background-color: #FFFFFF;">
			<view style="padding-bottom: 8rpx;">院校新闻</view>
			<u-divider>
				<view style="font-size: 18rpx;color: #a1a1a1;">
					* NEWS *
				</view>
			</u-divider>
		</view>
		<view style="background-color: #FFFFFF;padding-left:30rpx ;padding-right: 30rpx;">
			<u-row gutter="16" style="padding: 30rpx;border-bottom: 2rpx #a1a1a1;" v-for="(item,index) in infoList" :key="index"
			 @tap="toDetail(item.id)">
				<u-col span="8">
					<view>
						<view class="u-line-2" @click="toDetail(item.id)">{{item.title}}</view>
						<view style="color: #a1a1a1;font-size: 18rpx;padding-top: 15rpx;">
							<u-icon name="clock" />{{item.time}}</view>
					</view>
				</u-col>
				<u-col span="4">
					<u-image width="100%" height="120rpx" :border-radius="4" :src="src1[index]"></u-image>
				</u-col>
			</u-row>
			<u-line />
			<view class="u-text-center" style="padding: 30rpx;font-size: 24rpx;color: #828282;">查看更多资讯</view>
		</view>
		<view style="margin: 10px;"></view>
		<!-- <view class="card">
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
		<view class="card">
			<label>学费：$10000-2000</label>
		</view>
		<view style="margin: 10px;"></view>
		<view class="card">
			<label>热门攻略</label>
		</view>
		<view style="margin: 10px;"></view> -->
		<view class="card">
			<view class="u-text-center" style="padding: 30rpx;background-color: #FFFFFF;">
			<view style="padding-bottom: 8rpx;">精选评论</view>
			<u-divider>
				<view style="font-size: 18rpx;color: #a1a1a1;">
					* COMMENT *
				</view>
			</u-divider>
		</view>
			<!-- <label>精选点评（{{total}}）</label> -->
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

			<u-loadmore :status="status" @tap="loadmore" />
		</view>

		<view style="margin: 10px;"></view>
		<!-- <view class="comment" style="bottom: 0px;position: absolute;width: 100%;margin-top:5rpx;">
			<u-field v-model="value" label-width="0" placeholder="请填写评论" @focus="input" :clearable="false">
				<u-button size="mini" slot="right" type="success" @tap="comment">评论</u-button>
			</u-field>
		</view> -->
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
					api.HOUSEINTERFACES.root+ 'university_detail/u1.png',
					api.HOUSEINTERFACES.root+ 'university_detail/u2.png',
					api.HOUSEINTERFACES.root+ 'university_detail/u3.png',
					api.HOUSEINTERFACES.root+ 'university_detail/u4.png',
					api.HOUSEINTERFACES.root+ 'university_detail/u5.png',
					api.HOUSEINTERFACES.root+ 'university_detail/u6.png',
				],
				src1: [
					api.HOUSEINTERFACES.root+ 'university_detail/1.png',
					api.HOUSEINTERFACES.root+ 'university_detail/2.png',
					api.HOUSEINTERFACES.root+ 'university_detail/3.png',
					api.HOUSEINTERFACES.root+ 'university_detail/4.png',
					api.HOUSEINTERFACES.root+ 'university_detail/1.png',
					api.HOUSEINTERFACES.root+ 'university_detail/3.png',
				],
				CommentInput: '',
				status: 'loadmore',
				inputShow: false,
				height: 200,
				id: '',
				total: 0,
				pages: '',
				page: 1,
				limit: 6,
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
				infoList: [],
				commentList: [],
				collection: false,
				collection_id: ''
			};
		},
		onLoad(e) {
			this.id = e.id;
			this.getuniversity()
			this.getInfoList()
			this.getCommentList()
			this.getcollection()
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
			toDetail(e) {
				uni.navigateTo({
					url: '/pages/info/detail/detail?id=' + e
				})
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
			getInfoList() {
				uni.request({
					url: api.INTERFACES.InfoListByUniversity + this.id,
					data: {},
					method: 'GET',
					success: res => {
						this.infoList = res.data.list
					},
					fail: (res, code) => {
						console.log('fail' + JSON.stringify(res));
					}
				});
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
			getcollection() {
				uni.request({
					url: api.INTERFACES.collectionOne,
					data: {
						userId: uni.getStorageSync("user").id,
						universityId: this.id
					},
					method: 'POST',
					success: res => {
						if (res.data.code === 200) {
							this.collection = true
							this.collection_id = res.data.collection.id
						}
					},
					fail: (res, code) => {
						console.log('fail' + JSON.stringify(res));
					}
				});
			},
			setcollection() {
				if (this.collection == true) {
					uni.request({
						url: api.INTERFACES.collectionDeleteOne + this.collection_id,
						method: 'GET',
						data: {},
						success: res => {
							if (res.data.code === 200) {
								this.collection = false
								uni.showToast({
									title: '取消收藏成功！',
									icon: "none"
								});
							}
						},
						fail: (res, code) => {
							console.log('fail' + JSON.stringify(res));
						}
					});
				} else {
					var login = this.checkLogin('/pages/info/comment/comment?id=' + this.id, 2); //调用全局封装的函数,参数在上面已经介绍了
					if (!login) { //如果没有登录,则会提示
						uni.showToast({
							title: '请先登录！',
							icon: "none"
						});
						return false; //不走下面的,也就是不触发下面的函数
					} else {
						uni.request({
							url: api.INTERFACES.collectionSave,
							data: {
								userId: uni.getStorageSync("user").id,
								universityId: this.id
							},
							method: 'POST',
							success: res => {
								if (res.data.code === 200) {
									uni.showToast({
										title: '收藏成功！',
										icon: "none"
									});
									this.getcollection()
								}
							},
							fail: (res, code) => {
								console.log('fail' + JSON.stringify(res));
							}
						});
					}
				}
			},
		}
	}
</script>

<style lang="scss">
	page {
		background-color: #f7f7f7;
	}

	.slot-content {
		font-size: 28rpx;
		color: $u-content-color;
		padding: 10rpx;
	}

	.card {
		padding: 30rpx;
		font-size: 32rpx;
		background-color: #ffffff;
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
					margin-left: 10rpx;
					color: #5677fc;
					font-size: 28rpx;
					margin-bottom: 4rpx;
					padding-left: 30rpx;

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
				background-color: rgb(242, 242, 242);
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
