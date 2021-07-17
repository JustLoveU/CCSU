<template>
	<div class="content" style="padding-top: 20px;">
		<Row>
			<div class="square">
				<ul class="select">
					<Row>
						<Col span="24">
						<li class="select-result">
							<dl>
								<dt>院校广场</dt>
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
									<input class="input-search" placeholder="请输入内容" v-model="keywords" ></input>
									<button class="btn-search" @click="getUniversitySearch"></button>
									<div class="result-lists">
									</div>
								</dd>
							</dl>
						</li>
						<li class="select-list clearfix">
							<dl>
								<dt>成立时间：</dt>
								<dd>
									<RadioGroup v-model="year" @on-change="getUniversitySearch">							<Radio label="1600-1799" border>1600 ~ 1800</Radio>
										<Radio label="1800-1899" border>1800 ~ 1899</Radio>
										<Radio label="1900-1949" border>1900 ~ 1949</Radio>
										<Radio label="1950-1999" border>1950 ~ 1999</Radio>
										<Radio label="2000-2050" border>2000 ~ 至今</Radio>
										<Radio label="1800-2050" border>全部</Radio>
									</RadioGroup>
								</dd>
							</dl>
						</li>
						<li class="select-list clearfix selectCountry">
							<dl>
								<dt>位    置：</dt>
								<dd>
									<RadioGroup v-model="region" type="button" @on-change="getUniversitySearch">
										<Radio v-for="(item,index) in regionList" :key="index" :label="item"></Radio>
										<Radio label="" border>全部</Radio>
									</RadioGroup>
								</dd>
							</dl>
						</li>

						<li class="select-list clearfix">
							<dl>
								<dd>
									<RadioGroup v-model="sortField" @on-change="getUniversitySearch">
										<Radio label="" border>综合
										</Radio>
										<!-- <Radio label="name" border>
											名字
											<Icon type="md-school" />
										</Radio> -->
										<Radio label="collection" border>
											收藏量
											<Icon type="ios-ribbon" />
										</Radio>
										<Radio label="view" border>
											访问量
											<Icon type="md-eye" />
										</Radio>
										<Radio label="star" border>
											评价星级
											<Icon type="ios-star" />
										</Radio>
									</RadioGroup>
								</dd>

								<dd v-if="sortField != ''" style="padding-left: 50px;">
									<RadioGroup v-model="sortRule" @on-change="getUniversitySearch">
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
		<div v-if="total == 0" class="noinfo">
			<p>未搜索到相关院校，请换个条件搜索！</p>
		</div>

		<div class="school-result" v-else>
			<div class="head">
				<a  class="rank active" >访问量</a>
				<a class="rank  active" >收藏量</a>
				<a class="rank  active" >用户评级</a>
				<h3 class="school-num">共 {{total}} 所学校</h3>
			</div>
			<div class="school-info">
				<ul class="addContent">
					<li class="clearfix"  v-for="(item,index) in universityList" :key="index">
						<a class="school-logo" :href="'/university_detail?id=' + item.id" target="_blank">
							<img :src="item.content.img" :alt="item.content.name">
						</a>
						<div class="school-des">
							<h2 class="school-name">
								<a :href="'/university_detail?id=' + item.id" target="_blank" v-html="item.highlightFields.length!=0?item.highlightFields.name[0]:item.content.name"></a>
							</h2>
							<h5 class="school-position">
								<a :href="'/university_detail?id=' + item.id" target="_blank"><Icon type="md-pin" />  {{item.content.location}}</a>
							</h5>
						</div>

						<div class="rank rank-people active">
							<p>{{item.content.view}}</p>
						</div>
						<div class="rank rank-num  active ">
							<p>{{item.content.collection}}</p>
						</div>
						<div class="rank rank-num  active ">
							<p>{{item.content.star}}</p>
						</div>
						<div class="school-chat">
							<a href="javascript:void(0);" class="school-chat-btn">咨询院校申请条件</a>
						</div>
					</li>
				</ul>
			</div>
			
			<div style="text-align: center;margin: 30px;">
				<Page :current="page" :total="total" @on-change="changePage" show-elevator />
			</div>

		</div>
		<img :src="img" width="100%" />
	</div>
</template>

<script>
	export default {
		data() {
			return {
				keywords: '',
				page: 1,
				limit: 6,
				total: 0,
				year: '1800-2050',
				region: [],
				sortField: '',
				sortRule: 'desc',
				img: this.$global.yinghua,
				universityList: [],
				regionList: [
					'北海道',
					'东北',
					'关东',
					'中部',
					'近畿',
					'中国',
					'四国',
					'九州',
					'冲绳'
				]
			}
		},
		created() {
			this.keywords = this.$route.query.search
			this.getUniversitySearch()
		},
		methods: {
			changePage(page) {
				this.page = page
				this.getUniversitySearch()
			},
			getUniversitySearch() {
				if (this.keywords == '') {
					this.$Message.warning('关键字不能为空！！！');
					return;
				}
				this.universityList = []
				var params = {
					page: this.page,
					limit: this.limit,
					keywords: this.keywords,
					year: this.year,
					region: this.region,
					sortField: this.sortField,
					sortRule: this.sortRule,
				};
				this.$api.getRequest(this.$url.INTERFACES.universitySearch, params).then(res => {
					this.universityList = res.page.list
					this.total = Number(res.page.total);
					this.sourceList = res.page.source
				})
			},
		}
	}
</script>

<style>
	.address {
		overflow: hidden;
		text-overflow: ellipsis;
		white-space: nowrap;
		width: 50%;
	}


	.school-result {
		margin-top: 36px;
		margin-bottom: 100px;
		text-align: left;
	}

	.school-result .head {
		border-bottom: 1px solid #ddd;
		padding-bottom: 10px
	}

	.school-result .head h3 {
		font-size: 18px;
		color: #333
	}

	.school-result .head a {
		float: right;
		font-size: 16px;
		width: 100px;
		color: #333;
		text-align: center;
		cursor: pointer
	}

	.school-result .head a.rank.active {
		color: #ff7c00
	}

	.school-info li {
		width: 100%;
		height: 120px;
		padding: 20px 0;
		border-bottom: 1px solid #eee
	}

	.school-info li div {
		float: left
	}

	.school-info li .school-logo {
		margin-left: 50px;
		float: left;
		width: 88px;
		height: 88px;
		padding: 4px;
		margin-right: 20px;
		border: 1px solid #e0e0e0;
		overflow: hidden
	}

	.school-info li .school-logo img {
		width: 80px;
		height: 80px;
		margin-left: -1px;
		margin-top: -1px
	}

	.school-info li .school-des a {
		line-height: 1.5em
	}

	.school-info li .school-des .school-name a {
		color: #4a90e2;
		font-size: 16px;
		line-height:60px;
	}

	.school-info li .school-des .school-position a {
		color: #999;
		font-size: 14px;
		margin-top: 5px;
	}

	.school-info li .school-chat {
		float: right;
		padding-top: 13px;
		margin-right: 20px
	}

	.school-info li .school-chat-btn {
		background-color: #5b90de;
		color: #fff;
		font-size: 12px;
		text-align: center;
		height: 26px;
		line-height: 26px;
		border: 0;
		padding: 0 8px;
		border-radius: 5px;
		cursor: pointer;
		display: block
	}

	.school-info li .rank {
		float: right;
		width: 100px;
		text-align: center
	}

	.school-info li .rank p {
		font-size: 26px;
		height: 50px;
		line-height: 50px;
		color: #999
	}

	.school-info li .rank.active p {
		color: #ff7c00
	}

	.noschool p {
		text-align: center;
		background-color: #fffaf2;
		border: 1px solid #ffebcc;
		color: #818181;
		height: 38px;
		line-height: 38px;
		margin: 40px 0;
		font-size: 14px
	}
</style>
