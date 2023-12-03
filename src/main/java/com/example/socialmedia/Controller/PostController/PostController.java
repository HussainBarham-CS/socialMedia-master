package com.example.socialmedia.Controller.PostController;

import com.example.socialmedia.Model.Dto.PostDto;
import com.example.socialmedia.PostDtoUpdate;
import com.example.socialmedia.PostPreview;
import com.example.socialmedia.Service.UserService.UserPostService.*;
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
@SecurityRequirement(name = "bearerAuth")
@Tag(name = "Post Controller")
public class PostController {

    @Autowired
    private UserCreatePost userCreatePost;


    @PostMapping(value = "/api/v1/sparx/user/post/create")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    @Operation(
            description = "This api for USER to create a new post",
            summary = "CREATE NEW POST "
    )
    public ResponseEntity<PostDto> create(@Valid @RequestBody PostDto postDto)
    {
        return new ResponseEntity(userCreatePost.create(postDto), HttpStatus.CREATED);
    }


    @Autowired
    private UserDeletePost userDeletePost;

    @DeleteMapping(value = "/api/v1/sparx/user/post/{postId}")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    @Operation(
            description = "This api for USER to Delete post",
            summary = "DELETE POST "
    )
    public ResponseEntity<String> deletePost(@Valid @PathVariable String postId){
        return new ResponseEntity<>(userDeletePost.deletePost(postId),HttpStatus.OK);
    }

    @Autowired
    private UserGetPostById userGetPostById;


    @GetMapping(value = "/api/v1/sparx/user/post/{postId}")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    @Operation(
            description = "This api for USER to Show post ",
            summary = "SHOW POST DATA"
    )
    public ResponseEntity<PostDto> getPostById(@PathVariable String postId){
        return new ResponseEntity<>(userGetPostById.getById(postId), HttpStatus.OK);
    }

    @Autowired
    private UserGetPosts userGetPosts;


    @GetMapping(value = "/api/v1/sparx/user/post")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    @Operation(
            description = "This api for USER to ALL Posts ",
            summary = "SHOW ALL POSTS"
    )
    public ResponseEntity<List<PostPreview>> getPostsByUserId(
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "2") int size,
            @RequestParam(value = "sort", defaultValue = "registerDate") String sort,
            @RequestParam(value = "friendFirst", defaultValue = "false") Boolean friendFirst
    ){
        return new ResponseEntity<>(userGetPosts.getPosts(page,size,sort,friendFirst), HttpStatus.OK);
    }



    @Autowired
    private UserGetPostByTag userGetPostByTag;



    @GetMapping(value = "/api/v1/sparx/user/tag/{tagId}/post")
    @PreAuthorize(value = "hasAuthority('ROLE_USER')")
    @Operation(
            description = "This api for USER to show all posts contain a tag",
            summary = "SHOW ALL POSTS CONTAIN TAG "
    )
    public ResponseEntity<List<PostPreview>> getPostByTag(
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "1") int size,
            @RequestParam(value = "sort", defaultValue = "registerDate") String sort,
            @PathVariable String tagId
    ) {
        return new ResponseEntity<>(userGetPostByTag.getAllByTag(page, size, sort,tagId), HttpStatus.OK);
    }


    @Autowired
    private UserGetPostsByUserId userGetPostsByUserId;


    @GetMapping(value = "/api/v1/sparx/user/{userId}/post")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    @Operation(
            description = "This api for USER to Show all Posts for user",
            summary = "SHOW ALL POSTS FOR USER"
    )
    public ResponseEntity<List<PostPreview>> getPostsByUserId(
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "1") int size,
            @RequestParam(value = "sort", defaultValue = "registerDate") String sort,
            @PathVariable String userId
    ){
        return new ResponseEntity<>(userGetPostsByUserId.getPostsByUserId(page,size,sort,userId), HttpStatus.OK);
    }


    @Autowired
    private UserUpdatePost userUpdatePost;


    @PutMapping(value = "/api/v1/sparx/user/post/{postId}")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    @Operation(
            description = "This api for USER to Updata data for he post",
            summary = "UPDATE POST DATA"
    )
    public ResponseEntity<PostDtoUpdate> update(@Valid @PathVariable String postId,
                                                @Valid @RequestBody PostDtoUpdate postDtoUpdate) {
        return new ResponseEntity(userUpdatePost.update(postId,postDtoUpdate), HttpStatus.OK);}
}
