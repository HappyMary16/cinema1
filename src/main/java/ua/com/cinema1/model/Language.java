package ua.com.cinema1.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Language extends Entity {
    private String language;

    @Override
    public String toString() {
        return language;
    }
}


