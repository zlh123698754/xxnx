<%--
  Created by IntelliJ IDEA.
  User: 1
  Date: 2023/12/3
  Time: 21:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>用户登录</title>
    <link rel="stylesheet" href="css/login.css" />
</head>
<body>
<div class="container">
    <div class="brand-logo"></div>
    <div class="brand-title">志在解放大专生</div>
    <div class="inputs">
       <form action="login" method="post" id="loginForm">
           <br>
           <div class="juz">
           <p>学校：</p>
           <select id="school_id" name="school_id">
               <option value="287">宁夏工商职业技术学院</option>
               <option value="312">宁夏财经职业技术学院</option>
               <option value="290">宁夏职业技术学院</option>
           </select>
           </div>
           <div class="juz"><p style="vertical-align: middle">账号：</p><input type="number"    name="accout"    id="accout" value="${message.object.zhanghao}"></div>
           <div class="juz"><p>密码：</p><input type="password"  name="passwd"    id="passwd" value="${message.object.passwd}"></div>
           <span id="tishi" style="font-size: 12px; color:#ff0000 ">${message.mag}</span><br>
           <button type="button" class="button" id="logintbtn"><h3>登 录</h3></button>
       </form>
  </div>
</div>
</div>

</body>

<script type="text/javascript" src="js/jquery-3.7.1.js"></script>
<script type="text/javascript">
    $("#logintbtn").click(function() {
        

        var schoolid=$('#school_id').find('option:selected').val();
        var accout = $("#accout").val();
        var passwd = $("#passwd").val();

        if(CheckIsNullOrEmpty(schoolid)){
            $("#tishi").html("学校不可为空") ;
            return;
        }
        if(CheckIsNullOrEmpty(accout)){
            $("#tishi").html("账号不可为空");
            return;
        }
        if(CheckIsNullOrEmpty(passwd)){
            $("#tishi").html("密码不可为空");

        }else {$("#loginForm").submit();}

    });

    //判断字符串是否为空
    function CheckIsNullOrEmpty(value) {
        //正则表达式用于判斷字符串是否全部由空格或换行符组成
        var reg = /^\s*$/
        //返回值为true表示是空字符串
        return ( value=="" || value == null || value == undefined || reg.test(value))
    }

</script>
</html>
