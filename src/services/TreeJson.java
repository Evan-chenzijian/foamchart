package services;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class TreeJson extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        // 设置响应内容类型
        response.setContentType("text/json; charset=utf-8");
        // 跨域申请
        response.setHeader("Access-Control-Allow-Origin", "*");
        PrintWriter out = response.getWriter();

        //接收树形图页面发过来的请求
        String name = request.getParameter("treeName");

        try {
            JSONObject firstOb = new JSONObject();
            JSONObject secondOb = new JSONObject();
            JSONObject lastOb = new JSONObject();
            JSONArray thirdArr = new JSONArray();
            JSONObject thirdOb = new JSONObject();
            JSONArray secondArr = new JSONArray();
            JSONArray firstArr = new JSONArray();


            String firstLv = name;
            //先写入根节点
            firstOb.put("name", firstLv);
            String getSec = "select distinct secondLv from tree_data where firstLv='"+firstLv+"'";
            List secList = QueryList.getList(getSec);
            for(int i= 0; i<secList.size();i++){
                secondOb.put("name",secList.get(i));
                String getThird = "select distinct thirdLv from tree_data where secondLv='"+secList.get(i)+"'";
                List thirdList = new ArrayList();
                thirdList = QueryList.getList(getThird);
                for(int j=0; j<thirdList.size();j++){
                    thirdOb.put("name",thirdList.get(j));
                    String getLast = "select distinct lastLv from tree_data where thirdLv='"+thirdList.get(j)+"'and secondLv='"+secList.get(i)+"'";
                    List lastList = new ArrayList();
                    lastList = QueryList.getList(getLast);
//                    System.out.println(lastList);
                    for(int k=0;k<lastList.size();k++){
                        lastOb.put("name",lastList.get(k));
//                        System.out.println(lastOb);
                        //这个要put一次add一次
                        thirdArr.add(lastOb);
                    }
//                    lastOb = new JSONObject();
//                    System.out.println(thirdArr);
                    thirdOb.put("children",thirdArr);
                    //这个数组一定要清空一下,不然会重复加入
                    thirdArr = new JSONArray();
                    secondArr.add(thirdOb);
//                    thirdOb = new JSONObject();
//                    System.out.println(secondArr);
                }
                secondOb.put("children",secondArr);
                //同理这个数组也要清空
                secondArr = new JSONArray();
                firstArr.add(secondOb);
//                secondOb = new JSONObject();
//                System.out.println(firstArr);
            }
            firstOb.put("children",firstArr);
            System.out.println(firstOb);

            out = response.getWriter();
            out.println(firstOb);
        } catch (Exception e) {
            System.out.println("树形数据检索异常:" + e);
            e.printStackTrace();
        } finally {
//            不能跳转否则请求页面无数据
//            request.getRequestDispatcher("").forward(request, response);
        }
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        doGet(request, response);
    }
}
