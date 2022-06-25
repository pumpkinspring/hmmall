const baseUrl="https://api-hmugo-web.itheima.net";
// 6. 商品详情页面  获取商品信息
function getGoodsDetail(params = {}, callback){
  wx.showLoading({
    title: '数据加载中...'
  });
  wx.request({
    url: baseUrl + '/api/public/v1/goods/detail',
    data: params,
    success: (res)=>{
      if(res.statusCode===200 && res.data.meta.status===200){
        callback && callback(res.data);
      }else{
        wx.showToast({
          title: '数据请求失败！',
          icon: 'none'
        });
      };
      wx.hideLoading();
    }
  });
}

// 5. 商品列表页面  搜索商品数据
function getGoodsList(params = {}, callback){
  wx.showLoading({
    title: '数据加载中...'
  });
  wx.request({
    url: baseUrl + '/api/public/v1/goods/search',
    data: params,
    success: (res)=>{
      if(res.statusCode===200 && res.data.meta.status===200){
        callback && callback(res.data);
      }else{
        wx.showToast({
          title: '数据请求失败！',
          icon: 'none'
        });
      };
      wx.hideLoading();
    }
  });
}

//获取分类中的分页数据
function getCateList(callback){
  wx.showLoading({
    title: '数据加载中...'
  });
  wx.request({
    url: baseUrl + '/api/public/v1/categories',
    success: (res)=>{
      if(res.statusCode===200 && res.data.meta.status===200){
        callback && callback(res.data);
      }else{
        wx.showToast({
          title: '数据请求失败！',
          icon: 'none'
        });
      };
      wx.hideLoading();
    }
  });
}

// 3. 首页获取楼层数据
function getFloorList(callback){
  wx.showLoading({
    title: '数据加载中...'
  });
  wx.request({
    url: baseUrl + '/api/public/v1/home/floordata',
    success: (res)=>{
      if(res.statusCode===200 && res.data.meta.status===200){
        callback && callback(res.data);
      }else{
        wx.showToast({
          title: '数据请求失败！',
          icon: 'none'
        });
      };
      wx.hideLoading();
    }
  });
}

function getSwiperList(callback){
  //请求前显示load效果
 
  wx.showLoading({
    title: '数据加载中',
  })
  
wx.request({
  url: baseUrl+"/api/public/v1/home/swiperdata",
  method:"GET",
  success:(res)=>{
    console.log(res);
       if(res.statusCode==200&&res.data.meta.status==200)
   { //如果callback存在，那么执行callback
     callback&&callback(res.data.message)
   }else{
     wx.showToast({
       title: '数据请求失败',
       icon:"none"
     })
   }
    }
});
wx.hideLoading({
  success: (res) => {},
});

}
function getNavList(callback){
  wx.showLoading({
    title: '数据加载中...'
  });
  wx.request({
    url: baseUrl + '/api/public/v1/home/catitems',
    success: (res)=>{
      if(res.statusCode===200 && res.data.meta.status===200){
        callback && callback(res.data);
      }else{
        wx.showToast({
          title: '数据请求失败！',
          icon: 'none'
        });
      };
      wx.hideLoading();
    }
  });
}

function getWebData(callback,url){
  //请求前显示load效果
 
  wx.showLoading({
    title: '数据加载中',
  })
  
wx.request({
  url: baseUrl+url,
  method:"GET",
  success:(res)=>{
    console.log(res);
       if(res.statusCode==200&&res.data.meta.status==200)
   { //如果callback存在，那么执行callback
     callback&&callback(res.data.message)
   }else{
     wx.showToast({
       title: '数据请求失败',
       icon:"none"
     })
   }
    }
});
wx.hideLoading({
  success: (res) => {},
});

}

module.exports={
  getSwiperList,
  getWebData,
  getNavList,
  getFloorList,
  getCateList,
  getGoodsList,
  getGoodsDetail
}