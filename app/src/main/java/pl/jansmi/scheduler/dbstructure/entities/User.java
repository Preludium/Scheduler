package pl.jansmi.scheduler.dbstructure.entities;

import android.media.Image;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Date;

@Entity(tableName = "Users")
public class User {

    @PrimaryKey
    @NonNull
    private String id;

    @NonNull
    private String name;

    private Image profilePic;

    @NonNull
    private Date dateOfBirth;

    private boolean sex; // e.g. 0 - male, 1 - female

    private float weight;

    private float height;

    private int kcalPerDayTarget;

    public User(@NonNull String id, @NonNull String name, Image profilePic, @NonNull Date dateOfBirth,
                boolean sex, float weight, float height, int kcalPerDayTarget) {
        this.id = id;
        this.name = name;
        this.profilePic = profilePic;
        this.dateOfBirth = dateOfBirth;
        this.sex = sex;
        this.weight = weight;
        this.height = height;
        this.kcalPerDayTarget = kcalPerDayTarget;
    }

    @NonNull
    public String getId() {
        return id;
    }

    @NonNull
    public String getName() {
        return name;
    }

    public Image getProfilePic() { return profilePic; }

    @NonNull
    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public boolean isSex() {
        return sex;
    }

    public float getWeight() {
        return weight;
    }

    public float getHeight() {
        return height;
    }

    public int getKcalPerDayTarget() {
        return kcalPerDayTarget;
    }
}
