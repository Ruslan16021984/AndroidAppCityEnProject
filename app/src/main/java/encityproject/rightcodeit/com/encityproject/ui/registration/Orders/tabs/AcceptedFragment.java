package encityproject.rightcodeit.com.encityproject.ui.registration.Orders.tabs;


import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import encityproject.rightcodeit.com.encityproject.R;
import encityproject.rightcodeit.com.encityproject.ui.market.adapter.NewAdapter;
import encityproject.rightcodeit.com.encityproject.ui.market.model.CategoryModel;

/**
 * A simple {@link Fragment} subclass.
 */
public class AcceptedFragment extends Fragment {
    private NewAdapter adapter = null;
    private RecyclerView recyclerView;
    private ArrayList<CategoryModel> categoryList;
    private Activity activity;
    private Context context;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_accepted, container, false);
        activity = getActivity();
        context = getContext();
        recyclerView =  view.findViewById(R.id.rvAccepted);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));

        categoryList = new ArrayList<>();
        categoryList.add(new CategoryModel("1", "Категория", R.drawable.build));
        categoryList.add(new CategoryModel("2", "Категория", R.drawable.foods));
        categoryList.add(new CategoryModel("3", "Категория", R.drawable.service));
        categoryList.add(new CategoryModel("4", "Категория", R.drawable.pharma));
        categoryList.add(new CategoryModel("5", "Категория", R.drawable.technic));
        categoryList.add(new CategoryModel("6", "Категория", R.drawable.clothes));
        adapter = new NewAdapter(context,activity ,categoryList);

        recyclerView.setAdapter(adapter);
        return view;
    }

}
