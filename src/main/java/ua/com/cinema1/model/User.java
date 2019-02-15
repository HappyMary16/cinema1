package ua.com.cinema1.model;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User extends Entity {

    private String firstName;
    private String lastName;
    private String login;
    private String password;
    private String phone;
    private String email;
    private Role type;
}
