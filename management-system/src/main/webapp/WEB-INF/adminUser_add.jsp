<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>添加管理员</title>
    <%@ include file="header.jsp" %>
</head>
<body>
<form id="formId" class="layui-form layui-form-pane" action="" method="post">
    <div class="layui-form-item">
        <label class="layui-form-label">用户名</label>
        <div class="layui-input-block">
            <input type="text" name="name" value="" required autocomplete="off" placeholder="请输入用户名"
                   class="layui-input">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">登录id</label>
        <div class="layui-input-block">
            <input type="text" name="loginId" value="" required autocomplete="off" placeholder="请输入登录Id"
                   class="layui-input">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">手机号</label>
        <div class="layui-input-block">
            <input type="text" name="tels" value="" required autocomplete="off" placeholder="请输入手机号,多个手机号用英文逗号隔开"
                   class="layui-input">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">邮箱</label>
        <div class="layui-input-block">
            <input type="text" name="emails" value="" required autocomplete="off" placeholder="请输入邮箱，多个邮箱用英文逗号隔开"
                   class="layui-input">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">账号描述</label>
        <div class="layui-input-block">
            <input type="text" name="description" value="" required autocomplete="off" placeholder="请输入账号描述"
                   class="layui-input">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">密码</label>
        <div class="layui-input-block">
            <input type="text" name="password" value="" required autocomplete="off" placeholder="请输入密码"
                   class="layui-input">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">确认密码</label>
        <div class="layui-input-block">
            <input type="text" name="confirmPass" value="" required autocomplete="off" placeholder="请输入密码"
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
    function submitForm() {
        $.post(
            '/adminUser/addAdminUser',
            $('#formId').serialize(),
            function (jsonResult) {
                console.log(jsonResult);
                if (jsonResult.code == 0) {
                    var index = parent.layer.getFrameIndex(window.name);
                    layer.msg(
                        jsonResult.msg,
                        {icon: 1, time: 1500},
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
