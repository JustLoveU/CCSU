// request.js
import axios from 'axios';


// get请求
function getRequest(url, params = {}, method = 'get') {
	return new Promise((resolve, reject) => {
		axios({
				url: url,
				method: method,
				params: params
			})
			.then(res => {
				//成功
				resolve(res.data)
			})
			.catch(res => {
				//失败
				reject(res)
			})

	})
}

// post请求
function postRequest(url, data = {}, method = 'post') {
	return new Promise((resolve, reject) => {
		axios({
				url: url,
				method: method,
				headers: {
					'Content-Type': 'application/json'
				},
				data: data
			})
			.then(res => {
				//成功
				resolve(res.data)
			})
			.catch(res => {
				//失败
				reject(res)
			})
	})
}

export default {
	postRequest: postRequest,
	getRequest: getRequest
}
// 原文链接：https://blog.csdn.net/qq_34322905/article/details/83149266
