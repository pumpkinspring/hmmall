// components/cart_goods/cart_goods.js
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

     cartList:[],
     goods_id:0,
     httpUrl:"http://www.pumpkinspring.top:6808/hmmall/",

  },

  /**
   * 组件的方法列表
   */
  methods: {
    
  
       
  }
})
