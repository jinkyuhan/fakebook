package com.jkhan.fackebook.fakebookserver.e2e;

import com.jkhan.fakebookserver.FakebookServerApplication;
import com.jkhan.fakebookserver.user.UserAccountRepository;
import com.jkhan.fakebookserver.user.UserService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;

import java.util.UUID;

import static org.springframework.test.util.AssertionErrors.assertEquals;

@SpringBootTest(classes = FakebookServerApplication.class)
public class UserServiceTest {

    @Autowired
    private UserService userService;


    @Test
    public void getUserNicknameOrEmailTest() {
        assertEquals( "asdfasdf",
                null,
                userService.getUserByNicknameOrEmail("nickname", "email")
                        .orElse(null)
        );
    }
}
