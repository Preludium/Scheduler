package pl.jansmi.scheduler.dbstructure.entities;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.util.Date;

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
public class Invitation {

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

    @NonNull
    public String getTaskId() {
        return taskId;
    }

    public byte getState() {
        return state;
    }

}
