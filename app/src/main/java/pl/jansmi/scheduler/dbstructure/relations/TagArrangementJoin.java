package pl.jansmi.scheduler.dbstructure.relations;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.ForeignKey;

import pl.jansmi.scheduler.dbstructure.entities.Arrangement;
import pl.jansmi.scheduler.dbstructure.entities.Tag;

import static androidx.room.ForeignKey.CASCADE;

@Entity(tableName = "Tag_arrangement_join",
        primaryKeys = {"tagId", "arrangementId"},
        foreignKeys = {@ForeignKey(entity = Tag.class,
                                   parentColumns = "id",
                                   childColumns = "tagId",
                                   onDelete = CASCADE),
                       @ForeignKey(entity = Arrangement.class,
                                   parentColumns = "id",
                                   childColumns = "arrangementId",
                                   onDelete = CASCADE)})
public class TagArrangementJoin {

    @NonNull
    private String tagId;
    @NonNull
    private String arrangementId;

    public TagArrangementJoin(@NonNull String tagId, @NonNull String arrangementId) {
        this.tagId = tagId;
        this.arrangementId = arrangementId;
    }

    @NonNull
    public String getTagId() {
        return tagId;
    }

    @NonNull
    public String getArrangementId() {
        return arrangementId;
    }
}
