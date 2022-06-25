// pages/mine/mine.js
const app = getApp()
Page({

  /**
   * 页面的初始数据
   */
  data: {
    //存储用户信息
    usermsg:{},
  userInfo:{},
  isData:true,
  httpUrl:"http://localhost",
  
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad(options) {
this.getUserData();
this.gettoken();
  },
  getUserData(){
    //获取本地用户数据
var that=this;
wx.getStorage({
  key:'usermsg',
  success(res){
    console.log(res.data);
    that.setData({
      usermsg:res.data,
      userInfo:res.data.userInfo
    })
  }
})
  },
  //点击注册时，跳转到注册页面
toRegister: function () {
  wx.navigateTo({
  url: '/pages/register/register',
  })
  },
  //点击登录时跳转到登录
  toLogin:function(){
        wx.navigateTo({
          url: '/pages/login/login',
        })

  },
  //每次切换tabbar时触发
  onTabItemTap(e){
    console.log("eeee",e);
    var that = this;
    //2.获取本地缓存是否存在token
    wx.getStorage({
    key: 'token',
    success: function (res) {
    that.setData({
    isData: false
    })
    },
    })
    },
  clearuserinfo(){
    var that=this;
    if(this.data.isData==false){
          wx.removeStorage({
            key: 'token',  
            success(){
              wx.removeStorage({
                key: 'userId',
                success(){
                  console.log("清除数据");
                  that.setData({
                    isData:true
                  })
                  wx.showToast({
                    title: '已退出',
                  })
                
                }
              })
            }       
          });
        }else{
          wx.showToast({
            title: '您还没有登录',
            icon:'error'
          })
        }
  }
  ,
  // 获取用户token
  gettoken(){
  
    wx.getStorage({
      key:'token',
      success:(res)=>{
        this.setData({
          token:res.data
        })
        console.log(this.data.token);
      }
    })
  },
  //跳转到历史订单页面
  gotoOrders(){
      wx.navigateTo({
        url:'/pages/orders/orders'
      })
  },
  tests(){
    var i=1
     wx.request({
       url:app.globalData.serverurl+"/cartlist/changeNum",
       method:"PUT",
       data:{
         userid:1,
         goodsid:2004,
         num:1,
       },
       success(res){
         console.log(res);
       }
     })
  },
  gototestfal(){
   wx.navigateTo({
     url: '/pages/payfail/payfail',
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