package com.lv.service;

import com.lv.pojo.User;

import java.util.List;

public interface UserService {

    //登录
    public User getLoginUser(String userCode, String password);

    //修改用户密码
    public boolean updatePwd(int id, String userPassword);

    //查询用户数量
    public int getUserCount(String username, int userRole);

    //通过条件查询用户列表
    public List<User> getUserList(String username, int userRole, int currentPageNo, int pageSize);


}
