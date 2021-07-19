<template>
	<view>
		<view style="padding: 30rpx;">
			<u-steps :list="numList" :current="4" direction="column" mode="number"></u-steps>
			<!-- <u-time-line>
				<u-time-line-item nodeTop="2">
					<template v-slot:node>
						<view class="u-node" style="background: #19be6b;">
							<u-icon name="pushpin-fill" color="#fff" :size="24"></u-icon>
						</view>
					</template>
					<template v-slot:content>
						<view>
							<view class="u-order-title">待取件</view>
							<view class="u-order-desc">[自提柜]您的快件已放在楼下侧门，直走前方53.6米，左拐约10步，再右拐直走，见一红灯笼停下，叩门三下，喊“芝麻开门”即可。</view>
							<view class="u-order-time">2019-05-08 12:12</view>
						</view>
					</template>
				</u-time-line-item>
				<u-time-line-item>
					<template v-slot:content>
						<view>
							<view class="u-order-desc">申请&取得许可</view>
							<view class="u-order-time">2019-12-06 22:30</view>
						</view>
					</template>
				</u-time-line-item>
				<u-time-line-item>
					<template v-slot:content>
						<view>
							<view class="u-order-desc">出愿结果/入学通知书</view>
							<view class="u-order-time">2019-12-06 22:30</view>
						</view>
					</template>
				</u-time-line-item>
				<u-time-line-item>
					<template v-slot:content>
						<view>
							<view class="u-order-desc">拿内诺、出愿</view>
							<view class="u-order-time">2019-12-06 22:30</view>
						</view>
					</template>
				</u-time-line-item>
				<u-time-line-item>
					<template v-slot:content>
						<view>
							<view class="u-order-desc">准备研究计划书 / 面试</view>
							<view class="u-order-time">2019-12-06 22:30</view>
						</view>
					</template>
				</u-time-line-item>
				<u-time-line-item>
					<template v-slot:content>
						<view>
							<view class="u-order-desc">确认申请教授</view>
							<view class="u-order-time">2019-12-06 22:30</view>
						</view>
					</template>
				</u-time-line-item>
			</u-time-line> -->
		</view>
	</view>
</template>


<script>
	export default {
		data() {
			return {
				numList: [{
					name: '确认申请教授'
				},{
					name: '准备研究计划书 / 面试'
				},{
					name: '拿内诺、出愿'
				},{
					name: '出愿结果/入学通知书'
				},{
					name: '申请&取得许可'
				},{
					name: '办理签证'
				},{
					name: '赴日'
				}]
			}
		}
	}
</script>

<style lang="scss">
	.u-node {
		width: 44rpx;
		height: 44rpx;
		border-radius: 100rpx;
		display: flex;
		justify-content: center;
		align-items: center;
		background: #d0d0d0;
	}

	.u-order-title {
		color: #333333;
		font-weight: bold;
		font-size: 32rpx;
	}

	.u-order-desc {
		color: rgb(150, 150, 150);
		font-size: 28rpx;
		margin-bottom: 6rpx;
	}

	.u-order-time {
		color: rgb(200, 200, 200);
		font-size: 26rpx;
	}
</style>
