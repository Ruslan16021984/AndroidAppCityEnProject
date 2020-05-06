package encityproject.rightcodeit.com.encityproject.ui.registration.cloudMarket;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import encityproject.rightcodeit.com.encityproject.ui.registration.Orders.tabs.AcceptedFragment;
import encityproject.rightcodeit.com.encityproject.ui.registration.Orders.tabs.NewOrdersFragment;

public class BasketAdapter extends FragmentPagerAdapter {
    private CharSequence title[];
    private int numbOfTabsumb;

    public BasketAdapter(FragmentManager fm, CharSequence mTitle[], int mNumbOfTabsumb) {
        super(fm);
        this.title = mTitle;
        this.numbOfTabsumb = mNumbOfTabsumb;

    }

    @Override
    public Fragment getItem(int i) {
        if (i == 0){
            OrdersInBasketFragment ordersInBasketFragment = new OrdersInBasketFragment("nothing");
            return ordersInBasketFragment;
        }else {
            OrdersInBasketFragment ordersInBasketFragment2 = new OrdersInBasketFragment("completed");
            return ordersInBasketFragment2;
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
