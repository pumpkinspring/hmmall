// components/cart_goods/cart_goods.js
const app = getApp()
Component({
  /**
   * 组件的属性列表
   */
  properties: {
    item: {
      type: Object,
      value: {}
    },
    defaultPic:"https://img3.doubanio.com/f/movie/8dd0c794499fe925ae2ae89ee30cd225750457b4/pics/movie/celebrity-default-medium.png"
  },
  

  /**
   * 组件的初始数据
   */
  data: {
     num:0,
     cartList:[],
     goods_id:0,
     httpUrl:"http://localhost",
     userId:0
  },
  lifetimes:{
    attached: function() {
      var that=this;
      // 在组件实例进入页面节点树时执行
      this.setData({
        num:this.properties.item.goodsNum,
        goods_id:this.properties.item.goodsId
      }),
      wx.getStorage({
        key:'userId',
        success(res){
        that.setData({
          userId:res.data
        })
      }
      })
    },
  },

  /**
   * 组件的方法列表
   */
  methods: {
    //删除购物车数据
      deletecart:function(){
        var that=this;
           wx.request({
             url: app.globalData.serverurl+'/cartlist/deletecart',
             method:'DELETE',
             header: {
              "Content-Type": "application/x-www-form-urlencoded"
              },
              data:{
               goodsid:this.data.goods_id,
               userid:this.data.userId
              },
              success(res){
               wx.showToast({
                 title: '删除成功',
                 success(res){
                  setTimeout(function () {
                   wx.switchTab({
                     url: '../../pages/home/home',
                   })
                   }, 1000) //延迟时间 这里是1秒
                 }
               })
               
                console.log(res);
              }
           })
      },
    //修改num数量
       changeNum:function(e){
       
         var that=this;
         console.log(e);
         this.setData({
           num:e.detail.value,
           goods_id:this.properties.item.goodsId
         })

            // wx.getStorage({
            //   key:'cartList',
            //   success(res){
            //     that.setData({
            //       cartList:res.data
            //     });
            //     for(var i=0;i<that.data.cartList.length;i++){
            //       if(that.data.cartList[i].goods_id==that.properties.item.goods_id){
            //         that.data.cartList[i].goods_num=that.data.num;
            //         that.properties.item.goods_num=that.data.num;
            //         console.log("修改数量");
            //         console.log(that.properties.item.goods_id);
            //         console.log(that.data.cartList[i].goods_id);
            //         console.log(that.data.cartList);
            //       }
            //     }
            //     //这里已经改变
            //     //console.log(that.data.cartList);
            //     wx.setStorage({
            //       key:'cartList',
            //       data:that.data.cartList
            //     })
            //   }
            // })
       },
       //将num提交后端
       changeNumto:function(e){
         var that=this;      
         wx.getStorage({
           key:'userId',
           success(res){
                that.setData({
                  userid:res.data
                });
                wx.request({
                  url: app.globalData.serverurl+'/cartlist/changeNum',
                  method:"PUT",
                  header: {
                    "Content-Type": "application/x-www-form-urlencoded"
                    },
                  data:{ 
                    userid:that.data.userid,                  
                   goodsid:that.data.goods_id,   
                   num:that.data.num
                  },
                  success(res){
                    console.log(res);
                    wx.showToast({
                      title: '修改数量成功',
                    });
                   
                  },
                  fail(){
                    console.log("修改数量失败");
                  }
                })
           }
         });
         
       }
  }
})
