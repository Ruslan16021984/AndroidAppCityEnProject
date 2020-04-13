package encityproject.rightcodeit.com.encityproject.ui.registration.cloudMarket;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import encityproject.rightcodeit.com.encityproject.R;

/**
 * A simple {@link Fragment} subclass.
 */


import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;

import encityproject.rightcodeit.com.encityproject.R;

/**
 * A simple {@link Fragment} subclass.
 */
@SuppressLint("ValidFragment")
public class InmakingBasketFragment extends Fragment {
    private int port = 4656;
    private String ip = "192.168.1.46";
    //private String ip = "192.168.1.103";
    //private String ip = "35.232.178.112";

    private InmakingBasketAdapter adapter = null;
    private RecyclerView recyclerView;
    private ArrayList<String> categoryList;
    private Activity activity;
    private Context context;
    private TextView tvNoOrders;

    private SharedPreferences prefer;
    private SharedPreferences.Editor editor;
    private static final String APP_PREFERENCES = "ensettings";
    private String newOrClosed;
    private String phone;


    @SuppressLint("ValidFragment")
    public InmakingBasketFragment(String newOrClosed) {
        this.newOrClosed=newOrClosed;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_works_in_basket, container, false);

        activity = getActivity();
        context = getContext();
        tvNoOrders=view.findViewById(R.id.tvNoOrders);
        tvNoOrders.setVisibility(View.INVISIBLE);
        recyclerView =  view.findViewById(R.id.rvNewOrders);


        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));

        prefer=getContext().getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);

        if(prefer.contains("secondphone")){
            phone=prefer.getString("secondphone","");
        }
        else{
            phone=prefer.getString("phone","");

        }

        categoryList = new ArrayList<>();

        GetCompanyAllOrders getCompanyAllOrders = new GetCompanyAllOrders();
        getCompanyAllOrders.execute("getcomporder"+"@.#"+prefer.getString("auth", "")
                +"@.#"+prefer.getString("auth2", "")
                +"@.#"+phone+"@.#"+newOrClosed);
       /* getCompanyAllOrders.execute("getcomporder"+"@.#"+"0"
                +"@.#"+"0"
                +"@.#"+phone+"@.#"+newOrClosed);*/


        return view;
    }

    class GetCompanyAllOrders extends AsyncTask<String, Void, Socket> {
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
            //numorder, description, telclient, status, createdate, enddate
            if(categoryList!=null && categoryList.size()>0) {
                adapter = new InmakingBasketAdapter(context,activity ,categoryList, newOrClosed);
                recyclerView.setAdapter(adapter);

            }
            else {
                tvNoOrders.setVisibility(View.VISIBLE);
                //Toast.makeText(getContext(), "Спробуйте пізніше", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
