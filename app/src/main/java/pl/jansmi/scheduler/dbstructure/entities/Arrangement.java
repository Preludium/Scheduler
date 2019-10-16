package pl.jansmi.scheduler.dbstructure.entities;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import org.jetbrains.annotations.NotNull;

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
    private Date date;

    @NonNull
    private String userId;

    public Arrangement(@NotNull String id, @NotNull String name, @NotNull Date date, @NonNull String userId) {
        this.id = id;
        this.name = name;
        this.date = date;
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
    public Date getDate() {
        return date;
    }

    @NonNull
    public String getUserId() {
        return userId;
    }
}
