// pages/orders/orders.js
const app = getApp()
Page({

  /**
   * 页面的初始数据
   */
  data: {
      orderList:[],
      httpUrl:"http://localhost",
      userId:0
  },
//进入订单详情页
gotoOrderDetail(e){
  console.log(e);
  var order_num=e.currentTarget.dataset.goods_id;
  wx.navigateTo({
    url: '/subpkg/order_detail/order_detail?orderId='+order_num,
  })
},
  /**
   * 生命周期函数--监听页面加载
   */
  onLoad(options) {
     this.getdata();
  },
   //获取订单数据
   getdata(){
     var that=this;
      wx.getStorage({
        key:'userId',
        success(res){
          that.setData({
            userId:res.data
          });
          wx.request({
            url: app.globalData.serverurl+'/orders/queryorder?userId='+that.data.userId,
            header: {
              "Content-Type": "application/x-www-form-urlencoded"
              },
            success(res){
              console.log(res.data);
             that.setData({
               orderList:res.data
             })
             if(that.data.orderList==[]){
                 wx.showToast({
                   title: '订单为空',
                   icon:'error'
                 })
             }
            }
          })
        }
      })
    
    //  var that=this;
    //       wx.getStorage({
    //         key:'orderList',
    //         success(res){
    //           console.log("获取订单数据成功");
    //           that.setData({
    //             orderList:res.data
    //           })
    //         }
    //       });
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