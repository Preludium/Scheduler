package pl.jansmi.scheduler.dbstructure.daos;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import pl.jansmi.scheduler.dbstructure.entities.Practice;

@Dao
public interface PracticeDao {

    @Query("SELECT * FROM Practices WHERE arrangementId = :arrangementId AND dayNumber = :day")
    List<Practice> getByArrangementId(String arrangementId, int day);

    @Insert
    void insert(Practice practice);

    @Update
    void update(Practice practice);

    @Delete
    void delete(Practice practice);

    @Query("DELETE FROM Practices WHERE arrangementId = :arrangementId")
    void deleteByArrangementId(String arrangementId);
}
