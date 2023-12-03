package com.example.socialmedia.Controller.FriendsController;

import com.example.socialmedia.Service.UserService.UserFriendsService.UserAcceptRequest;
import com.example.socialmedia.Service.UserService.UserFriendsService.UserDeclineRequest;
import com.example.socialmedia.Service.UserService.UserFriendsService.UserRemoveFriend;
import com.example.socialmedia.Service.UserService.UserFriendsService.UserSendRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/v1/sparx/user")
@SecurityRequirement(name = "bearerAuth")
@Tag(name = "Friends Controller")
public class FriendController {



    @Autowired
    private UserAcceptRequest userAcceptRequest;

    @PostMapping(value = "/acceptRequest/{requestId}")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    @Operation(
            description = "This api for USER to accept a new friend request ",
            summary = "ACCEPT FRIEND REQUEST"
    )
    public ResponseEntity<String> acceptRequest(@PathVariable String requestId){
        return new ResponseEntity(userAcceptRequest.accept(requestId), HttpStatus.OK);
    }


    @Autowired
    private UserDeclineRequest userDeclineRequest;

    @PostMapping(value = "/declineRequest/{requestId}")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    @Operation(
            description = "This api for USER to decline a friend request ",
            summary = "DECLINE FRIEND REQUEST"
    )
    public ResponseEntity<String> declineRequest(@PathVariable String requestId){
        return new ResponseEntity(userDeclineRequest.decline(requestId), HttpStatus.OK);
    }



    @Autowired
    private UserRemoveFriend userRemoveFriend;

    @DeleteMapping(value = "/removeFriend/{userId}")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    @Operation(
            description = "This api for USER to DELETE a friend from friend list ",
            summary = "DELETE FRIEND"
    )
    public ResponseEntity<String> deleteFriend(@PathVariable String userId){
        return new ResponseEntity(userRemoveFriend.remove(userId), HttpStatus.OK);
    }


    @Autowired
    private UserSendRequest userSendRequest;

    @PostMapping(value = "/sendRequest/{userId}")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    @Operation(
            description = "This api for USER to SEND a new friend request ",
            summary = "SEND FRIEND REQUEST"
    )
    public ResponseEntity<String> sendRequest(@PathVariable String userId){
        return new ResponseEntity(userSendRequest.send(userId),HttpStatus.OK);
    }


}
