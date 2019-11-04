package pl.jansmi.scheduler.dbstructure.daos;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import pl.jansmi.scheduler.dbstructure.entities.Subject;

@Dao
public interface SubjectDao {

    @Query("SELECT * FROM Subjects WHERE id = :id")
    Subject getById(String id);

    @Insert
    void insert(Subject subject);

    @Update
    void update(Subject subject);

    @Delete
    void delete(Subject subject);

}
