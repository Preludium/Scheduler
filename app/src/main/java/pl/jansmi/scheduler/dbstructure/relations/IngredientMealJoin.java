package pl.jansmi.scheduler.dbstructure.relations;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.ForeignKey;

import pl.jansmi.scheduler.dbstructure.entities.Ingredient;
import pl.jansmi.scheduler.dbstructure.entities.Meal;

@Entity(tableName = "Ingredient_meal_join",
        primaryKeys = {"ingredientId", "mealId"},
        foreignKeys = {@ForeignKey(entity = Ingredient.class,
                                   parentColumns = "id",
                                   childColumns = "ingredientId"),
                       @ForeignKey(entity = Meal.class,
                                   parentColumns = "id",
                                   childColumns = "mealId")})
public class IngredientMealJoin {

    @NonNull
    private String ingredientId;
    @NonNull
    private String mealId;
    private float amount;

    public IngredientMealJoin(@NonNull String ingredientId, @NonNull String mealId, float amount) {
        this.ingredientId = ingredientId;
        this.mealId = mealId;
        this.amount = amount;
    }

    @NonNull
    public String getIngredientId() {
        return ingredientId;
    }

    @NonNull
    public String getMealId() {
        return mealId;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }
}
