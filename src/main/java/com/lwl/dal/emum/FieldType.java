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
public enum FieldType {
    String("String", ""), BigDecimal("BigDecimal", ""), Integer("Integer", ""), Timestamp("Timestamp", ""), Long("Long", "");

    private String type;
    private String message;

    private FieldType(String type, String message) {
        this.type = type;
        this.message = message;
    }

    public String getType() {
        return type;
    }

    public String getMessage() {
        return message;
    }

    public static List<KeyValue> getKeyValues() {
        List<KeyValue> keyValues = new ArrayList<KeyValue>();

        for (FieldType fieldGenerationType : FieldType.values()) {
            KeyValue keyValue = new KeyValue(fieldGenerationType.getMessage(), fieldGenerationType.getType());
            keyValues.add(keyValue);
        }

        return keyValues;
    }
}
