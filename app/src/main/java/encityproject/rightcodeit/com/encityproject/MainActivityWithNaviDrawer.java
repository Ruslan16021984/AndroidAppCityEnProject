package encityproject.rightcodeit.com.encityproject;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
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

public class MainActivityWithNaviDrawer extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    private SharedPreferences prefer;
    private SharedPreferences.Editor editor;
    private static final String APP_PREFERENCES = "ensettings";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_with_navi_drawer);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        prefer=getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                 R.id.nav_weather, R.id.nav_phonesBook,R.id.nav_discount,
                R.id.nav_bench, R.id.nav_busmap, R.id.nav_helsi, R.id.nav_news, R.id.nav_reg, R.id.nav_auth_company_fragment, R.id.nav_entrance_market/*,*R.id.nav_share, R.id.nav_send*/)
                .setDrawerLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);

        if(prefer.contains("addcats") && prefer.contains("auth")){
            Menu menuNav=navigationView.getMenu();
            MenuItem nav_reg = menuNav.findItem(R.id.nav_reg);
            nav_reg.setVisible(false);
            MenuItem nav_auth = menuNav.findItem(R.id.nav_auth_company_fragment);
            nav_auth.setVisible(true);
            MenuItem nav_cloud_market = menuNav.findItem(R.id.nav_entrance_market);
            nav_cloud_market.setVisible(true);
        }
        else{
            Menu menuNav=navigationView.getMenu();
            MenuItem nav_reg = menuNav.findItem(R.id.nav_reg);
            nav_reg.setVisible(true);
            MenuItem nav_auth = menuNav.findItem(R.id.nav_auth_company_fragment);
            nav_auth.setVisible(false);
            MenuItem nav_cloud_market = menuNav.findItem(R.id.nav_entrance_market);
            nav_cloud_market.setVisible(false);
        }



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_activity_with_navi_drawer, menu);


        return true;
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
}
