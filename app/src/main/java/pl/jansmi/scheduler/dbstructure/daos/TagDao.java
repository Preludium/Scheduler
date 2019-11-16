package pl.jansmi.scheduler.dbstructure.daos;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import pl.jansmi.scheduler.dbstructure.entities.Tag;

@Dao
public interface TagDao {

    @Query("SELECT * FROM Tags t JOIN Tag_arrangement_join j " +
            "ON t.id = j.tagId WHERE j.arrangementId = :arrangementId")
    List<Tag> getByArrangementId(String arrangementId);

    @Query("SELECT * FROM Tags WHERE id = :tagId")
    Tag getById(String tagId);

    @Insert
    void insert(Tag tag);

    @Update
    void update(Tag tag);

    @Delete
    void delete(Tag tag);
}
