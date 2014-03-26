<%@page pageEncoding="utf-8" %>
<%@taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <title>OCS在线计费系统</title>
        <link type="text/css" rel="stylesheet" media="all" href="../styles/global.css" />
        <link type="text/css" rel="stylesheet" media="all" href="../styles/global_color.css" />
    </head>
    <body>
        <!--Logo区域开始-->
        <div id="header">
            <a href="../common/common_exit">[退出]</a>            
        </div>
        <!--Logo区域结束-->
        <!--导航区域开始-->
        <div id="navi">                        
            <ul id="menu">
                <li><a href="../common/common_toIndex" class="index_off"></a></li>
                <li><a href="../role/role_find" class="role_off"></a></li>
                <li><a href="../user/user_find" class="user_off"></a></li>
                <li><a href="../cost/cost_find" class="cost_off"></a></li>
                <li><a href="../account/account_find" class="account_off"></a></li>
                <li><a href="../business/business_find" class="business_off"></a></li>
                <li><a href="../common/common_toUpdateUserInfo" class="information_on"></a></li>
                <li><a href="../common/common_toResetPassword" class="password_off"></a></li>
            </ul>            
        </div>
        <!--导航区域结束-->
        <!--主要区域开始-->
        <div id="main">            
            <!--保存操作后的提示信息：成功或者失败-->
            <div id="save_result_info" class="save_success">保存成功！</div><!--保存失败，数据并发错误！-->
            <form id="infoModi_form" action="common_updateUserInfo" method="post" class="main_form">
            	<input type="hidden" name="user.id" value="<s:property value="user.id" />"/>
            	
                <div class="text_info clearfix"><span>管理员账号：</span></div>
                <div class="input_info"><input type="text" readonly="readonly" class="readonly" value="<s:property value="user.userCode" />" /></div>
                <div class="text_info clearfix"><span>角色：</span></div>
                <div class="input_info">
                    <input type="text" readonly="readonly" class="readonly width400" value="<s:property value="user.roleNames" />" />
                </div>
                <div class="text_info clearfix"><span>姓名：</span></div>
                <div class="input_info">
                	<input type="hidden" value="<s:property value="user.name" />" id="originUserName" /> 
                    <input name="user.name" onblur="checkUserName();" id="user_name" type="text" value="<s:property value="user.name" />" />
                    <span class="required">*</span>
                    <div id="userNameInfo" class="validate_msg_long error_msg hidden">20长度以内的汉字、字母的组合</div>
                </div>
                <div class="text_info clearfix"><span>电话：</span></div>
                <div class="input_info">
                	<input type="hidden" value="<s:property value="user.telephone" />" id="originTelephone"  />
                    <input name="user.telephone" onblur="checkTelephone();" id="user_telephone" type="text" class="width200" value="<s:property value="user.telephone" />" />
                    <div id="userTelephoneInfo" class="validate_msg_medium hidden">手机号码格式不对</div>
                </div>
                <div class="text_info clearfix"><span>Email：</span></div>
                <div class="input_info">
                    <input name="user.email" onblur="checkEmail();" id="user_email" type="text" class="width200" value="<s:property value="user.email" />" />
                    <div id="userEmailInfo" class="validate_msg_medium hidden">不符合 email 格式</div>
                </div>
                <div class="text_info clearfix"><span>创建时间：</span></div>
                <div class="input_info"><input type="text" readonly="readonly" class="readonly" value="<s:date name="user.enrollDate" format="yyyy-MM-dd" />"/></div>
                <div class="button_info clearfix">
                    <input type="button" value="保存" class="btn_save" onclick="submitForm();" />
                    <input type="button" value="取消" class="btn_save" onclick="location.href='common_toIndex';"/>
                </div>
            </form>  
        </div>
        <!--主要区域结束-->
        <div id="footer">
        </div>
    <script type="text/javascript" language="javascript" src="../scripts/jquery-1.10.2.min.js"></script>    
    <script type="text/javascript" language="javascript" src="../scripts/main/infoModi.js"></script>
    </body>
</html>
