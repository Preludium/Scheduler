package pl.jansmi.scheduler.dbstructure.entities;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import static androidx.room.ForeignKey.CASCADE;

@Entity(tableName = "Daily_menus",
        foreignKeys = @ForeignKey(entity = Arrangement.class,
                                  parentColumns = "id",
                                  childColumns = "arrangementId",
                                  onDelete = CASCADE))
public class DailyMenu {

    @NonNull
    @PrimaryKey
    private String id;
    private int dayNumber;
    @NonNull
    private String arrangementId;

    public DailyMenu(@NonNull String id, @NonNull String arrangementId, int dayNumber) {
        this.id = id;
        this.arrangementId = arrangementId;
        this.dayNumber = dayNumber;
    }

    @NonNull
    public String getId() {
        return id;
    }

    @NonNull
    public String getArrangementId() {
        return arrangementId;
    }

    public int getDayNumber() {
        return dayNumber;
    }
}
