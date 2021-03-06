package pl.jansmi.scheduler.dbstructure.entities;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

@Entity(tableName = "Users", indices = {@Index(value = "name", unique = true)})
public class User implements Serializable {

    @Ignore
    public static final boolean SEX_MALE = false;
    @Ignore
    public static final boolean SEX_FEMALE = true;

    @PrimaryKey
    @NonNull
    private String id;
    @NonNull
    private String name;
    @NonNull
    private String password;
    private Date birthday;
    private boolean sex; // e.g. 0 - male, 1 - female
    private float weight;
    private int height;
    private int kcalPerDayTarget;

    public User(@NonNull String id, @NonNull String name, Date birthday, @NonNull String password,
                boolean sex, float weight, int height, int kcalPerDayTarget) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.birthday = birthday;
        this.sex = sex;
        this.weight = weight;
        this.height = height;
        this.kcalPerDayTarget = kcalPerDayTarget;
    }

    @Ignore
    public User(@NonNull String name, @NonNull String password) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.password = password;
        this.sex = SEX_MALE;
        this.weight = 0.f;
        this.height = 0;
        this.kcalPerDayTarget = 0;
    }

    @NonNull
    public String getId() {
        return id;
    }

    @NonNull
    public String getName() {
        return name;
    }

    public void setName(@NonNull String name) {
        this.name = name;
    }

    @NonNull
    public String getPassword() {
        return password;
    }

    public void setPassword(@NonNull String password) {
        this.password = password;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public boolean isSex() {
        return sex;
    }

    public void setSex(boolean sex) {
        this.sex = sex;
    }

    public float getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getKcalPerDayTarget() {
        return kcalPerDayTarget;
    }

    public void setKcalPerDayTarget(int kcalPerDayTarget) {
        this.kcalPerDayTarget = kcalPerDayTarget;
    }

    @Override
    public boolean equals(Object user) {
        if (user == this)
            return true;
        else if (user == null || user.getClass() != this.getClass())
            return false;
        else {
            User pom = (User) user;
            return this.id.equals(pom.id);
        }
    }
}
