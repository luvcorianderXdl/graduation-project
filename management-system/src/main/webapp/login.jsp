<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>登录</title>
    <%@ include file="header.jsp"%>
    <link rel="stylesheet" type="text/css" href="/static/css/login.css">
</head>
<body>
<div class = "layui-container">
    <div class="admin-login-background">
        <div class="logo-title">
            <h1>后端管理系统</h1>
        </div>
        <form id="formId" method="post" class="layui-form">
            <div class="layui-form-item">
                <label class="layui-icon layui-icon-username" for="username"></label>
                <input type="text" id="username" name="loginId" lay-verify="required|account" placeholder="请输入用户名" autocomplete="off" class="layui-input">
            </div>
            <div class="layui-form-item">
                <label class="layui-icon layui-icon-password" for="password"></label>
                <input type="password" id="password" name="password" lay-verify="required|password" placeholder="请输入密码" autocomplete="off" class="layui-input">
            </div>
            <div class="layui-form-item">
                <label class="layui-icon layui-icon-vercode" for="captcha"></label>
                <div class="code">
                    <input id="captcha" type="text" name="code" lay-verify="required|captcha" placeholder="请输入验证码" autocomplete="off" class="layui-input">
                    <img id="verifyCodeId" src="${pageContext.request.contextPath}/auth/code" onclick="refresh()"><br/>
                </div>
            </div>
            <div class="layui-form-item">
                <div class="logo-title">
                    <input class="mylogin" type="button" onclick="submitForm()" value="登 录"/>
                </div>
            </div>
        </form>
    </div>
</div>
<script>
    function refresh() {
        $('#verifyCodeId').attr('src', '${pageContext.request.contextPath}/auth/code?' + Math.random());
    }

    function submitForm() {
        $.post(
            '/adminUser/login',
            $('#formId').serialize(),
            function(jsonResult) {
                console.log(jsonResult);
                if (jsonResult.code == 0) {
                    mylayer.okUrl(jsonResult.msg, '${pageContext.request.contextPath}/')
                } else {
                    mylayer.errorMsg(jsonResult.msg);
                    refresh();
                }
            },
            'json'
        );
    }
</script>
</body>
</html>
