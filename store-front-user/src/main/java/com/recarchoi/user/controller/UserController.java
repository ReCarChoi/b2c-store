package com.recarchoi.user.controller;

import com.recarchoi.param.UserCheckParam;
import com.recarchoi.param.UserLoginParam;
import com.recarchoi.pojo.User;
import com.recarchoi.user.service.UserService;
import com.recarchoi.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author recarchoi
 * @description 用户模块的控制器
 * @since 2023/1/12 16:41
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 检查账号是否可用的接口
     *
     * @param userCheckParam 接收检查的账号实体 内部有参数校验注解
     * @param result         获取校验结果的实体对象
     * @return 返回封装结果
     */
    @PostMapping("/check")
    public Result check(@RequestBody @Validated UserCheckParam userCheckParam,
                        BindingResult result) {
        //检查是否符合校验注解的规则 符合 false 不符合 true
        if (result.hasErrors()) {
            return Result.fail("账号为null，不可使用!");
        }
        return userService.check(userCheckParam);
    }

    @PostMapping("/register")
    public Result register(@RequestBody @Validated User user,
                           BindingResult result) {
        if (result.hasErrors()) {
            return Result.fail("参数异常，不可注册!");
        }
        return userService.register(user);
    }

    @PostMapping("/login")
    public Result login(@RequestBody @Validated UserLoginParam userLoginParam,
                        BindingResult result) {
        if (result.hasErrors()){
            return Result.fail("参数异常，不可登陆!");
        }
        return userService.login(userLoginParam);
    }

}
