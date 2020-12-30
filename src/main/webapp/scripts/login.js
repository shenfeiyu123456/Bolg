//给按钮绑定单击事件
$(function () {
    $("#login").click(login);
    $("#regist_button").click(regist);
})

//登录处理
function login() {
    //1.获取请求参数
    var username = $("#count").val().trim();
    var password = $("#password").val().trim();
    //添加JS/CSS样式之前先去除对应的样式
    $("#count_span").html("");
    $("#password_span").html("");
    //2.参数格式校验
    var ok = true;
    if (username == "") {
        ok = false;
        $("#count_span").html("用户名为空");
    }
    if (password == "") {
        ok = false;
        $("#password_span").html("密码为空");
    }
    if (ok) {
        //3.发送Ajax
        $.ajax({
            url: base_path + "/user/login.do",
            type: "post",
            data: {"username": username, "password": password},
            dataType: "json",
            success: function (result) {
                if (result.status == 1) {
                    $("#count_span").html(result.msg);
                }else if(result.status == 2){
                    $("#password_span").html(result.msg);
                }else{
                    //把用户信息放到cookie中
                    addCookie("uid",result.data.cn_user_id,2);
                    addCookie("uname",result.data.cn_user_name,2);
                    window.location.href='edit.html';
                }
            },
            error: function () {
                alert("登录异常");
            }
        });
    }


}
//注册处理
function regist() {
    //1.获取请求参数
    //用户名
    var username = $("#regist_username").val().trim();
    //昵称
    var nick = $("#nickname").val().trim();
    //密码
    var passwrod = $("#regist_password").val().trim();
    //确认密码
    var f_pwd = $("#final_password").val().trim();
    //2.对请求参数进行格式校验
    $("#warning_1 span").html("");
    $("#warning_2 span").html("");
    $("#warning_3 span").html("");
    var ok = true;
    if(username == ""){
        ok = false;
        $("#warning_1").show("warning")
        $("#warning_1 span").html("用户名为空");
    }
    if(passwrod == ""){
        ok = false;
        $("#warning_2").show("warning")
        $("#warning_2 span").html("密码不能为空");
    }else if(passwrod.length < 6){
        ok = false;
        $("#warning_2").show("warning")
        $("#warning_2 span").html("密码过短");
    }else if(passwrod != f_pwd) {
        ok = false;
        $("#warning_3").show("warning")
        $("#warning_3 span").html("密码输入不一致");
    }
    if(ok){
        //3.发送Ajax
        $.ajax({
            url:base_path+"/user/add.do",
            type:"post",
            data:{"username":username,"password":passwrod,"nick":nick},
            dataType:"json",
            success:function (result) {
                if(result.status == 1){
                    $("#warning_1").show("warning")
                    $("#warning_1 span").html(result.msg);
                }else{
                    alert(result.msg);
                    //注册成功，返回登录页面
                    $("#back").click();
                }
            },
            error:function () {
                alert("用户注册异常");
            }
        });
    }

}