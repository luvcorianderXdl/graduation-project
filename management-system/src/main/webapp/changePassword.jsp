<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>修改密码</title>
    <%@ include file="header.jsp"%>
</head>
<body>
<form action=" " id="formId" method="post">
    <div class="data_form" >
        <table align="center">
            <tr>
                <td><font color="red">*</font>原密码：</td>
                <td><input type="password"  id="oldPassword"  name="oldPass" value=""  style="margin-top:5px;height:30px;" /></td>
            </tr>
            <tr>
                <td><font color="red">*</font>新密码：</td>
                <td><input type="password" id="newPassword"  name="newPass" value="" style="margin-top:5px;height:30px;" /></td>
            </tr>
            <tr>
                <td><font color="red">*</font>重复密码：</td>
                <td><input type="password" id="rPassword"  name="confirmPass" value="" style="margin-top:5px;height:30px;" /></td>
            </tr>
        </table>
        <div align="center">
            <input type="button" class="btn btn-primary" onclick="submitForm()" value="提交"/>
        </div>
        <div align="center">
            <font id="error" color="red"></font>
        </div>
    </div>
</form>
<script>
    function submitForm() {
        $.post(
            '/adminUser/changePassword',
            $('#formId').serialize(),
            function(jsonResult) {
                console.log(jsonResult);
                if (jsonResult.code == 0) {
                    mylayer.okUrl(jsonResult.msg + ',请重新登陆', '${pageContext.request.contextPath}/adminUser/logout')
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
