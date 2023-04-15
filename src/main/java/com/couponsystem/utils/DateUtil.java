package com.couponsystem.utils;

import java.sql.Date;

public class DateUtil {
    public static Date dateFormat(int yyyy, int mm, int dd) {
        return new Date(yyyy - 1900, mm - 1, dd);
    }
}
