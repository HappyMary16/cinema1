package ua.com.cinema1.model;

import lombok.*;

import java.io.File;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Film extends Entity {

    private String title;
    private String describe;
    private Integer minAge;
    private List<Genre> genres;
    private List<Director> directors;
    private List<Actor> actors;
    private List<Studio> studios;
    private List<Country> countries;
    private Integer duration;
    private String language;
    private Date firstSeance;
    private Date lastSeance;
    private File smallPoster;
    private File bigPoster;
    private String trailerLink;

}

