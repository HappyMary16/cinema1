package ua.com.cinema1.model;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Director extends Entity {

    private String firstName;
    private String lastName;
}
