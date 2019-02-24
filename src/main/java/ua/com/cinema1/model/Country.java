package ua.com.cinema1.model;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Country extends Entity {

    private String country;

    @Override
    public String toString() {
        return country;
    }
}
