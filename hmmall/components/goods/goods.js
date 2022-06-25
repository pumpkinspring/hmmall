// components/goods/goods.js
const app = getApp()
Component({
  /**
   * 组件的属性列表
   */
  properties: {
    item: {
      type: Object,
      value: {}
    },
    defaultPic:"https://img3.doubanio.com/f/movie/8dd0c794499fe925ae2ae89ee30cd225750457b4/pics/movie/celebrity-default-medium.png"
  },

  /**
   * 组件的初始数据
   */
  data: {
    httpUrl:"http://localhost",
  },

  /**
   * 组件的方法列表
   */
  methods: {

  }
})
