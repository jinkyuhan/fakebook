package com.jkhan.fackebook.fakebookserver.e2e;

import com.jkhan.fakebookserver.FakebookServerApplication;
import com.jkhan.fakebookserver.common.CommonResponseBody;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;

@SpringBootTest(classes = FakebookServerApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ChatRoomControllerTests {


    @Autowired
    private TestRestTemplate testRestTemplate;

    @Test
    public void getChatRoomsTests() {
    }
}
