package com.neogineer.geronimo.geronimosample.data;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

/**
 * Created by AchrafAmil (@neogineer) on 28/07/2018.
 */

@Dao
public interface KingDao {

    @Query("SELECT * FROM king")
    LiveData<List<King>> loadAllKings();

    @Insert
    void insertKing(King king);

    @Insert
    void insertAllKings(King... kings);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void updateKing(King king);

    @Delete
    void deleteKing(King king);
}
