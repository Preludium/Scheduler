package pl.jansmi.scheduler.dbstructure.daos;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import pl.jansmi.scheduler.dbstructure.entities.Localization;

@Dao
public interface LocalizationDao {

    @Query("SELECT * FROM Localizations")
    List<Localization> getAll();

    @Insert
    void insert(Localization localization);

    @Update
    void update(Localization localization);

    @Delete
    void delete(Localization localization);
}
