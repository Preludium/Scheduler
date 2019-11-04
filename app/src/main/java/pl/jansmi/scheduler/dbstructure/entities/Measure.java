package pl.jansmi.scheduler.dbstructure.entities;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.util.Date;
import java.util.UUID;

import static androidx.room.ForeignKey.CASCADE;

@Entity(tableName = "Measures",
        foreignKeys = {@ForeignKey(entity = User.class,
                                   parentColumns = "id",
                                   childColumns = "userId",
                                   onDelete = CASCADE)})
public class Measure {

    @PrimaryKey
    @NonNull
    private String id;
    @NonNull
    private Date date;
    private float weight;
    private float water; // number of glasses
    @NonNull
    private String userId;

    public Measure(@NonNull String id, @NonNull Date date, float weight, float water, @NonNull String userId) {
        this.id = id;
        this.date = date;
        this.weight = weight;
        this.water = water;
        this.userId = userId;
    }

    @Ignore
    public Measure(@NonNull Date date, @NonNull String userId) {
        this.id = UUID.randomUUID().toString();
        this.date = date;
        this.weight = 0;
        this.water = 0;
        this.userId = userId;
    }

    @NonNull
    public String getId() {
        return id;
    }

    @NonNull
    public Date getDate() {
        return date;
    }

    public void setDate(@NonNull Date date) {
        this.date = date;
    }

    public float getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }

    public float getWater() {
        return water;
    }

    public void setWater(float water) {
        this.water = water;
    }

    @NonNull
    public String getUserId() {
        return userId;
    }

}
