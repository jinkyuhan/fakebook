package com.jkhan.fackebook.fakebookserver;

import com.jkhan.fakebookserver.FakebookServerApplication;
import com.jkhan.fakebookserver.auth.LoginSession;
import com.jkhan.fakebookserver.auth.LoginSessionRepository;
import com.jkhan.fakebookserver.auth.AuthConfigConstant;

import com.jkhan.fakebookserver.chat.controller.ChatRoomController;
import com.jkhan.fakebookserver.user.UserAccount;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@SpringBootTest(classes = FakebookServerApplication.class)
public class FakebookServerApplicationTests {

    @Autowired
    private AuthConfigConstant authConfigConstant;

    @Autowired
    private LoginSessionRepository sessionRepository;

    @Autowired
    private ChatRoomController chatRoomController;

    @Test
    @Transactional
    void contextLoads() {
        List<LoginSession> sessions = sessionRepository.findAll();
        UserAccount user = sessions.get(0).getUserAccount();
        String email = user.getEmail();
    }

    @Test
    void ChatRoomUserRepositoryTest() {
    }

}
