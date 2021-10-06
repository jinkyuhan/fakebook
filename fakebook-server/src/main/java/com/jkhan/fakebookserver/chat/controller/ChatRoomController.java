package com.jkhan.fakebookserver.chat.controller;


import java.util.UUID;

import com.jkhan.fakebookserver.chat.ChatRoomUserRepository;
import com.jkhan.fakebookserver.chat.ChatService;
import com.jkhan.fakebookserver.chat.dto.ChatRoomListDto;
import com.jkhan.fakebookserver.common.CommonResponseBody;
import com.jkhan.fakebookserver.common.PageCursorVo;
import com.jkhan.fakebookserver.common.constant.ApiResult;
import com.jkhan.fakebookserver.common.exception.InvalidInputException;
import com.jkhan.fakebookserver.user.UserAccountRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
    public CommonResponseBody<ChatRoomListDto> getChatRoomList(
            @RequestParam(name = "idCursor", required = false) String idCursor,
            @RequestParam(name = "dateCursor", required = false) Long dateCursor,
            @RequestParam(name = "limit", required = false) Integer limit,
            Authentication authentication
    ) {
        UUID userId = UUID.fromString(String.valueOf(authentication.getPrincipal()));
        ChatRoomListDto responseData = new ChatRoomListDto();

        if (limit == null) {
            // no page
            responseData.setChatrooms(chatService.getAllChatRoomsOfUser(userId));
        } else {
            // page
            if (idCursor == null && dateCursor == null) {
                // first page
                responseData.setChatrooms(chatService.getFirstPageOfChatRoomsOfUser(userId, limit));
            } else if (idCursor != null && dateCursor != null) {
                responseData.setChatrooms(chatService.getChatRoomsOfUserWithCursor(
                        userId,
                        limit,
                        PageCursorVo.of(UUID.fromString(idCursor), dateCursor)));
            } else {
                throw new InvalidInputException("All the page request require params [idCursor, dateCursor]", "");
            }
        }

        return CommonResponseBody.<ChatRoomListDto>builder()
                .data(responseData)
                .result(ApiResult.SUCCESS)
                .build();
    }

}
