package com.lwl.web.admin.module.screen;

import com.alibaba.citrus.turbine.Context;
import com.alibaba.citrus.turbine.Navigator;
import com.alibaba.citrus.turbine.TurbineRunData;
import com.alibaba.citrus.turbine.dataresolver.Param;
import com.google.gson.Gson;
import com.lwl.biz.ao.TableAO;
import com.lwl.dal.dataobject.TableDO;
import com.lwl.dal.emum.FieldGenerationType;
import com.lwl.dal.jsonobject.TreeNode;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class TableDetail extends BaseScreen {

    @Autowired
    private TableAO tableAO;


    public void execute( @Param("tableName")String tableName,
                         Navigator nav, TurbineRunData rundata, Context context) throws Exception {

        rundata.setLayout("quiRightLayout");

        TableDO tableDO = tableAO.getTableDO(tableName);

        context.put("tableDO", tableDO);
        context.put("fieldGenerationTypes", FieldGenerationType.getKeyValues());
    }
}
