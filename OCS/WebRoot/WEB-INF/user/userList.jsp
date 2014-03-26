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
            <form id="user_form" action="user_find" method="post">
                <!--查询-->
                <div class="search_add">
                    <div>模块：
                        <select id="selModules" class="select_search" name="privilegeId">
                            <option value="0" <s:if test="privilegeId==0">selected="selected"</s:if> >全部</option>
                            <option value="1" <s:if test="privilegeId==1">selected="selected"</s:if> >角色管理</option>
                            <option value="2" <s:if test="privilegeId==2">selected="selected"</s:if> >用户管理</option>
                            <option value="3" <s:if test="privilegeId==3">selected="selected"</s:if> >资费管理</option>
                            <option value="4" <s:if test="privilegeId==4">selected="selected"</s:if> >账务账号</option>
                            <option value="5" <s:if test="privilegeId==5">selected="selected"</s:if> >业务账号</option>
                        </select>
                    </div>
                    <div>角色：<input name="roleName" type="text" value="<s:property value="roleName" />" class="text_search width200" /></div>
                    <div><input type="button" value="搜索" class="btn_search" onclick="findUsers(1);" /></div>
                    <input type="button" value="增加" class="btn_add" onclick="location.href='user_toAddUser';" />
                    <input id="user_page" type="hidden" name="page" value="" />
                </div>
                <!--删除和密码重置的操作提示-->
                <div id="operate_result_info" class="operate_fail">
                    <img src="../images/close.png" onclick="this.parentNode.style.display='none';" />
                    <span>删除失败！数据并发错误。</span><!--密码重置失败！数据并发错误。-->
                </div> 
                <!--数据区域：用表格展示数据-->     
                <div id="data">            
                    <table id="datalist">
                        <tr>
                            <th>用户ID</th>
                            <th>姓名</th>
                            <th>登录名</th>
                            <th>电话</th>
                            <th>电子邮件</th>
                            <th>授权日期</th>
                            <th class="width100">拥有角色</th>
                            <th></th>
                        </tr>
                        <s:iterator value="users">
                        	<tr>
	                            <td><s:property value="id" /></td>
	                            <td><s:property value="name" /></td>
	                            <td><s:property value="userCode" /></td>
	                            <td><s:property value="telephone" /></td>
	                            <td><s:property value="email" /></td>
	                            <td><s:date name="enrollDate" format="yyyy MM dd" /></td>
	                            <td>
	                                <a class="summary" onmouseover="showUserRoles(true, $(this));" onmouseout="showUserRoles(false, $(this));"><s:property value="roleNames.substring(0,3)+'……'" /></a>
	                                <!--浮动的详细信息-->
	                                <div class="detail_info">
	                                	<s:property value="roleNames" />
	                                </div>
	                            </td>
	                            <td class="td_modi">
	                                <input type="button" value="修改" class="btn_modify" onclick="location.href='user_toUpdateUser?userId=<s:property value="id" />';" />
	                                <input type="button" value="删除" class="btn_delete" onclick="location.href='user_delete?userId=<s:property value="id" />';" />
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
             			<a href="javascript:findUsers(<s:property value="page-1"/>);">上一页</a>
             		</s:else>
        	        
        	        <s:iterator begin="1" end="totalPages" var="p">
        	        	<s:if test="#p==page">
                    		<a href="javascript:findUsers(<s:property value="#p"/>);" class="current_page"><s:property value="#p"/></a>
                    	</s:if>
                    	<s:else>
                    		<a href="javascript:findUsers(<s:property value="#p"/>);"><s:property value="#p"/></a>
                    	</s:else>
        	        </s:iterator>
                    
                    <s:if test="page==totalPages">
                    	<a href="#">下一页</a>
                    </s:if>
                    <s:else>
                    	<a href="javascript:findUsers(<s:property value="page+1"/>);">下一页</a>
                    </s:else>
                </div>                    
            </form>
        </div>
        <!--主要区域结束-->
        <div id="footer">
        </div>
    <script type="text/javascript" language="javascript" src="../scripts/jquery-1.10.2.min.js"></script>    
    <script type="text/javascript" language="javascript" src="../scripts/user/userList.js"></script>
    </body>
</html>
