package com.lv.service;

import com.lv.pojo.User;

public interface UserService {

    //登录
    public User getLoginUser(String userCode, String password);

    //修改用户密码
    public boolean updatePwd(int id,String userPassword);

}
