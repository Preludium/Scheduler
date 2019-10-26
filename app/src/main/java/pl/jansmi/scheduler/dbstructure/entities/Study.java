package pl.jansmi.scheduler.dbstructure.entities;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.util.UUID;

import static androidx.room.ForeignKey.CASCADE;

@Entity(tableName = "Studies",
        foreignKeys = {@ForeignKey(entity = Arrangement.class,
                                   parentColumns = "id",
                                   childColumns = "arrangementId",
                                   onDelete = CASCADE)})
public class Study {

    @PrimaryKey
    @NonNull
    private String id;
    @NonNull
    private String subject;
    private String desc;
    private int dayNumber; // 0-6
    private int duration; // minutes
    @NonNull
    private String arrangementId;

    public Study(@NonNull String id, @NonNull String subject, @NonNull String desc,
                 int dayNumber, int duration, @NonNull String arrangementId) {
        this.id = id;
        this.subject = subject;
        this.desc = desc;
        this.dayNumber = dayNumber;
        this.duration = duration;
        this.arrangementId = arrangementId;
    }

    @Ignore
    public Study(@NonNull String subject, int dayNumber, @NonNull String arrangementId) {
        this.id = UUID.randomUUID().toString();
        this.subject = subject;
        this.dayNumber = dayNumber;
        this.duration = 0;
        this.arrangementId = arrangementId;
    }

    @NonNull
    public String getId() {
        return id;
    }

    @NonNull
    public String getSubject() {
        return subject;
    }

    public void setSubject(@NonNull String subject) {
        this.subject = subject;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
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

}
