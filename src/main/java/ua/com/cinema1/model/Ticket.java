package ua.com.cinema1.model;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Ticket extends Entity {

    private Seance seance;
    private Place place;
    private User user;
}
