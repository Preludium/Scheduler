package pl.jansmi.scheduler.dbstructure.relations;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.ForeignKey;

import pl.jansmi.scheduler.dbstructure.entities.Discipline;
import pl.jansmi.scheduler.dbstructure.entities.Tag;

@Entity(tableName = "Tag_discipline_join",
        foreignKeys = {@ForeignKey(entity = Tag.class,
                                   parentColumns = "id",
                                   childColumns = "tagId"),
                       @ForeignKey(entity = Discipline.class,
                                   parentColumns = "id",
                                   childColumns = "disciplineId")})
public class TagDisciplineJoin {

    @NonNull
    private String tagId;

    @NonNull
    private String disciplineId;

    public TagDisciplineJoin(@NonNull String tagId, @NonNull String disciplineId) {
        this.tagId = tagId;
        this.disciplineId = disciplineId;
    }

    @NonNull
    public String getTagId() {
        return tagId;
    }

    @NonNull
    public String getDisciplineId() {
        return disciplineId;
    }
}
