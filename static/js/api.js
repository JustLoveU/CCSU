// const ONLINEHOST = "https://ou.lomtom.top/website_university", 
// LOGINHOST = "https://ou.lomtom.top/website_university", 
// HOUSEONLINEHOST = "https://ou.lomtom.top",
const ONLINEHOST = "http://localhost:8083/website_university", 
LOGINHOST = "http://localhost:8083/website_university", 
HOUSEONLINEHOST = "http://localhost:8083",
 INTERFACES = {}, SINGLOGIN = {}, HOUSEINTERFACES = {};


const APILIST = {
	root:					"/",
  login:					"/user/login",				//登录
  register:					"/user/save",				//注册
  userOne:					"/user/info/",				//获得一个用户
  userUpdate:				"/user/update",				//更新一个用户
  infoTypeList:				"/infotype/list1",			//获取首页热门板块
  infoSearch:				"/info/infoSearch",			//获取搜索资讯列表分页
  infoList1:				"/info/list2",				//获取资讯列表分页
  infoList:					"/info/list1",				//获取首页热门板块
  InfoListByUniversity:		"/info/ListByUniversity/",	//通过大学获取政策
  infoOne:					"/info/info/",				//获取首页热门板块
  universityList:			"/university/list1",		//获取top大学
  universityOne:			"/university/info/",		//获取top大学
  universityListByUser:		"/university/ListByUser/",	//获取大学收藏
  universitySearch:			"/university/search",		//搜索
  universitySearch1:		"/university/search1",		//获取
  universityList2:			"/university/list2",		//获取
  universitylistLimit:		"/university/listLimit/",	//获取topnumber
  commentListByUniversity:	"/comment/ListByUniversity/",//通过大学获取政策
  commentSave:				"/comment/save"				,//保存评论
  commentListByUser:		"/comment/ListByUser/",		//评论根据用户
  imgUpload:				"/file/img",				//上传图片
  collectionOne:			"/collection/one",			//获取是否收藏
  collectionSave:			"/collection/save",			//收藏 
  collectionDeleteOne:		"/collection/deleteOne/",	//取消收藏 
  applySave:				"/apply/save/",				//保存申请 
  applyOneByUser:			"/apply/isHave/",			//获取申请
  ResultOneByApply:			"/result/getOneByApplyId/",	//获取申请结果
}

 // 调用
 // const api = require('../../common/api');
 // console.log(api.SINGLOGIN.homeSlideshow)
 // console.log(api.INTERFACES.homeSlideshow)
 
const HANDLEAPI = function (){screen
  let api = {};
  for(let k in APILIST){ api[k] = ONLINEHOST + APILIST[k]; }
  return api;
}
 
const LOGINAPI = function () {screen
  let api = {};
  for (let k in APILIST) { api[k] = LOGINHOST + APILIST[k]; }
  return api;
}
 
const HOUSEHANDLEAPI = function () {
  screen
  let api = {};
  for (let k in APILIST) { api[k] = HOUSEONLINEHOST + APILIST[k]; }
  return api;
}
 
module.exports = {
  INTERFACES: HANDLEAPI(),
  SINGLOGIN: LOGINAPI(),
  HOUSEINTERFACES: HOUSEHANDLEAPI()
}