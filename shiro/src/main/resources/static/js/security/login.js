layui.use(['layer', 'form'], function () {
    var layer = layui.layer,
        $ = layui.jquery,
        form = layui.form;
    //背景图像
    $(document).ready(function () {
        var srcBgArray = ["../../images/genie.jpg", "../../images/weizhuang.jpg", "../../images/tianyan.jpg", "../../images/shaosiming.jpg", "../../images/yueer.jpg"];
        $('#bg-body').bcatBGSwitcher({timeout: 5000, urls: srcBgArray, alt: '全屏背景图像'});
    });
    //验证码
    $("#mycode").on('click', function () {
        var t = Math.random();
        $("#mycode")[0].src = "/genCaptcha?t=" + t;
    });
    //登录
    form.on('submit(login)', function (data) {
        var loadIndex = layer.load(2, {
            shade: [0.3, '#333']
        });
        if ($('#formId').find('input[type = "checkbox"]')[0].checked) {
            data.field.rememberMe = true;
        } else {
            data.field.rememberMe = false;
        }
        $.post(data.form.action, data.field, function (res) {
            layer.close(loadIndex);
            if (res.status == "200") {
                location.href = "/" + res.data;
            } else {
                layer.msg(res.message);
                $("#mycode").click();
            }
        });
        return false;
    });
});