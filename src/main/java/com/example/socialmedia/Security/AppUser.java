package com.example.socialmedia.Security;



import com.example.socialmedia.AppUserUpdate;
import com.example.socialmedia.Model.Collection.Location;
import com.example.socialmedia.Model.Dto.LocationDto;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.data.mongodb.core.index.Indexed;
import javax.validation.Valid;
import javax.validation.constraints.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@Document(collection = "users")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AppUser implements UserDetails{
    @Id
    @JsonIgnore
    private String id;

    @Pattern(regexp = "^(mr|ms|mrs|miss|dr)?$", message = "Title must be one of: mr, ms, mrs, miss, dr")
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
    @Indexed(unique = true)
    @NotBlank(message = "email is required")
    private String email;

    @JsonIgnore
    @NotBlank(message = "password is required")
    private String password;

    @NotBlank(message = "role is required")
    @JsonIgnore
    private String role;

    private String dateOfBirth;

    private Date registerDate;

    private String phone;

    //private String picture;

    @Valid
    private Location location;


    public static AppUser toEntity(AppUserDto dto){

        return AppUser.builder()

                .id(dto.getId())
                .email(dto.getEmail())
                .dateOfBirth(dto.getDateOfBirth())
                .firstName(dto.getFirstName())
                .lastName(dto.getLastName())
                .gender(dto.getGender())
                .location((dto.getLocation()))
                .password(dto.getPassword())
                //.picture(dto.getPicture())
                .title(dto.getTitle())
                .role(dto.getRole())
                .registerDate(dto.getRegisterDate())
                .build();
    }
    public static AppUser toEntity1(AppUserUpdate dto){

        return AppUser.builder()

                .id(dto.getId())
                .email(dto.getEmail())
                .dateOfBirth(dto.getDateOfBirth())
                .firstName(dto.getFirstName())
                .lastName(dto.getLastName())
                .gender(dto.getGender())
                .location((dto.getLocation()))
                .password(dto.getPassword())
                //.picture(dto.getPicture())
                .title(dto.getTitle())
                .role(dto.getRole())
                .registerDate(dto.getRegisterDate())
                .build();
    }






    @Override
    @Bean
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = new ArrayList<>();

        // Assuming roles are stored as a comma-separated string
        String roles = this.getRole(); // Replace this with your actual method to get roles

        if (roles != null && !roles.isEmpty()) {
            String[] roleArray = roles.split(",");

            for (String role : roleArray) {
                authorities.add(new SimpleGrantedAuthority("ROLE_" + role.trim()));
            }
        }
        System.out.println(authorities);

        return authorities;
    }



    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }






}