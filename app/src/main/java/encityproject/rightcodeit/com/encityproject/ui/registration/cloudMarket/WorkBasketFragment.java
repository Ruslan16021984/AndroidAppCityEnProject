package encityproject.rightcodeit.com.encityproject.ui.registration.cloudMarket;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import encityproject.rightcodeit.com.encityproject.R;
import encityproject.rightcodeit.com.encityproject.ui.market.adapter.inner_adapter.SlidingTabLayout;

/**
 * A simple {@link Fragment} subclass.
 */
public class WorkBasketFragment extends Fragment {
    private SlidingTabLayout slidingTabLayout;
    private ViewPager viewPager;
    private WorkBasketAdapter adapter;
    CharSequence Titles[]={"Нові", "Діючі", "Архівні"};
    //CharSequence Titles[]={"Магазини","Послуги"};
    int Numboftabs = 3;
    int pos=0;

    public WorkBasketFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_work_basket, container, false);
        viewPager = view.findViewById(R.id.viewpager);

        Bundle bundle = getArguments();
        if(bundle!=null){
            if(bundle.containsKey("pos")){
                pos=bundle.getInt("pos");
            }
        }

        adapter =  new WorkBasketAdapter(getChildFragmentManager(),Titles,Numboftabs);
        viewPager.setAdapter(adapter);
        viewPager.setCurrentItem(pos);

        slidingTabLayout = view.findViewById(R.id.sliding_tabs);
        slidingTabLayout.setDistributeEvenly(true);
        slidingTabLayout.setViewPager(viewPager);
        return view;
    }

}
