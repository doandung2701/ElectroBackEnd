package com.example.demo.web;

import com.example.demo.model.Review;
import com.example.demo.payload.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
public class ClassController {

	@Autowired
	private SimpMessagingTemplate template;

	@MessageMapping("/chat.sendReview/{productId}")
	//@SendTo("/topic/public/1")
	public void chatMessage(@DestinationVariable Long productId,
							   @Payload Review review) {
		this.template.convertAndSend("/topic/public/"+productId,review);
	}
	
	@MessageMapping("/chat.addUser")
    @SendTo("/topic/public")
    public Message addUser(@Payload Message chatMessage,
                               SimpMessageHeaderAccessor headerAccessor) {
        // Add username in web socket session
        headerAccessor.getSessionAttributes().put("username", chatMessage.getSender());
        return chatMessage;
    }
	
}
