package pl.jansmi.scheduler.dbstructure.daos;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import pl.jansmi.scheduler.dbstructure.relations.MealArrangementJoin;

@Dao
public interface MealArrangementJoinDao {

    @Insert
    void insert(MealArrangementJoin mealArrangementJoin);

    @Update
    void update(MealArrangementJoin mealArrangementJoin);

    @Delete
    void delete(MealArrangementJoin mealArrangementJoin);

    @Query("DELETE FROM Meal_arrangement_join WHERE arrangementId = :arrangementId")
    void deleteByArrangementId(String arrangementId);
}
