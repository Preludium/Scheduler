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
            "ON m.id = j.mealId WHERE j.arrangementId = :arrangementId AND dayNumber = :day")
    List<Meal> getByArrangementId(String arrangementId, int day);

    @Query("SELECT * FROM Meals WHERE categoryId = :categoryId")
    List<Meal> getByCategoryId(String categoryId);

    @Query("SELECT * FROM Meals WHERE id = :mealId")
    Meal getById(String mealId);

    @Query("UPDATE Meals SET favour = 0.9*favour")
    void updateFavours();

    @Insert
    void insert(Meal meal);

    @Update
    void update(Meal meal);

    @Delete
    void delete(Meal meal);

}
