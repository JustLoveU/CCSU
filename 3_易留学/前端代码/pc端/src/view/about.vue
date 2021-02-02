<template>
	<div>
		<div>
			<img :src="img" width="100%" style="border-radius: 4px;" />
			<div style="top: -40px;position: relative;margin: 0 100px;">
				<Card>
					<p>本团队将一致专注于打造互联网去中介化智能留学交流服务平台，智能精准推荐留学高校及专业，营造一个纯净的O20留学服务生态。</p>
				</Card>
				<div class="content">
					<div style="margin-top: 20px;">
						<p style="text-align: left;padding-bottom: 20px;line-height: 20px;font-size: 18px;">
							<span class="line"></span>
							我的评论
						</p>
					</div>
					<Card>
						<p style="float: center;" v-if="commentTotal==0">啥也没有哦</p>
					</Card>
					<div style="margin-top: 20px;">
						<p style="text-align: left;padding-bottom: 20px;line-height: 20px;font-size: 18px;">
							<span class="line"></span>
							我的收藏
						</p>
					</div>
					<Card>
						<Row :gutter="16" style="margin-top: 20px;">
							<Col span="24">
							<Row :gutter="16">
								<Col span="6" v-for="(item,index) in universityList" :key="index" style="padding-top: 20px;">
								<!-- <img :src="item.pic" width="80%" height="300px"/> -->
								<a :href="'/university_detail?id=' + item.id" target="_blank">
									<div :style="'margin:10px; width: 60px; height:60px; display: inline-block;background-image: url('+item.img+');background-size: 100% 100%;border-radius:6px'">
									</div>
								</a>
								<div style="padding: 5px 30px">
									<span style="float: center;" class="address" :title="item.name">{{item.name}}</span>
									<!-- <span style="float: right;"><a :href="item.url" target="_blank" :title="item.url">访问官网</a></span> -->
								</div>
								</Col>
							</Row>
							</Col>
						</Row>
					</Card>
					<div style="margin-top: 20px;">
						<p style="text-align: left;padding-bottom: 20px;line-height: 20px;font-size: 18px;">
							<span class="line"></span>
							我的浏览记录
						</p>
					</div>
					<Card>
						<p style="float: center;" v-if="commentTotal==0">啥也没有哦</p></Card>
					<div style="margin-top: 20px;">
						<p style="text-align: left;padding-bottom: 20px;line-height: 20px;font-size: 18px;">
							<span class="line"></span>
							我的申请
						</p>
					</div>
					<Card>
						<p style="float: center;" v-if="commentTotal==0">啥也没有哦</p></Card>
				</div>
			</div>
		</div>
	</div>
</template>

<script>
	export default {
		data() {
			return {
				img: this.$url.HOUSEINTERFACES.root + 'img/fushishan1.png',
				data: {
					page: 1,
					limit: 3
				},
				id: '',
				universityList: [],
				universityTotal: '',
				commentList: [],
				commentTotal: ''
			}
		},
		created() {
			this.id = localStorage.getItem("id");
			this.getCommentList();
			this.getCollectionList();
		},
		methods: {
			getCollectionList() {
				this.$api.getRequest(this.$url.INTERFACES.universityListByUser + this.id, this.data).then(res => {
					this.universityList = res.page.records
					this.universityTotal = res.page.total
				})
			},
			getCommentList() {
				this.$api.getRequest(this.$url.INTERFACES.commentListByUser + this.id, this.data).then(res => {
					this.commentList = res.page.records
					this.commentTotal = res.page.total
				})
			}
		}
	}
</script>

<style>
</style>
