package pl.jansmi.scheduler.dbstructure.entities;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import java.util.Date;

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
    private float water;
    @NonNull
    private String userId;

    public Measure(@NonNull String id, @NonNull Date date, float weight, float water, @NonNull String userId) {
        this.id = id;
        this.date = date;
        this.weight = weight;
        this.water = water;
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

    public float getWeight() {
        return weight;
    }

    public float getWater() {
        return water;
    }

    @NonNull
    public String getUserId() {
        return userId;
    }
}
