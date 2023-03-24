package net.vidalibarraquer.pt6_pablosanjose;

public class League {

    private String key;
    private String name;
    private String code;

    public League(String key, String name, String code) {
        this.key = key;
        this.name = name;
        this.code = code;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }
}
