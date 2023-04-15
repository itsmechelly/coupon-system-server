package com.couponsystem.exceptions;

public class NotFoundException extends CouponSystemException {
    private static final long serialVersionUID = 1L;

    public NotFoundException(String s) {
        super("System error, unable to find " + s);
    }

    public NotFoundException(String s, double d) {
        super("System error, unable to find " + s + d + ".");
    }
}
