package pl.jansmi.scheduler.dbstructure.entities;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

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
    @NonNull
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

    @NonNull
    public String getId() {
        return id;
    }

    @NonNull
    public String getSubject() {
        return subject;
    }

    @NonNull
    public String getDesc() {
        return desc;
    }

    public int getDayNumber() {
        return dayNumber;
    }

    public int getDuration() {
        return duration;
    }

    @NonNull
    public String getArrangementId() {
        return arrangementId;
    }

}
