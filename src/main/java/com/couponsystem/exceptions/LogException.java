package com.couponsystem.exceptions;

public class LogException extends CouponSystemException {
    private static final long serialVersionUID = 1L;

    public LogException() {
        super("Failed to login, email or password incorrect, Please try again.");
    }

    public LogException(String s) {
        super("Failed to logout, token or identifier " + s + " dosen't exist in MapList.");
    }
}
