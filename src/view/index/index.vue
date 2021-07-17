<template>
	<div class="content">
		<Row>
			<Col span="7">
			<div class="index-apply-box" v-if="username != null">
				<aside class="status-logined">
					<div class="title">hi，{{username}} 童鞋，</div>
					<section class="form-group content1"><span class="color-fd9b27">易留学</span>已帮小伙伴们申请到了<span>110,352</span>个offer咯
					</section>

					<section class="form-group tips">想要知道你适合申请哪些国家和学历吗？</section>

					<section class="form-group" style="margin-bottom: 40px;">
						<a class="btn" href="" onclick="log('send','click', 'DIY', 'Go_Step1', '');">免费获取留学方案</a>
					</section>

					<div class="bottom-news2">
						关注<span class="color-fd9b27">易留学</span>微信服务号，领取留学大礼包！
						<div class="weixin-box">
							<div class="weixin-title">扫一扫关注<span class="color-fd9b27">易留学</span>微信服务号</div>
							<img src="../../assets/weixin-qr.png">
							<p class="weixin-little-title">关注<span class="color-fd9b27">易留学</span>微信服务号，你可以获得：</p>
							<p>签证宝典</p>
							<p>奖学金申请宝典</p>
						</div>
					</div>
				</aside>
			</div>
			<div v-else>
				<div class="left-section">
					<div class="little-title">
						<div class="l-shu">&nbsp;</div>
						<div class="title">易留学服务流程</div>
					</div>

					<div class="left-section-content">
						<a class="section">
							<i class="icon1"></i>
							<div>免费获取留学方案</div>
						</a>
						<div class="arrow" style="margin-left: 100px;">&nbsp;</div>
						<a class="section" style="padding-left: 80px;">
							<i class="icon2"></i>
							<div>专业顾问1对1服务</div>
						</a>
						<div class="arrow" style="margin-left: 180px;">&nbsp;</div>
						<a class="section" style="padding-left: 160px;">
							<i class="icon3"></i>
							<div>提交申请获得offer</div>
						</a>
					</div>
				</div>
			</div>
			</Col>
			<Col span="17">
			<Carousel autoplay v-model="value2" loop :autoplay-speed="2500">
				<CarouselItem v-for="(item,index) in InfoList" :key="index">
					<!-- <img :src="item" width="100%" height="500px"> -->
					<a :href="'/info_detail?id='+item.id" target="_blank">
						<div :style="' width: 100%; height:400px; display: inline-block;background-image: url('+item.pic+');background-size: 100% 100%;'">
							<p style="padding: 8px; color: #FFFFFF;font-size: 22px;padding-top: 80px;">{{item.title}}</p>
						</div>
					</a>
				</CarouselItem>
			</Carousel>
			</Col>
		</Row>
		<Row :gutter="16" style="margin-top: 20px;">
			<Col span="16">

			<p class="box-title">
				<span class="line"></span>
				<Icon type="md-school" />
				热门大学
				<a href="#" class=" more" @click.prevent="changeLimit">
					<Icon type="md-more" />
					more>>
				</a>
			</p>

			<Row :gutter="16">
				<Col span="8" v-for="(item,index) in universityList2" :key="index" style="padding-top: 20px;">
				<!-- <img :src="item.pic" width="80%" height="300px"/> -->
				<a :href="'/university_detail?id=' + item.id" target="_blank">
					<div :style="'margin:10px; width: 80%; height:200px; display: inline-block;background-image: url('+item.pic+');background-size: 100% 100%;border-radius:6px'">
						<p style="padding: 8px; color: #FFFFFF;font-size: 22px;">{{item.name}}</p>
					</div>
				</a>
				<div style="padding: 5px 30px">
					<span style="float: left;">{{item.location}}</span>
					<span style="float: right;"><a :href="item.url" target="_blank">访问官网</a></span></div>
				</Col>
			</Row>
			</Col>
			<Col span="8">
			<card :dis-hover="true">
				<p slot="title">
					<Icon type="md-school" />
					关于
				</p>
				<p style="padding: 10px;line-height: 1.625rem;">留学日本在文化、地理、安全、安心等方面占有绝对优势，并且日本也成为大多数留学生青睐的对象。但是传统留学中介行业特别是针对日本的留学行业无论是从模式还是从质量上都已经跟不上时代的步伐，留学市场乱象丛生。由此互联网+留学平台应运而生。而本团队将一致专注于打造互联网去中介化智能留学交流服务平台，智能精准推荐留学高校及专业，营造一个纯净的O20留学服务生态。
				</p>
				<img :src="bottom" width="100%" />
			</card>
			</Col>
		</Row>
<!-- 		<card style="margin-top: 20px;" :bordered="false">
			<p>广告招商：400-000-000-000</p>
		</card> -->
		<p class="box-title">
			<span class="line"></span>
			<Icon type="md-school" />
			热门大学Top3
			<a href="#" class=" more" @click.prevent="changeLimit">
				<Icon type="md-more" />
				more>>
			</a>
		</p>
		<Row :gutter="16">
			<Col span="8" v-for="(item,index) in universityList1" :key="index">
			<a :href="'/university_detail?id=' + item.id" target="_blank">
				<Card style="margin-top: 20px;">
					<Row :gutter="16">
						<Col span="6"><img :src="item.img" width="100px" height="100px"></Col>
						<Col span="18">
						<div style="font-size: 22px;line-height: 28px;">{{item.name}}</div>
						<div style="padding: 20px;">
							<span style="float: left;">
								<Icon type="md-navigate" />{{item.location}}
							</span>
							<span><a :href="item.url" target="_blank">
									<Icon type="ios-link" />访问官网</a>
							</span>
						</div>
						</Col>
					</Row>
				</Card>
			</a>
			</Col>
		</Row>
		<div style="margin: 10px 0;">
			<img :src="img" width="100%" style="border-radius: 4px;" />
		</div>
		<p class="box-title">
			<span class="line"></span>
			<Icon type="md-school" />
			热门资讯
			<a href="#" class=" more" @click.prevent="changeLimit">
				<Icon type="md-more" />
				more>>
			</a>
		</p>
		<List item-layout="vertical">
			<Card :bordered="false" :dis-hover="true">
				<ListItem v-for="(item,index) in InfoList" :key="index">
					<router-link :to="{ path: '/info_detail', query: { id: item.id}}">
						<ListItemMeta :title="item.title" :description="item.description" />
					</router-link>
					<template slot="action">
						<li>
							<Icon type="md-time" /> {{item.time}}
						</li>
						<li>
							<Icon type="ios-attach" /> {{item.source}}
						</li>
						<li>
							<Icon type="ios-eye-outline" /> {{item.view}}
						</li>
						<!-- 	<li>
									<Icon type="md-link" /> <a :href="item.url" target="_blank">查看原网</a>
								</li> -->
					</template>
					<template slot="extra">
						<img :src="item.pic" style="width: 280px;border-radius: 4px;">
					</template>
				</ListItem>
			</Card>
		</List>
		<div style="padding-top: 40px;">
			<p class="box-title">
				<span class="line"></span>
				<Icon type="md-school" />
				我们的优势
				<a href="#" class=" more" @click.prevent="">
					<Icon type="md-more" />
					关于我们
				</a>
			</p>
			<ul class="commitment" style="padding-top: 20px;">
				<li class="li2">
					<i></i>
					<p>1200所合作院校</p>
				</li>
				<li class="li3">
					<i></i>
					<p>专属顾问贴身专业服务</p>
				</li>
				<li class="li4">
					<i></i>
					<p>最多一次申请10所学校</p>
				</li>
				<li class="li5">
					<i></i>
					<p>随时随地掌握申请进度</p>
				</li>
				<li class="li6">
					<i></i>
					<p>各种增值服务任你挑选</p>
				</li>
			</ul>
		</div>
	</div>
</template>

<script>
	export default {
		data() {
			return {
				username: '',
				value2: 0,
				img: this.$global.yinghua,
				bottom: this.$global.bottom,
				universityList2: [],
				universityList1: [],
				InfoList: [],
				src1: this.$global.src1,
			}
		},
		created() {
			this.username = localStorage.getItem("username");
			console.log(this.username)
			this.getUniversityList()
			this.getInfoList()
			this.getUniversityList1()
		},
		methods: {
			getUniversityList() {
				this.$api.getRequest(this.$url.INTERFACES.universityList2).then(res => {
					if (res.status == 200) {
						console.log(res.message)
						this.universityList2 = res.list
					}
				})
			},
			getInfoList() {
				this.$api.getRequest(this.$url.INTERFACES.infoList).then(res => {
					if (res.status == 200) {
						this.InfoList = res.list
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
	.index-apply-box {
		float: left;
		width: 330px;
		height: 437px;
		margin-right: 20px;
		text-align: left
	}

	.color-fd9b27 {
		color: #fd9b27;
	}

	.status-logined {
		position: relative;
		padding: 70px 20px 0;
		min-height: 366px
	}

	.status-logined .title {
		font-size: 18px;
		line-height: 20px;
		color: #fd9b27;
		margin-bottom: 20px
	}

	.status-logined .form-group.content1 {
		margin-bottom: 35px;
		color: #818181;
		font-size: 18px;
		min-height: 61px
	}

	.status-logined .form-group.content1 span {
		color: #fd9b27
	}

	.status-logined .form-group.tips {
		font-size: 14px;
		color: #818181;
		margin-bottom: 65px
	}

	.status-logined .btn {
		border-radius: 4px;
		background-color: #ff9c00;
		color: #fff;
		font-size: 16px;
		font-weight: 700;
		height: 42px;
		line-height: 42px;
		padding: 15px 65px;
		text-align: center;
		width: 300px;
		margin-bottom: 35px
	}

	.status-logined .btn:hover {
		transition: color .2s ease;
		background-color: #f78400
	}

	.status-logined .bottom-news2 {
		border-top: 1px solid #f4f4f4;
		width: 268px;
		padding: 8px 10px;
		color: #ffb700;
		font-size: 12px;
		text-align: center;
		cursor: pointer
	}

	.status-logined .bottom-news2 .weixin-box {
		display: none;
		position: absolute;
		top: 23px;
		left: 0;
		width: 326px;
		height: 324px;
		z-index: 10;
		background-color: #fff;
		padding-top: 50px;
		color: #818181;
		cursor: auto
	}

	.status-logined .bottom-news2 .weixin-box .weixin-title {
		font-size: 18px;
		text-align: center
	}

	.status-logined .bottom-news2 .weixin-box img {
		display: block;
		vertical-align: top;
		margin: 15px auto;
		width: 135px;
		height: 135px
	}

	.status-logined .bottom-news2 .weixin-box p {
		text-indent: 52px;
		font-size: 12px;
		line-height: 22px;
		text-align: left
	}

	.status-logined .bottom-news2 .weixin-box p.weixin-little-title {
		font-size: 14px;
		margin-bottom: 12px
	}

	.status-logined .bottom-news2:hover .weixin-box {
		display: block
	}

	.status-logined .visa-activity-section {
		font-size: 14px
	}

	.status-logined .visa-activity-section .form-group {
		margin-bottom: 15px
	}

	.status-logined .visa-activity-section .title {
		color: #fdb427
	}

	.status-logined .visa-activity-section .content {
		font-size: 14px;
		color: #888;
		min-height: 42px
	}

	.status-logined .visa-activity-section .tips {
		color: #fdb427;
		text-align: center
	}

	.status-logined .visa-activity-section .visa-time {
		font-size: 16px;
		color: #75ced6;
		text-align: center
	}

	.status-logined .visa-activity-section .m-num {
		width: 48px;
		height: 45px;
		display: inline-block;
		background-color: #75ced6;
		color: #fff;
		font-size: 20px;
		line-height: 50px;
		text-align: center;
		border-radius: 4px
	}

	.status-logined .visa-activity-section .m-text {
		display: inline-block;
		font-size: 16px;
		color: #75ced6;
		margin: 0 3px
	}

	.status-logined .visa-activity-section .visa-lasthint {
		font-size: 12px;
		color: #888;
		text-align: center;
		margin-bottom: 25px
	}

	.status-logined .visa-activity-section .btn {
		margin-bottom: 0
	}





	.left-section {
		/* float: left; */
		text-align: center;
		width: 537px;
		height: 100%;
		border-right: 1px solid #eaeaea
	}

	.left-section .left-section-content {
		*zoom: 1
	}

	.left-section .left-section-content:before,
	.left-section .left-section-content:after {
		display: table;
		content: "";
		clear: both
	}

	.left-section .left-section-content .section {
		padding: 0 15px;
		/* float: left; */
		display: block;
		*zoom: 1
	}

	.left-section .left-section-content .section:before,
	.left-section .left-section-content .section:after {
		display: table;
		content: "";
		clear: both
	}

	.left-section .left-section-content .section i {
		display: block;
		width: 58px;
		height: 58px;
		float: left;
		margin-right: 8px;
		background: url(../../assets/index.png) no-repeat
	}

	.left-section .left-section-content .section i.icon1 {
		background-position: -80px 0
	}

	.left-section .left-section-content .section i.icon2 {
		background-position: -150px 0
	}

	.left-section .left-section-content .section i.icon3 {
		background-position: -220px 0
	}

	.left-section .left-section-content .section div {
		float: left;
		width: 68px;
		font-size: 16px;
		color: #676767
	}

	.left-section .left-section-content .arrow {
		float: left;
		width: 22px;
		height: 66px;
		background: url(../../assets/index.png) no-repeat;
		background-position: -304px 0
	}

	.little-title {
		margin: 14px 20px 12px 20px;
		color: #818181;
		font-size: 16px;
		line-height: 16px;
	}

	.little-title .l-shu {
		border-left: 2px solid #3a9bfc;
		height: 18px;
		margin-right: 15px;
		float: left;
		margin-bottom: 20px;
	}

	.little-title .title {
		float: left;
		margin-right: 20px;
		margin-bottom: 20px;
	}



	.commitment {
		width: 921px;
		margin: 0 auto;
		padding-bottom: 35px;
		color: #666;
		overflow: auto;
		zoom: 1;
		font-size: 12px;
		display: flex;
		justify-content: space-between
	}

	.commitment li {
		text-align: center;
		float: left;
		border-right: 1px dashed #dbdbdb;
		width: 100%
	}

	.commitment li i {
		display: block;
		width: 51px;
		height: 50px;
		margin: 0 auto;
		background: url(../../assets/global-info.png) no-repeat
	}

	.commitment .li6 {
		border-right: 0
	}

	.commitment .li1 i {
		background-position: 0 0
	}

	.commitment .li2 i {
		background-position: -51px 0
	}

	.commitment .li3 i {
		background-position: -102px 0
	}

	.commitment .li4 i {
		background-position: -153px 0
	}

	.commitment .li5 i {
		background-position: -204px 0
	}

	.commitment .li6 i {
		background-position: -255px 0
	}
</style>
