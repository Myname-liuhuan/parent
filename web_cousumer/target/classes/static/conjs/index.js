$(function () {

    var token = '';

    //加载所有的导航菜单
    loadAllMenu();
    //加载轮播图
    loadBanner();
    //加载登录信息
    loadToken();

    //加载所有导航菜单
    function loadAllMenu() {
        $.ajax({
            type:'POST',
            url:'/webmenu/loadAll',
            dataType:'JSON',
            success:function (data) {
                var menuLis = '';
                $.each(data,function (i,menu) {
                    menuLis += '<li><a href="'+menu.url+'">'+menu.title+'</a></li>';
                });
                $(".menu_r").html(menuLis);
            },
            error:function () {
                console.log("服务器异常!")
            }
        });
    }


    function loadBanner() {
        $.ajax({
            type:'POST',
            url:'/webbanner/loadAll',
            dataType:'JSON',
            success:function (data) {
                var bannerLis = '';
                $.each(data,function (i,banner) {
                    bannerLis += '<li><img src="'+banner.imgurl+'"/> </li>';
                });
                //填充图片
                $("#bxslider").html(bannerLis);
                //开启轮播 只能放到ajax里面 外面的话会出现不生效的情况 是因为ajax 默认异步请求 会先轮播再加载图片 然后导致轮播功能失效
                $(".bxslider").bxSlider({
                    auto:true,
                    prevSelector:jq(".top_slide_wrap .op_prev")[0],nextSelector:jq(".top_slide_wrap .op_next")[0]
                });
            },
            error:function () {
                console.log("服务器异常!")
            }
        });
    }

    /**
     * 获取到单点登录用户的cookie中的token值
     */
    function loadToken() {
        jQuery.ajax({
            url:"http://localhost:7079/webusers/getCookieValue",
            dataType:"text",
            type:"POST",
            async:false,
            xhrFields:{withCredentials:true},
            success:function (data) {
                if (data != ""){
                    token = data;
                    loadUserEntity(data);
                }
            },
            error:function () {
                console.log("登录加载服务器错误!")
            }
        });
    }

    /**
     * 通过token值 获取到登录的用户信息
     * @param token
     */
    function loadUserEntity(token) {
        jQuery.ajax({
            url:"http://localhost:7079/webusers/getUserEntity",
            dataType:"json",
            data:{"token":token},
            async:false,
            type:"post",
            success:function (data) {
                if (data != null && data.length != 0){
                    //将页面上的未登录改为登录信息
                    let loginInfo = '你好，'+data.uname+'&nbsp; <img src=" '+data.userheader+'"/><a href="#" id="signOut" style="color:#ff4e00;">注销</a>&nbsp;|&nbsp;<a href="#">我的订单</a>&nbsp;|';
                    jQuery("#loginInfo").html(loginInfo);
                }else{
                    console.log("登录失败");
                }
            },
            error:function () {
                console.log("获取redis中用户信息服务器错误!")
            }
        });
    }

    jQuery("#signOut").click(function () {
        if (confirm("确定注销登录吗?")){
            signOut();
        }
    });

    //注销登录
    function signOut() {
        jQuery.ajax({
            url:"http://localhost:7079/webusers/signOut",
            dataType:"text",
            data:{"token":token},
            async:false,
            type:"post",
            xhrFields:{withCredentials:true},
            success:function (data) {
                if (data == "success"){
                    //将页面上的未登录改为登录信息
                    let loginInfo = '你好，请<a href="http://localhost:7079/model/toLogin">登录</a>&nbsp; <a href="http://localhost:8890/model/toRegister" style="color:#ff4e00;">免费注册</a>&nbsp;|&nbsp;<a href="#">我的订单</a>&nbsp;|';
                    jQuery("#loginInfo").html(loginInfo);
                }else{
                    console.log("注销失败");
                }
            },
            error:function () {
                console.log("获取redis中用户信息服务器错误!")
            }
        });
    }

    /**
     * 简介一下轮询方式加载页面 当该项目日常用户数比较多时不推荐轮询 因为不断的向服务器轮询会浪费大量资源
     * 思路：
     * 在刚加载页面的时候开始轮询获取token的方法 如果获取到就停止轮询 没有获取到 就不断的调用getToken。
     */
});