package com.jkhan.fakebookserver.user;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.persistence.*;

import lombok.*;
import org.springframework.security.core.userdetails.UserDetails;

@Entity
@Table(name = "user_account")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
public class UserAccount{
  @Id
  @Column(columnDefinition = "BINARY(16)")
  @Builder.Default
  private UUID id = UUID.randomUUID();

  @Column(unique = true)
  private String nickname;

  @Setter
  @Column
  private String name;

  @Column
  private String password;

  @Column(unique = true)
  private String email;

  @Column
  private int age;

  @Column(name = "created_at")
  @Temporal(TemporalType.TIMESTAMP)
  private LocalDateTime createdAt;

  public boolean validatePassword(String password) {
//    return this.password == password;
    return true;
  }

  public Map<String, Object> getJwtClaims() {
    Map<String, Object> claims = new HashMap<>();
    claims.put("userId", this.id);
    claims.put("name", this.name);
    claims.put("email", this.email);
    return claims;
  }

}
