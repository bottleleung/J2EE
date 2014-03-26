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
                <li><a href="../business/business_find" class="business_on"></a></li>
                <li><a href="../common/common_toUpdateUserInfo" class="information_off"></a></li>
                <li><a href="../common/common_toResetPassword" class="password_off"></a></li>
            </ul>
        </div>
        <!--导航区域结束-->
        <!--主要区域开始-->
        <div id="main">
            <!--保存操作的提示信息-->
            <div id="save_result_info" class="save_fail">保存失败！192.168.0.23服务器上已经开通过 OS 账号 “mary”。</div>
            <form id="businessAdd" action="business_add" method="post" class="main_form">
                <!--内容项-->
                <div class="text_info clearfix"><span>身份证：</span></div>
                <div class="input_info">
                    <input id="idCardNo" type="text" value="" class="width180" onblur="checkIdCardNoExist();" />
                    <input type="button" value="查询账务账号" class="btn_search_large" onclick="findAccount();" />
                    <span class="required">*</span>
                    <div id="checkIdCardNoInfo" class="validate_msg_short hidden">没有此身份证号，请重新录入。</div>
                </div>
                <div class="text_info clearfix"><span>账务账号：</span></div>
                <div class="input_info">
                    <input id="account" type="text" readonly="readonly" value="" class="readonly"/>
                    <span class="required">*</span>
                </div>
                
                <input type="hidden" id="accountId" name="bvo.accountId" value="" />

                <div class="text_info clearfix"><span>资费类型：</span></div>
                <div class="input_info">
                    <select name="bvo.costId">
                       <s:iterator value="costs">
                       	  <option value="<s:property value="id" />"><s:property value="name" /></option>
                       </s:iterator> 
                    </select>                        
                </div> 
                <div class="text_info clearfix"><span>服务器 IP：</span></div>
                <div class="input_info">
                    <input id="unixHost" type="text" value="" name="bvo.unixHost" onblur="checkIpLegal();" />
                    <span class="required">*</span>
                    <div id="checkIp" class="validate_msg_long hidden">不符合IP的地址规范</div>
                </div>                   
                <div class="text_info clearfix"><span>登录 OS 账号：</span></div>
                <div class="input_info">
                    <input id="osUserName" name="bvo.osUserName" type="text" value="" onblur="checkOSUserName();"/>
                    <span class="required">*</span>
                    <div id="checkOsUserName" class="validate_msg_long hidden">8长度以内的字母、数字和下划线的组合</div>
                </div>
                <div class="text_info clearfix"><span>密码：</span></div>
                <div class="input_info">
                    <input id="passwd" type="password"  value="" name="bvo.loginPassword" onblur="checkPasswd();" />
                    <span class="required">*</span>
                    <div id="checkPasswd" class="validate_msg_long hidden">30长度以内的字母、数字和下划线的组合</div>
                </div>
                <div class="text_info clearfix"><span>重复密码：</span></div>
                <div class="input_info">
                    <input id="passwdRepeat" type="password" onblur="checkPasswdEqual()" />
                    <span class="required">*</span>
                    <div id="passwdRepeatInfo" class="validate_msg_long hidden">两次密码必须相同</div>
                </div>     
                <!--操作按钮-->
                <div class="button_info clearfix">
                    <input type="button" value="保存" class="btn_save" onclick="submitForm();" />
                    <input type="button" value="取消" class="btn_save" onclick="location.href='business_find';"/>
                </div>
            </form>
        </div>
        <!--主要区域结束-->
        <div id="footer">
        </div>
    <script type="text/javascript" language="javascript" src="../scripts/jquery-1.10.2.min.js"></script>    
    <script type="text/javascript" language="javascript" src="../scripts/business/businessAdd.js"></script> 
    </body>
</html>
