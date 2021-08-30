package com.jkhan.fakebookserver.chat;


import com.jkhan.fakebookserver.common.CommonResponseBody;
import com.jkhan.fakebookserver.constant.ApiResult;
import com.jkhan.fakebookserver.user.UserAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController("/api/chatroom")
public class ChatRoomController {

    @Autowired
    private UserAccountRepository userAccountRepository;

    @Autowired
    private ChatRoomUserRepository chatRoomUserRepository;

    @Autowired
    private ChatService chatService;

    @GetMapping("/")
    public CommonResponseBody<Map<String, List<ChatRoom>>> getChatRoomList(Authentication authentication) {
        UUID userId = UUID.fromString(String.valueOf(authentication.getPrincipal()));
        Map<String, List<ChatRoom>> responseData = new HashMap<>();
        responseData.put("chatrooms",
                chatService.getAllChatRoomsOfUser(userId));

        return CommonResponseBody.<Map<String, List<ChatRoom>>>builder()
                .data(responseData)
                .result(ApiResult.SUCCESS)
                .build();
    }

}
