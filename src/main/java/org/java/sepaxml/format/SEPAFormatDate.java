package org.java.sepaxml.format;

import java.text.SimpleDateFormat;
import java.util.Date;

public class SEPAFormatDate {

    public static String formatDate(Date date) {
        return new SimpleDateFormat("yyyyMMddhhmmss").format(date);
    }

    public static String formatDateShort(Date date) {
        return new SimpleDateFormat("yyyy-MM-dd").format(date);
    }

    public static String formatDateLong(Date date) {
        return new SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ss.000'Z'").format(date);
    }
}