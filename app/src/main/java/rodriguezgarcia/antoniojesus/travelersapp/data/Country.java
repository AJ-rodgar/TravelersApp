package rodriguezgarcia.antoniojesus.travelersapp.data;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "countries")
public class Country implements Serializable {

    @PrimaryKey (autoGenerate = true)
    private long id;
    @NonNull
    private String name;
    private String region;
    private String capital;
    private String language;
    private String currency;
    private double longitude;
    private double latitude;
    @NonNull
    private int state;
    private int imageID;

    public Country() {

    }

    public Country (String name, String region, String capital, String language, String currency, double longitude, double latitude, int state, int imageID) {
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

    public Country (String name, long id) {
        this.name = name;
        this.id = id;
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

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public int getImageID() {
        return imageID;
    }

    public void setImageID(int imageID) {
        this.imageID = imageID;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
