package pl.jansmi.scheduler.dbstructure.relations;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.ForeignKey;

import pl.jansmi.scheduler.dbstructure.entities.Arrangement;
import pl.jansmi.scheduler.dbstructure.entities.Meal;

import static androidx.room.ForeignKey.CASCADE;

@Entity(tableName = "Meal_arrangement_join",
        primaryKeys = {"mealId", "arrangementId"},
        foreignKeys = {@ForeignKey(entity = Meal.class,
                                   parentColumns = "id",
                                   childColumns = "mealId",
                                   onDelete = CASCADE),
                       @ForeignKey(entity = Arrangement.class,
                                   parentColumns = "id",
                                   childColumns = "arrangementId",
                                   onDelete = CASCADE)})
public class MealArrangementJoin {

    @NonNull
    private String mealId;
    @NonNull
    private String arrangementId;
    private int dayNumber;

    public MealArrangementJoin(@NonNull String mealId, @NonNull String arrangementId, int dayNumber) {
        this.mealId = mealId;
        this.arrangementId = arrangementId;
        this.dayNumber = dayNumber;
    }

    @NonNull
    public String getMealId() {
        return mealId;
    }

    @NonNull
    public String getArrangementId() {
        return arrangementId;
    }

    public int getDayNumber() {
        return dayNumber;
    }

}
