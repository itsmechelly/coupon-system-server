package com.couponsystem.service;

import org.springframework.stereotype.Service;

import com.couponsystem.exceptions.CouponSystemException;

@Service
public abstract class ClientService {
    public abstract boolean login(String email, String password) throws CouponSystemException;
}
