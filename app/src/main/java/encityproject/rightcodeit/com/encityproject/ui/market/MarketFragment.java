package encityproject.rightcodeit.com.encityproject.ui.market;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import java.util.ArrayList;

import encityproject.rightcodeit.com.encityproject.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class MarketFragment extends Fragment {

    private GridView gvMarket;
    private ArrayList<String> al;


    public MarketFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_market, container, false);
        gvMarket=v.findViewById(R.id.gvMarket);
        al=new ArrayList<>();
        al.add("Продукти");
        al.add("Аптека");
        al.add("Стройматеріали");
        al.add("Послуги");
        al.add("Продукти");
        GVAdapter gVAdapter = new GVAdapter(getActivity(),
                R.layout.custom_onekat_market,
                0,
                1,
                al);
        gvMarket.setAdapter(gVAdapter);
        return v;
    }

}
