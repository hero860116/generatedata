package com.lwl.dal.dao;

import com.lwl.dal.dataobject.TableDO;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 13-7-7
 * Time: ����9:57
 * To change this template use File | Settings | File Templates.
 */
public interface TableDAO {
    /**
     * ���ݱ�����ñ�ṹ
     *
     * @param tableDO@return
     */
    TableDO getTableDO(TableDO tableDO);

    /**
     * �������ݿ�
     * @param tableDO
     */
    void insert(TableDO tableDO);

    /**
     * �����������ݿ�
     * @param tableDO
     */
    void batchInsert(TableDO tableDO);
}
