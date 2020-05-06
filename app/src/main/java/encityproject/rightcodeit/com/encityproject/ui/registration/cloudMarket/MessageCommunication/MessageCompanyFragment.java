package encityproject.rightcodeit.com.encityproject.ui.registration.cloudMarket.MessageCommunication;


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
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
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
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

import encityproject.rightcodeit.com.encityproject.R;

/**
 * A simple {@link Fragment} subclass.
 */
@SuppressLint("ValidFragment")
public class MessageCompanyFragment extends Fragment {
    private int port = 4656;
    //private String ip = "192.168.1.46";
    //private String ip = "192.168.1.103";
    private String ip = "35.232.178.112";

    //private MessageAdapterCompany adapter = null;
    private SmsAdapterCompany adapter = null;
    private RecyclerView recyclerView;
    private ArrayList<String> categoryList;
    private Activity activity;
    private Context context;
    private TextView tvNoOrders;
    private ProgressBar pbOrdersInBasket;
    private ImageView ivMessageSend;
    private EditText etMessage;
    private Timer timer;

    private SharedPreferences prefer;
    private SharedPreferences.Editor editor;
    private static final String APP_PREFERENCES = "ensettings";
    private String newOrClosed;
    private String numOrder, aboutOrder;
    private int startSms=0;
    private String role;
    private String newSmsForSeller;
    private int forResume=0;

    @SuppressLint("ValidFragment")
    public MessageCompanyFragment() {
    }

    public boolean isOnline() {
        ConnectivityManager cm =
                (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        return netInfo != null && netInfo.isConnectedOrConnecting();
    }

    @Override
    public void onPause() {
        timer.cancel();
        timer=null;
        super.onPause();
    }

    @Override
    public void onResume() {
        timer=new Timer();
        timer.schedule(new ScreenTimerTask(),0,5000);
        super.onResume();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_message_company, container, false);
        prefer=getContext().getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);

        Bundle bundle= getArguments();
        if(bundle!=null){
            numOrder=bundle.getString("numorder","");
            aboutOrder=bundle.getString("aboutorder","");
        }
        Toolbar toolbar = (Toolbar) getActivity().findViewById(R.id.toolbar);
        if(numOrder.length()>8) {
            toolbar.setTitle("Чат замовлення "+ numOrder.substring(5,13));
        }
        else{
            toolbar.setTitle("Чат замовлення "+ numOrder);
        }

        if(prefer.contains("newsmsforseller")){
            newSmsForSeller=prefer.getString("newsmsforseller","");
            String[]str=newSmsForSeller.split(",");
            ArrayList<String> list=new ArrayList<>();
            for(int y=1;y<str.length;y++){
                list.add(str[y]);
            }
            for(int y=0;y<list.size();y++){
                if(list.get(y).equals(numOrder)){
                    list.remove(y);
                    y=y-1;
                }
            }
            String s="";
            for(int t=0; t<list.size();t++){
                s=s+","+list.get(t);
            }
            editor=prefer.edit();
            editor.putString("newsmsforseller",s);
            editor.apply();
        }

        activity = getActivity();
        context = getContext();

        tvNoOrders=view.findViewById(R.id.tvNoOrders2);
        tvNoOrders.setVisibility(View.INVISIBLE);
        ivMessageSend=view.findViewById(R.id.ivMessageSend2);
        etMessage=view.findViewById(R.id.etMessage2);
        pbOrdersInBasket=view.findViewById(R.id.pbOrdersInBasket2);
        pbOrdersInBasket.setVisibility(View.VISIBLE);
        recyclerView =  view.findViewById(R.id.rvNewOrders2);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));

        newOrClosed="nothing";
        prefer=getContext().getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);
        categoryList = new ArrayList<>();


        if(isOnline()) {
            GetMessageFromServer getMessageFromServer = new GetMessageFromServer();
            getMessageFromServer.execute("getmessagecomp" + "@.#" + numOrder + "@.#" + "seller" + "@.#" + prefer.getString("auth", ""));

            ivMessageSend.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(isOnline()){
                        if(etMessage.length()>0) {
                            if (startSms == 1) {
                                String s = etMessage.getText().toString();
                                s=s.replace("\n", "\\n");
                                SendMessageToServer sendMessageToServer = new SendMessageToServer();
                                //numorder,author,  auth, text
                                sendMessageToServer.execute("sendmessagecomp" + "@.#" + numOrder + "@.#" + "seller" + "@.#" + prefer.getString("auth2", "") + "@.#" +
                                        s);
                            }
                        }
                    }
                    else{
                        Toast.makeText(getContext(),"Перевірте інтернет", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
        else{
            Toast.makeText(getContext(), "Перевірте інтернет", Toast.LENGTH_SHORT).show();
        }


        return view;
    }

    class GetMessageFromServer extends AsyncTask<String, Void, Socket> {
        private Socket s = null;
        //private String fromServer;
        private PrintWriter pw = null;
        private InputStream is = null;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            ivMessageSend.setClickable(false);
        }

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
                tvNoOrders.setVisibility(View.INVISIBLE);
                // numorder, author, messagetext, photolink, createdate
             //   categoryList.add(0,aboutOrder);
                categoryList.add(0,"fff"+"@.#"+"client"+"@.#"+aboutOrder);
              //  adapter = new MessageAdapterCompany(context,activity ,categoryList, newOrClosed);
                adapter = new SmsAdapterCompany(context,activity ,categoryList, newOrClosed);
                recyclerView.setAdapter(adapter);
                recyclerView.smoothScrollToPosition(categoryList.size()-1);
            }
            else {
              //  categoryList.add(0,aboutOrder);
                categoryList.add(0,"fff"+"@.#"+"client"+"@.#"+aboutOrder);
                //adapter = new MessageAdapterCompany(context,activity ,categoryList, newOrClosed);
                adapter = new SmsAdapterCompany(context,activity ,categoryList, newOrClosed);
                recyclerView.setAdapter(adapter);
                recyclerView.smoothScrollToPosition(categoryList.size()-1);
                //tvNoOrders.setVisibility(View.VISIBLE);
                //Toast.makeText(getContext(), "Спробуйте пізніше", Toast.LENGTH_SHORT).show();
            }
            startSms=1;
            ivMessageSend.setClickable(true);
            forResume=1;
        }
    }

    class SendMessageToServer extends AsyncTask<String, Void, Socket> {
        private Socket s = null;
        //private String fromServer;
        private PrintWriter pw = null;
        private InputStream is = null;
        private String fromServer;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            ivMessageSend.setClickable(false);
        }

        @Override
        protected Socket doInBackground(String... params) {
            try {
                s = new Socket(ip, port);
                pw = new PrintWriter(new BufferedOutputStream(s.getOutputStream()));
                pw.write(params[0] + "\n");
                pw.flush();
                is = s.getInputStream();
                Scanner sc = new Scanner(is);
                fromServer = sc.nextLine();
                is.close();
                /*ObjectInputStream objectInput = new ObjectInputStream(s.getInputStream());
                try {
                    Object object = objectInput.readUnshared();
                    categoryList =  (ArrayList<String>) object;

                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
                objectInput.close();*/
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Socket socket) {
            super.onPostExecute(socket);
            pbOrdersInBasket.setVisibility(View.INVISIBLE);
            if(fromServer!=null && fromServer.equals("ok")){
                etMessage.setText("");
                GetMessageFromServer getMessageFromServer = new GetMessageFromServer();
                getMessageFromServer.execute("getmessagecomp"+"@.#"+numOrder+"@.#"+"seller"+"@.#"+prefer.getString("auth", ""));
            }
            startSms=1;
            ivMessageSend.setClickable(true);
        }
    }

    class GetMessageFromServerUpdate extends AsyncTask<String, Void, Socket> {
        private Socket s = null;
        //private String fromServer;
        private PrintWriter pw = null;
        private InputStream is = null;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            ivMessageSend.setClickable(false);
        }

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
                tvNoOrders.setVisibility(View.INVISIBLE);
                // numorder, author, messagetext, photolink, createdate
                //   categoryList.add(0,aboutOrder);
                categoryList.add(0,"fff"+"@.#"+"client"+"@.#"+aboutOrder);
                //  adapter = new MessageAdapterCompany(context,activity ,categoryList, newOrClosed);
                adapter.setCaList(categoryList);
                adapter.notifyDataSetChanged();
            }
            else {
                //  categoryList.add(0,aboutOrder);
                categoryList.add(0,"fff"+"@.#"+"client"+"@.#"+aboutOrder);
                //adapter = new MessageAdapterCompany(context,activity ,categoryList, newOrClosed);
                adapter.setCaList(categoryList);
                adapter.notifyDataSetChanged();

                //tvNoOrders.setVisibility(View.VISIBLE);
                //Toast.makeText(getContext(), "Спробуйте пізніше", Toast.LENGTH_SHORT).show();
            }
            startSms=1;
            ivMessageSend.setClickable(true);
        }
    }



    private class ScreenTimerTask extends TimerTask {

        @Override
        public void run() {
            if (isOnline()) {
                if(forResume==1) {
                    GetMessageFromServerUpdate getMessageFromServerUpdate = new GetMessageFromServerUpdate();
                    getMessageFromServerUpdate.execute("getmessagecomp" + "@.#" + numOrder + "@.#" + "seller" + "@.#" + prefer.getString("auth", ""));
                }
            } else {
                Toast.makeText(getContext(), "Перевірте інтернет", Toast.LENGTH_SHORT).show();


            }
        }
    }
}
