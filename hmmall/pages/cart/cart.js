// pages/cart/cart.js
const { getSetting,
  openSetting,
  chooseAddress}=require("../../utils/util")
  const app = getApp()
Page({
  /**
   * 页面的初始数据
   */
  data: {
    cartList:[
      
    ],
    sumPrice:0,
    userId:'',
    httpUrl:"http://localhost",
  },
  //获取pump服务器购物车信息
  getCartDataFromPump(){
    var that=this;
    wx.getStorage({
      key:'userId',
      success(res){
        that.setData({
          userId:res.data
        });
        wx.request({
          url: app.globalData.serverurl+'/cartlist/querycart?userid='+that.data.userId,
          success(res){
              console.log(res.data);
              that.setData({
                cartList:res.data.goods,
              });
              var price=0;
              for(var i=0;i<that.data.cartList.length;i++){
                price+=that.data.cartList[i].goodsPrice*that.data.cartList[i].goodsNum;
              }
              that.setData({
                sumPrice:price
              })
          },
          fail(){
            console.log("获取失败！！！！！");
          }
        })

      }
    })
    
  } ,



  //获取添加地址
  addCity() {
    var that=this;

    //获取当前位置信息
    getSetting().then(res => {
      console.log(res);
      const scope = res.authSetting["scope.address"];
      console.log(scope);
      if (scope === false) {
        openSetting().then(res => {
          console.log(res);
        })
        return;
      }
      chooseAddress().then(res => {
        console.log();
         console.log(res);
        let address = res;
        address.all = address.provinceName + address.cityName + address.countyName + address.detailInfo;
        wx.setStorageSync('address', address);
       
      })
    });
    
   
   
},

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad(options) {
    
      console.log("--------------------------------");
      this.getCartDataFromPump();
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
    this.getCartDataFromPump();
    // console.log("购物车显示");
    // var that=this;
    // wx.getStorage({
    //   key:'cartList',
    //   success(res){
    //        that.setData({
    //          cartList:res.data
    //        });
    //        that.getSumPrice();
    //        console.log(that.data.cartList);
    //   },
    //   fail(){
    //     that.setData({
    //       cartList:[],
    //       sumPrice:0
    //     })
    //   }
    // })
    const address = wx.getStorageSync('address');
    const cart = wx.getStorageSync('cart') || [];
    this.setData({
      address,
    })
  },

  /**
   * 生命周期函数--监听页面隐藏
   */
  onHide() {
    console.log("购物车页面隐藏");
    // var that=this;
    // wx.setStorage({
    //   key:"cartList",
    //   data:that.data.cartList
    // })
  },

  /**
   * 生命周期函数--监听页面卸载
   */
  onUnload() {
    var that=this;
    // console.log("购物车页面卸载");
    //   wx.setStorage({
    //     key:"cartList",
    //     data:that.data.cartList
    //   })
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

  },
  getSumPrice(){
    var price=0;
    for(var i=0;i<this.data.cartList.length;i++){
      price+=this.data.cartList[i].goods_price*this.data.cartList[i].goods_num;
    }
    this.setData({
      sumPrice:price
    })
  },
  //跳转到下单界面
  gotoOrder(){
    var that=this;
    wx.setStorage({
      key:'sumPrice',
      data:this.data.sumPrice,
      success(){
         wx.navigateTo({
          url: '/pages/order/order',
         })
      },
      fail(){
        wx.showToast({
          title: '网络繁忙',
          icon:'error',
          duration:2000
        })
      }
    })

  }
})