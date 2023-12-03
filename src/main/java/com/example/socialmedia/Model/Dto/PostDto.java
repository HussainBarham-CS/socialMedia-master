package com.example.socialmedia.Model.Dto;

import com.example.socialmedia.Model.Collection.Location;
import com.example.socialmedia.Model.Collection.Post;
import com.example.socialmedia.Model.Collection.Tag;
import com.example.socialmedia.UserPreview;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Data
@AllArgsConstructor@NoArgsConstructor@Builder
public class PostDto {

    private String id;

    @NotBlank(message = "Post text is required")
    @Size(min = 6, max = 1000, message = "Post text must be between 6 and 1000 characters")
    private String text;

    //private String image;

    private int likes = 0;

    @Size(min = 6, max = 200, message = "Link must be between 6 and 200 characters")
    private String link;



    @Valid
    private List<TagDto> tags;

    private Date publishDate;

    @Valid
    private UserPreview owner;


    public static PostDto toDto(Post entity){
        return PostDto.builder()

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
