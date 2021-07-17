<template>
	<div class="content">
		<Card>
			<div class="u-text-center" style="padding: 30rpx;background-color: #FFFFFF;">
				<div style="padding-bottom: 8rpx;">成功率</div>
				<Divider>
					<div style="font-size: 18rpx;color: #a1a1a1;">* RATE OF SUCCESS *</div>
				</Divider>
			</div>
			<div class="u-text-center" style="padding-bottom: 30rpx;font-size: 90px;background-color: #FFFFFF;">{{result.rate}}%</div>

			<div class="u-text-center" style="padding: 30rpx;background-color: #FFFFFF;">
				<div style="padding-bottom: 8rpx;">基本信息</div>
				<Divider>
					<div style="font-size: 18rpx;color: #a1a1a1;">* INFOMATION *</div>
				</Divider>
			</div>
			<Row :gutter="16">
				<Col span="12">
				<img :src="user.img" width="180px" />
				</Col>
				<Col span="12" style="text-align: left;">
				<div class="u-text-center" style="padding-bottom: 30rpx;font-size: 30px;background-color: #FFFFFF;">用户名：{{user.username}}</div>
				<div class="u-text-center" style="padding-bottom: 30rpx;font-size: 30px;background-color: #FFFFFF;">邮箱：{{user.email}}</div>
				<div class="u-text-center" style="padding-bottom: 30rpx;font-size: 30px;background-color: #FFFFFF;">简介：{{user.intro}}</div>
				</Col>
			</Row>

		</Card>
		<card style="margin-top: 20px;">
			<p slot="title">
				<Icon type="md-school" />
				推荐院校
			</p>
			<!-- <a href="#" slot="extra" @click.prevent="changeLimit">
				<Icon type="md-more" />
				more>>
			</a> -->
			<Row :gutter="16">
				<Col span="8" v-for="(item,index) in universityList" :key="index">
				<card style="margin-top: 20px;">
					<Row :gutter="16">

						<a :href="'/university_detail?id=' + item.id" target="_blank">
							<Col span="6"><img :src="item.img" width="100px" height="100px"></Col>
							<Col span="18">
							<div style="font-size: 22px;line-height: 28px;">{{item.name}}</div>
							<div style="padding: 20px;">
								<span style="float: left;">
									<Icon type="md-navigate" />{{item.location}}</span>
								<span style="float: right;"><a :href="item.url" target="_blank">
										<Icon type="ios-link" />访问官网</a></span>
							</div>
							</Col>
						</a>
					</Row>
				</card>
				</Col>
			</Row>
			<div style="margin: 40px;">
				<a href="http://wpa.qq.com/msgrd?v=3&amp;uin=2208518676&amp;site=qq&amp;menu=yes" title="QQ"><span style="padding: 20px;">QQ</span></a>
				<a href="mailto:lomtom@qq.com" title="邮箱"><span style="padding: 20px;">Email</span></a>
			</div>
		</Card>
	</div>
</template>

<script>
	export default {
		data() {
			return {
				apply: {},
				result: {},
				count: 5,
				value: 4,
				value1: 5,
				value2: 3,
				img: '',
				user: {
					id: '',
					username: '',
					img: '',
					email: '',
					intro: ''
				},
				universityList: [],
				id: '',
				user: {}
			}
		},
		created() {
			this.id = localStorage.getItem("id");
			this.getresult()
		},
		methods: {
			goUniversity(id) {
				uni.navigateTo({
					url: '/pages/info/comment/comment?id=' + id
				})
			},
			getresult() {
				this.$api.getRequest(this.$url.INTERFACES.ResultOneByUser + this.id).then(res => {
					if (res.code == 200) {
						this.universityList[0] = res.university1;
						this.universityList[1] = res.university2;
						this.universityList[2] = res.university3;
						this.apply = res.apply;
						this.result = res.result;
					}
				})
				this.$api.getRequest(this.$url.INTERFACES.userOne + this.id).then(res => {
					this.user = res.user
				})
			}
		}
	}
</script>

<style>
</style>
