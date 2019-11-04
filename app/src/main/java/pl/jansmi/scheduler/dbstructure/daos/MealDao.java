package pl.jansmi.scheduler.dbstructure.daos;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import pl.jansmi.scheduler.dbstructure.entities.Meal;

@Dao
public interface MealDao {

    @Query("SELECT * FROM Meals m JOIN Meal_arrangement_join j " +
            "ON m.id = j.mealId WHERE j.arrangementId = :arrangementId")
    List<Meal> getByArrangementId(String arrangementId);

    @Query("SELECT * FROM Meals WHERE categoryId = :categoryId")
    List<Meal> getByCategoryId(String categoryId);

    @Insert
    void insert(Meal meal);

    @Update
    void update(Meal meal);

    @Delete
    void delete(Meal meal);
}
