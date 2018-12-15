layui.use(['form', 'layer', 'laydate', 'jquery'], function () {
    var form = layui.form,
        layer = parent.layer === undefined ? layui.layer : top.layer,
        laydate = layui.laydate,
        $ = layui.jquery;

    /*$(".loginBody .seraph").click(function () {
        layer.msg("这只是做个样式，至于功能，你见过哪个后台能这样登录的？还是老老实实的找管理员去注册吧", {
            time: 5000
        });
    });*/
    //登录按钮
    /*form.on("submit(login)", function (data) {
        $(this).text("登录中...").attr("disabled", "disabled").addClass("layui-disabled");
        setTimeout(function () {
            window.location.href = "/login/login";
        }, 1000);
        return false;
    });*/
    form.on("submit(login1)", function (data) {
        var loginName = $("#loginName").val();
        var password = $("#password").val();
        var code = $("#code").val();
        var rememberMe =$('#rememberMe').is(':checked');
        //checkParams();
        $.ajax({
            url: '/login/login',
            type: 'POST',
            dataType: 'json',
            data: {"loginName": loginName, "password": password, "code": code, "rememberMe": rememberMe},
            success: function (data) {
                console.log(data);
                if (data.status == "200") {
                    window.location.href = "/index";
                    /*layer.alert("登录成功", function () {
                        window.location.href = "/index";
                    });*/
                } else {
                    $("#code").val("");
                    layer.alert(data.message, function () {
                        layer.closeAll();//关闭所有弹框
                    });
                }
            }
        });
        return false;
    });

    var path = window.location.href;
    if(path.indexOf("kickout")>0){
        layer.alert("您的账号已在别处登录；若不是您本人操作，请立即修改密码！",function(){
            window.location.href = "/";
        });
    }
    //表单输入效果
    $(".loginBody .input-item").click(function (e) {
        e.stopPropagation();
        $(this).addClass("layui-input-focus").find(".layui-input").focus();
    });

    $(".loginBody .layui-form-item .layui-input").focus(function () {
        $(this).parent().addClass("layui-input-focus");
    });

    $(".loginBody .layui-form-item .layui-input").blur(function () {
        $(this).parent().removeClass("layui-input-focus");
        if ($(this).val() != '') {
            $(this).parent().addClass("layui-input-active");
        } else {
            $(this).parent().removeClass("layui-input-active");
        }
    });
});
//校验
function checkParams(){
    var loginName = $("#loginName").val();
    var password = $("#password").val();
    var code = $("#code").val();
    if("ok" != ValidateUtils.checkUserName(loginName) || "ok" != ValidateUtils.checkSimplePassword(password)){
        layer.alert("请您输入正确的用户名和密码");
        return false;
    }
    if("ok" != ValidateUtils.checkPicCode(code)){
        layer.tips(ValidateUtils.checkPicCode(code), '#canvas', {
            tips: [2, '#78BA32'],//还可配置颜色
            tipsMore: true
        });
        return false;
    }
    if(picCode.toLowerCase() != code.toLowerCase()){
        layer.tips("请您输入正确的验证码", '#canvas', {
            tips: [2, '#78BA32'],//还可配置颜色
            tipsMore: true
        });
        return false;
    }
}