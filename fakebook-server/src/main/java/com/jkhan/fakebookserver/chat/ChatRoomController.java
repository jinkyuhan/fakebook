package com.jkhan.fakebookserver.chat;


import com.jkhan.fakebookserver.common.PageCursorVo;
import com.jkhan.fakebookserver.common.CommonResponseBody;
import com.jkhan.fakebookserver.common.exception.InvalidInputException;
import com.jkhan.fakebookserver.constant.ApiResult;
import com.jkhan.fakebookserver.user.UserAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/api/chatrooms")
public class ChatRoomController {

    @Autowired
    private UserAccountRepository userAccountRepository;

    @Autowired
    private ChatRoomUserRepository chatRoomUserRepository;

    @Autowired
    private ChatService chatService;

    @GetMapping
    public CommonResponseBody<Map<String, List<ChatRoom>>> getChatRoomList(
            @RequestParam(name = "idCursor", required = false) String idCursor,
            @RequestParam(name = "dateCursor", required = false) Long dateCursor,
            @RequestParam(name = "limit", required = false) Integer limit,
            Authentication authentication
    ) {
        UUID userId = UUID.fromString(String.valueOf(authentication.getPrincipal()));
        Map<String, List<ChatRoom>> responseData = new HashMap<>();
        List<ChatRoom> chatroomsOfUser;

        if (limit == null) {
            // no page
            chatroomsOfUser = chatService.getAllChatRoomsOfUser(userId);
        } else {
            // page
            if (idCursor == null && dateCursor == null) {
                // first page
                chatroomsOfUser = chatService.getFirstPageChatRoomsOfUser(userId, limit);
            } else if (idCursor != null && dateCursor != null) {
                chatroomsOfUser = chatService.getChatRoomsOfUserWithCursor(userId, limit, PageCursorVo.of(UUID.fromString(idCursor), dateCursor));
            } else {
                throw new InvalidInputException("All the page request require params [idCursor, dateCursor]", "");
            }
        }



        responseData.put("chatrooms", chatroomsOfUser);
        return CommonResponseBody.<Map<String, List<ChatRoom>>>builder()
                .data(responseData)
                .result(ApiResult.SUCCESS)
                .build();
    }

}
