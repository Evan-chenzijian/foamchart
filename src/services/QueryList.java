package services;

import db.DBUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class QueryList {
    public static List getList(String sql) throws Exception {

        List list = new ArrayList();
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();
            ResultSetMetaData rsmd = rs.getMetaData();//获取表中的字段名字
            while (rs.next()) {
                list.add(rs.getString(""+rsmd.getColumnName(1)+""));
//                Map map = new HashMap();
//                for (int i = 1; i <= rsmd.getColumnCount(); i++)//获取列然后存储入map
//                {
//                    map.put(rsmd.getColumnName(i), rs.getObject(i));
//                }

//                list.add(map);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rs != null)
                DBUtils.closeResources(conn, stmt, rs);
        }
        return list;
    }
}
