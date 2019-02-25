package ua.com.cinema1.model;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Studio extends Entity {

    private String studio;

    @Override
    public String toString() {
        return studio;
    }
}
