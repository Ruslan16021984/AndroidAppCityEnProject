package encityproject.rightcodeit.com.encityproject;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.text.SpannableString;
import android.text.style.TextAppearanceSpan;
import android.util.Log;
import android.util.TypedValue;
import android.view.MenuItem;
import android.view.View;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
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

import encityproject.rightcodeit.com.encityproject.ui.registration.cloudMarket.InmakingBasketAdapter;
import encityproject.rightcodeit.com.encityproject.ui.registration.cloudMarket.WorksInBasketAdapter;

public class MainActivityWithNaviDrawer extends AppCompatActivity {

    private int port = 4656;
    // private String ip = "192.168.1.46";
    private String ip = "35.232.178.112";
    // private String ip = "192.168.1.103";
    //private String ip ="192.168.0.103";

    private AppBarConfiguration mAppBarConfiguration;
    private SharedPreferences prefer;
    private SharedPreferences.Editor editor;
    private static final String APP_PREFERENCES = "ensettings";
    private ArrayList<String> listSMS;
    private NavigationView navigationView;
    private Menu menuNav;
    private ArrayList<String> categoryList;
    private String phone;

    public boolean isOnline() {
        ConnectivityManager cm =
                (ConnectivityManager) this.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        return netInfo != null && netInfo.isConnectedOrConnecting();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(isOnline()) {
            if(prefer.contains("auth")) {
                GetNumSMSClient getNumSMSClient = new GetNumSMSClient();
                getNumSMSClient.execute("getnumsmsclient" + "@.#" + "client" + "@.#" + prefer.getString("auth", ""));
            }
            if(prefer.contains("auth2")){
                GetNumSMSSeller getNumSMSSeller = new GetNumSMSSeller();
                getNumSMSSeller.execute("getnumsmsseller" + "@.#" + "seller" + "@.#" + prefer.getString("name", ""));
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_with_navi_drawer);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        listSMS=new ArrayList<>();
/*

        GetOferta getOferta = new GetOferta();
        getOferta.execute("oferta");
*/


        categoryList=new ArrayList<>();
        prefer=getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);
      // prefer.edit().clear().commit();

        //   Toast.makeText(this, prefer.getString("auth2",""), Toast.LENGTH_SHORT).show();
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);

        if(!prefer.contains("join")){
            Log.d("JOIN", "creating");
            editor=prefer.edit();
            editor.putString("join", String.valueOf(System.currentTimeMillis()));
            editor.apply();
        }
        if(isOnline()) {
            if (prefer.contains("auth") && prefer.contains("join")) {
                JoinApp joinApp = new JoinApp();
                joinApp.execute("join" + "@.#" + prefer.getString("join", "") + "@.#" + prefer.getString("auth", ""));
            } else if (!prefer.contains("auth") && prefer.contains("join")) {
                JoinApp joinApp = new JoinApp();
                joinApp.execute("join" + "@.#" + prefer.getString("join", "") + "@.#" + "noAuth");
            }
        }
        /*navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                if(menuItem.getItemId()==R.id.nav_basket_fragment){
                    Menu menuNav=navigationView.getMenu();
                    MenuItem nav = menuNav.findItem(R.id.nav_basket_fragment);
                    nav.setIcon(R.drawable.helsi);
                    Drawable drawable = nav.getIcon();
                    if(drawable != null) {
                        drawable.mutate();
                        //  drawable.setColorFilter(getResources().getColor(R.color.red), PorterDuff.Mode.SRC_ATOP);
                        drawable.setColorFilter(Color.BLACK, PorterDuff.Mode.SRC_ATOP);
                    }
                }
                return false;
            }
        });*/
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                 R.id.nav_weather, R.id.nav_phonesBook,R.id.nav_discount,
                /*R.id.nav_bench, R.id.nav_busmap,*/ R.id.nav_helsi, R.id.nav_news, R.id.nav_reg, R.id.nav_auth_company_fragment,
                R.id.nav_auth_client_fragment, R.id.nav_entrance_market, R.id.nav_basket_fragment, R.id.nav_work_basket_fragment/*,*R.id.nav_share, R.id.nav_send*/)
                .setDrawerLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);

        menuNav=navigationView.getMenu();


       /* MenuItem nav2 = menuNav.findItem(R.id.nav_work_basket_fragment);
        nav2.setIcon(R.drawable.newmessageblack);
        nav2.getIcon().setColorFilter(getResources().getColor(R.color.red), PorterDuff.Mode.SRC_ATOP);
        nav2.setTitle(nav2.getTitle()+" +1");*/

        /*MenuItem nav = menuNav.findItem(R.id.nav_basket_fragment);

        nav.setIcon(R.drawable.helsi);
        Drawable drawable = nav.getIcon();
        if(drawable != null) {
            drawable.mutate();
            //  drawable.setColorFilter(getResources().getColor(R.color.red), PorterDuff.Mode.SRC_ATOP);
            drawable.setColorFilter(Color.BLUE, PorterDuff.Mode.SRC_ATOP);
        }
*/
        if(prefer.contains("addcats") && prefer.contains("auth")){
         //   Menu menuNav=navigationView.getMenu();
            MenuItem nav_reg = menuNav.findItem(R.id.nav_reg);
            nav_reg.setVisible(false);
            MenuItem nav_auth_client = menuNav.findItem(R.id.nav_auth_client_fragment);
            nav_auth_client.setVisible(false);
            MenuItem nav_auth = menuNav.findItem(R.id.nav_auth_company_fragment);
            nav_auth.setVisible(true);
            MenuItem nav_cloud_market = menuNav.findItem(R.id.nav_entrance_market);
            nav_cloud_market.setVisible(true);
            MenuItem nav_basket = menuNav.findItem(R.id.nav_basket_fragment);
            /*SpannableString s = new SpannableString(nav_basket.getTitle());
            s.setSpan(new TextAppearanceSpan(this, R.style.TextMenu), 0, s.length(), 0);
            nav_basket.setTitle(s);*/
            nav_basket.setVisible(true);
            MenuItem nav_work_basket = menuNav.findItem(R.id.nav_work_basket_fragment);
            nav_work_basket.setVisible(true);
        }
        else if(prefer.contains("auth") && prefer.contains("role") && prefer.contains("phone") && !prefer.contains("regstatus")){
       //     Menu menuNav=navigationView.getMenu();
            MenuItem nav_reg = menuNav.findItem(R.id.nav_reg);
            nav_reg.setVisible(true);
            MenuItem nav_auth = menuNav.findItem(R.id.nav_auth_company_fragment);
            nav_auth.setVisible(false);
            MenuItem nav_cloud_market = menuNav.findItem(R.id.nav_entrance_market);
            nav_cloud_market.setVisible(false);
            MenuItem nav_work_basket = menuNav.findItem(R.id.nav_work_basket_fragment);
            nav_work_basket.setVisible(false);
                if (prefer.getString("role", "").equals("client")) {
                    MenuItem nav_auth_client = menuNav.findItem(R.id.nav_auth_client_fragment);
                    nav_auth_client.setVisible(true);
                    nav_reg.setVisible(false);
                    nav_cloud_market.setVisible(true);
                    MenuItem nav_basket = menuNav.findItem(R.id.nav_basket_fragment);
                    nav_basket.setVisible(true);
                    nav_work_basket.setVisible(false);
                }
                else if(prefer.getString("role", "").equals("seller")){
                    MenuItem nav_auth_client = menuNav.findItem(R.id.nav_auth_client_fragment);
                    nav_auth_client.setVisible(false);
                    MenuItem nav_basket = menuNav.findItem(R.id.nav_basket_fragment);
                    nav_basket.setVisible(false);
                    Bundle bundle = new Bundle();
                    bundle.putString("role", prefer.getString("role",""));
                    bundle.putString("phone", prefer.getString("phone",""));
                    navController.navigate(R.id.nav_cat_choice, bundle);
                }
        }
        else{
       //     Menu menuNav=navigationView.getMenu();
            MenuItem nav_reg = menuNav.findItem(R.id.nav_reg);
            nav_reg.setVisible(true);
            MenuItem nav_auth = menuNav.findItem(R.id.nav_auth_company_fragment);
            nav_auth.setVisible(false);
            MenuItem nav_auth_client = menuNav.findItem(R.id.nav_auth_client_fragment);
            nav_auth_client.setVisible(false);
            MenuItem nav_cloud_market = menuNav.findItem(R.id.nav_entrance_market);
            nav_cloud_market.setVisible(false);
            MenuItem nav_basket = menuNav.findItem(R.id.nav_basket_fragment);
            nav_basket.setVisible(false);
            MenuItem nav_work_basket = menuNav.findItem(R.id.nav_work_basket_fragment);
            nav_work_basket.setVisible(false);

        }
        /*Menu menuNav=navigationView.getMenu();
        MenuItem nav_cloud_market = menuNav.findItem(R.id.nav_entrance_market);
        nav_cloud_market.setVisible(true);*/


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_activity_with_navi_drawer, menu);


        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);

    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

    }

    class GetNumSMSClient extends AsyncTask<String, Void, Socket> {
        private Socket s = null;
        //private String fromServer;
        private PrintWriter pw = null;
        private InputStream is = null;
        private String sms="";

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
                    listSMS =  (ArrayList<String>) object;

                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
                objectInput.close();

                for(int i=0;i<listSMS.size();i++){
                    sms=sms+","+listSMS.get(i);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Socket socket) {
            super.onPostExecute(socket);
            //numorder, description, telclient, status, createdate, enddate
            if(listSMS!=null && listSMS.size()>0) {
                editor = prefer.edit();
                editor.putString("newsmsforclient", sms);
                editor.apply();
                MenuItem nav = menuNav.findItem(R.id.nav_basket_fragment);
                nav.setIcon(R.drawable.newmessageblack);
                nav.getIcon().setColorFilter(getResources().getColor(R.color.red), PorterDuff.Mode.SRC_ATOP);
                nav.setTitle("Мої покупки"+" +"+String.valueOf(listSMS.size()));
            }
            else {

            }
        }
    }

    class GetNumSMSSeller extends AsyncTask<String, Void, Socket> {
        private Socket s = null;
        //private String fromServer;
        private PrintWriter pw = null;
        private InputStream is = null;
        private String sms="";

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
                    listSMS =  (ArrayList<String>) object;

                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
                objectInput.close();

                for(int i=0;i<listSMS.size();i++){
                    sms=sms+","+listSMS.get(i);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Socket socket) {
            super.onPostExecute(socket);
            //numorder, description, telclient, status, createdate, enddate
            if(listSMS!=null && listSMS.size()>0) {
                editor = prefer.edit();
                editor.putString("newsmsforseller", sms);
                editor.apply();
                MenuItem nav = menuNav.findItem(R.id.nav_work_basket_fragment);
               // nav.setIcon(R.drawable.newmessageblack);
                nav.setIcon(R.drawable.workbasket2);
                nav.getIcon().setColorFilter(getResources().getColor(R.color.red), PorterDuff.Mode.SRC_ATOP);
                nav.setTitle("Моя робота"+" +"+String.valueOf(listSMS.size()));

            }
            else {

            }
            if(prefer.contains("secondphone")){
                phone=prefer.getString("secondphone","");
            }
            else{
                phone=prefer.getString("phone","");
            }
            GetCompanyAllOrders getCompanyAllOrders = new GetCompanyAllOrders();
            getCompanyAllOrders.execute("getcomporder" + "@.#" + prefer.getString("auth", "") + "@.#"
                    + prefer.getString("auth2", "") + "@.#" + prefer.getString("name", "")
                    + "@.#" + phone + "@.#" + "0");
        }
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
                MenuItem nav = menuNav.findItem(R.id.nav_work_basket_fragment);
             //   nav.setIcon(R.drawable.newmessageblack);
                nav.setIcon(R.drawable.workbasket2);
                nav.getIcon().setColorFilter(getResources().getColor(R.color.red), PorterDuff.Mode.SRC_ATOP);
                nav.setTitle("Моя робота"+" +"+String.valueOf((listSMS.size()+categoryList.size())));

            }

        }
    }

    class JoinApp extends AsyncTask<String, Void, Socket> {
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

        }
    }

}
