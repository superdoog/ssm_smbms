package com.lv.mapper;

import com.lv.pojo.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserMapper {

    //登录
    public User getLoginUser(String userCode) throws Exception;

    //插入用户

    //删除用户

    //修改用户密码
    boolean updatePwd(@Param("id") int id, @Param("userPassword") String userPassword) throws Exception;

    //通过Id查询用户

    //查询用户数量
    public int getUserCount(@Param("username") String username, @Param("userRole") int userRole) throws Exception;


    //通过条件查询用户列表
    public List<User> getUserList(@Param("username") String username, @Param("userRole") int userRole, @Param("currentPage") int currentPage, @Param("pageSizeNo") int pageSize) throws Exception;

}
