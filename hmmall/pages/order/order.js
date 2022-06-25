// pages/order/order.js
const app = getApp()
const {formatTime,getRandomOrderNum}=require("../../utils/util")
Page({

  /**
   * 页面的初始数据
   */
  data: {
    //订单号
      order_num:0,
    //下单时间
     order_time:"",
     //支付状态  0未下单  1已下单 
     order_status:0,
     //商品信息
     goodsList:[],
     //支付金额
     order_price:0,
     //收件人地址
     order_adr:"",
     //收件人姓名
     order_name:"",
     //收件人电话
     order_phone:"",
     //订单列表
     orderList:[],
     //商品信息
     orderInfo:[],
     httpUrl:"http://localhost",
     //用户id
     userId:0
     
  },
  //支付失败操作，跳转回购物车
  gotoPayFail(){
       wx.switchTab({
         url: '/pages/cart/cart',
       });
       wx.showToast({
         title: '支付失败',
         icon:'error',
         duration:2000
       })
  },
//支付成功后的操作，改变状态，清空购物车，添加订单，跳转
//该函数在后端应该保证原子性
gotoPaySuccess(){
  var that=this;
   wx.request({
     url: app.globalData.serverurl+'/orders/addorder',
     method:"POST",
     header: {
      "Content-Type": "application/x-www-form-urlencoded"
      },
      data:{
        address:that.data.address.all,
        name:that.data.address.userName,
        phone:that.data.address.telNumber,
        userid:that.data.userId
      }
      ,
      success(res){
        console.log(res);
        wx.showToast({
          title: '下单成功！',
          duration:2000,
          success(){
            setTimeout(function(){
              wx.switchTab({
                url: '/pages/cart/cart',
              })
            },2000);
          }
        });
        
        
      },
      fail(){
        console.log("添加订单请求失败");
      }
   })

  
  // //改变订单状态
  //     this.setData({
  //       order_status:1
  //     });
  //     //清空购物车
  //     wx.removeStorage({
  //       key: 'cartList',
  //       success(){
  //         console.log("清空购物车成功");
  //       //添加订单数据
  //         wx.getStorage({
  //           key:'orderList',
  //           //如果本地数据中有orderList，取出数据赋值给orderList,然后更新orderList再存入本地数据
  //           success(res){
  //             that.setData({
  //               orderList:res.data
  //             });
  //             var obj={
  //               order_num:that.data.order_num,
  //               order_time:that.data.order_time,
  //               order_status:that.data.order_status,
  //               goodsList:that.data.goodsList,
  //               order_adr:that.data.order_adr,
  //               order_name:that.data.order_name,
  //               order_phone:that.data.order_phone,
  //               order_price:that.data.order_price
  //             };
  //             var list=[];
  //             for(var i=0;i<that.data.orderList.length;i++){
  //               var obj2={
  //                 order_num:that.data.orderList[i].order_num,
  //                 order_time:that.data.orderList[i].order_time,
  //                 order_status:that.data.orderList[i].order_status,
  //                 goodsList:that.data.orderList[i].goodsList,
  //                 order_adr:that.data.orderList[i].order_adr,
  //                 order_name:that.data.orderList[i].order_name,
  //                 order_phone:that.data.orderList[i].order_phone,
  //                 order_price:that.data.orderList[i].order_price
  //               }
  //               list.push(obj2);
  //             }
  //             list.push(obj);
  //              that.setData({
  //                orderList:list
  //              });
  //              wx.setStorage({
  //                key:'orderList',
  //                data:that.data.orderList,
  //                success(){
  //                  console.log("下单成功");
  //                  wx.redirectTo({
  //                    url: '/pages/orders/orders',
  //                  })
  //                }
  //              });

  //           },
  //           //如果本地数据没有orderList，直接new一个数组然后存入本地数据
  //           fail(){
  //                var arr=[{
  //                  order_num:that.data.order_num,
  //                  order_time:that.data.order_time,
  //                  order_status:that.data.order_status,
  //                  goodsList:that.data.goodsList,
  //                  order_adr:that.data.order_adr,
  //                  order_name:that.data.order_name,
  //                  order_phone:that.data.order_phone,
  //                  order_price:that.data.order_price
  //                }];
  //                wx.setStorage({
  //                  key:'orderList',
  //                  data:arr,
  //                  success(){
  //                    console.log("订单添加成功");
  //                    wx.showToast({
  //                      title: '下单成功',
  //                    });
  //                    //sleep 1000
  //                    wx.navigateTo({
  //                      url: '/pages/orders/orders',
  //                    })
  //                  }
  //                })
  //           }
  //         })
  //       },
  //       fail(){
  //        console.log("清空失败");
  //       }
  //     })
},

  //获取收件人电话
  changgephone(e){
    this.setData({
      order_phone:e.detail.value
    })
  },
  //获取收件人姓名
  changgename(e){
    this.setData({
      order_name:e.detail.value
    })
  },
 //获取收件人地址
  changgeaddress(e){
    this.setData({
      order_adr:e.detail.value
    })
  },
   //初始化数据
   initdata(){
     var that=this;
     wx.getStorage({
       key:'sumPrice',
       success(res){
         that.setData({
          order_price:res.data
         })
       }
     });
     wx.getStorage({
       key:'userId',
       success(res){
         //从本地获取用户ID
            that.setData({
              userId:res.data
            });
         //通过用户ID查询购物车信息
         wx.request({
           url: that.data.httpUrl+'/cartlist/querycart?userid='+that.data.userId,
           method:"GET",
           success(res){
            that.setData({
              goodsList:res.data.goods,
            });
           }
         })
       }

     })

       const address = wx.getStorageSync('address');
       this.setData({
        address
      })
   },
  /**
   * 生命周期函数--监听页面加载
   */
  onLoad(options) {
      this.initdata();
      // this.getdata();
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

  },
  
})