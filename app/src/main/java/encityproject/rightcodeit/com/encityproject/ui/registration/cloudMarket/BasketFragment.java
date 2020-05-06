package encityproject.rightcodeit.com.encityproject.ui.registration.cloudMarket;


import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.text.SpannableString;
import android.text.style.TextAppearanceSpan;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

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
    private SharedPreferences prefer;
    private SharedPreferences.Editor editor;
    private static final String APP_PREFERENCES = "ensettings";
    private String newSmsForClient;


    public BasketFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_basket, container, false);
        viewPager = view.findViewById(R.id.viewpager);
        prefer=getContext().getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);

        NavigationView navigationView = getActivity().findViewById(R.id.nav_view);

        Menu menuNav=navigationView.getMenu();
        MenuItem nav = menuNav.findItem(R.id.nav_basket_fragment);


        if(prefer.contains("newsmsforclient")){
            newSmsForClient=prefer.getString("newsmsforclient","");
            String[]str=newSmsForClient.split(",");
            if(str.length>1){
            nav.setIcon(R.drawable.newmessageblack);
            nav.getIcon().setColorFilter(getResources().getColor(R.color.red), PorterDuff.Mode.SRC_ATOP);
            nav.setTitle("Мої покупки"+" +"+String.valueOf((str.length-1)));
            }
            else{
                nav.setIcon(R.drawable.cart);
                nav.setTitle("Мої покупки");
            }
        }
        else{
            nav.setIcon(R.drawable.cart);
            nav.setTitle("Мої покупки");
        }



       /*     NavigationView navigationView = getActivity().findViewById(R.id.nav_view);

        Menu menuNav=navigationView.getMenu();
        MenuItem nav = menuNav.findItem(R.id.nav_basket_fragment);

        nav.setTitle(nav.getTitle()+" 1");
        nav.setIcon(R.drawable.helsi);
        Drawable drawable = nav.getIcon();
        if(drawable != null) {
            drawable.mutate();
          //  drawable.setColorFilter(getResources().getColor(R.color.red), PorterDuff.Mode.SRC_ATOP);
            drawable.setColorFilter(new TextAppearanceSpan(R.color.blue,));
        }
        */
        /*SpannableString s = new SpannableString(nav_basket.getTitle());
            s.setSpan(new TextAppearanceSpan(this, R.style.TextMenu), 0, s.length(), 0);
            nav_basket.setTitle(s);*/
        adapter =  new BasketAdapter(getChildFragmentManager(),Titles,Numboftabs);
        viewPager.setAdapter(adapter);

        slidingTabLayout = view.findViewById(R.id.sliding_tabs);
        slidingTabLayout.setDistributeEvenly(true);
        slidingTabLayout.setViewPager(viewPager);
        return view;
    }



}
