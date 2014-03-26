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
            <form action="" method="" class="main_form">
                <div class="text_info clearfix"><span>资费ID：</span></div>
                <div class="input_info"><input type="text" class="readonly" readonly value="<s:property value="cost.id"/>" /></div>
                <div class="text_info clearfix"><span>资费名称：</span></div>
                <div class="input_info"><input type="text" class="readonly" readonly value="<s:property value="cost.name"/>"/></div>
                <div class="text_info clearfix"><span>资费状态：</span></div>
                <div class="input_info">
                    <select class="readonly" disabled>
                    	<s:if test="cost.status==1">
                    		<option>开通</option>
                    	</s:if>
                        <s:else>
                        	<option>暂停</option>
                        </s:else>        
                    </select>                        
                </div>
                <div class="text_info clearfix"><span>资费类型：</span></div>
                <div class="input_info cost_type">
                	<s:if test="cost.costType==1">
                		<input type="radio" name="radFeeType" id="monthly" disabled="disabled" />
	                    <label for="monthly">包月</label>
	                    <input type="radio" name="radFeeType" id="package" disabled="disabled" />
	                    <label for="package">套餐</label>
	                    <input type="radio" name="radFeeType" checked="checked" id="timeBased" disabled="disabled" />
	                    <label for="timeBased">计时</label>
                	</s:if>
                	<s:elseif test="cost.costType==2">
                		<input type="radio" name="radFeeType" checked="checked" id="monthly" disabled="disabled" />
	                    <label for="monthly">包月</label>
	                    <input type="radio" name="radFeeType" id="package" disabled="disabled" />
	                    <label for="package">套餐</label>
	                    <input type="radio" name="radFeeType" id="timeBased" disabled="disabled" />
	                    <label for="timeBased">计时</label>
                	</s:elseif>
                	<s:else>
                		<input type="radio" name="radFeeType" id="monthly" disabled="disabled" />
	                    <label for="monthly">包月</label>
	                    <input type="radio" name="radFeeType" checked="checked" id="package" disabled="disabled" />
	                    <label for="package">套餐</label>
	                    <input type="radio" name="radFeeType" id="timeBased" disabled="disabled" />
	                    <label for="timeBased">计时</label>
                	</s:else>
                    
                </div>
                <div class="text_info clearfix"><span>基本时长：</span></div>
                <div class="input_info">
                    <input type="text" class="readonly" readonly value="<s:property value="cost.basicDuration"/>"  />
                    <span>小时</span>
                </div>
                <div class="text_info clearfix"><span>基本费用：</span></div>
                <div class="input_info">
                    <input type="text"  class="readonly" readonly value="<s:property value="cost.basicCost"/>" />
                    <span>元</span>
                </div>
                <div class="text_info clearfix"><span>单位费用：</span></div>
                <div class="input_info">
                    <input type="text"  class="readonly" readonly value="<s:property value="cost.unitCost"/>" />
                    <span>元/小时</span>
                </div>
                <div class="text_info clearfix"><span>创建时间：</span></div>
                <div class="input_info"><input type="text"  class="readonly" readonly value="<s:date name="cost.createTime" format="yyyy-MM-dd"/>" /></div>      
                <div class="text_info clearfix"><span>启动时间：</span></div>
                <div class="input_info"><input type="text"  class="readonly" readonly value="<s:date name="cost.startTime" format="yyyy-MM-dd"/>" /></div>      
                <div class="text_info clearfix"><span>资费说明：</span></div>
                <div class="input_info_high">
                    <textarea class="width300 height70 readonly" readonly><s:property value="cost.descr"/></textarea>
                </div>                    
                <div class="button_info clearfix">
                    <input type="button" value="返回" class="btn_save" onclick="location.href='cost_find';" />
                </div>
            </form>  
        </div>
        <!--主要区域结束-->
        <div id="footer">
        </div>   
    </body>
</html>
