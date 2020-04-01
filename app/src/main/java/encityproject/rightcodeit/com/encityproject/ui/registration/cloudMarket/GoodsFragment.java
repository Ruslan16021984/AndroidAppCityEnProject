package encityproject.rightcodeit.com.encityproject.ui.registration.cloudMarket;


import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
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
public class GoodsFragment extends Fragment {
    private CloudMarketAdapter adapter = null;
    private RecyclerView recyclerView;
    private ArrayList<CategoryModel> categoryList;
    private Activity activity;
    private Context context;

    public GoodsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_goods, container, false);
        activity = getActivity();
        context = getContext();
        recyclerView =  view.findViewById(R.id.rvNewOrders);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2, GridLayoutManager.VERTICAL, false));

        /*catsListStore = new ArrayList<>();
        catsListStore.add("Продовольчі");
        catsListStore.add("Одежи");
        catsListStore.add("Спортивні");
        catsListStore.add("Автомобільні");
        catsListStore.add("Будівельні");
        catsListStore.add("Побутової техніки");
        catsListStore.add("Сантехніки");
        catsListStore.add("Електрики та освітлення");
        catsListStore.add("Зоомагазини");
        catsListStore.add("Сільськогосподарські");
        catsListStore.add("Побутової хімії");
        catsListStore.add("Канцелярські");
        catsListStore.add("Дитячі");
        catsListStore.add("Інші");*/

        categoryList = new ArrayList<>();
        categoryList.add(new CategoryModel("1", "Продовольчі", R.drawable.catgoods));
        categoryList.add(new CategoryModel("2", "Одежи", R.drawable.catclothes));
        categoryList.add(new CategoryModel("3", "Спортивні", R.drawable.catsport));
        categoryList.add(new CategoryModel("4", "Автомобільні", R.drawable.catauto));
        categoryList.add(new CategoryModel("5", "Будівельні", R.drawable.catbuild));
        categoryList.add(new CategoryModel("6", "Побутової техніки", R.drawable.catdevice));
        categoryList.add(new CategoryModel("6", "Сантехніки", R.drawable.catpipe));
        categoryList.add(new CategoryModel("6", "Електрики та освітлення", R.drawable.catelec));
        categoryList.add(new CategoryModel("6", "Зоомагазини", R.drawable.catzoo));
        categoryList.add(new CategoryModel("6", "Сільськогосподарські", R.drawable.catbyt));
        categoryList.add(new CategoryModel("6", "Аптеки", R.drawable.pharma));
        categoryList.add(new CategoryModel("6", "Побутової хімії", R.drawable.catchemi));
        categoryList.add(new CategoryModel("6", "Канцелярські", R.drawable.catoffice));
        categoryList.add(new CategoryModel("6", "Дитячі", R.drawable.catkid));
        categoryList.add(new CategoryModel("6", "Інші", R.drawable.catother));

        //  adapter = new NewAdapter(context,activity ,categoryList);
        adapter = new CloudMarketAdapter(context,activity ,categoryList);

        recyclerView.setAdapter(adapter);
        return view;
    }

}
