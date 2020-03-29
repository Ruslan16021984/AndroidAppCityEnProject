package encityproject.rightcodeit.com.encityproject.ui.registration;


import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.BounceInterpolator;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import java.util.ArrayList;

import encityproject.rightcodeit.com.encityproject.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class TotalFragment extends Fragment {

    private TextView tvTotalName;
    private TextView tvTotalPhone, tvTotalCats;
    private Button btnTotalNext;
    private ImageView ivTotal;
    private TextView tvCats;
    private String bunPhone, bunRole, bunName;
    private ArrayList<String> catList;
    private SharedPreferences prefer;
    private SharedPreferences.Editor editor;
    private static final String APP_PREFERENCES = "ensettings";

    public TotalFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_total, container, false);

        prefer=getContext().getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);

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

        btnTotalNext=v.findViewById(R.id.btnTotalNext);
        tvTotalName=v.findViewById(R.id.tvTotalName);
        tvTotalPhone=v.findViewById(R.id.tvTotalPhone);
        tvTotalCats=v.findViewById(R.id.tvTotalCats);

        tvTotalName.setText(bunName);
        tvTotalPhone.setText(bunPhone);
        tvTotalCats.setText(totalCats);

        ivTotal=v.findViewById(R.id.ivTotal);
        ivTotal.setRotation(270);




        btnTotalNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ObjectAnimator buttonAnimator = ObjectAnimator.ofFloat(ivTotal, "translationY", 0f,-2000f);
                buttonAnimator.setDuration(3000);
                buttonAnimator.start();

                buttonAnimator.addListener(new Animator.AnimatorListener() {
                    @Override
                    public void onAnimationStart(Animator animator) {

                    }

                    @Override
                    public void onAnimationEnd(Animator animator) {
                        //AsyncTask for confirm reg
                        editor = prefer.edit();
                        editor.putString("name", bunName);
                        editor.putString("phone", bunPhone);
                        editor.putString("who", bunRole);
                        editor.putString("cats", tvTotalCats.getText().toString());
                        editor.apply();
                        setHasOptionsMenu(true);
                        NavController navController = Navigation.findNavController(getActivity(), R.id.nav_host_fragment);
                        navController.navigate(R.id.nav_auth_company_fragment);
                    }

                    @Override
                    public void onAnimationCancel(Animator animator) {

                    }

                    @Override
                    public void onAnimationRepeat(Animator animator) {

                    }
                });

            }
        });



        return v;
    }

}
