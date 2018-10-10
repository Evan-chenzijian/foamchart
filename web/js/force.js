//定义读取json的URL
var myURL = "http://localhost:8080/getJson";
//直接读取网页上的数据
d3.json(myURL, function(data) {
        // console.log(data);

    //start
    var	links = data;
    var nodes = {};

// Compute the distinct nodes from the links.
    links.forEach(function(link) {
        link.source = nodes[link.source] || (nodes[link.source] = {name: link.source});
        link.target = nodes[link.target] || (nodes[link.target] = {name: link.target});
    });

    var width = 1650,
        height = 2100;

    nodes = d3.values(nodes);

//绑定相连节点
    for(var i = 0; i < nodes.length; i++){
        for(var j = 0; j < links.length; j++){
            if(nodes[i].name === links[j].source.name){
                nodes[i][links[j].target.name] = {name: links[j].target.name};
            }
            if(nodes[i].name === links[j].target.name){
                nodes[i][links[j].source.name] = {name: links[j].source.name};
            }
        }
    }

    var force = d3.layout.force()
        .nodes(nodes)
        .links(links)
        .size([width, height])
        .linkDistance(60)
        .charge(-600)
        .on("tick", tick);

    var drag = force.drag()
        .on("dragstart",dragstart);

    force.start();

    var svg = d3.select("body").append("svg")
        .attr("width", 1640)
        .attr("height", height);

// Per-type markers, as they don't inherit styles.
    svg.append("defs").selectAll("marker")
        .data(["suit", "licensing", "resolved"])
        .enter().append("marker")
        .attr("id", function(d) { return d; })
        .attr("viewBox", "0 -5 10 10")
        .attr("refX", 15)
        .attr("refY", -1.5)
        .attr("markerWidth", 6)
        .attr("markerHeight", 6)
        .attr("orient", "auto")
        .append("path")
        .attr("d", "M0,-5L10,0L0,5");

    var path = svg.append("g").selectAll("path")
        .data(force.links())
        .enter().append("path")
        .attr("class", function(d) { return "link " + d.type; })
        .attr("marker-end", function(d) { return "url(#" + d.type + ")"; });

    var circle = svg.append("g").selectAll("circle")
        .data(force.nodes())
        .enter().append("circle")
        .attr("r", function (d) {
            switch(d.name){
                case "客户需求":
                    return 10;
                case "来料接收":
                    return 10;
                case "来料检验":
                    return 10;
                case "来料存储":
                    return 10;
                case "化工料配置":
                    return 10;
                case "化工料抽料":
                    return 10;
                case "日料罐储存":
                    return 10;
                case "生产准备":
                    return 10;
                case "清模":
                    return 10;
                case "喷脱模剂":
                    return 10;
                case "外协件放置":
                    return 10;
                case "浇注":
                    return 10;
                case "熟化":
                    return 10;
                case "起模":
                    return 10;
                case "开孔":
                    return 10;
                case "首检":
                    return 10;
                case "后道处理":
                    return 10;
                case "过程检查":
                    return 10;
                case "上悬挂链料架车摆放":
                    return 10;
                case "后熟化":
                    return 10;
                case "终检":
                    return 10;
                case "产品特性检查":
                    return 10;
                case "成品包装":
                    return 10;
                case "成品审核":
                    return 10;
                case "成品储存":
                    return 10;
                case "发运":
                    return 10;
                default:
                    return 6;
            }
        })
        // .call(dragBehavior)
        .on("click",function(d){
            //隐藏其它连线上文字
            // text.style("fill-opacity",function(edge){
            //     // console.log(edge.source);
            //     // console.log(edge.target);
            //     if( edge.source === d || edge.target === d ){
            //         return 1;
            //     }
            //     if( edge.source !== d && edge.target !== d ){
            //         return 0;
            //     }
            // })
            //其它节点亮度调低
            circle.style("opacity",function(edge){
                var v = d.name;
                if( edge.name === v || (edge[v] !== undefined &&  edge[v].name === v)){
                    return 1;
                }else{
                    return 0.2;
                }
            })
            //其他连先亮度调低
            path.style("opacity",function(edge){
                if( edge.source === d || edge.target === d ){
                    return 1;
                }
                if( edge.source !== d && edge.target !== d ){
                    return 0.2;
                }
            })
            //其他节点文字亮度调低
            text.style("opacity",function(edge){
                var v = d.name;
                if( edge.name === v || (edge[v] !== undefined &&  edge[v].name === v)){
                    return 1;
                }else{
                    return 0.2;
                }
            })

        })
        // .on("mouseout",function(){
        //     //显示连线上的文字
        //     text.style("fill-opacity",1);
        //     path.style("opacity",1);
        //     circle.style("opacity",1);
        //     text.style("opacity",1);
        // })


        .on("dblclick",dblclick)
        .call(force.drag);
    circle.style("stroke","#333")
        .style("stroke-width",1.5)
        .style("fill",function (d) {
                switch(d.name){
                    case "来料接收":
                        return "#FF0000";
                    case "来料检验":
                        return "#FF0000";
                    case "来料存储":
                        return "#FF0000";
                    case "化工料配置":
                        return "#FF0000";
                    case "化工料抽料":
                        return "#FF0000";
                    case "日料罐储存":
                        return "#FF0000";
                    case "生产准备":
                        return "#FF0000";
                    case "清模":
                        return "#FF0000";
                    case "喷脱模剂":
                        return "#FF0000";
                    case "外协件放置":
                        return "#FF0000";
                    case "浇注":
                        return "#FF0000";
                    case "熟化":
                        return "#FF0000";
                    case "起模":
                        return "#FF0000";
                    case "开孔":
                        return "#FF0000";
                    case "首检":
                        return "#FF0000";
                    case "后道处理":
                        return "#FF0000";
                    case "过程检查":
                        return "#FF0000";
                    case "上悬挂链料架车摆放":
                        return "#FF0000";
                    case "后熟化":
                        return "#FF0000";
                    case "终检":
                        return "#FF0000";
                    case "产品特性检查":
                        return "#FF0000";
                    case "成品包装":
                        return "#FF0000";
                    case "成品审核":
                        return "#FF0000";
                    case "成品储存":
                        return "#FF0000";
                    case "发运":
                        return "#FF0000";
                    case "客户需求":
                        return "#FF0000";
                    default:
                        return "#ccc";
                }
            }
        );

    var text = svg.append("g").selectAll("text")
        .data(force.nodes())
        .enter().append("text")
        .attr("x", 8)
        .attr("y", ".31em")
        .text(function(d) { return d.name; });

// Use elliptical arc path segments to doubly-encode directionality.
    function tick() {
        path.attr("d", linkArc);
        circle.attr("transform", transform);
        text.attr("transform", transform);
    }

    function linkArc(d) {
        var dx = d.target.x - d.source.x,
            dy = d.target.y - d.source.y,
            dr = Math.sqrt(dx * dx + dy * dy);
        return "M" + d.source.x + "," + d.source.y + "A" + dr + "," + dr + " 0 0,1 " + d.target.x + "," + d.target.y;
    }

    function transform(d) {
        return "translate(" + d.x + "," + d.y + ")";
    }

    function dragstart(d) {
        d3.select(this).classed(d.fixed = true);
    }

    function dblclick(d) {
            var name = d.name;
            window.open('Tree.jsp?name='+name);//open a new page and send the param name as title
    }
//end

    //search
    $("#searchBtn").click(function(){
        // alert("postive!");
        var dName = $("#searchValue").val();
        // alert(dName);
        //先把所有的点变暗
        // circle.style("opacity",0.2)
        // path.style("opacity",0.2)
        // text.style("opacity",0.2)
        //再把搜索点弄亮
        circle.style("opacity",function(edge){
            // console.log(edge);
            if( edge.name === dName || (edge[dName] !== undefined &&  edge[dName].name === dName)){
                return 1;
            }else{
                return 0.2;
            }
        });
        path.style("opacity",function(d){
            // console.log(d);
            if( d.source.name === dName || d.target.name===dName){
                return 1;
            }else{
                return 0.2;
            }
        });
        text.style("opacity",function(edge){
            if( edge.name === dName || (edge[dName] !== undefined &&  edge[dName].name === dName)){
                return 1;
            }else{
                return 0.2;
            }
        })
    });

    //clear
    $("#clearBtn").click(function(){
            // alert("postive!")
        $("#searchValue").val("");
                text.style("fill-opacity",1);
                path.style("opacity",1);
                circle.style("opacity",1);
                text.style("opacity",1);
    }
    )
});


