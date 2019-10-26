package pl.jansmi.scheduler.dbstructure.entities;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.util.UUID;

@Entity(tableName = "Ingredients")
public class Ingredient {

    @Ignore
    public static final String UNIT_NONE = "pcs.";
    @Ignore
    public static final String UNIT_G = "g";
    @Ignore
    public static final String UNIT_ML = "ml";

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

    @Ignore
    public Ingredient(@NonNull String name) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.quantity = 1;
        this.unit = UNIT_NONE;
        this.kcal = 0;
        this.favour = 1.f;
    }

    @NonNull
    public String getId() {
        return id;
    }

    @NonNull
    public String getName() {
        return name;
    }

    public void setName(@NonNull String name) {
        this.name = name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public int getKcal() {
        return kcal;
    }

    public void setKcal(int kcal) {
        this.kcal = kcal;
    }

    public float getFavour() {
        return favour;
    }

    public void setFavour(float favour) {
        this.favour = favour;
    }
}
