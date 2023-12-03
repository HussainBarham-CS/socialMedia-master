package com.example.socialmedia.Model.Dto;

import com.example.socialmedia.Model.Collection.Comment;
import com.example.socialmedia.Model.Collection.Location;
import com.example.socialmedia.UserPreview;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Date;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CommentDto {

    private String id;
    @Size(min = 2,max = 500,message = " ")
    private String message;
    private UserPreview owner;
    private String post;//post_id
    private Date publishDate;


    public static CommentDto toDto(Comment entity){
        return CommentDto.builder()

                .id(entity.getId())
                .owner(entity.getOwner())
                .post(entity.getPost())
                .message(entity.getMessage())
                .publishDate(entity.getPublishDate())
                .build();
    }



}
