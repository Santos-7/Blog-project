package com.blog.service.Impl;

import com.blog.entity.Post;
import com.blog.exception.ResourceNotFoundException;
import com.blog.payload.PostDto;
import com.blog.repository.PostReository;
import com.blog.service.PostService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {
    private PostReository postReo;

    public PostServiceImpl(PostReository postReo) {
        this.postReo = postReo;
    }

    @Override
    public void deletePost(long id) {
       Post post= postReo.findById(id).orElseThrow(
        ()->new ResourceNotFoundException("post not found with id:"+id)
        );
       postReo.deleteById(id);
    }

    @Override
    public PostDto createPost(PostDto postDto) {
        Post post=new Post();
        post.setTitle(postDto.getTitle());
        post.setDescription(postDto.getDescription());
        post.setContent(postDto.getContent());
        Post savedPost = postReo.save(post);
        PostDto dto=new PostDto();
        dto.setId(savedPost.getId());
        dto.setTitle(savedPost.getTitle());
        dto.setDescription(savedPost.getDescription());
        dto.setContent(savedPost.getContent());

        return dto;
    }

    @Override
    public List<PostDto> getAllPost(int pageNo, int pageSize, String sortBy, String sortDis) {
        Sort sort=(sortDis.equalsIgnoreCase("asc"))?Sort.by(sortBy).ascending():Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);
        Page<Post> pagePost = postReo.findAll(pageable);
        List<Post> posts = pagePost.getContent();
        List<PostDto> dtos = posts.stream().map(p -> mapToDto(p)).collect(Collectors.toList());
        return dtos;
    }

    @Override
    public PostDto updatePost(long postId, PostDto postDto) {
       Post post= postReo.findById(postId).orElseThrow(
                ()-> new ResourceNotFoundException("Post not found with id"+postId)
        );
       post.setTitle(postDto.getTitle());
       post.setContent(postDto.getContent());
       post.setDescription(postDto.getDescription());
        Post savedPost = postReo.save(post);
        PostDto dto = mapToDto(savedPost);
        return dto;
    }


    PostDto  mapToDto(Post post){
      PostDto dto=new PostDto();
      dto.setId(post.getId());
      dto.setTitle(post.getTitle());
      dto.setDescription(post.getDescription());
      dto.setContent(post.getContent());
      return dto;
}

}
