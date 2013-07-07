package com.lwl.dal.emum;

import com.lwl.common.bean.KeyValue;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 13-7-7
 * Time: 下午2:28
 * To change this template use File | Settings | File Templates.
 */
public enum FieldGenerationType {
    random(1, "随机生成"), increase(2, "自增长");

    private Integer type;
    private String message;

    private FieldGenerationType(Integer type, String message) {
        this.type = type;
        this.message = message;
    }

    public Integer getType() {
        return type;
    }

    public String getMessage() {
        return message;
    }

    public static List<KeyValue> getKeyValues() {
        List<KeyValue> keyValues = new ArrayList<KeyValue>();

        for (FieldGenerationType fieldGenerationType : FieldGenerationType.values()) {
            KeyValue keyValue = new KeyValue(fieldGenerationType.getMessage(), fieldGenerationType.getType());
            keyValues.add(keyValue);
        }

        return keyValues;
    }
}
