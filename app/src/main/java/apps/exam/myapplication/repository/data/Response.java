package apps.exam.myapplication.repository.data;

import java.util.ArrayList;

/**
 * Created by msaycon on 21,Apr,2020
 */
public class Response {

    public ArrayList<Article> articles;

    public Response(ArrayList<Article> articles) {
        this.articles = articles;
    }
}
