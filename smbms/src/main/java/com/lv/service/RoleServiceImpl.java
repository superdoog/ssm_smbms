package com.lv.service;

import com.lv.mapper.RoleMapper;
import com.lv.pojo.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    @Qualifier("roleMapper")
    private RoleMapper roleMapper;

    public void setRoleMapper(
            RoleMapper roleMapper) { this.roleMapper = roleMapper;
    }

    @Override
    public List<Role> getRoleList() {
        List<Role> roleList = null;
        try {
            roleList = roleMapper.getRoleList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return roleList;
    }



}
