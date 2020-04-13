package encityproject.rightcodeit.com.encityproject.ui.registration.cloudMarket;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

import encityproject.rightcodeit.com.encityproject.ui.registration.Orders.tabs.AcceptedFragment;
import encityproject.rightcodeit.com.encityproject.ui.registration.Orders.tabs.NewOrdersFragment;

public class EntrancePagerAdapter extends FragmentPagerAdapter {
  private CharSequence title[];
  private int numbOfTabsumb;
  private ArrayList<String> catsListStore;
  private ArrayList<String> catsListService;

    public EntrancePagerAdapter(FragmentManager fm, CharSequence mTitle[], int mNumbOfTabsumb, ArrayList<String> catsListStore,
                                ArrayList<String> catsListService) {
        super(fm);
       this.title = mTitle;
       this.numbOfTabsumb = mNumbOfTabsumb;
       this.catsListStore=catsListStore;
       this.catsListService=catsListService;

    }

    @Override
    public Fragment getItem(int i) {
        if (i == 0){
            GoodsFragment goodsFragment = new GoodsFragment(catsListStore);
            return goodsFragment;
        }else {
            ServicesFragment servicesFragment = new ServicesFragment(catsListService);
            return servicesFragment;
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
