package com.jkhan.fackebook.fakebookserver.e2e;

import com.jkhan.fakebookserver.FakebookServerApplication;
import com.jkhan.fakebookserver.dto.UserCreationDto;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;


@SpringBootTest(classes = FakebookServerApplication.class)
public class UserSignupTest {

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Test
    public void signupUserRequestTest() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

//        UserCreationDto requestBody = new UserCreationDto();
//        requestBody.setNickname("jkhan");
//        requestBody.setName("jinkyuhan");
//        requestBody.setEmail("gkswlsrb95@gmail.com");
//        requestBody.setPassword("password");
//        requestBody.setAge(27);

        ResponseEntity<Void> responseEntity = testRestTemplate.postForEntity(
                "localhost:8080" + "/api/users",
                UserCreationDto.builder()
                        .nickname("jkhan")
                        .name("jinkyuhan")
                        .age(27)
                        .email("gkswlsrb95@gmail.com")
                        .password("1234")
                        .build(),
                null
        );
    }
}
