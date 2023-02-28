package com.codecool.tvseriesdatabase;


public class Series {
    private String title;
    private int numberOfSeasons;
    private String genre;

    public Series(String title, int numberOfSeasons, String genre) {
        this.title = title;
        this.numberOfSeasons = numberOfSeasons;
        this.genre = genre;
    }

    public String getTitle() {
        return title;
    }

    public int getNumberOfSeasons() {
        return numberOfSeasons;
    }

    public String getGenre() {
        return genre;
    }
}
