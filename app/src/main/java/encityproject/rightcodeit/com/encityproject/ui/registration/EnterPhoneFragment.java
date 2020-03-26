package encityproject.rightcodeit.com.encityproject.ui.registration;


import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
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
import java.util.Scanner;

import encityproject.rightcodeit.com.encityproject.R;
import encityproject.rightcodeit.com.encityproject.ui.news.News;
import encityproject.rightcodeit.com.encityproject.ui.news.NewsAdapter;

/**
 * A simple {@link Fragment} subclass.
 */
public class EnterPhoneFragment extends Fragment {

    private int port = 4656;
    //private String ip = "192.168.1.46";
    private String ip = "35.232.178.112";
    private Button btnEnterPhoneNext;
    private EditText etPhone;

    public EnterPhoneFragment() {
        // Required empty public constructor
    }

    private boolean checkPhone(String strPhone){
        if(strPhone.length()==9){
            if(strPhone.startsWith("50") ||
                    strPhone.startsWith("66") ||
                    strPhone.startsWith("99") ||
                    strPhone.startsWith("95") ||
                    strPhone.startsWith("39") ||
                    strPhone.startsWith("67") ||
                    strPhone.startsWith("68") ||
                    strPhone.startsWith("96") ||
                    strPhone.startsWith("97") ||
                    strPhone.startsWith("98") ||
                    strPhone.startsWith("63") ||
                    strPhone.startsWith("93") ||
                    strPhone.startsWith("91") ||
                    strPhone.startsWith("92") ||
                    strPhone.startsWith("94")){
                return true;
            }
            else {
                Toast.makeText(getContext(),"Змініть код мобільного оператора", Toast.LENGTH_SHORT).show();
                return false;
            }
        }
        else {
            Toast.makeText(getContext(),"Невірно вказан номер", Toast.LENGTH_SHORT).show();
            return false;
        }
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
        View v= inflater.inflate(R.layout.fragment_enter_phone, container, false);
        btnEnterPhoneNext=v.findViewById(R.id.btnEnterPhoneNext);
        etPhone = v.findViewById(R.id.etPhone);

        btnEnterPhoneNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(checkPhone(etPhone.getText().toString())){
                    if(isOnline()){
                        SendForRegPhone sendForRegPhone = new SendForRegPhone();
                        sendForRegPhone.execute(etPhone.getText().toString());
                    }
                }
            }
        });

        return  v;
    }

    class SendForRegPhone extends AsyncTask<String, Void, Socket> {
        private String linkCheckVApp = "myNull";
        private Socket socket;
        private PrintWriter pw = null;
        private InputStream is = null;
        private String fromServer="";

        @Override
        protected Socket doInBackground(String... params) {

            /*PrintWriter pw;
            try {
                socket = new Socket(ip, port);

                pw = new PrintWriter(new BufferedOutputStream(socket.getOutputStream()));
                pw.write(params[0] + "@.#" + linkCheckVApp + "\n");
                pw.flush();

                is = socket.getInputStream();
                Scanner sc = new Scanner(is);
                fromServer = sc.nextLine();

                is.close();
            } catch (IOException e) {
                //e.printStackTrace();
                Log.d("Ex GetWeather", e.getMessage());
            }
             finally {

            }*/

            return null;
        }

        @Override
        protected void onPostExecute(Socket socket) {
            super.onPostExecute(socket);
            //pbListPhones.setVisibility(View.INVISIBLE);
            fromServer="ok";
            if(fromServer.length()>0){
                NavController navController = Navigation.findNavController(getActivity(), R.id.nav_host_fragment);
                navController.navigate(R.id.nav_enter_sms);
            }
            else{
                Toast.makeText(getContext(), "Виникли технічні помилки. Вже вирішуемо", Toast.LENGTH_SHORT).show();
            }
        }
    }

}