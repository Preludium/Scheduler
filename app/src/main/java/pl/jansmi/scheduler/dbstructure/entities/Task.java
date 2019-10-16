package pl.jansmi.scheduler.dbstructure.entities;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import java.util.Date;

import static androidx.room.ForeignKey.CASCADE;

@Entity(tableName = "Tasks",
        foreignKeys = @ForeignKey(entity = Arrangement.class,
                                  parentColumns = "id",
                                  childColumns = "arrangementId",
                                  onDelete = CASCADE))
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

    @NonNull
    private String arrangementId;

    public Task(@NonNull String id, @NonNull String name, String description, Date deadline, int durationMinutes, float favour, @NonNull String arrangementId) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.deadline = deadline;
        this.durationMinutes = durationMinutes;
        this.favour = favour;
        this.arrangementId = arrangementId;
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

    @NonNull
    public String getArrangementId() {
        return arrangementId;
    }
}
