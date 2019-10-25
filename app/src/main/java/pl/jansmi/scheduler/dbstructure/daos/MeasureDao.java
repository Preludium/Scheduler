package pl.jansmi.scheduler.dbstructure.daos;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import pl.jansmi.scheduler.dbstructure.entities.Measure;

@Dao
public interface MeasureDao {

    @Query("SELECT * FROM Measures WHERE userId = :userId")
    List<Measure> getByUserId(String userId);

    @Insert
    void insert(Measure measure);

    @Update
    void update(Measure measure);

    @Delete
    void delete(Measure measure);
}
