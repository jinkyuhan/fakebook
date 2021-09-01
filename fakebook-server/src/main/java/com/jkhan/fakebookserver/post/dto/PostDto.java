package com.jkhan.fakebookserver.post.dto;

import com.jkhan.fakebookserver.post.Post;
import com.jkhan.fakebookserver.user.UserAccount;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class PostDto {

    private String id;
    private String content;
    private long createdAt;
    private long updatedAt;
    private UserAccount writer;

    public static PostDto fromEntity(Post p) {
        return new PostDto(
                p.getId().toString(),
                p.getContent(),
                p.getCreatedAt().getTime(),
                p.getUpdatedAt().getTime(),
                p.getWriter()
        );
    }
}
