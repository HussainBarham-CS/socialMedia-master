package com.example.socialmedia.Model.Collection;

import com.example.socialmedia.Model.Dto.PostDto;
import com.example.socialmedia.Model.Dto.TagDto;

import com.example.socialmedia.PostDtoUpdate;
import com.example.socialmedia.UserPreview;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Document(collection = "posts")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Post {

    @Id
    private String id;

    @NotBlank(message = "Post text is required")
    @Size(min = 6, max = 1000, message = "Post text must be between 6 and 1000 characters")
    private String text;

    //private String image;

    private int likes = 0;

    @Size(min = 6, max = 200, message = "Link must be between 6 and 200 characters")
    private String link;

    private List<TagDto> tags;

    private Date publishDate;

    @Valid
    @NotBlank(message = "Post owner is required")
    private UserPreview owner;


    public static Post toEntity(PostDto dto){

        return Post.builder()

                .id(dto.getId())
                .text(dto.getText())
                .tags(dto.getTags())
                .likes(dto.getLikes())
                .link(dto.getLink())
                .publishDate(dto.getPublishDate())
                .owner(dto.getOwner())
                .build();
    }

    public static Post toEntity1(PostDtoUpdate dto){

        return Post.builder()

                .id(dto.getId())
                .text(dto.getText())
                .tags(dto.getTags())
                .likes(dto.getLikes())
                .link(dto.getLink())
                .publishDate(dto.getPublishDate())
                .owner(dto.getOwner())
                .build();
    }


}