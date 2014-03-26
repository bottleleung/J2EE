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
            <div id="save_result_info" class="save_fail">保存失败，该身份证已经开通过账务账号！</div>
            <form id="account_form" action="account_add" method="post" class="main_form">
                <!--必填项-->
                <div class="text_info clearfix"><span>姓名：</span></div>
                <div class="input_info">
                    <input name="account.realName" id="account_name" type="text" value="" onblur="checkName();"/>
                    <span class="required">*</span>
                    <div id="checkAccountNameInfo" class="validate_msg_long hidden">20长度以内的汉字、字母和数字的组合</div>
                </div>
                <div class="text_info clearfix"><span>身份证：</span></div>
                <div class="input_info">
                    <input name="account.idCardNo" id="account_idCardNo" type="text" value="" onblur="checkIdCardNo();" />
                    <span class="required">*</span>
                    <div id="idCardNoInfo" class="validate_msg_long hidden">身份证号码格式不正确</div>
                </div>
                <div class="text_info clearfix"><span>登录账号：</span></div>
                <div class="input_info">
                    <input name="account.loginName" id="account_loginName" type="text" value="" onblur="checkLoginName(); " />
                    <span class="required">*</span>
                    <div id="loginNameInfo" class="validate_msg_long hidden">30长度以内的字母、数字和下划线的组合</div>
                </div>
                <div class="text_info clearfix"><span>密码：</span></div>
                <div class="input_info">
                    <input name="account.loginPassword" id="account_passwd" type="password" onblur="checkPassword();" />
                    <span class="required">*</span>
                    <div id="paswdInfo" class="validate_msg_long hidden">30长度以内的字母、数字和下划线的组合</div>
                </div>
                <div class="text_info clearfix"><span>重复密码：</span></div>
                <div class="input_info">
                    <input id="passwdRepeat" type="password" onblur="checkPasswdEqual();" />
                    <span class="required">*</span>
                    <div id="passwdEqualInfo" class="validate_msg_long hidden">两次密码必须相同</div>
                </div>     
                <div class="text_info clearfix"><span>电话：</span></div>
                <div class="input_info">
                    <input name="account.telephone" id="account_telephone" type="text" class="width200" onblur="checkTelephone();"/>
                    <span class="required">*</span>
                    <div id="telephoneInfo" class="validate_msg_medium hidden">正确的电话号码格式：手机</div>
                </div>                
                <!--可选项-->
                <div class="text_info clearfix"><span>可选项：</span></div>
                <div class="input_info">
                    <img id="btn_option" src="../images/show.png" alt="展开" onclick="showOptionInfo();" />
                </div>
                <div id="optionalInfo" class="hide">
                    <div class="text_info clearfix"><span>推荐人身份证号码：</span></div>
                    <div class="input_info">
                        <input id="recommenderIdCardNo" type="text" onblur="checkRecommenderIdCardNo();"/>
                        <div id="recommenderInfo" class="validate_msg_long hidden">身份证号码格式不正确</div>
                    </div>
                    <input id="recommenderId" type="hidden" value="" name="account.recommenderId" />
                    
                    <div class="text_info clearfix"><span>生日：</span></div>
                    <div class="input_info">
                        <input name="account.birthday" id="accouont_birthday" type="text" value="根据身份证自动计算出来" readonly class="readonly" />
                    </div>
                    <div class="text_info clearfix"><span>Email：</span></div>
                    <div class="input_info">
                        <input name="account.email" id="account_email" type="text" class="width350" onblur="checkEmail();" />
                        <div id="emailInfo" class="validate_msg_tiny hidden">Email格式不合法</div>
                    </div> 
                    <div class="text_info clearfix"><span>职业：</span></div>
                    <div class="input_info">
                        <select name="account.occupation">
                            <option>干部</option>
                            <option>学生</option>
                            <option>技术人员</option>
                            <option>其他</option>
                        </select>                        
                    </div>
                    <div class="text_info clearfix"><span>性别：</span></div>
                    <div class="input_info cost_type">
                        <input value="0" type="radio" name="account.gender" id="female" />
                        <label for="female">女</label>
                        <input value="1" type="radio" name="account.gender" id="male" />
                        <label for="male">男</label>
                    </div> 
                    <div class="text_info clearfix"><span>通信地址：</span></div>
                    <div class="input_info">
                        <input name="account.address" id="account_address" type="text" class="width350" onblur="checkAddress();" />
                        <div id="addressInfo" class="validate_msg_tiny hidden">50长度以内</div>
                    </div> 
                    <div class="text_info clearfix"><span>邮编：</span></div>
                    <div class="input_info">
                        <input name="account.zipCode" id="account_zipcode" type="text" onblur="checkZipCode();"/>
                        <div id="zipcodeInfo" class="validate_msg_long hidden">6位数字</div>
                    </div> 
                    <div class="text_info clearfix"><span>QQ：</span></div>
                    <div class="input_info">
                        <input name="account.qq" id="account_QQ" type="text" onblur="checkQQ();" />
                        <div id="QQInfo" class="validate_msg_long hidden">5到13位数字</div>
                    </div>                
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
    <script type="text/javascript" language="javascript" src="../scripts/account/accountAdd.js"></script>
    </body>
</html>
