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
    // �����Ŀ��ȫ·�������������Ŀ��MyApp����ô��õ��ĵ�ַ����http://localhost:8080/MyApp/��:
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
    <title>�ļ��ϴ�</title>
    <!-- base��Ҫ�ŵ�head�� -->
    <base href=" <%=basePath%>">
    <%--����һ���ϴ����--%>
    <script src="js/jquery-latest.js"></script>
    <script src="js/jquery.ocupload-1.1.2.js"></script>
</head>
<body>
<h2>����PDF�ļ�</h2>
<p style="color: #CD0000">ע�⵼���ļ����Ʊ��������ӽڵ�������ͬ!</p>
<form action="Upload" method="post" enctype="multipart/form-data">
    <%--�����name���Բ���ʡ��SmartUpload��Ҫͨ��name����ȡ�ļ���--%>
    <input type="file" id="file1" name="file1"/><br>
    <input type="file" id="file2" name="file2"/><br>
    <input type="file" id="file3" name="file3"/><br>
    <input type="file" id="file4" name="file4"/><br>
    <input type="file" id="file5" name="file5"/><br>
    <input type="submit" value="�ύ">${result}
</form>
</body>
</html>
