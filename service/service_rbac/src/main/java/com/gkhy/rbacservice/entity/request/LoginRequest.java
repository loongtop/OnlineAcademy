package com.gkhy.rbacservice.entity.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.*;

/**
 * Created by Leo
 * 2020-08-26.
 */
@Getter
@Setter
@NoArgsConstructor
public class LoginRequest {
//    @NotNull
//    @Email
//    @NotBlank(message = "Email can not be blank!")
//    private String email;

//    @NotNull
//    @NotBlank(message = "Name can not be blank!")
    private String username;

//    @NotBlank(message = "Password can not be blank!")
//    @Size(min = 6, max = 20)
    private String password;

    private String remember;
}
