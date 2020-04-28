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
    private final static String link = "http://www.bstu.ru/about/important/antiterror/info/med";

    private TextView tvAlgorithm;
    private TextView tvArtificialRespiration;
    private TextView tvIndirectCardiacMassage;
    private TextView tvBleeding;
    private TextView tvFractures;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_first_aid, container, false);

        tvAlgorithm = v.findViewById(R.id.tv_algorithm);
        tvArtificialRespiration = v.findViewById(R.id.tv_artificial_respiration);
        tvIndirectCardiacMassage = v.findViewById(R.id.tv_indirect_cardiac_massage);
        tvBleeding = v.findViewById(R.id.tv_bleeding);
        tvFractures = v.findViewById(R.id.tv_fractures);


        tvAlgorithm.setOnClickListener(this);
        tvArtificialRespiration.setOnClickListener(this);
        tvIndirectCardiacMassage.setOnClickListener(this);
        tvBleeding.setOnClickListener(this);
        tvFractures.setOnClickListener(this);

        return v;
    }

    @Override
    public void onClick(View v) {
        NavController navController = Navigation.findNavController(getActivity(), R.id.nav_host_fragment);
        switch (v.getId()) {
            case (R.id.tv_algorithm):
                navController.navigate(R.id.nav_firstAid_fragment_algorithm);
                break;
            case (R.id.tv_artificial_respiration):
                navController.navigate(R.id.nav_firstAid_fragment_artificial_respiration);
                break;
            case (R.id.tv_indirect_cardiac_massage):
                navController.navigate(R.id.nav_firstAid_fragment_indirect_cardiac_massage);
                break;
            case (R.id.tv_bleeding):
                navController.navigate(R.id.nav_firstAid_fragment_bleeding);
                break;
            case (R.id.tv_fractures):
                navController.navigate(R.id.nav_firstAid_fragment_fractures);
                break;
        }
    }
}
