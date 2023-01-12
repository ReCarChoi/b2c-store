package com.recarchoi.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * @author recarchoi
 * @description 用户的实体类
 * @since 2023/1/12 16:28
 */
@Data
@TableName("user")
public class User implements Serializable {

    public static final Long serialVersionUID = 1L;

    @JsonProperty("user_id")//jackson的注解，用于进行属性格式化
    @TableId(type = IdType.AUTO)
    private Integer userId;
    @Length(min = 6)
    private String userName;
    @NotBlank
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String password;
    @NotBlank
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String userPhonenumber;

}
