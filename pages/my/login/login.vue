<template>
	<view class="wrap">
		<view class="top"></view>
		<view class="content">
			<view class="title">欢迎登录</view>
			<u-form :model="form" ref="uForm">
				<u-form-item label="姓名" prop="username">
					<u-input v-model="form.username" />
				</u-form-item>
				<u-form-item label="密码" prop="password">
					<u-input v-model="form.password" />
				</u-form-item>
			</u-form>
			<button @tap="submit" :style="[inputStyle]" class="getCaptcha">提交</button>
			<view class="alternative">
				<navigator url="/pages/my/register/register" navigateTo>
					<view class="password">注册账号</view>
				</navigator>
			</view>
		</view>
		<view class="buttom">
			<!-- <view class="loginType">
				<view class="wechat item">
					<view class="icon">
						<u-icon size="70" name="weixin-fill" color="rgb(83,194,64)"></u-icon>
					</view>
					微信
				</view>
				<view class="QQ item">
					<view class="icon">
						<u-icon size="70" name="qq-fill" color="rgb(17,183,233)"></u-icon>
					</view>
					QQ
				</view>
			</view> -->
			<view class="hint">
				登录代表同意
				<text class="link">用户协议、隐私政策，</text>
				<!-- 并授权使用您的账号信息（如昵称、头像、收获地址）以便您统一管理 -->
			</view>
		</view>

	</view>
</template>

<script>
	export default {
		data() {
			return {
				backtype: '',
				backpage: '',
				tel: '',
				form: {
					username: "lomtom",
					password: "123"
				},
				dataRule: {
					username: [{
						required: true,
						message: '用户名不能为空',
						trigger: 'blur'
					}],
					password: [{
						required: true,
						message: '密码不能为空',
						trigger: 'blur'
					}]
				}
			}
		},
		computed: {
			inputStyle() {
				let style = {};
				if (this.tel) {
					style.color = "#fff";
					style.backgroundColor = this.$u.color['warning'];
				}
				return style;
			}
		},
		onLoad(e) {
			this.backpage = e.backpage;
			this.backtype = e.backtype;
			this.$refs.uForm.setRules(this.dataRule);
		},
		methods: {
			toPage(url) {
				uni.navigateTo({
					url: url
				})
			},
			submit() {
				this.$refs.uForm.validate(valid => {
					if (valid) {
						uni.showLoading({
							title: '登录中'
						});
						const api = require('static/js/api');
						uni.request({
							url: api.INTERFACES.login,
							data: {
								username: this.form.username,
								password: this.form.password
							},
							method: 'POST',
							success: res => {
								uni.hideLoading();
								if (res.data.code === 200) {
									// 设置2019-现在年份
									var date = new Date;
									this.nowtime = date.getFullYear();
									//TODO 登录验证
									uni.setStorage({
										key: 'user',
										data: res.data.user
									});
									uni.showToast({
										title: '登陆成功！',
										icon: "none"
									});
									var _this = this
									setTimeout(function() {
									if (_this.backtype === '1') {
										uni.switchTab({
											url: _this.backpage
										})
									} else {
										uni.navigateTo({
											url: _this.backpage
										})
									}
									}, 1000)
								} else if (res.data.code === 400) {
									uni.showToast({
										title: res.data.date,
										icon: "none"
									});
								} else {
									uni.showToast({
										title: '登录失败，请检查！',
										icon: "none"
									});
								}
							},
							fail: (res, code) => {
								console.log('fail' + JSON.stringify(res));
							}
						});
					} else {
						console.log('请检查');
					}
				});
			}
			
		}
	};
</script>

<style lang="scss" scoped>
	button::after {
		border: none;
	}

	button {
		background-color: #fff;
	}

	page {
		background-color: #f7f7f7;
	}
	.wrap {
		background-color: #fff;
		font-size: 28rpx;
		padding: 40rpx;
		margin: 35rpx;

		.content {
			width: 600rpx;
			margin: 80rpx auto 0;

			.title {
				text-align: left;
				font-size: 60rpx;
				font-weight: 500;
				margin-bottom: 100rpx;
			}

			input {
				text-align: left;
				margin-bottom: 10rpx;
				padding-bottom: 6rpx;
			}

			.tips {
				color: $u-type-info;
				margin-bottom: 60rpx;
				margin-top: 8rpx;
			}

			.getCaptcha {
				background-color: rgb(253, 243, 208);
				color: $u-tips-color;
				border: none;
				font-size: 30rpx;
				padding: 12rpx 0;

				&::after {
					border: none;
				}
			}

			.alternative {
				color: $u-tips-color;
				display: flex;
				justify-content: space-between;
				margin-top: 30rpx;
			}
		}

		.buttom {
			.loginType {
				display: flex;
				padding: 350rpx 150rpx 150rpx 150rpx;
				justify-content: space-between;

				.item {
					display: flex;
					flex-direction: column;
					align-items: center;
					color: $u-content-color;
					font-size: 28rpx;
				}
			}

			.hint {
				padding: 20rpx 40rpx;
				font-size: 20rpx;
				color: $u-tips-color;

				.link {
					color: $u-type-warning;
				}
			}
		}
	}
</style>
