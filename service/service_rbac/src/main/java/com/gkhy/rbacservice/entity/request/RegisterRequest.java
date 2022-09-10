package com.gkhy.rbacservice.entity.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


/**
 * Created by Leo
 * 2020-08-26.
 */

@Getter
@Setter
@NoArgsConstructor
public final class RegisterRequest {
    @NotNull
    @NotBlank(message = "Name can not be blank!")
    private String name;

    @NotNull
    @Email
    @NotBlank(message = "Email can not be blank!")
    private String email;

    @NotBlank(message = "Password can not be blank!")
    @Size(min = 6, max = 20)
    private String password1;

    @NotBlank(message = "Password can not be blank!")
    @Size(min = 6, max = 20)
    private String password2;

    private String remember;
}
