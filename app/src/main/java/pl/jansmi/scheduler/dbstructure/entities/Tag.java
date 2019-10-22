package pl.jansmi.scheduler.dbstructure.entities;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "Tags")
public class Tag {

    @PrimaryKey
    @NonNull
    private String id;
    @NonNull
    private String name;
    private float favour;

    public Tag(@NonNull String id, @NonNull String name, float favour) {
        this.id = id;
        this.name = name;
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

    public float getFavour() {
        return favour;
    }

}
