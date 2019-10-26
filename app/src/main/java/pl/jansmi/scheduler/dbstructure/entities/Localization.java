package pl.jansmi.scheduler.dbstructure.entities;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.util.UUID;

@Entity(tableName = "Localizations")
public class Localization {

    @PrimaryKey
    @NonNull
    private String id;
    @NonNull
    private String place;
    private String address;
    private String city;
    private float favour;

    public Localization(@NonNull String id, @NonNull String place, String address, String city, float favour) {
        this.id = id;
        this.place = place;
        this.address = address;
        this.city = city;
        this.favour = favour;
    }

    @Ignore
    public Localization(@NonNull String place) {
        this.id = UUID.randomUUID().toString();
        this.place = place;
        this.favour = 1.f;
    }

    @NonNull
    public String getId() {
        return id;
    }

    @NonNull
    public String getPlace() {
        return place;
    }

    public void setPlace(@NonNull String place) {
        this.place = place;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public float getFavour() {
        return favour;
    }

    public void setFavour(float favour) {
        this.favour = favour;
    }
}
