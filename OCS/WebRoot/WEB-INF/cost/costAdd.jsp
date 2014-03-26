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
                <li><a href="../cost/cost_find" class="cost_on"></a></li>
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
            <form id="form_addCost" action="cost_add" method="post" class="main_form">
                
                <div class="text_info clearfix"><span>资费名称：</span></div>
                <div class="input_info">
                    <input onblur="checkCostRepeat();" id="costName" name="cost.name" type="text" class="width300" value=""/>
                    <span class="required">*</span>
                    <div id="info_costName" class="validate_msg_short hidden">50长度的字母、数字、汉字和下划线的组合</div>
                </div>
                <div class="text_info clearfix"><span>资费类型：</span></div>
                <div class="input_info cost_type">
	                <input onclick="changeInputField(1);" name="cost.costType" value="2" type="radio" checked="checked" id="monthly" />
	                <label for="monthly">包月</label>   
	                <input onclick="changeInputField(2);" name="cost.costType" value="0" type="radio" id="package" />     
	                <label for="package">套餐</label>     
	                <input onclick="changeInputField(3);" name="cost.costType" value="1" type="radio" id="timeBased" />
	                <label for="timeBased">计时</label>             	
                </div>
                
                <div class="text_info clearfix"><span>基本时长：</span></div>
                <div class="input_info">
                	
                	<input id="basicDuration" name="cost.basicDuration" readonly="readonly" type="text" value="" class="width100 readonly" />
                    <span class="info">小时</span>
                    <span class="required">*</span>
                    <div class="validate_msg_long hidden">1-600之间的整数</div>
                </div>
                
                <div class="text_info clearfix"><span>基本费用：</span></div>
                <div class="input_info">
                	<input id="basicCost" name="cost.basicCost" type="text" value="" class="width100" />
                    <span class="info">元</span>
                    <span class="required">*</span>
                    <div class="validate_msg_long hidden">0-99999.99之间的数值</div>
                </div>
                
                <div class="text_info clearfix"><span>单位费用：</span></div>
                <div class="input_info">
                	<input id="unitCost" name="cost.unitCost" readonly="readonly" type="text" value="" class="width100 readonly" />
                    <span class="info">元/小时</span>
                    <span class="required">*</span>
                    <div class="validate_msg_long hidden">0-99999.99之间的数值</div>
                </div>  
                
                <div class="text_info clearfix"><span>资费说明：</span></div>
                <div class="input_info_high">
                    <textarea name="cost.descr" class="width300 height70"></textarea>
                    <div class="validate_msg_short hidden">100长度的字母、数字、汉字和下划线的组合</div>
                </div>                    
                <div class="button_info clearfix">
                    <input onclick="submitForm();" id="saveCost" type="button" value="保存" class="btn_save" />
                    <input type="button" value="取消" class="btn_save" onclick="location.href='cost_find';"/>
                </div>
            </form>
        </div>
        <!--主要区域结束-->
        <div id="footer">
        </div>
    <script type="text/javascript" language="javascript" src="../scripts/jquery-1.10.2.min.js"></script>    
    <script type="text/javascript" language="javascript" src="../scripts/cost/costAdd.js"></script> 
    </body>
</html>
