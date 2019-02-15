package ua.com.cinema1.model;

import lombok.*;

import java.io.File;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Cinema extends Entity {

    private String name;
    private String describe;
    private String address;
    private List<String> phoneNumbers;
    private List<String> emails;
    private List<File> photos;
    private Double coordinateX;
    private Double coordinateY;

}
