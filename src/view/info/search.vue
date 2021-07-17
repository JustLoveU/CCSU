<template>
	<div style="padding-top: 20px;" class="content">
		<Row>
			<div class="square">
				<ul class="select">
					<Row>
						<Col span="24">
						<li class="select-result">
							<dl>
								<dt>资讯广场</dt>
								<dd class="arrowright">&gt;</dd>
								<dd class="school">“{{keywords}}”</dd>
							</dl>
						</li>
						</Col>
						<Col span="24">
						<li class="select-search">
							<dl>
								<dt>资讯名称：</dt>
								<dd>
									<input class="input-search" placeholder="请输入内容" v-model="keywords"></input>
									<button class="btn-search" @click="getInfoSearch"></button>
									<div class="result-lists">
									</div>
								</dd>
								<dd style="padding-left: 50px;">
									<RadioGroup v-model="searchField" @on-change="getInfoSearch">
										<Radio label="title" border>
											标题
											<Icon type="md-easel" />
											</Radio>
										<Radio label="content" border>
											内容
											<Icon type="md-list-box" />
											</Radio>
									</RadioGroup>
								</dd>
							</dl>
						</li>
						<li class="select-list clearfix">
							<dl>
								<dt>发布时间：</dt>
								<dd>
									<RadioGroup v-model="year" @on-change="getInfoSearch">
										<Radio label="2010-2013" border>2010 ~ 2013</Radio>
										<Radio label="2014-2017" border>2014 ~ 2017</Radio>
										<Radio label="2018-2021" border>2018 ~ 至今</Radio>
										<Radio label="2000-2021" border>全部</Radio>
									</RadioGroup>
								</dd>
							</dl>
						</li>
						<li class="select-list clearfix selectCountry">
							<dl>
								<dt>来 源：</dt>
								<dd>
									<RadioGroup v-model="source" type="button" @on-change="getInfoSearch">
										<Radio v-for="(item,index) in sourceList" :key="index" :label="item" v-if="index <= 6"></Radio>
										<Radio label="" border>全部</Radio>
									</RadioGroup>
								</dd>
							</dl>
						</li>

						<li class="select-list clearfix">
							<dl>
								<dd>
									<RadioGroup v-model="sortField" @on-change="getInfoSearch">
										<Radio label="" border>综合
										</Radio>
										<Radio label="time" border>
											时间
											<Icon type="md-clock" />
										</Radio>
										<Radio label="view" border>
											访问量
											<Icon type="md-eye" />
										</Radio>
									</RadioGroup>
								</dd>

								<dd v-if="sortField != ''" style="padding-left: 50px;">
									<RadioGroup v-model="sortRule" @on-change="getInfoSearch">
										<Radio label="desc" border>降序
											<Icon type="md-arrow-round-down" />
										</Radio>
										<Radio label="asc" border>升序
											<Icon type="md-arrow-round-up" />
										</Radio>
									</RadioGroup>
								</dd>
							</dl>
						</li>
						</Col>
					</Row>
				</ul>
			</div>
		</Row>
		<Row :gutter="16" style="padding-top: 20px;">
			<Col span="16">
			<div v-if="total == 0" class="noinfo">
				<p>未搜索到相关资讯，请换个条件搜索！</p>
			</div>
			<Card v-else :bordered="false" :dis-hover="true">
				<List size="large">
					<ListItem >
						<h3 style="font-size: 18px;">共  {{total}} 篇资讯</h3>
					</ListItem>
					<ListItem v-for="(item,index) in InfoList" :key="index">
						<div style="text-align: left;">
							<a :href="'/info_detail?id='+item.content.id" target="_blank" style="font-size: 18px;" v-if="searchField=='title' && item.highlightFields != {}" v-html="item.highlightFields.title[0]"></a>
							<a :href="'/info_detail?id='+item.content.id" target="_blank" style="font-size: 18px;" v-else v-html="item.content.title"></a>
							<div style="text-align: left;padding-top: 3px;padding-bottom: 5px;font-size: 10px;color: #8d8d8d;">
								<Icon type="md-time" style="padding-right: 5px;" /> {{item.content.time}}
								<Icon type="ios-eye-outline" /> {{item.content.view}}
							</div>
							<div>
								<Row>
									<Col span="8" v-if="item.content.pic != null">
									<img :src="item.content.pic" width="100%">
									</Col>
									<Col :span="item.content.pic != null?16:24">
									<span class="info_row" v-if="searchField=='title'" v-html="item.content.content">
										<!-- <p v-for="(item1,index1) in item.highlightFields.content" :key="index1" v-if="index1 < 4">{{item1}}</p> -->
									</span>
									<span class="info_row" v-for="(item1,index1) in item.highlightFields.content" :key="index1" v-if="searchField!='title' && index1 < 2">
										<span v-html="item1"></span>
									</span>
									</Col>
								</Row>
							</div>
						</div>
					</ListItem>
				</List>
			</Card>
			<div style="text-align: center;margin: 30px;">
				<Page :current="page" :total="total" @on-change="changePage" show-elevator />
			</div>

			</Col>
			<Col span="8">
			<List header="热门资讯" border>
				<ListItem v-for="(item,index) in InfoList1" :key="index">
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

			<img :src="img" width="100%" style="border-radius: 4px;" />
		</Row>
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
				keywords: '',
				page: 1,
				limit: 6,
				total: 0,
				InfoList: [],
				InfoList1: [],
				universityList1: [],
				img: this.$global.fushishan1,
				status: '加载更多',
				source: '',
				year: '2000-2021',
				searchField: 'title',
				sortField: '',
				sortRule: 'desc',
				sourceList: []
			}
		},
		created() {
			this.keywords = this.$route.query.search
			this.getInfoSearch()
			this.getInfoList1()
			this.getUniversityList1()
		},
		methods: {
			changePage(page) {
				this.page = page
				this.getInfoSearch()
			},
			getInfoSearch() {
				if(this.keywords == ''){
					this.$Message.warning('关键字不能为空！！！');
					return;
				}
				this.InfoList = []
				var params = {
					page: this.page,
					limit: this.limit,
					keywords: this.keywords,
					searchField: this.searchField,
					year: this.year,
					source: this.source,
					sortField: this.sortField,
					sortRule: this.sortRule,
				};
				this.$api.getRequest(this.$url.INTERFACES.infoSearch, params).then(res => {
					this.InfoList = res.page.list
					this.total = Number(res.page.total);
					this.sourceList = res.page.source
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

	ol,
	ul,
	li {
		list-style: none;
	}


	.info_row {
		padding-left: 10px;
		max-height: 60px;
		text-overflow: -o-ellipsis-lastline !important;
		overflow: hidden !important;
		text-overflow: ellipsis !important;
		display: -webkit-box !important;
		-webkit-line-clamp: 3 !important;
		-webkit-box-orient: vertical !important;
	}

	.noinfo p {
		text-align: center;
		background-color: #fffaf2;
		border: 1px solid #ffebcc;
		color: #818181;
		height: 38px;
		line-height: 38px;
		margin: 40px 0;
		font-size: 14px;
	}

	.square ul.select {
		color: #333;
	}

	.clearfix {
		*zoom: 1;
	}

	.square ul.select li.select-result dl dt {
		float: left;
		font-size: 28px;
		color: #333;
		padding: 15px 0;
	}

	.square ul.select li.select-result dl dd {
		float: left;
		color: #999;
		font-size: 18px;
		margin-top: 18px;
		padding: 3px 15px;
	}

	.square ul.select li.select-search {
		padding: 10px;
		padding-left: 150px;
		font-size: 14px;
		border: 1px solid #e0e0e0;
		height: 53px;
	}

	.square ul.select li.select-search dl {
		position: relative;
	}

	.square ul.select li.select-search dl dt {
		position: absolute;
		left: -124px;
		height: 29px;
		line-height: 29px;
		width: 104px;
		text-align: right;
	}

	.square ul.select li.select-search dl dd {
		float: left;
		display: inline;
		position: relative;
	}

	.square ul.select li.select-search dl dd .input-search {
		height: 29px;
		line-height: 31px;
		width: 260px;
		border: 1px solid #e0e0e0;
		padding-left: 10px;
	}

	.square ul.select li.select-search dl dd button {
		width: 44px;
		height: 30px;
		background-color: #4a90e2;
		border-radius: 5px;
		border: 0;
		vertical-align: bottom;
		background-image: url(../../assets/search.png);
		background-repeat: no-repeat;
		background-position: center center;
		cursor: pointer;
		margin-left: 5px;
	}

	.square ul.select li.select-list {
		padding: 12px;
		padding-left: 150px;
		font-size: 14px;
		border: 1px solid #e0e0e0;
		border-top: 0;
		height: 53px;
	}

	.square ul.select li.select-list dl {
		position: relative;
		width: 1000px;
	}

	.square ul.select li.select-list dl dt {
		position: absolute;
		left: -126px;
		width: 106px;
		text-align: right;
	}

	.square ul.select li.select-list dl dd {
		float: left;
		display: inline;
		margin-right: 30px;
		height: 33px;
		cursor: pointer;
	}
</style>
