package encityproject.rightcodeit.com.encityproject.ui.registration;


import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.BounceInterpolator;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import encityproject.rightcodeit.com.encityproject.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class TotalFragment extends Fragment {

    private TextView tvTotalName;
    private TextView tvTotalPhone, tvTotalCats;
    private ImageView ivTotal;
    private TextView tvCats;
    private String bunPhone, bunRole, bunName;
    private ArrayList<String> catList;

    public TotalFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_total, container, false);

        Bundle bundle = getArguments();
        bunPhone=bundle.getString("phone");
        bunRole=bundle.getString("role");
        bunName=bundle.getString("nameCompany");
        catList=bundle.getStringArrayList("catnames");
        ArrayList<Integer> alCats= bundle.getIntegerArrayList("cat");

        String totalCats="";

        for(int i=0; i<alCats.size();i++){
            if(i==0){
                totalCats=catList.get(alCats.get(i));
            }
            else {
                totalCats=totalCats+", "+catList.get(alCats.get(i));
            }
        }

        tvTotalName=v.findViewById(R.id.tvTotalName);
        tvTotalPhone=v.findViewById(R.id.tvTotalPhone);
        tvTotalCats=v.findViewById(R.id.tvTotalCats);

        tvTotalName.setText(bunName);
        tvTotalPhone.setText(bunPhone);
        tvTotalCats.setText(totalCats);

        ivTotal=v.findViewById(R.id.ivTotal);
        ivTotal.setRotation(270);

        ObjectAnimator buttonAnimator = ObjectAnimator.ofFloat(ivTotal, "translationY", 0f,-2000f);
        buttonAnimator.setDuration(3000);
        buttonAnimator.start();


        return v;
    }

}
