package org.java.sepaxml.format;

import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.assertEquals;

public class SEPAFormatDateTest {

    @Test
    public void formatDate() throws Exception {
        assertEquals("20000010120000", SEPAFormatDate.formatDate(new Date(100, 5, 10)));
        assertEquals("20150029120000", SEPAFormatDate.formatDate(new Date(115, 11, 29)));
        assertEquals("19700002120000", SEPAFormatDate.formatDate(new Date(70, 1, 30)));
        assertEquals("20250014120000", SEPAFormatDate.formatDate(new Date(125, 8, 14)));
    }

    @Test
    public void formatDateShort() throws Exception {
        assertEquals("2000-06-10", SEPAFormatDate.formatDateShort(new Date(100, 5, 10)));
        assertEquals("2015-12-29", SEPAFormatDate.formatDateShort(new Date(115, 11, 29)));
        assertEquals("1970-03-02", SEPAFormatDate.formatDateShort(new Date(70, 1, 30)));
        assertEquals("2025-09-14", SEPAFormatDate.formatDateShort(new Date(125, 8, 14)));
    }

    @Test
    public void formatDateLong() throws Exception {
        assertEquals("2000-06-10T12:00:00.000Z", SEPAFormatDate.formatDateLong(new Date(100, 5, 10, 0, 0)));
        assertEquals("2015-12-29T12:02:55.000Z", SEPAFormatDate.formatDateLong(new Date(115, 11, 29, 0, 2, 55)));
        assertEquals("1970-03-02T02:12:37.000Z", SEPAFormatDate.formatDateLong(new Date(70, 1, 30, 14, 12, 37)));
        assertEquals("2025-09-14T08:41:34.000Z", SEPAFormatDate.formatDateLong(new Date(125, 8, 14, 8, 41, 34)));
    }
}