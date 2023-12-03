package com.example.socialmedia;


import com.example.socialmedia.Model.Collection.Post;
import com.example.socialmedia.Model.Collection.Tag;
import com.example.socialmedia.Model.Dto.PostDto;
import com.example.socialmedia.Model.Dto.TagDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PostCreate {
    @NotBlank(message = "id is required")
    private String id;

    @NotBlank(message = "Post text is required")
    @Size(min = 6, max = 1000, message = "Post text must be between 6 and 1000 characters")
    private String text;

    //private String image;

    private int likes = 0;


    @NotBlank(message = "tag is required")
    private List<TagDto> tags;

    @Valid
    @NotBlank(message = "Post owner is required")
    private UserPreview owner;


    public static PostCreate toPostCreate(Post toPostCreate){

        return PostCreate.builder()

                .id(toPostCreate.getId())
                .text(toPostCreate.getText())
                .tags(toPostCreate.getTags())
                .likes(toPostCreate.getLikes())
                .owner(toPostCreate.getOwner())
                .build();
    }
}
