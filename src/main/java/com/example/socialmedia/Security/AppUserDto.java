package com.example.socialmedia.Security;


import com.example.socialmedia.Model.Collection.Location;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.*;
import java.util.Date;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AppUserDto {


    private String id;

    @Pattern(regexp = "^(mr|ms|mrs|miss|dr|)?$", message = "Title must be one of: mr, ms, mrs, miss, dr")
    private String title;

    @NotBlank(message = "First name is required")
    @Size(min = 2, max = 50, message = "First name must be between 2 and 50 characters")
    private String firstName;

    @NotBlank(message = "Last name is required")
    @Size(min = 2, max = 50, message = "Last name must be between 2 and 50 characters")
    private String lastName;

    @Pattern(regexp = "^(male|female|other)?$", message = "Gender must be one of: male, female, other")
    private String gender;

    @Email(message = "Invalid email address")
    @NotBlank(message = "email is required")
    private String email;


    @NotBlank(message = "password is required")
    private String password;

    @JsonIgnore
    private String role;
    private String dateOfBirth;


    private Date registerDate;

    private String phone;

    //private String picture;

    @Valid
    private Location location;


    public static AppUserDto toDto(AppUser entity){
        return AppUserDto.builder()

                .id(entity.getId())
                .email(entity.getEmail())
                .dateOfBirth(entity.getDateOfBirth())
                .firstName(entity.getFirstName())
                .lastName(entity.getLastName())
                .gender(entity.getGender())
                .location((entity.getLocation()))
                .password(entity.getPassword())
                //.picture(entity.getPicture())
                .title(entity.getTitle())
                .role(entity.getRole())
                .registerDate(entity.getRegisterDate())
                .build();
    }
}