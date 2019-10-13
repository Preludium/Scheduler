package pl.jansmi.scheduler.dbstructure.entities;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Date;

@Entity(tableName = "Tasks")
public class Task {

    @PrimaryKey
    @NonNull
    private String id;

    @NonNull
    private String name;

    private String description;

    private Date deadline;

    private int durationMinutes;

    private float favour;

    public Task(@NonNull String id, @NonNull String name, String description, Date deadline, int durationMinutes, float favour) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.deadline = deadline;
        this.durationMinutes = durationMinutes;
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

    public String getDescription() {
        return description;
    }

    public Date getDeadline() {
        return deadline;
    }

    public int getDurationMinutes() {
        return durationMinutes;
    }

    public float getFavour() {
        return favour;
    }
}
