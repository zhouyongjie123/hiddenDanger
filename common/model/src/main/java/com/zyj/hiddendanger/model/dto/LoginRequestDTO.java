package com.zyj.hiddendanger.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public final class LoginRequestDTO {
    private String account;

    private String password;

    private String phoneNumber;

    private String verificationCode;
}
