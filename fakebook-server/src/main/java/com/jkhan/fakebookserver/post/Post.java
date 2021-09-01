package com.jkhan.fakebookserver.post;


import com.jkhan.fakebookserver.user.UserAccount;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

@Table(name = "post")
@Entity
@Getter
@Setter
public class Post {

    @Id
    private UUID id;

    @Column(length = 255)
    private String content;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column
    private Date createdAt;

    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column
    private Date updatedAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "writer_id")
    private UserAccount writer;

    public Post() {
        this.id = UUID.randomUUID();
    }
}

