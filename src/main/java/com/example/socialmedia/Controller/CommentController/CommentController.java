package com.example.socialmedia.Controller.CommentController;

import com.example.socialmedia.CommentCreate;
import com.example.socialmedia.Model.Dto.CommentDto;
import com.example.socialmedia.Service.UserService.UserPostCommentService.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/sparx/user")
@SecurityRequirement(name = "bearerAuth")
@Tag(name = "Comment Controller")
public class CommentController {

    @Autowired
    private UserCreateComment userCreateComment;


    @PostMapping(value = "/{postId}/comment/create")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    @Operation(
            description = "This api for USER to create a new comment for a Post",
            summary = "ADD COMMENT"
    )
    public ResponseEntity<CommentDto> create(@Valid @RequestBody CommentDto commentDto,
                                             @PathVariable String postId){
        return new ResponseEntity<CommentDto>(userCreateComment.create(postId,commentDto),
                HttpStatus.CREATED);

    }

    @Autowired
    private UserDeleteComment userDeleteComment;


    @DeleteMapping(value = "/{commentId}/comment/delete")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    @Operation(
            description = "This api for USER to delete a comment from Post",
            summary = "DELETE COMMENT"
    )
    public ResponseEntity<String> delete(@PathVariable String commentId){
        return new ResponseEntity<String>(userDeleteComment.delete(commentId),
                HttpStatus.OK);
    }

    @Autowired
    private UserGetComments userGetComments;


    @GetMapping(value = "/comment")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    @Operation(
            description = "This api for USER to show all comments",
            summary = "GET COMMENTS"
    )
    public ResponseEntity<List<CommentDto>> getAllComments(
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "3") int size,
            @RequestParam(value = "sort", defaultValue = "registerDate") String sort
    ) {
        return new ResponseEntity<List<CommentDto>>((List<CommentDto>) userGetComments.get(page, size, sort), HttpStatus.OK);
    }



    @Autowired
    private UserGetCommentsByPostId userGetCommentsByPostId;


    @GetMapping(value = "/post/{postId}/comment")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    @Operation(
            description = "This api for USER to show all comments on a post",
            summary = "GET COMMENTS ON A POST"
    )
    public ResponseEntity<List<CommentCreate>> getAllCommentsByPostId(
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "3") int size,
            @RequestParam(value = "sort", defaultValue = "registerDate") String sort,
            @PathVariable String postId
    ) {
        return new ResponseEntity<>(userGetCommentsByPostId.get(page, size, sort,postId), HttpStatus.OK);
    }


    @Autowired
    private UserGetCommentsByUserId userGetCommentsByUserId;


    @GetMapping(value = "/{userId}/comment")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    @Operation(
            description = "This api for USER to show he comments ",
            summary = "GET YOUR COMMENTS "
    )
    public ResponseEntity<List<CommentCreate>> getAllCommentsByUserId(
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "3") int size,
            @RequestParam(value = "sort", defaultValue = "registerDate") String sort,
            @PathVariable String userId
    ) {
        return new ResponseEntity<>(userGetCommentsByUserId.get(page, size, sort,userId), HttpStatus.OK);
    }

}
