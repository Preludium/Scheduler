package pl.jansmi.scheduler.dbstructure.daos;

import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import pl.jansmi.scheduler.dbstructure.entities.Meal;

public interface MealDao {

    @Query("SELECT * FROM Meals m JOIN Meal_daily_menu_join j " +
            "USING(mealId) WHERE j.dailyMenuId = :dailyMenuId")
    List<Meal> getByDailyMenuId(String dailyMenuId);

    @Insert
    void insert(Meal meal);

    @Update
    void update(Meal meal);

    @Delete
    void delete(Meal meal);
}
