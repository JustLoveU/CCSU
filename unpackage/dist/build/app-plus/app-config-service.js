
var isReady=false;var onReadyCallbacks=[];
var isServiceReady=false;var onServiceReadyCallbacks=[];
var __uniConfig = {"pages":["pages/index/index/index","pages/comment/index/index","pages/info/index/index","pages/diy/index/index","pages/my/index/index","pages/my/login/login","pages/info/comment/comment","pages/diy/result/result","pages/info/list/list","pages/info/detail/detail","pages/my/about/about","pages/my/register/register","pages/my/service/service","pages/my/collection/collection","pages/index/search/search","pages/index/universitylist/universitylist","pages/info/university_detail/university_detail","pages/diy/diy_list/diy_list","pages/my/ing/ing"],"window":{"navigationBarTextStyle":"black","navigationBarTitleText":"留学无难事","navigationBarBackgroundColor":"#F8F8F8","backgroundColor":"#F8F8F8"},"tabBar":{"color":"#000000","selectedColor":"#23EBB9","borderStyle":"black","list":[{"pagePath":"pages/index/index/index","text":"首页","iconPath":"static/index.png","selectedIconPath":"static/index_select.png"},{"pagePath":"pages/comment/index/index","text":"资讯","iconPath":"static/info.png","selectedIconPath":"static/info_select.png"},{"pagePath":"pages/info/index/index","text":"点评","iconPath":"static/comment.png","selectedIconPath":"static/comment_select.png"},{"pagePath":"pages/diy/index/index","text":"DIY","iconPath":"static/diy.png","selectedIconPath":"static/diy_select.png"},{"pagePath":"pages/my/index/index","text":"我的","iconPath":"static/my.png","selectedIconPath":"static/my_select.png"}]},"nvueCompiler":"uni-app","renderer":"auto","splashscreen":{"alwaysShowBeforeRender":true,"autoclose":false},"appname":"website_2_Student","compilerVersion":"2.8.3","entryPagePath":"pages/index/index/index","networkTimeout":{"request":60000,"connectSocket":60000,"uploadFile":60000,"downloadFile":60000}};
var __uniRoutes = [{"path":"/pages/index/index/index","meta":{"isQuit":true,"isTabBar":true},"window":{"navigationBarTitleText":"易留学","navigationStyle":"custom"}},{"path":"/pages/comment/index/index","meta":{"isQuit":true,"isTabBar":true},"window":{"navigationBarTitleText":"资讯","navigationStyle":"custom"}},{"path":"/pages/info/index/index","meta":{"isQuit":true,"isTabBar":true},"window":{"navigationBarTitleText":"点评","navigationStyle":"custom"}},{"path":"/pages/diy/index/index","meta":{"isQuit":true,"isTabBar":true},"window":{"navigationBarTitleText":"智能DIY","navigationStyle":"custom"}},{"path":"/pages/my/index/index","meta":{"isQuit":true,"isTabBar":true},"window":{"navigationBarTitleText":"我的","navigationStyle":"custom"}},{"path":"/pages/my/login/login","meta":{},"window":{}},{"path":"/pages/info/comment/comment","meta":{},"window":{"navigationStyle":"custom"}},{"path":"/pages/diy/result/result","meta":{},"window":{"navigationBarTitleText":"智能DIY结果","navigationStyle":"custom"}},{"path":"/pages/info/list/list","meta":{},"window":{"navigationStyle":"custom"}},{"path":"/pages/info/detail/detail","meta":{},"window":{"navigationStyle":"custom"}},{"path":"/pages/my/about/about","meta":{},"window":{"navigationStyle":"custom"}},{"path":"/pages/my/register/register","meta":{},"window":{"navigationStyle":"custom"}},{"path":"/pages/my/service/service","meta":{},"window":{}},{"path":"/pages/my/collection/collection","meta":{},"window":{}},{"path":"/pages/index/search/search","meta":{},"window":{}},{"path":"/pages/index/universitylist/universitylist","meta":{},"window":{}},{"path":"/pages/info/university_detail/university_detail","meta":{},"window":{}},{"path":"/pages/diy/diy_list/diy_list","meta":{},"window":{}},{"path":"/pages/my/ing/ing","meta":{},"window":{}}];
__uniConfig.onReady=function(callback){if(__uniConfig.ready){callback()}else{onReadyCallbacks.push(callback)}};Object.defineProperty(__uniConfig,"ready",{get:function(){return isReady},set:function(val){isReady=val;if(!isReady){return}const callbacks=onReadyCallbacks.slice(0);onReadyCallbacks.length=0;callbacks.forEach(function(callback){callback()})}});
__uniConfig.onServiceReady=function(callback){if(__uniConfig.serviceReady){callback()}else{onServiceReadyCallbacks.push(callback)}};Object.defineProperty(__uniConfig,"serviceReady",{get:function(){return isServiceReady},set:function(val){isServiceReady=val;if(!isServiceReady){return}const callbacks=onServiceReadyCallbacks.slice(0);onServiceReadyCallbacks.length=0;callbacks.forEach(function(callback){callback()})}});
service.register("uni-app-config",{create(a,b,c){if(!__uniConfig.viewport){var d=b.weex.config.env.scale,e=b.weex.config.env.deviceWidth,f=Math.ceil(e/d);Object.assign(__uniConfig,{viewport:f,defaultFontSize:Math.round(f/20)})}return{instance:{__uniConfig:__uniConfig,__uniRoutes:__uniRoutes,global:void 0,window:void 0,document:void 0,frames:void 0,self:void 0,location:void 0,navigator:void 0,localStorage:void 0,history:void 0,Caches:void 0,screen:void 0,alert:void 0,confirm:void 0,prompt:void 0,fetch:void 0,XMLHttpRequest:void 0,WebSocket:void 0,webkit:void 0,print:void 0}}}});