package com.blog.blogging.service.implementation;

import com.blog.blogging.entity.Category;
import com.blog.blogging.entity.Post;
import com.blog.blogging.entity.User;
import com.blog.blogging.exception.ResourceNotFoundException;
import com.blog.blogging.payload.PostDto;
import com.blog.blogging.repository.CategoryRepository;
import com.blog.blogging.repository.PostRepository;
import com.blog.blogging.repository.UserRepository;
import com.blog.blogging.service.PostService;
import com.blog.blogging.utility.Conversion;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    private Conversion convert=new Conversion();

    @Override
    public PostDto createPost(PostDto postDto, Integer userId, Integer categoryId) {

        User user = this.userRepository.findById(userId).
                orElseThrow(()->new ResourceNotFoundException("User", "User id", userId));

        Category category = this.categoryRepository.findById(categoryId).
                orElseThrow(()-> new ResourceNotFoundException("Category", "Category id", categoryId));

        Post post = convert.dtoToPost(postDto);
        post.setImageName("image.jpg");
        post.setAddedDate(new Date());
        post.setUser(user);
        post.setCategory(category);
        Post newPost = this.postRepository.save(post);
        return convert.postToDto(newPost);
    }

    @Override
    public List<PostDto> getAllPosts() {
        List<Post> posts = this.postRepository.findAll();
        List<PostDto> postDtoList = posts.stream().map(post->
                        convert.postToDto(post))
                .collect(Collectors.toList());
        return postDtoList;
    }

    @Override
    public PostDto getPostById(Integer postId) {

        Post post = this.postRepository.findById(postId)
                .orElseThrow(()->
                        new ResourceNotFoundException("Post", "Post id", postId));
        return convert.postToDto(post);
    }

    @Override
    public PostDto updatePost(PostDto postDto, Integer postId) {

        Post post = postRepository.findById(postId).
                orElseThrow(() ->
                        new ResourceNotFoundException("Post","Id", postId));
        post.setTitle(postDto.getTitle());
        post.setContent(postDto.getContent());
        post.setImageName(postDto.getImageName());
        Post updatedPost = this.postRepository.save(post);
        return convert.postToDto(updatedPost);

    }

    @Override
    public void deletePost(Integer postId) {

        Post post = this.postRepository.findById(postId)
                .orElseThrow(()->
                        new ResourceNotFoundException("Post","Id", postId));
        this.postRepository.delete(post);

    }



    @Override
    public List<PostDto> getAllPostByCategory(Integer categoryId) {
        Category category = this.categoryRepository.findById(categoryId)
                .orElseThrow(()->new ResourceNotFoundException("Category", "category id", categoryId));
        List<Post> posts = this.postRepository.findByCategory(category);
        List<PostDto> postDtos = posts.stream().map(
                (post-> convert.postToDto(post)))
                        .collect(Collectors.toList());
        return postDtos;
    }

    @Override
    public List<PostDto> getAllPostByUser(Integer userId) {
        User user = this.userRepository.findById(userId)
                .orElseThrow(()->new ResourceNotFoundException("User", "user id", userId));
        List<Post> posts = this.postRepository.findByUser(user);
        List<PostDto> postDtos = posts.stream().map(
                (post -> convert.postToDto(post)))
                .collect(Collectors.toList());
        return postDtos;
    }
}
