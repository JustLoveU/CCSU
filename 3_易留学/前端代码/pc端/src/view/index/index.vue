<template>
	<div class="content">
		<Carousel autoplay v-model="value2" loop :autoplay-speed="2500">
			<CarouselItem v-for="(item,index) in InfoList" :key="index">
				<!-- <img :src="item" width="100%" height="500px"> -->
				<a :href="'/info_detail?id='+item.id" target="_blank">
				<div :style="' width: 100%; height:500px; display: inline-block;background-image: url('+src1[index]+');background-size: 100% 100%;'">
					<p style="padding: 8px; color: #FFFFFF;font-size: 22px;padding-top: 80px;">{{item.title}}</p>
				</div>
				</a>
			</CarouselItem>
		</Carousel>
		<Row :gutter="16" style="margin-top: 20px;">
			<Col span="16">
			<card>
				<p slot="title">
					<Icon type="md-school" />
					热门大学
				</p>

				<a href="#" slot="extra" @click.prevent="changeLimit">
					<Icon type="md-more" />
					more>>
				</a>
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
			</card>
			</Col>
			<Col span="8">
			<card>
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
		<card style="margin-top: 20px;">
			<p>广告招商：400-000-000-000</p>
		</card>
		<card style="margin-top: 20px;">
			<p slot="title">
				<Icon type="md-school" />
				热门大学Top3
			</p>
			<a href="#" slot="extra" @click.prevent="changeLimit">
				<Icon type="md-more" />
				more>>
			</a>
			<Row :gutter="16">
				<Col span="8" v-for="(item,index) in universityList1" :key="index">
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
		</card>
		<div style="margin: 10px 0;">
			<img :src="img" width="100%" style="border-radius: 4px;" />
		</div>
		<card style="margin-top: 20px;padding: 0  20px;">
			<p slot="title">
				<Icon type="md-school" />
				热门资讯
			</p>
			<a href="#" slot="extra" @click.prevent="changeLimit">
				<Icon type="md-more" />
				more>>
			</a>
			<List item-layout="vertical">
				<Card>
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
							<img :src="src1[index]" style="width: 280px;border-radius: 4px;">
						</template>
					</ListItem>
				</Card>
			</List>
		</card>
	</div>
</template>

<script>
	export default {
		data() {
			return {
				value2: 0,
				img: this.$url.HOUSEINTERFACES.root + 'img/yinghua.png',
				bottom: this.$global.bottom,
				inner: [
					'/inner/t1.png',
					'/inner/t2.png',
					'/inner/t3.png',
					'/inner/t4.png'
				],
				universityList2: [],
				universityList1: [],
				InfoList: [],
				src1: this.$global.src1,
				data: [{
						title: 'This is title 1',
						description: 'This is description, this is description, this is description.',
						avatar: 'https://dev-file.iviewui.com/userinfoPDvn9gKWYihR24SpgC319vXY8qniCqj4/avatar',
						content: 'This is the content, this is the content, this is the content, this is the content.'
					},
					{
						title: 'This is title 2',
						description: 'This is description, this is description, this is description.',
						avatar: 'https://dev-file.iviewui.com/userinfoPDvn9gKWYihR24SpgC319vXY8qniCqj4/avatar',
						content: 'This is the content, this is the content, this is the content, this is the content.'
					},
					{
						title: 'This is title 3',
						description: 'This is description, this is description, this is description.',
						avatar: 'https://dev-file.iviewui.com/userinfoPDvn9gKWYihR24SpgC319vXY8qniCqj4/avatar',
						content: 'This is the content, this is the content, this is the content, this is the content.'
					}
				]
			}
		},
		created() {
			this.getUniversityList()
			this.getInfoList()
			this.getUniversityList1()
		},
		methods: {
			getUniversityList() {
				this.$api.postRequest(this.$url.INTERFACES.universityList2).then(res => {
					if (res.code == 200) {
						this.universityList2 = res.list
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
		}
	}
</script>

<style>
</style>
