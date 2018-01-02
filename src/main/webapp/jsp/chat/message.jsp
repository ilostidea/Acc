<%@page import="com.bit.acc.service.intfs.IUserService"%>
<%@page import="org.apache.shiro.subject.PrincipalCollection"%>
<%@page import="org.apache.shiro.SecurityUtils"%>
<%@page import="com.bit.acc.model.SysUser"%>
<%@page import="java.util.List"%>
<%@page import="org.springframework.web.context.support.WebApplicationContextUtils"%>
<%@page import="org.apache.shiro.subject.Subject"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>聊天</title>
<!-- ${static_context}${appJs}${toast} -->
<style type="text/css">
.bubbleDiv{  
    margin: 0 auto;
    height: 350px; 
    overflow-y:scroll;
    /*width: 1000px;
      border: 1px solid #4a4f58;
      */
}  
.bubbleItem{  
    width: 100%;  
}  
.bubble{  
    max-width: 655px;  
    position: relative;  
    line-height: 30px;  
    padding-left: 10px;  
    padding-top: 3px;  
    padding-bottom: 3px;  
    border-radius: 7px;  
    margin-top: 15px;  
    padding-right: 10px;  
    display: inline-block;  
}  
.leftBubble{  
    position: relative;  
    margin-left: 25px;  
    border: 1px solid #eee;  
    background-color: #fefefe;  
}  
.leftBubble .bottomLevel{  
    position: absolute;  
    top: 10px;  
    left: -10px;  
    border-top: 10px solid #eee;  
    border-left: 10px solid transparent;  
}  
.leftBubble .topLevel{  
    position: absolute;  
    top: 11px;  
    left: -8px;  
    border-top: 10px solid #fefefe;  
    border-left: 10px solid transparent;  
    z-index: 100;  
}  
.rightBubble{  
    position: relative;  
    margin-right: 25px;  
    float: right;  
    border: 1px solid #eee;  
    background-color: #32CD32;  
}  
.rightBubble .bottomLevel{  
    position: absolute;  
    bottom: 11px;  
    right: -10px;  
    border-bottom: 10px solid #eee;  
    border-right: 10px solid transparent;  
}  
.rightBubble .topLevel{  
    position: absolute;  
    bottom: 12px;  
    right: -8px;  
    border-bottom: 10px solid #32CD32;  
    border-right: 10px solid transparent;  
    z-index: 100;  
}  
.clearfix:after {  
    visibility: hidden;  
    display: block;  
    font-size: 0;  
    content: " ";  
    clear: both;  
    height: 0;  
}  
</style>
<link rel="stylesheet" href="http://apps.bdimg.com/libs/bootstrap/3.2.0/css/bootstrap.min.css" type="text/css" />
<script type="text/javascript" >
var wsEndPoint = "websocket";
var contextPath = "${request.contextPath}";
</script>
<script type="text/javascript" src="http://cdn.sockjs.org/sockjs-0.3.min.js"></script>
<script type="text/javascript" src="https://cdn.bootcss.com/stomp.js/2.3.3/stomp.min.js"></script>
<script type="text/javascript" src="http://cdn.bootcss.com/jquery/1.11.1/jquery.min.js"></script>
<script type="text/javascript" src="http://apps.bdimg.com/libs/bootstrap/3.2.0/js/bootstrap.min.js"></script>
        
<script type="text/javascript" src="message.js"></script>
</head>
<body>
	<%
		Subject subject = SecurityUtils.getSubject();
		PrincipalCollection col = subject.getPrincipals();
		IUserService userService = (IUserService) WebApplicationContextUtils.getRequiredWebApplicationContext( session.getServletContext() ).getBean("userService");
		List<SysUser> userModel = userService.findByAccount( col == null || col.isEmpty() ? "" : String.valueOf( col.getPrimaryPrincipal() ) );
	%>
	
	<input type="hidden" value="<%=( userModel==null || userModel.size() ==0 ?"":userModel.get(0).getId() )%>" id="username" />
	<input type="hidden" value="<%=( userModel==null || userModel.size() ==0 ?"":userModel.get(0).getNickName() )%>" id="nickname"/>
	<input type="hidden" id="to"/>
	<input type="hidden" id="tonickname"/>
    <div style="margin-bottom:20px"></div>  
    <div class="container">  
        <div class="row">  
           <!-- 在线用户列表 -->  
            <div class="col-md-3">  
                <div class="list-group">  
                <ul class="list-group" id="online">  
                  <li class="list-group-item active">在线用户（<%=( userModel==null || userModel.size() ==0 ?"":userModel.get(0).getNickName() )%>）</li>
                </ul>  
                </div>  
            </div>
              
            <!-- 聊天窗体-->  
            <div class="col-md-9" id="chatwindows">  
            </div>   
        </div>      
        <!-- 发送消息文本 -->  
        <div class="row" style="display:none" id="send_text">  
            <div class="col-md-2"></div>  
            <div class="col-md-1"></div>  
            <div class="col-md-9" >  
                <textarea id="messgeContext" class="form-control" rows="3" placeholder="按下回车键发送消息...."></textarea>  
            </div>  
        </div>  
        <!-- 发送按钮 -->  
        <div class="row" style="display:none" id="execute_send">  
            <div class="col-md-2"></div>  
            <div class="col-md-4">  
            </div>  
            <div class="col-md-5" style="margin-top:5px;">  
                <button type="button" class="btn btn-danger btn_lg" id="btn_close" >关闭</button>  
                <button type="button" class="btn btn-danger btn_lg" id="btn_send" >发送</button>  
            </div>  
        </div>  
    </div>  
</body>
</html>