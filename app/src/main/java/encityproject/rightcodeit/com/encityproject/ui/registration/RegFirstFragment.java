package encityproject.rightcodeit.com.encityproject.ui.registration;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import java.io.InputStream;
import java.io.PrintWriter;
import java.net.Socket;

import encityproject.rightcodeit.com.encityproject.MainActivityWithNaviDrawer;
import encityproject.rightcodeit.com.encityproject.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class RegFirstFragment extends Fragment {

    private Button btnStartReg;
    private SharedPreferences prefer;
    private SharedPreferences.Editor editor;
    private static final String APP_PREFERENCES = "ensettings";


    public RegFirstFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_reg_first, container, false);
        prefer=getContext().getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);

        btnStartReg = v.findViewById(R.id.btnStartReg);

        btnStartReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if(prefer.contains("auth")){
                    Intent myIntent = new Intent(getContext(), MainActivityWithNaviDrawer.class);
                    getContext().startActivity(myIntent);
                }
                else {
                    NavController navController = Navigation.findNavController(getActivity(), R.id.nav_host_fragment);
                    navController.navigate(R.id.nav_rocket_choice);
                }
            }
        });

        return v;
    }


}
