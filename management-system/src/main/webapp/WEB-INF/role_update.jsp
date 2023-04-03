<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>修改角色信息</title>
    <%@ include file="header.jsp" %>
</head>
<body>
<form id="formId" class="layui-form layui-form-pane" action="">
    <input type="hidden" name="id" value="${role.id}"/>
    <div class="layui-form-item">
        <label class="layui-form-label">名称</label>
        <div class="layui-input-block">
            <input type="text" name="name" value="${role.name}" autocomplete="off" placeholder="请输入用户名"
                   class="layui-input">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">描述</label>
        <div class="layui-input-block">
            <input type="text" name="description" value="${role.description}" autocomplete="off" placeholder="请输入用户名"
                   class="layui-input">
        </div>
    </div>
    <div class="layui-input-block">
        <button type="button" onclick="submitForm()" class="layui-btn" lay-submit="" lay-filter="demo1">立即提交
        </button>
        <button type="reset" class="layui-btn layui-btn-primary">重置</button>
    </div>
    </div>
</form>
<script>
    function submitForm() {
        $.post(
            '${pageContext.request.contextPath}/role/updateRole',
            $('#formId').serialize(),
            function (jsonResult) {
                console.log(jsonResult);
                if (jsonResult.code == 0) {
                    // mylayer.okMsg(jsonResult.msg);
                    // 获得当前弹出框的index
                    var index = parent.layer.getFrameIndex(window.name);
                    layer.msg(
                        jsonResult.msg,
                        {icon: 1, time: 1000},
                        function () { // msg弹出1秒后消失触发这个函数
                            // 关闭弹出层
                            parent.layer.close(index);
                            // 刷新父页面
                            window.parent.location.reload();
                        }
                    );
                } else {
                    mylayer.errorMsg(jsonResult.msg);
                }
            },
            'json'
        );
    }
</script>
</body>
</html>
