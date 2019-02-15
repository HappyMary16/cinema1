package ua.com.cinema1.model;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Place extends Entity {

    private int row;
    private int number;
}
