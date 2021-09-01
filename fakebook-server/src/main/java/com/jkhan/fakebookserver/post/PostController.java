package com.jkhan.fakebookserver.post;

import com.jkhan.fakebookserver.common.CommonResponseBody;
import com.jkhan.fakebookserver.common.PageCursorVo;
import com.jkhan.fakebookserver.common.constant.ApiResult;
import com.jkhan.fakebookserver.common.exception.InvalidInputException;
import com.jkhan.fakebookserver.post.dto.NewPostDto;
import com.jkhan.fakebookserver.post.dto.PostDto;
import com.jkhan.fakebookserver.post.dto.PostListDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/posts")
public class PostController {

    @Autowired
    private PostService postService;

    @GetMapping()
    public CommonResponseBody<PostListDto> getPosts(
            @RequestParam(name = "idCursor", required = false) String idCursor,
            @RequestParam(name = "timeCursor", required = false) Long timeCursor,
            @RequestParam(name = "limit", required = false) Integer limit
    ) {
        PostListDto responseData = new PostListDto();
        List<Post> queryResult;
        if (limit == null) {
            queryResult = postService.getAllPosts();
        } else {
            if (idCursor == null && timeCursor == null) {
                // only limit
                queryResult = postService.getFirstPageOfAllPosts(limit);
            } else if (idCursor != null && timeCursor != null) {
                queryResult =
                        postService.getAllPostsWithCursor(
                                limit,
                                PageCursorVo.of(UUID.fromString(idCursor), timeCursor));
            } else {
                throw new InvalidInputException("Request Param[idCursor, timeCursor] is required", "");
            }
        }

        responseData.setPosts(queryResult.stream()
                .map(PostDto::fromEntity)
                .collect(Collectors.toList()));

        return CommonResponseBody.<PostListDto>builder()
                .data(responseData)
                .result(ApiResult.SUCCESS)
                .build();
    }

    @GetMapping("/mine")
    @Cacheable(value = "MY_POST", key = "#authentication.principal")
    public CommonResponseBody<PostListDto> getMyPosts(
            Authentication authentication,
            @RequestParam(name = "idCursor", required = false) String idCursor,
            @RequestParam(name = "timeCursor", required = false) Long timeCursor,
            @RequestParam(name = "limit", required = false) Integer limit
    ) {
        UUID userId = UUID.fromString(String.valueOf(authentication.getPrincipal()));
        PostListDto responseData = new PostListDto();
        List<Post> queryResult;
        if (limit == null) {
            queryResult = postService.getAllPostsOfUser(userId);
        } else {
            if (idCursor == null && timeCursor == null) {
                // only limit
                queryResult = new ArrayList<Post>();
            } else if (idCursor != null && timeCursor != null) {
                queryResult = new ArrayList<Post>();
            } else {
                throw new InvalidInputException("Request Param[idCursor, timeCursor] is required", "");
            }
        }

        responseData.setPosts(queryResult.stream()
                .map(PostDto::fromEntity)
                .collect(Collectors.toList()));

        return CommonResponseBody.<PostListDto>builder()
                .data(responseData)
                .result(ApiResult.SUCCESS)
                .build();
    }


    @PostMapping()
    public CommonResponseBody<Void> addMyPost(@RequestBody NewPostDto newPost, Authentication authentication) {
        UUID userId = UUID.fromString(String.valueOf(authentication.getPrincipal()));
        postService.addNewPost(newPost, userId);
        return CommonResponseBody.<Void>builder()
                .result(ApiResult.SUCCESS)
                .build();
    }
}
