<template>
	<view>
		<u-navbar :is-back="true" title="　" :border-bottom="false">
			<view style="width: 100%;" class="u-line-1">
				{{info.title}}
			</view>
		</u-navbar>
		<template>
			<view>
				<view class="comment" style="text-align: center;">
					<view style="margin: 20rpx;font-size: 36rpx;">{{info.title}}</view>
					<u-line border-style="dotted"></u-line>
					<view style="margin: 10rpx;font-size: 22rpx;">
						<u-row gutter="10">
							<u-col span="6">
								<u-icon name="eye" />{{info.view}}
							</u-col>
							<u-col span="6">
								<u-icon name="clock" />{{info.time}}
							</u-col>
						</u-row>
					</view>
					<u-line border-style="dotted"></u-line>
				</view>
				<u-read-more>
					<towxml :nodes="info.content" />
				</u-read-more>
			</view>
		</template>
	</view>
</template>

<script>
	import towxml from '@/static/towxml/towxml'
	const api = require('static/js/api');
	export default {
		components: {
			towxml,
		},
		data() {
			return {
				id: '',
				towxmlFunc: require('@/static/towxml/index.js'),
				info: {
					content: '',
					id: '',
					time: '',
					title: '',
					typeId: '',
					universityId: '',
					url: '',
					view: '',
				}
			}
		},
		onLoad(e) {
			this.id = e.id
			this.getInfo()
		},
		methods: {
			getInfo() {
				uni.request({
					url: api.INTERFACES.infoOne + this.id,
					data: {},
					method: 'GET',
					success: res => {
						this.info = res.data.info
						this.info.content = this.towxmlFunc(res.data.info.content, "markdown")
					},
					fail: (res, code) => {
						console.log('fail' + JSON.stringify(res));
					}
				});
			}
		}
	}
</script>

<style lang="scss">
	page {
		background-color: #f7f7f7;
	}

	.comment {
		margin-bottom: 15rpx;
		padding: 30rpx;
		background-color: #ffffff;
	}
</style>
