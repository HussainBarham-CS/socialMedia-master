package com.example.socialmedia;


import com.example.socialmedia.Security.AppUser;
import com.example.socialmedia.Security.AppUserDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data@AllArgsConstructor@NoArgsConstructor
@Builder
public class UserPreview {
    private String id;
    @Pattern(regexp = "^(mr|ms|mrs|miss|dr)?$", message = "Title must be one of: mr, ms, mrs, miss, dr")
    private String title;
    @Size(min = 2,max = 50, message = "Last name must be between 2 and 50 characters")
    private String firstName;
    @Size(min = 2,max = 50, message = "Last name must be between 2 and 50 characters")
    private String lastName;



    public static UserPreview toUserPreview(AppUser entity){
        return UserPreview.builder()

                .id(entity.getId())
                .title(entity.getTitle())
                .firstName(entity.getFirstName())
                .lastName(entity.getLastName())
                .build();
    }
}
