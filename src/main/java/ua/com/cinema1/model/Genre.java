package ua.com.cinema1.model;

public enum Genre {
    ROMANCE_COMEDY(1), SCIENCE_FICTION(2), HORROR(3), DOCUMENTARY(4), ANIMATION(5), ACTION(6),
    THRILLER(7), DRAMA(8), COMEDY(9), ADVENTURE(10), FEATURE(11), SHORT(12), CRIME(13), FANTASY(14),
    ROMANCE(15), FAMILY(16), WAR(17), MUSICAL(18), BIOGRAPHY(19), WESTERN(20), POST_APOCALYPTIC(21);

    private int id;

    Genre(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public static Genre getById(int id) {
        return Genre.values()[id - 1];
    }
}
