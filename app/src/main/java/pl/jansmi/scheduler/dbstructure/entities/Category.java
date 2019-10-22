package pl.jansmi.scheduler.dbstructure.entities;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "Categories")
public class Category {

    @PrimaryKey
    @NonNull
    private String id;
    @NonNull
    private String name;
    private int order;

    public Category(@NonNull String id, @NonNull String name, int order) {
        this.id = id;
        this.name = name;
        this.order = order;
    }

    @NonNull
    public String getId() {
        return id;
    }

    @NonNull
    public String getName() {
        return name;
    }

    public int getOrder() {
        return order;
    }

}
