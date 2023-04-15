package com.couponsystem.beans;

import com.couponsystem.enums.ClientType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginForm {
    private String email;
    private String password;
    private ClientType clientType;
}
