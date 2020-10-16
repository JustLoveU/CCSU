// import { find, assign } from 'lodash'
import axios from 'axios'
import assign from 'lodash'
import util from '@/libs/util.js'

// const users = [
//   { username: 'admin', password: 'admin', uuid: 'admin-uuid', name: 'Admin' },
//   { username: 'lomtom', password: '123', uuid: '超级管理员', name: 'lomtom' }]

export default ({ service, request, serviceForMock, requestForMock, mock, faker, tools }) => ({
  /**
   * @description 登录
   * @param {Object} data 登录携带的信息
   */
  SYS_USER_LOGIN (data = {}) {
    axios.post('/admin/user/login', {
      username: data.username,
      password: data.password
    }).then(({
      data
    }) => {
      if (data.code === 0) {
        console.log(data)
        const user = data.user
        util.cookies.set('uuid', user.uuid)
        util.cookies.set('token', faker.random.uuid())
        return tools.responseSuccess(assign({}, user, { token: faker.random.uuid() }))
      } else {
        console.log(data)
        return tools.responseError({}, '账号或密码不正确')
      }
    })
    // // 模拟数据
    // mock
    //   .onAny('/login')
    //   .reply(config => {
    //     const user = find(users, tools.parse(config.data))
    //     return user
    //       ? tools.responseSuccess(assign({}, user, { token: faker.random.uuid() }))
    //       : tools.responseError({}, '账号或密码不正确')
    //   })
    // // 接口请求
    // return requestForMock({
    //   url: '/login',
    //   method: 'post',
    //   data
    // })
  }
})
