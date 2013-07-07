package com.lwl.dal.dao.jdbc;

import com.lwl.dal.dao.TableDAO;
import com.lwl.dal.dataobject.FieldDO;
import com.lwl.dal.dataobject.TableDO;
import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 13-7-7
 * Time: 上午9:59
 * To change this template use File | Settings | File Templates.
 */
@Service("tableDAO")
public class TableDAOImpl implements TableDAO{

    @Autowired
    private SimpleJdbcTemplate simpleJdbcTemplate;

    @Autowired
    private ComboPooledDataSource comboPooledDataSource;

    public TableDO getTableDO(TableDO tableDO) {
        Connection connection = null;
        PreparedStatement pstmt = null;
        ResultSetMetaData rsmd = null;

        try {
            connection = comboPooledDataSource.getConnection();
            pstmt = (PreparedStatement) connection.prepareStatement(tableDO.getQuerySql());
            rsmd = (ResultSetMetaData) pstmt.getMetaData();

            for (int i = 1; i < rsmd.getColumnCount() + 1; i++) {
                FieldDO fieldDO = new FieldDO();
                fieldDO.setName(rsmd.getColumnName(i));
                fieldDO.setType(rsmd.getColumnClassName(i).substring(rsmd.getColumnClassName(i).lastIndexOf(".") + 1));
                tableDO.addField(fieldDO);

                System.out.println(rsmd.getColumnName(i)+ "  " +rsmd.getColumnTypeName(i)
                        +"  " +rsmd.getColumnClassName(i)+ "  "+rsmd.getTableName(i) + " ," +  rsmd.getColumnLabel(i)
                + " ," +  rsmd.getColumnType(i) + " ," +  rsmd.getCatalogName(i) + " ," +  rsmd.getSchemaName(i) + " ," +  rsmd.getPrecision(i) + " ," +  rsmd.getScale(i));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return tableDO;
    }

    public void insert(TableDO tableDO) {
        Connection connection = null;
        PreparedStatement pstmt = null;
    }

    public void batchInsert(TableDO tableDO) {
        long s = System.currentTimeMillis();
        Connection connection = null;
        PreparedStatement pstmt = null;

        try {
            connection = comboPooledDataSource.getConnection();
            connection.setAutoCommit(false);

            pstmt = connection.prepareStatement(tableDO.getInsertSql());

            List<FieldDO> fields = tableDO.getFields();

            //便利值列表
            for (int j = 0; j < tableDO.getFieldValueMapList().size(); j++)   {
                Map<String, Object> fieldValueMap =     tableDO.getFieldValueMapList().get(j) ;

                //按fields的顺序设值
                for (int i=0; i < fields.size(); i++) {
                    FieldDO fieldDO = fields.get(i);
                    pstmt.setObject(i+1, fieldValueMap.get(fieldDO.getName()));
                }
                pstmt.addBatch();

                if ((j+1) % 200 == 0 || j == tableDO.getFieldValueMapList().size() - 1) {
                    pstmt.executeBatch();
                }
            }

            connection.commit();
            long e = System.currentTimeMillis();
            System.out.println("############cost Time:" + (e-s));

        } catch (SQLException e) {
            System.out.println(e);
        }
    }
}
