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
                <li><a href="../account/account_find" class="account_on"></a></li>
                <li><a href="../business/business_find" class="business_off"></a></li>
                <li><a href="../common/common_toUpdateUserInfo" class="information_off"></a></li>
                <li><a href="../common/common_toResetPassword" class="password_off"></a></li>
            </ul>
        </div>
        <!--导航区域结束-->
        <!--主要区域开始-->
        <div id="main">  
            <!--保存成功或者失败的提示消息-->          
            <div id="save_result_info" class="save_fail">保存失败，旧密码错误！</div>
            <form id="account_form" action="account_update" method="post" class="main_form">
                    <!--必填项-->
                    <div class="text_info clearfix"><span>账务账号ID：</span></div>
                    <div class="input_info">
                        <input name="account.id" type="text" value="<s:property value="account.id" />" readonly class="readonly" />
                    </div>
                    <div class="text_info clearfix"><span>姓名：</span></div>
                    <div class="input_info">
                        <input name="account.realName" type="text" value="<s:property value="account.realName" />" readonly class="readonly"/>
                        <span class="required">*</span>
                        <div class="validate_msg_long error_msg hidden">20长度以内的汉字、字母和数字的组合</div>
                    </div>
                    <div class="text_info clearfix"><span>身份证：</span></div>
                    <div class="input_info">
                        <input name="account.idCardNo" type="text" value="<s:property value="account.idCardNo" />" readonly class="readonly" />
                    </div>
                    <div class="text_info clearfix"><span>登录账号：</span></div>
                    <div class="input_info">
                        <input name="account.loginName" type="text" value="<s:property value="account.loginName" />" readonly class="readonly"  />                        
                        <div class="change_pwd">
                            <input id="chkModiPwd" type="checkbox" onclick="showPwd();" />
                            <label for="chkModiPwd">修改密码</label>
                            <input id="oldPasswdHidden" type="hidden" value="<s:property value="account.loginPassword" />" />
                        </div>
                    </div>
                    
                    <!--修改密码部分-->
                    <div id="divPwds">
                        <div class="text_info clearfix"><span>旧密码：</span></div>
                        <div class="input_info">
                            <input id="account_oldPasswd" type="password" onblur="checkOldPasswd();" />
                            <span class="required">*</span>
                            <div id="oldPasswdInfo" class="validate_msg_long hidden">30长度以内的字母、数字和下划线的组合</div>
                        </div>
                        <div class="text_info clearfix"><span>新密码：</span></div>
                        <div class="input_info">
                            <input value="<s:property value="account.loginPassword" />" name="account.loginPassword" id="account_newPasswd" onblur="checkNewPasswd();" type="password"  />
                            <span class="required">*</span>
                            <div id="newPasswdInfo" class="validate_msg_long hidden">30长度以内的字母、数字和下划线的组合</div>
                        </div>
                        <div class="text_info clearfix"><span>重复新密码：</span></div>
                        <div class="input_info">
                            <input onblur="checkPasswdEqual();" id="passwdRepeat" type="password"  />
                            <span class="required">*</span>
                            <div id="passwdEqualInfo" class="validate_msg_long hidden">两次密码必须相同</div>
                        </div>  
                    </div>  
                    <input type="hidden" name="account.telephone" value="<s:property value="account.telephone" />"/>                 
                    <div class="text_info clearfix"><span>Email：</span></div>
                    <div class="input_info">
                        <input name="account.email" onblur="checkEmail();" id="account_email" type="text" class="width200" value="<s:property value="account.email" />"/>
                        <div id="emailInfo" class="validate_msg_medium hidden">50长度以内，合法的 Email 格式</div>
                    </div> 
                    <div class="text_info clearfix"><span>职业：</span></div>
                    <div class="input_info">
                        <select name="account.occupation">
                            <option value="干部">干部</option>
                            <option value="学生">学生</option>
                            <option value="技术人员">技术人员</option>
                            <option value="其他">其他</option>
                        </select>                        
                    </div>
                    <div class="text_info clearfix"><span>性别：</span></div>
                    <div class="input_info fee_type">
                        <input value="0" type="radio" name="account.gender" id="female"/>
                        <label for="female">女</label>
                        <input value="1" type="radio" name="account.gender" id="male" />
                        <label for="male">男</label>
                    </div> 
                    <div class="text_info clearfix"><span>通信地址：</span></div>
                    <div class="input_info">
                        <input name="account.address" onblur="checkAddress();" id="account_address" type="text" class="width350" value="<s:property value="account.address" />" />
                        <div id="addressInfo" class="validate_msg_tiny hidden">50长度以内</div>
                    </div> 
                    <div class="text_info clearfix"><span>邮编：</span></div>
                    <div class="input_info">
                        <input name="account.zipCode" onblur="checkZipCode();" id="account_zipcode" type="text" value="<s:property value="account.zipCode" />"/>
                        <div id="zipcodeInfo" class="validate_msg_long hidden">6位数字</div>
                    </div> 
                    <div class="text_info clearfix"><span>QQ：</span></div>
                    <div class="input_info">
                        <input name="account.qq" onblur="checkQQ();" id="account_QQ" type="text" value="<s:property value="account.qq" />" />
                        <div id="QQInfo" class="validate_msg_long hidden">5到13位数字</div>
                    </div>                
                    <!--操作按钮-->
                    <div class="button_info clearfix">
                        <input type="button" value="保存" class="btn_save" onclick="submitForm();" />
                        <input type="button" value="取消" class="btn_save" onclick="location.href='account_find';" />
                    </div>
                </form>  
        </div>
        <!--主要区域结束-->
        <div id="footer">
        </div>
    <script type="text/javascript" language="javascript" src="../scripts/jquery-1.10.2.min.js"></script>    
    <script type="text/javascript" language="javascript" src="../scripts/account/accountUpdate.js"></script>
    </body>
</html>
