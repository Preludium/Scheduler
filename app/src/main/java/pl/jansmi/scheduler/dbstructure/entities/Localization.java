package pl.jansmi.scheduler.dbstructure.entities;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "Localizations")
public class Localization {

    @PrimaryKey
    @NonNull
    private String id;

    @NonNull
    private String name;

    private double longtitude;

    private double latitude;

    private float favour;

}
