<template>
	<div class="university_page">
		<img :src="img" width="100%" />
		<div class="content">
			<div class="content" style="top: -200px;position: relative;margin: 0 100px;padding: 20px;background-color: #FFFFFF;border-radius: 6px;">
				<Row :gutter="16" style="background-color: #f7f8f9;padding: 20px;border-radius: 6px;">
					<Col span="4">
					<img :src="university.img" width="80%">
					<Tag checkable color="primary">+关注</Tag>
					</Col>
					<Col span="20" style="text-align: left;padding-left: 40px;">
					<h1 style="line-height: 70px;font-size: 40px;">{{university.name}} <a :href="university.url" target="_blank">
							<Tag type="border" color="primary">官网</Tag>
						</a></h1>
					<p style="line-height: 40px;">所在城市：{{university.location}}</p>
					<Rate allow-half disabled v-model="university.star" />
					</Col>
				</Row>

				<Row :gutter="16" style="padding-top: 40px;">
					<Col span="16">
					<div style="text-align: left;">
						<p class="box-title" style="font-size: 24px;border-bottom: 1px solid #e2e8ee;line-height: 44px;margin-bottom: 7px;   color: #333;">
							<Icon type="md-school" />
							热门大学
						</p>
						<Row :gutter="16">
							<Col span="12">
							<p class="intro_content"><span style="color: #999999;">创办时间：</span>{{university.setData}}</p>
							</Col>

							<Col span="12">
							<p class="intro_content"><span style="color: #999999;">学费：</span>{{university.tuition}}</p>
							</Col>

							<Col span="12">
							<p class="intro_content"><span style="color: #999999;">坐标：</span>{{university.location}}</p>
							</Col>
						</Row>
						<p class="box-title1">
							<span class="line"></span>
							简介
						</p>
						<p class="p_content">{{university.intro}}</p>
						<p class="box-title1">
							<span class="line"></span>
							地理位置
						</p>
						<p class="p_content">{{university.location}}</p>
						<p class="box-title1">
							<span class="line"></span>
							专业
						</p>
						<p class="p_content">暂无</p>
						<p class="box-title1">
							<span class="line"></span>
							联系
						</p>

						<p class="p_content">
							<Icon type="ios-school" size="20" />
							{{university.name}}
						</p>
						<p class="p_content">
							<Icon type="ios-call" size="20" />
							{{university.tel}}
						</p>
						<p class="p_content">
							<Icon type="md-link" size="20" />
							{{university.url}}
						</p>
					</div>

					</Col>
					<Col span="8">
					<List header="热门资讯" border>
						<ListItem v-for="(item,index) in InfoList" :key="index">
							<!-- <router-link :to="{ path: '/detail', query: { id: item.id}}"></router-link> -->
							<a :href="'/info_detail?id='+item.id" class="row">{{item.title}}</a>
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
										<span style="padding-left: 20px;"><a :href="item.url">
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

				<div class="box">
					<h1 style="font-size: 24px;border-bottom: 1px solid #e2e8ee;line-height: 44px;margin-bottom: 7px;">学校资讯：</h1>
					<Row :gutter="16">
						<Col span="8" v-for="(item,index) in InfoListAboutUniversity" :key="index">
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
					<div v-if="InfoListAboutUniversity.length == 0" class="noinfo">
						<p>没有该院校的相关资讯！</p>
					</div>
					<div style="text-align: center;margin-top: 40px;">
						<a href="/info_index" target="_blank"><Button type="primary" ghost>查看更多学校资讯>></Button></a>
					</div>
				</div>
				</Row>
			</div>
		</div>
	</div>
</template>

<script>
	export default {
		data() {
			return {
				id: '',
				img: this.$global.yinghua,
				src1: this.$global.src1,
				university: {},
				InfoList: [],
				InfoListAboutUniversity: [],
				universityList1: [],
			}
		},
		created() {
			this.id = this.$route.query.id
			this.getUniversity()
			this.getInfoListAboutUniversity()
			this.getInfoList()
			this.getUniversityList1()

		},
		methods: {
			getUniversity() {
				this.$api.getRequest(this.$url.INTERFACES.universityOne + this.id).then(res => {
					if (res.status == 200) {
						this.university = res.university
					}
				})
			},
			getInfoListAboutUniversity() {
				this.$api.getRequest(this.$url.INTERFACES.InfoListByUniversity + this.id).then(res => {
					this.InfoListAboutUniversity = res.list
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

<!-- id: 1
img: "https://upload.wikimedia.org/wikipedia/commons/thumb/f/f0/UnivOfTokyo_mark.svg/200px-UnivOfTokyo_mark.svg.png"
infoTypeId: null
intro: "东京大学创办于1877年，是日本第一所国立综合性大学。作为领先的研究型大学，东京大学提供的课程包括本科生和研究生的所有学科，进行的科研涵盖学术研究的方方面面。东京大学致力于为它的莘莘学子提供丰富而多样的学术氛围，为他们开发智力和习得专业知识技术提供机会。想知道更多关于东京大学，请访问下面的网页。"
location: "东京都文京区"
name: "东京大学"
pic: "https://bkimg.cdn.bcebos.com/pic/7e3e6709c93d70cf5b02c01cf8dcd100baa12b22"
setData: "1877"
star: 5
tel: null
tuition: "11000"
url: null
view: 12 -->
<style>
	.university_page {
		background-color: #FFFFFF;
	}

	.intro_content {
		font-size: 18px;
		line-height: 30px;
		padding-top: 10px;
	}

	.box {
		text-align: left;
		margin-top: 40px;
	}

	.p_content {
		font-size: 16px;
		line-height: 26px;
	}
</style>
