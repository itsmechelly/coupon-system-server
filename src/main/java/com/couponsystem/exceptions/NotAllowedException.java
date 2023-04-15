package com.couponsystem.exceptions;

public class NotAllowedException extends CouponSystemException {
    private static final long serialVersionUID = 1L;

    public NotAllowedException(String string, String s2) {
        super("System error, unable to update " + string + " " + s2 + ", this function not Allowed.");
    }

    public NotAllowedException(String s, int i) {
        super("System error, unable to update " + s + " " + i + ", this function not Allowed.");
    }
}
