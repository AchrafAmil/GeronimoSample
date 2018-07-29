package com.neogineer.geronimo.geronimosample.data;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;

import com.neogineer.geronimo.geronimosample.AppExecutors;
import com.neogineer.geronimo.geronimosample.MainActivity;
import com.neogineer.geronimo.geronimosample.Utils;

import java.util.List;

/**
 * Created by AchrafAmil (@neogineer) on 28/07/2018.
 */
@Database(entities = {King.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase{

    private static final String LOG_TAG = AppDatabase.class.getSimpleName();
    private static final Object LOCK = new Object();
    private static final String DATABASE_NAME = "kings";
    private static AppDatabase sInstance;

    public static AppDatabase getInstance(Context context) {
        if (sInstance == null) {
            synchronized (LOCK) {
                Log.d(LOG_TAG, "Creating new database instance");
                sInstance = Room.databaseBuilder(context.getApplicationContext(),
                        AppDatabase.class, AppDatabase.DATABASE_NAME)
                        .addCallback(new Callback() {
                            @Override
                            public void onCreate(@NonNull SupportSQLiteDatabase db) {
                                super.onCreate(db);
                                AppExecutors.getInstance().diskIO().execute(() -> {
                                    List<King> kingsToAdd = Utils.getHardcodedList();
                                    getInstance(context).kingDao().insertAllKings( kingsToAdd.toArray(new King[kingsToAdd.size()]));
                                        });
                            }
                        })
                        .build();
            }
        }
        Log.d(LOG_TAG, "Getting the database instance");
        return sInstance;
    }



    public abstract KingDao kingDao();
}
