package apps.exam.myapplication.repository.room;

import android.app.Application;

import javax.inject.Inject;

import androidx.room.Room;

/**
 * Created by msaycon on 21,Apr,2020
 */
public class RoomDataImpl implements RoomData {

    private AppDatabase mAppDatabase;

    @Inject
    public RoomDataImpl(Application application) {
        mAppDatabase = Room.databaseBuilder(application,
                AppDatabase.class, "sample-database").build();
    }

    @Override
    public AppDatabase getAppDatabase() {
        return mAppDatabase;
    }

    @Override
    public DatabaseDao getDatabaseDao() {
        return mAppDatabase.getDatabaseDao();
    }
}
