package encityproject.rightcodeit.com.encityproject.ui.registration;


import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Spanned;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.BounceInterpolator;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
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
import java.io.ObjectInputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;

import encityproject.rightcodeit.com.encityproject.MainActivity;
import encityproject.rightcodeit.com.encityproject.MainActivityWithNaviDrawer;
import encityproject.rightcodeit.com.encityproject.R;
import encityproject.rightcodeit.com.encityproject.ui.news.News;
import encityproject.rightcodeit.com.encityproject.ui.news.NewsAdapter;

import static android.text.Layout.JUSTIFICATION_MODE_INTER_WORD;

/**
 * A simple {@link Fragment} subclass.
 */
public class EnterPhoneFragment extends Fragment {

    private int port = 4656;
    //private String ip = "192.168.1.46";
    //private String ip = "192.168.1.103";
    private String ip = "35.232.178.112";
    //private String ip ="192.168.0.103";
  //  private String ip2 ="192.168.0.103";
    private Button btnEnterPhoneNext;
    private EditText etPhone;
    private Bundle bundle;
    private String bunStr;
    private ImageView ivRocketEnterPhone;
    private SharedPreferences prefer;
    private static final String APP_PREFERENCES = "ensettings";
    private LinearLayout llOferta;
    private TextView tvOferta;
    private TextView tvZgoda;
    private CheckBox cbZgoda;
    private Button btnCloseOferta;
    private String fromServerOf;
    private ArrayList<String> listOferta;

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
            Toast.makeText(getContext(),"Введіть вірний номер та поставте згоду з умовами Публічної оферти", Toast.LENGTH_SHORT).show();
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
        getActivity().getWindow().setFlags(WindowManager.LayoutParams.FLAG_SECURE, WindowManager.LayoutParams.FLAG_SECURE);

        prefer=getContext().getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);

        if(prefer.contains("auth")){
            Intent myIntent = new Intent(getContext(), MainActivityWithNaviDrawer.class);
            getContext().startActivity(myIntent);
        }

        View v= inflater.inflate(R.layout.fragment_enter_phone, container, false);
        llOferta=v.findViewById(R.id.llOferta);
        llOferta.setVisibility(View.INVISIBLE);
        tvOferta=v.findViewById(R.id.tvOferta);
        tvOferta.setVisibility(View.INVISIBLE);
        cbZgoda=v.findViewById(R.id.cbZgoda);
        tvZgoda=v.findViewById(R.id.tvZgoda);

        Spanned textSpan  =  android.text.Html.fromHtml(tvZgoda.getText().toString());
        tvZgoda.setText(textSpan);

        btnCloseOferta=v.findViewById(R.id.btnCloseOferta);
        btnCloseOferta.setVisibility(View.INVISIBLE);

        tvZgoda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               GetOferta getOferta = new GetOferta();
               getOferta.execute("oferta");
            }
        });

        btnEnterPhoneNext=v.findViewById(R.id.btnEnterPhoneNext);
        etPhone = v.findViewById(R.id.etPhone);

        bundle = getArguments();
        bunStr = bundle.getString("role");
       // bundle.putString("role", bunStr);


        /////////////////////////////////////////////////////////////////////////////
        ivRocketEnterPhone=v.findViewById(R.id.ivRocket_enter_phone);
        ObjectAnimator buttonAnimator = ObjectAnimator.ofFloat(ivRocketEnterPhone, "translationX",0f, 400f);
        buttonAnimator.setDuration(3000);
        buttonAnimator.setInterpolator(new BounceInterpolator());
        buttonAnimator.start();
        /////////////////////////////////////////////////////////////////////////////


        btnEnterPhoneNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(checkPhone(etPhone.getText().toString()) && cbZgoda.isChecked()){
                    if(isOnline()){

        /////////////////////////////////////////////////////////////////////////////
                        ObjectAnimator buttonAnimator = ObjectAnimator.ofFloat(ivRocketEnterPhone, "translationX",400f, 1000f);
                        buttonAnimator.setDuration(1000);
                        buttonAnimator.start();
                        buttonAnimator.addListener(new Animator.AnimatorListener() {
                            @Override
                            public void onAnimationStart(Animator animator) {

                            }

                            @Override
                            public void onAnimationEnd(Animator animator) {
//                                Bundle bundle = new Bundle();
//                                bundle.putString("role", "client");
//                                NavController navController = Navigation.findNavController(getActivity(), R.id.nav_host_fragment);
//                                navController.navigate(R.id.nav_enter_sms, bundle);
                                SendForRegPhone sendForRegPhone = new SendForRegPhone();
                                sendForRegPhone.execute("phone"+"@.#"+etPhone.getText().toString());
                            }

                            @Override
                            public void onAnimationCancel(Animator animator) {

                            }

                            @Override
                            public void onAnimationRepeat(Animator animator) {

                            }
                        });
         /////////////////////////////////////////////////////////////////////////////

//                        SendForRegPhone sendForRegPhone = new SendForRegPhone();
//                        sendForRegPhone.execute(etPhone.getText().toString());
                    }
                    else{
                        Toast.makeText(getContext(), "Перевірте інтернет", Toast.LENGTH_SHORT).show();
                    }
                }
                else if (!cbZgoda.isChecked() && checkPhone(etPhone.getText().toString())){
                    Toast.makeText(getContext(), "Поставте згоду з умовами Публічної оферти", Toast.LENGTH_SHORT).show();
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
                Log.d("send phone fo reg", e.getMessage());
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
            if(fromServer.length()>0){
                bundle.putString("role", bunStr);
                bundle.putString("phone", etPhone.getText().toString());
                NavController navController = Navigation.findNavController(getActivity(), R.id.nav_host_fragment);
                navController.navigate(R.id.nav_enter_sms, bundle);
            }
            else{
                Toast.makeText(getContext(), "Виникли технічні помилки. Вже вирішуемо", Toast.LENGTH_SHORT).show();
            }
        }
    }

    class GetOferta extends AsyncTask<String, Void, Socket> {
        private String linkCheckVApp = "myNull";
        private Socket socket;
        private PrintWriter pw = null;
        private InputStream is = null;
       // private String fromServer="";

        @Override
        protected Socket doInBackground(String... params) {

            PrintWriter pw;
            try {
                socket = new Socket(ip, port);

                pw = new PrintWriter(new BufferedOutputStream(socket.getOutputStream()));
                pw.write(params[0] + "@.#" + linkCheckVApp + "\n");
                pw.flush();

               /* is = socket.getInputStream();
                Scanner sc = new Scanner(is);
                fromServerOf = sc.nextLine();

                is.close();*/
                listOferta=new ArrayList<>();
                ObjectInputStream objectInput = new ObjectInputStream(socket.getInputStream());
                try {
                    Object object = objectInput.readUnshared();
                    listOferta =  (ArrayList<String>) object;

                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
                objectInput.close();

            } catch (IOException e) {
                //e.printStackTrace();
                Log.d("send phone fo reg", e.getMessage());
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
            if(listOferta!=null && listOferta.size()>0){
                Animation anim = AnimationUtils.loadAnimation(getContext(), R.anim.appear);
                llOferta.startAnimation(anim);


                anim.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        tvOferta.setText(listOferta.get(0));
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                            tvOferta.setJustificationMode(JUSTIFICATION_MODE_INTER_WORD);
                        }
                        llOferta.setVisibility(View.VISIBLE);
                        tvOferta.setVisibility(View.VISIBLE);
                        btnCloseOferta.setVisibility(View.VISIBLE);
                        btnCloseOferta.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                llOferta.setVisibility(View.INVISIBLE);
                                tvOferta.setVisibility(View.INVISIBLE);
                                btnCloseOferta.setVisibility(View.INVISIBLE);
                            }
                        });
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {

                    }
                });

            }
            else{
                Toast.makeText(getContext(), "Виникли технічні помилки. Вже вирішуемо", Toast.LENGTH_SHORT).show();
            }
        }
    }


}
