function login() {

    //点击登录按钮,获取用户名和密码属性
    $("#loginBtn").click(function () {

        //校验业务逻辑,用户名不能为空,密码也不能为空
        var loginAct = $("#loginAct").val();

        //对用户名进行校验
        if(loginAct == ""){
            //没有输入用户名,不能够让它提交登录操作
            //return的含义就是跳出该方法,不再继续向下执行代码
            //alert("用户名不能为空")
            //<span id="msg" style...>用户名不能为空</span>
            $("#msg").html("用户名不能为空...");
            return;
        }

        //对密码进行校验
        var loginPwd = $("#loginPwd").val();

        if(loginPwd == ""){
            $("#msg").html("密码不能为空...");
            return;
        }

        //获取十天免登陆的标记
        var flag = $("#flag").val();

        //校验通过,可以将提示信息清空掉
        //$("#msg").css("color","#AAFFAA");
        //$("#msg").html("校验通过...");
        //通过ajax发送请求,进行登录操作,谢惨用户名和密码的参数
        $.ajax({
            //前端页面使用的是相对路径,不以/开头的路径
            //使用相对路径的时候:
            //String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() + "/";
            // basePath = http://localhost:8080/crm/ + settings/user/login.do
            //http://localhost:8080/crm/settings/user/login.do
            //使用绝对路径的时候:
            //http://localhost:8080/settings/user/login.do
            //绝对路径没有项目名称
            url:"settings/user/login.do",
            data:{
                //根据用户名和密码进行发送数据
                "loginAct":loginAct,
                "loginPwd":loginPwd,
                "flag":flag
            },
            type:"POST",
            dataType:"json",
            success:function(data) {
                //接收服务器返回的响应数据
                //data : {code:xxx,msg:xxx,data:xxx}
                //code代表公司中自定义的返回值的响应编码,0代表成功,1代表失败
                //msg代表编码的含义,code返回0,代表操作成功,可以根据指定的msg提示信息,返回登录成功
                //data代表页面中需要加载的数据,加载列表时,需要,登录操作不需要
                if(data.code == 0){
                    //操作成功
                    window.location.href = "workbench/toIndex.do";
                }else{
                    //操作失败,给出提示信息
                    //alert(data.msg);
                    $("#msg").html(data.msg);
                }
            }
        })
    });
}

function autoLogin() {

    //当勾选/取消勾选十天免登陆复选框时
    //设置标记,提交
    $("#autoLogin").click(function () {

        //获取当前复选框的选中状态
        var ck = $(this).prop("checked");

        if(ck){
            //选中状态
            $("#flag").val("a");
        }else{
            //取消选中状态
            $("#flag").val("");
        }
    })
}