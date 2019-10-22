package pl.jansmi.scheduler.dbstructure.relations;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.ForeignKey;

import pl.jansmi.scheduler.dbstructure.entities.Arrangement;
import pl.jansmi.scheduler.dbstructure.entities.Tag;

@Entity(tableName = "Tag_arrangement_join",
        foreignKeys = {@ForeignKey(entity = Tag.class,
                                   parentColumns = "id",
                                   childColumns = "tagId"),
                       @ForeignKey(entity = Arrangement.class,
                                   parentColumns = "id",
                                   childColumns = "arrangementId")})
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
