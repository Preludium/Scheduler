package pl.jansmi.scheduler.dbstructure.daos;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import pl.jansmi.scheduler.dbstructure.entities.Target;

@Dao
public interface TargetDao {

    @Query("SELECT * FROM Targets WHERE userId = :userId")
    List<Target> getByUserId(String userId);

    @Insert
    void insert(Target target);

    @Update
    void update(Target target);

    @Delete
    void delete(Target target);
}
