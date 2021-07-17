<template>
	<div style="padding-top: 20px;" class="content">
		<Row :gutter="16">
			<Col span="16">
			<img :src="img" width="100%" style="border-radius: 4px;" />
			<Card>
				<List size="large">
					<ListItem v-for="(item,index) in InfoList" :key="index">
						<div style="padding-right: 20px;">
							<Icon type="md-time" style="padding-right: 5px;" /> {{item.time}}
							<Icon type="ios-eye-outline" /> {{item.view}}</div>
						<div><a :href="'/info_detail?id='+item.id" target="_blank" class="row">{{item.title}}</a></div>
					</ListItem>
				</List>
			</Card>
			<div style="text-align: center;margin: 30px;"><Button @click="loadmore">{{status}}</Button></div>
			</Col>
			<Col span="8">
			<List header="热门资讯" border>
				<ListItem v-for="(item,index) in InfoList1" :key="index">
					<!-- <router-link :to="{ path: '/detail', query: { id: item.id}}"></router-link> -->
					<a :href="'/info_detail?id='+item.id" target="_blank" class="row">{{item.title}}</a>
				</ListItem>
			</List>
			<!-- <Affix :offset-top="80"> -->
			<List header="热门大学" border style="margin-top: 20px;">
				<ListItem v-for="(item,index) in universityList1" :key="index">
					<!-- <router-link :to="{ path: '/detail', query: { id: item.id}}"></router-link> -->
					<div>
						<Row :gutter="16">
							<Col span="4"><img :src="item.img" width="50px" height="50px" style="margin: 10px;"></Col>
							<Col span="20">
							<div style="font-size: 22px;line-height: 28px;">{{item.name}}</div>
							<div style="padding: 10px 30px;">
								<span>
									<Icon type="md-navigate" />{{item.location}}</span>
								<span style="padding-left: 20px;"><a :href="item.url" target="_blank">
										<Icon type="ios-link" />访问官网</a></span>
							</div>
							</Col>
						</Row>
					</div>
				</ListItem>
			</List>
			<!-- </Affix> -->
			</Col>
		</Row>
	</div>
</template>

<script>
	export default {
		data() {
			return {
				id: '',
				search: '',
				page: 1,
				limit: 6,
				totalPage: '',
				InfoList: [],
				InfoList1: [],
				universityList1: [],
				img: this.$global.fushishan1,
				status: '加载更多'
			}
		},
		created() {
			this.id = this.$route.query.id
			this.search = this.$route.query.search
			if (this.id == undefined) {
				this.getInfoSearch()
			} else {
				this.getInfoList()
			}
			this.getInfoList1()
			this.getUniversityList1()
		},
		methods: {
			getInfoList() {
				var params = {
					page: this.page,
					limit: this.limit,
					id: this.id
				};
				this.$api.getRequest(this.$url.INTERFACES.infoList1, params).then(res => {
					this.InfoList = this.InfoList.concat(res.page.list)
					this.totalPage = res.page.totalPage;
					if (res.page.currPage >= res.page.totalPage) {
						this.status = '没有更多了';
					} else {
						this.status = '加载更多';
					}
				})
			},
			getInfoSearch() {
				var params = {
					page: this.page,
					limit: this.limit,
					search: this.search
				};
				this.$api.getRequest(this.$url.INTERFACES.infoSearch, params).then(res => {
					this.InfoList = this.InfoList.concat(res.page.list)
					this.totalPage = res.page.totalPage;
					if (res.page.currPage >= res.page.totalPage) {
						this.status = '没有更多了';
					} else {
						this.status = '加载更多';
					}
				})
			},
			getInfoList1() {
				this.$api.getRequest(this.$url.INTERFACES.infoList).then(res => {
					if (res.status == 200) {
						this.InfoList1 = res.list
					}
				})
			},
			getUniversityList1() {
				this.$api.getRequest(this.$url.INTERFACES.universityList).then(res => {
					if (res.status == 200) {
						this.universityList1 = res.list
					}
				})
			},
			loadmore() {
				if (this.page >= this.totalPage) {
					this.status = '没有更多了';
				} else {
					this.page = this.page + 1
					if (this.id == undefined) {
						this.getInfoSearch()
					} else {
						this.getInfoList()
					}
				}
			}
		}
	}
</script>

<style>
	button::after {
		border: none;
	}

	button {
		background-color: #fff;
	}
</style>
