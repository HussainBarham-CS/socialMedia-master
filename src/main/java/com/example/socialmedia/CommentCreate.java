package com.example.socialmedia;

import com.example.socialmedia.Model.Collection.Comment;
import com.example.socialmedia.Model.Dto.CommentDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CommentCreate {
    @NotBlank(message = "message is required")
    @Size(min = 2,max = 500,message = " ")
    private String message;
    @NotBlank(message = "message is required")
    private UserPreview owner;
    @NotBlank(message = "post id is required")
    private String post;//post_id

    public static CommentCreate toDto(Comment entity){
        return CommentCreate.builder()

                .owner(entity.getOwner())
                .post(entity.getPost())
                .message(entity.getMessage())
                .build();
    }
}
