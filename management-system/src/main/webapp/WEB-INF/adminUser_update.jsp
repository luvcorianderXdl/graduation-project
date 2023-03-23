<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>修改管理员信息</title>
    <%@ include file="header.jsp" %>
</head>
<body>
<form id="formId" class="layui-form layui-form-pane" action="">
    <input type="hidden" name="id" value="${adminUser.id}"/>
    <div class="layui-form-item">
        <label class="layui-form-label">用户名</label>
        <div class="layui-input-block">
            <input type="text" name="name" value="${adminUser.name}" autocomplete="off" placeholder="请输入用户名"
                   class="layui-input">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">电话号码</label>
        <div class="layui-input-block">
            <input type="text" name="tels" value="${adminUser.tels}" autocomplete="off" placeholder="请输入电话号码"
                   class="layui-input">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">邮箱</label>
        <div class="layui-input-block">
            <input type="text" name="emails" value="${adminUser.emails}" autocomplete="off" placeholder="请输入邮箱"
                   class="layui-input">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">账号描述</label>
        <div class="layui-input-block">
            <input type="text" name="description" value="${adminUser.description}" autocomplete="off"
                   placeholder="请输入账号描述"
                   class="layui-input">
        </div>
    </div>
    <div class="layui-form-item">
        <div class="layui-input-block">
            <button type="button" onclick="submitForm()" class="layui-btn" lay-submit="" lay-filter="demo1">立即提交
            </button>
            <button type="reset" class="layui-btn layui-btn-primary">重置</button>
        </div>
    </div>
</form>
<script>

    layui.use(['form', 'laydate'], function () {
        var form = layui.form;
        var laydate = layui.laydate;
    });

    function submitForm() {
        $.post(
            '${pageContext.request.contextPath}/adminUser/updateAdminUser',
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
