package pl.jansmi.scheduler.dbstructure.daos;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Update;

import pl.jansmi.scheduler.dbstructure.relations.MealDailyMenuJoin;

@Dao
public interface MealDailyMenuJoinDao {

    @Insert
    void insert(MealDailyMenuJoin mealDailyMenuJoin);

    @Update
    void update(MealDailyMenuJoin mealDailyMenuJoin);

    @Delete
    void delete(MealDailyMenuJoin mealDailyMenuJoin);
}
