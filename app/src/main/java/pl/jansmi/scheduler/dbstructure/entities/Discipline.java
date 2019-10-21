package pl.jansmi.scheduler.dbstructure.entities;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "Disciplines")
public class Discipline {

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

    @NonNull
    public String getId() {
        return id;
    }

    @NonNull
    public String getName() {
        return name;
    }

    public int getKcalPerMinute() {
        return kcalPerMinute;
    }

    public float getFavour() {
        return favour;
    }

}
