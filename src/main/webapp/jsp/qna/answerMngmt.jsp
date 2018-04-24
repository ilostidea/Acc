<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<meta charset="UTF-8">
	<title>Acc管理工作台</title>
	<link href="<%=request.getContextPath() %>/style/bootstrap.min.css" rel="stylesheet">
	<link href="<%=request.getContextPath() %>/style/ie10-viewport-bug-workaround.css" rel="stylesheet">
    <link href="<%=request.getContextPath() %>/style/offcanvas.css" rel="stylesheet">
    <link href="<%=request.getContextPath() %>/style/fontawesome-all.css" rel="stylesheet" >
    <link href="<%=request.getContextPath() %>/style/messenger.css" rel="stylesheet" >
    <link href="<%=request.getContextPath() %>/style/messenger-theme-air.css" rel="stylesheet" >
	<link href="<%=request.getContextPath() %>/style/bootstrap-markdown.min.css" rel="stylesheet">
	<style type="text/css">
	/* messages */
	.messages {margin:0; padding:0;}
	.messages .messages {padding-top:19px;}
	.messages li {list-style-type:none; padding-bottom:0.3em;}
	.messages .well {padding:19px; background-color: #fefefe;}
	.messages .messages .well {border:none; -webkit-box-shadow:none;-moz-box-shadow:none; box-shadow:none; border-left:3px solid #eee;border-left:3px solid rgba(0, 0, 0, 0.05);}
	</style>
</head>
<body>
 
    <jsp:include page="../topbar.jsp"	 flush="true">
    	<jsp:param value="3" name="menu"/>
    </jsp:include>

	<div class="container">

		<div class="row row-offcanvas row-offcanvas-left">

			<div class="col-xs-6 col-sm-2 sidebar-offcanvas" id="sidebar">
				<div class="list-group">
					<a href="questionQuery.jsp" class="list-group-item">问题管理</a> <a
						href="answerQuery.jsp" class="list-group-item active">回答管理</a>
				</div>
			</div>
			<!--/.sidebar-offcanvas-->

			<div class="col-xs-12 col-sm-10 pull-right">
				<p class="pull-left visible-xs">
					<button type="button" class="btn btn-primary btn-xs" data-toggle="offcanvas">Toggle nav</button>
				</p>
				<div class="row">

					<div class="span9">
						<h2 id="qtitle" class="text-center">&nbsp;&nbsp;</h2>
						<ul class="messages">
							<li class="well" style="background-color: #f0f0f0;">
								<h3 style="margin-top: 0px;margin-left:-5px;">问</h3>
								<p id="question" class="message"></p>
								<span class="meta">
								<em id="quser"></em> 发布于 <em id="qtime"></em>
								<a id="qstatus" title="点击查看"></a>
								</span>
							</li>
							<li class="well">
								<h3 style="margin-top: 0px;margin-left:-5px;">答</h3>
								<p id="answer" class="message"></p>
								<span class="meta"><em id="answerUser"></em> 发布于 <em id="answerTime"></em></span>
								<a id="answerStatus"></a>
								<span class="pull-right" id="answerStatic"></span>
								<ul id="pumps" class="messages">
								</ul>
							</li>
						</ul>
					</div>

				</div>
			</div>
			<!--/.col-xs-12.col-sm-9-->

		</div>
		<!--/row-->

	</div>
	<!--/.container-->

	<footer class="footer" >
        <p class="text-muted">acc admin &copy; 2017</p>
    </footer>
    <!-- Bootstrap core JavaScript
    ================================================== -->
    <!-- Placed at the end of the document so the pages load faster -->
    <!-- script src="../../js/jquery-3.2.1.slim.js"></script> -->
    <script src="<%=request.getContextPath() %>/js/jquery.min.js"></script>
    <script src="<%=request.getContextPath() %>/js/bootstrap.js"></script>
    <!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
    <script src="<%=request.getContextPath() %>/js/ie10-viewport-bug-workaround.js"></script>
    <script src="<%=request.getContextPath() %>/js/offcanvas.js"></script>
    <script src="<%=request.getContextPath() %>/js/messenger.js"></script>
    <script>
	$._messengerDefaults = {
         extraClasses: 'messenger-fixed messenger-theme-air messenger-on-bottom'
    }
	
	function switchStatus(type, sid, toStatus, element){
        var url = "";
        if(type == "answer")
        	url = "/answer/admin/switchStatus";
        else
        	url = "/pump/admin/switchStatus";
		$.ajax({
			type : "POST",
			url : url,
			data :  { "id" : sid, "status" : toStatus } ,
			contentType : "application/x-www-form-urlencoded",
			dataType : "text",
			success : function(){
				Messenger().post({
					message : ('已' + (toStatus?"启用":"禁用") + '！'),//提示信息
      				type : 'success',//消息类型。error、info、success
      				hideAfter : 2,//多长时间消失
      				showCloseButton : true,//是否显示关闭按钮
      				hideOnNavigate : false //是否隐藏导航
      		    });
				$(element).text( (toStatus?"启用":"禁用") );
				$(element).unbind();
				$(element).click(function(e){
					switchStatus(type, sid, !toStatus, element);
					e.stopPropagation();
				});
		    }
		});
	}
	
	function query(){
		var id = ${param.id};
		$.get("/answer/details",
	    		  { answerID : id },
	    		  function(responseTxt, status){
					if(status == "success"){
						var data = responseTxt.data;
						$("#qtitle").text(data.question.title);
						$("#question").text(data.question.question);
						$("#quser").text(data.question.user.nickName);
						$("#qtime").text(data.question.createTime);
						$("#qstatus").text(data.question.status?"启用":"禁用");
						$("#qstatus").css({"cursor":"pointer"});
						$("#qstatus").click( function(){
							window.location.href="questionMngmt.jsp?id="+data.question.id;
						});
						
						var answer = data.answer;
						$("#answer").text(answer.answer);
						$("#answerUser").text(answer.user.nickName);
						$("#answerTime").text(answer.createTime);
						$("#answerStatus").text(answer.status?"启用":"禁用");
						$("#answerStatus").css({"cursor":"pointer"});
						$("#answerStatus").click( function(){
							switchStatus("answer", answer.id, !answer.status, $("#answerStatus"));
						});
						$("#answerStatic").text("评论["+answer.pumps.length+"]    顶["+answer.approveCount+"]    踩["+answer.disapproveCount+"]");

						var pumps = answer.pumps;
						for(var j=0; j<pumps.length; j++){
							var pump = pumps[j];
							var pumpLi = $("<li class=\"well\"></li>");
							var pumpP = $("<p class=\"message\"></p>").text(pump.content);
							pumpLi.append(pumpP);
							var pumpSpan = $("<span class=\"meta\"><em>"+pump.user.nickName+"</em>发布于<em>" + pump.createTime + "</em>&nbsp;&nbsp;</span>");
							let alink = $("<a style='cursor:pointer;'></a>");
							alink.text(pump.status?'启用':'禁用');
							$(alink).click( function(){
								switchStatus("pump", pump.id, !pump.status, alink);
							});
							pumpSpan.append(alink);
							pumpLi.append(pumpSpan);
							$("#pumps").append(pumpLi);
						}

					}
				},
			   "json"
	    );
	}

	$(document).ready(function(){
		query();
	});
	</script>
</body>
</html>