package ua.com.cinema1.model;

import lombok.*;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Seance extends Entity {

    private Film film;
    private Date dateAdnTime;
    private Hall hall;
    private int priceTicket;
}
