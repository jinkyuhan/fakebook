package com.jkhan.fakebookserver.post;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface PostRepository extends JpaRepository<Post, UUID> {

    public List<Post> findByWriter(UUID writerId);
}