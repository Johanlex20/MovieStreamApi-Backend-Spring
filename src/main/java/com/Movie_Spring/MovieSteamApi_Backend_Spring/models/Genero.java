package com.Movie_Spring.MovieSteamApi_Backend_Spring.models;

public enum Genero {
    FANTASY("Sci-Fi & Fantasy", "Fantasía"),
    ACTION("Action & Adventure", "Acción"),
    ANIMATION("Animation", "Animación"),
    COMEDY("Comedy", "Comedia"),
    COMEDIA("Comedia", "Comedy"), // Alias para "Comedy" en español
    CRIME("Crimen", "Crime"),
    DOCUMENTARY("Documentary", "Documental"),
    DRAMA("Drama", "Drama"),
    FAMILY("Family", "Familia"),
    KIDS("Kids", "Niños"),
    MYSTERY("Mystery", "Misterio"),
    NEWS("News", "Noticias"),
    REALITY("Reality", "Reality"),
    SOAP("Soap", "Telenovela"),
    TALK("Talk", "Charla"),
    WAR("War & Politics", "Guerra y Política"),
    WESTERN("Western", "Occidental");

    // IMPORTANTE CREAR UNA VAIRABLE Y UN CONSTRUCTOR PARA PASAR LOS VALORES DEL GENERO COMO VIENE EN EL API Y COMO LOS QUIERO TENER EN MI ENUM
    private String[] generoApi;

    Genero(String... generoApi) {
        this.generoApi = generoApi;
    }

    // METODO PARA CONVERTIR LOS GENEROS DE API A VALORES OBJETO JAVA
    public static Genero fromString(String text) throws IllegalAccessException {
        for (Genero genero : Genero.values()) {
            for (String alias : genero.generoApi) {
                if (alias.equalsIgnoreCase(text)) {
                    return genero;
                }
            }
        }
        throw new IllegalAccessException("Ningun Genero encontrado: " + text);
    }

}
