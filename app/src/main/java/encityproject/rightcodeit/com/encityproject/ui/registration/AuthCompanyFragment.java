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
public class AuthCompanyFragment extends Fragment {

    private Button btnAuthClose;
    private SharedPreferences prefer;
    private SharedPreferences.Editor editor;
    private static final String APP_PREFERENCES = "ensettings";
    private String authName, authCats, authPhone, authAbout;
    private TextView tvAuthName, tvAuthCats, tvAuthPhone, tvAuthAbout;

    public AuthCompanyFragment() {
        // Required empty public constructor
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


        if(prefer.contains("addcats") && prefer.contains("auth")){
            authName=prefer.getString("name","");
            authCats=prefer.getString("addcats", "");
            authPhone=prefer.getString("phone", "");
            authAbout=prefer.getString("about","");
            tvAuthName.setText(authName);
            tvAuthCats.setText(authCats);
            tvAuthPhone.setText(authPhone);
            tvAuthAbout.setText(authAbout);
            if(prefer.contains("secondphone")){
                tvAuthPhone.setText("+380"+tvAuthPhone.getText().toString()+"\n"+"+380"+prefer.getString("secondphone","")+"(для клієнтів)");
            }
        }
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

}
