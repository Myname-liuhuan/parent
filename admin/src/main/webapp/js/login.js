layui.use(['jquery','form','table','laydate','layer'],function () {
    var $ = layui.jquery,
        form = layui.form,
        table = layui.table,
        layer = layui.layer,
        laydate = layui.laydate;

    init();

    function init(){
        //如果拦截提示不为"" 就将其弹出
        if ($("#backMsg").val()!=""){
            layer.msg($("#backMsg").val());
        }

        listener();
    }

    function listener() {
        var isSubmit = false;

        /**
         * 监听验证码输入框 失去焦点事件 验证验证码的正确性
         */
        $("#verifyCode").blur(function () {
            $.ajax({
                url:"login/checkVerifyCode",
                type:"POST",
                dataType:"text",
                data:{"checkCode":$(this).val()},
                success:function (data) {
                    if (data == "true"){
                        isSubmit =true;
                        layer.tips("验证码正确!","#verifyCode",{tips:[2,"#66fc55"],time:3000});
                    }else{
                        layer.tips("验证码错误!","#verifyCode",{tips:[2,"#fc1505"],time:3000});
                    }
                },
                error:function () {
                    layer.msg("验证码服务器错误!");
                }
            });
        });

        /**
         * 表单提交的监听
         */
        form.on("submit(loginButtonFilter)",function (formData) {
            //验证账号密码的正确性
            console.log(formData.field);
            if (isSubmit){
                $.ajax({
                    url:"user/loginCheck",
                    type:"POST",
                    dataType:"text",
                    data:formData.field,
                    success:function (data) {
                        if (data == "success"){
                            window.location="authority/authList";
                        }else{
                            layer.msg("账户名或者密码错误!");
                        }
                    },
                    error:function () {
                        layer.msg("登录服务器错误!");
                    }
                });
            }else{
                layer.tips("请正确填写验证码","#verifyCode",{tips:[2,"#fc1505"],time:3000});
            }
            return false;
        });
    }
});