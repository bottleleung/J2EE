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
                <li><a href="../role/role_find" class="role_on"></a></li>
                <li><a href="../user/user_find" class="user_off"></a></li>
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
            <form action="" method="">
                <!--查询-->
                <div class="search_add">
                    <input type="button" value="增加" class="btn_add" onclick="location.href='role_toAddRole';" />
                </div>  
                <!--删除的操作提示-->
                <div id="operate_result_info" class="operate_success">
                    <img src="../images/close.png" onclick="this.parentNode.style.display='none';" />
                    删除成功！
                </div> <!--删除错误！该角色被使用，不能删除。-->
                <!--数据区域：用表格展示数据-->     
                <div id="data">                      
                    <table id="datalist">
                        <tr>                            
                            <th>角色 ID</th>
                            <th>角色名称</th>
                            <th class="width600">拥有的权限</th>
                            <th class="td_modi"></th>
                        </tr>  
                        <s:iterator value="roleVOs">
                        	<tr>
	                            <td><s:property value="id" /></td>
	                            <td><s:property value="name" /></td>
	                            <td><s:property value="privilegeNames" /></td>
	                            <td>
	                                <input type="button" value="修改" class="btn_modify" onclick="location.href='role_toUpdateRole?id=<s:property value="id" />';"/>
	                                <input type="button" value="删除" class="btn_delete" onclick="location.href='role_delete?id=<s:property value="id" />';" />
	                            </td>
	                        </tr>
                    	</s:iterator>                    
                    </table>
                </div> 
                <!--分页-->
                <div id="pages">
        	        <s:if test="page==1">
             			<a href="#">上一页</a>
             		</s:if>
             		<s:else>
             			<a href="role_find.action?page=<s:property value="page-1"/>">上一页</a>
             		</s:else>
        	        
        	        <s:iterator begin="1" end="totalPages" var="p">
        	        	<s:if test="#p==page">
                    		<a href="role_find.action?page=<s:property value="#p"/>" class="current_page"><s:property value="#p"/></a>
                    	</s:if>
                    	<s:else>
                    		<a href="role_find.action?page=<s:property value="#p"/>"><s:property value="#p"/></a>
                    	</s:else>
        	        </s:iterator>
                    
                    <s:if test="page==totalPages">
                    	<a href="#">下一页</a>
                    </s:if>
                    <s:else>
                    	<a href="role_find.action?page=<s:property value="page+1"/>">下一页</a>
                    </s:else>
                </div>
            </form>
        </div>
        <!--主要区域结束-->
        <div id="footer">
        </div>
    </body>
</html>
