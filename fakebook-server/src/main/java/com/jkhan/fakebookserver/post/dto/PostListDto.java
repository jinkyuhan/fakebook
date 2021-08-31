package com.jkhan.fakebookserver.post.dto;

import com.jkhan.fakebookserver.post.Post;
import lombok.Setter;

import java.util.List;

@Setter
public class PostListDto {

    private List<Post> posts;
}
