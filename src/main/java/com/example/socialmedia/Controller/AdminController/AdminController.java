package com.example.socialmedia.Controller.AdminController;

import com.example.socialmedia.Model.Dto.TagDto;
import com.example.socialmedia.Service.AdminService.Tags.TagCreate;
import com.example.socialmedia.Service.AdminService.Tags.TagDelete;
import com.example.socialmedia.Service.AdminService.Tags.TagGet;

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
@RequestMapping(value = "/api/v1/sparx")
@SecurityRequirement(name = "bearerAuth")
@Tag(name = "Admin Tag Controller")
public class AdminController {

    @Autowired
    private TagCreate tagCreate;


    @PostMapping(value = "/admin/createTag")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @SecurityRequirement(name = "bearerAuth")
    @Operation(
            description = "This api for ADMIN to create a new tag on database",
            summary = "ADD TAG"
    )
    public ResponseEntity<TagDto> createTag(@Valid @RequestBody TagDto tagDto){
        return new ResponseEntity<>(tagCreate.createTag(tagDto), HttpStatus.CREATED);
    }

    @Autowired
    private TagDelete tagDelete;


    @DeleteMapping(value = "/admin/deleteTag/{tagId}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @SecurityRequirement(name = "bearerAuth")
    @Operation(
            description = "This api for ADMIN to delete a tag from database",
            summary = "DELETE TAG"
    )
    public ResponseEntity<String> deleteTag(@Valid @PathVariable String tagId){
        return new ResponseEntity<>(tagDelete.deleteTag(tagId), HttpStatus.OK);
    }

    @Autowired
    private TagGet tagGet;


    @GetMapping(value = "/tag")
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_USER')")
    @Operation(
            description = "This api for ADMIN OR USER to show tags from database",
            summary = "GET TAGS"
    )
    public ResponseEntity<List<String>> getTags(){
        return new ResponseEntity<>(tagGet.getTags(), HttpStatus.OK);
    }




}
