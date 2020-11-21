package io.builders.discoveryapis.domain;

import io.builders.discoveryapis.dto.CreateTitleDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Builder
@Data
@Document
@NoArgsConstructor
@AllArgsConstructor
public class Title {

    @Id
    private String id;
    private Type type;
    private String title;
    private String director;
    private String cast;
    private String country;
    private String dateAdded;
    private String releaseYear;
    private String rating;
    private String duration;
    private String listedIn;
    private String description;

    public static Title from(CreateTitleDTO createTitleDTO) {
        return Title.builder()
                .title(createTitleDTO.getTitle())
                .director(createTitleDTO.getDirector())
                .cast(createTitleDTO.getCast())
                .country(createTitleDTO.getCountry())
                .dateAdded(createTitleDTO.getDateAdded())
                .releaseYear(createTitleDTO.getReleaseYear())
                .rating(createTitleDTO.getRating())
                .duration(createTitleDTO.getDuration())
                .listedIn(createTitleDTO.getListedIn())
                .description(createTitleDTO.getDescription())
                .build();
    }
}
