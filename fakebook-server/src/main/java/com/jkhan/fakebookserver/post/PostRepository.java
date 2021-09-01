package com.jkhan.fakebookserver.post;

import com.jkhan.fakebookserver.common.PageCursorVo;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface PostRepository extends JpaRepository<Post, UUID> {

    @Query("SELECT p FROM Post p JOIN FETCH p.writer ORDER BY p.createdAt DESC")
    public List<Post> findAllFetchJoinUser();

    @Query("SELECT p FROM Post p JOIN FETCH p.writer ORDER BY p.createdAt DESC")
    public List<Post> findAllFetchJoinUser(Pageable pageable);

    @Query("SELECT p FROM  Post p " +
            "JOIN FETCH p.writer " +
            "WHERE (p.createdAt = :#{#cursor.dateCursor} AND p.id < :#{#cursor.idCursor}) " +
            "OR p.createdAt < :#{#cursor.dateCursor} " +
            "ORDER BY p.createdAt DESC")
    public List<Post> findAllFetchJoinUser(
            @Param("cursor") PageCursorVo cursor,
            Pageable pageable
    );

    public List<Post> findByWriterId(UUID writerId);

    public List<Post> findByWriterId(UUID writerId, Pageable pageable);

    @Query("SELECT p FROM Post p " +
            "WHERE ((p.createdAt = :#{#cursor.dateCursor} AND p.id < :#{#cursor.idCursor}) " +
            "OR p.createdAt < :#{#cursor.dateCursor}) AND p.writer.id = :writerId " +
            "ORDER BY p.createdAt DESC")
    public List<Post> findByWriterId(
            @Param("writerId") UUID writerId,
            @Param("cursor") PageCursorVo cursor,
            Pageable pageable);


}
