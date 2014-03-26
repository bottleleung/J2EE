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
            <form id="form_findBusiness" action="business_find" method="post">
                <!--查询-->
                <div class="search_add">                        
                    <div>OS 账号：<input type="text" name="osUserName" value="<s:property value="osUserName" />" class="width100 text_search" /></div>                            
                    <div>服务器 IP：<input type="text" name="unixHost" value="<s:property value="unixHost" />" class="width100 text_search" /></div>
                    <div>身份证：<input type="text"  name="idCardNo" value="<s:property value="idCardNo" />" class="text_search" /></div>
                    <div>状态：
                        <select class="select_search" name="status">
                            <option value="-1" <s:if test="status==-1">selected="selected"</s:if> >全部</option>
                            <option value="1" <s:if test="status==1">selected="selected"</s:if> >开通</option>
                            <option value="0" <s:if test="status==0">selected="selected"</s:if> >暂停</option>
                            <option value="2" <s:if test="status==2">selected="selected"</s:if> >删除</option>
                        </select>
                    </div>
                    <input id="business_page" type="hidden" name="page" value="" />
                    <div><input type="button" value="搜索" class="btn_search" onclick="findBusiness(1)" /></div>
                    <input type="button" value="增加" class="btn_add" onclick="location.href='business_toAddBusiness';" />
                </div>  
                <!--删除的操作提示-->
                <div id="operate_result_info" class="operate_success">
                    <img src="../images/close.png" onclick="this.parentNode.style.display='none';" />
                    删除成功！
                </div>   
                <!--数据区域：用表格展示数据-->     
                <div id="data">            
                    <table id="datalist">
	                    <tr>
	                        <th class="width50">业务ID</th>
	                        <th class="width70">账务账号ID</th>
	                        <th class="width150">身份证</th>
	                        <th class="width70">姓名</th>
	                        <th>OS 账号</th>
	                        <th class="width50">状态</th>
	                        <th class="width100">服务器 IP</th>
	                        <th class="width100">资费</th>                                                        
	                        <th class="width200"></th>
	                    </tr>
	                    <s:iterator value="businessVOs">
	                   		<tr>
		                        <td><s:property value="id" /></td>
		                        <td><s:property value="accountId" /></td>
		                        <td><s:property value="idCardNo" /></td>
		                        <td><s:property value="realName" /></td>
		                        <td><s:property value="osUserName" /></td>
		                        <td id="business_status">
		                        	<s:if test="status==1">开通</s:if>
		                        	<s:elseif test="status==0">暂停</s:elseif>
		                        	<s:elseif test="status==2">删除</s:elseif>
		                        </td>
		                        <td><s:property value="unixHost" /></td>
		                        <td>
		                            <a class="summary"  onmouseover="showDetail(true, $(this));" onmouseout="showDetail(false, $(this));"><s:property value="costName" /></a>
		                            <!--浮动的详细信息-->
		                            <div class="detail_info">
		                                <s:property value="costDescr" />
		                            </div>
		                        </td>                            
		                        <td class="td_modi" >
		                        	<s:if test="status==0||status==1">
			                        	<s:if test="status==0">
			                        		<input type="button" value="开通" class="btn_start" onclick="startBusiness(<s:property value="id" />);" />
			                        	</s:if>
			                        	<s:if test="status==1">
			                        		<input type="button" value="暂停" class="btn_pause" onclick="location.href='business_pause?id=<s:property value="id" />';" />
			                        	</s:if> 
			                            
			                            <input type="button" value="修改" class="btn_modify" onclick="location.href='business_toUpdateBusiness?id=<s:property value="id" />';" />
			                            <input type="button" value="删除" class="btn_delete" onclick="deleteBusiness(<s:property value="id" />, $(this));" />
		                        	</s:if>                 	
		                        </td>
		                    </tr> 
                    	</s:iterator>                                                            
                	</table>                
                <p>业务说明：<br />
                1、创建即开通，记载创建时间；<br />
                2、暂停后，记载暂停时间；<br />
                3、重新开通后，删除暂停时间；<br />
                4、删除后，记载删除时间，标示为删除，不能再开通、修改、删除；<br />
                5、业务账号不设计修改密码功能，由用户自服务功能实现；<br />
                6、暂停和删除状态的账务账号下属的业务账号不能被开通。</p>
                </div>                    
                <!--分页-->
                <div id="pages">
                    <a href="javascript:findBusiness(1);">首页</a>
                    <s:if test="page==1">
                    	<a href="#">上一页</a>
                    </s:if>
        	        <s:else>
        	        	<a href="javascript:findBusiness(<s:property value="page-1"/>);">上一页</a>
        	        </s:else>
        	        
                    <s:iterator begin="1" end="totalPages" var="p">
        	        	<s:if test="#p==page">
                    		<a href="javascript:findBusiness(<s:property value="#p"/>);" class="current_page"><s:property value="#p"/></a>
                    	</s:if>
                    	<s:else>
                    		<a href="javascript:findBusiness(<s:property value="#p"/>);"><s:property value="#p"/></a>
                    	</s:else>
        	        </s:iterator>
        	        <s:if test="page==totalPages">
                    	<a href="#">下一页</a>
                    </s:if>
        	        <s:else>
        	        	<a href="javascript:findBusiness(<s:property value="page+1"/>);">下一页</a>
        	        </s:else>
                    <a href="javascript:findBusiness(<s:property value="totalPages"/>);">末页</a>
                </div>                    
            </form>
        </div>
        <!--主要区域结束-->
        <div id="footer">
        </div>
    <script type="text/javascript" language="javascript" src="../scripts/jquery-1.10.2.min.js"></script>    
    <script type="text/javascript" language="javascript" src="../scripts/business/businessList.js"></script> 
    </body>
</html>
