package com.recarchoi.param;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @author recarchoi
 * @since 2023/1/12 19:11
 */
@Data
public class UserLoginParam {
    @NotBlank
    private String userName;
    @NotBlank
    private String password;
}
