package encityproject.rightcodeit.com.encityproject.ui.registration;


import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.BounceInterpolator;
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

import encityproject.rightcodeit.com.encityproject.MainActivityWithNaviDrawer;
import encityproject.rightcodeit.com.encityproject.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class EnterSmsFragment extends Fragment {

    private int port = 4656;
    private String ip = "192.168.1.46";
    //private String ip = "35.232.178.112";
   // private String ip = "192.168.1.103";
    private Button btnEnterSms;
    private EditText etSms;
    private Bundle bundle;
    private String bunRole, bunPhone;
    private SharedPreferences prefer;
    private SharedPreferences.Editor editor;
    private static final String APP_PREFERENCES = "ensettings";
    private ImageView ivRocketEnterSms;

    public EnterSmsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        prefer=getContext().getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);

        if(prefer.contains("auth")){
            Intent myIntent = new Intent(getContext(), MainActivityWithNaviDrawer.class);
            getContext().startActivity(myIntent);
        }

        View v= inflater.inflate(R.layout.fragment_enter_sms, container, false);
        btnEnterSms=v.findViewById(R.id.btnEnterSmsNext);
        etSms=v.findViewById(R.id.etSms);

        bundle = getArguments();
        bunRole = bundle.getString("role");
        bunPhone = bundle.getString("phone");

        prefer=getContext().getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);

        //bundle.putString("role", bunStr);

        /////////////////////////////////////////////////////////////////////////////
        ivRocketEnterSms=v.findViewById(R.id.ivRocket_enter_sms);
        ObjectAnimator buttonAnimator = ObjectAnimator.ofFloat(ivRocketEnterSms, "translationX",0f, 400f);
        buttonAnimator.setDuration(3000);
        buttonAnimator.setInterpolator(new BounceInterpolator());
        buttonAnimator.start();
        /////////////////////////////////////////////////////////////////////////////

        btnEnterSms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(etSms.getText().length()>0){

                    /////////////////////////////////////////////////////////////////////////////
                    ObjectAnimator buttonAnimator = ObjectAnimator.ofFloat(ivRocketEnterSms, "translationX",400f, 1000f);
                    buttonAnimator.setDuration(1000);
                    buttonAnimator.start();
                    /////////////////////////////////////////////////////////////////////////////
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
                            SendForRegSms sendForRegSms = new SendForRegSms();
                            sendForRegSms.execute("sms"+"@.#"+bunPhone+"@.#"+etSms.getText().toString()+"@.#"+bunRole);
                        }

                        @Override
                        public void onAnimationCancel(Animator animator) {

                        }

                        @Override
                        public void onAnimationRepeat(Animator animator) {

                        }
                    });



                }
            }
        });
        return v;
    }

    class SendForRegSms extends AsyncTask<String, Void, Socket> {
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
                Log.d("Ex GetWeather", e.getMessage());
            }
             finally {

            }

            return null;
        }

        @Override
        protected void onPostExecute(Socket socket) {
            super.onPostExecute(socket);
            //pbListPhones.setVisibility(View.INVISIBLE);
         //   fromServer="ok";
            if(fromServer.length()>0 && !fromServer.equals("no")){
                if(fromServer.contains("@.#")){
                    String[] str = fromServer.split("@.#");
                    editor = prefer.edit();
                    editor.putString("auth", str[0]);
                    editor.putString("role", "seller");
                    editor.putString("auth2", str[1]);
                    if(str[5].contains(",")){
                        editor.putString("phone", str[5].split(",")[0]) ;
                        editor.putString("secondphone", str[5].split(",")[1]) ;
                    } else {
                        editor.putString("phone", str[5]);
                    }
                    editor.putString("name", str[2]);
                    editor.putString("about", str[4]);
                    editor.putString("who", "seller");
                    editor.putString("regstatus","ok");
                    Log.d("str6", str[6]);
                    if(!str[6].contains(",")) {
                        editor.putString("addcats", str[6]);
                    }
                    else{
                        String cts="";
                        String[] cattext = str[6].split(";");
                        for(int r=0;r<cattext.length;r++){
                            if(r==0) {
                                cts = cattext[0];
                            }
                            else{
                                cts=cts+"\n"+cattext[1];
                            }
                        }
                        editor.putString("addcats", cts);
                    }
                    editor.putString("addcatsindex", str[3]);
                    editor.apply();
                    setHasOptionsMenu(true);
                    NavigationView navigationView = getActivity().findViewById(R.id.nav_view);
                    Menu menuNav = navigationView.getMenu();
                    MenuItem nav_reg = menuNav.findItem(R.id.nav_reg);
                    nav_reg.setVisible(false);
                    MenuItem nav_auth = menuNav.findItem(R.id.nav_auth_company_fragment);
                    nav_auth.setVisible(true);
                    MenuItem nav_cloud_market = menuNav.findItem(R.id.nav_entrance_market);
                    nav_cloud_market.setVisible(true);
                    MenuItem nav_basket_work = menuNav.findItem(R.id.nav_work_basket_fragment);
                    nav_basket_work.setVisible(true);
                    MenuItem nav_basket = menuNav.findItem(R.id.nav_basket_fragment);
                    nav_basket.setVisible(true);
                    NavController navController = Navigation.findNavController(getActivity(), R.id.nav_host_fragment);
                    navController.navigate(R.id.nav_auth_company_fragment);
                    // uniquenumuser0, uniquenumcompany1, companyname2, cats3, about4, phone5, cattext6
                }
                else {
                    editor = prefer.edit();
                    editor.putString("auth", fromServer);
                    editor.putString("role", bunRole);
                    editor.putString("phone", bunPhone);
                    editor.apply();
                    if (bunRole.equals("seller")) {
                        Bundle bundle = new Bundle();
                        bundle.putString("role", bunRole);
                        bundle.putString("phone", bunPhone);
                        NavController navController = Navigation.findNavController(getActivity(), R.id.nav_host_fragment);
                        navController.navigate(R.id.nav_cat_choice, bundle);
                        //navController.navigate(R.id.nav_sub_cats_fragment, bundle);
                    } else {
                        final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                        View ve = (LinearLayout) getLayoutInflater()
                                .inflate(R.layout.dialog_congratulate, null);

                        Button btnCong = ve.findViewById(R.id.btnCong);

                        builder
                                .setView(ve)
                                .setCancelable(false)
                        ;
                        final AlertDialog alert2 = builder.create();

                        btnCong.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                alert2.dismiss();
                                /*editor.putString("auth", fromServer);
                                editor.putString("phone", bunPhone);
                                editor.putString("role", bunRole);
                                editor.apply();*/
                                setHasOptionsMenu(true);
                                NavigationView navigationView = getActivity().findViewById(R.id.nav_view);
                                Menu menuNav = navigationView.getMenu();
                                MenuItem nav_reg = menuNav.findItem(R.id.nav_reg);
                                nav_reg.setVisible(false);
                                MenuItem nav_auth = menuNav.findItem(R.id.nav_auth_client_fragment);
                                nav_auth.setVisible(true);
                                MenuItem nav_cloud_market = menuNav.findItem(R.id.nav_entrance_market);
                                nav_cloud_market.setVisible(true);
                                MenuItem nav_basket = menuNav.findItem(R.id.nav_basket_fragment);
                                nav_basket.setVisible(true);
                                NavController navController = Navigation.findNavController(getActivity(), R.id.nav_host_fragment);
                                navController.navigate(R.id.nav_entrance_market, bundle);
                            }
                        });

                        alert2.show();
                        alert2.setCancelable(false);
                    }
                }
                /*NavController navController = Navigation.findNavController(getActivity(), R.id.nav_host_fragment);
                navController.navigate(R.id.nav_enter_sms);*/

            }
            else{
                Toast.makeText(getContext(), "Виникли технічні помилки. Вже вирішуемо", Toast.LENGTH_SHORT).show();
            }
        }
    }


}
