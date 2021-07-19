<template>
	<view>
		<u-navbar :is-back="true" title="　" :border-bottom="false">
			<view style="width: 100%;">
				{{type}}
			</view>
		</u-navbar>
		<view style="margin: 10rpx;"></view>
		<view>
			<view class="comment" v-for="(item,index) in infoList" :key="index" @tap="toDetail(item.id)">
				<u-section class="u-line-1" :title="(index+1)+'、'+item.title" :right="false" :bold="false" ></u-section>
			</view>
		</view>
		<u-loadmore :status="status" bg-color="#ededed" @tap="loadmore" />
	</view>
</template>

<script>
	const api = require('static/js/api');
	export default {
		data() {
			return {
				status: 'loadmore',
				type: '',
				infoList: [],
				totalPage: '',
				page: 1,
				limit: 6,
				id: '',
				search: ''
			}
		},
		onLoad(e) {
			this.id = e.id
			this.type = e.type
			this.search = e.search
			if (this.id == undefined) {
				this.getInfoSearch()
			} else {
				this.getInfoList1()
			}
		},
		onReachBottom() {
			this.loadmore()
		},
		methods: {
			getInfoList1() {
				this.status = 'loading';
				uni.request({
					url: api.INTERFACES.infoList1,
					data: {
						page: this.page,
						limit: this.limit,
						id: this.id
					},
					method: 'GET',
					success: res => {
						this.infoList = this.infoList.concat(res.data.page.list)
						this.totalPage = res.data.page.totalPage;
						if (res.data.page.currPage >= res.data.page.totalPage) {
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
			getInfoSearch() {
				this.status = 'loading';
				uni.request({
					url: api.INTERFACES.infoSearch,
					data: {
						page: this.page,
						limit: this.limit,
						search: this.search
					},
					method: 'GET',
					success: res => {
						this.infoList = this.infoList.concat(res.data.page.list)
						this.totalPage = res.data.page.totalPage;
						if (res.data.page.currPage >= res.data.page.totalPage) {
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
			loadmore() {
				if (this.page >= this.totalPage) {
					this.status = 'nomore';
				} else {
					this.page = this.page + 1
					if (this.id == undefined) {
						this.getInfoSearch()
					} else {
						this.getInfoList1()
					}
				}
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
		background-color: #ededed;
	}

	.comment {
		margin-bottom: 15rpx;
		padding: 30rpx;
		background-color: #ffffff;
	}
</style>
