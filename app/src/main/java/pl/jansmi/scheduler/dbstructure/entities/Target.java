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

@Entity(tableName = "Targets",
        foreignKeys = {@ForeignKey(entity = User.class,
                                   parentColumns = "id",
                                   childColumns = "userId",
                                   onDelete = CASCADE)})
public class Target implements Serializable {

    @PrimaryKey
    @NonNull
    private String id;
    @NonNull
    private String name;
    private Date deadline;
    private boolean achieved;
    @NonNull
    private String userId;

    public Target(@NonNull String id, @NonNull String name, Date deadline, boolean achieved, @NonNull String userId) {
        this.id = id;
        this.name = name;
        this.deadline = deadline;
        this.achieved = achieved;
        this.userId = userId;
    }

    @Ignore
    public Target(@NonNull String name, Date deadline, @NonNull String userId) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.deadline = deadline;
        this.achieved = false;
        this.userId = userId;
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

    public Date getDeadline() {
        return deadline;
    }

    public void setDeadline(Date deadline) {
        this.deadline = deadline;
    }

    public boolean isAchieved() {
        return achieved;
    }

    public void setAchieved(boolean achieved) {
        this.achieved = achieved;
    }

    @NonNull
    public String getUserId() {
        return userId;
    }

}
