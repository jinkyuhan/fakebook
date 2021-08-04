package com.jkhan.fakebookserver.user;


import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "user_login_session")
@NoArgsConstructor
@Getter
public class UserLoginSession {

    @Id
    private String id;

//    @Column(name = "user_account_id")
//    @OneToMany()
//    private UserAccount userAccount;

    public UserLoginSession(UserAccount user) {
        // TODO: this.user = user, after OneToMany Setting done.
    };

}
