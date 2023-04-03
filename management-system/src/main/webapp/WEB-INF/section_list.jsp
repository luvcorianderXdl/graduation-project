<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>用户管理</title>
    <%@ include file="header.jsp" %>
</head>
<style>
    .layui-table-cell {
        height: auto;
        line-height: 30px;
    }
</style>
<body>
<table class="layui-hide" id="test" lay-filter="layFilter"></table>
<script type="text/html" id="barDemo">
    <a class="layui-btn layui-btn-xs" lay-event="edit">编辑</a>
    <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>
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
            , url: '${pageContext.request.contextPath}/section/selectByPage'
            , toolbar: '#toolbarDemo'
            , cols: [[
                , {field: 'id', title: 'ID', sort: true, width: 80}
                , {field: 'sectionName', title: '板块名称'}
                , {field: 'description', title: '描述'}
                , {
                    field: 'sectionImage',
                    title: '图像',
                    templet: function (d) {
                        return '<img src="' + d.sectionImage + '">';
                    }
                }
                , {field: 'createTime', title: '创建时间', width: 170}
                , {field: 'modifyTime', title: '修改时间', width: 170}
                , {field: 'modifyUserId', title: '修改人', width: 170}
                , {
                    field: 'useFlag', title: '状态', templet: function (d) {
                        return d.useFlag === 1 ? '正常' : '已删除';
                    }, width: 80
                }
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
            if (obj.event === 'del') {
                layer.confirm('请确认删除', function (index) {
                    $.post(
                        '${pageContext.request.contextPath}/section/deleteByIds?ids=' + ids,
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
                    title: "编辑板块",
                    area: ['430px', '470px'],
                    offset: [80, 400],
                    content: '${pageContext.request.contextPath}/section/getUpdatePage?id=' + data.id
                });
            }
        });

        table.on('toolbar(layFilter)', function (obj) {
            var checkStatus = table.checkStatus(obj.config.id);
            switch (obj.event) {
                case 'add':
                    layer.open({
                        type: 2,
                        title: "添加板块",
                        area: ['430px', '470px'],
                        offset: [80, 400],
                        content: '${pageContext.request.contextPath}/section/getAddPage'
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
