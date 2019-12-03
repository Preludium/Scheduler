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

    @Query("SELECT * FROM Studies WHERE arrangementId = :arrangementId AND dayNumber = :day")
    List<Study> getByArrangementId(String arrangementId, int day);

    @Query("SELECT * FROM Studies WHERE id = :studyId")
    Study getById(String studyId);

    @Insert
    void insert(Study study);

    @Update
    void update(Study study);

    @Delete
    void delete(Study study);

    @Query("DELETE FROM Studies WHERE arrangementId = :arrangementId")
    void deleteByArrangementId(String arrangementId);
}
