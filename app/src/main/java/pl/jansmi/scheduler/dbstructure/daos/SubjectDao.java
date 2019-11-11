package pl.jansmi.scheduler.dbstructure.daos;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import pl.jansmi.scheduler.dbstructure.entities.Subject;

@Dao
public interface SubjectDao {

    @Query("SELECT * FROM Subjects WHERE id = :id")
    Subject getById(String id);

    @Query("SELECT * FROM Subjects")
    List<Subject> getAll();

    @Insert
    void insert(Subject subject);

    @Update
    void update(Subject subject);

    @Delete
    void delete(Subject subject);

}
