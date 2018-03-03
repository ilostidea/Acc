/**
 * 
 */
var messageArr = {};  
$(function(){  
    mseeage.init();  
    bindEvent.init();  
      
});  
  
var bindEvent = {  
        //初始化  
        init :function(){  
            this.sendEvent();  
        },  
        //渲染视图  
        viewEvent: function(){  
            $(".list-group-item").not(":eq(0)").click(function(){  
                var name = $(this).attr("value");
                var nickname = $(this).attr("nickname");
                $(".panel-primary").hide();
                $("#"+name).show();
                $("#to").val(name);
                $("#tonickname").val(nickname);
                messageArr[name] = null;
                $(".list-group-item[value='" + name + "']").find("span").html("");
                $("#send_text").show();
                $("#execute_send").show();
            });  
        },  
        //发送消息  
        sendEvent : function(){  
            //发送  
            $('#btn_send').click(function(){  
                mseeage.snedMessage();  
            });  
            //关闭  
            $('#btn_close').click(function(){  
                $(".panel-primary").remove();  
                 $("#send_text").hide();  
                 $("#execute_send").hide();  
            });  
            //enter事件  
            $(window).keydown(function(event){  
                  if(event.keyCode ==13){  
                      mseeage.snedMessage();  
                      }  
                  });  
        }  
}  
  
var mseeage ={  
    init : function(){  
        this.initOnlineUsers();  
        this.initSocket();  
    },  
    //初始化在线用户  
    initOnlineUsers : function(){  
        $.ajax({  
            url:contextPath + "/sysUser/admin/list",
            success :  function(result){
                var $html ="";
                var $content = "";
                var data = result.data;
                for (var i = 0; i < data.length; i++) {
                    var  userName = data[i].nickName;
                    var  id = data[i].id;
                    var  curid = $("#username").val();
                    if(id != curid){
	                     $html +=" <li class='list-group-item' value='"+id+"' nickname='"+userName+"'>"+userName+"<span class='badge'></span></li>"  
	                     $content +="<div class='panel panel-primary' style='height:400px;display:none' id='"+id+"'>" +  
	                            " <div class='panel-heading'>聊天栏--<span>"+userName+"</span></div>" +  
	                            "<div id='tolk' class='panel-body bubbleDiv'><div id=\"msg_end\" class=\"bubbleItem\"><span class=\"bubble leftBubble\">message<span class=\"bottomLevel\"></span><span class=\"topLevel\"></span></span></div></div></div>";
                    }
                }  
                $('#chatwindows').append($content);
                $("#online").not("li:eq(0)").append($html);
                //绑定点击事件  
                bindEvent.viewEvent();  
            }  
        })  
    },
    //初始化
    initSocket : function(){
        var userName = $("#username").val();
        var socket = new SockJS(contextPath + "/" + wsEndPoint);
        var stompClient = Stomp.over(socket);
        stompClient.connect({}, function(frame) {
			stompClient.subscribe("/user/" + userName + "/message", function(greeting){
			    var result = eval("("+greeting.body+")");
			    console.log(result);
			    var msg_ = "<div class=\"bubbleItem\"><span class=\"bubble leftBubble\">"
			    	+ result.message
			    	+ "<span class=\"bottomLevel\"></span><span class=\"topLevel\"></span></span></div>";
			    //$("#"+result.from).find("div:eq(1)").append(msg_);
			    $("#msg_end").before(msg_);
                $("#msg_end").scrollIntoView();
			    if($('#to').val() != result.from) {
			        if (messageArr[result.from]) {
			            messageArr[result.from]++;
			        } else {
			            messageArr[result.from] = 1;
			        }
			        $(".list-group-item[value='" + result.from + "']").find("span").html(messageArr[result.from]);
			    }
			});
        });

    },
    //发送消息  
    snedMessage : function(){  
        var messge = $("#messgeContext").val();
        var from = $("#username").val();
        var fromnickname = $("#nickname").val();
        var to = $("#to").val();
        var tonickname = $("#tonickname").val();
        if(messge.trim().length>0 && $("#to").val()){
            var data = {
                from : from,
                fromnickname : fromnickname,
                to : to,
                tonickname : tonickname,
                message : messge
            };
            $.ajax({
                url:contextPath + "/message/send",
                data: data,
                success :  function(msg){
                	var msg_= "<div><div class=\"bubbleItem clearfix\" style='text-align:right;'><span class=\"bubble rightBubble\">"
			    	+ data.message
			    	+ "<span class=\"bottomLevel\"></span><span class=\"topLevel\"></span></span></div>";
                    $("#messgeContext").val("");
				    $("#msg_end").before(msg_);
				    $("#msg_end").scrollIntoView();
                    //$("#"+data.to).find("div:eq(1)").scrollTop = $("#"+data.to).find("div:eq(1)").scrollHeight;
                }
            })
        }else{
            toastr.warning("请输入消息!");
        }
    }
};