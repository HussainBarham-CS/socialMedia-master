package com.example.socialmedia.Model.Dto;

import com.example.socialmedia.Model.Collection.Location;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LocationDto {

    @NotBlank(message = "id is required")
    private String id;

    @NotBlank(message = "Street is required")
    @Size(min = 5, max = 100, message = "Street must be between 5 and 100 characters")
    private String street;

    @NotBlank(message = "City is required")
    @Size(min = 2, max = 30, message = "City must be between 2 and 30 characters")
    private String city;

    @NotBlank(message = "State is required")
    @Size(min = 2, max = 30, message = "State must be between 2 and 30 characters")
    private String state;

    @NotBlank(message = "Country is required")
    @Size(min = 2, max = 30, message = "Country must be between 2 and 30 characters")
    private String country;

    /**
     * [+/-]: Indicates a positive or negative offset.
     * hh: Represents one or two digits for the hours (0-9).
     * :: Separates hours and minutes.
     * mm: Represents exactly two digits for the minutes (0-9).
     * Examples of valid matches include +05:30, -08:00, +02:00, etc., representing different timezone offsets.
     */
    @NotBlank(message = "Timezone is required")
    @Pattern(regexp = "^([+-]\\d{1,2}:\\d{2})$", message = "Invalid timezone format")
    private String timezone;

    @NotBlank(message = "latitude is required")
    private double latitude;

    @NotBlank(message = "longitude is required")
    private double longitude;


    public static LocationDto toDto(Location entity){
        return LocationDto.builder()

                .id(entity.getId())
                .street(entity.getStreet())
                .city(entity.getCity())
                .state(entity.getState())
                .country(entity.getCountry())
                .timezone(entity.getTimezone())
                .latitude(entity.getLatitude())
                .longitude(entity.getLongitude())
                .build();
    }
}
