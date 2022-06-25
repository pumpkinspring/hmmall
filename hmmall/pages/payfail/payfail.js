// pages/payfail/payfail.js
Page({

  /**
   * 页面的初始数据
   */
  data: {
           cid:10,
           hmurl:"https://api-hmugo-web.itheima.net/api/public/v1/goods/search?cid=",
           list:[],
           index:0

           
  },
      
  /**
   * 生命周期函数--监听页面加载
   */
  onLoad(options) {

  },
  changecid(e){
    
      this.setData({
        cid:e.detail.value
      })
      console.log(this.data.cid);
    
  },
  add(){
     var c=this.data.cid+1;
     this.setData({
       cid:c
     })
  },
  zero(){
this.setData({
  index:0
})
  },
  //获取数据
  getdata(){
    var that=this;
       wx.request({
         url: this.data.hmurl+this.data.cid,
         success(res){
           console.log("获取数据");
           console.log(res);
           that.setData({
             list:res.data.message.goods
           })
         }
       })
  },
  //向数据库插入数据
  setdata1(){
    var index=this.data.index;
       wx.request({
         url: 'http://localhost:8080/hmmall/goods/add',
         method:'POST',
         header: {
          "Content-Type": "application/x-www-form-urlencoded"
          },
          data:{
               goodsId:this.data.list[this.data.index].goods_id,
               goodsName:this.data.list[this.data.index].goods_name,
               catId:this.data.list[this.data.index].cat_id,
               goodsNumber:this.data.list[this.data.index].goods_number,
               goodsSmallLogo:this.data.list[this.data.index].goods_small_logo,
               goodsBigLogo:this.data.list[this.data.index].goods_big_logo,
               goodsPrice:this.data.list[this.data.index].goods_price

          },
          success(res){
            console.log(res);
          }

       })
  },
  setdatas(){
    
      
    for(var i=0;i<this.data.list.length;i++){
      this.setdata1();
      this.data.index++;
      
    

  }
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