// subpkg/order_detail.js
const app = getApp()
Page({

  /**
   * 页面的初始数据
   */
  data: {
  orderId:"",
  orderList:[],
  orderInfo:{},
  httpUrl:"http://localhost",
  },
  //跳转到订单页面
  gotoOrder(){
     

  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad(options) {
    this.setData({orderId: options.orderId});
    this.getdeata();
  },
  //获取数据
   getdeata(){
        var that=this;
       wx.request({
         url: app.globalData.serverurl+'/orders/queryoneorder?orderId='+this.data.orderId,
         method:"GET",
         success(res){
           console.log(res);
           that.setData({
             orderInfo:res.data
           })
         }
             
        })
   },
   deleteorder(){
    var that=this;
    wx.request({
      url: app.globalData.serverurl+'/orders/deleteorder',
      method:'DELETE',
      header: {
        "Content-Type": "application/x-www-form-urlencoded"
        },
        data:{
          orderId:this.data.orderId
        },
        success(res){
          console.log(res);
          if(res.data.code==0){
               wx.showToast({
                 title: '删除成功',
                  success(){
                    setTimeout(function () {
                      wx.navigateTo({
                      
                        url: '../../pages/orders/orders',
                      })   
                     }, 2000) //延迟时间 这里是1秒
                    
                  }
               });
                          
          }
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

  /**
   * 用户点击右上角分享
   */
  onShareAppMessage() {

  }
})