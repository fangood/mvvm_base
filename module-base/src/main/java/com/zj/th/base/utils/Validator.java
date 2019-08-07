package com.zj.th.base.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 */
public class Validator {

    /**
     * 匹配是否为手机号码
     *
     * @param mobiles
     * @return
     */
    public static boolean isMobileNO(String mobiles) {
        // 电信号段：133 139 153 180 181 189 177 170 176
        // 移动号段：138 137 136 135 134 178 188 187 183 182 159 158 157 152 150 147
        // 联通号段：186 185 130 131 132 155 156
        // String str = "^((13[0-9])|(147)|(15[0,2,3,5-9])|(17[0,6-8])|(18[0-3,5-9]))\\d{8}$";
        return validate(mobiles, "^(1)\\d{10}$");
    }

    public static boolean isPassword(String password) {
        return validate(password, "^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9]).{8,}$");
    }

    public static boolean isEmail(String email) {
        String regex = "[\\w!#$%&'*+/=?^_`{|}~-]+(?:\\.[\\w!#$%&'*+/=?^_`{|}~-]+)*@(?:[\\w](?:[\\w-]*[\\w])?\\.)+[\\w](?:[\\w-]*[\\w])?";
        return validate(email, regex);
    }

    public static boolean validate(String input, String regex) {
        if (input == null) {
            return false;
        }
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(input);
        return m.matches();
    }

}