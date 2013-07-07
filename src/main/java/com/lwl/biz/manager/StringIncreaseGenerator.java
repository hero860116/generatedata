package com.lwl.biz.manager;

import com.lwl.common.bean.Singleton;
import org.springframework.stereotype.Service;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 13-7-7
 * Time: 下午3:50
 * To change this template use File | Settings | File Templates.
 */
@Service("stringIncreaseGenerator")
public class StringIncreaseGenerator {
    public String generator(String key, String defaultValue) {
        String newKey =     getClass().getSimpleName() + "-" + key;

        String name =  Singleton.getString(newKey, null);
        Long increase = Singleton.getLong(newKey, null);
        if (name == null || increase == null) {
            if (defaultValue == null) {
                defaultValue = "default1";
            }

            increase = 0l;
            for (int i = defaultValue.length() - 1; i >= 0; i--) {
                int j = defaultValue.length() - 1 - i;
                String c = "" + defaultValue.charAt(i);

                //如果为数字
                if (c.matches("\\d")) {
                    Double doubleValue =  Long.valueOf(c) * Math.pow(10, j);
                    increase += doubleValue.longValue();
                } else {
                    name = defaultValue.substring(0, i);
                    Singleton.getInstance().getLongMap().put(newKey, increase);
                    Singleton.getInstance().getStringMap().put(newKey, name);
                    break;
                }
            }
        } else {
            increase++;
            Singleton.getInstance().getLongMap().put(newKey, increase);
        }

       String result =  name + increase;

        return result;
    }
}
