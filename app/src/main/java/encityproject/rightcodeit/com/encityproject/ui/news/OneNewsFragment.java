package encityproject.rightcodeit.com.encityproject.ui.news;

import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.squareup.picasso.Picasso;

import encityproject.rightcodeit.com.encityproject.R;

import static android.text.Layout.JUSTIFICATION_MODE_INTER_WORD;

public class OneNewsFragment extends Fragment {
    private TextView tvTextOneNews;
    private TextView tvTitleOneNews;
    private TextView tvDateAndTimeOneNews;
    private ImageView ivOneNews;
    private TextView tvLinkSite;

    public OneNewsFragment(){
    }

    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_one_news, container, false);
        tvTitleOneNews = v.findViewById(R.id.tv_title_one_news);
        tvDateAndTimeOneNews= (TextView) v.findViewById(R.id.tv_date_and_time_one_news);
        tvTextOneNews = (TextView) v.findViewById(R.id.tv_text_one_news);
        ivOneNews = (ImageView) v.findViewById(R.id.iv_item_one_news);
        tvLinkSite = v.findViewById(R.id.tv_link_one_news);

        Bundle bundle = this.getArguments();

        if (bundle != null) {
            String fromBundle = bundle.getString("singleNews");
            tvTitleOneNews.setText(fromBundle.split("@")[0]);
            tvDateAndTimeOneNews.setText(fromBundle.split("@")[1]);
            tvTextOneNews.setText(fromBundle.split("@")[2]);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                tvTextOneNews.setJustificationMode(JUSTIFICATION_MODE_INTER_WORD);
            }
            String imgPathOneNews = fromBundle.split("@")[3];
                        Picasso.get()
                    .load(imgPathOneNews)
                    .resize(200, 200)
                    .centerCrop()
                    .into(ivOneNews);
            tvLinkSite.setText("Джерело - "+fromBundle.split("@")[4]);
        }
        return v;
    }
}
