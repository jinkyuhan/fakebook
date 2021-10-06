package com.jkhan.fakebookserver.user;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "user_account")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
public class UserAccount {
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

  @Column(name = "created_at", updatable = false)
  @CreationTimestamp
  @Temporal(TemporalType.TIMESTAMP)
  private Date createdAt;

  @Column(name = "updated_at")
  @UpdateTimestamp
  @Temporal(TemporalType.TIMESTAMP)
  private Date updatedAt;

  public Map<String, Object> getJwtClaims() {
    Map<String, Object> claims = new HashMap<>();
    claims.put("userId", this.id);
    claims.put("name", this.name);
    claims.put("email", this.email);
    return claims;
  }

  public void maskPassword() {
    this.password = "-";
  }

}
