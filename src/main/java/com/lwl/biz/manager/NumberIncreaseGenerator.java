package com.lwl.biz.manager;

import com.lwl.common.bean.Singleton;
import org.springframework.stereotype.Service;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 13-7-7
 * Time: обнГ3:50
 * To change this template use File | Settings | File Templates.
 */
@Service("numberIncreaseGenerator")
public class NumberIncreaseGenerator {
    public long generator(String key, long defaultValue) {
        String newKey = getClass().getSimpleName() + "-" + key;
        long number = Singleton.getLong(newKey, defaultValue);
        Singleton.getInstance().getLongMap().put(newKey, (number + 1));

        return number;
    }
}
