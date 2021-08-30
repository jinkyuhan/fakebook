package com.jkhan.fackebook.fakebookserver.e2e;

import com.jkhan.fackebook.fakebookserver.TestUtils;
import com.jkhan.fakebookserver.FakebookServerApplication;
import com.jkhan.fakebookserver.auth.LoginDto;
import com.jkhan.fakebookserver.common.CommonResponseBody;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;


@SpringBootTest(classes = FakebookServerApplication.class, webEnvironment = WebEnvironment.RANDOM_PORT)
public class AuthControllerTests {
    @Autowired
    TestRestTemplate testRestTemplate;

    @Test
    public void loginUserRequestTest() {
        ResponseEntity<CommonResponseBody> response = testRestTemplate.postForEntity(
                TestUtils.buildUrl("/api/auth/login"),
                new LoginDto("gkswlsrb97@gmail.com", "1234"),
                CommonResponseBody.class
        );
        
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertEquals("SUCCESS", response.getBody().getResult());
    }

}
