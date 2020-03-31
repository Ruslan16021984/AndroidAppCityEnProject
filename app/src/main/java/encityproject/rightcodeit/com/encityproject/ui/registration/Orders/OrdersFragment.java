package encityproject.rightcodeit.com.encityproject.ui.registration.Orders;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import encityproject.rightcodeit.com.encityproject.R;
import encityproject.rightcodeit.com.encityproject.ui.market.adapter.OrdersPagerAdapter;
import encityproject.rightcodeit.com.encityproject.ui.market.adapter.inner_adapter.SlidingTabLayout;


public class OrdersFragment extends Fragment {
    private SlidingTabLayout slidingTabLayout;
    private ViewPager viewPager;
    OrdersPagerAdapter adapter;
    CharSequence Titles[]={"Новые заказы","Принятые"};
    int Numboftabs = 2;


    public OrdersFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_orders, container, false);
        // Inflate the layout for this fragment
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        viewPager = view.findViewById(R.id.viewpager);
        adapter =  new OrdersPagerAdapter(getChildFragmentManager(),Titles,Numboftabs);
        viewPager.setAdapter(adapter);

        slidingTabLayout = view.findViewById(R.id.sliding_tabs);
        slidingTabLayout.setDistributeEvenly(true);
        slidingTabLayout.setViewPager(viewPager);
    }
}
