package com.recarchoi.user.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.recarchoi.pojo.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author recarchoi
 * @since 2023/1/12 16:59
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {
}
