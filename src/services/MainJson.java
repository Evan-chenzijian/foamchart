package services;

import db.DBUtils;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class MainJson extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        // 设置响应内容类型
        response.setContentType("text/json; charset=utf-8");
        // 跨域申请
        response.setHeader("Access-Control-Allow-Origin", "*");
        PrintWriter out = response.getWriter();

        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        String sql = "select label from main_data order by seq";
        try {
            conn = DBUtils.getConnection();
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
            JSONArray jsonarray = new JSONArray();
            JSONObject jsonobj = new JSONObject();
            while (rs.next()) {
                // 通过字段检索
                jsonobj.put("myLabel", rs.getString("label"));
                jsonarray.add(jsonobj);
            }
            out = response.getWriter();
            out.println(jsonarray);
//            System.out.println(jsonarray);
        } catch (Exception e) {
            System.out.println("主数据检索异常:" + e);
            e.printStackTrace();
        } finally {
        }
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        doGet(request, response);
    }
}
