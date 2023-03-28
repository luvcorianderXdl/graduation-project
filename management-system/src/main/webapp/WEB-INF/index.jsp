<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>主页</title>
    <%@ include file="header.jsp" %>
</head>
<body class="layui-layout-body">
<script type="text/javascript">
</script>
<div class="layui-layout layui-layout-admin">
    <div class="layui-header">
        <div class="layui-logo">后端管理系统-Nyl&Xdl</div>
        <ul class="layui-nav layui-layout-left"></ul>
        <ul class="layui-nav layui-layout-right">
            <li class="layui-nav-item">
                <a href="javascript:;">
                    ${adminUser.name}
                </a>
                <dl class="layui-nav-child">
                    <dd>
                        <%--                        <a class="site-demo-active"  data-url="<%=request.getContextPath()%>/adminUser/cp" href="javascript:;">修改密码</a>--%>
                        <a href="javascript:changePass()">修改密码</a>
                    </dd>
                </dl>
            </li>
            <li class="layui-nav-item">
                <a href="javascript:logout()">注销</a>
            </li>
        </ul>
    </div>
    <div class="layui-side layui-bg-black">
        <div class="layui-side-scroll">
            <!-- 左侧导航区域（可配合layui已有的垂直导航） -->
            <ul class="layui-nav layui-nav-tree">
                <li class="layui-nav-item layui-nav-itemed">
                    <a href="javascript:;">人员管理</a>
                    <dl class="layui-nav-child">
                        <dd>
                            <a href="javascript:;"
                               data-url="<%=request.getContextPath()%>/adminUser/getAdminUserListPage"
                               class="site-demo-active">管理员管理</a>
                        </dd>
                        <dd>
                            <a href="javascript:;"
                               data-url="/user/getUserListPage"
                               class="site-demo-active">用户管理</a>
                        </dd>
                    </dl>
                </li>
                <li class="layui-nav-item">
                    <a href="javascript:;">数据管理</a>
                    <dl class="layui-nav-child">
                        <dd>
                            <a href="javascript:;"
                               data-url="<%=request.getContextPath()%>/teacher"
                               class="site-demo-active">作品管理</a>
                        </dd>
                        <dd>
                            <a href="javascript:;"
                               data-url="<%=request.getContextPath()%>/teacher"
                               class="site-demo-active">板块管理</a>
                        </dd>
                        <dd>
                            <a href="javascript:;"
                               data-url="<%=request.getContextPath()%>/teacher"
                               class="site-demo-active">标签管理</a>
                        </dd>
                        <a href="javascript:;"
                           data-url="<%=request.getContextPath()%>/teacher"
                           class="site-demo-active">评论管理</a>
                        </dd>
                    </dl>
                </li>
            </ul>
        </div>
    </div>
    <div class="layui-body"><!-- 内容主体区域 -->
        <%--        <iframe name="rightframe" width="99%" height="97%" src="<%=request.getContextPath()%>/echarts.jsp"></iframe>--%>
        <iframe name="rightframe" width="99%" height="97%"></iframe>

    </div>
    <div class="layui-footer">© JAVA - 底部固定区域</div>
</div>
<script type="text/javascript">
    var $ = layui.jquery;
    var layer = layui.layer;
    var element = layui.element;
    $('.site-demo-active').click(function () {
        window.open($(this).data('url'), "rightframe");
    });
    element.render();// element.init();
    function openURL(url) {
        window.open(url, "rightframe");
    }

    function logout() {
        layer.confirm(
            '您确认要退出么',
            {icon: 3},
            function () {
                location.href = '<%=request.getContextPath()%>/adminUser/logout'
            }
        );
    }

    function changePass() {
        layer.open({
            type: 2,
            title: "修改密码",
            area: ['400px', '300px'],
            //转发式(jsp位于WEB-INF)
            content: '${pageContext.request.contextPath}/adminUser/getChangePasswordPage'
            //直接访问(jsp位于webapp直接下级 不建议使用)
            <%--content: '<%=request.getContextPath()%>/adminUser_changePassword.jsp'--%>
        });
    }

</script>
</body>
</html>
