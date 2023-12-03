package com.example.socialmedia.Service.UserService.UserPostCommentService;

import com.example.socialmedia.CurrentUser;
import com.example.socialmedia.Model.Collection.Comment;
import com.example.socialmedia.Model.Collection.Post;
import com.example.socialmedia.Model.Dto.CommentDto;
import com.example.socialmedia.Model.Repostiroy.CommentRepo;
import com.example.socialmedia.Model.Repostiroy.PostRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserDeleteComment extends CurrentUser {

    @Autowired
    private CommentRepo commentRepo;
    @Autowired
    private PostRepo postRepo;


    public String delete(String commentId) {
        try{
            Optional<Comment> check = commentRepo.findById(commentId);
            if(check.isPresent()){
                Optional<Post> post = postRepo.findById(check.get().getPost());
                if(getCurrentUser().getId().equals(post.get().getOwner().getId())
                        ||getCurrentUser().getId().equals(check.get().getOwner().getId())){
                    commentRepo.deleteById(commentId);
                    return "Deleted";
                }else{
                    System.out.println(getCurrentUser());
                    throw new RuntimeException("it is forbidden to delete this comment");}
            }else{throw new RuntimeException("sorry but the comment id is not define");}
        }catch (Exception e){throw new RuntimeException(e.getMessage());}
    }
}
