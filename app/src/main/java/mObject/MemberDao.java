package mObject;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface MemberDao {
    @Query("SELECT * FROM Member")
    List<Member> getAllMember();

    @Insert
    void insert(Member... members);

//    @Update
//    void update(Member... users);
//    @Delete
//    void delete(Member... users);
}
