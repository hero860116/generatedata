package com.lwl.dal.dataobject;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 13-7-7
 * Time: 上午9:51
 * To change this template use File | Settings | File Templates.
 */
public class FieldDO extends BaseDO{
    private String name;
    private String comment;
    private String type;
    private Integer generationType;

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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getGenerationType() {
        return generationType;
    }

    public void setGenerationType(Integer generationType) {
        this.generationType = generationType;
    }
}
