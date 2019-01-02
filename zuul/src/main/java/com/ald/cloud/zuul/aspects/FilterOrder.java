package com.ald.cloud.zuul.aspects;

import java.lang.annotation.*;

/**
 * @Author luohao
 * @Version 1.0.0
 * @Date 15:14 2017/11/29
 * @Desription 标记filter的出生顺序, 从0开始
 * @CopyRight 本内容仅限于杭州阿拉丁信息科技股份有限公司内部传阅，禁止外泄以及用于其他的商业目的
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface FilterOrder {
    int value(); //filter的出生顺序, 不可重复
}
