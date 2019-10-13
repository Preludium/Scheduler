package pl.jansmi.scheduler.dbstructure.entities;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "Ingredients")
public class Ingredient {

    @PrimaryKey
    @NonNull
    private String id;

    @NonNull
    private String name;

    private int quantity;

    private String unit;

    private int kcal;

    private float favour;

    public Ingredient(@NonNull String id, @NonNull String name, int quantity, String unit, int kcal, float favour) {
        this.id = id;
        this.name = name;
        this.quantity = quantity;
        this.unit = unit;
        this.kcal = kcal;
        this.favour = favour;
    }

    @NonNull
    public String getId() {
        return id;
    }

    @NonNull
    public String getName() {
        return name;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getUnit() {
        return unit;
    }

    public int getKcal() {
        return kcal;
    }

    public float getFavour() {
        return favour;
    }
}
