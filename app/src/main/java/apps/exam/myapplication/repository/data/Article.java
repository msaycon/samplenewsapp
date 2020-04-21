package apps.exam.myapplication.repository.data;

/**
 * Created by msaycon on 21,Apr,2020
 */
public class Article {

    public Source source;

    public String title;

    public String description;

    public String url;

    public String urlToImage;

    public String publishedAt;

    public Article(Source source, String title, String description, String url, String urlToImage, String publishedAt) {
        this.source = source;
        this.title = title;
        this.description = description;
        this.url = url;
        this.urlToImage = urlToImage;
        this.publishedAt = publishedAt;
    }
}
