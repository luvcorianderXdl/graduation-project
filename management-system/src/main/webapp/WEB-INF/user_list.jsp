<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>用户管理</title>
    <%@ include file="header.jsp" %>
</head>
<body>
<table class="layui-hide" id="test" lay-filter="layFilter"></table>
<script type="text/html" id="barDemo">
    <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>
</script>
<style>
    .toolbar-container {
        display: flex;
        align-items: center;
    }

    .layui-btn-container {
        margin-right: 10px;
        margin-bottom: 0;
    }

    .layui-input-block {
        display: flex;
        align-items: center;
        padding-top: 10px;
        margin-top: 0;
    }

    .layui-input-block input {
        margin-right: 10px;
    }
</style>

<script type="text/html" id="toolbarDemo">
    <div class="toolbar-container">
        <div class="layui-btn-container">
            <button class="layui-btn layui-btn-sm" lay-event="deleteAll">批量删除</button>
        </div>
        <div class="layui-input-block">
            <input type="text" name="openId" id="openId" value="" autocomplete="off" style="width: 150px;height: 35px"
                   placeholder="请输入openId英文逗号分割"
                   class="layui-input">
            <button class="layui-btn layui-btn-sm" id="search" lay-event="search">搜索</button>
        </div>
    </div>
</script>

<script>
    layui.use('table', function () {
        var table = layui.table;
        var initUrl = '${pageContext.request.contextPath}/user/selectByPage';
        var myTable = table.render({
            elem: '#test'
            , url: initUrl
            , toolbar: '#toolbarDemo'
            // , cellMinWidth: 80 //全局定义常规单元格的最小宽度，layui 2.2.1 新增
            , cols: [[
                {type: 'checkbox', fixed: 'left'}
                , {field: 'id', title: 'ID', sort: true, width: 80}
                , {field: 'name', title: '用户名', width: 150}
                , {field: 'age', title: '年龄'}
                , {
                    field: 'gender', title: '性别', templet: function (d) {
                        if (d.gender == 1) {
                            return '男';
                        } else if (d.gender == 0) {
                            return '女';
                        } else {
                            return '不愿透露';
                        }
                    }
                }
                , {field: 'grade', title: '年级'}
                , {field: 'classNo', title: '班级'}
                , {field: 'openid', title: '唯一标识'}
                , {field: 'createTime', title: '创建时间', width: 170}
                , {field: 'modifyTime', title: '修改时间', width: 170}
                , {field: 'modifyUserId', title: '修改人', width: 80}
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
        document.getElementById('search').addEventListener('click', function () {
            var openId = $('#openId').val();
            var newUrl = '${pageContext.request.contextPath}/user/getByOpenId?openId=' + openId;
            myTable.reload({
                url: newUrl,
                method: 'POST',
                where: {
                    // 传递其他参数（如果需要）
                },
                page: {
                    curr: 1, // 重置为第一页
                },
                done: function () {
                    // 恢复初始URL
                    myTable.config.url = initUrl;
                }
            });
        });
        //行工具条
        table.on('tool(layFilter)', function (obj) {
            var data = obj.data;
            var ids = [];
            ids.push(data.id);
            if (obj.event === 'del') {
                layer.confirm('请确认删除', function (index) {
                    $.post(
                        '${pageContext.request.contextPath}/user/deleteByIds?ids=' + ids,
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
            }
        });

        table.on('toolbar(layFilter)', function (obj) {
            var checkStatus = table.checkStatus(obj.config.id);
            switch (obj.event) {
                case 'deleteAll':
                    var data = checkStatus.data;
                    var ids = [];
                    $(data).each(function () {
                        ids.push(this.id);
                    });
                    layer.confirm('请确认删除', function (index) {
                        $.post(
                            '${pageContext.request.contextPath}/user/deleteByIds?ids=' + ids,
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
                <%--case 'search' :--%>
                <%--    var openId = $('#openId').val();--%>
                <%--    table.reload('#test', {--%>
                <%--        url: '${pageContext.request.contextPath}/user/getByOpenId?openId=' + openId--%>
                <%--    });--%>
                <%--    break;--%>
                case 'LAYTABLE_TIPS':
                    layer.alert('这是工具栏右侧自定义的一个图标按钮');
                    break;
            }
        });
    });
</script>
</body>
</html>
