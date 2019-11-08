package pl.jansmi.scheduler.dbstructure.daos;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import pl.jansmi.scheduler.dbstructure.entities.User;

@Dao
public interface UserDao {

    @Query("SELECT * FROM Users WHERE name = :name")
    User getByName(String name);

    @Query("SELECT * FROM Users WHERE id = :id")
    User getById(String id);

    @Query("SELECT * FROM Users")
    List<User> getAll();

    @Insert
    void insert(User user);

    @Update
    void update(User user);

    @Delete
    void delete(User user);

}
