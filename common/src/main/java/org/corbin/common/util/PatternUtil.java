package org.corbin.common.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

public class PatternUtil {

    /**
     * 判断字符串是否是Mac地址，Mac地址中的字母是大写
     */
    public static boolean isMac(String str) throws PatternSyntaxException {
        String regExp = "^[A-F0-9]{2}([A-F0-9]{2}){5}$";
        Pattern p = Pattern.compile(regExp);
        Matcher m = p.matcher(str);
        return m.matches();
    }

    /**
     * 大陆号码或香港号码均可
     */
    public static boolean isPhoneLegal(String str) throws PatternSyntaxException {
        return isChinaPhoneLegal(str) || isHKPhoneLegal(str);
    }

    /**
     * 大陆手机号码11位数，匹配格式：前三位固定格式+后8位任意数 此方法中前三位格式有： 13+任意数 15+除4的任意数 18+除1和4的任意数 17+除9的任意数 147
     */
    public static boolean isChinaPhoneLegal(String str) throws PatternSyntaxException {
        String regExp = "^((13[0-9])|(15[^4])|(18[0,2,3,5-9])|(17[0-8])|(147))\\d{8}$";
        Pattern p = Pattern.compile(regExp);
        Matcher m = p.matcher(str);
        return m.matches();
    }

    /**
     * 香港手机号码8位数，5|6|8|9开头+7位任意数
     */
    public static boolean isHKPhoneLegal(String str) throws PatternSyntaxException {
        String regExp = "^(5|6|8|9)\\d{7}$";
        Pattern p = Pattern.compile(regExp);
        Matcher m = p.matcher(str);
        return m.matches();
    }

    /**
     * 是否是身份证号码
     */
    public static boolean isIdCareNumber(String str) throws PatternSyntaxException {
        String regExp = "^(^\\d{15}$)|(^\\d{18}$)|(^\\d{17}(\\d|X|x)$)$";
        Pattern p = Pattern.compile(regExp);
        Matcher m = p.matcher(str);
        return m.matches();
    }

    /**
     * @Author: Corbin
     * @Date: 2019/2/6
     * @Param: [mailString]
     * @return: boolean
     * @Description: 是否是邮箱
     */
    public static boolean isMail(String mailString) throws PatternSyntaxException {
        if (mailString == null) {
            return false;
        }

        String regExp = "^[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(\\.[a-zA-Z0-9_-]+)+$";
        Pattern p = Pattern.compile(regExp);
        Matcher m = p.matcher(mailString);
        return m.matches();
    }


}
