package pl.jansmi.scheduler.dbstructure.entities;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

import static androidx.room.ForeignKey.CASCADE;

@Entity(tableName = "Invitations",
        foreignKeys = {@ForeignKey(entity = User.class,
                                   parentColumns = "id",
                                   childColumns = "fromId",
                                   onDelete = CASCADE),
                       @ForeignKey(entity = User.class,
                                   parentColumns = "id",
                                   childColumns = "toId",
                                   onDelete = CASCADE)})
public class Invitation implements Serializable {

    @Ignore
    public static final byte STATE_SENT = 0;
    @Ignore
    public static final byte STATE_ACCEPTED = 1;
    @Ignore
    public static final byte STATE_REFUSED = 2;


    @NonNull
    @PrimaryKey
    private String id;
    @NonNull
    private String fromId;
    @NonNull
    private String toId;
    @NonNull
    private String taskId;
    private byte state; // 0-sent, 1-accepted, 2-refused

    public Invitation(@NonNull String id, @NonNull String fromId, @NonNull String toId, @NonNull String taskId, byte state) {
        this.id = id;
        this.fromId = fromId;
        this.toId = toId;
        this.taskId = taskId;
        this.state = state;
    }

    @Ignore
    public Invitation(@NonNull String fromId, @NonNull String toId, @NonNull String taskId) {
        this.id = UUID.randomUUID().toString();
        this.fromId = fromId;
        this.toId = toId;
        this.taskId = taskId;
        this.state =STATE_SENT;
    }

    @NonNull
    public String getId() {
        return id;
    }

    @NonNull
    public String getFromId() {
        return fromId;
    }

    public void setFromId(@NonNull String fromId) {
        this.fromId = fromId;
    }

    @NonNull
    public String getToId() {
        return toId;
    }

    public void setToId(@NonNull String toId) {
        this.toId = toId;
    }

    @NonNull
    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(@NonNull String taskId) {
        this.taskId = taskId;
    }

    public byte getState() {
        return state;
    }

    public void setState(byte state) {
        this.state = state;
    }
}
