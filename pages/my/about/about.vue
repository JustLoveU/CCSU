<template>
	<view>
		<u-navbar :is-back="true" title="关于" :border-bottom="false">
		</u-navbar>
		<view class="card">
			<view style="text-align: center;margin-bottom: 20rpx;font-size: 35rpx;">关于这个小程序</view>
			<text style="line-height: 60rpx;">
				留学日本在文化、地理、安全、安心等方面占有绝对优势，并且日本也成为大多数留学生青睐的对象。但是传统留学中介行业特别是针对日本的留学行业无论是从模式还是从质量上都已经跟不上时代的步伐，留学市场乱象丛生。由此互联网+留学平台应运而生。而本团队将一致专注于打造互联网去中介化智能留学交流服务平台，智能精准推荐留学高校及专业，营造一个纯净的O20留学服务生态。
			</text>
		</view>
	</view>
</template>

<script>
	export default {
		data() {
			return {

			};
		}
	}
</script>

<style lang="scss">
	page {
		background-color: #f7f7f7;
	}

	.card {
		margin: 35rpx;
		padding: 40rpx;
		background-color: #FFFFFF;
		border-radius: 6rpx;
	}
</style>
