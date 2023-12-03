package com.example.socialmedia;

import com.example.socialmedia.Model.Collection.Post;
import com.example.socialmedia.Model.Dto.TagDto;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PostDtoUpdate {


    private String id;


    @Size(min = 6, max = 1000, message = "Post text must be between 6 and 1000 characters")
    private String text;

    //private String image;

    private int likes = 0;

    @Size(min = 6, max = 200, message = "Link must be between 6 and 200 characters")
    private String link;


    private List<TagDto> tags;

    private Date publishDate;

    @Valid
    private UserPreview owner;


    public static PostDtoUpdate toDto(Post entity){
        return PostDtoUpdate.builder()

                .id(entity.getId())
                .text(entity.getText())
                .tags(entity.getTags())
                .likes(entity.getLikes())
                .link(entity.getLink())
                .publishDate(entity.getPublishDate())
                .owner(entity.getOwner())
                .build();
    }
}
