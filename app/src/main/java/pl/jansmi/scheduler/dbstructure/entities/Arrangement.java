package pl.jansmi.scheduler.dbstructure.entities;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.util.Date;
import java.util.UUID;

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

    @Ignore
    public Arrangement(@NonNull String name, @NonNull Date created, @NonNull String userId) {
        this.id = UUID.randomUUID().toString();
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

    public void setName(@NonNull String name) {
        this.name = name;
    }

    @NonNull
    public Date getCreated() {
        return created;
    }

    public void setCreated(@NonNull Date created) {
        this.created = created;
    }

    @NonNull
    public String getUserId() {
        return userId;
    }

}
