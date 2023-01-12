package com.recarchoi.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.recarchoi.constants.UserConstants;
import com.recarchoi.param.UserCheckParam;
import com.recarchoi.param.UserLoginParam;
import com.recarchoi.pojo.User;
import com.recarchoi.user.mapper.UserMapper;
import com.recarchoi.user.service.UserService;
import com.recarchoi.utils.MD5Util;
import com.recarchoi.utils.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;

/**
 * @author recarchoi
 * @since 2023/1/12 16:58
 */
@Slf4j
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    /**
     * 检查账号是否可用
     *
     * @param userCheckParam 账号参数 不为空
     * @return 检查结果
     */
    @Override
    public Result check(UserCheckParam userCheckParam) {
        //参数封装
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_name", userCheckParam.getUserName());
        //数据库查询
        Long total = userMapper.selectCount(queryWrapper);
        //查询结果处理
        if (total == 0) {
            //数据库中，不存在 可用
            log.info("UserServiceImpl.check业务结束，结果：{}", "账号可以使用！");
            return (Result) Result.ok("账号不存在，可以使用！");
        }
        log.info("UserServiceImpl.check业务结束，结果：{}", "账号不可以使用！");
        return (Result) Result.fail("账号已经存在，不可以使用！");

    }

    /**
     * 注册业务
     *
     * @param user 参数已经校验，但是密码是明文
     * @return 结果001 004
     */
    @Override
    public Result register(User user) {
        //1 .检查账号是否存在
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_name", user.getUserName());
        Long count = userMapper.selectCount(queryWrapper);
        if (count > 0) {
            return Result.fail("账户已经存在，不可注册");
        }
        //2.密码加密处理
        String newPwd = MD5Util.encode(user.getPassword() + UserConstants.USER_SLAT);
        user.setPassword(newPwd);
        //3.插入数据库数据
        int rows = userMapper.insert(user);
        //4.返回结果封装
        if (rows == 0) {
            return Result.fail("注册失败！请稍后再尝试");
        }
        return Result.ok("注册成功！");
    }

    /**
     * 登陆业务
     *
     * @param userLoginParam 账户和密码 已经校验 但是密码是明文
     * @return 结果001 004
     */
    @Override
    public Result login(UserLoginParam userLoginParam) {
        //1.密码的加密和加盐
        String newPwd = MD5Util.encode(userLoginParam.getPassword() + UserConstants.USER_SLAT);
        //2.账号和密码进行数据库查询，返回一个完整的user对象
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_name", userLoginParam.getUserName()).eq("password", newPwd);
        User user = userMapper.selectOne(queryWrapper);
        //3.判断返回结果
        if (user == null) {
            return Result.fail("账号或密码错误!");
        }
        HashMap<String, String> data = new HashMap<>();
        user.setPassword(null);
        return Result.ok("登陆成功!", user);
    }
}
