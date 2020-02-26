package com.lv.service;

import com.lv.mapper.UserMapper;
import com.lv.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    @Qualifier("userMapper")
    private UserMapper userMapper;

    public void setUserMapper(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    //登录验证
    @Override
    public User getLoginUser(String userCode, String password) {
        User user = null;
        try {
            user = userMapper.getLoginUser(userCode);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }

    //修改用户密码
    @Override
    public boolean updatePwd(int id, String userPassword) {
        boolean flag = false;
        try {
            flag = userMapper.updatePwd(id, userPassword);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }

    //查询用户数量
    @Override
    public int getUserCount(String username, int userRole) {
        int count = 0;
        try {
            count = userMapper.getUserCount(username,userRole);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return count;
    }

    //通过条件查询用户列表
    @Override
    public List<User> getUserList(String username, int userRole, int currentPage, int pageSize) {
        List<User> userList = null;
        //换算索引
        int currentPageNo = (currentPage-1)*pageSize;
        try {
            userList = userMapper.getUserList(username, userRole, currentPageNo, pageSize);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return userList;
    }
}
