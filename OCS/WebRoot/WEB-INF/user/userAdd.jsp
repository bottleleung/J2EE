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
                <li><a href="../user/user_find" class="user_on"></a></li>
                <li><a href="../cost/cost_find" class="cost_off"></a></li>
                <li><a href="../account/account_find" class="account_off"></a></li>
                <li><a href="../business/business_find" class="business_off"></a></li>
                <li><a href="../common/common_toUpdateUserInfo" class="information_off"></a></li>
                <li><a href="../common/common_toResetPassword" class="password_off"></a></li>
            </ul>
        </div>
        <!--导航区域结束-->
        <!--主要区域开始-->
        <div id="main">            
            <div id="save_result_info" class="save_success">保存成功！</div>
            <form id="user_form" action="user_add" method="post" class="main_form">
                <div class="text_info clearfix"><span>姓名：</span></div>
                <div class="input_info">
                    <input name="user.name" onblur="checkUserName();" id="user_name" type="text" />
                    <span class="required">*</span>
                    <div id="userNameInfo" class="validate_msg_long hidden">20长度以内的汉字、字母、数字的组合</div>
                </div>
                <div class="text_info clearfix"><span>用户账号：</span></div>
                <div class="input_info">
                    <input name="user.userCode" onblur="checkUserCode();" id="user_userCode" type="text"  />
                    <span class="required">*</span>
                    <div id="userCodeInfo" class="validate_msg_long hidden">30长度以内的字母、数字和下划线的组合</div>
                </div>
                <div class="text_info clearfix"><span>密码：</span></div>
                <div class="input_info">
                    <input name="user.password" onblur="checkPassword();" id="user_passwd" type="password"  />
                    <span class="required">*</span>
                    <div id="paswdInfo" class="validate_msg_long error_msg hidden">30长度以内的字母、数字和下划线的组合</div>
                </div>
                <div class="text_info clearfix"><span>重复密码：</span></div>
                <div class="input_info">
                    <input onblur="checkPasswdEqual();" id="passwdRepeat" type="password"  />
                    <span class="required">*</span>
                    <div id="passwdEqualInfo" class="validate_msg_long error_msg hidden">两次密码必须相同</div>
                </div>      
                <div class="text_info clearfix"><span>电话：</span></div>
                <div class="input_info">
                    <input name="user.telephone" onblur="checkTelephone();" id="user_telephone" type="text" class="width200"/>
                    <span class="required">*</span>
                    <div id="userTelephoneInfo" class="validate_msg_medium error_msg hidden">手机号码格式不正确</div>
                </div>
                <div class="text_info clearfix"><span>Email：</span></div>
                <div class="input_info">
                    <input name="user.email" onblur="checkEmail();" id="user_email" type="text" class="width200"/>
                    <span class="required">*</span>
                    <div id="userEmailInfo" class="validate_msg_medium error_msg hidden">50长度以内，正确的 email 格式</div>
                </div>
                <div class="text_info clearfix"><span>角色：</span></div>
                <div class="input_info_high">
                    <div class="input_info_scroll">
                        <ul>
				<s:iterator value="roles">
					<li><input name="user.roleIds" value="<s:property value="id" />" type="checkbox"  /><s:property value="name" /></li>
				</s:iterator>
                        </ul>
                    </div>
                    <span class="required">*</span>
                    <div id="RoleInfo" class="validate_msg_tiny error_msg hidden">至少选择一个</div>
                </div>
                <div class="button_info clearfix">
                    <input type="button" value="保存" class="btn_save" onclick="submitForm();" />
                    <input type="button" value="取消" class="btn_save" onclick="location.href='user_find';"/>
                </div>
            </form>  
        </div>
        <!--主要区域结束-->
        <div id="footer">
        </div>
    <script type="text/javascript" language="javascript" src="../scripts/jquery-1.10.2.min.js"></script>    
    <script type="text/javascript" language="javascript" src="../scripts/user/userAdd.js"></script>
    </body>
</html>
