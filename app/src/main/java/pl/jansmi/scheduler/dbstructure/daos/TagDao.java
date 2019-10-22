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

    @Query("SELECT * FROM Tags JOIN Tag_arrangement_join " +
            "USING(arrangementId) WHERE arrangementId = :arrangementId")
    List<Tag> getByArrangementId(String arrangementId);

    @Insert
    void insert(Tag tag);

    @Update
    void update(Tag tag);

    @Delete
    void delete(Tag tag);
}
