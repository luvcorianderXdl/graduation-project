<%@ page import="com.qdu.graduationProject.managementSystem.vo.AdminUserToRoleTableInfoVo" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>修改管理员权限</title>
    <%@ include file="header.jsp" %>
    <style>
        #myTableDiv {
            position: relative;
            top: 5px;
            left: 50px;
        }
    </style>
</head>
<body>
<%
    List<AdminUserToRoleTableInfoVo> vos = (List<AdminUserToRoleTableInfoVo>) request.getAttribute("vos");
%>
<form id="formId" class="layui-form layui-form-pane" action="">
    <div class="layui-form-item" id="myTableDiv">
        <table id="myTable">
            <tbody>
            <% for (int i = 0; i < vos.size(); i++) { %>
            <tr>
                <td><input type="checkbox" name="check" class="layui-input"
                           value="<%=vos.get(i).getRoleId()%>" <% if (vos.get(i).getCheck() == 1) { %>
                           checked <% } %>></td>
                <td><%=vos.get(i).getRoleName()%>
                </td>
                <td>
                    <input type="hidden" name="adminUserId" value="<%=vos.get(i).getAdminUserId()%>">
                </td>
            </tr>
            <% } %>
            </tbody>
        </table>
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
        var roles = [];
        $('input[type="checkbox"]').each(function () {
            var value = $(this).val();
            var checked = $(this).prop('checked');
            if (checked) {
                roles.push(value);
            }
        });
        if (roles.length === 0) {
            roles = null;
        }
        var vo = {
            roles: roles,
            adminUserId:<%=vos.get(0).getAdminUserId()%>
        };
        $.post(
            '${pageContext.request.contextPath}/adminUserToRole/update',
            vo,
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
            'json',
            {
                beforeSend: function (xhr) {
                    xhr.setRequestHeader('Content-Type', 'application/json;charset=UTF-8');
                }
            });
    }
</script>
</body>
</html>

