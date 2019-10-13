package pl.jansmi.scheduler.dbstructure.entities;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "Practices")
public class Practice {

    @PrimaryKey
    @NonNull
    private String id;

    @NonNull
    private String name;

    private int duration;

    private float favour;

    public Practice(@NonNull String id, @NonNull String name, int duration, float favour) {
        this.id = id;
        this.name = name;
        this.duration = duration;
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

    public int getDuration() {
        return duration;
    }

    public float getFavour() {
        return favour;
    }
}
