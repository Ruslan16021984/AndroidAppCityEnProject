package encityproject.rightcodeit.com.encityproject.ui.registration.cloudMarket;


import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;

import encityproject.rightcodeit.com.encityproject.R;
import encityproject.rightcodeit.com.encityproject.ui.registration.CatChoiceAdapter;

/**
 * A simple {@link Fragment} subclass.
 */
@SuppressLint("ValidFragment")
public class CurrentCatFragment extends Fragment {

    private int port = 4656;
    private String ip = "192.168.1.46";
    //private String ip = "192.168.1.103";
    //private String ip = "35.232.178.112";
    private String currCopm;
    private TextView tvSingleNameComp, tvAboutCur, tvPhoneCur;
    private ImageView ivCall;
    private Button btnBuy;
    private SharedPreferences prefer;
    private SharedPreferences.Editor editor;
    private static final String APP_PREFERENCES = "ensettings";


    public CurrentCatFragment() {
    }

//companyname, about, phone

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View v= inflater.inflate(R.layout.fragment_current_cat, container, false);
        prefer=getContext().getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);

        Bundle bundle =getArguments();
        currCopm=bundle.getString("curcomp","");

        Toolbar toolbar = (Toolbar) getActivity().findViewById(R.id.toolbar);
        toolbar.setTitle(bundle.getString("cat","").split("@.#")[1]);

        ivCall=v.findViewById(R.id.imageViewCall);
        tvSingleNameComp =v.findViewById(R.id.tvSingleNameComp);
        btnBuy=v.findViewById(R.id.btnBuy);
        tvAboutCur=v.findViewById(R.id.tvAboutCur);
        tvPhoneCur=v.findViewById(R.id.tvPhoneCur);

        tvSingleNameComp.setText(currCopm.split("@.#")[0]);
        tvAboutCur.setText(currCopm.split("@.#")[1]);
        tvPhoneCur.setText(currCopm.split("@.#")[2]);

        ivCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse
                        ("tel: " + "0"+tvPhoneCur.getText()));
                if (intent != null) {
                    startActivity(intent);
                }
            }
        });

        btnBuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                View ve = (LinearLayout) getLayoutInflater()
                        .inflate(R.layout.dialog_to_order_company, null);

                TextView tvQuickAsk= ve.findViewById(R.id.tvQuickAsk);
                Button btnQuickConfirm = ve.findViewById(R.id.btnQuickConfirm);
                Button btnQuickCancel = ve.findViewById(R.id.btnQuickCancel);
                EditText etQuickOrder= ve.findViewById(R.id.etQuickOrder);

                tvQuickAsk.setText(tvQuickAsk.getText()+currCopm.split("@.#")[0]);

                builder
                        .setView(ve)
                        .setCancelable(false)
                ;
                final AlertDialog alert2 = builder.create();


                btnQuickConfirm.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if(prefer.contains("auth")){
                            if(etQuickOrder.getText().length()>5){
                                alert2.dismiss();
                                SendOrderToCompany sendOrderToCompany = new SendOrderToCompany();
                                sendOrderToCompany.execute("tocomp"+"@.#"+prefer.getString("auth","")+
                                        "@.#"+prefer.getString("phone","")+
                                        "@.#"+currCopm.split("@.#")[2]+
                                        "@.#"+etQuickOrder.getText().toString()
                                        +"@.#"+bundle.getString("cat","").split("@.#")[0]);
                            }
                            else{
                                Toast.makeText(getContext(), "Мінімально 5 символів", Toast.LENGTH_SHORT).show();
                            }
                        }
                        else {
                            tvQuickAsk.setText("Ви не авторизовані. Для можливості робити замовлення пройдіть Найшвидку Авторизацію");
                            tvQuickAsk.setTextColor(Color.RED);
                        }

                    }
                });

                btnQuickCancel.setOnClickListener(new View.OnClickListener() {
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

    class SendOrderToCompany extends AsyncTask<String, Void, Socket> {
        private String linkCheckVApp = "myNull";
        private Socket socket;
        private PrintWriter pw = null;
        private InputStream is = null;
        private String fromServer="";

        @Override
        protected Socket doInBackground(String... params) {

            PrintWriter pw;
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
                Log.d("send order to comp", e.getMessage());
            }
            finally {

            }

            return null;
        }

        @Override
        protected void onPostExecute(Socket socket) {
            super.onPostExecute(socket);
            //pbListPhones.setVisibility(View.INVISIBLE);
            //fromServer="ok";
            if(fromServer.equals("ok")){
                final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                View ve = (LinearLayout) getLayoutInflater().inflate(R.layout.dialog_ordered_to_comp, null);

                Button btn = ve.findViewById(R.id.btnQuickConfirmOK);

                builder
                        .setView(ve)
                        .setCancelable(false)
                ;
                final AlertDialog alert2 = builder.create();

                btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        alert2.dismiss();
                    }
                });

                alert2.show();
                alert2.setCancelable(false);

            }
            else{
                Toast.makeText(getContext(), "Виникли технічні помилки. Вже вирішуемо", Toast.LENGTH_SHORT).show();
            }
        }
    }

}
