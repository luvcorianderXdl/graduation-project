<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>修改板块信息</title>
    <%@ include file="header.jsp" %>
</head>
<body>
<form id="formId" class="layui-form layui-form-pane" action="">
    <input type="hidden" name="id" value="${section.id}"/>
    <div class="layui-form-item">
        <label class="layui-form-label">板块名称</label>
        <div class="layui-input-block">
            <input type="text" name="sectionName" value="${section.sectionName}" autocomplete="off" placeholder="请输入用户名"
                   class="layui-input">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">描述</label>
        <div class="layui-input-block">
            <input type="text" name="description" value="${section.description}" autocomplete="off"
                   placeholder="请输入电话号码"
                   class="layui-input">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">图片上传</label>
        <div class="layui-input-block">
            <div class="layui-upload">
                <div class="layui-upload-list">
                    <img class="layui-upload-img" src="${section.sectionImage}" id="demo1" width="150px" height="150px">
                    <input type="hidden" name="sectionImage" id="sectionImage" value="${section.sectionImage}"/>
                    <p id="demoText"></p>
                </div>
                <button type="button" class="layui-btn" id="test1">上传图片</button>
            </div>
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
    var form;
    layui.use(['form', 'upload'], function () {
        form = layui.form;
        var upload = layui.upload;

        //常规使用 - 普通图片上传
        var uploadInst = upload.render({
            elem: '#test1'
            , url: '/upload/upload2section'
            , before: function (obj) {
                //预读本地文件示例，不支持ie8
                obj.preview(function (index, file, result) {
                    $('#demo1').attr('src', result); //图片链接（base64）
                });
                // element.progress('demo', '0%'); //进度条复位
                layer.msg('上传中', {icon: 16, time: 0});
            }
            ,
            done: function (jsonResult) {
                layer.closeAll('dialog');
                //如果上传失败
                if (jsonResult.code == 1) {
                    return layer.msg('上传失败');
                } else {
                    layer.msg('上传完毕', {icon: 1});
                }
                //上传成功的一些操作
                //……
                $('#sectionImage').val(jsonResult.data);
            }
        });
    });

    function submitForm() {
        $.post(
            '${pageContext.request.contextPath}/section/updateSection',
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
