package com.blog.service;

import com.blog.payload.PostDto;

import java.util.List;

public interface PostService {


   void deletePost(long id);

    public PostDto createPost(PostDto postDto);

    List<PostDto> getAllPost(int pageNo, int pageSize, String sortBy, String sortDis);


    PostDto updatePost(long postId, PostDto postDto);
}
