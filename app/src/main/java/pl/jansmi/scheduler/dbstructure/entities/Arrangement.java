package pl.jansmi.scheduler.dbstructure.entities;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import java.util.Date;

import static androidx.room.ForeignKey.CASCADE;

@Entity(tableName = "Arrangements",
        foreignKeys = @ForeignKey(entity = User.class,
                                  parentColumns = "id",
                                  childColumns = "userId",
                                  onDelete = CASCADE))
public class Arrangement {

    @PrimaryKey
    @NonNull
    private String id;
    @NonNull
    private String name;
    @NonNull
    private Date created;
    @NonNull
    private String userId;

    public Arrangement(@NonNull String id, @NonNull String name, @NonNull Date created, @NonNull String userId) {
        this.id = id;
        this.name = name;
        this.created = created;
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

    @NonNull
    public Date getCreated() {
        return created;
    }

    @NonNull
    public String getUserId() {
        return userId;
    }
}
