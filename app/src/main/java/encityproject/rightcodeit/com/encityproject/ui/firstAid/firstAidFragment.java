package encityproject.rightcodeit.com.encityproject.ui.firstAid;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import encityproject.rightcodeit.com.encityproject.R;

public class firstAidFragment extends Fragment implements View.OnClickListener {
    private TextView tvAlgorithm;
    private TextView tvArtificialRespiration;
    private TextView tvIndirectCardiacMassage;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_first_aid, container, false);

        tvAlgorithm = v.findViewById(R.id.tv_algorithm);
        tvArtificialRespiration = v.findViewById(R.id.tv_artificial_respiration);
        tvIndirectCardiacMassage = v.findViewById(R.id.tv_indirect_cardiac_massage);


        tvAlgorithm.setOnClickListener(this);
        tvArtificialRespiration.setOnClickListener(this);
        tvIndirectCardiacMassage.setOnClickListener(this);

        return v;
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case (R.id.tv_algorithm):
                NavController navController = Navigation.findNavController(getActivity(), R.id.nav_host_fragment);
                navController.navigate(R.id.nav_firstAid_fragment_algorithm);
                break;
            case (R.id.tv_artificial_respiration):
                NavController navController2 = Navigation.findNavController(getActivity(), R.id.nav_host_fragment);
                navController2.navigate(R.id.nav_firstAid_fragment_artificial_respiration);
                break;
            case (R.id.tv_indirect_cardiac_massage):
                NavController navController3 = Navigation.findNavController(getActivity(), R.id.nav_host_fragment);
                navController3.navigate(R.id.nav_firstAid_fragment_indirect_cardiac_massage);
                break;
        }
    }
}
