package pl.jansmi.scheduler.dbstructure.entities;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import static androidx.room.ForeignKey.CASCADE;

@Entity(tableName = "Practices",
        foreignKeys = {@ForeignKey(entity = Arrangement.class,
                                   parentColumns = "id",
                                   childColumns = "arrangementId",
                                   onDelete = CASCADE),
                       @ForeignKey(entity = Discipline.class,
                                   parentColumns = "id",
                                   childColumns = "disciplineId",
                                   onDelete = CASCADE)})
public class Practice {

    @PrimaryKey
    @NonNull
    private String id;
    @NonNull
    private String name;
    private int duration;
    private float favour;
    @NonNull
    private String arrangementId;
    @NonNull
    private String disciplineId;

    public Practice(@NonNull String id, @NonNull String name, int duration, float favour,
                    @NonNull String arrangementId, @NonNull String disciplineId) {
        this.id = id;
        this.name = name;
        this.duration = duration;
        this.favour = favour;
        this.arrangementId = arrangementId;
        this.disciplineId = disciplineId;
    }

    @NonNull
    public String getId() {
        return id;
    }

    @NonNull
    public String getName() {
        return name;
    }

    public int getDuration() {
        return duration;
    }

    public float getFavour() {
        return favour;
    }

    @NonNull
    public String getArrangementId() {
        return arrangementId;
    }

    @NonNull
    public String getDisciplineId() {
        return disciplineId;
    }
}
