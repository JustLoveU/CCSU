<template>
	<div :style="'width: 100%; height: 40rem; display: inline-block;background-image: url('+background_image+');background-size: 100% 100%;'">
		<Row style="margin: 5% 30%;height: 480px;">
			<Col span="12">
			<div style="height: 480px; background: rgba(0,0,0,.4);color: #FFFFFF;text-align: left;">
				<div style="padding: 40px;">
					<h1>
						留学无难事
					</h1>
					<p style="margin-top: 20px;line-height: 34px;">留学日本在文化、地理、安全、安心等方面占有绝对优势，并且日本也成为大多数留学生青睐的对象。但是传统留学中介行业特别是针对日本的留学行业无论是从模式还是从质量上都已经跟不上时代的步伐，留学市场乱象丛生。由此互联网+留学平台应运而生。
						<span style="border-bottom: 1px solid #f06c03;
    padding: 5px 0;">而本团队将一致专注于打造互联网去中介化智能留学交流服务平台，智能精准推荐留学高校及专业，营造一个纯净的O20留学服务生态。</span></p>
				</div>
			</div>
			</Col>
			<Col span="12">
			<div style="height: 480px;background-color: #fff;border: 1px solid #fff;">
				<div style="padding: 40px;">
					<h2 style="margin-bottom: 30px;">登录</h2>
					<Form ref="form_login" :model="form_login" :rules="ruleInline" inline>
						<FormItem prop="username" style="width:98%">
							<Input type="text" v-model="form_login.username" placeholder="username">
							<Icon type="ios-person-outline" slot="prepend"></Icon>
							</Input>
						</FormItem>
						<FormItem prop="password" style="width:98%">
							<Input type="password" v-model="form_login.password" placeholder="Password">
							<Icon type="ios-lock-outline" slot="prepend"></Icon>
							</Input>
						</FormItem>
						<p style="text-align: right;">注册账号</p>
						<FormItem>
							<Button type="primary" @click="handleSubmit('form_login')">登录</Button>
						</FormItem>
					</Form>
				</div>
			</div>

			</Col>
		</Row>


	</div>
</template>
<script>
	export default {
		data() {
			return {
				background_image: this.$url.HOUSEINTERFACES.root + 'img/login_background.png',
				form_login: {
					username: '',
					password: ''
				},
				ruleInline: {
					username: [{
						required: true,
						message: '请输入用户名',
						trigger: 'blur'
					}],
					password: [{
							required: true,
							message: '请输入密码',
							trigger: 'blur'
						},
						{
							type: 'string',
							min: 6,
							message: '密码不能少于6个字符',
							trigger: 'blur'
						}
					]
				}
			}
		},
		methods: {
			handleSubmit(name) {
				var user = {
					id: 1,
					username: 'lomtom'
				}
				localStorage.setItem("user", user);
				this.$refs[name].validate((valid) => {
					if (valid) {
						this.login()
					} else {
						this.$Message.error('Fail!');
					}
				})
			},
			login() {
				var user = {
					username: this.form_login.username,
					password: this.form_login.password
				}
				console.log(user)
				this.$api.postRequest(this.$url.INTERFACES.login, user).then(res => {
					if (res.code == 404) {
						this.$Message.success('请检查输入!');
					} else if (res.code == 200) {
						this.$Message.success('登陆成功!');
						this.$router.push({
							path: localStorage.getItem("to")
						});
						localStorage.removeItem("to")
						localStorage.setItem("id", res.user.id);
					}
				})
			}
		}
	}
</script>
