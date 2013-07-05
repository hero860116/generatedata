package com.lwl.data.generatedata.data.dataobject;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 13-7-5
 * Time: 下午6:09
 * To change this template use File | Settings | File Templates.
 */
public class TableDO {
    /**
     * 字段名
     */
    private String name;

    /**
     * 字段注释
     */
    private String comment;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
