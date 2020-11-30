package cn.corgy.utils;

import cn.corgy.exception.ParamException;

/**
 * 判断结果返回 正确返回true
 * 错误抛出异常
 */
public class AssertUtil {

    public static void istrue(Boolean flag, String msg) {
        if (flag) {
            throw new ParamException(msg);
        }
    }
}
