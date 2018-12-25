var g = new dagreD3.graphlib.Graph()
    .setGraph({})
    .setDefaultEdgeLabel(function() { return {}; });
// 获取网页数据
$.getJSON('MainJson',function(data){
    // console.log(data);
    //循环赋值
    for(var i = 0;i<data.length;i++){
        //悲剧,label命名冲突了
        g.setNode(i,{ label: data[i].myLabel});
        // g.setEdge(i, i+1);
    }
    for(var i = 0;i<data.length-1;i++){
        //要比节点少一次循环
        g.setEdge(i, i+1);
    }

    g.nodes().forEach(function(v) {
        var node = g.node(v);
        // Round the corners of the nodes
        node.rx = node.ry = 5;
    });

// Create the renderer
    var render = new dagreD3.render();

// Set up an SVG group so that we can translate the final graph.
    var svg = d3.select("svg"),
        svgGroup = svg.append("g");

// Run the renderer. This is what draws the final graph.
    render(d3.select("svg g"), g);

//define the click function for nodes
    $("g.node").click(function () {
        var upText = this.innerHTML;
        var reg = /[\u4E00-\u9FA5\uF900-\uFA2D]/g;
        var doText = upText.match(reg).join("");
        console.log(upText);
        console.log(doText);
        $("#searchValue").val(doText);
    });

// Center the graph
    var xCenterOffset = (svg.attr("width") - g.graph().width) / 2;
    svgGroup.attr("transform", "translate(" + xCenterOffset + ", 20)");
    svg.attr("height", g.graph().height + 40);
});


