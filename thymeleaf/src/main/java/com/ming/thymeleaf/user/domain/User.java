package com.ming.thymeleaf.user.domain;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Setter
@Getter
public class User {

    private Integer id;
    @NotEmpty(message = "name error1")
    @NotNull(message = "name error2")
    private String name;
    @NotEmpty(message = "password error1")
    @NotNull(message = "password error2")
    @Length(min = 6, max = 20)
    private String password;

}