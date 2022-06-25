// subpkg/goods_detail/goods_detail.js
const { getGoodsDetail } = require("../../utils/apis");
const app = getApp();
Page({

  /**
   * 页面的初始数据
   */
  data: {
    goods_id: '',
    goods_info: '',
    cartList:[],
    userId:'',
    httpUrl:"http://localhost",
  },
  

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad(options) {
    this.setData({goods_id: options.goods_id});
    this.initData();
  },
//前往购买单个商品页面
gotoOrderOne(){
  var goodsInfo=this.data.goods_info
   wx.navigateTo({
     url: '/pages/orderone/orderone?goods_info='+goodsInfo,
   })
},
//将该商品加入收藏
addToCollect(){
  console.log("点击了加入购物车...");
  var that=this;
        wx.getStorage({
          key:'collectList',
          success(res){
            console.log("数据获取成功");
            that.setData({
              collectList:res.data
            })
            console.log(that.data.collectList);
            var obj={
              goods_id:that.data.goods_id,
              goods_name:that.data.goods_info.goods_name,
              goods_price:that.data.goods_info.goods_price,
              goods_small_logo:that.data.goods_info.goods_small_logo,
            };
            var list=[];
            for(var i=0;i<that.data.collectList.length;i++){
              var obj2={
                goods_id:that.data.collectList[i].goods_id,
                goods_name:that.data.collectList[i].goods_name,
                goods_price:that.data.collectList[i].goods_price,
                goods_small_logo:that.data.collectList[i].goods_small_logo,
               
              }
              console.log("list");
              console.log(list);
              list.push(obj2);
            }
            list.push(obj);
             that.setData({
             collectList:list
             });
          
              console.log("添加数据成功");
            console.log(that.data.collectList);
            wx.setStorage({
              key:'collectList',
              data:that.data.collectList,
              success(res){
              wx.showToast({
                title: '收藏成功',
              })
              }
            })
          },
          fail(){
            console.log("用户购物车为空，正在生成购物车数据...");
            //如果没有获取到本地的购物车数据（本地没有cartList）
            var obj={
              goods_id:that.data.goods_id,
              goods_name:that.data.goods_info.goods_name,
              goods_price:that.data.goods_info.goods_price,
              goods_small_logo:that.data.goods_info.goods_small_logo,
              goods_num:1
            };
    
          


           wx.setStorage({
             key:'collectList',
             data:[obj],
             success(res){
               console.log("购物车数据生成成功");
            wx.showToast({
              title: '收藏成功',
            })
            }
           })
          }
          
        });
        
       
},

 // 进入页面, 初始化数据
 initData(){
  var params = {
    goods_id: this.data.goods_id
  }
  getGoodsDetail(params, res=>{
    this.setData({ goods_info: res.message });
  });
  var that=this;
  wx.getStorage({
    key:'userId',
    success(res){
         
           that.setData({
             userId:res.data
           })
    }
  })
},
//加入购物车 并跳转
addToCart(){
        wx.request({
          url: app.globalData.serverurl+'/cartlist/addgoods',
          method:"post",
          header: {
            "Content-Type": "application/x-www-form-urlencoded"
            },
          data:{
              userid:this.data.userId,
              goodsid:this.data.goods_id
          },
          success(res){
            wx.switchTab({
              url: '../../pages/cart/cart',
            })
          }
        })

  // console.log("点击了加入购物车...");
  // var that=this;
  //       wx.getStorage({
  //         key:'cartList',
  //         success(res){
  //           console.log("数据获取成功");
  //           that.setData({
  //             cartList:res.data
  //           })
  //           console.log(that.data.cartList);
  //           var obj={
  //             goods_id:that.data.goods_id,
  //             goods_name:that.data.goods_info.goods_name,
  //             goods_price:that.data.goods_info.goods_price,
  //             goods_small_logo:that.data.goods_info.goods_small_logo,
  //             goods_num:1
  //           };
  //           var list=[];
  //           for(var i=0;i<that.data.cartList.length;i++){
  //             var obj2={
  //               goods_id:that.data.cartList[i].goods_id,
  //               goods_name:that.data.cartList[i].goods_name,
  //               goods_price:that.data.cartList[i].goods_price,
  //               goods_small_logo:that.data.cartList[i].goods_small_logo,
  //               goods_num:that.data.cartList[i].goods_num
  //             }
  //             console.log("list");
  //             console.log(list);
  //             list.push(obj2);
  //           }
  //           list.push(obj);
  //            that.setData({
  //              cartList:list
  //            });
          
  //             console.log("添加数据成功");
  //           console.log(that.data.cartList);
  //           wx.setStorage({
  //             key:'cartList',
  //             data:that.data.cartList,
  //             success(res){
  //               wx.switchTab({
  //                 url: '/pages/cart/cart',
  //               })
  //             }
  //           })
  //         },
  //         fail(){
  //           console.log("用户购物车为空，正在生成购物车数据...");
  //           //如果没有获取到本地的购物车数据（本地没有cartList）
  //           var obj={
  //             goods_id:that.data.goods_id,
  //             goods_name:that.data.goods_info.goods_name,
  //             goods_price:that.data.goods_info.goods_price,
  //             goods_small_logo:that.data.goods_info.goods_small_logo,
  //             goods_num:1
  //           };
  //          wx.setStorage({
  //            key:'cartList',
  //            data:[obj],
  //            success(res){
  //              console.log("购物车数据生成成功");
  //             wx.switchTab({
  //               url: '/pages/cart/cart',
  //             })
  //           }
  //          })
  //         }
          
  //       });
        
        // var obj={
        //   goods_id:this.data.goods_id,
        //   goods_name:this.data.goods_info.goods_name,
        //   goods_price:this.data.goods_info.goods_price,
        //   goods_small_logo:this.data.goods_info.goods_small_logo
        // };
        // var list=[];
        // for(var i=0;i<that.data.cartList.length;i++){
        //   var obj2={
        //     goods_id:cartList[i].goods_id,
        //     goods_name:cartList[i].goods_name,
        //     goods_price:cartList[i].goods_price,
        //     goods_small_logo:cartList[i].goods_small_logo
        //   }
        //   console.log("list");
        //   console.log(list);
        //   list.push(obj2);
        // }
        // list.push(obj);
        //  that.setData({
        //    cartList:list
        //  });
      
        //   console.log("添加数据成功");
        // console.log(that.data.cartList);
        // wx.setStorage({
        //   key:'cartList',
        //   data:that.data.cartList
        // })
      //  wx.switchTab({
      //    url: '/pages/cart/cart',
      //  })
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