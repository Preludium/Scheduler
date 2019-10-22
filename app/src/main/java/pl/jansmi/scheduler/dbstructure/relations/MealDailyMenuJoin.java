package pl.jansmi.scheduler.dbstructure.relations;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.ForeignKey;

import pl.jansmi.scheduler.dbstructure.entities.DailyMenu;
import pl.jansmi.scheduler.dbstructure.entities.Meal;

@Entity(tableName = "Meal_daily_menu_join",
        foreignKeys = {@ForeignKey(entity = Meal.class,
                                   parentColumns = "id",
                                   childColumns = "mealId"),
                       @ForeignKey(entity = DailyMenu.class,
                                   parentColumns = "id",
                                   childColumns = "dailyMenuId")})
public class MealDailyMenuJoin {

    @NonNull
    private String mealId;
    @NonNull
    private String dailyMenuId;

    public MealDailyMenuJoin(@NonNull String mealId, @NonNull String dailyMenuId) {
        this.mealId = mealId;
        this.dailyMenuId = dailyMenuId;
    }

    @NonNull
    public String getMealId() {
        return mealId;
    }

    @NonNull
    public String getDailyMenuId() {
        return dailyMenuId;
    }
}
