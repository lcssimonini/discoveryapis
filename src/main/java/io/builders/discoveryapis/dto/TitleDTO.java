package io.builders.discoveryapis.dto;

import io.builders.discoveryapis.domain.Title;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TitleDTO {

    @Id
    private String id;
    private String type;
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

    public static TitleDTO from(Title title) {
        return TitleDTO.builder()
                .id(title.getId())
                .title(title.getTitle())
                .type(title.getType().name())
                .director(title.getDirector())
                .cast(title.getCast())
                .country(title.getCountry())
                .dateAdded(title.getDateAdded())
                .releaseYear(title.getReleaseYear())
                .rating(title.getRating())
                .duration(title.getDuration())
                .listedIn(title.getListedIn())
                .description(title.getDescription())
                .build();
    }
}
