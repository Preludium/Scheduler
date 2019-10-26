package pl.jansmi.scheduler.dbstructure.entities;

import androidx.annotation.NonNull;
import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.util.UUID;

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
    private int weekday;
    private int durationMinutes;
    @NonNull
    private String arrangementId;
    private String localizationId;

    public Task(@NonNull String id, @NonNull String name, String description, int weekday, int durationMinutes, @NonNull String arrangementId, String localizationId) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.weekday = weekday;
        this.durationMinutes = durationMinutes;
        this.arrangementId = arrangementId;
        this.localizationId = localizationId;
    }

    @Ignore
    public Task(@NonNull String name, int weekday, @NonNull String arrangementId) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.weekday = weekday;
        this.durationMinutes = 0;
        this.arrangementId = arrangementId;
        this.localizationId = null;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getWeekday() {
        return weekday;
    }

    public void setWeekday(int weekday) {
        this.weekday = weekday;
    }

    public int getDurationMinutes() {
        return durationMinutes;
    }

    public void setDurationMinutes(int durationMinutes) {
        this.durationMinutes = durationMinutes;
    }

    @NonNull
    public String getArrangementId() {
        return arrangementId;
    }

    public String getLocalizationId() {
        return localizationId;
    }

    public void setLocalizationId(String localizationId) {
        this.localizationId = localizationId;
    }
}
