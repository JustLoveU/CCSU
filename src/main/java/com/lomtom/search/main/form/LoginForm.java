package com.lomtom.search.main.form;

import com.lomtom.search.common.group.AddGroup;
import lombok.Data;

import javax.validation.constraints.NotEmpty;

/**
 * @Author: LOMTOM
 * @Date: 2021/4/12
 * @Time: 19:46
 */

@Data
public class LoginForm {

    @NotEmpty(message = "用户名不能为空")
    private String username;

    @NotEmpty(message = "密码不能为空")
    private String password;
}
