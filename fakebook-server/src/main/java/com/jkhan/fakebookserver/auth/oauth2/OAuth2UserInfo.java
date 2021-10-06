package com.jkhan.fakebookserver.auth.oauth2;

import java.util.Map;

import lombok.Getter;

public class OAuth2UserInfo {

  @Getter
  private Map<String, Object> attributes;
  @Getter
  private String userNameAttributeKey;
  private String name;
  private String email;
  
  
  public static OAuth2UserInfo of(String registrationId, String userNameAttributeKey, Map<String, Object> attributes) {
    switch(registrationId) {
      case "kakao":
        return ofKakao(userNameAttributeKey, attributes);
      default:
        return null;
    }
  }

  private static OAUth2UserInfo ofKakao(String userNameAttributeKey, Map<String, Object> attributes) {
    return null;
  }

}
