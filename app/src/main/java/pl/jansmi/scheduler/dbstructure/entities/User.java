package pl.jansmi.scheduler.dbstructure.entities;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import java.util.Date;

@Entity(tableName = "Users", indices = {@Index(value = "name", unique = true)})
public class User {

    @Ignore
    public static final boolean SEX_MALE = false;
    @Ignore
    public static final boolean SEX_FEMALE = true;

    @PrimaryKey
    @NonNull
    private String id;
    @NonNull
    private String name;
    private Date birthday;
    private boolean sex; // e.g. 0 - male, 1 - female
    private float weight;
    private int height;
    private int kcalPerDayTarget;

    public User(@NonNull String id, @NonNull String name, Date birthday,
                boolean sex, float weight, int height, int kcalPerDayTarget) {
        this.id = id;
        this.name = name;
        this.birthday = birthday;
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

    public Date getBirthday() {
        return birthday;
    }

    public boolean isSex() {
        return sex;
    }

    public float getWeight() {
        return weight;
    }

    public int getHeight() {
        return height;
    }

    public int getKcalPerDayTarget() {
        return kcalPerDayTarget;
    }
}
