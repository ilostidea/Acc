package com.bit.acc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/message")
public class WebSocketController {

	@Autowired
	private SimpMessagingTemplate template;

	@RequestMapping(value="/send", method=RequestMethod.GET)
    public @ResponseBody String sendMessage(@RequestParam("from") String from, @RequestParam("fromnickname") String fromnickname, 
    		@RequestParam("to") String to, @RequestParam("tonickname") String tonickname, @RequestParam("message") String message) throws Exception {

        String convertMessage ="{from:'"+from+"',to:'" + to + "',message:'" +message+"'}"; 
        //template.convertAndSendToUser(id, "/send/" + to, message);
        template.convertAndSendToUser(to, "/message", convertMessage);
        return "ok";
    }
}
