package encityproject.rightcodeit.com.encityproject.ui.news;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.squareup.picasso.Picasso;
import java.util.ArrayList;
import encityproject.rightcodeit.com.encityproject.R;

public class NewsAdapter extends BaseAdapter {
    private Context ctx;
    LayoutInflater lInflater;
    ArrayList<News> news;

    public NewsAdapter(Context context, ArrayList<News> news) {
        this.ctx = context;
        this.news = news;
        lInflater = (LayoutInflater) ctx
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public News getNews(int position) {
        return ((News) getItem(position));
    }

    // кол-во элементов
    @Override
    public int getCount() {
        return news.size();
    }

    // элемент по позиции
    @Override
    public News getItem(int position) {
        return news.get(position);
    }

    // id по позиции
    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View v = convertView;
        final News oneNews = getNews(position);

        if (v == null) {
            LayoutInflater vi;
            vi = LayoutInflater.from(ctx);
            v = vi.inflate(R.layout.item_news, null);
        }

        TextView tvTitleNews = (TextView) v.findViewById(R.id.tv_title_news);
        TextView tvDateAndTimeNews = (TextView) v.findViewById(R.id.tv_date_and_time_news);
        ImageView ivImgLinkNews = (ImageView) v.findViewById(R.id.iv_item_news);

        tvTitleNews.setText(oneNews.getTitleNews());
        tvDateAndTimeNews.setText(oneNews.getDateAndTimeNews());

        Picasso.get()
                .load(oneNews.getImgLinkNews())
                .resize(800, 600)
                .centerCrop()
                .into(ivImgLinkNews);

        return v;
    }

}
