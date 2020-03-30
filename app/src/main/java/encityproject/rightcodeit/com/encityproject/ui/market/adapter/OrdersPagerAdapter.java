package encityproject.rightcodeit.com.encityproject.ui.market.adapter;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import encityproject.rightcodeit.com.encityproject.ui.registration.Orders.tabs.AcceptedFragment;
import encityproject.rightcodeit.com.encityproject.ui.registration.Orders.tabs.NewOrdersFragment;

public class OrdersPagerAdapter extends FragmentPagerAdapter {
  private CharSequence title[];
  private int numbOfTabsumb;

    public OrdersPagerAdapter(FragmentManager fm, CharSequence mTitle[], int mNumbOfTabsumb) {
        super(fm);
       this.title = mTitle;
       this.numbOfTabsumb = mNumbOfTabsumb;

    }

    @Override
    public Fragment getItem(int i) {
        if (i == 0){
            NewOrdersFragment newOrdersFragment = new NewOrdersFragment();
            return newOrdersFragment;
        }else {
            AcceptedFragment acceptedFragment = new AcceptedFragment();
            return acceptedFragment;
        }
    }

    @Override
    public int getCount() {
        return numbOfTabsumb;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return title[position];
    }

}
