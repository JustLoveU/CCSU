<template>
	<u-index-list :scrollTop="scrollTop">
		<view v-for="(item, index) in indexList" :key="index">
			<u-index-anchor :index="item" />
			<navigator url="/pages/info/comment/comment?id=1" navigateTo>
				<view class="list-cell">
					普林斯顿大学
				</view>
			</navigator>
			<navigator url="/pages/info/comment/comment?id=1" navigateTo>
				<view class="list-cell">
					哈佛大学
				</view>
			</navigator>
			<navigator url="/pages/info/comment/comment?id=1" navigateTo>
				<view class="list-cell">
					哥伦比亚大学
				</view>
			</navigator>
		</view>
	</u-index-list>
</template>

<script>
	export default {
		data() {
			return {
				scrollTop: 0,
				indexList: ["A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U",
					"V", "W", "X", "Y", "Z", "#"
				]
			}
		},
		onPageScroll(e) {
			this.scrollTop = e.scrollTop;
		}
	}
</script>

<style lang="scss">
	.list-cell {
		display: flex;
		box-sizing: border-box;
		width: 100%;
		padding: 10px 24rpx;
		overflow: hidden;
		color: #323233;
		font-size: 14px;
		line-height: 24px;
		background-color: #fff;
	}
</style>
