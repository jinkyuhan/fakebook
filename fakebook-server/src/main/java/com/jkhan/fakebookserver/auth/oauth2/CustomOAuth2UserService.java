package com.jkhan.fakebookserver.auth.oauth2;

import java.util.Arrays;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

@Service
public class CustomOAuth2UserService implements OAuth2UserService<OAuth2UserRequest, OAuth2User> {

  @Override
  public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
    OAuth2UserService<OAuth2UserRequest, OAuth2User> oAuth2UserService = new DefaultOAuth2UserService();
    OAuth2User oAuth2User = oAuth2UserService.loadUser(userRequest);

    String registrationId = userRequest
        .getClientRegistration()
        .getRegistrationId();
    String userNameAttributeKey = userRequest.getClientRegistration()
        .getProviderDetails()
        .getUserInfoEndpoint()
        .getUserNameAttributeName();

    OAuth2UserInfo userInfoFromExternalService = OAuth2UserInfo.of(registrationId, userNameAttributeKey, oAuth2User.getAttributes());
    

    return new DefaultOAuth2User(
        Arrays.asList(new SimpleGrantedAuthority("ROLE_USER")),
            userInfoFromExternalService.getAttributes(),
            userInfoFromExternalService.getUserNameAttributeKey()
    );
  }
  
}
