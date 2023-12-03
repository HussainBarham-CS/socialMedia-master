package com.example.socialmedia.Service.UserService.UserPostService;


import com.example.socialmedia.Model.Collection.Post;
import com.example.socialmedia.Model.Dto.PostDto;
import com.example.socialmedia.Model.Repostiroy.PostRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserGetPostById {

    @Autowired
    private PostRepo postRepo;


    public PostDto getById(String postId){
        try{
            Optional<Post> check = postRepo.findById(postId);
            if(check.isPresent()){
                return PostDto.toDto(check.get());
            }else{throw new RuntimeException("the post id is not define");}
        }catch(Exception e){throw new RuntimeException(e.getMessage());}
    }
}