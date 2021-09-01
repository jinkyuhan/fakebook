package com.jkhan.fakebookserver.post.dto;

import com.jkhan.fakebookserver.post.Post;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class PostListDto {
    private List<PostDto> posts;
}
