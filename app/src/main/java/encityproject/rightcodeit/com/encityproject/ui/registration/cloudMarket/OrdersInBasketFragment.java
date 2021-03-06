package encityproject.rightcodeit.com.encityproject.ui.registration.cloudMarket;


import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;

import encityproject.rightcodeit.com.encityproject.R;
import encityproject.rightcodeit.com.encityproject.ui.market.adapter.inner_adapter.CloudMarketAdapter;
import encityproject.rightcodeit.com.encityproject.ui.market.model.CategoryModel;
import encityproject.rightcodeit.com.encityproject.ui.registration.CatChoiceAdapter;

/**
 * A simple {@link Fragment} subclass.
 */
@SuppressLint("ValidFragment")
public class OrdersInBasketFragment extends Fragment {
    private int port = 4656;
    //private String ip = "192.168.1.46";
    //private String ip = "192.168.1.103";
    private String ip = "35.232.178.112";

    private OrdersInBasketAdapterTwoView adapter = null;
    private RecyclerView recyclerView;
    private ArrayList<String> categoryList;
    private Activity activity;
    private Context context;
    private TextView tvNoOrders;
    private ProgressBar pbOrdersInBasket;

    private SharedPreferences prefer;
    private SharedPreferences.Editor editor;
    private static final String APP_PREFERENCES = "ensettings";
    private String newOrClosed;
    private String newSmsForClient;


    @SuppressLint("ValidFragment")
    public OrdersInBasketFragment(String newOrClosed) {
        this.newOrClosed=newOrClosed;
    }

    public boolean isOnline() {
        ConnectivityManager cm =
                (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        return netInfo != null && netInfo.isConnectedOrConnecting();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_orders_in_basket, container, false);
        // Inflate the layout for this fragment
        activity = getActivity();
        context = getContext();
        tvNoOrders=view.findViewById(R.id.tvNoOrders);
        tvNoOrders.setVisibility(View.INVISIBLE);
        pbOrdersInBasket=view.findViewById(R.id.pbOrdersInBasket);
        pbOrdersInBasket.setVisibility(View.VISIBLE);
        recyclerView =  view.findViewById(R.id.rvNewOrders);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));

        prefer=getContext().getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);
        categoryList = new ArrayList<>();

        if(prefer.contains("newsmsforclient")){
            newSmsForClient=prefer.getString("newsmsforclient","");
           /* editor=prefer.edit();
            editor.putString("newsmsforclient","");
            editor.apply();*/
        }
        else{
            newSmsForClient="";
        }

        if(isOnline()) {
            GetClientAllOrders getClientAllOrders = new GetClientAllOrders();
            getClientAllOrders.execute("getneworderstoclient" + "@.#" + prefer.getString("auth", "") + "@.#" +
                    prefer.getString("phone", "") + "@.#" + newOrClosed);
        }
        else{
            Toast.makeText(getContext(), "Перевірте інтернет", Toast.LENGTH_SHORT).show();
        }
        return view;
    }

    class GetClientAllOrders extends AsyncTask<String, Void, Socket> {
        private Socket s = null;
        //private String fromServer;
        private PrintWriter pw = null;
        private InputStream is = null;

        @Override
        protected Socket doInBackground(String... params) {
            try {
                s = new Socket(ip, port);
                pw = new PrintWriter(new BufferedOutputStream(s.getOutputStream()));
                pw.write(params[0] + "\n");
                pw.flush();
                /*is = s.getInputStream();
                Scanner sc = new Scanner(is);
                fromServer = sc.nextLine();
                is.close();*/
                ObjectInputStream objectInput = new ObjectInputStream(s.getInputStream());
                try {
                    Object object = objectInput.readUnshared();
                    categoryList =  (ArrayList<String>) object;

                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
                objectInput.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Socket socket) {
            super.onPostExecute(socket);
            pbOrdersInBasket.setVisibility(View.INVISIBLE);
            if(categoryList!=null && categoryList.size()>0) {
                ArrayList<String> reBackList = new ArrayList<>();
                for(int y=categoryList.size()-1;y>=0;y--){
                    reBackList.add(categoryList.get(y));
                }
                ArrayList<String> al=new ArrayList<>();
                if(newSmsForClient.length()>0) {
                    String[] ss = newSmsForClient.split(",");
                    for (int u = 1; u < ss.length; u++) {
                        Log.d("newSMsFor", String.valueOf(u));
                        al.add(ss[u]);
                    }
                }
                adapter = new OrdersInBasketAdapterTwoView(context,activity ,reBackList, newOrClosed, al);
                recyclerView.setAdapter(adapter);

            }
            else {
                tvNoOrders.setVisibility(View.VISIBLE);
                //Toast.makeText(getContext(), "Спробуйте пізніше", Toast.LENGTH_SHORT).show();
            }
        }
    }


}
