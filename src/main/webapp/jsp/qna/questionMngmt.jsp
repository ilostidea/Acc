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

  <body>
    <nav class="navbar navbar-fixed-top navbar-inverse">
      <div class="container">
        <div class="navbar-header">
          <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
            <span class="sr-only">Toggle navigation</span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
          </button>
          <a class="navbar-brand" href="../index.jsp">Acc Admin</a>
        </div>
        <div id="navbar" class="collapse navbar-collapse">
          <ul class="nav navbar-nav">
            <li><a href="../../index.jsp">统计</a></li>
            <li><a href="../gaap/index.jsp">财会</a></li>
            <li class="active"><a href="index.jsp">问答</a></li>
            <li class="dropdown">
              <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Dropdown <span class="caret"></span></a>
              <ul class="dropdown-menu">
                <li><a href="#">Action</a></li>
                <li><a href="#">Another action</a></li>
                <li><a href="#">Something else here</a></li>
                <li role="separator" class="divider"></li>
                <li class="dropdown-header">Nav header</li>
                <li><a href="#">Separated link</a></li>
                <li><a href="#">One more separated link</a></li>
              </ul>
            </li>
          </ul>
          <ul class="nav navbar-nav navbar-right">
			<li class="dropdown"><a href="#" class="dropdown-toggle"><i class="fa fa-bell fa-fw"></i><span class="badge badge-green">3</span></a></li>
			<li id="topbar-chat" class="hidden-xs"><a href="#" class="dropdown-toggle"><i class="fa fa-comments"></i><span class="badge badge-info">3</span></a></li>
			<li class="dropdown">
				<a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">
				    <img src="" alt="" class="img-responsive img-circle"/>&nbsp;<span class="hidden-xs">Robert John</span>&nbsp;<span class="caret"></span>
				</a>
				<ul class="dropdown-menu">
					<li><a href="#"><i class="fa fa-user"></i>My Profile</a></li>
					<li class="divider"></li>
					<li><a href="Login.html"><i class="fa fa-key"></i>Log Out</a></li>
				</ul>
			</li>
		  </ul>
        </div><!-- /.nav-collapse -->
      </div><!-- /.container -->
    </nav><!-- /.navbar -->

    <div class="container">

      <div class="row row-offcanvas row-offcanvas-left" >

        <div class="col-xs-6 col-sm-2 sidebar-offcanvas" id="sidebar">
          <div class="list-group">
            <a href="questionQuery.jsp" class="list-group-item active">问题管理</a>
            <a href="answerQuery.jsp" class="list-group-item">回答管理</a>
          </div>
        </div><!--/.sidebar-offcanvas-->
          
        <div class="col-xs-12 col-sm-10 pull-right">
          <p class="pull-left visible-xs">
            <button type="button" class="btn btn-primary btn-xs" data-toggle="offcanvas">Toggle nav</button>
          </p>
          <div class="row">
	            
	           <div class="span9">
	            <h2 id="title" class="text-center"></h2>
					<ul class="messages">
						<li class="well">
							<p id="question" class="message"></p>
							<span class="meta"><em id="quser"></em> 发布于 <em id="qtime"></em></span>
							<a id="qstatus"></a>
							<span class="pull-right" id="qstatic"></span>
							<ul id="answers" class="messages"><!-- 
								<li class="well">
									<p class="message">
										Nascetur ridiculus mus. Phasellus enim nibh, congue nec tincidunt sed, luctus ullamcorper leo. Nunc ac mauris augue. Nam non nulla tellus, vitae volutpat nibh. Maecenas fringilla vestibulum neque vitae tristique. Ut fermentum accumsan dolor, ut tincidunt lacus dictum non. Proin non ultrices libero. Praesent hendrerit, dolor ut facilisis porta, lorem massa ullamcorper dolor, at dictum elit augue vel lorem. Pellentesque vitae elit quis erat congue gravida ac sed urna. Vivamus vitae purus lectus. Maecenas nec dui lorem. Mauris viverra, est et mattis malesuada, sapien lectus congue justo, eget ultricies lorem ante a nulla.
									</p>
									<span class="meta">Written <em>2 days ago</em> by <em>Jill</em></span>
									<ul class="messages">
										<li class="well">
											<p class="message">
												Etiam felis tellus.
											</p>
											<span class="meta">Written <em>1 day ago</em> by <em>Jill</em></span>
										</li>
									</ul>
								</li> -->
							</ul>
						</li>
					</ul>
				</div>
	            
	          
          </div>
        </div><!--/.col-xs-12.col-sm-10-->

      </div><!--/row-->

    </div><!--/.container-->

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
    <script src="<%=request.getContextPath() %>/js/bootstrap-markdown.js"></script>
    <script src="<%=request.getContextPath() %>/js/bootstrap-markdown.zh.js"></script>
    <script src="<%=request.getContextPath() %>/js/marked.js"></script>
    <script>
	$._messengerDefaults = {
         extraClasses: 'messenger-fixed messenger-theme-air messenger-on-bottom'
    }
	
	function query(){
		var id = ${param.id};
		$.get("/question/detail",
	    		  { questionID : id },
	    		  function(responseTxt, status){
					if(status == "success"){
						var data = responseTxt.data;
						$("#title").text(data.title);
						$("#question").text(data.question);
						$("#quser").text(data.user.nickName);
						$("#qtime").text(data.createTime);
						$("#qstatus").text(data.status?"启用":"禁用");
						$("#qstatic").text("回答["+data.answerCount+"]    收藏["+data.collectedCount+"]");
						var answers = data.answers;
						for( var i=0; i<answers.length; i++){
							var answer = answers[i];
							var li = $("<li class=\"well\"></li>");
							var p = $("<p class=\"message\"></p>").text(answer.answer);
							li.append(p);
							var span1 = $("<span class=\"meta\"><em>"+answer.user.nickName+"</em>发布于<em>" + answer.createTime + "</em>&nbsp;&nbsp;<a>"+(answer.status?'启用':'禁用')+"</a></span>");
							li.append(span1);
							var span2 = $("<span class=\"meta pull-right\">评论["+answer.pumpCount+"]    顶["+answer.approveCount+"]    踩["+answer.disapproveCount+"]</span>");
							li.append(span2);
							$("#answers").append(li);
							var pumps = answer.pumps;
							var pumpUl = $("<ul class=\"messages\"></ul>");
							if(pumps.length > 0)
								li.append(pumpUl);
							for(var j=0; j<pumps.length; j++){
								var pump = pumps[j];
								var pumpLi = $("<li class=\"well\"></li>");
								var pumpP = $("<p class=\"message\"></p>").text(pump.content);
								pumpLi.append(pumpP);
								var pumpSpan = $("<span class=\"meta\"><em>"+pump.user.nickName+"</em>发布于<em>" + pump.createTime + "</em>&nbsp;&nbsp;<a>"+(pump.status?'启用':'禁用')+"</a></span>");
								pumpLi.append(pumpSpan);
								pumpUl.append(pumpLi);
							}
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