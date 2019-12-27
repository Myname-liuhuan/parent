$(function () {

    //加载所有的导航菜单
    loadAllMenu();

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

});