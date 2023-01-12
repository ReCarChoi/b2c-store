package com.recarchoi.user.service;

import com.recarchoi.param.UserCheckParam;
import com.recarchoi.param.UserLoginParam;
import com.recarchoi.pojo.User;
import com.recarchoi.utils.Result;

/**
 * @author recarchoi
 * @since 2023/1/12 16:54
 */
public interface UserService {
    /**
     * 检查账号是否可用
     *
     * @param userCheckParam 账号参数 不为空
     * @return 检查结果
     */
    Result check(UserCheckParam userCheckParam);

    /**
     * 注册业务
     *
     * @param user 参数已经校验，但是密码是明文
     * @return 结果001 004
     */
    Result register(User user);

    /**
     * 登陆业务
     * @param userLoginParam 账户和密码 已经校验 但是密码是明文
     * @return 结果001 004
     */
    Result login(UserLoginParam userLoginParam);
}
