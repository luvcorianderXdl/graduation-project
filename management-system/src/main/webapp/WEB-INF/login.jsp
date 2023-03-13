<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <%@ include file="header.jsp"%>
</head>
<body>
<form id="formId" method="post">
    用户名:<input type="text" name="username"/> <br/>
    密码: <input type="password" name="password"/><br/>
    验证码：<input type="text" name="code"/>
    <img id="verigyCodeId" src="${pageContext.request.contextPath}/auth/code" onclick="refresh()"><br/>
    <input type="button" onclick="submitForm()" value="登录"/>
</form>

<script>
    function refresh() {
        $('#verigyCodeId').attr('src', '${pageContext.request.contextPath}/auth/code?' + Math.random());
    }

    function submitForm() {
        // {'name':'zhansgan', 'age':23}
        // console.log($('#formId').serialize());
        $.post(
            '/user/login',
            $('#formId').serialize(),
            function(jsonResult) {
                console.log(jsonResult);
                if (jsonResult.code == 0) {
                    mylayer.okUrl(jsonResult.msg, '${pageContext.request.contextPath}/')
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
