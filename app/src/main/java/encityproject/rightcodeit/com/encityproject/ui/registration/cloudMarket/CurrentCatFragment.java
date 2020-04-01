package encityproject.rightcodeit.com.encityproject.ui.registration.cloudMarket;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import encityproject.rightcodeit.com.encityproject.R;
import encityproject.rightcodeit.com.encityproject.ui.registration.CatChoiceAdapter;

/**
 * A simple {@link Fragment} subclass.
 */
public class CurrentCatFragment extends Fragment {
    private RecyclerView recyclerView;
    private ArrayList<String> categoryList;
    private Context context;
    private CatChoiceAdapter catChoiceAdapter;


    public CurrentCatFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        categoryList=new ArrayList<>();
        categoryList.add("Компания 1");
        categoryList.add("Компания 2");
        categoryList.add("Компания 3");
        context = getContext();
        View v= inflater.inflate(R.layout.fragment_current_cat, container, false);
        recyclerView =  v.findViewById(R.id.rvCurrCat);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 1, GridLayoutManager.VERTICAL, false));
        catChoiceAdapter = new CatChoiceAdapter(context,categoryList);
        recyclerView.setAdapter(catChoiceAdapter);



        return v;
    }

}
