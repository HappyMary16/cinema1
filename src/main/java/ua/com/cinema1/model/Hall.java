package ua.com.cinema1.model;

import lombok.*;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Hall extends Entity {

    private String name;
    private Integer width;
    private Integer height;
    private boolean[][] placement;
    private List<Place> places;
}
