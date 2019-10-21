package pl.jansmi.scheduler.dbstructure.daos;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import pl.jansmi.scheduler.dbstructure.entities.DailyMenu;

@Dao
public interface DailyMenuDao {

    @Query("SELECT * FROM Daily_menus WHERE arrangementId = :arrangementId")
    List<DailyMenu> getByArrangementId(String arrangementId);

    @Insert
    void insert(DailyMenu dailyMenu);

    @Update
    void update(DailyMenu dailyMenu);

    @Delete
    void delete(DailyMenu dailyMenu);
}
