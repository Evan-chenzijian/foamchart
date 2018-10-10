<%--
  Created by IntelliJ IDEA.
  User: aliu4830
  Date: 2018/10/09
  Time: 7:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=gbk" language="java" %>
<%
    String path = request.getContextPath();
    // 获得项目完全路径（假设你的项目叫MyApp，那么获得到的地址就是http://localhost:8080/MyApp/）:
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
    response.setCharacterEncoding("GBK");
%>
<html>
<head>
    <style type="text/css">
        body{
            text-align: center;
        }
    </style>
    <meta charset="GBK">
    <title>文件上传</title>
    <!-- base需要放到head中 -->
    <base href=" <%=basePath%>">
    <%--引用一键上传插件--%>
    <script src="js/jquery-latest.js"></script>
    <script src="js/jquery.ocupload-1.1.2.js"></script>
</head>
<body>
<h2>导入PDF文件</h2>
<p style="color: #CD0000">注意导入文件名称必须与链接节点名称相同!</p>
<form action="Upload" method="post" enctype="multipart/form-data">
    <%--这里的name属性不能省略SmartUpload需要通过name来读取文件流--%>
    <input type="file" id="file1" name="file1"/><br>
    <input type="file" id="file2" name="file2"/><br>
    <input type="file" id="file3" name="file3"/><br>
    <input type="file" id="file4" name="file4"/><br>
    <input type="file" id="file5" name="file5"/><br>
    <input type="submit" value="提交">${result}
</form>
</body>
</html>
