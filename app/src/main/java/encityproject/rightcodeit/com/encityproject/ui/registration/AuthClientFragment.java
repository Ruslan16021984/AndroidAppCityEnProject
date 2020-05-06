package encityproject.rightcodeit.com.encityproject.ui.registration;


import android.app.AlertDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import encityproject.rightcodeit.com.encityproject.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class AuthClientFragment extends Fragment {

    private TextView tvAuthPhoneClient;
    private Button btnAuthCloseClient;
    private SharedPreferences prefer;
    private SharedPreferences.Editor editor;
    private static final String APP_PREFERENCES = "ensettings";

    public AuthClientFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v=inflater.inflate(R.layout.fragment_auth_client, container, false);

        prefer=getContext().getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);
        tvAuthPhoneClient=v.findViewById(R.id.tvAuthPhoneClient);
        btnAuthCloseClient=v.findViewById(R.id.btnAuthCloseClient);

        if(prefer.contains("auth")){
            tvAuthPhoneClient.setText(prefer.getString("phone",""));
        }

        btnAuthCloseClient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                View ve = (LinearLayout) getLayoutInflater()
                        .inflate(R.layout.dialog_confirm_delete_client, null);

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
                        MenuItem nav_auth = menuNav.findItem(R.id.nav_auth_client_fragment);
                        nav_auth.setVisible(false);
                        MenuItem nav_cloud_market = menuNav.findItem(R.id.nav_entrance_market);
                        nav_cloud_market.setVisible(false);
                        MenuItem nav_basket = menuNav.findItem(R.id.nav_basket_fragment);
                        nav_basket.setVisible(false);
                        prefer=getContext().getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);
                        prefer.edit().clear().commit();
                        Toast.makeText(getContext(), "Вашу авторизацію деактивовано", Toast.LENGTH_SHORT).show();
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

}
