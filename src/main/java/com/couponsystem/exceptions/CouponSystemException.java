package com.couponsystem.exceptions;

public class CouponSystemException extends Exception {
    private static final long serialVersionUID = 1L;

    public CouponSystemException() {
        super();
    }

    public CouponSystemException(String message) {
        super(message);
    }

    public CouponSystemException(String string, String string2) {
        super(string + string2);
    }
}
