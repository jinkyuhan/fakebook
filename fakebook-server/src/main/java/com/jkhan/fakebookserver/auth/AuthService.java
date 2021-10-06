package com.jkhan.fakebookserver.auth;

import java.util.Date;
import java.util.UUID;

import com.jkhan.fakebookserver.auth.dto.AuthTokenBundleDto;
import com.jkhan.fakebookserver.auth.dto.AuthTokenDto;
import com.jkhan.fakebookserver.auth.jwt.JwtProvider;
import com.jkhan.fakebookserver.user.UserAccount;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AuthService {

    @Autowired
    JwtProvider jwtProvider;

    @Autowired
    private LoginSessionRepository loginSessionRepository;

    @Transactional
    public AuthTokenBundleDto issueNewLoginSession(UserAccount user) {
        AuthTokenBundleDto newTokenBundle = new AuthTokenBundleDto();
        newTokenBundle.setAccessToken(jwtProvider.generateAccessToken(user));

        LoginSession newLoginSession = new LoginSession(user);
        AuthTokenDto newRefreshToken = jwtProvider.generateRefreshToken(user, newLoginSession);
        newTokenBundle.setRefreshToken(newRefreshToken);

        newLoginSession.setExpiredAt(new Date(newRefreshToken.getExpiredAt()));
        loginSessionRepository.save(newLoginSession);

        return newTokenBundle;
    }

    @Transactional
    public AuthTokenBundleDto refreshLoginSession(LoginSession oldSession) {
        AuthTokenBundleDto newTokenBundle = issueNewLoginSession(oldSession.getUserAccount());
        loginSessionRepository.delete(oldSession);
        return newTokenBundle;
    }

    public void deleteLoginSession(LoginSession targetSession) {
        loginSessionRepository.delete(targetSession);
    }

    @Transactional
    public void deleteAllLoginSessionsOfUser(UUID userId) {
        loginSessionRepository.deleteByUserAccountId(userId);
    }
}
