package apps.exam.myapplication.repository.room;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import apps.exam.myapplication.repository.room.entities.LocalArticle;

/**
 * Created by msaycon on 21,Apr,2020
 */
@Database(
        entities = {LocalArticle.class},
        version = 1,
        exportSchema = false
)
public abstract class AppDatabase extends RoomDatabase {
    public abstract DatabaseDao getDatabaseDao();
}
