package com.gkhy.rbacservice.entity.request;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;


/**
 * Created by Leo
 * 2020-08-26.
 */
@Data
public class RegisterRequest {
    @NotBlank
    private String name;

    @NotBlank
    @Email
    private String email;

    @NotBlank
    private String password;

    private String remember;
}
