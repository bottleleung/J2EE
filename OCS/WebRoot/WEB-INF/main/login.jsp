<%@page pageEncoding="utf-8"%>
<%@taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" /> 
        <title>OCS在线计费系统</title>
        <link type="text/css" rel="stylesheet" media="all" href="../styles/global.css" />
        <link type="text/css" rel="stylesheet" media="all" href="../styles/global_color.css" /> 
    </head>
    <body class="index">
        <div class="login_box">
        	<form action="login_login" method="post">
       			<table>
	                <tr>
	                    <td class="login_info">账号：</td>
	                    <td colspan="2"><input name="userCode" type="text" class="width150" /></td>
	                </tr>
	                <tr>
	                    <td class="login_info">密码：</td>
	                    <td colspan="2"><input name="password" type="password" class="width150" /></td>
	                </tr>
	                <tr>
	                    <td class="login_info">验证码：</td>
	                    <td class="width70"><input name="checkCode" type="text" class="width70" /></td>
	                    <td><img id="checkCode" onclick="changeCheckCode();" src="login_createCheckCodeImg" alt="验证码" title="点击更换" /></td>             
	                </tr>            
	                <tr>
	                    <td></td>
	                    <td class="login_button" colspan="2">
	                        <a href="javascript:document.forms[0].submit();"><img src="../images/login_btn.png" /></a>
	                    </td>    
	                    <td>
	                    	<s:if test="loginStatus==0">
	                    		<span class="required">验证码、用户名或密码错误，请重试</span>
	                    	</s:if>
	                    </td>                
	                </tr>
            	</table>
        	</form>
        </div>
    <script type="text/javascript" language="javascript" src="../scripts/jquery-1.10.2.min.js"></script>    
    <script type="text/javascript" language="javascript" src="../scripts/main/login.js"></script>
    </body>
</html>
