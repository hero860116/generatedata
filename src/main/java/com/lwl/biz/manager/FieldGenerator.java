package com.lwl.biz.manager;

import com.lwl.dal.emum.FieldGenerationType;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 13-7-20
 * Time: 下午8:26
 * To change this template use File | Settings | File Templates.
 */
public interface FieldGenerator {
    String generator(String key, String defaultValue);

    FieldGenerationType getFieldGenerationType();

}
