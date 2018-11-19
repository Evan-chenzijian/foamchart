<%--
  Created by IntelliJ IDEA.
  User: aliu4830
  Date: 2018/09/29
  Time: 9:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
    // 获得项目完全路径（假设你的项目叫MyApp，那么获得到的地址就是http://localhost:8080/MyApp/）:
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
//    response.setCharacterEncoding("GBK");
%>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <base href=" <%=basePath%>">
    <title>树状图</title>
    <!-- Bootstrap -->
    <link href="js/bootstrap-3.3.7-dist/css/bootstrap.min.css" rel="stylesheet">
</head>

<style>

    .node {
        cursor: pointer;
    }

    .node circle {
        fill: #fff;
        stroke: steelblue;
        stroke-width: 1.5px;
    }

    .node text {
        font: 10px sans-serif;
        font-weight: 800;
    }

    .link {
        fill: none;
        stroke: #ccc;
        stroke-width: 1.5px;
    }</style>
<body>
<div class="container" style="margin-top: 10px">
    <div class="row">
        <span class="col-md-2 col-lg-2" style="font-size: 25px;font-weight: 800">树状图:</span>
        <form action="TreeJson" id="treeForm" class="col-md-4 col-lg-4">
            <input type="text" name="treeName" id="treeName" style="margin-top: 6px">
            <button type="button" onclick="toSubmit()">显示</button>
        </form>
    </div>
</div>
<hr>
<div class="container">
    <div class="row">
        <P class="col-md-1 col-lg-1">图例说明:</P>
        <ul class="col-md-6 col-lg-6">
            <li style="color:#CD0000;float:left">MES</li>
            <li style="color:#F0E68C;float:left;margin-left:30px">SAP</li>
            <LI style="color:#FF7F50;float:left;margin-left:30px">TMS</LI>
            <LI style="color:#9932CC;float:left;margin-left:30px">AMP</LI>
            <LI style="color:#00EE00;float:left;margin-left:30px">WMS</LI>
            <li style="color:#8B7500;float:left;margin-left:30px">WMS&SAP</li>
            <li style="color:#607B8B;float:left;margin-left:30px">maximo</li>
        </ul>
    </div>
</div>
<div class="container">
    <div class="row">
        <div id="newTree"></div>
    </div>
</div>
    <script src="js/jquery.js"></script>
    <script src="js/d3.v3.min.js"></script>
    <script src="js/jquery-latest.js"></script>
    <script src="js/bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>
    <script src="js/jquery-form.js"></script>
<script>
    var margin = {top: 20, right: 120, bottom: 20, left: 120},
        width = 960 - margin.right - margin.left,
        height = 800 - margin.top - margin.bottom;

    var i = 0,
        duration = 750,
        root;

    var tree = d3.layout.tree()
        .size([height, width]);

    var diagonal = d3.svg.diagonal()
        .projection(function(d) { return [d.y, d.x]; });

    var svg = d3.select("body").append("svg")
        .attr("width", width + margin.right + margin.left)
        .attr("height", height + margin.top + margin.bottom)
        .append("g")
        .attr("transform", "translate(" + margin.left + "," + margin.top + ")");

    //获取name值
    function GetRequest() {
        var url = decodeURI(decodeURI(location.search)); //获取url中"?"符后的字串，使用了两次
        var theRequest = new Object();
        if (url.indexOf("?") != -1) {
            var str = url.substr(1);
            strs = str.split("&");
            for (var i = 0; i < strs.length; i++) {
                theRequest[strs[i].split("=")[0]] = (strs[i].split("=")[1]);
            }
        }
        return theRequest;
    }

    var Request = new Object();
    Request = GetRequest();
    var dataName;
    dataName = Request["name"];
    // console.log(dataName);
    $("#treeName").val(dataName);

    // d3.json(dataName+".json", function(error, flare) {
    // d3.json("喷脱模剂.json", function(error, flare) {
    //定义读取json的URL
    // var myURL = "http://localhost:8080/TreeJson";
    //直接读取网页上的数据
    // d3.json(myURL, function(data) {
        // if (error) throw error;
        // console.log(flare);

    //采用ajax从后台取数据
    function toSubmit(){
        var options = {
            //需要刷新的区域id
            target:'#newTree',
            success:function(data){
                root = data;
                root.x0 = height / 2;
                root.y0 = 0;

                function collapse(d) {
                    if (d.children) {
                        d._children = d.children;
                        d._children.forEach(collapse);
                        d.children = null;
                    }
                }
                root.children.forEach(collapse);
                update(root);
                // });

                d3.select(self.frameElement).style("height", "800px");

                function update(source) {

                    // Compute the new tree layout.
                    var nodes = tree.nodes(root).reverse(),
                        links = tree.links(nodes);

                    // Normalize for fixed-depth.
                    nodes.forEach(function(d) { d.y = d.depth * 180; });

                    // Update the nodes…
                    var node = svg.selectAll("g.node")
                        .data(nodes, function(d) { return d.id || (d.id = ++i); });

                    // Enter any new nodes at the parent's previous position.
                    var nodeEnter = node.enter().append("g")
                        .attr("class", "node")
                        .attr("transform", function(d) { return "translate(" + source.y0 + "," + source.x0 + ")"; })
                        .on("dblclick",dblclick)//改为打开文件链接
                        .on("click", click);

                    nodeEnter.append("circle")
                    // .attr("r", 1e-6)
                        .attr("r", 7)
                        .style("fill", function(d) {switch (d.type){
                            case "MES":
                                return "#CD0000";
                            case "TMS":
                                return "#FF7F50";
                            case "SAP":
                                return "#F0E68C";
                            case "AMP":
                                return "#9932CC";
                            case "WMS":
                                return "#00EE00";
                            case "WMS&SAP":
                                return "#8B7500";
                            case "maximo":
                                return "#607B8B";
                            default:
                                return "#fff";
                        } })
                        .style("stroke", function(d) { return d._children ? "#000000" : "lightsteelblue"; });
                    // .style("fill", function(d) { return d._children ? "lightsteelblue" : "#fff"; });

                    nodeEnter.append("text")
                        .attr("x", function(d) { return d.children || d._children ? -10 : 10; })
                        .attr("dy", ".35em")
                        .attr("text-anchor", function(d) { return d.children || d._children ? "end" : "start"; })
                        .text(function(d) { return d.name; })
                        .style("fill-opacity", 1e-6);

                    // Transition nodes to their new position.
                    var nodeUpdate = node.transition()
                        .duration(duration)
                        .attr("transform", function(d) { return "translate(" + d.y + "," + d.x + ")"; });

                    nodeUpdate.select("circle")
                        .attr("r", 7)
                        .style("stroke-width",3)
                        .style("stroke", function(d) { return d._children ? "#000000" : "lightsteelblue"; });
                    // .style("fill", function(d) { return d._children ? "lightsteelblue" : "#fff"; });

                    nodeUpdate.select("text")
                        .style("fill-opacity", 1);

                    // Transition exiting nodes to the parent's new position.
                    var nodeExit = node.exit().transition()
                        .duration(duration)
                        .attr("transform", function(d) { return "translate(" + source.y + "," + source.x + ")"; })
                        .remove();

                    nodeExit.select("circle")
                        .attr("r", 1e-6);

                    nodeExit.select("text")
                        .style("fill-opacity", 1e-6);

                    // Update the links…
                    var link = svg.selectAll("path.link")
                        .data(links, function(d) { return d.target.id; });

                    // Enter any new links at the parent's previous position.
                    link.enter().insert("path", "g")
                        .attr("class", "link")
                        .attr("d", function(d) {
                            var o = {x: source.x0, y: source.y0};
                            return diagonal({source: o, target: o});
                        });

                    // Transition links to their new position.
                    link.transition()
                        .duration(duration)
                        .attr("d", diagonal);

                    // Transition exiting nodes to the parent's new position.
                    link.exit().transition()
                        .duration(duration)
                        .attr("d", function(d) {
                            var o = {x: source.x, y: source.y};
                            return diagonal({source: o, target: o});
                        })
                        .remove();

                    // Stash the old positions for transition.
                    nodes.forEach(function(d) {
                        d.x0 = d.x;
                        d.y0 = d.y;
                    });
                }

                // Toggle children on click.
                function click(d) {
                    if (d.children) {
                        d._children = d.children;
                        d.children = null;
                    } else {
                        d.children = d._children;
                        d._children = null;
                    }
                    update(d)
                }

                function dblclick(d) {
                    //获取节点名称
                    var dname = d.name;
                    //ajax重新获取编号名称
                    $.ajax({
                        url: "getLink", //请求路径
                        type: "POST",
                        data: {"dname":""+dname+""},//传递一个OBJECT
                        dataType: "json",
                        success: function (data) {
                            var codeName = data.map(function (item) {
                                return item.newcode;
                            });
                            //定义请求地址
                            var pdfUrl = "PDF/"+codeName+".pdf";
                            //使用ajax请求文件
                            $.ajax(pdfUrl,{
                                type:'get',
                                timeout:10000,
                                success:function () {
                                    window.open(pdfUrl);
                                },
                                error:function () {
                                    alert("没有相应文件,请上传!")
                                }
                            });
                        }
                    });

                }
            }
        };
        $("#treeForm").ajaxSubmit(options);}

    //修改按钮
    // $("#update").click(function () {
    //     // alert("postive!")
    //     var name = '来料接收';
    //     window.open(encodeURI("../../console/index.html?name="+name),name);
    // });
</script>
</div>
</body>
</html>
