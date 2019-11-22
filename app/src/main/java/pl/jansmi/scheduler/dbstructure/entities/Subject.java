package pl.jansmi.scheduler.dbstructure.entities;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.io.Serializable;
import java.util.UUID;

@Entity(tableName = "Subjects")
public class Subject implements Serializable {

    @PrimaryKey
    @NonNull
    private String id;
    @NonNull
    private String name;
    private float favour;

    public Subject(@NonNull String id, @NonNull String name, float favour) {
        this.id = id;
        this.name = name;
        this.favour = favour;
    }

    @Ignore
    public Subject(@NonNull String name) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
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

    public float getFavour() {
        return favour;
    }

    public void setFavour(float favour) {
        this.favour = favour;
    }

}
