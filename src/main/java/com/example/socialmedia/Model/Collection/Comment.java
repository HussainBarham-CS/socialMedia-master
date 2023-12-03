package com.example.socialmedia.Model.Collection;

import com.example.socialmedia.Model.Dto.CommentDto;
import com.example.socialmedia.Model.Dto.LocationDto;
import com.example.socialmedia.UserPreview;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Date;

@Document(collection = "comment")
@Data
@AllArgsConstructor@NoArgsConstructor
@Builder
public class Comment {
    @Id
    private String id;
    @NotBlank(message = "message is required")
    @Size(min = 2,max = 500,message = " ")
    private String message;
    @NotBlank(message = "message is required")
    private UserPreview owner;
    @NotBlank(message = "post id is required")
    private String post;//post_id
    private Date publishDate;


    public static Comment toEntity(CommentDto dto){

        return Comment.builder()

                .id(dto.getId())
                .owner(dto.getOwner())
                .post(dto.getPost())
                .message(dto.getMessage())
                .publishDate(dto.getPublishDate())
                .build();
    }


}
