package com.lwl.dal.dao;

import com.lwl.dal.dataobject.TableDO;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 13-7-7
 * Time: 上午9:57
 * To change this template use File | Settings | File Templates.
 */
public interface TableDAO {
    /**
     * 根据表名获得表结构
     *
     * @param tableDO@return
     */
    TableDO getTableDO(TableDO tableDO);

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
