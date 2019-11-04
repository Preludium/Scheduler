package pl.jansmi.scheduler.dbstructure.daos;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Update;

import pl.jansmi.scheduler.dbstructure.relations.TagArrangementJoin;

@Dao
public interface TagArrangementJoinDao {

    @Insert
    void insert(TagArrangementJoin tagArrangementJoin);

    @Update
    void update(TagArrangementJoin tagArrangementJoin);

    @Delete
    void delete(TagArrangementJoin tagArrangementJoin);

}
