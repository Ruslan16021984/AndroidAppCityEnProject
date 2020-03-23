package encityproject.rightcodeit.com.encityproject.ui.news;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.net.Socket;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

import encityproject.rightcodeit.com.encityproject.R;
import encityproject.rightcodeit.com.encityproject.ui.discount.Discount;
import encityproject.rightcodeit.com.encityproject.ui.discount.DiscountAdapter;
import encityproject.rightcodeit.com.encityproject.ui.phonesBook.PhonesFragment;

import static org.apache.commons.lang3.StringUtils.split;

public class NewsFragment extends Fragment {
    private ArrayOfNews arrayOfNews;
    private NewsAdapter newsAdapter;
    private int port = 4656;
    //private String ip = "192.168.1.46";
    private String ip = "35.232.178.112";
    private ArrayList<News> lNwews;
    private ListView listView;
    private Handler h;
    private ProgressBar pb;
    private TextView tvFNews;


    public boolean isOnline() {
        ConnectivityManager cm =
                (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        return netInfo != null && netInfo.isConnectedOrConnecting();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        h.removeCallbacksAndMessages(null);
    }

    @Override
    public void onPause() {
        super.onPause();
        h.removeCallbacksAndMessages(null);
    }

    @Override
    public void onResume() {
        super.onResume();
        lNwews = new ArrayList<>();
        listView.setVisibility(View.INVISIBLE);
        h = new Handler();
        h.post(new Runnable() {
            @Override
            public void run() {
                if(isOnline()){
                    tvFNews.setVisibility(View.INVISIBLE);
                    GetNews getNews = new GetNews();
                    getNews.execute("getNews");
                }
                else {
                    tvFNews.setVisibility(View.VISIBLE);
                    h.postDelayed(this,5000);
                }
            }
        });


    }

    @Nullable
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_news, container, false);

        tvFNews=v.findViewById(R.id.tvFNews);
        tvFNews.setVisibility(View.INVISIBLE);
        pb=v.findViewById(R.id.pbListNews);
        pb.setVisibility(View.VISIBLE);
        lNwews = new ArrayList<>();
        listView = v.findViewById(R.id.lv_news);

//        arrayOfNews = new ArrayOfNews();
     /*   newsAdapter = new NewsAdapter(getActivity(), arrayOfNews.getArrayOfNews());
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
*/
        return v;
    }

    class GetNews extends AsyncTask<String, Void, Socket> {
        private ArrayList<String> listNews;
        private String linkCheckVApp = "myNull";
        private Socket socket;

        @Override
        protected Socket doInBackground(String... params) {
            listNews = new ArrayList<>();

            PrintWriter pw;
            try {
                socket = new Socket(ip, port);

                pw = new PrintWriter(new BufferedOutputStream(socket.getOutputStream()));
                pw.write(params[0] + "@.#" + linkCheckVApp + "\n");
                pw.flush();

                ObjectInputStream objectInput = new ObjectInputStream(socket.getInputStream());
                Object object = objectInput.readUnshared();
                listNews = (ArrayList<String>) object;
                Log.d("check size ", String.valueOf(listNews.size()));
           /*     try {

                } catch (ClassNotFoundException e) {
                    //e.printStackTrace();
                }*/
                objectInput.close();

            } catch (IOException e) {
                //e.printStackTrace();
                Log.d("Ex GetWeather", e.getMessage());
            } catch (ClassNotFoundException e) {
                //e.printStackTrace();
                Log.d("Ex GetWeather", e.getMessage());
            } finally {

            }

//head0, body1, datenews2, photo3, linknews4,
            /*private String titleNews;
            private String dateAndTimeNews;
            private String textNews;
            private String imgLinkNews;
            private String LinkSite;*/
            for(int i=listNews.size()-1;i>=0;i--){
                //Log.d("DISC ", listDisc.get(i));
                lNwews.add(new News(listNews.get(i).split("#.#")[0],listNews.get(i).split("#.#")[2],
                        listNews.get(i).split("#.#")[1],listNews.get(i).split("#.#")[3],listNews.get(i).split("#.#")[4]));
            }
//data111,43,23
            return null;
        }

        @Override
        protected void onPostExecute(Socket socket) {
            super.onPostExecute(socket);
            //pbListPhones.setVisibility(View.INVISIBLE);
            pb.setVisibility(View.INVISIBLE);
            listView.setVisibility(View.VISIBLE);
            if(lNwews.size()>0){
                newsAdapter = new NewsAdapter(getActivity(), lNwews);
                listView.setAdapter(newsAdapter);

                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        //создать диалог
                        String oneNews = lNwews.get(position).getTextNews();

                        Bundle bundle = new Bundle();
                        bundle.putString("singleNews",
                                lNwews.get(position).getTitleNews() + "@" +
                                        lNwews.get(position).getDateAndTimeNews() + "@" +
                                        lNwews.get(position).getTextNews() + "@" +
                                        lNwews.get(position).getImgLinkNews() + "@" +
                                        lNwews.get(position).getLinkSite());

                        NavController navController = Navigation.findNavController(getActivity(), R.id.nav_host_fragment);
                        navController.navigate(R.id.nav_one_news, bundle);
                    }
                });

            }
            else{
                Toast.makeText(getContext(), "Виникли технічні помилки. Вже вирішуемо", Toast.LENGTH_SHORT).show();
            }
        }
    }

}
