package com.ychun.account.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.AssertTrue;

@ApiModel(value = "用户对象")
@Data
public class CreateUserRequest {

    @ApiModelProperty(value = "用户名", name = "username", example = "test", required = true)
    private String username;

    @ApiModelProperty(value = "密码", name = "password", example = "123456", required = true)
    @Length(min = 6, message = "密码长度不能少于6位")
    private String password;

    @AssertTrue(message = "Empty request")
    private boolean isValidRequest() {
        return StringUtils.isNotBlank(username) && StringUtils.isNoneBlank(password);
    }
}
