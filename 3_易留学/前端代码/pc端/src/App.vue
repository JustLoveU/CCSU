<template>
	<div id="app">
		<Layout>
			<Row :gutter="16" style="background-color: #FFFFFF;margin: 0;z-index: 999;">
				<Col span="6">
				<img :src="bottom" height="100%" />
				</Col>
				<Col span="12">
				<div style="padding-top: 60px;">
					<i-input v-model="search" size="large" on-search="search" on-enter="seach">
					<Select v-model="select" slot="prepend" style="width: 80px;">
						<Option value="university">大学</Option>
						<Option value="info">资讯</Option>
					</Select>
					<Button slot="append" icon="ios-search" @click="seach"></Button>
					</i-input></div>
				</Col>
				<Col span="6">
				</Col>
			</Row>
			<Affix>
				<Header style="background-color: #FFFFFF;padding:0 200px;border-bottom: 2px solid #3a9bfc;">
					<Menu mode="horizontal" theme="light" active-name="1" @on-select="(key) => openSaveTip(key)">
						<MenuItem style="font-size: 20px;margin-right:20px ;" v-for="(item,index) in menu" :key="index" :name="index" :to="item.to">
						<Icon :type="item.icon" />{{item.name}}
						</MenuItem>
						<MenuItem style="font-size: 20px;" name="99">
						<Icon type="ios-exit" />logout
						</MenuItem>
					</Menu>
				</Header>
			</Affix>
			<Content>
				<router-view></router-view>
			</Content>
			<Footer>Copyright © 2020 Made with love By LomTom'Blog</Footer>

			<div class="call">
				<ButtonGroup shape="circle" vertical>
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
						icon: 'md-send'
					}
				],
				search: '',
				select: 'university',
				bottom: this.$url.HOUSEINTERFACES.root + 'img/bottom.png',
			}
		},
		methods: {
			logout() {
				localStorage.removeItem("user")
				this.$Message.success('注销成功');
				this.$router.push({
					path: '/index'
				});
			},
			openSaveTip(data) {
				if (data == '99') {
					this.logout()
				}
				// data就是默认返回选择值，type就是传入的first
			},
			seach() {
				console.log(this.search,this.select)
				if(this.select == 'info'){
					let route = this.$router.resolve({
						path: '/info_list?search='+ this.search
					});
					 window.open(route.href, '_blank');
				}
				else{
					let route = this.$router.resolve({
						path: '/university_list?search='+ this.search
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
	}

	.call {
		bottom: 150px;
		right: 100px;
		z-index: 10;
		position: fixed;
		cursor: pointer;
		color: #000000;
	}
</style>
