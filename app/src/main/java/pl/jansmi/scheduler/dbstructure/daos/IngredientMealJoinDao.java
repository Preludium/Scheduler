package pl.jansmi.scheduler.dbstructure.daos;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import pl.jansmi.scheduler.dbstructure.relations.IngredientMealJoin;

@Dao
public interface IngredientMealJoinDao {

    @Query("SELECT * FROM Ingredient_meal_join WHERE mealId = :mealId")
    List<IngredientMealJoin> getIngredientsByMealId(String mealId);

    @Insert
    void insert(IngredientMealJoin ingredientMealJoin);

    @Update
    void update(IngredientMealJoin ingredientMealJoin);

    @Delete
    void delete(IngredientMealJoin ingredientMealJoin);
}
