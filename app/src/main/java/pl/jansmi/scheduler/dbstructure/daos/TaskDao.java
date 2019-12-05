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

    @Query("SELECT * FROM Tasks WHERE arrangementId = :arrangementId AND weekday = :day")
    List<Task> getByArrangementIdAndDay(String arrangementId, int day);

    @Query("SELECT * FROM Tasks WHERE arrangementId = :arrangementId")
    List<Task> getByArrangementId(String arrangementId);

    @Query("SELECT * FROM Tasks WHERE id = :id")
    Task getTaskById(String id);

    @Insert
    void insert(Task task);

    @Update
    void update(Task task);

    @Delete
    void delete(Task task);

    @Query("DELETE FROM Tasks WHERE arrangementId = :arrangementId")
    void deleteByArrangementId(String arrangementId);
}
