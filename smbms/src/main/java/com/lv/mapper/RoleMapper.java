package com.lv.mapper;

import com.lv.pojo.Role;

import java.util.List;

public interface RoleMapper {

    //获取角色列表
    public List<Role> getRoleList() throws Exception;
}
