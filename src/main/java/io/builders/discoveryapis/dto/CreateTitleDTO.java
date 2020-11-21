package io.builders.discoveryapis.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateTitleDTO {

    @NotBlank
    private String title;
    private String director;
    private String cast;
    private String country;
    private String dateAdded;
    private String releaseYear;
    @NotBlank(message = "should have rating")
    private String rating;
    private String duration;
    private String listedIn;
    private String description;
}
