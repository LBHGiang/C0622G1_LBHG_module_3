package furama_resort.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Date;

public class Validation {

    public static final String NAME_REGEX = "^\\p{L}+(\\s\\p{L}+)*$";//dễ dãi
    public static final String PHONE_REGEX = "^(09[01]\\d{7})|([(]84[)][+]9[01]\\d{7})$";
    public static final String IDENTITY_CARD_REGEX = "^[1-9]((\\d{8})|(\\d{11}))$";
    public static final String EMAIL_REGEX = "^[A-Za-z0-9]+@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)$";
    public static final String ADDRESS_REGEX = "^(\\p{L}+(\\s\\p{L}+)*)\\s\\-\\s(\\p{L}+(\\s\\p{L}+)*)$";
    public static final double MIN_NUMBER = 10000;

    /**
     * Tất cả các hàm validation này
     *
     * @param str
     * @return false - nếu có lỗi
     */

    public static boolean checkNull(String str) {
        return !str.equals("");
    }

    public static boolean checkDateAndAge(String dateStr) {
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy-MM-dd");
//        fmt.setLenient(false);
        LocalDate date = null;
        try {
            date = LocalDate.parse(dateStr, fmt);
        } catch (
                DateTimeParseException e) {
            return false;
        }
//        return LocalDate.now().getYear() - date.getYear() >= 18;
        return true;
    }

    public static boolean checkName(String str) {
        return str.matches(NAME_REGEX);
    }

    public static boolean checkPhone(String str) {
        return str.matches(PHONE_REGEX);
    }

    public static boolean checkIdCard(String str) {
        return str.matches(IDENTITY_CARD_REGEX);
    }

    public static boolean checkEmail(String str) {
        return str.matches(EMAIL_REGEX);
    }

    public static boolean checkAddress(String str) {
        return str.matches(ADDRESS_REGEX);
    }

    public static boolean checkNumber(double number) {
        return number > MIN_NUMBER;
    }

}
