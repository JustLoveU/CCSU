<template>
	<div :style="'width: 100%; height: 40rem; display: inline-block;background-image: url('+background_image+');background-size: 100% 100%;'">
		<Row style="margin: 5% 20%;height: 480px;">
			<Col span="12">
			<div style="height: 480px; background: rgba(0,0,0,.4);color: #FFFFFF;text-align: left;">
				<div style="padding: 40px;">
					<h1>
						留学无难事
					</h1>
					<p style="margin-top: 20px;line-height: 30px;">留学日本在文化、地理、安全、安心等方面占有绝对优势，并且日本也成为大多数留学生青睐的对象。但是传统留学中介行业特别是针对日本的留学行业无论是从模式还是从质量上都已经跟不上时代的步伐，留学市场乱象丛生。由此互联网+留学平台应运而生。
						<span style="border-bottom: 1px solid #f06c03;
    padding: 5px 0;">而本团队将一致专注于打造互联网去中介化智能留学交流服务平台，智能精准推荐留学高校及专业，营造一个纯净的O20留学服务生态。</span></p>
				</div>
			</div>
			</Col>
			<Col span="12">
			<div style="height: 480px;background-color: #fff;border: 1px solid #fff;">
				<div style="padding: 40px;">
					<Tabs value="name1">
						<TabPane label="登录" name="name1">
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
								<FormItem>
									<Button type="primary" @click="handleSubmit('form_login')">登录</Button>
								</FormItem>
							</Form>
						</TabPane>
						<TabPane label="注册" name="name2">
							<h2 style="margin-bottom: 30px;">注册</h2>
							<Form ref="form_register" :model="form_register" :rules="ruleInline_register" inline>
								<FormItem prop="username" style="width:98%">
									<Input type="text" v-model="form_register.username" placeholder="用户名">
									<Icon type="ios-person-outline" slot="prepend"></Icon>
									</Input>
								</FormItem>
								<FormItem prop="password" style="width:98%">
									<Input type="password" v-model="form_register.password" placeholder="密码">
									<Icon type="ios-lock-outline" slot="prepend"></Icon>
									</Input>
								</FormItem>
								<FormItem prop="email" style="width:98%">
									<Input type="text" v-model="form_register.email" placeholder="邮箱">
									<Icon type="ios-mail-outline" slot="prepend"/>
									</Input>
								</FormItem>
								<FormItem prop="code" style="width:98%">

									<Row :gutter="16">
										<Col span="16">
										<Input type="text" v-model="form_register.code" placeholder="验证码">
										</Icon><Icon type="md-code-working" slot="prepend"/>
										</Input>
										</Col>
										<Col span="4">
										<Button @click="sendCode()" :disabled="send">
											<span v-if="!send">获取验证码</span>
											<span v-else>已发送</span>
										</Button>
										</Col>
									</Row>
								</FormItem>
								<FormItem>
									<Button type="primary" @click="handleSubmit_register('form_register')">注册</Button>
								</FormItem>
							</Form>
						</TabPane>
					</Tabs>

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
				send: false,
				background_image: this.$global.background_image,
				form_login: {
					username: '',
					password: ''
				},
				form_register: {
					username: '',
					password: '',
					email: '',
					code: ''
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
				},
				ruleInline_register: {
					username: [{
						required: true,
						message: '请输入用户名',
						trigger: 'blur'
					}],
					email: [{
							required: true,
							message: '邮箱不能为空！！！',
							trigger: 'blur'
						},
						{
							type: 'email',
							message: '请检查邮箱格式',
							trigger: 'blur'
						}
					],
					code: [{
						required: true,
						message: '请输入验证码',
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
			sendCode() {
				if (this.form_register.email == '') {
					this.$Message.error('邮箱不能为空!');
					return
				}
				this.send = true
				var email = this.form_register.email
				this.$api.getRequest(this.$url.INTERFACES.sendCode + '?email=' + email).then(res => {
					if (res.status == 200) {
						this.$Message.success('发送成功!');
					} else {
						this.$Message.error('出现错误，请联系管理员!');
					}
				})
			},
			handleSubmit_register(name) {
				this.$refs[name].validate((valid) => {
					if (valid) {
						this.register()
					} else {
						this.$Message.error('Fail!');
					}
				})
			},
			register() {
				var form_register = {
					username: this.form_register.username,
					password: this.form_register.password,
					email: this.form_register.email,
					code: this.form_register.code,
				}
				this.$api.postRequest(this.$url.INTERFACES.register, form_register).then(res => {
					if (res.status == 200) {
						this.$Message.success('注册成功!');
						this.form_register = {
							username: '',
							password: '',
							email: '',
							code: ''
						}
					} else {
						this.$Message.success(res.message);
					}
				})
			},
			handleSubmit(name) {
				this.$refs[name].validate((valid) => {
					if (valid) {
						this.login()()
					} else {
						this.$Message.error('Fail!');
					}
				})
			},
			login() {
				var loginForm = {
					username: this.form_login.username,
					password: this.form_login.password
				}
				this.$api.postRequest(this.$url.INTERFACES.login, loginForm).then(res => {
					if (res.status == 1103) {
						this.$Message.success('登陆成功!');
						localStorage.setItem("id", res.user.id);
						localStorage.setItem("username", res.user.username);
						localStorage.setItem("token", res.token);
						this.$router.push({
							path: '/about'
						});
					} else {
						this.$Message.error(res.message);
					}
				})
			}
		}
	}
</script>
