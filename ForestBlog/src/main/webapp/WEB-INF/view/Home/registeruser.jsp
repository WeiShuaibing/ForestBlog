<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>

<!DOCTYPE html>
<!--[if IE 8]>
<html xmlns="http://www.w3.org/1999/xhtml" class="ie8" lang="zh-CN">
<![endif]-->
<!--[if !(IE 8) ]><!-->
<html xmlns="http://www.w3.org/1999/xhtml" lang="zh-CN">
<!--<![endif]-->
<html xmlns="http://www.w3.org/1999/xhtml" lang="zh-CN">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <title>${options.optionSiteTitle} &lsaquo; 登录</title>
    <link rel="stylesheet" href="/plugin/font-awesome/css/font-awesome.min.css">
    <link rel="shortcut icon" href="/img/logo.png">
    <link rel='stylesheet' id='dashicons-css'  href='/plugin/login/dashicons.min.css' type='text/css' media='all' />
    <link rel='stylesheet' id='buttons-css'  href='/plugin/login/buttons.min.css' type='text/css' media='all' />
    <link rel='stylesheet' id='forms-css'  href='/plugin/login/forms.min.css' type='text/css' media='all' />
    <link rel='stylesheet' id='l10n-css'  href='/plugin/login/l10n.min.css' type='text/css' media='all' />
    <link rel='stylesheet' id='login-css'  href='/plugin/login/login.min.css' type='text/css' media='all' />
    <style type="text/css">
        body{
            font-family: "Microsoft YaHei", Helvetica, Arial, Lucida Grande, Tahoma, sans-serif;
            background: url(http://i2.bvimg.com/622932/c0b9e9160447fad6.jpg);
            width:100%;
            height:100%;
        }
        .login h1 a {
            background-size: 220px 50px;
            width: 220px;
            height: 50px;
            padding: 0;
            margin: 0 auto 1em;
        }
        .login form {
            background: #fff;
            background: rgba(255, 255, 255, 0.6);
            border-radius: 2px;
            border: 1px solid #fff;
        }
        .login label {
            color: #000;
            font-weight: bold;
        }

        #backtoblog a, #nav a {
            color: #fff !important;
        }

    </style><meta name='robots' content='noindex,follow' />
    <meta name="viewport" content="width=device-width" />
    <style>
        body {
            background-repeat: no-repeat;
            background-size: 100% 100%;
            background-attachment: fixed;
        }
    </style>
</head>
<body class="login login-action-login wp-core-ui  locale-zh-cn">
<div id="login">
    <h1><a href="/" title="欢迎您光临本站！" tabindex="-1">${options.optionSiteTitle}</a></h1>
    <%--<%
         String username = "";
         String password = "";
         //获取当前站点的所有Cookie
         Cookie[] cookies = request.getCookies();
         for (int i = 0; i < cookies.length; i++) {//对cookies中的数据进行遍历，找到用户名、密码的数据
             if ("username".equals(cookies[i].getName())) {
                    username = cookies[i].getValue();
             } else if ("password".equals(cookies[i].getName())) {
                 password = cookies[i].getValue();
             }
         }
         %>--%>
    <form name="loginForm" id="loginForm"  method="post">
        <p>
            <label for="user_name">用户名<br />
                <input type="text" name="username" id="user_name" class="input" placeholder="用户名或者密码" size="20" required/></label>
        </p>
        <p>
            <label for="user_nickname">用户昵称<br />
                <input type="text" name="nickname" id="user_nickname" class="input" placeholder="昵称" size="20" required/></label>
        </p>
        <p>
            <label for="user_email">邮箱<br />
                <input type="text" name="email" id="user_email" class="input" placeholder="邮箱" size="20" required/></label>
        </p>
        <p>
            <label for="user_pass">密码<br />
                <input type="password" name="password" id="user_pass" class="input" placeholder="请输入密码" size="20" required/>
            </label>
        </p>
        <p>
            <label for="user_pass_2">密码<br />
                <input type="password" name="password2" id="user_pass_2" class="input" placeholder="请再次输入密码" size="20" required/>
            </label>
        </p>
<%--
        <p class="forgetmenot"><label for="rememberme"><input name="rememberme" type="checkbox" id="rememberme" value="1" checked /> 记住密码</label></p>
--%>

        <p class="submit">
            <input type="button" name="wp-regist" id="regist-btn" class="button button-primary button-large" value="注册" />
        </p>

    </form>



    <script type="text/javascript">
        function wp_attempt_focus(){
            setTimeout( function(){ try{
                d = document.getElementById('user_name');
                d.focus();
                d.select();
            } catch(e){}
            }, 200);
        }

        wp_attempt_focus();
        if(typeof wpOnload=='function')wpOnload();
    </script>

    <p id="backtoblog"><a href="/">&larr; 返回到博客首頁</a></p>

</div>


<div class="clear"></div>

<script src="/js/jquery.min.js"></script>
<script type="text/javascript">
    <%--注册普通用户 --%>
    $("#regist-btn").click(function () {
            if($(" input[ name='password' ] ").val() == $(" input[ name='password2' ] ").val()){
                $.ajax({
                    async: false,//同步，待请求完毕后再执行后面的代码
                    type: "POST",
                    url: '/registerUser',
                    contentType: "application/x-www-form-urlencoded; charset=utf-8",
                    data: $("#loginForm").serialize(),
                    dataType: "json",
                    success: function (data) {
                        if(data != null) {
                            console.log(data)
                            alert(" 恭喜你注册成功 ")
                            window.location.href="/userlogin";
                        } else {
                            alert("后台返回信息出现异常！！！")
                        }
                    },
                    error: function () {
                        alert("注册信息有误，可能用户名或邮箱已注册哦！！！")
                    }
                })
            }else {
                alert("两次输入密码不一致哦！！！")
            }

    })

    <%--登录验证--%>
    $("#submit-btn").click(function () {
        var user = $("#user_login").val();
        var password = $("#user_pass").val();
        if(user=="") {
            alert("用户名不可为空!");
        } else if(password==""){
            alert("密码不可为空!");
        } else {
            $.ajax({
                async: false,//同步，待请求完毕后再执行后面的代码
                type: "POST",
                url: '/loginVerify',
                contentType: "application/x-www-form-urlencoded; charset=utf-8",
                data: $("#loginForm").serialize(),
                dataType: "json",
                success: function (data) {
                    if(data.code==0) {
                        alert(data.msg);
                    } else {
                        window.location.href="/admin";

                    }
                },
                error: function () {
                    alert("数据获取失败")
                }
            })
        }
    })

</script>
</body>
</html>

