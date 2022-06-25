// pages/cate/cate.js
const {getCateList}=require("../../utils/apis")
const app = getApp()
Page({

  /**
   * 页面的初始数据
   */
  data: {
    cateList:{},
    cateLevel2:{},
    active: 0,
    httpUrl:"http://www.pumpkinspring.top:6808/hmmall/",
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad(options) {
this.initData();
  },
  changeActive(e){
    var i = e.currentTarget.dataset.index;
  var cateLevel2 = this.data.cateList[i].children;
  this.setData({
    active: i,
    cateLevel2
  });
},
//获取分类信息
initData(){
  getCateList((res)=>{
    var cateLevel2 = res.message[this.data.active].children;
    this.setData({
      cateList: res.message,
      cateLevel2: res.message[0].children
    });
  });
},
// 点击跳转 goodsList 页面
gotoGoodsList(e){
  var cid = e.currentTarget.dataset.cid;
  wx.navigateTo({
    url: '/subpkg/goods_list/goods_list?cid=' + cid,
  });
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