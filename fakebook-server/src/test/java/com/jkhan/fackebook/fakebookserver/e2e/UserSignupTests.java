package com.jkhan.fackebook.fakebookserver.e2e;

import com.jkhan.fackebook.fakebookserver.TestUtils;
import com.jkhan.fakebookserver.FakebookServerApplication;
import com.jkhan.fakebookserver.common.CommonResponseBody;
import com.jkhan.fakebookserver.user.UserCreationDto;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;


@SpringBootTest(classes = FakebookServerApplication.class, webEnvironment = WebEnvironment.RANDOM_PORT)
public class UserSignupTests {

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Test
    public void signupUserRequestTest() {
        ResponseEntity<CommonResponseBody> responseEntity = testRestTemplate.postForEntity(
                TestUtils.buildUrl("/api/users"),
                UserCreationDto.builder()
                        .nickname("jkhan")
                        .name("jinkyuhan")
                        .age(27)
                        .email("gkswlsrb95@gmail.com")
                        .password("1234")
                        .build(),
                CommonResponseBody.class
        );
    }
}
