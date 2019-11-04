package pl.jansmi.scheduler.dbstructure.daos;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import pl.jansmi.scheduler.dbstructure.entities.Study;

@Dao
public interface StudyDao {

    @Query("SELECT * FROM Studies WHERE arrangementId = :arrangementId")
    List<Study> getByArrangementId(String arrangementId);

    @Insert
    void insert(Study study);

    @Update
    void update(Study study);

    @Delete
    void delete(Study study);
}
