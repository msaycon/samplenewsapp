package apps.exam.myapplication.repository.room;

/**
 * Created by msaycon on 21,Apr,2020
 */
public interface RoomData {

    AppDatabase getAppDatabase();

    DatabaseDao getDatabaseDao();
}
