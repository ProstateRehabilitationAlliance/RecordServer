package com.prostate.record.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class phoneValidation {

    /**
     * 正则校验手机号
     * @param phone
     * @return
     */
    public static boolean regexCheck(String phone) {
        String regex = "^((13[0-9])|(14[5|7])|(15([0-3]|[5-9]))|(17[013678])|(18[0,5-9]))\\d{8}$";
        if (phone.length() != 11) {
            return false;
        } else {
            Pattern p = Pattern.compile(regex);
            Matcher m = p.matcher(phone);
            return m.matches();
        }
    }
}
