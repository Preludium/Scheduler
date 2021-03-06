package pl.jansmi.scheduler.dbstructure.daos;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import pl.jansmi.scheduler.dbstructure.entities.Ingredient;

@Dao
public interface IngredientDao {

    @Query("SELECT * FROM Ingredients")
    List<Ingredient> getAll();

    @Query("SELECT * FROM Ingredients WHERE name LIKE '%' || :phrase || '%'")
    List<Ingredient> getAllLike(String phrase);

    @Query("SELECT * FROM Ingredients WHERE id = :ingredientId")
    Ingredient getById(String ingredientId);

    @Query("SELECT * FROM Ingredients i JOIN Ingredient_meal_join j " +
            "ON i.id = j.ingredientId WHERE j.mealId = :mealId")
    List<Ingredient> getByMealId(String mealId);

    @Query("UPDATE Ingredients SET favour = 0.9*favour")
    void updateFavours();

    @Insert
    void insert(Ingredient ingredient);

    @Update
    void update(Ingredient ingredient);

    @Delete
    void delete(Ingredient ingredient);

}
