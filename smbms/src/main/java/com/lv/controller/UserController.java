package com.lv.controller;

import com.alibaba.fastjson.JSONArray;
import com.lv.pojo.Role;
import com.lv.pojo.User;
import com.lv.service.RoleServiceImpl;
import com.lv.service.UserServiceImpl;
import com.lv.utils.Constants;
import com.lv.utils.PageSupport;
import com.mysql.cj.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    @Qualifier("userServiceImpl")
    private UserServiceImpl userService;

    @Autowired
    @Qualifier("RoleServiceImpl")
    private RoleServiceImpl roleService;

    //登录
    @RequestMapping("/dologin.html")
    public String login(Model model, @RequestParam("userCode") String userCode, @RequestParam("userPassword") String userPassword, HttpSession session) {
        User user = userService.getLoginUser(userCode, userPassword);
        if (user != null) { //用户存在
            //用户密码正确
            if (user.getUserPassword().equals(userPassword)) {
                session.setAttribute(Constants.USER_SESSION, user);
                return "redirect:/user/main.html";
            } else {//密码错误
                model.addAttribute("error", "密码错误!");
            }
        } else { //用户不存在
            model.addAttribute("error", "用户名不存在！");
        }
        return "login";
    }

    @RequestMapping("/main.html")
    public String main(HttpSession session) {
        if (session.getAttribute(Constants.USER_SESSION) == null) {
            return "redirect:/user/login.html";
        }
        return "jsp/frame";
    }

    //登出
    @RequestMapping("/logout.html")
    public String logout(HttpSession session) {
        session.removeAttribute(Constants.USER_SESSION);
        return "login";
    }

    //跳转修改密码页面
    @RequestMapping("/pwdmodify.html")
    public String pwdPage() {
        return "/jsp/pwdmodify";
    }

    //校验原密码
    @ResponseBody
    @RequestMapping("/pwd.html")
    public String pwd(@RequestParam("oldpassword") String oldpassword, HttpSession session) {
        User user = (User) session.getAttribute(Constants.USER_SESSION);
        String userPassword = user.getUserPassword();
        Map<String, String> resultMap = new HashMap<>();
        if (user == null) {
            resultMap.put("result", "sessionerror");
        } else if (StringUtils.isNullOrEmpty(oldpassword)) {
            resultMap.put("result", "error");
        } else {
            if (oldpassword.equals(userPassword)) {
                resultMap.put("result", "true");
            } else {
                resultMap.put("result", "false");
            }
        }

        return JSONArray.toJSONString(resultMap);
    }

    //修改密码
    @RequestMapping("/updatepwd.html")
    public String updatePwd(Model model, @RequestParam("newpassword") String newpassword, HttpSession session) {
        User user = (User) session.getAttribute(Constants.USER_SESSION);
        boolean flag = false;
        if (user != null && !StringUtils.isNullOrEmpty(newpassword)) {
            flag = userService.updatePwd(user.getId(), newpassword);
            if (flag) {
                model.addAttribute("message", "修改成功，退出后重新登录");
                return "login";
            } else {
                model.addAttribute("message", "修改失败");
            }
        } else {
            model.addAttribute("message", "新密码有问题");
        }
        return "jsp/pwdmodify";
    }

    //跳转用户管理页面
    @RequestMapping("/userlist.html")
    public String userList(Model model,
                           @RequestParam(value = "queryUserName", required = false) String queryUserName,
                           @RequestParam(value = "queryUserRole", required = false) String strQueryUserRole,
                           @RequestParam(value = "pageIndex", required = false) String pageIndex) {
        //默认值为0
        int queryUserRole = 0;
        //页面显示用户数量
        int pageSize = 6;
        //获取用户总数
        int totalCount = userService.getUserCount(queryUserName, queryUserRole);
        //当前页面
        int currentPageNo = 1;
        //总页数
        PageSupport pageSupport = new PageSupport();
        pageSupport.setCurrentPageNo(currentPageNo);
        pageSupport.setPageSize(pageSize);
        pageSupport.setTotalCount(totalCount);
        int totalPageCount = pageSupport.getTotalPageCount();

        if (queryUserName == null){
            queryUserName="";
        }
        if (strQueryUserRole!=null && !strQueryUserRole.equals("")){
            queryUserRole = Integer.parseInt(strQueryUserRole);
        }
        if (pageIndex != null) {
            try {
                currentPageNo = Integer.valueOf(pageIndex);
            } catch (NumberFormatException e) {
                return "jsp/error";
            }
        }
        // 控制首页和尾页在范围内
        if (currentPageNo < 1) {
            currentPageNo = 1;
        } else if (currentPageNo > totalPageCount) {
            currentPageNo = totalPageCount;
        }

        List<User> userList = userService.getUserList(queryUserName, queryUserRole, currentPageNo, pageSize);
        model.addAttribute("userList", userList);
        List<Role> roleList = null;
        roleList = roleService.getRoleList();
        model.addAttribute("roleList", roleList);
        model.addAttribute("queryUserName", queryUserName);
        model.addAttribute("queryUserRole", queryUserRole);
        model.addAttribute("totalPageCount", totalPageCount);
        model.addAttribute("totalCount", totalCount);
        model.addAttribute("currentPageNo", currentPageNo);
        return "jsp/userlist";
    }

}
