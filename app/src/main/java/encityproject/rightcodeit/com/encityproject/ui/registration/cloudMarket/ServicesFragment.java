package encityproject.rightcodeit.com.encityproject.ui.registration.cloudMarket;


import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import encityproject.rightcodeit.com.encityproject.R;
import encityproject.rightcodeit.com.encityproject.ui.market.adapter.inner_adapter.CloudMarketAdapter;
import encityproject.rightcodeit.com.encityproject.ui.market.model.CategoryModel;

/**
 * A simple {@link Fragment} subclass.
 */
public class ServicesFragment extends Fragment {
    private CloudMarketAdapter adapter = null;
    private RecyclerView recyclerView;
    private ArrayList<CategoryModel> categoryList;
    private Activity activity;
    private Context context;

    public ServicesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_services, container, false);
        activity = getActivity();
        context = getContext();
        recyclerView =  view.findViewById(R.id.rvNewOrders);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2, GridLayoutManager.VERTICAL, false));

        categoryList = new ArrayList<>();
        categoryList.add(new CategoryModel("1", "Здоров'я", R.drawable.serhealth));
        categoryList.add(new CategoryModel("2", "Логістичні", R.drawable.serdel));
        categoryList.add(new CategoryModel("2", "Авторемонтні", R.drawable.sercar));
        categoryList.add(new CategoryModel("3", "Краси", R.drawable.serbeauty));
        categoryList.add(new CategoryModel("4", "Освіти", R.drawable.serteach));
        categoryList.add(new CategoryModel("5", "Комунальні", R.drawable.sercom));
        categoryList.add(new CategoryModel("6", "Побутові", R.drawable.serbyt));
        categoryList.add(new CategoryModel("6", "Нерухомість", R.drawable.serbuild));
        categoryList.add(new CategoryModel("6", "Туристичні", R.drawable.serrest));
        categoryList.add(new CategoryModel("6", "Страхові", R.drawable.serstrah));
        categoryList.add(new CategoryModel("6", "Банківські", R.drawable.serbank));
        categoryList.add(new CategoryModel("6", "Інші", R.drawable.catother));

        //  adapter = new NewAdapter(context,activity ,categoryList);
        adapter = new CloudMarketAdapter(context,activity ,categoryList);

        recyclerView.setAdapter(adapter);
        return view;
    }

}
