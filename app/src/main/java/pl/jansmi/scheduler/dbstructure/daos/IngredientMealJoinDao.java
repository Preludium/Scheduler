package pl.jansmi.scheduler.dbstructure.daos;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Update;

import pl.jansmi.scheduler.dbstructure.relations.IngredientMealJoin;

@Dao
public interface IngredientMealJoinDao {

    @Insert
    void insert(IngredientMealJoin ingredientMealJoin);

    @Update
    void update(IngredientMealJoin ingredientMealJoin);

    @Delete
    void delete(IngredientMealJoin ingredientMealJoin);
}
