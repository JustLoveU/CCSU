<template>
	<div id="app">
		<Layout>
			<Row :gutter="16" style="background-color: #FFFFFF;margin: 0;z-index: 999;">
				<Col span="6">
				<div >
					<img :src="bottom" style="height: 120px;" />
					<span style="padding-left: 120px;  font-size: 16px;
    color: #949494;
    height: 19px;
    text-align: justify;
    text-align-last: justify;">让留学变得简单</span>
				</div>
				</Col>
				<Col span="12">
				<div style="padding-top: 60px;display: flex;">
					<!-- <Input v-model="search" :border="false" placeholder="Enter something..." /> -->
					<Select v-model="select" slot="prepend" style="width: 100px;" size="large">
						<Option value="university">大学</Option>
						<Option value="info">资讯</Option>
					</Select>
					<Input v-model="search" size="large" @on-search="seachSubmit" @on-enter="seachSubmit" :border="false" placeholder="请输入你要搜索的内容..."
					 style="border-bottom: #a7a7a7 2px solid;margin-left: 20px;">
					<Icon type="ios-search" slot="suffix" size="30" color="#3a9bfc" />
					</Input>
				</div>
				</Col>
				<Col span="6">
				<div style="padding-top: 60px;text-align: left;padding-left: 20px;">
					<a>
						<Poptip trigger="hover" placement="bottom">
							<div slot="content" style="text-align: center;">
								<img src="https://website-university.oss-cn-beijing.aliyuncs.com/download.png" width="160px">
								<p style="color: #989898;">扫一扫下载</p>
							</div>
							<p style="font-size: 16px;font-weight: bold;">
								易留学 app
								<Icon type="md-arrow-dropdown" color="#ff9c00" size="20"/>
							</p>
							<p style="color: #989898;font-size: 10px;line-height: 16px;">扫描二维码下载</p>
						</Poptip>
					</a>
				</div>
				</Col>
			</Row>
			<Affix>
				<Header style="background-color: #FFFFFF;padding:0 200px;border-bottom: 2px solid #3a9bfc;">
					<Menu mode="horizontal" theme="light" active-name="1" @on-select="(key) => openSaveTip(key)">
						<MenuItem style="font-size: 20px;margin-right:90px ;" v-for="(item,index) in menu" :key="index" :name="index" :to="item.to">
						<Icon :type="item.icon" />{{item.name}}
						</MenuItem>
						<!-- <MenuItem style="font-size: 20px;" name="99">
						<Icon type="ios-exit" />logout
						</MenuItem> -->
					</Menu>
				</Header>
			</Affix>
			<Content>
				<router-view></router-view>
			</Content>
			<Footer>
				<div style="color: #989898;font-size: 12px;line-height: 26px;">
					<p><router-link style="color: #989898;" v-for="(item,index) in menu" :key="index" :name="index" :to="item.to">{{item.name}} | </router-link><a style="color: #989898;">关于我们</a></p>
					<p>易留学 让留学变得简单 Copyright © 2021 湖南瑞展</p>
				</div>
			</Footer>

			<div class="call">
				<ButtonGroup shape="circle" vertical>
					<Poptip placement="left-start" trigger="hover" style="position:fixed; bottom: 200px;right: 100px;">
						<Button icon="md-qr-scanner" size="large">
						</Button>
						<div slot="content">
							<Row>
								<Col :span="12">
								<img src="./assets/weixin-qr.png" height="80px" />
								<p style="font-size: 10px;">公众号</p>
								</Col>

								<Col :span="12">
								<img src="https://website-university.oss-cn-beijing.aliyuncs.com/download.png" height="80px" />
								<p style="font-size: 10px;">应用下载</p>
								</Col>
							</Row>
						</div>
					</Poptip>

					<Poptip title="电话:4000-0000-000" content="微信:lomtom-" placement="left-start" trigger="hover">
						<Button icon="md-mail" size="large">
						</Button>
					</Poptip>
					<BackTop :height="100" :bottom="100" :right="100">
						<Button size="large" shape="circle" icon="md-arrow-dropup">
						</Button>
					</BackTop>

				</ButtonGroup>
			</div>


		</Layout>
	</div>
</template>

<script>
	export default {
		name: 'app',
		data() {
			return {
				menu: [{
						to: '/index',
						name: '首页',
						icon: 'md-home',
					},
					{
						to: '/info_index',
						name: '留学资讯',
						icon: 'ios-paper'
					},
					{
						to: '/university_index',
						name: '留学选校',
						icon: 'md-flask'
					},
					{
						to: '/diy_index',
						name: '留学申请',
						icon: 'md-school'
					},
					{
						to: '/about',
						name: '我的',
						icon: 'md-contact'
					}
				],
				search: '',
				select: 'university',
				bottom: this.$global.bottom,
			}
		},
		methods: {
			
			openSaveTip(data) {
				if (data == '99') {
					this.logout()
				}
				// data就是默认返回选择值，type就是传入的first
			},
			seachSubmit() {
				console.log(this.search, this.select)
				if (this.select == 'info') {
					let route = this.$router.resolve({
						path: '/info_search?search=' + this.search
					});
					window.open(route.href, '_blank');
				} else {
					let route = this.$router.resolve({
						path: '/university_search?search=' + this.search
					});
					window.open(route.href, '_blank');
				}
			}
		}
	}
</script>

<style>
	.ivu-back-top i {
		/* !important */
		color: #515A6E !important;
		font-size: 24px;
		padding: 0 !important;
	}

	#app {
		font-family: 'Avenir', Helvetica, Arial, sans-serif;
		-webkit-font-smoothing: antialiased;
		-moz-osx-font-smoothing: grayscale;
		text-align: center;
		color: #2c3e50;
		min-width: 1500px;
	}

	.content {
		padding-left: 10%;
		padding-right: 10%;
		padding-top: 20px;
		background-color: #FFFFFF;
	}

	.call {
		bottom: 150px;
		right: 100px;
		z-index: 10;
		position: fixed;
		cursor: pointer;
		color: #000000;
	}

	.box-title {
		margin-top: 20px;
		text-align: left;
		padding-bottom: 10px;
		line-height: 20px;
		font-size: 18px;
		border-bottom: 1px solid #e4e4e4
	}
	
	.box-title1 {
		margin-top: 20px;
		text-align: left;
		padding-bottom: 10px;
		line-height: 20px;
		font-size: 18px;
	}

	.line {
		position: relative;
		display: inline-block;
		content: "";
		width: 5px;
		height: 1.5em;
		background: #3a9bfc;
		left: -1em;
		top: 5px;
	}


	.more {
		color: #555;
		float: right;
		font-size: 12px;
		padding-right: 30px;
		font-family: "Helvetica Neue", Helvetica, simsun, sans-serif
	}
</style>
