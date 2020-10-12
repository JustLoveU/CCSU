<template>
	<view>
		<u-navbar :is-back="true" title="注册" :border-bottom="false">
		</u-navbar>
		<!-- :title="!dataForm.id ? '新增' : '修改'" -->
		<view style="padding: 40rpx;background-color: #FFFFFF;margin: 30rpx;border-radius: 12rpx;">
			<u-form :model="dataForm" :rules="dataRule" ref="dataForm" @keyup.enter.native="dataFormSubmit()" label-width="80px">
				<u-form-item label="用户名" prop="username">
					<u-input v-model="dataForm.username" placeholder="用户名"></u-input>
				</u-form-item>
				<u-form-item label="密码" prop="password">
					<u-input v-model="dataForm.password" placeholder="密码"></u-input>
				</u-form-item>
				<u-form-item label="头像" prop="img">
					<u-image width="100%" height="300rpx" :src="dataForm.img"></u-image>
					<!-- http://www.hcoder.net/tutorials/info_1352.html -->
					<button type="primary" :hidden="disabled" :loading="loading" :disabled="disabled" @click="upload">选择照片</button>
				</u-form-item>
				<u-form-item label="简介" prop="intro">
					<u-input v-model="dataForm.intro" placeholder="简介"></u-input>
				</u-form-item>
				<u-form-item label="邮箱" prop="email">
					<u-input v-model="dataForm.email" placeholder="邮箱"></u-input>
				</u-form-item>
				<!-- 				<u-form-item label="注册时间" prop="date">
					<u-input v-model="dataForm.date" placeholder="注册时间"></u-input>
				</u-form-item>
				<u-form-item label="是否可用" prop="status">
					<u-input v-model="dataForm.status" placeholder="是否可用"></u-input>
				</u-form-item>
				<u-form-item label="角色" prop="role">
					<u-input v-model="dataForm.role" placeholder="角色"></u-input>
				</u-form-item> -->
			</u-form>
			<span>
				<u-button @click="dataFormSubmit()">确定</u-button>
			</span>
		</view>
	</view>
</template>

<script>
	const api = require('static/js/api');
	export default {
		data() {
			return {
				dataForm: {
					id: 0,
					username: '',
					password: '',
					img: '',
					intro: '',
					email: '',
					// date: '',
					// status: '',
					// role: '',
				},
				percent: 0,
				loading: false,
				disabled: false,
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
					}],
					// img: [{
					// 	required: true,
					// 	message: '头像不能为空',
					// 	trigger: 'blur'
					// }],
					intro: [{
						required: true,
						message: '简介不能为空',
						trigger: 'blur'
					}],
					email: [{
						required: true,
						message: '邮箱不能为空',
						trigger: 'blur'
					}],
					// date: [{
					// 	required: true,
					// 	message: '注册时间不能为空',
					// 	trigger: 'blur'
					// }],
					// status: [{
					// 	required: true,
					// 	message: '是否可用不能为空',
					// 	trigger: 'blur'
					// }],
					// role: [{
					// 	required: true,
					// 	message: '角色不能为空',
					// 	trigger: 'blur'
					// }]
				}
			}
		},
		onLoad(e) {
			console.log(e)
			this.$refs.dataForm.setRules(this.dataRule);
			this.init(e.id);
		},
		methods: {
			upload() {
				var _this = this
				uni.chooseImage({
					count: 1,
					sizeType: ['original', 'compressed'], //可以指定是原图还是压缩图，默认二者都有
					sourceType: ['album'], //从相册选择
					success: function(res) {
						const tempFilePaths = res.tempFilePaths;
						const uploadTask = uni.uploadFile({
							url: api.INTERFACES.imgUpload,
							filePath: tempFilePaths[0],
							name: 'image',
							success: function(uploadFileRes) {
								var data = JSON.parse(uploadFileRes.data)
								_this.dataForm.img = data.url
								_this.disabled = true
								console.log(_this.dataForm.img);
							}
						});
						uploadTask.onProgressUpdate(function(res) {
							_this.percent = res.progress;
							console.log('上传进度' + res.progress);
							console.log('已经上传的数据长度' + res.totalBytesSent);
							console.log('预期需要上传的数据总长度' + res.totalBytesExpectedToSend);
						});
					},
					error: function(e) {
						console.log(e);
					}
				});
			},
			init(id) {
				this.dataForm.id = id || 0
				this.$nextTick(() => {
					this.$refs['dataForm'].resetFields()
					if (this.dataForm.id) {
						uni.request({
							url: api.INTERFACES.userOne + id,
							method: 'get',
							success: res => {
								var data = res.data
								if (data && data.code === 0) {
									this.dataForm.username = data.user.username
									this.dataForm.password = ''
									this.dataForm.img =  data.user.img
									this.dataForm.intro = data.user.intro
									this.dataForm.email = data.user.email
									// this.dataForm.date = data.user.date
									// this.dataForm.status = data.user.status
									// this.dataForm.role = data.user.role
								}
							}
						})
					}
				})
			},
			// 表单提交
			dataFormSubmit() {
				this.$refs.dataForm.validate(valid => {
					if (valid) {
						uni.showLoading({
							title: '注册中'
						});
						uni.request({
							url: this.dataForm.id ? api.INTERFACES.userUpdate : api.INTERFACES.register,
							data: {
								'id': this.dataForm.id || undefined,
								'username': this.dataForm.username,
								'password': this.dataForm.password,
								'img': this.dataForm.img,
								'intro': this.dataForm.intro,
								'email': this.dataForm.email,
								// 'date': this.dataForm.date,
								// 'status': this.dataForm.status,
								// 'role': this.dataForm.role
							},
							method: 'POST',
							success: res => {
								uni.hideLoading();
								if (res.data.code === 400) {
									uni.showToast({
										title: '注册失败，该账号已被注册！',
										icon: "none"
									});
									this.dataForm.username = ''
								} else {
									uni.showToast({
										title: this.dataForm.id ? '修改成功！' : '注册成功！',
										icon: "none"
									});
									var _this = this
									setTimeout(function() {
										uni.clearStorage()
										_this.check()
									}, 1000)
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
			},
			check() {
				var user = this.checkLogin('/pages/my/index/index', 1); //调用全局封装的函数,参数在上面已经介绍了
				if (!user) { //如果没有登录,则会提示
					uni.showToast({
						title: '请登录！',
						icon: "none"
					});
					return false; //不走下面的,也就是不触发下面的函数
				}
			}
		}
	}
</script>
<style lang="scss">
	page {
		background-color: #f7f7f7;
	}
</style>
