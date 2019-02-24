package ua.com.cinema1.model;

public enum Genre {
    ROMANCE_COMEDY(1, "Romance comedy"), SCIENCE_FICTION(2, "Science fiction"), HORROR(3, "Horror"),
    DOCUMENTARY(4, "Documentary"), ANIMATION(5, "Animation"), ACTION(6, "Action"),
    THRILLER(7, "Thriller"), DRAMA(8, "Drama"), COMEDY(9, "Comedy"), ADVENTURE(10, "Adventure"),
    FEATURE(11, "Feature"), SHORT(12, "Short"), CRIME(13, "Crime"), FANTASY(14, "Fantasy"),
    ROMANCE(15, "Romance"), FAMILY(16, "Family"), WAR(17, "War"), MUSICAL(18, "Musical"),
    BIOGRAPHY(19, "Biography"), WESTERN(20, "Western"), POST_APOCALYPTIC(21, "Post-apocalyptic");

    private int id;
    private String genreName;

    Genre(int id, String genreName) {
        this.id = id;
        this.genreName = genreName;
    }

    public int getId() {
        return id;
    }

    public static Genre getById(int id) {
        return Genre.values()[id - 1];
    }

    @Override
    public String toString() {
        return genreName;
    }
}
