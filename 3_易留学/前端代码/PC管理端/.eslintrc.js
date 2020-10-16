// https://eslint.org/docs/user-guide/configuring

module.exports = {
  root: true,
  parser: 'babel-eslint',
  parserOptions: {
    sourceType: 'module'
  },
  env: {
    browser: true,
  },
  // https://github.com/standard/standard/blob/master/docs/RULES-en.md
  extends: 'standard',
  // required to lint *.vue files
  plugins: [
    'html'
  ],
  // add your custom rules here
  rules: {
    // allow async-await
    'generator-star-spacing': 'off',
    // allow debugger during development
    'no-debugger': process.env.NODE_ENV === 'production' ? 'error' : 'off',
    //这句话表示在文件末尾可以不加回车，贴个链接  https://eslint.org/docs/rules/eol-last
    'eol-last': 0,
     //这句话表示在函数后可以不加空格
    'space-before-function-paren': 0,
     // 箭头函数中的箭头前后是否需要空格
    'arrow-spacing':[2,{
      'before':true,
      'after':true
    }],
    // // 对逗号前后的空格进行检测
    // 'comma-spacing':[2,{
    //   // 'before':false表示逗号前面无需空格
    //   'before':false,
    //   // 'after':false表示逗号后面无需空格
    //   'after':false
    // }],
  }
}
