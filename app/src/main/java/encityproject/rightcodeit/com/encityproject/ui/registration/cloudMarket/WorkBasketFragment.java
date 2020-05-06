package encityproject.rightcodeit.com.encityproject.ui.registration.cloudMarket;


import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.PorterDuff;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
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
    private SharedPreferences prefer;
    private SharedPreferences.Editor editor;
    private static final String APP_PREFERENCES = "ensettings";
    private String newSmsForSeller;
    int Numboftabs = 3;
    int pos=0;

    public WorkBasketFragment() {
        // Required empty public constructor
    }

    public boolean isOnline() {
        ConnectivityManager cm =
                (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        return netInfo != null && netInfo.isConnectedOrConnecting();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_work_basket, container, false);
        viewPager = view.findViewById(R.id.viewpager);
        prefer=getContext().getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);

        NavigationView navigationView = getActivity().findViewById(R.id.nav_view);

        Menu menuNav=navigationView.getMenu();
        MenuItem nav = menuNav.findItem(R.id.nav_work_basket_fragment);
        /*nav.setIcon(R.drawable.workbasket);
        nav.getIcon().setColorFilter(getResources().getColor(R.color.black), PorterDuff.Mode.SRC_ATOP);
        nav.setTitle("Моя робота");
*/
        Bundle bundle = getArguments();
        if(bundle!=null){
            if(bundle.containsKey("pos")){
                pos=bundle.getInt("pos");
            }
        }
/*
        if(prefer.contains("newsmsforseller")){
            newSmsForSeller=prefer.getString("newsmsforseller","");
            String[]str=newSmsForSeller.split(",");
            if(str.length>1){
                nav.setIcon(R.drawable.workbasket2);
                nav.getIcon().setColorFilter(getResources().getColor(R.color.red), PorterDuff.Mode.SRC_ATOP);
                nav.setTitle("Моя робота"+" +"+String.valueOf((str.length-1)));
            }
            else{
                nav.setIcon(R.drawable.workbasket);
                nav.setTitle("Моя робота");
            }
        }
        else{
            nav.setIcon(R.drawable.workbasket);
            nav.setTitle("Моя робота");
        }
*/

        adapter =  new WorkBasketAdapter(getChildFragmentManager(),Titles,Numboftabs);
        viewPager.setAdapter(adapter);
        viewPager.setCurrentItem(pos);

        slidingTabLayout = view.findViewById(R.id.sliding_tabs);
        slidingTabLayout.setDistributeEvenly(true);
        slidingTabLayout.setViewPager(viewPager);
        return view;
    }

}
