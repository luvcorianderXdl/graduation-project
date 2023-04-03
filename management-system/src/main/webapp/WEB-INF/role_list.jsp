<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>权限角色管理</title>
    <%@ include file="header.jsp" %>
</head>
<body>
<table class="layui-hide" id="test" lay-filter="layFilter"></table>
<script type="text/html" id="barDemo">
    <a class="layui-btn layui-btn-xs" lay-event="edit">编辑</a>
</script>
<script type="text/html" id="toolbarDemo">
    <div class="layui-btn-container">
        <button class="layui-btn layui-btn-sm" lay-event="add">添加</button>
    </div>
</script>
<script>
    layui.use('table', function () {
        var table = layui.table;
        table.render({
            elem: '#test'
            , url: '${pageContext.request.contextPath}/role/selectByPage'
            , toolbar: '#toolbarDemo'
            // , cellMinWidth: 80 //全局定义常规单元格的最小宽度，layui 2.2.1 新增
            , cols: [[
                {type: 'checkbox', fixed: 'left'}
                , {field: 'id', title: 'ID', sort: true, width: 80}
                , {field: 'name', title: '名称', width: 150}
                , {field: 'description', title: '描述', width: 150}
                , {field: 'createTime', title: '创建时间', width: 170}
                , {field: 'modifyTime', title: '修改时间', width: 170}
                , {field: 'modifyUserId', title: '修改人', width: 80}
                , {title: '操作', toolbar: '#barDemo', width: 120}
            ]]
            , page: true
            , id: 'tableId'
        });
        //行工具条
        table.on('tool(layFilter)', function (obj) {
            var data = obj.data;
            var ids = [];
            ids.push(data.id);
            if (obj.event === 'edit') {
                layer.open({
                    type: 2,
                    title: "编辑板块",
                    area: ['430px', '230px'],
                    offset: [100, 400],
                    content: '${pageContext.request.contextPath}/role/getUpdatePage?id=' + data.id
                });
            }
        });

        table.on('toolbar(layFilter)', function (obj) {
            var checkStatus = table.checkStatus(obj.config.id);
            switch (obj.event) {
                case 'add':
                    layer.open({
                        type: 2,
                        title: "添加角色",
                        area: ['430px', '230px'],
                        offset: [100, 400],
                        content: '${pageContext.request.contextPath}/role/getAddPage'
                    });
                    break;
                case 'LAYTABLE_TIPS':
                    layer.alert('这是工具栏右侧自定义的一个图标按钮');
                    break;
            }
        });

    });
</script>
</body>
</html>
