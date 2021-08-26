package com.jkhan.fackebook.fakebookserver;

import com.jkhan.fakebookserver.FakebookServerApplication;
import com.jkhan.fakebookserver.auth.LoginSession;
import com.jkhan.fakebookserver.auth.LoginSessionRepository;
import com.jkhan.fakebookserver.auth.AuthConfigConstant;

import com.jkhan.fakebookserver.user.UserAccount;
import com.jkhan.fakebookserver.user.UserAccountRepository;
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
    private UserAccountRepository userAccountRepository;

    @Test
    @Transactional
    void contextLoads() {
        List<LoginSession> sessions = sessionRepository.findAll();
        UserAccount user = sessions.get(0).getUserAccount();
        String email = user.getEmail();
    }

    @Test
    void testRepository() {
        UserAccount account = userAccountRepository.findByEmail("gkswlsrb95@gmail.com").orElseThrow(() -> new RuntimeException(""));
        account.setName("testJinkyu");
        userAccountRepository.save(account);
    }

}
