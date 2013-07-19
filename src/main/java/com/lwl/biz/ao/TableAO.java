package com.lwl.biz.ao;

import com.lwl.dal.dataobject.TableDO;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 13-7-7
 * Time: 上午11:00
 * To change this template use File | Settings | File Templates.
 */
public interface TableAO {
    /**
     * 根据表名获得表结构
     *
     * @param tableName @return
     */
    TableDO getTableDO(String tableName);

    /**
     * 插入数据库
     * @param tableDO
     */
    void insert(TableDO tableDO);

    /**
     * 批量插入数据库
     * @param tableDO
     */
    void batchInsert(TableDO tableDO);
}
