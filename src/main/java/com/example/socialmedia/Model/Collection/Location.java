package com.example.socialmedia.Model.Collection;

import com.example.socialmedia.Model.Dto.LocationDto;
import com.example.socialmedia.Security.AppUser;
import com.example.socialmedia.Security.AppUserDto;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;


@Document(collection = "location")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Location {
    @Id
    @JsonIgnore
    private String id;

    @Size(min = 5, max = 100, message = "Street must be between 5 and 100 characters")
    private String street;

    @Size(min = 2, max = 30, message = "City must be between 2 and 30 characters")
    private String city;

    @Size(min = 2, max = 30, message = "State must be between 2 and 30 characters")
    private String state;

    @Size(min = 2, max = 30, message = "Country must be between 2 and 30 characters")
    private String country;

    /**
     * [+/-]: Indicates a positive or negative offset.
     * hh: Represents one or two digits for the hours (0-9).
     * :: Separates hours and minutes.
     * mm: Represents exactly two digits for the minutes (0-9).
     * Examples of valid matches include +05:30, -08:00, +02:00, etc., representing different timezone offsets.
     */
    @Pattern(regexp = "^([+-]\\d{1,2}:\\d{2})$", message = "Invalid timezone format")
    private String timezone;

    private double latitude;

    private double longitude;

    public static Location toEntity(LocationDto dto){

        return Location.builder()

                .id(dto.getId())
                .street(dto.getStreet())
                .city(dto.getCity())
                .state(dto.getState())
                .country(dto.getCountry())
                .timezone(dto.getTimezone())
                .latitude(dto.getLatitude())
                .longitude(dto.getLongitude())
                .build();
    }
}
