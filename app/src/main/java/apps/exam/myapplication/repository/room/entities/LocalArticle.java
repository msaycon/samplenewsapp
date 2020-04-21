package apps.exam.myapplication.repository.room.entities;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

/**
 * Created by msaycon on 21,Apr,2020
 */

@Entity(tableName = "article")
public class LocalArticle {

    @PrimaryKey
    @NonNull
    public String uid;

    @ColumnInfo(name = "title")
    public String title;

    @ColumnInfo(name = "description")
    public String description;

    @ColumnInfo(name = "url")
    public String url;

    @ColumnInfo(name = "urlToImage")
    public String urlToImage;

    @ColumnInfo(name = "publishedAt")
    public String publishedAt;

    public LocalArticle(@NonNull String uid, String title, String description, String url, String urlToImage, String publishedAt) {
        this.uid = uid;
        this.title = title;
        this.description = description;
        this.url = url;
        this.urlToImage = urlToImage;
        this.publishedAt = publishedAt;
    }
}
