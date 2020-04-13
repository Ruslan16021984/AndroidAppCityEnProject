package encityproject.rightcodeit.com.encityproject.ui.registration.cloudMarket;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import encityproject.rightcodeit.com.encityproject.R;
import encityproject.rightcodeit.com.encityproject.ui.market.adapter.OrdersPagerAdapter;
import encityproject.rightcodeit.com.encityproject.ui.market.adapter.inner_adapter.SlidingTabLayout;

/**
 * A simple {@link Fragment} subclass.
 */
public class BasketFragment extends Fragment {
    private SlidingTabLayout slidingTabLayout;
    private ViewPager viewPager;
    private BasketAdapter adapter;
    CharSequence Titles[]={"Поточні","Архівні"};
    //CharSequence Titles[]={"Магазини","Послуги"};
    int Numboftabs = 2;

    public BasketFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_basket, container, false);
        viewPager = view.findViewById(R.id.viewpager);


        adapter =  new BasketAdapter(getChildFragmentManager(),Titles,Numboftabs);
        viewPager.setAdapter(adapter);

        slidingTabLayout = view.findViewById(R.id.sliding_tabs);
        slidingTabLayout.setDistributeEvenly(true);
        slidingTabLayout.setViewPager(viewPager);
        return view;
    }



}
