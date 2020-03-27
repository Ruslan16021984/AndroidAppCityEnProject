package encityproject.rightcodeit.com.encityproject.ui.registration;


import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.BounceInterpolator;
import android.widget.Button;
import android.widget.ImageView;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import encityproject.rightcodeit.com.encityproject.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class WhoFragment extends Fragment {

    private Button btnClientReg,btnSellerReg;
    private ImageView ivRocket;


    public WhoFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_who, container, false);
        btnClientReg=v.findViewById(R.id.btnClientReg);
        btnSellerReg=v.findViewById(R.id.btnSellerReg);
        ivRocket=v.findViewById(R.id.ivRocket);

       /* Animation anim = AnimationUtils.loadAnimation(getContext(), R.anim.trans);
        ivRocket.startAnimation(anim);*/
        ObjectAnimator buttonAnimator = ObjectAnimator.ofFloat(ivRocket, "translationX",0f, 400f);
        buttonAnimator.setDuration(3000);
        buttonAnimator.setInterpolator(new BounceInterpolator());
        buttonAnimator.start();

        btnSellerReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ObjectAnimator buttonAnimator = ObjectAnimator.ofFloat(ivRocket, "translationX",400f, 1000f);
                buttonAnimator.setDuration(1000);
                buttonAnimator.start();
                buttonAnimator.addListener(new Animator.AnimatorListener() {
                    @Override
                    public void onAnimationStart(Animator animator) {

                    }

                    @Override
                    public void onAnimationEnd(Animator animator) {
                        Bundle bundle = new Bundle();
                        bundle.putString("role", "seller");
                        NavController navController = Navigation.findNavController(getActivity(), R.id.nav_host_fragment);
                        navController.navigate(R.id.nav_enter_phone, bundle);
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

        btnClientReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ObjectAnimator buttonAnimator = ObjectAnimator.ofFloat(ivRocket, "translationX",400f, 1000f);
                buttonAnimator.setDuration(1000);
                buttonAnimator.start();
                buttonAnimator.addListener(new Animator.AnimatorListener() {
                    @Override
                    public void onAnimationStart(Animator animator) {

                    }

                    @Override
                    public void onAnimationEnd(Animator animator) {
                        Bundle bundle = new Bundle();
                        bundle.putString("role", "client");
                        NavController navController = Navigation.findNavController(getActivity(), R.id.nav_host_fragment);
                        navController.navigate(R.id.nav_enter_phone, bundle);
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
