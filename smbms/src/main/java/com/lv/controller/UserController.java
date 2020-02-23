package com.lv.controller;

import com.alibaba.fastjson.JSONArray;
import com.lv.pojo.User;
import com.lv.service.UserServiceImpl;
import com.lv.utils.Constants;
import com.mysql.cj.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    @Qualifier("userServiceImpl")
    private UserServiceImpl userService;

    //登录
    @RequestMapping("/dologin.html")
    public String login(@RequestParam("userCode") String userCode, @RequestParam("userPassword") String userPassword , HttpSession session, HttpServletRequest request){
        User user = userService.getLoginUser(userCode, userPassword);
        if (user!=null){ //用户存在
            //用户密码正确
            if(user.getUserPassword().equals(userPassword)){
                session.setAttribute(Constants.USER_SESSION, user);
                return "redirect:/user/main.html";
            }else {//密码错误
                request.setAttribute("error", "密码错误!");
            }
        }else { //用户不存在
            request.setAttribute("error", "用户名不存在！");
        }
        return "login";
    }

    @RequestMapping("/main.html")
    public String main(HttpSession session){
        if (session.getAttribute(Constants.USER_SESSION)==null){
            return "redirect:/user/login.html";
        }
        return "jsp/frame";
    }

    //登出
    @RequestMapping("/logout.html")
    public String logout(HttpSession session){
        session.removeAttribute(Constants.USER_SESSION);
        return  "login";
    }

    //跳转修改密码页面
    @RequestMapping("/pwdmodify.html")
    public String pwdPage(){
        return "/jsp/pwdmodify";
    }
    //校验原密码
    @ResponseBody
    @RequestMapping("/pwd.html")
    public String pwd(@RequestParam("oldpassword") String oldpassword,HttpSession session){
        User user = (User) session.getAttribute(Constants.USER_SESSION);
        String userPassword = user.getUserPassword();
        Map<String, String> resultMap = new HashMap<>();
        if (user==null){
            resultMap.put("result","sessionerror");
        }else if (StringUtils.isNullOrEmpty(oldpassword)){
            resultMap.put("result","error");
        }else {
            if (oldpassword.equals(userPassword)){
                resultMap.put("result","true");
            }else {
                resultMap.put("result","false");
            }
        }

        return JSONArray.toJSONString(resultMap);
    }
    //修改密码
    @RequestMapping("/updatepwd.html")
    public String updatePwd(@RequestParam("newpassword") String newpassword, HttpSession session, HttpServletRequest request){
        User user = (User) session.getAttribute(Constants.USER_SESSION);
        boolean flag = false;

        if (user!=null && !StringUtils.isNullOrEmpty(newpassword)){
           flag = userService.updatePwd(user.getId(), newpassword);
           if (flag){
               request.setAttribute("message", "修改成功，退出后重新登录");
               return "login";
           }else {
               request.setAttribute("message", "修改失败");
           }
        }else {
            request.setAttribute("message", "新密码有问题");
        }
        return "jsp/pwdmodify";
    }

}
