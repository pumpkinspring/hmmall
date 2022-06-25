package com.whx.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.whx.config.HttpClientUtil;
import com.whx.config.ResponseData;
import com.whx.config.StringUtil;
import com.whx.entity.User;
import com.whx.dao.UserDao;
import com.whx.service.UserService;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import javax.annotation.Resource;

/**
 *  (User)表服务实现类
 *
 * @author makejava
 * @since 2022-06-21 11:34:26
 */
@Service("userService")
public class UserServiceImpl implements UserService {
    @Resource
    private UserDao userDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public User queryById(Integer id) {
        return this.userDao.queryById(id);
    }

    /**
     * 分页查询
     *
     * @param user 筛选条件
     * @param pageRequest      分页对象
     * @return 查询结果
     */
    @Override
    public Page<User> queryByPage(User user, PageRequest pageRequest) {
        long total = this.userDao.count(user);
        return new PageImpl<>(this.userDao.queryAllByLimit(user, pageRequest), pageRequest, total);
    }

    /**
     * 新增数据
     *
     * @param user 实例对象
     * @return 实例对象
     */
    @Override
    public User insert(User user) {
        this.userDao.insert(user);
        return user;
    }

    /**
     * 修改数据
     *
     * @param user 实例对象
     * @return 实例对象
     */
    @Override
    public User update(User user) {
        this.userDao.update(user);
        return this.queryById(user.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer id) {
        return this.userDao.deleteById(id) > 0;
    }

    //注册

    @Override
    public ResponseData userRegister(User user) {
        String phone=user.getPhone();
        String password=user.getPassword();
        String username=user.getUsername();
        //非空校验
        if(StringUtil.isNull(phone)){
            return new ResponseData("9001","手机号为空");
        }
        if(StringUtil.isNull(password)){
            return new ResponseData("9002","密码为空");

        }
        if(StringUtil.isNull(username)){
            return new ResponseData("9003","用户名为空");

        }
        try {


        //校验用户是否存在
        //根据username查询user表 若有数据 则存在
        User queryUser = userDao.queryUserByUserName(username);
          if(queryUser!=null){
              return new ResponseData("9004","用户名已经存在");
          }
          //加密 source：加密的资源 salt：盐值 hashIterations：加密次数
        Md5Hash md5Hash=new Md5Hash(password,"moximoxi",10);
          //加密后的密码
          String newPassword=md5Hash.toString();
          user.setPassword(newPassword);
          user.setUserId(Long.valueOf(phone));
          userDao.insert(user);
          return new ResponseData("0","请求成功");
        }catch (Exception e ){
            System.out.println(e);
            return new ResponseData("9999","网络异常");
        }

    }
    /*
    * 登录
    * */

    @Override
    public ResponseData userLogin(String phone, String password, String code) {
        if(StringUtil.isNull(phone)){
            return new ResponseData("9001","手机号为空");

        }
        if(StringUtil.isNull(password)){
            return new ResponseData("9002","密码为空");

        }
        Md5Hash md5Hash=new Md5Hash(password,"moximoxi",10);
        String newPassword=md5Hash.toString();
        //根据手机号和密码查询用户信息
        User user=userDao.queryUserByPhoneAndPwd(phone,newPassword);
        if(user==null){
            return new ResponseData("9005","账号密码不匹配");
        }
        try {
            //4.调用微信的接口 请求方式 get 模拟一个get请求
//https://api.weixin.qq.com/sns/jscode2session?appid=APPID&secret=SECRET&js_code=JSCODE&grant_type=authorization_code
            String url = "https://api.weixin.qq.com/sns/jscode2session?appid=wxe46984039966f955&secret=dd1dc991f645bfcc5b7146f221fd0367&js_code="+code+"&grant_type=authorization_code";
            String result = HttpClientUtil.doGet(url);//返回一个字符串包含session_key 和 openid
            System.out.println("result = "+result);
//5.获取session_key 和 openid 字符串 转 json
            JSONObject jsonResult = (JSONObject)JSONObject.parse(result);
            String session_key = (String)jsonResult.get("session_key");
            String openid = (String)jsonResult.get("openid");
            System.out.println("session_key = "+session_key);
            System.out.println("openid = "+openid);
//6.生成自定义登录状态
            Md5Hash md5Hash1 = new Md5Hash(session_key,openid,10);
            String token = md5Hash1.toString();
//7.保存(跟新) user token、session_key openid 根据 id去跟新
            user.setOpenid(openid);
            user.setSessionkey(session_key);
            user.setToken(token);
            userDao.update(user);
            return new ResponseData("0","请求成功",user);


        }catch (Exception e){
           return new ResponseData("9999","网络异常");
        }

    }

}
