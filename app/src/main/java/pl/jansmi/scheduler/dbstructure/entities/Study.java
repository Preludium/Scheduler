package pl.jansmi.scheduler.dbstructure.entities;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.io.Serializable;
import java.util.UUID;

import static androidx.room.ForeignKey.CASCADE;

@Entity(tableName = "Studies",
        foreignKeys = {@ForeignKey(entity = Arrangement.class,
                                   parentColumns = "id",
                                   childColumns = "arrangementId",
                                   onDelete = CASCADE),
                       @ForeignKey(entity = Subject.class,
                                   parentColumns = "id",
                                   childColumns = "subjectId",
                                   onDelete = CASCADE)})
public class Study implements Serializable {
    private static final long serialVersionUID = 1L;

    @PrimaryKey
    @NonNull
    private String id;
    @NonNull
    private String title;
    private String desc;
    private int dayNumber; // 0-6
    private int duration; // minutes
    @NonNull
    private String arrangementId;
    @NonNull
    private String subjectId;

    public Study(@NonNull String id, @NonNull String title, @NonNull String desc,
                 int dayNumber, int duration, @NonNull String arrangementId, @NonNull String subjectId) {
        this.id = id;
        this.title = title;
        this.desc = desc;
        this.dayNumber = dayNumber;
        this.duration = duration;
        this.arrangementId = arrangementId;
        this.subjectId = subjectId;
    }

    @Ignore
    public Study(@NonNull String title, String desc, int dayNumber, @NonNull String arrangementId, int duration,@NonNull String subjectId) {
        this.id = UUID.randomUUID().toString();
        this.title = title;
        this.desc = desc;
        this.dayNumber = dayNumber;
        this.duration = duration;
        this.arrangementId = arrangementId;
        this.subjectId = subjectId;
    }

    @NonNull
    public String getId() {
        return id;
    }

    @NonNull
    public String getTitle() {
        return title;
    }

    public void setTitle(@NonNull String subject) {
        this.title = title;
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

    @NonNull
    public String getSubjectId() {
        return subjectId;
    }
}
