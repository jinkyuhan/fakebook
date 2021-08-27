package com.jkhan.fakebookserver.auth;


import com.jkhan.fakebookserver.user.UserAccount;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "login_session")
@NoArgsConstructor
@Getter
public class LoginSession {

    @Id
    @Column(columnDefinition = "BINARY(16)")
    private UUID id = UUID.randomUUID();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_account_id")
    private UserAccount userAccount;

    @Column
    @Setter
    private long expiredAt;

    public LoginSession(UserAccount user) {
        this.userAccount = user;
    }

}
