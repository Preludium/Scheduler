package pl.jansmi.scheduler.dbstructure.entities;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.io.Serializable;
import java.util.UUID;

@Entity(tableName = "Disciplines")
public class Discipline implements Serializable {

    @PrimaryKey
    @NonNull
    private String id;
    @NonNull
    private String name;
    private int kcalPerMinute;
    private float favour;

    public Discipline(@NonNull String id, @NonNull String name, int kcalPerMinute, float favour) {
        this.id = id;
        this.name = name;
        this.kcalPerMinute = kcalPerMinute;
        this.favour = favour;
    }

    @Ignore
    public Discipline(@NonNull String name) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.kcalPerMinute = 0;
        this.favour = 1.f;
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

    public int getKcalPerMinute() {
        return kcalPerMinute;
    }

    public void setKcalPerMinute(int kcalPerMinute) {
        this.kcalPerMinute = kcalPerMinute;
    }

    public float getFavour() {
        return favour;
    }

    public void setFavour(float favour) {
        this.favour = favour;
    }
}
