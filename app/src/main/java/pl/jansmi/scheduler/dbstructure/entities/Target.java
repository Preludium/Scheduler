package pl.jansmi.scheduler.dbstructure.entities;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import java.util.Date;

import static androidx.room.ForeignKey.CASCADE;

@Entity(tableName = "Targets",
        foreignKeys = {@ForeignKey(entity = User.class,
                                   parentColumns = "id",
                                   childColumns = "userId",
                                   onDelete = CASCADE)})
public class Target {

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

    @NonNull
    public String getId() {
        return id;
    }

    @NonNull
    public String getName() {
        return name;
    }

    public Date getDeadline() {
        return deadline;
    }

    public boolean isAchieved() {
        return achieved;
    }

    @NonNull
    public String getUserId() {
        return userId;
    }

}
