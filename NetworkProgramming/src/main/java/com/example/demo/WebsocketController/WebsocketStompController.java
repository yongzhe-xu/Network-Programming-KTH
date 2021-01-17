package com.example.demo.WebsocketController;

import com.example.demo.MessageQueueClass.MessageRequest;
import com.example.demo.MessageQueueClass.MessageResponse;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class WebsocketStompController {

    @MessageMapping("/ChatRoomOne")//message will be sent here if "/ChatRoom" is evoked
    @SendTo("/topic/chat1")//send to all subscribers under "/topic/chat"
    public MessageResponse sendMessageToRoomOne(MessageRequest request)
    {
        return new MessageResponse(request.getName() +  ":" + request.getMessage()+" At: " + request.getDate());
    }

    @MessageMapping("/ChatRoomTwo")
    @SendTo("/topic/chat2")
    public MessageResponse sendMessageToRoomTwo(MessageRequest request)
    {
        return new MessageResponse(request.getName() +  ":" + request.getMessage()+" At: " + request.getDate());
    }

    @MessageMapping("/ChatRoomThree")
    @SendTo("/topic/chat3")
    public MessageResponse sendMessageToRoomThree(MessageRequest request)
    {
        return new MessageResponse(request.getName() +  ":" + request.getMessage()+" At: " + request.getDate());
    }
}
