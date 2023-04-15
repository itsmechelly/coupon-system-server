package com.couponsystem.exceptions;

public class AlreadyExistException extends CouponSystemException {
    private static final long serialVersionUID = 1L;

    public AlreadyExistException(String string, String string2) {
        super("Error entering new details.\r\n" + string + string2 + ", already exists.\r\n" + "Please try again.");
    }
}
