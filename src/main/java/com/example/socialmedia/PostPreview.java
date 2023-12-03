package com.example.socialmedia;


import com.example.socialmedia.Model.Collection.Post;
import com.example.socialmedia.Model.Dto.PostDto;
import com.example.socialmedia.Model.Dto.TagDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor@NoArgsConstructor
@Builder
public class PostPreview {
    @NotBlank(message = "id is required")
    private String id;

    @NotBlank(message = "Post text is required")
    @Size(min = 6, max = 1000, message = "Post text must be between 6 and 1000 characters")
    private String text;

    //private String image;

    private int likes = 0;

    private List<TagDto> tags;

    private Date publishDate;

    @Valid
    @NotBlank(message = "Post owner is required")
    private UserPreview owner;


    public static PostPreview toPostPreview(Post entity){
        return PostPreview.builder()

                .id(entity.getId())
                .text(entity.getText())
                .tags(entity.getTags())
                .likes(entity.getLikes())
                .publishDate(entity.getPublishDate())
                .owner(entity.getOwner())
                .build();
    }



}
