package net.vidalibarraquer.pablosanjose_examen;

public class Torneig {

    private String tourney_name;
    private String tourney_location;
    private String logo;
    private String key;

    public Torneig(String tourney_name, String tourney_location, String key) {
        this.tourney_name = tourney_name;
        this.tourney_location = tourney_location;
        this.key = key;
    }

    public String gettourney_name() {
        return tourney_name;
    }

    public void settourney_name(String tourney_name) {
        this.tourney_name = tourney_name;
    }

    public String gettourney_location() {
        return tourney_location;
    }

    public void settourney_location(String tourney_location) {this.tourney_location = tourney_location; }

    public String getlogo() {
        return logo;
    }

    public void setlogo(String logo) {
        this.logo = logo;
    }

    public String getkey() {
        return key;
    }

    public void setkey(String key) {
        this.key = key;
    }

}