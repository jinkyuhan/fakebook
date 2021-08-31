package com.jkhan.fakebookserver.post;

import com.jkhan.fakebookserver.common.PageCursorVo;
import com.jkhan.fakebookserver.common.exception.ResourceNotFoundException;
import com.jkhan.fakebookserver.post.dto.NewPostDto;
import com.jkhan.fakebookserver.user.UserAccount;
import com.jkhan.fakebookserver.user.UserAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;


@Service
public class PostService {


    @Autowired
    private UserAccountRepository userAccountRepository;

    @Autowired
    private PostRepository postRepository;

    public List<Post> getAllPostsOfUser(UUID userId) {
        return postRepository.findByWriter(userId);
    }

    public List<Post> getFirstPagePostsOfUser(UUID userId, int limit) {
        return postRepository.findByWriter(userId);
    }

    public List<Post> getPostsOfUser(UUID userId, int limit, PageCursorVo cursor) {
        return postRepository.findByWriter(userId);
    }

    public void addNewPost(NewPostDto post, UUID writerId) {
        Post newPost = new Post();
        UserAccount writer = userAccountRepository.findById(writerId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Writer id is wrong, request from unknown",
                        ""
                ));
        newPost.setContent(post.getContent());
        newPost.setWriter(writer);
        postRepository.save(newPost);
    }
}
