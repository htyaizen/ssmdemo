package com.controller;

import com.beans.UserPojo;
import com.service.impl.UserServiceImpl;
import com.uilts.JsonResult;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * Created by Administrator on 2017/6/19.
 */
@Controller
@RequestMapping("/keda")
public class UserController extends BaseController {
    @Resource
    private UserServiceImpl userService;

    @RequestMapping(value = "/login.do", method = {RequestMethod.GET})
    @ResponseBody
    public Object toLogin(HttpServletRequest request) {
        String name = request.getParameter("name");
        String psd = request.getParameter("psd");
        System.out.println("调用控制器! name:" + name + "    psd:" + psd);
        if (userService.toLogin(name, psd)) {
            return new JsonResult("登录成功");
        }
        return new JsonResult(-2, null, "登录失败");
    }
    @RequestMapping(value = "/register.do", method = {RequestMethod.POST})
    @ResponseBody
    public Object toRegister(UserPojo userPojo) {
        System.out.print("收到请求");
        if (userService.toRegister(userPojo)) {
            return new JsonResult("注册成功");
        }
        return new JsonResult(-2, null, "请完善信息");
    }
}
