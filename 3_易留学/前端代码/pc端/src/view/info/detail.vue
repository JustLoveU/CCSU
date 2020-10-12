<template>
	<div style="padding-top: 20px;" class="content">
		<Row :gutter="16">
			<Col span="16">
			<Card>
				<div>
					<div style="font-size: 22px;">{{info.title}}</div>
					<div style="margin: 10px 0;">
						<Icon type="md-time" class="padding-left" /> {{info.time}}
						<Icon type="ios-eye-outline" class="padding-left" /> {{info.view}}
						<Icon type="md-link" class="padding-left" /> <a :href="info.url" target="_blank"> {{info.source}}</a>
					</div>
					<div style="margin: 30px 5px;">
						<mavon-editor class="md" :value="info.content" :subfield="false" :defaultOpen="'preview'" :toolbarsFlag="false"
						 :editable="false" :scrollStyle="true" :ishljs="true" :boxShadow="true" previewBackground="#FFFFFF" />
					</div>
				</div>
			</Card>
			</Col>
			<Col span="8">
			<List header="热门资讯" border>
				<ListItem v-for="(item,index) in InfoList" :key="index">
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
		<div v-if="InfoList1.length != 0">
			<p style="text-align: left;padding-bottom: 20px;line-height: 20px;font-size: 18px;">
				<span class="line"></span>
				热门推荐
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
</template>

<script>
	import {
		mavonEditor
	} from 'mavon-editor'
	import 'mavon-editor/dist/css/index.css'
	export default {
		components: {
			mavonEditor
		},
		data() {
			return {
				id: '',
				info: {},
				InfoList: [],
				universityList1: [],
				InfoList1: [],
				src1: this.$global.src1
			}
		},

		created() {
			this.id = this.$route.query.id
			this.getInfo()
			this.getInfoList()
			this.getUniversityList1()
		},
		methods: {
			getInfo() {
				this.$api.postRequest(this.$url.INTERFACES.infoOne + this.id).then(res => {
					if (res.code == 0) {
						this.info = res.info
						this.getInfoList1(this.info.typeId)
					} else {
						console.log("1")
					}
				})
			},
			getInfoList() {
				this.$api.postRequest(this.$url.INTERFACES.infoList).then(res => {
					if (res.code == 200) {
						this.InfoList = res.list
					}
				})
			},
			getUniversityList1() {
				this.$api.postRequest(this.$url.INTERFACES.universityList).then(res => {
					if (res.code == 200) {
						this.universityList1 = res.list
					}
				})
			},
			getInfoList1(i) {
				var params = {
					page: 1,
					limit: 6,
					id: i
				};
				this.$api.getRequest(this.$url.INTERFACES.infoList1, params).then(res => {
					this.InfoList1 = res.page.list
				})
			},
		}
	}
</script>

<style>
	.padding-left {
		padding-left: 20px;
	}

	.row {
		overflow: hidden;
		text-overflow: ellipsis;
		white-space: nowrap;
	}
</style>
