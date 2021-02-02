<template>
	<view>
		<view>
			<img :src="top" style="width: 100%;"></img>
			<view style="padding: 20rpx;">
				<u-form :model="form" ref="uForm" label-position="left" label-width="250" label-align="left" label-style="color:#ebebeb">
					<u-form-item label="预选区域:" label-width="180">
						<!--					<u-checkbox-group @change="checkboxGroupChange">
							<u-checkbox @change="checkboxChange" v-model="item.checked" :disabled="item.disabled" v-for="(item, index) in addressList"
							 :key="index" :name="item.name">{{item.name}}</u-checkbox>
						</u-checkbox-group> -->
						<u-radio-group @change="radioGroupChange" v-model="form.address">
							<u-radio @change="radioChange" :disabled="item.disabled" v-for="(item, index) in addressList" :key="index" :name="item.name">{{item.name}}</u-radio>
						</u-radio-group>
					</u-form-item>
					<u-form-item label="计划攻读学位:">
						<u-input v-model="form.planDegree" type="select" :border="border" @click="show.planDegreeList = true" />
						<u-action-sheet :list="planDegreeList" v-model="show.planDegreeList" @click="actionSheetCallback"></u-action-sheet>
					</u-form-item>
					<u-form-item label="已学习年限:">
						<u-input v-model="form.studyTime" type="select" :border="border" @click="show.studyTimeList = true" />
						<u-action-sheet :list="studyTimeList" v-model="show.studyTimeList" @click="studyTimeCallback"></u-action-sheet>
					</u-form-item>
					<u-form-item label="在读专业:">
						<u-input v-model="form.major" :border="border"/>
					</u-form-item>
					<u-form-item label="出国时间:">
						<u-input v-model="form.outsideTime" type="select" :border="border" @click="show.outsideTimeList = true" />
						<u-action-sheet :list="outsideTimeList" v-model="show.outsideTimeList" @click="outsideTimeCallback"></u-action-sheet>
					</u-form-item>
					<u-form-item label="毕业就读院校:">
						<u-input v-model="form.university" :border="border"/>
					</u-form-item>
					<u-form-item label="平时成绩:">
						<u-input v-model="form.score" placeholder="满分5分" :border="border"/>
					</u-form-item>
					<u-form-item label="手机号码:">
						<u-input v-model="form.tel" :border="border"/>
					</u-form-item>
					

				</u-form>
				<u-button @click="submit">智能DIY</u-button>
				<view style="margin-top: 20px;"></view>
				<!-- <u-button @click="toresult" :disabled="!button">已有定制，去看看</u-button> -->
			</view>

		</view>
	</view>
</template>

<script>
	const api = require('static/js/api');
	export default {
		data() {
			return {
				top: api.HOUSEINTERFACES.root+ 'img/diy_top.png',
				border: true,
				button: false,
				show: {
					planDegreeList: false,
					studyTimeList: false,
					outsideTimeList: false
				},
				addressList: [{
						name: '关东',
						checked: false,
						disabled: false
					},
					{
						name: '四国',
						checked: false,
						disabled: false
					},
					{
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
						name: '中国',
						checked: false,
						disabled: false
					},
					{
						name: '东北',
						checked: false,
						disabled: false
					},
					{
						name: '近畿',
						checked: false,
						disabled: false
					},
					{
						name: '九州',
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
					address: '',
					planDegree: '',
					studyTime: '',
					major: '',
					outsideTime: '',
					university: '',
					score: '',
					tel: ''
				}
			};
		},
		onLoad() {
			var login = this.checkLogin('/pages/diy/index/index', 1); //调用全局封装的函数,参数在上面已经介绍了
			if (!login) { //如果没有登录,则会提示
				uni.showToast({
					title: '请先登录！',
					icon: "none"
				});
				return false; //不走下面的,也就是不触发下面的函数
			}
			//TODO 获取当前用
			this.isHave();
		},
		methods: {
			toresult() {
				uni.navigateTo({
					url: '/pages/diy/result/result'
				})
			},
			isHave() {
				uni.request({
					url: api.INTERFACES.applyOneByUser + uni.getStorageSync("user").id,
					method: 'GET',
					success: res => {
						if (res.data.code == 200) {
							this.button = true;
							this.form = res.data.apply
							this.form.planDegree = res.data.apply.degree
							this.form.studyTime = res.data.apply.years
							this.form.outsideTime = res.data.apply.outsideDate
							// uni.navigateTo({
							// 	url: '/pages/diy/result/result'
							// })
							return false;
						}
					},
					fail: (res, code) => {
						console.log('fail' + JSON.stringify(res));
					}
				});
			},
			submit() {
				console.log(this.form)
				uni.request({
					url: api.INTERFACES.applySave,
					data: {
						userId: uni.getStorageSync("user").id,
						address: this.form.address,
						degree: this.form.planDegree,
						years: this.form.studyTime,
						major: this.form.major,
						outsideDate: this.form.outsideTime,
						university: this.form.university,
						score: this.form.score,
						tel: this.form.tel
					},
					method: 'POST',
					success: res => {
						uni.navigateTo({
							url: '/pages/diy/result/result?id='+res.data.apply.id
						})
						console.log(res.data)
					},
					fail: (res, code) => {
						console.log('fail' + JSON.stringify(res));
					}
				});
			},
			// 选中某个复选框时，由checkbox时触发
			checkboxChange(e) {
				// console.log(e);
			},
			// 选中任一checkbox时，由checkbox-group触发
			checkboxGroupChange(e) {
				this.form.address = e;
				console.log(this.form.address);
			},

			// 选中某个单选框时，由radio时触发
			radioChange(e) {
				console.log(e);
			},
			// 选中任一radio时，由radio-group触发
			radioGroupChange(e) {
				console.log(e);
			},
			// 点击planDegree回调
			actionSheetCallback(index) {
				this.form.planDegree = this.planDegreeList[index].text;
			},
			studyTimeCallback(index) {
				this.form.studyTime = this.studyTimeList[index].text;
			},
			outsideTimeCallback(index) {
				this.form.outsideTime = this.outsideTimeList[index].text;
			},
		}
	}
</script>

<style>
</style>
