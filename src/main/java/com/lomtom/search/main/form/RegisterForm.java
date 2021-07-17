package com.lomtom.search.main.form;

import com.lomtom.search.common.group.AddGroup;
import lombok.Data;

import javax.validation.constraints.NotEmpty;

/**
 * @Author: LOMTOM
 * @Date: 2021/4/12
 * @Time: 19:47
 */

@Data
public class RegisterForm {

    @NotEmpty(message = "用户名不能为空",groups = {AddGroup.class})
    private String username;

    @NotEmpty(message = "密码不能为空",groups = {AddGroup.class})
    private String password;

    @NotEmpty(message = "邮箱不能为空",groups = {AddGroup.class})
    private String email;

    @NotEmpty(message = "验证码不能为空",groups = {AddGroup.class})
    private String code;

}
