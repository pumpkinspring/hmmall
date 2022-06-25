// pages/register/register.js
const app = getApp()
Page({

  /**
   * 页面的初始数据
   */
  data: {
    httpUrl:"http://localhost",
  },
  // 跳转登录页面
toLogin:function(){
  wx.navigateTo({
  url: '/pages/login/login',
  })
  },
  //提交表单
  formSubmit:function(e){
  var that = this;
  //e.detail.value为获取用户输入的所有数据
  console.log(e.detail.value);
  //1.获取本地缓存的user信息
  wx.getStorage({
  key: 'usermsg',
  success (res) {
    console.log(res);
  //res.data用户信息
//2.发起请求 注册 post请求
wx.request({
  url: app.globalData.serverurl+'/user/register', //仅为示例，并非真实的接口地址
  method:"post",
  data: {
  nickname: res.data.userInfo.nickName,
  imageurl: res.data.userInfo.avatarUrl,
  username:e.detail.value.username,
  password:e.detail.value.password,
  phone:e.detail.value.phone
  },
  header: {
  "Content-Type": "application/x-www-form-urlencoded"

  },
  success (res) {
  //res.data返回的值
  console.log(res.data)
  if(res.data.code==0){//表示成功
  //需要跳转到登录页面
  wx.showToast({
    title:"成功",
    duration:2000
  });
  setTimeout(function () {
    wx.navigateTo({
    
      url: '/pages/login/login',
    })   
   }, 2000);
  }else{
  //失败 显示提示信息 wx.showToast()用于显示提示框
  wx.showToast({
  title: res.data.msg,//消息
  icon: 'error', //图标
  duration: 2000//延迟时间
  })
  }
  }
  })
  }
  })
  },
  

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad(options) {

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