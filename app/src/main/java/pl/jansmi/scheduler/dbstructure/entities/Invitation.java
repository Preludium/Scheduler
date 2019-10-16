package pl.jansmi.scheduler.dbstructure.entities;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import java.util.Date;

import static androidx.room.ForeignKey.CASCADE;

@Entity(tableName = "Names",
        foreignKeys = {@ForeignKey(entity = User.class,
                                   parentColumns = "id",
                                   childColumns = "fromId",
                                   onDelete = CASCADE),
                       @ForeignKey(entity = User.class,
                                   parentColumns = "id",
                                   childColumns = "toId",
                                   onDelete = CASCADE)})
public class Invitation {

    @NonNull
    @PrimaryKey
    private String id;

    @NonNull
    private String fromId;

    @NonNull
    private String toId;

    private Date deadline;

    private byte state; // 0-sent, 1-accepted, 2-refused

    public Invitation(@NonNull String id, @NonNull String fromId, @NonNull String toId, Date deadline) {
        this.id = id;
        this.fromId = fromId;
        this.toId = toId;
        this.deadline = deadline;
        this.state = 0;
    }

    @NonNull
    public String getId() {
        return id;
    }

    @NonNull
    public String getFromId() {
        return fromId;
    }

    @NonNull
    public String getToId() {
        return toId;
    }

    public Date getDeadline() {
        return deadline;
    }

    public byte getState() {
        return state;
    }

    public void setState(byte state) {
        this.state = state;
    }
}
