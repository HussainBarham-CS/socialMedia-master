package com.example.socialmedia.Model.Dto;


import com.example.socialmedia.Model.Collection.Location;
import com.example.socialmedia.Model.Collection.Tag;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.index.Indexed;

import javax.validation.constraints.NotBlank;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TagDto {

    private String id;
    @NotBlank(message = "tag is required")
    private String tag;

    public static TagDto toDto(Tag entity){
        return TagDto.builder()

                .id(entity.getId())
                .tag(entity.getTag())
                .build();
    }
}
