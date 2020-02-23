package com.lv.mapper;

import com.lv.pojo.User;
import org.apache.ibatis.annotations.Param;

public interface UserMapper {

    //登录
    public User getLoginUser(String userCode) throws Exception;

    //插入用户

    //删除用户

    //修改用户密码
    boolean updatePwd(@Param("id") int id, @Param("userPassword") String userPassword) throws Exception;

    //通过Id查询用户


}
