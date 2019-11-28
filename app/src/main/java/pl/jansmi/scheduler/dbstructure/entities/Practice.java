package pl.jansmi.scheduler.dbstructure.entities;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.io.Serializable;
import java.util.UUID;

import static androidx.room.ForeignKey.CASCADE;

@Entity(tableName = "Practices",
        foreignKeys = {@ForeignKey(entity = Arrangement.class,
                                   parentColumns = "id",
                                   childColumns = "arrangementId",
                                   onDelete = CASCADE),
                       @ForeignKey(entity = Discipline.class,
                                   parentColumns = "id",
                                   childColumns = "disciplineId",
                                   onDelete = CASCADE)})
public class Practice implements Serializable {

    @PrimaryKey
    @NonNull
    private String id;
    @NonNull
    private String name;
    private int dayNumber;
    private int duration;
    @NonNull
    private String arrangementId;
    @NonNull
    private String disciplineId;

    public Practice(@NonNull String id, @NonNull String name, int dayNumber, int duration,
                    @NonNull String arrangementId, @NonNull String disciplineId) {
        this.id = id;
        this.name = name;
        this.dayNumber = dayNumber;
        this.duration = duration;
        this.arrangementId = arrangementId;
        this.disciplineId = disciplineId;
    }

    @Ignore
    public Practice(@NonNull String name, int dayNumber,
                    @NonNull String arrangementId, int duration,  @NonNull String disciplineId) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.dayNumber = dayNumber;
        this.duration = duration;
        this.arrangementId = arrangementId;
        this.disciplineId = disciplineId;
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

    public int getDayNumber() {
        return dayNumber;
    }

    public void setDayNumber(int dayNumber) {
        this.dayNumber = dayNumber;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    @NonNull
    public String getArrangementId() {
        return arrangementId;
    }

    public void setArrangementId(@NonNull String arrangementId) {
        this.arrangementId = arrangementId;
    }

    @NonNull
    public String getDisciplineId() {
        return disciplineId;
    }

    public void setDisciplineId(@NonNull String disciplineId) {
        this.disciplineId = disciplineId;
    }

}
