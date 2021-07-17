<template>
	<div>
		<div>
			<img :src="img" width="100%" style="border-radius: 4px;" />
			<div style="top: -140px;position: relative;margin: 0 100px;">
				<Card>
					<p>本团队将一致专注于打造互联网去中介化智能留学交流服务平台，智能精准推荐留学高校及专业，营造一个纯净的O20留学服务生态。</p>
				</Card>
				<div class="content">
					<div style="margin-top: 20px;">
						<p style="text-align: left;padding-bottom: 20px;line-height: 20px;font-size: 18px;">
							<span class="line"></span>
							个人信息
							<Button type="text" style="margin-left: 20px;" @click="drawerClose = true">修改</Button>
							<Button type="text" style="margin-left: 20px;" @click="logout()">注销</Button>
						</p>
					</div>

					<Row :gutter="16" style="padding-bottom: 20px;text-align: left;">
						<Col span="24" style="padding-bottom: 20px;">
						<Row :gutter="16">
							<Col span="3" style="text-align: right;">
							<p class="user_title">头像</p>
							</Col>
							<Col span="21">
							<Avatar :src="user.img" size="100" />
							</Col>
						</Row>
						</Col>
						<Col span="24" style="padding-bottom: 20px;">
						<Row :gutter="16">
							<Col span="3" style="text-align: right;">
							<p class="user_title">用户名</p>
							</Col>
							<Col span="21">
							<p class="user_content">{{user.username}}</p>
							</Col>
						</Row>
						</Col>
						<Col span="24" style="padding-bottom: 20px;">
						<Row :gutter="16">
							<Col span="3" style="text-align: right;">
							<p class="user_title">邮箱</p>
							</Col>
							<Col span="21">
							<p class="user_content">{{user.email}}</p>
							</Col>
						</Row>
						</Col>
						<Col span="24" style="padding-bottom: 20px;">
						<Row :gutter="16">
							<Col span="3" style="text-align: right;">
							<p class="user_title">简介</p>
							</Col>
							<Col span="21">
							<p class="user_content">{{user.intro}}</p>
							</Col>
						</Row>
						</Col>
					</Row>
					<!-- <div style="margin-top: 20px;">
						<p style="text-align: left;padding-bottom: 20px;line-height: 20px;font-size: 18px;">
							<span class="line"></span>
							我的评论
						</p>
					</div>
					<Card>
						<p style="float: center;" v-if="commentTotal==0">啥也没有哦</p>
					</Card> -->
					<div style="margin-top: 20px;">
						<p style="text-align: left;padding-bottom: 20px;line-height: 20px;font-size: 18px;">
							<span class="line"></span>
							我的收藏
						</p>
					</div>
					<Row :gutter="16" style="padding-bottom: 20px;">
						<Col span="24">
						<Row :gutter="16">
							<Col span="6" v-for="(item,index) in universityList" :key="index" style="padding-top: 20px;">
							<Card>
								<!-- <img :src="item.pic" width="80%" height="300px"/> -->
								<a :href="'/university_detail?id=' + item.id" target="_blank">
									<div :style="'margin:10px; width: 60px; height:60px; display: inline-block;background-image: url('+item.img+');background-size: 100% 100%;border-radius:6px'">
									</div>
								</a>
								<div style="padding: 5px 30px">
									<span style="float: center;" class="address" :title="item.name">{{item.name}}</span>
									<!-- <span style="float: right;"><a :href="item.url" target="_blank" :title="item.url">访问官网</a></span> -->
								</div>
							</Card>
							</Col>
						</Row>
						</Col>
					</Row>
					<!-- <div style="margin-top: 20px;">
						<p style="text-align: left;padding-bottom: 20px;line-height: 20px;font-size: 18px;">
							<span class="line"></span>
							我的浏览记录
						</p>
					</div>
					<Card>
						<p style="float: center;" v-if="commentTotal==0">啥也没有哦</p>
					</Card>
					<div style="margin-top: 20px;">
						<p style="text-align: left;padding-bottom: 20px;line-height: 20px;font-size: 18px;">
							<span class="line"></span>
							我的申请
						</p>
					</div>
					<Card>
						<p style="float: center;" v-if="commentTotal==0">啥也没有哦</p>
					</Card> -->
				</div>
			</div>
		</div>
		<Drawer title="个人信息修改" :closable="false" width="512" v-model="drawerClose">
			<Form :model="user" :label-width="80">
				<FormItem label="用户名">
					<Input v-model="user.username" placeholder="Enter something..."></Input>
				</FormItem>

				<FormItem label="简介">
					<Input type="text" v-model="user.email" placeholder="邮箱">
					</Input>
				</FormItem>
				<FormItem label="头像">
					<img :src="user.img" width="200px">
				</FormItem>
				<FormItem>
					<Button type="primary">Submit</Button>
					<Button style="margin-left: 8px" @click="drawerClose = false">Cancel</Button>
					<Button style="margin-left: 8px" @click="Modal1Show = true">修改邮箱</Button>
					<Button style="margin-left: 8px" @click="Modal2Show = true">修改密码</Button>
				</FormItem>
			</Form>
		</Drawer>
		<Modal v-model="Modal1Show" title="修改邮箱" >
			<Form ref="user" :model="user" :rules="user" inline>
				<FormItem prop="email" style="width:98%">
					<Input type="text" v-model="user.email" placeholder="邮箱">
					<Icon type="ios-mail-outline" slot="prepend" />
					</Input>
				</FormItem>
				<FormItem prop="code" style="width:98%">

					<Row :gutter="16">
						<Col span="16">
						<Input type="text" v-model="user.code" placeholder="验证码">
						</Icon>
						<Icon type="md-code-working" slot="prepend" />
						</Input>
						</Col>
						<Col span="4">
						<!-- <Button @click="sendCode()" :disabled="send">
							<span v-if="!send">获取验证码</span>
							<span v-else>已发送</span>
						</Button> -->
						</Col>
					</Row>
				</FormItem>
			</Form>
		</Modal>
		
		<Modal v-model="Modal2Show" title="修改邮箱" >
			<Form ref="user" :model="user" :rules="user" inline>
				<FormItem prop="password" style="width:98%">
					<Input type="password" v-model="user.password" placeholder="密码">
					<Icon type="ios-lock-outline" slot="prepend"></Icon>
					</Input>
				</FormItem>
				<FormItem prop="password" style="width:98%">
					<Input type="password" v-model="user.password" placeholder="密码">
					<Icon type="ios-lock-outline" slot="prepend"></Icon>
					</Input>
				</FormItem>
				<FormItem prop="password" style="width:98%">
					<Input type="password" v-model="user.password" placeholder="密码">
					<Icon type="ios-lock-outline" slot="prepend"></Icon>
					</Input>
				</FormItem>
			</Form>
		</Modal>
	</div>
</template>

<script>
	export default {
		data() {
			return {
				img: this.$global.fushishan1,
				data: {
					page: 1,
					limit: 3
				},
				id: '',
				universityList: [],
				universityTotal: '',
				commentList: [],
				commentTotal: '',
				user: {

				},
				drawerClose: false,
				Modal1Show: false,
				Modal2Show: false
			}
		},
		created() {
			this.id = localStorage.getItem("id");
			this.getUser();
			this.getCollectionList();
		},
		methods: {
			logout() {
				localStorage.removeItem("id")
				this.$Message.success('注销成功');
				this.$router.push({
					path: '/login'
				});
			},
			getUser() {
				this.$api.getRequest(this.$url.INTERFACES.userOne + this.id).then(res => {
					this.user = res.user
				})
			},
			getCollectionList() {
				this.$api.getRequest(this.$url.INTERFACES.universityListByUser + this.id, this.data).then(res => {
					this.universityList = res.list
				})
			},
			getCommentList() {
				this.$api.getRequest(this.$url.INTERFACES.commentListByUser + this.id, this.data).then(res => {
					this.commentList = res.page.records
					this.commentTotal = res.page.total
				})
			}
		}
	}
</script>

<style>
	.user_title {
		padding-right: 30px;
		padding-top: 10px;
		font-size: 16px;
		line-height: 20px;
		color: #989898;
	}

	.user_content {
		padding-top: 10px;
		font-size: 20px;
		line-height: 20px;
	}
</style>
