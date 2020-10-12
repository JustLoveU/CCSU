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
					
					
					<Page :current="page" :total="total" simple @on-change="change" @on-page-size-change="change" style="margin: 30px;"/>
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
				img: this.$url.HOUSEINTERFACES.root + 'img/yinghua.png',
				universityList2: []
			}
		},
		created() {
			this.getUniversityList()
		},
		methods: {
			getUniversityList() {
				var data = {
					page: this.page,
					limit: '20'
				}
				this.$api.getRequest(this.$url.INTERFACES.universityPage, data).then(res => {
					if (res.code == 0) {
						this.total = res.page.totalCount;
						this.universityList2 = res.page.list
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
