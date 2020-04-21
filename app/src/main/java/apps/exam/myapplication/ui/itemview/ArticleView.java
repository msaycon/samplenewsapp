package apps.exam.myapplication.ui.itemview;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import apps.exam.myapplication.R;
import apps.exam.myapplication.helpers.DateHelper;
import apps.exam.myapplication.repository.data.Article;
import butterknife.BindView;
import butterknife.ButterKnife;
import eu.davidea.flexibleadapter.FlexibleAdapter;
import eu.davidea.flexibleadapter.items.IFlexible;
import eu.davidea.viewholders.FlexibleViewHolder;

/**
 * Created by msaycon on 21,Apr,2020
 */
public class ArticleView implements IFlexible<ArticleView.ViewHolder> {

    public Article article;

    public String uid;

    public ArticleView(Article article) {
        this.article = article;

        if (article.source.id == null) {
            this.uid = article.source.name.toLowerCase();
        } else {
            this.uid = article.source.id;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ArticleView that = (ArticleView) o;
        return uid.equals(that.uid);
    }

    @Override
    public int hashCode() {
        return Objects.hash(uid);
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public void setEnabled(boolean enabled) {

    }

    @Override
    public boolean isHidden() {
        return false;
    }

    @Override
    public void setHidden(boolean hidden) {

    }

    @Override
    public int getSpanSize(int spanCount, int position) {
        return 0;
    }

    @Override
    public boolean shouldNotifyChange(IFlexible newItem) {
        return false;
    }

    @Override
    public boolean isSelectable() {
        return true;
    }

    @Override
    public void setSelectable(boolean selectable) {

    }

    @Override
    public String getBubbleText(int position) {
        return null;
    }

    @Override
    public boolean isDraggable() {
        return false;
    }

    @Override
    public void setDraggable(boolean draggable) {

    }

    @Override
    public boolean isSwipeable() {
        return false;
    }

    @Override
    public void setSwipeable(boolean swipeable) {

    }

    @Override
    public int getItemViewType() {
        return 0;
    }

    @Override
    public int getLayoutRes() {
        return R.layout.item_article;
    }

    @Override
    public ViewHolder createViewHolder(View view, FlexibleAdapter<IFlexible> adapter) {
        return new ViewHolder(view, adapter);
    }

    @Override
    public void bindViewHolder(FlexibleAdapter<IFlexible> adapter, ViewHolder holder, int position, List<Object> payloads) {
        Glide.with(holder.itemView.getContext())
                .load(article.urlToImage)
                .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
                .into(holder.contentImage);

        holder.title.setText(article.title);

        if (article.description != null) {
            holder.content.setText(article.description);
        }

        Date publishedDate = DateHelper.getDate(article.publishedAt);
        holder.publishDate.setText(DateHelper.getPublishedDate(publishedDate));
    }

    @Override
    public void unbindViewHolder(FlexibleAdapter<IFlexible> adapter, ViewHolder holder, int position) {

    }

    @Override
    public void onViewAttached(FlexibleAdapter<IFlexible> adapter, ViewHolder holder, int position) {

    }

    @Override
    public void onViewDetached(FlexibleAdapter<IFlexible> adapter, ViewHolder holder, int position) {

    }

    public static class ViewHolder extends FlexibleViewHolder {

        @BindView(R.id.title)
        public TextView title;

        @BindView(R.id.content)
        public TextView content;

        @BindView(R.id.contentImage)
        public ImageView contentImage;

        @BindView(R.id.publishDate)
        public TextView publishDate;

        public ViewHolder(View view, FlexibleAdapter adapter) {
            super(view, adapter);
            ButterKnife.bind(this, itemView);
        }
    }

    public static class ViewComparator implements Comparator<IFlexible> {
        @Override
        public int compare(IFlexible iFlexible, IFlexible t1) {
            ArticleView a1 = (ArticleView) iFlexible;
            ArticleView a2 = (ArticleView) t1;

            Date upper = DateHelper.getDate(a1.article.publishedAt);
            Date lower = DateHelper.getDate(a2.article.publishedAt);

            return upper.compareTo(lower);
        }
    }
}
