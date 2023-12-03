package com.example.socialmedia.Service.UserService.UserPostCommentService;


import com.example.socialmedia.Model.Collection.Comment;
import com.example.socialmedia.Model.Collection.Post;
import com.example.socialmedia.Model.Dto.CommentDto;
import com.example.socialmedia.Model.Repostiroy.CommentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserGetComments {

    @Autowired
    private CommentRepo commentRepo;


    public Object get(int page, int size, String sort) {
        try{
            Pageable pageable = PageRequest.of(page, size, Sort.by(sort));
            Page<Comment> comments = commentRepo.findAll(pageable);
            List<CommentDto> result = comments.stream().map(e->CommentDto.toDto(e)).collect(Collectors.toList());
            return result;
        }catch (Exception e){throw new RuntimeException(e.getMessage());}
    }
}
