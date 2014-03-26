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
            <form action="" method="">
                <!--排序-->
                <div class="search_add">
                    <!-- <div>
                        <select class="sort_condition01">
                            <option>请选择</option>
                        </select>
                        <select class="sort_condition02">
                        	<option></option>
                        </select>
                    </div> -->
                    <input type="button" value="增加" class="btn_add" onclick="location.href='cost_toAddCost';" />
                </div> 
                <!--启用操作的操作提示-->
                <div id="operate_result_info" class="operate_success">
                    <img src="../images/close.png" onclick="this.parentNode.style.display='none';" />
                    删除成功！
                </div>    
                <!--数据区域：用表格展示数据-->     
                <div id="data">            
                    <table id="datalist">
                        <tr>
                            <th>资费ID</th>
                            <th class="width100">资费名称</th>
                            <th>基本时长</th>
                            <th>基本费用</th>
                            <th>单位费用</th>
                            <th>创建时间</th>
                            <th>开通时间</th>
                            <th class="width50">状态</th>
                            <th class="width200"></th>
                        </tr>
	                    <s:iterator value="costs">
	                     	<tr>
	                            <td><s:property value="id"/></td>
	                            <td><a href="cost_toCostDetail?id=<s:property value="id"/>"><s:property value="name"/></a></td>
	                            <td><s:property value="basicDuration"/></td>
	                            <td><s:property value="basicCost"/></td>
	                            <td><s:property value="unitCost"/></td>
	                            <td><s:date name="createTime" format="yyyy-MM-dd"/></td>
	                            <td><s:date name="startTime" format="yyyy-MM-dd"/></td>
	                        	<td>
	                        		<s:if test="status==0">暂停</s:if>
	                        		<s:else>开通</s:else>
	                        	</td>          
	                            <td>
	                            	<s:if test="status==0"> 
	                            		<input id="startCost" type="button" value="启用" class="btn_start" onclick="location.href='cost_start?id=<s:property value="id"/>';" />
	                                	<input type="button" value="修改" class="btn_modify" onclick="location.href='cost_toUpdateCost?id=<s:property value="id"/>';" />
	                                	<input type="button" value="删除" class="btn_delete" onclick="location.href='cost_delete?id=<s:property value="id"/>';" />
	                            	</s:if>                           	                                
	                            </td>
	                        </tr>
	                    </s:iterator>                      
                    </table>
                    <p>业务说明：<br />
                    1、创建资费时，状态为暂停，记载创建时间；<br />
                    2、暂停状态下，可修改，可删除；<br />
                    3、开通后，记载开通时间，且开通后不能修改、不能再停用、也不能删除；<br />
                    4、业务账号修改资费时，在下月底统一触发，修改其关联的资费ID（此触发动作由程序处理）
                    </p>
                </div>
                <!--分页-->
                <div id="pages">
             		<s:if test="page==1">
             			<a href="#">上一页</a>
             		</s:if>
             		<s:else>
             			<a href="cost_find.action?page=<s:property value="page-1"/>">上一页</a>
             		</s:else>
        	        
        	        <s:iterator begin="1" end="totalPages" var="p">
        	        	<s:if test="#p==page">
                    		<a href="cost_find.action?page=<s:property value="#p"/>" class="current_page"><s:property value="#p"/></a>
                    	</s:if>
                    	<s:else>
                    		<a href="cost_find.action?page=<s:property value="#p"/>"><s:property value="#p"/></a>
                    	</s:else>
        	        </s:iterator>
                    
                    <s:if test="page==totalPages">
                    	<a href="#">下一页</a>
                    </s:if>
                    <s:else>
                    	<a href="cost_find.action?page=<s:property value="page+1"/>">下一页</a>
                    </s:else>
                    
                </div>
            </form>
        </div>
        <!--主要区域结束-->
        <div id="footer">
        </div>
    <script type="text/javascript" language="javascript" src="../scripts/jquery-1.10.2.min.js"></script>     
    </body>
</html>
