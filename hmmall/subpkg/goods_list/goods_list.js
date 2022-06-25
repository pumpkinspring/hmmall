// subpkg/goods_list/goods_list.js
const {getGoodsList}=require("../../utils/apis")
const app = getApp()
Page({

  /**
   * 页面的初始数据
   */
  data: {
    cid: '',
    queryObj: {
      query: '',
      cid: '',
      pagenum: 1,
      pagesize: 10
    },
    // 商品列表的数据
    goodsList: [],
    // 商品总数
    total: 0
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad(options) {
    this.setData({
      cid: options.cid,
      queryObj: {
        query: options.query || '',
        cid: options.cid || '',
        pagenum: 1,
     	pagesize: 10
      }
    });
    this.initData();
   
    
  },
  initData(){
    getGoodsList(this.data.queryObj, (res)=>{
      this.setData({
        goodsList: res.message.goods,
        total: res.message.total
      });
    });
    
  },
   // 上拉加载
   onReachBottom(){
    var queryObj = this.data.queryObj;
    // 临界值判断
    if(queryObj.pagenum * queryObj.pagesize > this.data.total){
      wx.showToast({
        title: '没有更多数据了',
        icon: "none"
      });
      return 
    }
    // 修改请求参数
    queryObj.pagenum += 1;
    this.setData({ queryObj });
    // 请求数据
    getGoodsList(this.data.queryObj, (res)=>{
      this.setData({
        goodsList: [...this.data.goodsList, ...res.message.goods],
        total: res.message.total
      })
    });
  },
  // 下拉刷新
onPullDownRefresh(){
  var queryObj = this.data.queryObj;
  queryObj.pagenum = 1;
  this.setData({
    queryObj: queryObj,
    goodsList: [],
    total: 0
  });
  
  getGoodsList(this.data.queryObj, (res)=>{
    this.setData({
      goodsList: res.message.goods,
      total: res.message.total
    });
  });
},
// 跳转到商品详情页
gotoGoodsDetail(e){
  var goods_id = e.currentTarget.dataset.goods_id;
  wx.navigateTo({
    url: '/subpkg/goods_detail/goods_detail?goods_id=' + goods_id,
  })
}
,
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