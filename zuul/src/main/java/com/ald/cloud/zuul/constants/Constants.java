package com.ald.cloud.zuul.constants;

import java.util.List;
import java.util.Map;

/**
  *                  ,___         .-;'
  *                  `"-.`\_...._/`.`
  *             ,       \        /
  *         .-' ',      / ()   ()\
  *         `'._ \     /()    .  (|
  *             > .'  ;,     -'-  /
  *            / <   |;,     __.;
  *            '-.'-.|  , \    , \
  *               `>.|;, \_)    \_)
  *                `-;     ,    /
  *                  \    /   <
  *                  '. <`'-, _)
  *                  '._)
  * 
  * @Author luohao
  * @Version 1.0.0
  * @Date 17:16 2017/11/29
  * @Desription 
  * @CopyRight 本内容仅限于杭州阿拉丁信息科技股份有限公司内部传阅，禁止外泄以及用于其他的商业目的
  */
 
public class Constants {
    /**
     * 全局异常代码
     */
    public static final Integer ERR_CODE = 999;

    /**
     * 全局异常提示信息
     */
    public static final String ERR_MSG = "an exception occurred...";

    /**
     * 未定义日志提示信息
     */
    public static final String LOG_FILTER_ABANDON= "'{}' abandon, url is '{}', for url was not defined at this rule.";

    /**
     * 开始过滤日志
     */
    public static final String LOG_FILTER_STARTED = "'{}' started, url is '{}'";

    /**
     * 过滤异常日志
     */
    public static final String LOG_FILTER_ERROR = "'{}' occurred an error, url is '{}', exception is :";

    /**
     * 过滤通过
     */
    public static final String LOG_FILTER_NEXT = "'{}' pass to next filter if exists, url is '{}'";

    /**
     * 过滤不通过
     */
    public static final String LOG_FILTER_DINED = "'{}' dined , url is '{}'";

    /**
     * 响应默认状态
     */
    public static final Integer DEFAULT_RESPONSE_STATUS = 0;

    /**
     * IP黑名单被过滤
     */
    public static final Integer FORBIDDEN_IP_BLACK = 210;

    /**
     * IP白名单被过滤
     */
    public static final Integer FORBIDDEN_IP_WHITE = 211;

    /**
     * MD5被过滤
     */
    public static final Integer FORBIDDEN_MD5 = 212;

    /**
     * RSA被过滤
     */
    public static final Integer FORBIDDEN_RSA = 213;

    /**
     * SSL被过滤
     */
    public static final Integer FORBIDDEN_SSL = 214;

    /**
     *TOKEN被过滤
     */
    public static final Integer FORBIDDEN_TOKEN = 215;

    /**
     * pre：可以在请求被路由之前调用。
     */
    public static final String FILTER_TYPE_PRE = "pre";

    /**
     * pre：可以在请求被路由之前调用。
     */
    public static final String FILTER_TYPE_ROUTING = "routing";

    /**
     * pre：可以在请求被路由之前调用。
     */
    public static final String FILTER_TYPE_POST = "post";

    /**
     * pre：可以在请求被路由之前调用。
     */
    public static final String FILTER_TYPE_ERROR = "error";

    /**
     * ip黑名单
     */
    public static final Integer FILTER_BLACK = 0;

    /**
     * ip白名单
     */
    public static final Integer FILTER_WHITE = 1;

    /**
     * MD5过滤
     */
    public static final Integer FILTER_MD5 = 2;

    /**
     * RSA过滤
     */
    public static final Integer FILTER_RSA = 3;

    /**
     * TOKEN过滤
     */
    public static final Integer FILTER_TOKEN = 4;


    /**
     * token在请求中的键
     */
    public static final String KEY_TOKEN = "token";

    /**
     * TOKEN加密密钥
     */
    public static final String SECRET_TOKEN = "12345678";

    public static final String PRIVATE_KEY = "MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBANXSVyvH4C55YKzvTUCN0fvrpKjIC5lBzDe6QlHCeMZaMmnhJpG/O+aao0q7vwnV08nk14woZEEVHbNHCHcfP+gEIQ52kQvWg0L7DUS4JU73pXRQ6MyLREGHKT6jgo/i1SUhBaaWOGI9w5N2aBxj1DErEzI7TA1h/M3Ban6J5GZrAgMBAAECgYAHPIkquCcEK6Nz9t1cc/BJYF5AQBT0aN+qeylHbxd7Tw4puy78+8XhNhaUrun2QUBbst0Ap1VNRpOsv5ivv2UAO1wHqRS8i2kczkZQj8vcCZsRh3jX4cZru6NoBb6QTTFRS6DRh06iFm0NgBPfzl9PSc3VwGpdj9ZhMO+oTYPBwQJBAPApB74XhZG7DZVpCVD2rGmE0pAlO85+Dxr2Vle+CAgGdtw4QBq89cA/0TvqHPC0xZaYWK0N3OOlRmhO/zRZSXECQQDj7JjxrUaKTdbS7gD88qLZBbk8c07ghO0qDCpp8J2U6D9baVBOrkcz+fTh7B8LzyCo5RY8vk61v/rYqcgk1F+bAkEAvYkELUfPCGZBoCsXSSiEhXpn248nFh5yuWq0VecJ25uObtqN7Qw4PxOeg9SOJoHkdqehRGJuc9LaMDQ4QQ4+YQJAJaIaOsVWgV2K2/cKWLmjY9wLEs0jN/Uax7eMhUOCcWTLmUdRSDyEazOZWHhJRATmKpzwyATQMDhLrdySvGoIgwJBALusECkz5zT4lIujwUNO30LlO8PKPCSKiiQJk4pN60pv2AFX4s2xVdZlXsFJh6btIJ9CGrMvEmogZTIGWq1xOFs=";
    public static final String PUBLIC_KEY = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDV0lcrx+AueWCs701AjdH766SoyAuZQcw3ukJRwnjGWjJp4SaRvzvmmqNKu78J1dPJ5NeMKGRBFR2zRwh3Hz/oBCEOdpEL1oNC+w1EuCVO96V0UOjMi0RBhyk+o4KP4tUlIQWmljhiPcOTdmgcY9QxKxMyO0wNYfzNwWp+ieRmawIDAQAB";

}
