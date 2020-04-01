package encityproject.rightcodeit.com.encityproject.ui.registration;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import java.util.ArrayList;

import encityproject.rightcodeit.com.encityproject.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class AboutCompanyFragment extends Fragment {

    private String name, phone, secondphone;
    private ArrayList<String> listCatStore;
    private ArrayList<String> listCatService;
    private ArrayList<Integer> checkedCatStore;
    private ArrayList<Integer> checkedCatService;
    private EditText etAbout;
    private Button btnAboutNext;

    public AboutCompanyFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v=inflater.inflate(R.layout.fragment_about_company, container, false);

        etAbout=v.findViewById(R.id.etAbout);
        btnAboutNext = v.findViewById(R.id.btnAboutNext);

        Bundle bundle =getArguments();
        if (bundle.containsKey("secondphone")) {
            secondphone=bundle.getString("secondphone");
        }
        checkedCatStore=bundle.getIntegerArrayList("checkedcatstore");
        checkedCatService=bundle.getIntegerArrayList("checkedcatservice");
        phone= bundle.getString("phone");
        listCatStore=bundle.getStringArrayList("catstore");
        listCatService=bundle.getStringArrayList("catservice");
        name= bundle.getString("nameCompany");

        btnAboutNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(etAbout.getText().length()>5){
                    Bundle bundle = new Bundle();
                    bundle.putString("about", etAbout.getText().toString());
                    if (secondphone!= null) {
                        bundle.putString("secondphone", secondphone);
                    }
                    bundle.putIntegerArrayList("checkedcatstore", checkedCatStore);
                    bundle.putIntegerArrayList("checkedcatservice", checkedCatService);
                    bundle.putString("phone", phone);
                    bundle.putStringArrayList("catstore", listCatStore);
                    bundle.putStringArrayList("catservice", listCatService);
                    bundle.putString("nameCompany", name);
                    NavController navController = Navigation.findNavController(getActivity(), R.id.nav_host_fragment);
                    navController.navigate(R.id.nav_total_fragment, bundle);
                }
            }
        });


        return v;
    }

}
