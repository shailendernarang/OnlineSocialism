package com.ss.SocialistM.restcontroller;

import java.util.Date;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


import com.ss.SocialistB.model.Message;
import com.ss.SocialistB.model.OutputMessage;

@Controller
@RequestMapping("/")
public class ChatController {


    
  @MessageMapping("/chat")
  @SendTo("/topic/message")
  public OutputMessage sendMessage(Message message) {
    return new OutputMessage(message, new Date());
  }
}