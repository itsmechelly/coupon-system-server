package com.couponsystem.security;

import java.util.Date;

import com.couponsystem.service.ClientService;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CustomSession {
    private ClientService clientService;
    private Date timeStemp;
}
