package pl.jansmi.scheduler.dbstructure.daos;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import pl.jansmi.scheduler.dbstructure.entities.Category;

@Dao
public interface CategoryDao {

    @Query("SELECT * FROM Categories WHERE id = :id")
    Category getById(String id);

    @Insert
    void insert(Category category);

    @Update
    void update(Category category);

    @Delete
    void delete(Category category);
}
