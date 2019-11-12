package pl.jansmi.scheduler.dbstructure.entities;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.util.UUID;

import static androidx.room.ForeignKey.CASCADE;

@Entity(tableName = "Meals",
        foreignKeys = @ForeignKey(entity = Category.class,
                                  parentColumns = "id",
                                  childColumns = "categoryId",
                                  onDelete = CASCADE))
public class Meal {

    @PrimaryKey
    @NonNull
    private String id;
    @NonNull
    private String name;
    private float favour;
    @NonNull
    private String categoryId;

    public Meal(@NonNull String id, @NonNull String name, float favour, @NonNull String categoryId) {
        this.id = id;
        this.name = name;
        this.favour = favour;
        this.categoryId = categoryId;
    }

    @Ignore
    public Meal(@NonNull String name, @NonNull String categoryId) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.favour = 1.f;
        this.categoryId = categoryId;
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

    public float getFavour() {
        return favour;
    }

    public void setFavour(float favour) {
        this.favour = favour;
    }

    @NonNull
    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(@NonNull String categoryId) {
        this.categoryId = categoryId;
    }

}
