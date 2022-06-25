//获取当前时间
const formatTime = date => {
  const year = date.getFullYear()
  const month = date.getMonth() + 1
  const day = date.getDate()
  const hour = date.getHours()
  const minute = date.getMinutes()
  const second = date.getSeconds()

  return `${[year, month, day].map(formatNumber).join('/')} ${[hour, minute, second].map(formatNumber).join(':')}`
}

//获取订单号
const getRandomOrderNum = date => {
  const year = date.getFullYear()
  const month = date.getMonth() + 1
  const day = date.getDate()
  const hour = date.getHours()
  const minute = date.getMinutes()
  const second = date.getSeconds()

  return `${[year, month, day].map(formatNumber).join('')} ${[hour, minute, second].map(formatNumber).join('')}`
}

const formatNumber = n => {
  n = n.toString()
  return n[1] ? n : `0${n}`
}


const getSetting = () => {
  return new Promise((resolve, reject) => {
    wx.getSetting({
      success: resolve,
      fail: reject,
    });
  })
}
const openSetting = () => {
  return new Promise((resolve, reject) => {
    wx.openSetting({
      success: resolve,
      fail: reject,
    });
  })
}
const chooseAddress = () => {
  return new Promise((resolve, reject) => {
    wx.chooseAddress({
      success: resolve,
      fail: reject
    });
  })
}
module.exports = {
  formatTime,
  getRandomOrderNum,
  getSetting,
  openSetting,
  chooseAddress
}
