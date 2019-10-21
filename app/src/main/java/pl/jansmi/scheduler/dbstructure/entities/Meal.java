package pl.jansmi.scheduler.dbstructure.entities;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

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

    @NonNull
    public String getId() {
        return id;
    }

    @NonNull
    public String getName() {
        return name;
    }

    public float getFavour() {
        return favour;
    }

    @NonNull
    public String getCategoryId() {
        return categoryId;
    }
}
