<template>
  <d2-container>
    <template slot="header">{{title}}</template>
    <template>
      <mavon-editor
        class="md"
        :value="value"
        :subfield = "false"
        :defaultOpen = "'preview'"
        :toolbarsFlag = "false"
        :editable="false"
        :scrollStyle="true"
        :ishljs = "true"/>
    </template>
  </d2-container>
</template>

<script>
import { mavonEditor } from 'mavon-editor'
import 'mavon-editor/dist/css/index.css'
export default {
  name: 'page3',
  components: { mavonEditor },
  data () {
    return {
      value: '',
      title: ''
    }
  },
  created () {
    this.getArticle(this.$route.query.id)
  },
  methods: {
    getArticle (id) {
      this.$axios.get('/admin/article/one/' + id).then(res => {
        this.value = res.data.content
        this.title = res.data.title
      }).catch(err => {
        console.log(err)
      })
    }
  }
  // computed: {
  // // 解析器配置
  //   prop () {
  //     let data = {
  //       subfield: false,
  //       // edit： 默认展示编辑区域 ， preview： 默认展示预览区域
  //       defaultOpen: 'preview',
  //       // 是否允许编辑
  //       editable: false,
  //       toolbarsFlag: false,
  //       scrollStyle: true
  //     }
  //     return data
  //   }
  // }
}
</script>
