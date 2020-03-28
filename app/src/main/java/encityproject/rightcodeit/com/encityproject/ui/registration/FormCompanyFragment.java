package encityproject.rightcodeit.com.encityproject.ui.registration;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import java.util.ArrayList;

import encityproject.rightcodeit.com.encityproject.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class FormCompanyFragment extends Fragment {

    private EditText etNameCompany;
    private Button btnNameCompanyNext;
    private String bunRole, bunPhone, bunName;
    private ArrayList<String> catList;

    public FormCompanyFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_form_company, container, false);
        etNameCompany=v.findViewById(R.id.etNameCompany);
        btnNameCompanyNext=v.findViewById(R.id.btnNameCompanyNext);

        Bundle bundle = getArguments();
        bunPhone=bundle.getString("phone");
        bunRole=bundle.getString("role");
        catList=bundle.getStringArrayList("catnames");
        ArrayList<Integer>alCats= bundle.getIntegerArrayList("cat");

        btnNameCompanyNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(etNameCompany.getText().length()>=5){
                    Bundle bundle = new Bundle();
                    bundle.putIntegerArrayList("cat", alCats);
                    bundle.putString("phone", bunPhone);
                    bundle.putStringArrayList("catnames", catList);
                    bundle.putString("nameCompany", etNameCompany.getText().toString());
                    NavController navController = Navigation.findNavController(getActivity(), R.id.nav_host_fragment);
                    navController.navigate(R.id.nav_total_fragment, bundle);
                }
                else {
                    Toast.makeText(getContext(), "Назва має бути більше 4-х символів", Toast.LENGTH_SHORT).show();
                }
            }
        });
        return v;
    }

}
