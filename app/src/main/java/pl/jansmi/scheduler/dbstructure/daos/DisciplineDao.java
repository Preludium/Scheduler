package pl.jansmi.scheduler.dbstructure.daos;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import pl.jansmi.scheduler.dbstructure.entities.Discipline;

@Dao
public interface DisciplineDao {

    @Query("SELECT * FROM Disciplines")
    List<Discipline> getAll();

    @Query("SELECT * FROM Disciplines WHERE id = :id")
    Discipline getById(String id);

    @Insert
    void insert(Discipline discipline);

    @Update
    void update(Discipline discipline);

    @Delete
    void delete(Discipline discipline);
}
