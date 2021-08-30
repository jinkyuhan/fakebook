package com.jkhan.fakebookserver.post;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.UUID;

public class PostService {

    @Autowired
    public List<Post> getPostsOfUser(UUID userId) {

    }
}
