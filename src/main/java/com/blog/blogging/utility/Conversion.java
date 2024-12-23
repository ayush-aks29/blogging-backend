package com.blog.blogging.utility;

import com.blog.blogging.entity.Category;
import com.blog.blogging.entity.Comment;
import com.blog.blogging.entity.Post;
import com.blog.blogging.entity.User;
import com.blog.blogging.payload.CategoryDto;
import com.blog.blogging.payload.CommentDto;
import com.blog.blogging.payload.PostDto;
import com.blog.blogging.payload.UserDto;
import com.blog.blogging.service.implementation.UserServiceImpl;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

public class Conversion {

    @Autowired
    public UserServiceImpl userServiceImpl;

    public ModelMapper modelMapper=new ModelMapper();


    public User dtoToUser(UserDto userDto){
        /*
        User user = new User();
        user.setId(userDto.getId());
        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        user.setAbout(userDto.getAbout());
        return user;
        */

        return modelMapper.map(userDto, User.class);

    }

    public UserDto userToDto(User user){
        return modelMapper.map(user, UserDto.class);
    }

    public Category dtoToCategory(CategoryDto categoryDto){
        return modelMapper.map(categoryDto, Category.class);
    }

    public CategoryDto categoryToDto(Category category){
        return modelMapper.map(category, CategoryDto.class);
    }

    public Post dtoToPost(PostDto postDto){
        return modelMapper.map(postDto, Post.class);
    }

    public PostDto postToDto(Post post){
        return modelMapper.map(post, PostDto.class);
    }

    public CommentDto commentToDto(Comment comment){
        return modelMapper.map(comment, CommentDto.class);
    }

    public Comment dtoToComment(CommentDto commentDto){
        return modelMapper.map(commentDto, Comment.class);
    }

}
