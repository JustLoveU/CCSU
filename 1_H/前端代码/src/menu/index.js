import { uniqueId } from 'lodash'

/**
 * @description 给菜单数据补充上 path 字段
 * @description https://github.com/d2-projects/d2-admin/issues/209
 * @param {Array} menu 原始的菜单数据
 */
function supplementPath (menu) {
  return menu.map(e => ({
    ...e,
    path: e.path || uniqueId('d2-menu-empty-'),
    ...e.children ? {
      children: supplementPath(e.children)
    } : {}
  }))
}

export const menuHeader = supplementPath([
  { path: '/index', title: '首页', icon: 'home' },
  {
    title: '文章管理',
    icon: 'file-text',
    path: '/page1'
    // children: [
    //   { path: '/page1', icon: 'file-text-o', title: '文章列表' },
    //   { path: '/page2?id=-1', icon: 'edit', title: '文章编辑' }
    // ]
  },
  {
    title: '人员管理',
    icon: 'user',
    path: '/list'
    // children: [
    //   { path: '/list', icon: 'users', title: '人员列表' },
    //   { path: '/edit?id=-1', icon: 'edit', title: '人员编辑' }
    // ]
  },
  {
    title: '友链管理',
    icon: 'link',
    path: '/friend'
  },
  {
    title: '管理员管理',
    icon: 'gear',
    path: '/user'
  },
  {
    title: '简介修改',
    icon: 'file-powerpoint-o',
    path: '/page4'
  },
  {
    title: '页脚修改',
    icon: 'file-excel-o',
    path: '/page5'
  }
])

export const menuAside = supplementPath([
  { path: '/index', title: '首页', icon: 'home' },
  {
    title: '文章管理',
    icon: 'file-text',
    path: '/page1'
    // children: [
    //   { path: '/page1', icon: 'file-text-o', title: '文章列表' },
    //   { path: '/page2?id=-1', icon: 'edit', title: '文章编辑' }
    // ]
  },
  {
    title: '人员管理',
    icon: 'user',
    path: '/list'
    // children: [
    //   { path: '/list', icon: 'users', title: '人员列表' },
    //   { path: '/edit?id=-1', icon: 'edit', title: '人员编辑' }
    // ]
  },
  {
    title: '友链管理',
    icon: 'link',
    path: '/friend'
  },
  {
    title: '管理员管理',
    icon: 'gear',
    path: '/user'
  },
  {
    title: '简介修改',
    icon: 'file-powerpoint-o',
    path: '/page4'
  },
  {
    title: '页脚修改',
    icon: 'file-excel-o',
    path: '/page5'
  }
])
