package encityproject.rightcodeit.com.encityproject.ui.registration;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import java.util.ArrayList;

import encityproject.rightcodeit.com.encityproject.R;
import encityproject.rightcodeit.com.encityproject.ui.market.adapter.CatAdapter;
import encityproject.rightcodeit.com.encityproject.ui.market.model.CategoryModel;

/**
 * A simple {@link Fragment} subclass.
 */
public class CatChoiceFragment extends Fragment {

    private RecyclerView recyclerView;
    private ArrayList<String> categoryList;
    private Context context;
    private CatChoiceAdapter catChoiceAdapter;
    private Button btnCatChoice;
    private String bunRole, bunPhone;

    public CatChoiceFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        context = getContext();
        View v= inflater.inflate(R.layout.fragment_cat_choice, container, false);
        btnCatChoice=v.findViewById(R.id.btnCatChoice);
        recyclerView =  v.findViewById(R.id.rvChoiceList);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 1, GridLayoutManager.VERTICAL, false));

        Bundle bundle = getArguments();
        bunRole= bundle.getString("role");
       bunPhone= bundle.getString("phone");

        categoryList = new ArrayList<>();
        categoryList.add("Стройматеріали");
        categoryList.add("Продукти");
        categoryList.add("Послуги");
        categoryList.add("Аптека");
        categoryList.add("Техніка");
        categoryList.add("Одежа");
        catChoiceAdapter = new CatChoiceAdapter(context, categoryList);
        recyclerView.setAdapter(catChoiceAdapter);

        btnCatChoice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(), catChoiceAdapter.getItemsForReturn().toString(), Toast.LENGTH_SHORT).show();
                Bundle bundle = new Bundle();
                bundle.putStringArrayList("catnames", categoryList);
                bundle.putString("role", bunRole);
                bundle.putString("phone", bunPhone);
                bundle.putIntegerArrayList("cat", catChoiceAdapter.getItemsForReturn());
                NavController navController = Navigation.findNavController(getActivity(), R.id.nav_host_fragment);
                navController.navigate(R.id.nav_form_company_fragment, bundle);
            }
        });

        return v;
    }

}
