<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>管理员管理</title>
    <%@ include file="header.jsp"%>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/static/layui/css/layui.css" media="all">
</head>
<body>
<script src="<%=request.getContextPath()%>/static/jquery-2.1.4.js"></script>
<script src="<%=request.getContextPath()%>/static/layui/layui.js" charset="utf-8"></script>
<script src="<%=request.getContextPath()%>/static/layer/layer.js"></script>
<script src="<%=request.getContextPath()%>/static/mylayer.js"></script>

<table class="layui-hide" id="test" lay-filter="layFilter"></table>
<script type="text/html" id="barDemo">
    <a class="layui-btn layui-btn-xs" lay-event="edit">编辑</a>
    <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>
</script>
<script type="text/html" id="toolbarDemo">
    <div class="layui-btn-container">
        <button class="layui-btn layui-btn-sm" lay-event="add">添加</button>
        <button class="layui-btn layui-btn-sm" lay-event="deleteAll">批量删除</button>
    </div>
</script>
<script>
    layui.use('table', function () {
        var table = layui.table;

        table.render({
            elem: '#test'
            , url: '${pageContext.request.contextPath}/adminUser/selectByPage'
            , toolbar: '#toolbarDemo'
            , cellMinWidth: 80 //全局定义常规单元格的最小宽度，layui 2.2.1 新增
            , cols: [[
                {type: 'checkbox', fixed: 'left'}
                , {field: 'id', title: 'ID', sort: true}
                , {field: 'name', title: '用户名'}
                , {field: 'loginId', title: '登录ID'}
                , {field: 'tels', title: '手机号'}
                , {field: 'emails', title: '邮箱'}
                , {field: 'description', title: '账号描述'}
                , {field: 'createTime', title: '创建时间'}
                , {field: 'deleteTime', title: '删除时间'}
                , {field: 'useFlag', title: '账号状态'}
                , {title: '操作', toolbar: '#barDemo'}
            ]]
            , page: true
            , id: 'tableId'
        });
        //行工具条
        table.on('tool(layFilter)', function (obj) {
            var data = obj.data;
            var ids = [];
            ids.push(data.id);
            if (obj.event === 'del') {
                layer.confirm('请确认删除', function (index) {
                    $.post(
                        '${pageContext.request.contextPath}/adminUser/deleteByIds?ids='+ids,
                        // {'id': data.id},
                        function (jsonObj) {
                            console.log(jsonObj);
                            if (jsonObj.code == 0) {
                                mylayer.okMsg(jsonObj.msg);
                                // 删除之后重新刷新table表格
                                table.reload('tableId');
                            } else {
                                mylayer.errorMsg(jsonObj.msg);
                            }
                        },
                        'json'
                    );

                });
            } else if (obj.event === 'edit') {
                layer.open({
                    type: 2,
                    area: ['550px', '350px'],
                    content: '${pageContext.request.contextPath}/adminUser/getUpdatePage&id=' + data.id
                });
            }
        });

        table.on('toolbar(layFilter)', function (obj) {
            var checkStatus = table.checkStatus(obj.config.id);
            switch (obj.event) {
                case 'add':
                    layer.open({
                        type: 2,
                        title: "添加管理员",
                        area: ['430px', '500px'],
                        content: '${pageContext.request.contextPath}/adminUser/getAdminUserPage'
                    });

                    break;
                case 'deleteAll':
                    var data = checkStatus.data;
                    var ids = [];
                    $(data).each(function () {
                        ids.push(this.id);
                    });
                    layer.confirm('请确认删除', function (index) {
                        $.post(
                            '${pageContext.request.contextPath}/adminUser/deleteByIds?ids='+ids,
                            // {'ids': ids},
                            function (jsonObj) {
                                console.log(jsonObj);
                                if (jsonObj.code == 0) {
                                    mylayer.okMsg(jsonObj.msg);
                                    // 删除之后重新刷新table表格
                                    table.reload('tableId');
                                } else {
                                    mylayer.errorMsg(jsonObj.msg);
                                    table.reload('tableId');
                                }
                            },
                            'json'
                        );
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
