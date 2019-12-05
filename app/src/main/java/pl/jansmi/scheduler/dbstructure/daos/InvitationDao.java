package pl.jansmi.scheduler.dbstructure.daos;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import pl.jansmi.scheduler.dbstructure.entities.Invitation;

@Dao
public interface InvitationDao {

    @Query("SELECT * FROM Invitations WHERE fromId = :id OR toId = :id")
    List<Invitation> getByUserId(String id);

    @Query("SELECT * FROM Invitations WHERE toId = :id")
    List<Invitation> getReceivedByUserId(String id);

    @Query("SELECT * FROM Invitations WHERE fromId = :id")
    List<Invitation> getSentByUserId(String id);

    @Insert
    void insert(Invitation invitation);

    @Update
    void update(Invitation invitation);

    @Delete
    void delete(Invitation invitation);
}
