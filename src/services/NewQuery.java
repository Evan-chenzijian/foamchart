package services;

import db.DBUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NewQuery {
    public static List getList(String sql) throws Exception {

        List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();
            ResultSetMetaData rsmd = rs.getMetaData();//获取表中的字段名字
            while (rs.next()) {
//                list.add(rs.getString(""+rsmd.getColumnName(1)+""));
//                list.add(rs.getString(""+rsmd.getColumnName(2)+""));
                Map map = new HashMap();
                for (int i = 1; i <= rsmd.getColumnCount(); i++)//获取列然后存储入map
                {
                    map.put(rsmd.getColumnName(i), rs.getObject(i));
                }

                list.add(map);
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
