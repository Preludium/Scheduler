package pl.jansmi.scheduler.dbstructure.daos;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import pl.jansmi.scheduler.dbstructure.entities.Arrangement;

@Dao
public interface ArrangementDao {

    @Query("SELECT * FROM Arrangements WHERE userId = :userId")
    List<Arrangement> getByUserId(String userId);

    @Insert
    void insert(Arrangement arrangement);

    @Update
    void update(Arrangement arrangement);

    @Delete
    void delete(Arrangement arrangement);
}
