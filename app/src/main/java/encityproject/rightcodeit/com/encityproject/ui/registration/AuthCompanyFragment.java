package encityproject.rightcodeit.com.encityproject.ui.registration;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import encityproject.rightcodeit.com.encityproject.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class AuthCompanyFragment extends Fragment {

    private Button btnAuthClose;
    private SharedPreferences prefer;
    private SharedPreferences.Editor editor;
    private static final String APP_PREFERENCES = "ensettings";

    public AuthCompanyFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_auth_company, container, false);
        btnAuthClose=v.findViewById(R.id.btnAuthClose);
        btnAuthClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                prefer=getContext().getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);
                prefer.edit().clear().commit();
            }
        });
        return v;
    }

}
