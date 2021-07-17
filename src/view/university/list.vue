<template>
	<div>
		<img :src="img" width="100%" />
		<div class="content" style="top: -100px;position: relative;margin: 0 100px;">
			<Row :gutter="16" style="margin-top: 20px;">
				<Col span="24">
				<card>
					<Row :gutter="16">
						<Col span="6" v-for="(item,index) in universityList2" :key="index" style="padding-top: 20px;">
						<!-- <img :src="item.pic" width="80%" height="300px"/> -->
						<a :href="'/university_detail?id=' + item.id" target="_blank">
							<div :style="'margin:10px; width: 80%; height:200px; display: inline-block;background-image: url('+item.pic+');background-size: 100% 100%;border-radius:6px'">
								<p style="padding: 8px; color: #FFFFFF;font-size: 22px;">{{item.name}}</p>
							</div>
						</a>
						<div style="padding: 5px 30px">
							<span style="float: left;" class="address" :title="item.location">{{item.location}}</span>
							<span style="float: right;"><a :href="item.url" target="_blank" :title="item.url">访问官网</a></span></div>
						</Col>
					</Row>
					
					<div style="text-align: center;margin-top: 40px;">
						<a href="/university_index" target="_blank"><Button type="primary" ghost>查看更多学校>></Button></a>
					</div>
				</card>
				</Col>
			</Row>
		</div>
	</div>
</template>

<script>
	export default {
		data() {
			return {
				page: 1,
				total: 0,
				search : '',
				img: this.$url.HOUSEINTERFACES.root + 'img/yinghua.png',
				universityList2: []
			}
		},
		created() {
			this.search = this.$route.query.search
			this.getUniversityList()
		},
		methods: {
			getUniversityList() {
				var data = {
					search: this.search
				}
				this.$api.getRequest(this.$url.INTERFACES.universitySearch1, data).then(res => {
					if (res.code == 0) {
						this.universityList2 = res.list
					}
				})
			},
			change(data) {
				this.page = data
				this.getUniversityList()
			}
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
</style>
