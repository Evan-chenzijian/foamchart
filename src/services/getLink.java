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

public class getLink extends HttpServlet {
    private static final long serialVersionUID = 1L;
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        // 设置响应内容类型
        response.setContentType("text/json; charset=utf-8");
        response.setHeader("Access-Control-Allow-Origin", "*");
        PrintWriter out = response.getWriter();
        //获取传过来的原始节点名称
        String dname = request.getParameter("dname");
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        //todo:需要根据传来的dname查询单一数据
        String sql = "select * from link_data where prename='"+dname+"'";
        System.out.println("查询语句:"+sql);
        try {
            conn = DBUtils.getConnection();
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
            JSONArray jsonarray = new JSONArray();
            JSONObject jsonobj = new JSONObject();
            while (rs.next()) {
                // 通过字段检索
                jsonobj.put("prename", rs.getString("prename"));
                jsonobj.put("newcode", rs.getString("newcode"));
                jsonarray.add(jsonobj);
            }
            out = response.getWriter();
            out.println(jsonarray);
            System.out.println("链接:"+jsonarray);
        } catch (Exception e) {
            System.out.println("链接数据检索异常:" + e);
            e.printStackTrace();
        } finally {
        }
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        doGet(request, response);
    }
}
