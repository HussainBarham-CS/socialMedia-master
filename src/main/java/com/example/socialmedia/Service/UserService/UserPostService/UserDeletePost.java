package com.example.socialmedia.Service.UserService.UserPostService;


import com.example.socialmedia.CurrentUser;
import com.example.socialmedia.Model.Collection.Post;
import com.example.socialmedia.Model.Repostiroy.PostRepo;
import com.example.socialmedia.Security.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserDeletePost extends CurrentUser {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PostRepo postRepo;



    public String deletePost(String postId){
        try{
            Optional<Post> post = postRepo.findById(postId);
            if(post.isPresent()){
                if(post.get().getOwner().getId().equals(getCurrentUser().getId())){
                    postRepo.deleteById(postId);
                    return "Deleted";
                }else {throw new RuntimeException("it is forbidden to delete post to another user");}
            }else{throw new RuntimeException("the postId is not define");}
        } catch (Exception e){throw new RuntimeException(e.getMessage());}
    }





}
