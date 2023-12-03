package com.example.socialmedia.Model.Collection;

import com.example.socialmedia.Model.Dto.TagDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotBlank;
import java.util.List;

@Document(collection="tag")
@Data@Builder
@AllArgsConstructor@NoArgsConstructor
public class Tag {
    @Id
    private String id;
    @NotBlank(message = "tag is required")
    private String tag;


    public static Tag toEntity(TagDto dto){
        return Tag.builder()

                .id(dto.getId())
                .tag(dto.getTag())
                .build();
    }
}