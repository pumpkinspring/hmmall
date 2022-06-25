// components/order_detail.js
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

  },

  /**
   * 组件的初始数据
   */
  data: {
    httpUrl:"http://localhost",
  },

  /**
   * 组件的方法列表
   */
  methods: {
      deleteorder:function(){
        var that=this;
        wx.request({
          url: app.globalData.serverurl+'/orders/deleteorder',
          method:'DELETE',
          header: {
            "Content-Type": "application/x-www-form-urlencoded"
            },
            data:{
              orderId:this.data.item.orderId
            },
            success(res){
              console.log(res);
              if(res.data.code==0){
                    that.setData({

                    })
              }
            }
        })
      }
  }
})
