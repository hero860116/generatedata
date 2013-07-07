package com.lwl.web.admin.module.action;

import com.alibaba.citrus.service.requestcontext.parser.ParameterParser;
import com.alibaba.citrus.turbine.Context;
import com.alibaba.citrus.turbine.Navigator;
import com.alibaba.citrus.turbine.TurbineRunData;
import com.alibaba.citrus.turbine.dataresolver.Param;
import com.lwl.biz.ao.TableAO;
import com.lwl.dal.dataobject.FieldDO;
import com.lwl.dal.dataobject.TableDO;
import com.lwl.dal.emum.FieldGenerationType;
import com.lwl.web.admin.module.screen.BaseScreen;
import org.springframework.beans.factory.annotation.Autowired;

public class TableAction {

    @Autowired
    private TableAO tableAO;


    public void doSave( @Param("tableName")String tableName,
            Navigator nav, TurbineRunData rundata, Context context) throws Exception {

        TableDO tableDO = tableAO.getTableDO(tableName);
        ParameterParser pp = rundata.getParameters();

        for (FieldDO fieldDO : tableDO.getFields()) {
            fieldDO.setGenerationType(pp.getInt(fieldDO.getName()));
        }

        tableAO.batchInsert(tableDO);

    }
}
