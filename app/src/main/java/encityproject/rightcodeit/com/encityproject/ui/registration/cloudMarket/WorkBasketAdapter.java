package encityproject.rightcodeit.com.encityproject.ui.registration.cloudMarket;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class WorkBasketAdapter extends FragmentPagerAdapter {
    private CharSequence title[];
    private int numbOfTabsumb;

    public WorkBasketAdapter(FragmentManager fm, CharSequence mTitle[], int mNumbOfTabsumb) {
        super(fm);
        this.title = mTitle;
        this.numbOfTabsumb = mNumbOfTabsumb;
    }

    @Override
    public Fragment getItem(int i) {
        if (i == 0){
            WorksInBasketFragment worksInBasketFragment = new WorksInBasketFragment("0");
            return worksInBasketFragment;
        }else if(i==1){
            InmakingBasketFragment inmakingBasketFragment = new InmakingBasketFragment("1");
            return inmakingBasketFragment;
        }
        else {
            EndInBasketFragment endInBasket = new EndInBasketFragment("completed");
            return endInBasket;
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
