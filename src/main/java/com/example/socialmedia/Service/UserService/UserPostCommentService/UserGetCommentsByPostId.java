package com.example.socialmedia.Service.UserService.UserPostCommentService;

import com.example.socialmedia.CommentCreate;
import com.example.socialmedia.Model.Collection.Comment;
import com.example.socialmedia.Model.Collection.Post;
import com.example.socialmedia.Model.Dto.CommentDto;
import com.example.socialmedia.Model.Repostiroy.CommentRepo;
import com.example.socialmedia.Model.Repostiroy.PostRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserGetCommentsByPostId {
    @Autowired
    private CommentRepo commentRepo;
    @Autowired
    private PostRepo postRepo;


    public List<CommentCreate> get(int page, int size, String sort, String postId) {
        try{
            Optional<Post> check = postRepo.findById(postId);
            if(check.isPresent()){
                Pageable pageable = PageRequest.of(page, size, Sort.by(sort));
                List<Comment> comments = commentRepo.findAll(pageable).stream().filter(e->e.getPost().equals(postId)).collect(Collectors.toList());
                List<CommentCreate> result = comments.stream().map(e->CommentCreate.toDto(e)).collect(Collectors.toList());
                return result;
            }else{throw new RuntimeException("the post id is not define");}
        }catch (Exception e){throw new RuntimeException(e.getMessage());}
    }
}
