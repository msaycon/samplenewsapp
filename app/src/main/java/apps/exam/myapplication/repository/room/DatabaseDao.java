package apps.exam.myapplication.repository.room;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import apps.exam.myapplication.repository.room.entities.LocalArticle;
import io.reactivex.Completable;
import io.reactivex.Flowable;

/**
 * Created by msaycon on 21,Apr,2020
 */
@Dao
public interface DatabaseDao {

    @Query("SELECT * FROM article")
    Flowable<List<LocalArticle>> getAllArticle();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Completable insertLocalArticle(LocalArticle localArticle);
}
