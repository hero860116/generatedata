package com.lwl.biz.ao;

import com.lwl.dal.dataobject.TableDO;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 13-7-7
 * Time: ����11:00
 * To change this template use File | Settings | File Templates.
 */
public interface TableAO {
    /**
     * ���ݱ�����ñ�ṹ
     *
     * @param tableName @return
     */
    TableDO getTableDO(String tableName);

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
