// pages/home/home.js
//const {getSwiperList}=require("../../utils/apis")
const {getWebData}=require("../../utils/apis")
const { getSwiperList, getNavList ,getFloorList} = require("../../utils/apis");
Page({

  /**
   * 页面的初始数据
   */
  data: {
swiperList:[],
navList: []
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad(options) {
    this.initdata();
    // wx.setStorage({
    //   key:'cartList',
    //   value:[]
    // });
     
  },
initdata(){
  //获取轮播图数据
  //传递一个回调参数，用于改变swiperList的值
  getSwiperList((result)=>{
    this.setData({
      swiperList:result
    })
  });

  //获取导航栏数据
  getNavList(res => {
    this.setData({ navList: res.message });
  });
  // getWebData((result)=>{
  //   this.setData({
  //     swiperList:result
  //   })
  // },'/api/public/v1/home/swiperdata');
  //获取楼层数据
     getFloorList(res => {
    this.setData({ floorList: res.message });
  });
  

},
// 点击导航跳转
navClickHandler(e) {
  var item = e.currentTarget.dataset.item;
  if (item.name === '分类') {
    wx.switchTab({
      url: '/pages/cate/cate',
    });
  }
},
  /**
   * 生命周期函数--监听页面初次渲染完成
   */
  onReady() {

  },

  /**
   * 生命周期函数--监听页面显示
   */
  onShow() {

  },

  /**
   * 生命周期函数--监听页面隐藏
   */
  onHide() {

  },

  /**
   * 生命周期函数--监听页面卸载
   */
  onUnload() {

  },

  /**
   * 页面相关事件处理函数--监听用户下拉动作
   */
  onPullDownRefresh() {

  },

  /**
   * 页面上拉触底事件的处理函数
   */
  onReachBottom() {

  },

  /**
   * 用户点击右上角分享
   */
  onShareAppMessage() {

  }
})