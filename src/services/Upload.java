package services;

import com.jspsmart.upload.SmartUpload;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;

//@WebServlet(name = "Upload")
public class Upload extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("GBK");
        String result = "未上传";
        //设置上传文件保存路径
        String filePath = getServletContext().getRealPath("/")+"PDF";
        System.out.println(filePath);
        File pdfFile = new File(filePath);
        if (!pdfFile.exists()){
            pdfFile.mkdir();
        }

        SmartUpload smartUpload = new SmartUpload();
        //初始化
        smartUpload.initialize(getServletConfig(),request,response);
        //设置上传文件大小限制
        smartUpload.setMaxFileSize(1024*1024*10);
        //总文件大小限制
        smartUpload.setTotalMaxFileSize(1024*1024*50);
        //类型白名单
        smartUpload.setAllowedFilesList("PDF,pdf,txt,xls");
        try {
            //黑名单
            smartUpload.setDeniedFilesList("exe,rar");
            smartUpload.upload();
            int count = smartUpload.save(filePath);
            if(count>0){
                result="成功上传"+count+"个文件!";
            }
        } catch (Exception e) {
            result="上传失败!"+e.toString();
            e.printStackTrace();
            System.out.println("失败:"+e.toString());
        }
        request.setAttribute("result",result);
        request.getRequestDispatcher("upload.jsp").forward(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
