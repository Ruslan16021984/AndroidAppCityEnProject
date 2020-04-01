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

    private EditText etNameCompany, etSecondPhone;
    private Button btnNameCompanyNext;
    private String bunRole, bunPhone, bunName;
    private ArrayList<String> catListStore;
    private ArrayList<String> catListService;

    public FormCompanyFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_form_company, container, false);
        etNameCompany=v.findViewById(R.id.etNameCompany);
        etSecondPhone=v.findViewById(R.id.etSecondPhone);
        btnNameCompanyNext=v.findViewById(R.id.btnNameCompanyNext);

        Bundle bundle = getArguments();
        bunPhone=bundle.getString("phone");
        bunRole=bundle.getString("role");
        catListStore=bundle.getStringArrayList("catstore");
        catListService=bundle.getStringArrayList("catservice");
        ArrayList<Integer>CheckedCatsStore= bundle.getIntegerArrayList("checkedcatstore");
        ArrayList<Integer>CheckedCatsService= bundle.getIntegerArrayList("checkedcatservice");

        btnNameCompanyNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(etNameCompany.getText().length()>=5){
                    if(etSecondPhone.getText().length()>0 && etSecondPhone.getText().length()!=9){
                        Toast.makeText(getContext(), "Введіть вірний телефон або видалить його", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        Bundle bundle = new Bundle();
                        if (etSecondPhone.getText().length() == 9) {
                            bundle.putString("secondphone", etSecondPhone.getText().toString());
                        }
                        bundle.putIntegerArrayList("checkedcatstore", CheckedCatsStore);
                        bundle.putIntegerArrayList("checkedcatservice", CheckedCatsService);
                        bundle.putString("phone", bunPhone);
                        bundle.putStringArrayList("catstore", catListStore);
                        bundle.putStringArrayList("catservice", catListService);
                        bundle.putString("nameCompany", etNameCompany.getText().toString());
                        NavController navController = Navigation.findNavController(getActivity(), R.id.nav_host_fragment);
                        navController.navigate(R.id.nav_about_company_fragment, bundle);
                    }
                }
                else {
                    Toast.makeText(getContext(), "Назва має бути більше 4-х символів", Toast.LENGTH_SHORT).show();
                }
            }
        });
        return v;
    }

}
