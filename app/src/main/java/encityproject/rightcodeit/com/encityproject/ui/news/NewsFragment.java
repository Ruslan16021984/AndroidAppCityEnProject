package encityproject.rightcodeit.com.encityproject.ui.news;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import encityproject.rightcodeit.com.encityproject.R;

public class NewsFragment extends Fragment {
    private ArrayOfNews arrayOfNews;
    private NewsAdapter newsAdapter;

    @Nullable
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_news, container, false);
        arrayOfNews = new ArrayOfNews();
        newsAdapter = new NewsAdapter(getActivity(), arrayOfNews.getArrayOfNews());
        ListView listView = v.findViewById(R.id.lv_news);
        listView.setAdapter(newsAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //создать диалог
                String oneNews = arrayOfNews.getArrayOfNews().get(position).getTextNews();

                Bundle bundle = new Bundle();
                bundle.putString("singleNews",
                        arrayOfNews.getArrayOfNews().get(position).getTitleNews() + "@" +
                                arrayOfNews.getArrayOfNews().get(position).getDateAndTimeNews() + "@" +
                                arrayOfNews.getArrayOfNews().get(position).getTextNews() + "@" +
                                arrayOfNews.getArrayOfNews().get(position).getImgLinkNews() + "@" +
                                arrayOfNews.getArrayOfNews().get(position).getLinkSite());

                NavController navController = Navigation.findNavController(getActivity(), R.id.nav_host_fragment);
                navController.navigate(R.id.nav_one_news, bundle);
            }
        });

        return v;
    }
}
