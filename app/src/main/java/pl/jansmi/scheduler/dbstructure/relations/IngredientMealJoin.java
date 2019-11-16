package pl.jansmi.scheduler.dbstructure.relations;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.ForeignKey;

import pl.jansmi.scheduler.dbstructure.entities.Ingredient;
import pl.jansmi.scheduler.dbstructure.entities.Meal;

import static androidx.room.ForeignKey.CASCADE;

@Entity(tableName = "Ingredient_meal_join",
        primaryKeys = {"ingredientId", "mealId"},
        foreignKeys = {@ForeignKey(entity = Ingredient.class,
                                   parentColumns = "id",
                                   childColumns = "ingredientId",
                                   onDelete = CASCADE),
                       @ForeignKey(entity = Meal.class,
                                   parentColumns = "id",
                                   childColumns = "mealId",
                                   onDelete = CASCADE)})
public class IngredientMealJoin {

    @NonNull
    private String ingredientId;
    @NonNull
    private String mealId;
    private int quantity;

    public IngredientMealJoin(@NonNull String ingredientId, @NonNull String mealId, int quantity) {
        this.ingredientId = ingredientId;
        this.mealId = mealId;
        this.quantity = quantity;
    }

    @NonNull
    public String getIngredientId() {
        return ingredientId;
    }

    @NonNull
    public String getMealId() {
        return mealId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
