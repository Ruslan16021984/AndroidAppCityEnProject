package encityproject.rightcodeit.com.encityproject.ui.registration.cloudMarket;


import android.annotation.SuppressLint;
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
@SuppressLint("ValidFragment")
public class GoodsFragment extends Fragment {
    private CloudMarketAdapter adapter = null;
    private RecyclerView recyclerView;
    private ArrayList<CategoryModel> categoryList;
    private Activity activity;
    private Context context;
    private ArrayList<String> catsListStore;

    @SuppressLint("ValidFragment")
    public GoodsFragment(ArrayList<String> catsListStore) {
        this.catsListStore=catsListStore;
    }

    private int setDrawIcon (String name){
        int nameToInt=getResources().getIdentifier(name , "drawable", getContext().getPackageName());
        return nameToInt;
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

        String ee = "catgoods";
        int dd = getResources().getIdentifier(ee , "drawable", getContext().getPackageName());
        /*categoryList = new ArrayList<>();
        categoryList.add(new CategoryModel("1", "Продовольчі", setDrawIcon("catgoods")));
        categoryList.add(new CategoryModel("2", "Одежи", setDrawIcon("catclothes")));
        categoryList.add(new CategoryModel("3", "Спортивні", setDrawIcon("catsport")));
        categoryList.add(new CategoryModel("4", "Автомобільні", setDrawIcon("catauto")));
        categoryList.add(new CategoryModel("5", "Будівельні", setDrawIcon("catbuild")));
        categoryList.add(new CategoryModel("6", "Побутової техніки", setDrawIcon("catdevice")));
        categoryList.add(new CategoryModel("6", "Сантехніки", setDrawIcon("catpipe")));
        categoryList.add(new CategoryModel("6", "Електрики та освітлення", setDrawIcon("catelec")));
        categoryList.add(new CategoryModel("6", "Зоомагазини", setDrawIcon("catzoo")));
        categoryList.add(new CategoryModel("6", "Сільськогосподарські", setDrawIcon("catbyt")));
        categoryList.add(new CategoryModel("6", "Аптеки", setDrawIcon("catpharma")));
        categoryList.add(new CategoryModel("6", "Побутової хімії", setDrawIcon("catchemi")));
        categoryList.add(new CategoryModel("6", "Канцелярські", setDrawIcon("catoffice")));
        categoryList.add(new CategoryModel("6", "Дитячі", setDrawIcon("catkid")));
        categoryList.add(new CategoryModel("6", "Інші", setDrawIcon("catother")));*/

        //  adapter = new NewAdapter(context,activity ,categoryList);
        //adapter = new CloudMarketAdapter(context,activity ,categoryList);
        adapter = new CloudMarketAdapter(context,activity ,catsListStore);

        recyclerView.setAdapter(adapter);
        return view;
    }

}
