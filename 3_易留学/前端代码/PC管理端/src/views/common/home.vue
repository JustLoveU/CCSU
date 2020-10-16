<template>
  <!-- <dv-full-screen-container> -->
  <div id="show">
    <div>
      <el-row>
        <el-col :span="8"><img src="../../../static/assets/ksh31.png"></img></el-col>
        <el-col :span="8" style="text-align: center;">
          <span id="title">易留学</span>
          <dv-decoration-5 style="width:100%;height:40px;" dur="3" />
        </el-col>
        <el-col :span="8"><img src="../../../static/assets/ksh32.png"></img></el-col>
      </el-row>
      <el-row>
        <el-col :span="6" class="box">
          <div class="visual_title">
            <span>访问来源</span>
            <img src="../../../static/assets/ksh33.png">
          </div>
          <dv-border-box-1>
            <div id="chart1" style="width: 100%;height: 280px;z-index: 999;padding: 10px;"></div>
          </dv-border-box-1>
          <div class="visual_title">
            <span>资讯所属</span>
            <img src="../../../static/assets/ksh33.png">
          </div>
          <dv-border-box-1>
            <div id="chart2" style="width: 100%;height: 360px;;z-index: 999;padding: 10px;"></div>
          </dv-border-box-1>
        </el-col>
        <el-col :span="12" class="box">
          <div class="visual_conTop">
            <div class="visual_conTop_box visual_conTop1">
              <div>
                <h3>资讯数量</h3>
                <p>67</p>
                <div class="conTop_smil">
                  <a class="sz">日环比:<span>+3%</span><i class="fa fa-long-arrow-up"></i></a>
                  <a class="xd">周环比:<span>-2%</span><i class="fa fa-long-arrow-down"></i></a>
                </div>
              </div>
            </div>
            <div class="visual_conTop_box visual_conTop2">
              <div>
                <h3>DIY次数</h3>
                <p>14</p>
                <div class="conTop_smil">
                  <!-- <a class="">缓行</a> -->
                  <a class="">平均车速<span>120</span>KM/H</a>
                </div>
              </div>
            </div>
            <div class="visual_conTop_box visual_conTop1">
              <div>
                <h3>用户</h3>
                <p>99</p>
                <div class="conTop_smil">
                  <a class="sz">日环比:<span>+3%</span><i class="fa fa-long-arrow-up"></i></a>
                  <a class="xd">增长:<span>2%</span><i class="fa fa-long-arrow-down"></i></a>
                </div>
              </div>
            </div>
            <div class="visual_conTop_box visual_conTop2">
              <div>
                <h3>入驻院校数</h3>
                <p>741</p>
                <div class="conTop_smil">
                  <a class="xd">增长:<span>2%</span><i class="fa fa-long-arrow-down"></i></a>
                </div>
              </div>
            </div>
            <div class="clear"></div>
          </div>
          <div style="height: 30px;"></div>
          <dv-border-box-11 title="用户数据" style="height: 580px;">
            <div id="chart3" style="width: 100%;height: 560px;;z-index: 999;padding: 10px;"></div>
          </dv-border-box-11>
        </el-col>
        <el-col :span="6" class="box">
          <dv-border-box-10>
            <div style="padding: 10px;">
              <el-row>
                <el-col :span="12">
                  <dv-decoration-9 style="width:150px;height:150px;">66%</dv-decoration-9>
                </el-col>
                <el-col :span="12">
                  <dv-decoration-12 style="width:150px;height:150px;" />
                </el-col>
              </el-row>
            </div>
          </dv-border-box-10>
          <div class="visual_title">
            <span>访问来源</span>
            <img src="../../../static/assets/ksh33.png">
          </div>
          <dv-border-box-13 style="height: 280px;">
            <dv-scroll-board :config="config1" style="width: 95%; height:220px;padding: 10px;padding-top: 15px;" />
          </dv-border-box-13>
          <dv-decoration-1 style="width:200px;height:50px;" />
          <dv-water-level-pond :config="config2" style="width:100%;height:200px" />
        </el-col>
      </el-row>
    </div>
  </div>
  <!-- </dv-full-screen-container> -->
</template>

<script>
  export default {
    name: 'index',
    data() {
      return {
        config1: {
          data: [
            ['行1列1', '行1列2', '行1列3'],
            ['行2列1', '行2列2', '行2列3'],
            ['行3列1', '行3列2', '行3列3'],
            ['行4列1', '行4列2', '行4列3'],
            ['行5列1', '行5列2', '行5列3'],
            ['行6列1', '行6列2', '行6列3'],
            ['行7列1', '行7列2', '行7列3'],
            ['行8列1', '行8列2', '行8列3'],
            ['行9列1', '行9列2', '行9列3'],
            ['行10列1', '行10列2', '行10列3']
          ]
        },
        config2: {
          data: [66, 45],
          shape: 'roundRect'
        }
      }
    },
    methods: {
      drawChart() {
        // 基于准备好的dom，初始化echarts实例
        let myChart1 = this.$echarts.init(document.getElementById('chart1'))
        let myChart2 = this.$echarts.init(document.getElementById('chart2'),'light')
        let myChart3 = this.$echarts.init(document.getElementById('chart3'),'dark')
        // 指定图表的配置项和数据
        let option1 = {
          backgroundColor: '',
          tooltip: {
            trigger: 'item',
            formatter: '{a} <br/>{b}: {c} ({d}%)'
          },
          legend: {
            orient: 'vertical',
            left: 10,
            data: ['直接访问', 'app', 'pc端', '小程序', '搜索引擎']
          },
          series: [{
            name: '访问来源',
            type: 'pie',
            radius: ['50%', '70%'],
            avoidLabelOverlap: false,
            label: {
              show: false,
              position: 'center'
            },
            emphasis: {
              label: {
                show: true,
                fontSize: '30',
                fontWeight: 'bold'
              }
            },
            labelLine: {
              show: false
            },
            data: [{
                value: 335,
                name: '直接访问'
              },
              {
                value: 310,
                name: 'app'
              },
              {
                value: 234,
                name: 'pc端'
              },
              {
                value: 135,
                name: '小程序'
              },
              {
                value: 1548,
                name: '搜索引擎'
              }
            ]
          }]
        }
        let option2 = {
          backgroundColor: '',
          legend: {
            left: 'center',
            top: 'bottom',
            data: ['语培', '面试', '签证', '机票', '智能', '选校']
          },
          series: [{
            name: '面积模式',
            type: 'pie',
            radius: [30, 110],
            center: ['50%', '50%'],
            roseType: 'area',
            data: [{
                value: 10,
                name: '语培'
              },
              {
                value: 15,
                name: '面试'
              },
              {
                value: 15,
                name: '签证'
              },
              {
                value: 25,
                name: '机票'
              },
              {
                value: 20,
                name: '智能'
              },
              {
                value: 35,
                name: '选校'
              }
            ]
          }]
        }
        let option3 = {
          backgroundColor: '',
          tooltip: {
            trigger: 'axis',
            axisPointer: {
              type: 'cross',
              label: {
                backgroundColor: '#6a7985'
              }
            }
          },
          dataZoom: [{
            type: 'slider',
            start: 60,
            end: 100
          }],
          grid: {
            left: '3%',
            right: '4%',
            // bottom: '3%',
            containLabel: true
          },
          xAxis: [{
            type: 'category',
            boundaryGap: false,
            data: ['周一', '周二', '周三', '周四', '周五', '周六', '周日',
              '周一', '周二', '周三', '周四', '周五', '周六', '周日',
              '周一', '周二', '周三', '周四', '周五', '周六', '周日',
              '周一', '周二', '周三', '周四', '周五', '周六', '周日',
              '周一', '周二', '周三', '周四', '周五', '周六', '周日'
            ]
          }],
          yAxis: [{
            type: 'value'
          }],
          series: [{
              name: '用户数',
              type: 'line',
              stack: '1',
              label: {
                normal: {
                  show: true,
                  position: 'top'
                }
              },
              areaStyle: {},
              data: [50, 122, 221, 234, 390, 330, 320,
                520, 532, 501, 534, 590, 530, 520,
                720, 732, 701, 734, 790, 730, 720,
                820, 832, 701, 934, 830, 830, 820,
                820, 862, 901, 934, 1560, 1570, 1590
              ]
            },
            {
              name: '活跃数',
              type: 'line',
              stack: '2',
              areaStyle: {},
              data: [420, 332, 301, 334, 390, 330, 320,
                320, 442, 301, 334, 450, 330, 350,
                320, 332, 301, 334, 390, 550, 370,
                220, 422, 301, 334, 499, 330, 440,
                330, 562, 451, 434, 870, 670, 770
              ]
            },
            {
              name: '会员数',
              type: 'line',
              stack: '3',
              areaStyle: {},
              data: [
                320, 332, 301, 334, 390, 330, 320,
                320, 332, 301, 334, 390, 330, 350,
                320, 332, 301, 334, 390, 330, 370,
                320, 332, 301, 334, 490, 330, 380,
                320, 332, 301, 334, 390, 350, 520
              ]
            }
          ]
        }
        // 使用刚指定的配置项和数据显示图表。
        myChart1.setOption(option1)
        myChart2.setOption(option2)
        myChart3.setOption(option3)
      }
    },
    mounted() {
      this.drawChart()
    }
  }
</script>


<style>
  @font-face {
    font-family: yjsz;
    src: url('../../../static/assets/yjsz.ttf');
  }

  .el-card__body{
    padding: 0;
   }
  #show {
    color: #ffffff;
    padding-top: 6px;
    /* padding-bottom: 40px; */
    background-size: cover;
    background: url(../../../static/assets/data08.png) no-repeat #061537;

  }

  #title {
    text-align: center;
    height: 100%;
    display: flex;
    justify-content: center;
    align-items: center;
    font-size: 40px;
    background: linear-gradient(0deg, #0095d6, #46daff);
    background-size: cover;
    -webkit-background-clip: text;
    background-clip: text;
    -webkit-text-fill-color: transparent;
    text-fill-color: transparent;
    font-weight: bolder;
    cursor: text;
  }

  .box {
    padding: 10px;
  }

  .visual_title {
    position: relative;
    text-align: left;
    height: 35px;
    margin: 5px 0;
  }

  .visual_title span {
    padding-left: 10px;
    color: #fff;
    font-size: 18px;
    line-height: 35px;
  }

  .visual_title img {
    width: 100%;
    position: absolute;
    left: 0;
    bottom: 0;
  }

  .visual_conTop {
    height: 130px;
    margin-bottom: 10px;
  }

  .visual_conTop .visual_conTop_box>div {
    height: 100%;
  }

  .visual_conTop .visual_conTop1 {
    width: 20%;
    height: 100%;
    float: left;
  }

  .visual_conTop .visual_conTop2 {
    width: 30%;
    height: 100%;
    float: left;
  }

  .visual_conTop .visual_conTop1>div {
    margin: 5px;
    padding-bottom: 20px;
    background: url(../../../static/assets/ksh40.png) no-repeat;
    background-size: 100% 100%;
  }

  .visual_conTop .visual_conTop_box>div h3 {
    color: #fff;
    font-size: 16px;
    padding: 16px 0 0 20px;
  }

  .visual_conTop .visual_conTop_box>div p {
    width: 33%;
    float: left;
    line-height: 80px;
    color: #20dbfd;
    text-shadow: 0 0 25px #00d8ff;
    font-size: 46px;
    font-family: 'yjsz';
    text-align: right;
    margin: 0;
  }

  .visual_conTop .visual_conTop_box>div .conTop_smil {
    width: 67%;
    height: 60px;
    float: left;
    padding-top: 23px;
  }

  .visual_conTop .visual_conTop_box>div .conTop_smil a {
    display: block;
    line-height: 20px;
    text-align: left;
    color: #fff;
    padding-left: 9px;
  }

  .visual_conTop .visual_conTop2>div {
    padding-bottom: 20px;
    background: url(../../../static/assets/ksh39.png) no-repeat;
    background-size: 100% 100%;
  }

  .visual_conTop .visual_conTop_box>div .conTop_smil a.sz {
    color: #fe3e12;
  }

  .visual_conTop .visual_conTop_box>div .conTop_smil a.xd {
    color: #12fe81;
  }
</style>
