package com.jkhan.fakebookserver.post.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Setter
@Getter
public class PostListDto implements Serializable {
    private List<PostDto> posts;
}
