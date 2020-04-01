package encityproject.rightcodeit.com.encityproject.ui.registration.marketCats02;


import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.LinearLayout;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import java.util.ArrayList;

import encityproject.rightcodeit.com.encityproject.R;
import encityproject.rightcodeit.com.encityproject.ui.market.adapter.CatAdapter;
import encityproject.rightcodeit.com.encityproject.ui.market.model.CategoryModel;
import encityproject.rightcodeit.com.encityproject.ui.registration.CatChoiceAdapter;
import encityproject.rightcodeit.com.encityproject.ui.registration.marketCats01.SubCat;

/**
 * A simple {@link Fragment} subclass.
 */
public class SubCatsFragment extends Fragment {

    private Button btnCatsNext;
    private LinearLayout btnStore, btnService;
    private RecyclerView rvStore, rvService;
    private ArrayList<String> catsListStore;
    private ArrayList<String > catsListService;
    private CatChoiceAdapter adapterStore, adapterService;
    private Context context;
    private int b1,b2;
    private String bunRole, bunPhone;

    public SubCatsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v=inflater.inflate(R.layout.fragment_sub_cats, container, false);

        b1=0;
        b2=0;

        Bundle bundle = getArguments();
        bunRole= bundle.getString("role");
        bunPhone= bundle.getString("phone");

        context = getContext();
        rvStore =  v.findViewById(R.id.rvStore);
        rvStore.setLayoutManager(new GridLayoutManager(getActivity(), 1, GridLayoutManager.VERTICAL, false));
        rvService=v.findViewById(R.id.rvService);
        rvService.setLayoutManager(new GridLayoutManager(getActivity(), 1, GridLayoutManager.VERTICAL, false));

        Animation animAppear = AnimationUtils.loadAnimation(context,R.anim.appear);
        Animation animDisAppear = AnimationUtils.loadAnimation(context,R.anim.disappear);
//Продовольчий,Одежи,Спортивний,Автомобільний,Будівельний,Побутової техніки,Сантехніки,Електрики та освітлення,Зоомагазин,Сільськогосподарський," +
//                "Побутової хімії,Канцелярський,Дитячий,Інший
        catsListStore = new ArrayList<>();
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
        catsListStore.add("Інші");
        adapterStore = new CatChoiceAdapter(context,catsListStore);

        //Здоров'я,Логістині,Краси,Освіти,Комунальні,Побутові,Нерухомість,Туристичні,Страхові,Банківські
        catsListService=new ArrayList<>();
        catsListService.add("Здоров'я");
        catsListService.add("Логістичні");
        catsListService.add("Краси");
        catsListService.add("Освіти");
        catsListService.add("Комунальні");
        catsListService.add("Побутові");
        catsListService.add("Нерухомість");
        catsListService.add("Туристичні");
        catsListService.add("Страхові");
        catsListService.add("Банківські");
        adapterService=new CatChoiceAdapter(context,catsListService);
/////
/////
        btnStore=v.findViewById(R.id.btnStore);
        btnService=v.findViewById(R.id.btnService);
        btnCatsNext=v.findViewById(R.id.btnCatsNext);

        btnStore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(b1==0){
                    b1=1;
                    adapterStore=new CatChoiceAdapter(context, catsListStore);
                    rvStore.setAdapter(adapterStore);
                    rvStore.startAnimation(animAppear);
                }
                else {
                    b1=0;
                    rvStore.startAnimation(animDisAppear);
                    adapterStore=null;
                    rvStore.setAdapter(null);

                }
            }
        });

        btnService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(b2==0){
                    b2=1;
                adapterService=new CatChoiceAdapter(context, catsListService);
                rvService.setAdapter(adapterService);
                rvService.startAnimation(animAppear);
                }
                else {
                    b2=0;
                    rvService.startAnimation(animDisAppear);
                    adapterService=null;
                    rvService.setAdapter(null);
                }
            }
        });

        btnCatsNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                adapterStore.getItemsForReturn();
                adapterService.getItemsForReturn();
                Bundle bundle = new Bundle();
                bundle.putStringArrayList("catnamestore", catsListStore);
                bundle.putStringArrayList("catnameservice", catsListService);
                bundle.putString("role", bunRole);
                bundle.putString("phone", bunPhone);
                bundle.putIntegerArrayList("addedcatstore", adapterStore.getItemsForReturn());
                bundle.putIntegerArrayList("addedcatservice", adapterService.getItemsForReturn());
                NavController navController = Navigation.findNavController(getActivity(), R.id.nav_host_fragment);
                navController.navigate(R.id.nav_form_company_fragment, bundle);
            }
        });

        return v;
    }

}
