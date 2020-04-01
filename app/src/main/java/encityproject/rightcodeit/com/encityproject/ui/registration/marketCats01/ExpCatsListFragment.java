package encityproject.rightcodeit.com.encityproject.ui.registration.marketCats01;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import encityproject.rightcodeit.com.encityproject.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class ExpCatsListFragment extends Fragment {
    private RecyclerView recycler;

    public ExpCatsListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v=inflater.inflate(R.layout.fragment_exp_cats_list, container, false);
        recycler=v.findViewById(R.id.recycler);
        List<String> items= new ArrayList<>();
        items.add("Магазин;Продовольчий,Одежи,Спортивний,Автомобільний,Будівельний,Побутової техніки,Сантехніки,Електрики та освітлення,Зоомагазин,Сільськогосподарський," +
                "Побутової хімії,Канцелярський,Дитячий,Інший");
        items.add("Послуги;Здоров'я,Логістині,Краси,Освіти,Комунальні,Побутові,Нерухомість,Туристичні,Страхові,Банківські");
        recycler.setAdapter(new RecyclerAdapter(recycler, items));
        recycler.setLayoutManager(new LinearLayoutManager(getContext()));

        return v;
    }

}
