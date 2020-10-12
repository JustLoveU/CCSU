<template>
	<div >
		<img :src="img" width="100%" style="border-radius: 4px;" />
		<div style="top: -60px;position: relative;margin: 0 100px;" class="content">
			<Card>
				<Row :gutter="16">
					<Col span="6">
					<a href="/info_list?id=1" target="_blank">
						<div class="inner">
							<Icon type="md-flame" :size="50" class="icon" />
							<p>Popular information</p>
							<p class="chinese">热点</p>
						</div>
					</a>
					</Col>
					<Col span="6">
					<a href="/info_list?id=2" target="_blank">
						<div class="inner">
							<Icon type="ios-book" :size="50" class="icon" />
							<p>Policy Support</p>
							<p class="chinese">政策</p>
						</div>
					</a>
					</Col>
					<Col span="6">
					<a href="/info_list?id=1" target="_blank">
						<div class="inner">
							<Icon type="md-bookmarks" :size="50" class="icon" />
							<p>Special research</p>
							<p class="chinese">专题</p>
						</div>
					</a>
					</Col>
					<Col span="6">
					<a href="/info_list?id=4" target="_blank">
						<div class="inner">
							<Icon type="ios-bulb" :size="50" class="icon" />
							<p>tips</p>
							<p class="chinese">小知识</p>
						</div>
					</a>
					</Col>
				</Row>
				<Row :gutter="16">
					<Col span="6" v-for="(item,index) in InfoList" :key="index">
					<List style="padding: 20px;">
						<ListItem v-for="(item1,index1) in InfoList[index]" :key="index1">
							<a :href="'/info_detail?id='+item1.id" target="_blank" class="row">{{item1.title}}</a>
						</ListItem>
					</List>
					</Col>
				</Row>
			</Card>

			<div style="margin-top: 20px;">
				<p style="text-align: left;padding-bottom: 20px;line-height: 20px;font-size: 18px;">
					<span class="line"></span>
					热门分类
				</p>
				<Row :gutter="16">
					<Col span="6" v-for="(item,index) in HotList" :key="index">
					<a :href="'/info_list?id=' + item.id" target="_blank">
						<div class="hot-card">
							<Row :gutter="16">
								<Col span="10">
								<img :src="hot[index]" width="90%"></Col>
								<Col span="14">
								<div style="margin: 10px;font-size: 22px;line-height: 24px;padding: 10px;">{{item.name}}</div>
								</Col>
							</Row>
						</div>
					</a>
					</Col>
				</Row>
			</div>

			<div style="margin-top: 20px;">
				<p style="text-align: left;padding-bottom: 20px;line-height: 20px;font-size: 18px;">
					<span class="line"></span>
					热门导读
				</p>
				<List item-layout="vertical">
					<Row :gutter="16">
						<Col span="8" v-for="(item,index) in InfoList1" :key="index">
						<div class="card">
							<a :href="'/info_detail?id='+item.id" target="_blank">
								<div :style="'width: 100%; height:200px; display: inline-block;background-image: url('+src1[index]+');background-size: 100% 100%;border-radius:6px 6px 0 0;'">
									<!-- <p style="padding: 8px; color: #FFFFFF;font-size: 22px;">123</p> -->
								</div>
								<div style="margin: 15px;text-align: left;height: 100px;">
									<span>{{item.title}}</span>
									<p style="position: absolute;bottom: 30px;">
										<Icon type="md-time" style="padding-right: 5px;" /> {{item.time}}
										<Icon type="ios-attach" /> {{item.source}}
										<Icon type="ios-eye-outline" /> {{item.view}}</p>
								</div>
							</a>
						</div>
						</Col>
					</Row>
				</List>
			</div>
		</div>
	</div>
</template>

<script>
	export default {
		data() {
			return {
				img: this.$url.HOUSEINTERFACES.root + 'img/fushishan.png',
				src1: this.$global.src1,
				hot: this.$global.hot,
				InfoList: [],
				InfoList1: [],
				HotList: [],
				page: 1,
				limit: 6
			}
		},
		created() {
			for (var i = 1; i <= 4; ++i) {
				this.getInfoList(i)
			}
			this.getInfoList1()
			this.getHotList("地区")
			this.getHotList("环节")
		},
		methods: {
			getInfoList(i) {
				var params = {
					page: this.page,
					limit: this.limit,
					id: i
				};
				this.$api.getRequest(this.$url.INTERFACES.infoList1, params).then(res => {
					this.InfoList[i - 1] = res.page.list
				})
			},
			getInfoList1() {
				this.$api.postRequest(this.$url.INTERFACES.infoList).then(res => {
					if (res.code == 200) {
						this.InfoList1 = res.list
					}
				})
			},
			getHotList(type) {
				var data = {
					status: "true",
					type: type
				};
				this.$api.postRequest(this.$url.INTERFACES.infoTypeList, data).then(res => {
					if (res.code == 200) {
						this.HotList = this.HotList.concat(res.list)
					}
				})
			}
		}
	}
</script>

<style>
	a {
		color: #000000;
	}

	.inner {
		width: 230px;
		height: 230px;
		border-radius: 50%;
		line-height: 1.8em;
		font-size: 22px;
		color: #333;
		margin: 0 3% 0px;
		-webkit-box-shadow: 0 0 5px #999;
		box-shadow: 0 0 5px #999;
		cursor: pointer;
	}

	.icon {
		margin-top: 40px;
		margin-bottom: 10px;
	}

	.chinese {
		font-size: 18px;
		color: #8c8c8c;
	}

	.line {
		position: relative;
		display: inline-block;
		content: "";
		width: 5px;
		height: 1.5em;
		background: #d2555d;
		left: -1em;
		top: 5px;
	}

	.card {
		box-shadow: 0 1px 1px rgba(0, 0, 0, .1), 0 2px 2px rgba(0, 0, 0, .1);
		background-color: #FFFFFF;
		padding: 0;
		border-radius: 6px;
	}

	.card:hover {
		transition: all .2s ease;
		box-shadow: 0 1px 11px 4px rgba(0, 0, 0, 0.1), 0 2px 4px 0px rgba(0, 0, 0, 0.1), 0 4px 8px 0px rgba(0, 0, 0, 0.1);
		animation-duration: .3s, 1.5s;
		animation-timing-function: ease-out, ease-in-out;
		animation-delay: 0s, .3s;
		animation-iteration-count: 1, infinite;
		animation-direction: normal, alternate;
		animation-fill-mode: forwards;
		animation-play-state: paused;
	}

	.hot-card {
		cursor: pointer;
		display: block;
		float: left;
		width: 100%;
		height: 90px;
		border: 1px solid #e2e8ee;
		border-radius: 6px;
		margin-top: 30px;
		margin-right: 32px;
		position: relative;
		background-color: #FFFFFF;
	}
</style>
