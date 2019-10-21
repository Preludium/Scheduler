package pl.jansmi.scheduler.dbstructure.daos;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import pl.jansmi.scheduler.dbstructure.entities.Task;

@Dao
public interface TaskDao {

    @Query("SELECT * FROM Tasks WHERE arrangementId = :arrangementId")
    List<Task> getByArrangementId(String arrangementId);

    @Insert
    void insert(Task task);

    @Update
    void update(Task task);

    @Delete
    void delete(Task task);
}
