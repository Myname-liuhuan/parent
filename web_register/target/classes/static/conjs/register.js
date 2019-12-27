layui.use(['layer','form'], function() {
    var layer = layui.layer,
        form = layui.form;


    //全局的当前验证码 用来验证是否匹配
    var codeNum = '';
    //将全局的验证码初始化 防止 空字符串的相等
    randomCode(5);


    listener();

    function listener() {

        //声明一系列的全局变量作为是否提交的标志位
        var isUsername = false;
        var isPhone = false;
        var isCode = false;

        //监听用户名是否存在 失去焦点事件
        $("#username").blur(function () {
            var usernameVal = $(this).val();
            if (usernameVal.length < 4 || usernameVal.length >12){
                layer.tips('用户名格式错误', "#username", {tips: [2,'#fc1c1d'], time:3000});
            }else{
                var paramJson = {};
                paramJson["username"]=usernameVal;
                isUsername = checkExisted("#username",paramJson,"用户名");
            }
        });


        //监听手机号是否重复
        $("#phone").blur(function () {
            var  phoneVal = $(this).val();
            //先进行正则验证 满足后才进行 访问后台的验证
            if (/^1[3456789]\d{9}$/.test(phoneVal)){
                var paramJson  = {};
                paramJson["phone"] = phoneVal;
                isPhone = checkExisted("#phone",paramJson,"手机号");
            } else{
                layer.tips('手机号格式错误', "#phone", {tips: [2,'#fc1c1d'], time:3000});
            }
        });

        //监听发送短信按钮 点击就发送短信验证码
        $("#send_btn").click(function () {
            var paramsJson = {};
            paramsJson["phone"] = $("#phone").val();
            paramsJson["code"] = randomCode(6);
            $.ajax({
                url:"webusers/sendMsg",
                type:"post",
                dataType:"text",
                data:paramsJson,
                success:function (data) {
                    if (data == 'success'){
                        //提示已发送 让按钮不可点击 并且读秒
                        layer.msg("短信验证码已发送 请注意查收!");
                        setSendButton();
                    } else{
                        layer.msg("发送失败 请再次尝试!");
                    }
                },
                error:function () {
                    layer.msg("发送短信 服务器错误!");
                }
            });
        });

        //监听短信验证码框去本地验证是否匹配
        $("#userSms").blur(function () {
            var code = $(this).val();
            if(code == codeNum){
                isCode = true;
                layer.tips('验证码正确', "#userSms", {tips: [4,'#63fc41'], time:3000});
            }else{
                layer.tips('验证码错误', "#userSms", {tips: [4,'#fc1c1d'], time:3000});
            }
        });

        //表单提交(注册按钮)的监听
        form.on("submit(registerButtonFilter)",function (formData) {
            //满足所有条件就发送请求 不然就提示不正确的item项
            if (isUsername == false){
                layer.tips('用户名已存在', "#username", {tips: [2,'#c62e3d'], time:3000});
            }else if(isPhone == false) {
                layer.tips('手机号已存在', "#phone", {tips: [2,'#c62e3d'], time:3000});
            }else if (isCode == false){
                layer.tips('验证码错误', "#userSms", {tips: [2,'#c62e3d'], time:3000});
            }else if (!checkPassword()){
                layer.tips('两次输入的密码不同', "#pwd2", {tips: [2,'#c62e3d'], time:3000});
            } else{

                //最后当所有的条件都为true 即可先后台发起请求
                var paramJson = formData.field;
                //添加json 元素
                paramJson["uname"]= "无名氏";
                paramJson["updatetime"] = getStringOfDate(new Date());
                paramJson["userheader"] = "http://q1ivuydqx.bkt.clouddn.com/headimage.PNG";
                $.ajax({
                    url:"webusers/insert",
                    type:"post",
                    dataType:"text",
                    async:false,
                    data:paramJson,
                    success:function (data) {
                        if (data == 'success'){
                            //注册成功 就跳转到主界面
                            layer.msg("注册成功");
                            //马上将所有的标志位置false 防止提交重复数据
                            isUsername =false;
                            isPhone = false;
                            isCode = false;
                        } else{
                            //注册失败 提示
                            layer.msg("注册失败 请刷新页面后重试!");
                        }
                    },
                    error:function () {
                        layer.msg("注册服务器错误!");
                    }
                });
            }
        });
    }

    /*******************************分割线*************************************/

    function checkExisted(jQueryId,paramJson,tipStr) {
        var isCouldUse = false;
        $.ajax({
            url:"webusers/checkExisted",
            type:"POST",
            async:false,
            dataType:"text",
            data:paramJson,
            success:function (data) {
                if (data=='success'){
                    layer.tips(tipStr+'不可用', jQueryId, {tips: [2,'#c62e3d'], time:3000});
                }else{
                    isCouldUse = true;
                    layer.tips(tipStr+'可用', jQueryId, {tips: [2,'#4dc641'], time:3000});
                }
            },
            error:function () {
                layer.msg("服务器错误!");
            }
        });
        return isCouldUse;
    }

    /**
     * 操作是先将按钮不可用 然后每秒将其val值减一 结束后变为可用 且value值为"发送短信"
     */
    var i = 60;
    var interval
    function setSendButton() {
        $("#send_btn").attr("disabled", "true");
        $("#send_btn").css("background-color","grey");
        //每秒执行一次该句柄
        interval = window.setInterval(showSecond,1000);
    }

    function showSecond() {
        i--;
        if (i == 0){
            //清除该计时器 并且将按钮设置为可用 value变为 "发送短信"
            window.clearInterval(interval);
            $("#send_btn").removeAttr("disabled");
            $("#send_btn").css("background-color","blue");
            $("#send_btn").val("发送短信");
        }else{
            $("#send_btn").val(i);
        }
    }


    //自定义验证
    form.verify({
        pwd: [/^[\S]{6,18}$/,'登录密码必须6到18位，且不能出现空格']
        ,pwd2: function(value, item){ //value：表单的值、item：表单的DOM对象
            var pwdVal = $("#pwd").val();
            if(value!=pwdVal){
                return '两次密码不一致';
            }
        }
    });


    //产生的随机数一式两份 一份给全局变量codeNum 一份返回给调用者
    function randomCode(len) {
        var code = '';
        for (var i = 0; i < len; i++){
            var n = Math.floor(Math.random()*10);
            if (i == 0 && n == 0){
                n = 1;
            }
            code += ''+n;
        }
        codeNum = code;
        return code;
    }

    //获取时间对应的字符串     Date()   ---->  yyyy-MM-dd HH:mm:ss 格式的字符串
    function getStringOfDate(date) {
        var sign1 = "-";
        var sign2 = ":";
        var year = date.getFullYear(); // 年
        var month = date.getMonth() + 1; // 月
        var day  = date.getDate(); // 日
        var hour = date.getHours(); // 时
        var minutes = date.getMinutes(); // 分
        var seconds = date.getSeconds(); //秒
        if (month >= 1 && month <= 9) {
            month = "0" + month;
        }
        if (day >= 0 && day <= 9) {
            day = "0" + day;
        }
        if (hour >= 0 && hour <= 9) {
            hour = "0" + hour;
        }
        if (minutes >= 0 && minutes <= 9) {
            minutes = "0" + minutes;
        }
        if (seconds >= 0 && seconds <= 9) {
            seconds = "0" + seconds;
        }
        var currentdate = year + sign1 + month + sign1 + day + " " + hour + sign2 + minutes + sign2 + seconds ;
        return currentdate;
    }
    
    //验证两次密码是否相等
    function checkPassword() {
        var pwd = $("#pwd").val();
        var pwd2 = $("#pwd2").val();
        if (pwd == pwd2) {
            return true;
        }
        return false;
    } 
});