package net.vib.dam.stocks_template;

public class Stock {
    private String id;
    private String country;
    private String stock_industry;
    private String stock_market;
    private String stock_market_cap;
    private String stock_name;
    private String stock_sector;
    private String stock_symbol;
    private Double latitude;
    private Double longitude;

    public Stock(String id, String country, String stock_industry, String stock_market, String stock_market_cap, String stock_name, String stock_sector, String stock_symbol, Float latitude, Float longitude) {
        this.id = id;
        this.country = country;
        this.stock_industry = stock_industry;
        this.stock_market = stock_market;
        this.stock_market_cap = stock_market_cap;
        this.stock_name = stock_name;
        this.stock_sector = stock_sector;
        this.stock_symbol = stock_symbol;
        this.latitude = Double.valueOf(latitude);
        this.longitude = Double.valueOf(longitude);
    }

    public Stock() {

    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getStock_industry() {
        return stock_industry;
    }

    public void setStock_industry(String stock_industry) {
        this.stock_industry = stock_industry;
    }

    public String getStock_market() {
        return stock_market;
    }

    public void setStock_market(String stock_market) {
        this.stock_market = stock_market;
    }

    public String getStock_market_cap() {
        return stock_market_cap;
    }

    public void setStock_market_cap(String stock_market_cap) {
        this.stock_market_cap = stock_market_cap;
    }

    public String getStock_name() {
        return stock_name;
    }

    public void setStock_name(String stock_name) {
        this.stock_name = stock_name;
    }

    public String getStock_sector() {
        return stock_sector;
    }

    public void setStock_sector(String stock_sector) {
        this.stock_sector = stock_sector;
    }

    public String getStock_symbol() {
        return stock_symbol;
    }

    public void setStock_symbol(String stock_symbol) {
        this.stock_symbol = stock_symbol;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    @Override
    public String toString() {
        return "Stock{" +
                "id='" + id + '\'' +
                ", country='" + country + '\'' +
                ", stock_industry='" + stock_industry + '\'' +
                ", stock_market='" + stock_market + '\'' +
                ", stock_market_cap='" + stock_market_cap + '\'' +
                ", stock_name='" + stock_name + '\'' +
                ", stock_sector='" + stock_sector + '\'' +
                ", stock_symbol='" + stock_symbol + '\'' +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                '}';
    }
}
