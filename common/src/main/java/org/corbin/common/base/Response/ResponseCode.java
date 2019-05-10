package org.corbin.common.base.Response;

import java.io.Serializable;

public class ResponseCode implements Serializable {

    /**
     * 成功
     */
    public static final Integer SUCC_0 = 0;

    /**
     * 成功,结果为空
     */
    public static final Integer SUCC_1 = 1;

    /**
     * 参数错误
     */
    public static final Integer ERR_10001 = 10001;

    /**
     * 邮箱格式错误
     */
    public static final Integer ERR_10002 = 10002;
    /**
     * 账号不存在
     */
    public static final Integer ERR_11001 = 11001;
    /**
     * 密码不正确
     */
    public static final Integer ERR_11002 = 11002;
    /**
     * 登录失效
     */
    public static final Integer ERR_11003 = 11003;


    /**
     *验证码发送失败
     */
    public static final Integer ERR_12001 = 12001;

    /**
     * 验证码错误
     */
    public static final Integer ERR_12002 = 12002;
    /**
     * 验证码已失效
     */
    public static final Integer ERR_12003= 12003;


    /**
     * 该邮箱已注册，请直接登录
     */
    public static final Integer ERR_13001 = 13001;
    /**
     *
     */
    public static final Integer ERR_11005 = 11005;
    /**
     *
     */
    public static final Integer ERR_11006 = 11006;
    /**
     *
     */
    public static final Integer ERR_11007 = 11007;
    /**
     *
     */
    public static final Integer ERR_11008 = 11008;
    /**
     *
     */
    public static final Integer ERR_11009 = 11009;
}
