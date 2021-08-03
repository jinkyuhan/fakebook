package com.jkhan.fakebookserver.user;

import java.util.UUID;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "user_account")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
public class UserAccount {
  @Id
  @Column
  @Builder.Default
  private UUID id = UUID.randomUUID();

  @Column(unique = true)
  private String nickname;

  @Column
  private String name;

  @Column
  private String password;

  @Column(unique = true)
  private String email;

  @Column
  private int age;
}
