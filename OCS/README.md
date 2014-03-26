###   项目名称：OCS在线计费系统
###   开发工具：MyEclipse10、Tomcat7、Oracle
###   开发平台：Linux
###   责任描述：登录模块、公共模块、角色管理模块、用户管理模块、资费管理模块、账户账号模块和业务账号模块的前端和后端开发
###   技术要点：
    前端技术——html+css+javascript+jQuery+AJAX
    后端技术——spring+struts+java+jdbc+jsp
###   项目描述：
模拟电信公司的unix服务器的出租业务而设计的子系统，实现给电信公司的工作人员进行管理客户信息、
业务信息等功能。前端基于html+css进行布局，并基于jQuery框架编写脚本，另外，还使用了AJAX进行异
步验证和异步提交。后端基于MVC思想开发，控制层采用struts的Action，表现层采用jsp+ognl标签，数据
访问层采用jdbc，采用spring整合系统。另外，基于struts拦截器实现了检验网页超时和权限检查功能。
    
    PS：请使用Oracle数据库测试，不然某些SQL语句可能出现问题。整个系统需要的sql语句已导出在config
    文件夹的dbinit.sql文件中。 权限文件写在了config/privileges.xml中。请查看数据库中user_info表里
    的数据，里面的用户数据可以登录该系统，用户super拥有查看所有模块的权限。其他用户有其各自的权限。
    用户表示是先间接和角色表作关联，再通过角色表与权限作关联实现权限的控制。最后，请使用OCS/login/login_toLogin
    域名来打开登录页面。
