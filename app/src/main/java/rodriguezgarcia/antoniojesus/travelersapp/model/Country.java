package rodriguezgarcia.antoniojesus.travelersapp.model;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "countries")
public class Country implements Serializable {

    @PrimaryKey
    private String name;
    private String region;
    private String capital;
    private String language;
    private String currency;
    private double longitude;
    private double latitude;
    @NonNull
    private String state;
    private int imageID;

    public Country() {

    }

    public Country (String name, String region, String capital, String language, String currency, double longitude, double latitude, int imageID) {
        this.name = name;
        this.region = region;
        this.capital = capital;
        this.language = language;
        this.currency = currency;
        this.longitude = longitude;
        this.latitude = latitude;
        state = "Not Visited";
        this.imageID = imageID;
    }

    public Country (String name, String region, String capital, String language, String currency, double longitude, double latitude, String state, String imageURL) {
        this.name = name;
        this.region = region;
        this.capital = capital;
        this.language = language;
        this.currency = currency;
        this.longitude = longitude;
        this.latitude = latitude;
        this.state = state;
        this.imageID = imageID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCapital() {
        return capital;
    }

    public void setCapital(String capital) {
        this.capital = capital;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public int getFlag() {
        return imageID;
    }

    public void setFlag(int imageID) {
        this.imageID = imageID;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }
}
