package com.lwl.dal.dataobject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 13-7-7
 * Time: 上午9:48
 * To change this template use File | Settings | File Templates.
 */
public class TableDO extends BaseDO{
    private String name;
    private List<FieldDO> fields = new ArrayList<FieldDO>();
    private List<Map<String, Object>> fieldValueMapList = new ArrayList<Map<String, Object>>();

    public String getQuerySql() {
        return "select * from " + name;
    }

    public String getInsertSql() {

        StringBuilder sb = new StringBuilder("insert into ").append(name);
        sb.append("(");
        for (int i=0; i < fields.size(); i++) {
            FieldDO fieldDO = fields.get(i);
            sb.append(fieldDO.getName());
            if (i < fields.size() - 1) {
                sb.append(",");
            }
        }
        sb.append(") values (");

        for (int i=0; i < fields.size(); i++) {
            sb.append("?");
            if (i < fields.size() - 1) {
                sb.append(",");
            }
        }

        sb.append(")");

        return sb.toString();
    }

    public void addFieldValue(Map<String, Object> fieldValueMap) {
        fieldValueMapList.add(fieldValueMap);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<FieldDO> getFields() {
        return fields;
    }

    public void setFields(List<FieldDO> fields) {
        this.fields = fields;
    }

    public void addField(FieldDO fieldDO) {
        fields.add(fieldDO);
    }

    public List<Map<String, Object>> getFieldValueMapList() {
        return fieldValueMapList;
    }

    public void setFieldValueMapList(List<Map<String, Object>> fieldValueMapList) {
        this.fieldValueMapList = fieldValueMapList;
    }
}
