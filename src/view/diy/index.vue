<template>
	<div class="diy_page">
		<div class="content" style="background-color: #2b4266;">
			<div style="width: 60%;height:450px;margin:0 20%; background: url(https://s2.51offer.com/51offer/2016-12-21/bg1.png) no-repeat center 0;background-size: 100% 100%;">
				<h1 style="font-size: 48px;margin-top: 40px;">想要<span>好的</span>申请结果，先做<span>聪明</span>的选择。</h1>
				<p style="font-size: 24px;">选择我们，让留学不再是一件<span id="span">难事</span></p>
				<Row :gutter="16" style="margin: 40px 0;">
					<Col span="8">
					<i-circle :percent="96" :size="200">
						<span style="font-size:24px">96%</span>
					</i-circle>
					<p style="margin: 20px 0;font-size: 20px;">至少拿到一个offer</p>
					</Col>
					<Col span="8">
					<i-circle :percent="90" :size="200">
						<span style="font-size:24px">90%</span>
					</i-circle>
					<p style="margin: 20px 0;font-size: 20px;">拿到Dream school offer</p>
					</Col>
					<Col span="8">
					<i-circle :percent="100" :size="200">
						<span style="font-size:24px">100%</span>
					</i-circle>
					<p style="margin: 20px 0;font-size: 20px;">获得个性化定制</p>
					</Col>
				</Row>
			</div>

			<a class="diy_button" @click="diy">个性化定制</a>
			<div style="margin: 40px 100px;color: #000000;">
				<Card style="padding: 10px 30px;">
					<img :src="bottom" width="50%" />
					<Table :columns="columns1" :data="data1"></Table>
					<div style="margin: 40px 0;">
						<h1 style="font-size: 46px;">我们的优势</h1>
						<Row :gutter="16" style="margin: 40px 0;">
							<Col span="6">
							<div class="inner">
								<Icon type="md-flame" :size="50" class="icon" />
								<p class="chinese">多个战略合作伙伴</p>
							</div>
							</Col>
							<Col span="6">
							<div class="inner">
								<Icon type="ios-book" :size="50" class="icon" />
								<p class="chinese">大数据分析推荐</p>
							</div>
							</Col>
							<Col span="6">
							<div class="inner">
								<Icon type="md-bookmarks" :size="50" class="icon" />
								<p class="chinese">一站式留学方案的自动定制</p>
							</div>
							</Col>
							<Col span="6">
							<div class="inner">
								<Icon type="ios-bulb" :size="50" class="icon" />
								<p class="chinese">去中介化</p>
							</div>
							</Col>
						</Row>
						<a class="diy_button" @click="diy">开启我的留学之旅</a>
					</div>
				</Card>
			</div>
			<div style="padding: 50px;"></div>
		</div>
		<!-- <Modal v-model="modal" fullscreen title="开启你的DIY生活"> -->
		<Drawer title="开启你的DIY生活" v-model="modal" width="720" :mask-closable="false">
			<Form :model="form" :label-width="80">
				<FormItem label="预选区域">
					<RadioGroup v-model="form.address">
						<Radio :disabled="item.disabled" v-for="(item, index) in addressList" :key="index" :label="item.name"></Radio>
					</RadioGroup>
				</FormItem>
				<FormItem label="计划攻读学位">
					<Select v-model="form.planDegree" style="width:300px">
						<Option v-for="item in planDegreeList" :value="item.text" :key="item.text">{{ item.text }}</Option>
					</Select>
				</FormItem>
				<FormItem label="已学习年限">
					<Select v-model="form.studyTime" style="width:300px">
						<Option v-for="item in studyTimeList" :value="item.text" :key="item.text">{{ item.text }}</Option>
					</Select>
				</FormItem>
				<FormItem label="在读专业">
					<input v-model="form.major" style="width: 300px" />
				</FormItem>
				<FormItem label="出国时间">
					<Select v-model="form.outsideTime" style="width:300px">
						<Option v-for="item in outsideTimeList" :value="item.text" :key="item.text">{{ item.text }}</Option>
					</Select>
				</FormItem>
				<FormItem label="毕业就读院校">
					<input v-model="form.university" style="width: 300px" />
				</FormItem>
				<FormItem label="平时成绩">
					<input v-model="form.score" placeholder="满分5分" style="width: 300px" />
				</FormItem>
				<FormItem label="手机号码">
					<input v-model="form.tel" placeholder="满分5分" style="width: 300px" />
				</FormItem>


				<FormItem>
					<Button type="primary" @click="submit" >智能DIY</Button>
					<Button type="primary" @click="toresult" :disabled="!button">已有定制，去看看</Button>
				</FormItem>
			</Form>

		</Drawer>
		<!-- </Modal> -->

	</div>
</template>

<script>
	export default {
		data() {
			return {
				button: false,
				modal: false,
				bottom: this.$global.bottom,
				addressList: [{
						name: '北海道',
						checked: false,
						disabled: false
					},
					{
						name: '中部',
						checked: false,
						disabled: false
					},
					{
						name: '关西',
						checked: false,
						disabled: false
					},
					{
						name: '九州',
						checked: false,
						disabled: false
					},
					{
						name: '东北',
						checked: false,
						disabled: false
					}
				],
				planDegreeList: [{
					text: '本科'
				}, {
					text: '硕士'
				}, {
					text: '博士'
				}],
				studyTimeList: [{
					text: 1
				}, {
					text: 2
				}],
				outsideTimeList: [{
					text: '尽快'
				}, {
					text: '半年'
				}, {
					text: '两年'
				}, {
					text: '未定'
				}],
				form: {
					// address: [],
					address: '九州',
					planDegree: '硕士',
					studyTime: 2,
					major: '英语翻译',
					outsideTime: '半年',
					university: '清华大学',
					score: '4.9',
					tel: '1234567890'
				},
				columns1: [{
						title: '比较',
						key: 'name',
						className: 'pk'
					},
					{
						title: '我们',
						key: 'us',
						className: 'us',
						width: 600
					},
					{
						title: '传统中介',
						key: 'it',
						className: 'it'
					}
				],
				data1: [{
					name: '定价',
					us: '40000',
					it: '5W - 10W'
				}, {
					name: '服务特点',
					us: '专属顾问服务',
					it: '传统中介模式无产品特点和差异见人下菜 '
				}, {
					name: '选校方案',
					us: '智选顾问结合大数据定制冲刺方案',
					it: '个人主观经验选校顾问根据自己的经验主观选择学校'
				}],
				id: ''
			}
		},
		methods: {
			toresult() {
				this.$router.push({
					path: '/diy_result'
				});
			},
			diy() {
				if (!localStorage.getItem("id")) {
					localStorage.setItem("to", "/diy_index")
					this.$router.push({
						path: "/login"
					});
				}
				this.id = localStorage.getItem("id");
				this.$api.getRequest(this.$url.INTERFACES.applyOneByUser + this.id).then(res => {
					if (res.code == 200) {
						this.button = true;
					}
				})
				this.modal = true
			},
			submit() {
				var data = {
					userId: localStorage.getItem("id"),
					address: this.form.address,
					degree: this.form.planDegree,
					years: this.form.studyTime,
					major: this.form.major,
					outsideDate: this.form.outsideTime,
					university: this.form.university,
					score: this.form.score,
					tel: this.form.tel
				};
				console.log(data)
				this.$api.postRequest(this.$url.INTERFACES.applySave, data).then(res => {
					this.$Message['success']({
						background: true,
						content: '定制化成功'
					});
					this.$router.push({
						path: '/diy_result'
					});
				})
			},
		}
	}
</script>

<style>
	.diy_page {
		background-color: #2B4266;
		color: #FFFFFF;
	}

	.content h1 span {
		color: #43b4ff;
	}

	.content p #span {
		border-bottom: 1px solid #f06c03;
		padding: 5px 0;
	}

	.diy_button {
		display: block;
		width: 330px;
		height: 80px;
		line-height: 80px;
		text-align: center;
		margin: 0 auto;
		color: #fff;
		font-size: 30px;
		font-weight: 700;
		background-color: #f46d01;
		border-radius: 10px;
		margin-top: 50px;
		cursor: pointer;
	}


	.ivu-table td.pk {
		background-color: #FFFFFF;
		color: #999999;
	}

	.ivu-table th.pk {
		text-align: center;
		font-size: 22px;
		background-color: #fff;
		color: #999999;
	}

	.ivu-table td.us {
		background-color: #2db7f5;
		color: #fff;
	}

	.ivu-table th.us {
		text-align: center;
		font-size: 25px;
		background-color: #2db7f5;
		color: #fff;
		top: -9%;
		border-radius: 10px 10px 0 0;
		height: 120%;
	}

	.ivu-table td.it {
		background-color: #FFFFFF;
		color: #000000;
	}

	.ivu-table th.it {
		text-align: center;
		font-size: 22px;
		background-color: #FFFFFF;
		color: #000000;
	}

	table {
		border-radius: 4px;
	}
</style>
