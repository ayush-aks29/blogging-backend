package com.blog.blogging.service;

import com.blog.blogging.payload.PostDto;

import java.util.List;

public interface PostService {

    //create post
    PostDto createPost(PostDto postDto, Integer userId, Integer categoryId);



    //update post
    PostDto updatePost(PostDto postDto, Integer postId);

    //delete Post
    void deletePost(Integer postId);

    //get all posts
    List<PostDto> getAllPosts();

    //get post by id
    PostDto getPostById(Integer postId);

    //get all post by category
    List<PostDto> getAllPostByCategory(Integer categoryId);

    //get all post by user id
    List<PostDto> getAllPostByUser(Integer userId);


}
