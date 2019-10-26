package pl.jansmi.scheduler.dbstructure.entities;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.util.UUID;

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

    @Ignore
    public Category(@NonNull String name) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.order = 100;
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

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }
}
