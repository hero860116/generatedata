package com.lwl.biz.ao.impl;

import com.lwl.biz.ao.TableAO;
import com.lwl.biz.manager.NumberIncreaseGenerator;
import com.lwl.biz.manager.StringIncreaseGenerator;
import com.lwl.dal.dao.TableDAO;
import com.lwl.dal.dataobject.FieldDO;
import com.lwl.dal.dataobject.TableDO;
import com.lwl.dal.emum.FieldGenerationType;
import com.lwl.dal.emum.FieldType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 13-7-7
 * Time: 上午11:00
 * To change this template use File | Settings | File Templates.
 */
@Service("tableAO")
public class TableAOImpl implements TableAO {

    @Autowired
    private TableDAO tableDAO;

    @Autowired
    private StringIncreaseGenerator stringIncreaseGenerator;

    @Autowired
    private NumberIncreaseGenerator numberIncreaseGenerator;

    @Override
    public TableDO getTableDO(String tableName) {
        TableDO tableDO = new TableDO();
        tableDO.setName(tableName);

        return tableDAO.getTableDO(tableDO);
    }

    @Override
    public void insert(TableDO tableDO) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void batchInsert(TableDO tableDO) {

        //按配置规则生成变量
        List<FieldDO> fieldDOs = tableDO.getFields();

        //
        for (int i = 0 ; i < 100000; i++) {
            Map<String, Object> fieldValueMap = new HashMap<String, Object>();

            for (FieldDO fieldDO : fieldDOs) {
                Object value = null;
                if (FieldGenerationType.increase.getType().equals(fieldDO.getGenerationType()))  {
                       if (FieldType.BigDecimal.getType().equals(fieldDO.getType()))  {
                           value =   numberIncreaseGenerator.generator(fieldDO.getName(), 1l);
                       }  else if (FieldType.Integer.getType().equals(fieldDO.getType())) {
                            value = numberIncreaseGenerator.generator(fieldDO.getName(), 1l) ;
                       } else if (FieldType.Long.getType().equals(fieldDO.getType())) {
                           value = numberIncreaseGenerator.generator(fieldDO.getName(), 1l) ;
                       }  else if (FieldType.String.getType().equals(fieldDO.getType())) {
                           value = stringIncreaseGenerator.generator(fieldDO.getName(), null) ;
                       }
                }

                fieldValueMap.put(fieldDO.getName(), value);
            }

            tableDO.addFieldValue(fieldValueMap);

        }

        tableDAO.batchInsert(tableDO);
    }
}
