// pages/start/start.js
Page({

  /**
   * 页面的初始数据
   */
  data: {
   
  },
  getUserProfile(e) {
    // 获取用户信息，每次通过该接口获取用户个人信息均需用户确认
    
    wx.getUserProfile({
      desc: '用于完善会员资料', // 声明获取用户个人信息后的用途，后续会展示在弹窗中
      success: (res) => {
        console.log(res);
        //console.log("用户信息：",res.userInfo);
        wx.setStorage({
          key:"usermsg",
          data:res
        })

        // wx.setStorage({
        //   key:"token",
        //   data:"moximoxi31415926"
        // })
        
        //跳转到首页
        wx.switchTab({
          url: '/pages/home/home'
        })
        
      }
    })
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad(options) {
//获取本地缓存是否已经有user信息 如果有，那么直接跳转到首页
wx.getStorage({
  key: 'usermsg',
  success (res) {
  //跳转到首页
  wx.switchTab({
  url: '/pages/home/home'
  })
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