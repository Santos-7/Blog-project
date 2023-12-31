package com.blog.repository;

import com.blog.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostReository extends JpaRepository<Post,Long> {
}
