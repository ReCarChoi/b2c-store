package com.recarchoi.param;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @author recarchoi
 * @since 2023/1/12 16:33
 * @description 接收前端参数的param
 * TODO: 要使用jsr 303的注解 进行参数校验
 * TODO： @NotBlank 字符串 不能为null 和空字符串""
 * TODO： @NotNull 字符串 不能为null
 * TODO:  @NotEmpty 集合类型 集合长度不能为空
 */
@Data
public class UserCheckParam {

    //注意：参数名称要等于前端传递的JSON key的名称！
    @NotBlank
    private String userName;

}
