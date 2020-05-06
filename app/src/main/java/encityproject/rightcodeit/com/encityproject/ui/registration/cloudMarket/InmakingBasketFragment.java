package encityproject.rightcodeit.com.encityproject.ui.registration.cloudMarket;


import android.graphics.PorterDuff;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
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
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

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
    //private String ip = "192.168.1.46";
    //private String ip = "192.168.1.103";
    private String ip = "35.232.178.112";

    //private InmakingBasketAdapter adapter = null;
    private InmakingBasketAdapterTwoView adapter = null;
    private RecyclerView recyclerView;
    private ArrayList<String> categoryList;
    private Activity activity;
    private Context context;
    private TextView tvNoOrders;
    private ProgressBar pbWorksInBasket;

    private SharedPreferences prefer;
    private SharedPreferences.Editor editor;
    private static final String APP_PREFERENCES = "ensettings";
    private String newOrClosed;
    private String phone;
    private String newSmsForSeller;
    private NavigationView navigationView;
    private Menu menuNav;
    private MenuItem nav;

    @SuppressLint("ValidFragment")
    public InmakingBasketFragment(String newOrClosed) {
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
        View view= inflater.inflate(R.layout.fragment_works_in_basket, container, false);

        activity = getActivity();
        context = getContext();
        pbWorksInBasket=view.findViewById(R.id.pbWorksInBasket);
        pbWorksInBasket.setVisibility(View.VISIBLE);
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
        if(prefer.contains("newsmsforseller")){
            newSmsForSeller=prefer.getString("newsmsforseller","");
           /* editor=prefer.edit();
            editor.putString("newsmsforclient","");
            editor.apply();*/
        }
        else{
            newSmsForSeller="";
        }

        navigationView = getActivity().findViewById(R.id.nav_view);
        menuNav=navigationView.getMenu();
        nav = menuNav.findItem(R.id.nav_work_basket_fragment);

        categoryList = new ArrayList<>();

        if(isOnline()) {
            GetCompanyAllOrders getCompanyAllOrders = new GetCompanyAllOrders();
            getCompanyAllOrders.execute("getcomporder" + "@.#" + prefer.getString("auth", "")
                    + "@.#" + prefer.getString("auth2", "") + "@.#" + prefer.getString("name", "")
                    + "@.#" + phone + "@.#" + newOrClosed);
       /* getCompanyAllOrders.execute("getcomporder"+"@.#"+"0"
                +"@.#"+"0"
                +"@.#"+phone+"@.#"+newOrClosed);*/
        }
        else{
            Toast.makeText(getContext(), "Перевірте інтернет", Toast.LENGTH_SHORT).show();
        }

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
            pbWorksInBasket.setVisibility(View.INVISIBLE);
            if(categoryList!=null && categoryList.size()>0) {
                ArrayList<String> reBackList = new ArrayList<>();
                for(int y=categoryList.size()-1;y>=0;y--){
                    reBackList.add(categoryList.get(y));
                }
                ArrayList<String> al=new ArrayList<>();
                if(newSmsForSeller.length()>0) {
                    String[] ss = newSmsForSeller.split(",");
                    for (int u = 1; u < ss.length; u++) {
                        Log.d("newSMsFor", String.valueOf(u));
                        al.add(ss[u]);
                    }
                }
                adapter = new InmakingBasketAdapterTwoView(context,activity ,reBackList, newOrClosed, al);
                //adapter = new InmakingBasketAdapter(context,activity ,reBackList, newOrClosed);
                recyclerView.setAdapter(adapter);
                /*ArrayList<String> temp = new ArrayList<>();
                for(int e=0;e<categoryList.size();e++){
                    if(categoryList.get(e).split("@.#")[3].equals("wait")){
                        temp.add(categoryList.get(e).split("@.#")[0]);
                    }
                }
                if(prefer.contains("newsmsforseller")){
                    newSmsForSeller=prefer.getString("newsmsforseller","");
                    String[]str=newSmsForSeller.split(",");
                    if(str.length>1 || temp.size()>0){
                        nav.setIcon(R.drawable.workbasket2);
                        nav.getIcon().setColorFilter(getResources().getColor(R.color.red), PorterDuff.Mode.SRC_ATOP);
                        if(str.length>1){
                            nav.setTitle("Моя робота"+" +"+String.valueOf(((str.length-1)+temp.size())));
                        }
                        else {
                            nav.setTitle("Моя робота"+" +"+String.valueOf(temp.size()));
                        }
                    }
                    else{
                        nav.setIcon(R.drawable.workbasket);
                        nav.setTitle("Моя робота");
                    }
                }*/

            }
            else {
                tvNoOrders.setVisibility(View.VISIBLE);
                //Toast.makeText(getContext(), "Спробуйте пізніше", Toast.LENGTH_SHORT).show();
                /*if(prefer.contains("newsmsforseller")){
                    newSmsForSeller=prefer.getString("newsmsforseller","");
                    String[]str=newSmsForSeller.split(",");
                    if(str.length>1){
                        nav.setIcon(R.drawable.workbasket2);
                        nav.getIcon().setColorFilter(getResources().getColor(R.color.red), PorterDuff.Mode.SRC_ATOP);
                        nav.setTitle("Моя робота"+" +"+String.valueOf((str.length-1)));
                    }
                    else{
                        nav.setIcon(R.drawable.workbasket);
                        nav.setTitle("Моя робота");
                    }
                }
                else{
                    nav.setIcon(R.drawable.workbasket);
                    nav.setTitle("Моя робота");
                }*/
            }
        }
    }
}
