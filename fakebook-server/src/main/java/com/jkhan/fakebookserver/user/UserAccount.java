package com.jkhan.fakebookserver.user;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Builder;
import lombok.Getter;

@Entity
@Table(name = "user_account")
@Builder
@Getter
public class UserAccount {

  @Id
  @Column
  @Builder.Default
  private UUID id = UUID.randomUUID();

  @Column
  private String username;

  @Column
  private String name;

  @Column
  private String email;

  @Column
  private int age;
}
