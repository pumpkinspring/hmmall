// pages/collect/collect.js
Page({

  /**
   * 页面的初始数据
   */
  data: {
   collectList:[],
   cid: '',
   queryObj: {
     query: '',
     cid: '',
   },
   total:0
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad(options) {
    var that=this;
    wx.getStorage({
      key:'collectList',
      success(res){
           that.setData({
             collectList:res.data
           })
           
           console.log(that.data.collectList);
      }
    })
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
  gotoGoodsDetail(e){
    var goods_id = e.currentTarget.dataset.goods_id;
    wx.navigateTo({
      url: '/subpkg/goods_detail/goods_detail?goods_id=' + goods_id,
    })
  }
  ,

  /**
   * 用户点击右上角分享
   */
  onShareAppMessage() {

  },
})