package encityproject.rightcodeit.com.encityproject.ui.market;


import android.app.Activity;
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
import encityproject.rightcodeit.com.encityproject.ui.registration.adapters.CategoryAdapter;
import encityproject.rightcodeit.com.encityproject.ui.market.model.CategoryModel;

/**
 * A simple {@link Fragment} subclass.
 */
public class GridCategoryFragment extends Fragment {
    private ArrayList<CategoryModel> categoryList;
    private Activity activity;
    private Context context;
    private CategoryAdapter adapter = null;
    private RecyclerView recyclerView;


    public GridCategoryFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        activity = getActivity();
        context = getContext();
        View view = inflater.inflate(R.layout.fragment_market, container, false);
        recyclerView =  view.findViewById(R.id.rvContent);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2, GridLayoutManager.VERTICAL, false));

        categoryList = new ArrayList<>();
        categoryList.add(new CategoryModel("1", "Вода"));
        categoryList.add(new CategoryModel("2", "Картошка"));
        categoryList.add(new CategoryModel("3", "Кирпич"));
        categoryList.add(new CategoryModel("4", "Киллер"));
        categoryList.add(new CategoryModel("5", "Интим"));
        adapter = new CategoryAdapter(context, activity, categoryList);
        recyclerView.setAdapter(adapter);
        return view;
    }

}
