package com.example.socialmedia.Service.UserService.UserPostCommentService;


import com.example.socialmedia.CurrentUser;
import com.example.socialmedia.Model.Collection.Comment;
import com.example.socialmedia.Model.Collection.Post;
import com.example.socialmedia.Model.Dto.CommentDto;
import com.example.socialmedia.Model.Repostiroy.CommentRepo;
import com.example.socialmedia.Model.Repostiroy.PostRepo;
import com.example.socialmedia.UserPreview;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class UserCreateComment extends CurrentUser {


    @Autowired
    private CommentRepo commentRepo;
    @Autowired
    private PostRepo postRepo;


    public CommentDto create(String postId, CommentDto commentDto) {
        try{
            Optional<Post> check = postRepo.findById(postId);
            if(check.isPresent()){
                if(commentDto.getOwner()==null&&commentDto.getId()==null&&commentDto.getPublishDate()==null
                        &&commentDto.getPost()==null){
                    commentDto.setPublishDate(new Date());
                    commentDto.setOwner(UserPreview.toUserPreview(getCurrentUser()));
                    commentDto.setPost(postId);
                    return CommentDto.toDto(commentRepo.save(Comment.toEntity(commentDto)));
                }else{throw new RuntimeException("sorry but you can not send any thing with body either massege");}
            }else{throw new RuntimeException("the post id is not define");}
        }catch (Exception e){throw new RuntimeException(e.getMessage());}
    }
}
