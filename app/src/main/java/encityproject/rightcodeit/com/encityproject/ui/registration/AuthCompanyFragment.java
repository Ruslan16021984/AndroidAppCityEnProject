package encityproject.rightcodeit.com.encityproject.ui.registration;


import android.app.AlertDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

import encityproject.rightcodeit.com.encityproject.R;
import encityproject.rightcodeit.com.encityproject.ui.registration.cloudMarket.MessageCommunication.MessageFragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class AuthCompanyFragment extends Fragment {

    private int port = 4656;
    //private String ip = "192.168.1.46";
    //private String ip = "192.168.1.103";
    private String ip = "35.232.178.112";

    private Button btnAuthClose;
    private SharedPreferences prefer;
    private SharedPreferences.Editor editor;
    private static final String APP_PREFERENCES = "ensettings";
    private String authName, authCats, authPhone, authAbout;
    private TextView tvAuthName, tvAuthCats, tvAuthPhone, tvAuthAbout;
    private ImageView ivEditAbout;
    private EditText etAbout;
    private String s1;

    public AuthCompanyFragment() {
        // Required empty public constructor
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
        View v= inflater.inflate(R.layout.fragment_auth_company, container, false);
        prefer=getContext().getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);
        tvAuthName=v.findViewById(R.id.tvAuthName);
        tvAuthCats=v.findViewById(R.id.tvAuthCats);
        tvAuthPhone=v.findViewById(R.id.tvAuthPhone);
        tvAuthAbout=v.findViewById(R.id.tvAuthAbout);
        ivEditAbout=v.findViewById(R.id.ivEditAbout);
        ivEditAbout.setVisibility(View.INVISIBLE);

        editor=prefer.edit();
        s1="";

        if(prefer.contains("addcats") && prefer.contains("auth")){
            ivEditAbout.setVisibility(View.VISIBLE);
            authName=prefer.getString("name","");
            authCats=prefer.getString("addcats", "");
            authPhone=prefer.getString("phone", "");
            authAbout=prefer.getString("about","");
            tvAuthName.setText(authName);
            tvAuthCats.setText(authCats);
            tvAuthPhone.setText("+380"+authPhone);
            tvAuthAbout.setText(authAbout);
            if(prefer.contains("secondphone")){
                tvAuthPhone.setText("+380"+authPhone+"\n"+"+380"+prefer.getString("secondphone","")+"(для клієнтів)");
            }
        }

        ivEditAbout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                View ve = (LinearLayout) getLayoutInflater()
                        .inflate(R.layout.dialog_edit_about, null);

                Button btnConfirmEdit = ve.findViewById(R.id.btnConfirmEdit);
                Button btnConfirmCancel = ve.findViewById(R.id.btnConfirmCancel);
                etAbout = ve.findViewById(R.id.etAbout);
                TextView tvSubText=ve.findViewById(R.id.tvSubText);
                etAbout.setText(authAbout);

                builder
                        .setView(ve)
                        .setCancelable(false)
                ;
                final AlertDialog alert2 = builder.create();

                btnConfirmEdit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if(isOnline()){
                        if(etAbout.length()>5) {
                            alert2.dismiss();
                            s1 = etAbout.getText().toString();
                            s1=s1.replace("\n", "\\n");
                            SendEditAbout sendEditAbout = new SendEditAbout();
                            sendEditAbout.execute("updateabout" + "@.#" + prefer.getString("auth","") + "@.#" +
                                    prefer.getString("auth2","") + "@.#" + s1);
                        }
                        else {
                            tvSubText.setText(tvSubText.getText()+"\n"+"Введіть більше символів");
                        }
                        }
                        else {
                            Toast.makeText(getContext(), "Перевірте інтернет", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

                btnConfirmCancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        alert2.dismiss();
                    }
                });

                alert2.show();
                alert2.setCancelable(false);

            }
        });

        btnAuthClose=v.findViewById(R.id.btnAuthClose);
        btnAuthClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                View ve = (LinearLayout) getLayoutInflater()
                        .inflate(R.layout.dialog_confirm_delete_comp, null);

                Button btnConfirmDelete = ve.findViewById(R.id.btnConfirmDelete);
                Button btnConfirmCancel = ve.findViewById(R.id.btnConfirmCancel);

                builder
                        .setView(ve)
                        .setCancelable(false)
                ;
                final AlertDialog alert2 = builder.create();

                btnConfirmDelete.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        alert2.dismiss();
                        setHasOptionsMenu(true);
                        NavigationView navigationView = getActivity().findViewById(R.id.nav_view);
                        Menu menuNav=navigationView.getMenu();
                        MenuItem nav_reg = menuNav.findItem(R.id.nav_reg);
                        nav_reg.setVisible(true);
                        MenuItem nav_auth = menuNav.findItem(R.id.nav_auth_company_fragment);
                        nav_auth.setVisible(false);
                        MenuItem nav_cloud_market = menuNav.findItem(R.id.nav_entrance_market);
                        nav_cloud_market.setVisible(false);
                        MenuItem nav_basket = menuNav.findItem(R.id.nav_basket_fragment);
                        nav_basket.setVisible(false);
                        MenuItem nav_work_basket = menuNav.findItem(R.id.nav_work_basket_fragment);
                        nav_work_basket.setVisible(false);
                        prefer=getContext().getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);
                        prefer.edit().clear().commit();
                        Toast.makeText(getContext(), "Вашу Компанію деактивовано", Toast.LENGTH_SHORT).show();
                        NavController navController = Navigation.findNavController(getActivity(), R.id.nav_host_fragment);
                        navController.navigate(R.id.nav_weather);
                    }
                });

                btnConfirmCancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        alert2.dismiss();
                    }
                });

                alert2.show();
                alert2.setCancelable(false);

            }
        });
        return v;
    }

    class SendEditAbout extends AsyncTask<String, Void, Socket> {
        private Socket s = null;
        //private String fromServer;
        private PrintWriter pw = null;
        private InputStream is = null;
        private String fromServer;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
           // ivMessageSend.setClickable(false);
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
         //   pbOrdersInBasket.setVisibility(View.INVISIBLE);
            if(fromServer!=null && fromServer.equals("ok")){
                editor.putString("about", etAbout.getText().toString());
                editor.apply();
                tvAuthAbout.setText(prefer.getString("about",""));
            }
            else{
                Toast.makeText(getContext(), "Помилка редагування", Toast.LENGTH_SHORT).show();
            }
        }
    }

}
