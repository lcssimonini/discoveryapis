package io.builders.discoveryapis.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateTitleDTO {

    private String title;
    private String director;
    private String cast;
    private String country;
    private Date dateAdded;
    private Integer releaseYear;
    private String rating;
    private String duration;
    private String listedIn;
    private String description;
}
