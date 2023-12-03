package com.example.socialmedia.Controller.UserController;

import com.example.socialmedia.AppUserUpdate;
import com.example.socialmedia.CurrentUser;
import com.example.socialmedia.Security.*;
import com.example.socialmedia.Service.UserService.*;
import com.example.socialmedia.UserPreview;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@Tag(name = "User Controller")
public class UserController {

    @Autowired
    private UserCreate userCreate;


    @PostMapping(value = "/api/v1/sparx/signup")
    @Operation(
            description = "This api for USER to create a new account",
            summary = "CREATE A NEW ACCOUNT"
    )
    public ResponseEntity<Object> create(@Valid @RequestBody AppUserDto user)
    {
        return new ResponseEntity(userCreate.createUser(user), HttpStatus.CREATED);
    }


    @Autowired
    private UserDelete userDelete;



    @DeleteMapping(value = "/api/v1/sparx/user/{userId}")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    @Operation(
            description = "This api for USER to delete he account",
            summary = "DELETE ACCOUNT"
    )@SecurityRequirement(name = "bearerAuth")
    public ResponseEntity<String> delete(@PathVariable String userId){
        System.out.println(userId);
        return new ResponseEntity<>(userDelete.delete(userId), HttpStatus.OK);
    }

    @Autowired
    private UserById userById;



    @GetMapping(value={"/api/v1/sparx/user/{userId}" })
    @PreAuthorize("hasAuthority('ROLE_USER')")
    @Operation(
            description = "This api for USER to show data for user",
            summary = "GET USER DATA "
    )@SecurityRequirement(name = "bearerAuth")
    public ResponseEntity<AppUserDto> getUserById(@Valid @PathVariable String userId)
    {
        return new ResponseEntity<>(userById.getUserById(userId), HttpStatus.ACCEPTED);
    }

    @Autowired
    private UsersGet usersGet;


    @GetMapping(value="/api/v1/sparx/users")
    @PreAuthorize(value = "hasAuthority('ROLE_USER')")
    @Operation(
            description = "This api for USER to show all users",
            summary = "GET USERS "
    )@SecurityRequirement(name = "bearerAuth")
    public ResponseEntity<List<UserPreview>> getAllUsers(
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "2") int size,
            @RequestParam(value = "sort", defaultValue = "registerDate") String sort
    ) {
        return new ResponseEntity<>(usersGet.getAll(page, size, sort), HttpStatus.OK);
    }

    @Autowired
    private UserUpdate userUpdate;


    @PutMapping(value = "/api/v1/sparx/user/{userId}")
    @Operation(
            description = "This api for USER to update he Data",
            summary = "UPDATE USER DATA "
    )@SecurityRequirement(name = "bearerAuth")
    public ResponseEntity<AppUserDto> update(@PathVariable String userId ,@Valid @RequestBody AppUserUpdate user){
        return new ResponseEntity<>(userUpdate.update(userId , user), HttpStatus.OK);
    }

}
