<template>
  <div class="d2-page-cover">
    <div class="d2-page-cover__logo">
      <slot/>
    </div>
    <!-- <p class="d2-page-cover__title">D2 Admin {{$version}}</p> -->
    <p class="d2-page-cover__sub-title">后台管理平台</p>
    <div style="float: left;">
    <p>1、列表筛选时，只能筛选当页，如果要筛选所有的，可以设置每页1000来显示全部，搜索同理</p>
    <p>2、编辑文章注意：</p>
    <p>（1）、如果设置了外部链接，访问时就会跳转到外部链接，如果不想跳转，请勿填此项</p>
    <p>（2）、如果需要设置轮播图，则需要添加轮播图的链接地址，否则首页显示将出错，轮播图超过八个不显示</p>
    <p>（3）、如果需要设置轮播图，则需要先上传，再复制链接到图片输入框</p>
    <p>（4）、单张上传图片最大限度为10M，具体表现为上传后不显示链接，而是一个数字。正常：![标题](/article_img/1.jpg)，非正常：![标题](1)</p>
    <p>（5）、文章内插入视频：&lt;video src=&quot;视频链接&quot; controls=&quot;controls&quot; style=&quot;width:100%&quot;&gt;&lt;/video&gt;</p>
	<p>	(6) 、上传的图片应该尽量使用英文，尽量避免特殊字符，例如@ # / * ()</p>
    <p>3、编辑人员注意：</p>
    <p>（1）、单张上传图片最大限度为10M，并且格式只能为jpg与png</p>
    <p>（2）、人员管理的是否显示为首页滚动的显示</p>
    </div>
    <!-- <p class="d2-page-cover__build-time">FINAL BUILD TIME {{$buildTime}}</p> -->
    <slot name="footer"/>
<!--    <a target="blank" href="https://github.com/d2-projects/d2-admin">
      <img
        style="position: absolute; top: 0; right: 0; border: 0; width: 150px;"
        src="./image/darkblue@2x.png"
        alt="Fork me on GitHub">
    </a> -->
  </div>
</template>

<style lang="scss" scoped>
.d2-page-cover {
  @extend %full;
  @extend %unable-select;
  display: flex;
  flex-flow: column nowrap;
  justify-content: center;
  align-items: center;
  .d2-page-cover__logo {
    img {
      width: 200px;
    }
  }
  .d2-page-cover__title {
    margin: 0px;
    margin-bottom: 20px;
    font-weight: bold;
    color: $color-text-main;
  }
  .d2-page-cover__sub-title {
    margin: 0px;
    margin-bottom: 5px;
    color: $color-text-normal;
  }
  .d2-page-cover__build-time {
    margin: 0px;
    margin-bottom: 10px;
    font-size: 12px;
    color: $color-text-placehoder;
  }
}
</style>
