package io.builders.discoveryapis.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

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
    private Date dateAdded;
    private Integer releaseYear;
    private String rating;
    private String duration;
    private String listedIn;
    private String description;
}
