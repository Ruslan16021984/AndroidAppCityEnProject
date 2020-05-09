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
    private TextView tvBurns;
    private TextView tvFainting;
    private TextView tvDrowningAndSunstroke;
    private TextView tvHypothermiaAndFrostbite;
    private TextView tvPoisoning;
    private TextView tvFirstPsychologicalAid;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_first_aid, container, false);

        tvAlgorithm = v.findViewById(R.id.tv_algorithm);
        tvArtificialRespiration = v.findViewById(R.id.tv_artificial_respiration);
        tvIndirectCardiacMassage = v.findViewById(R.id.tv_indirect_cardiac_massage);
        tvBleeding = v.findViewById(R.id.tv_bleeding);
        tvFractures = v.findViewById(R.id.tv_fractures);
        tvBurns = v.findViewById(R.id.tv_burns);
        tvFainting = v.findViewById(R.id.tv_fainting);
        tvDrowningAndSunstroke = v.findViewById(R.id.tv_drowning_and_sunstroke);
        tvHypothermiaAndFrostbite = v.findViewById(R.id.tv_hypothermia_and_frostbite);
        tvPoisoning = v.findViewById(R.id.tv_poisoning);
        tvFirstPsychologicalAid = v.findViewById(R.id.tv_first_psychological_aid);


        tvAlgorithm.setOnClickListener(this);
        tvArtificialRespiration.setOnClickListener(this);
        tvIndirectCardiacMassage.setOnClickListener(this);
        tvBleeding.setOnClickListener(this);
        tvFractures.setOnClickListener(this);
        tvBurns.setOnClickListener(this);
        tvFainting.setOnClickListener(this);
        tvDrowningAndSunstroke.setOnClickListener(this);
        tvHypothermiaAndFrostbite.setOnClickListener(this);
        tvPoisoning.setOnClickListener(this);
        tvFirstPsychologicalAid.setOnClickListener(this);

        return v;
    }

    @Override
    public void onClick(View v) {
        NavController navController = Navigation.findNavController(getActivity(), R.id.nav_host_fragment);
//        switch (v.getId()) {
//            case (R.id.tv_algorithm):
//                navController.navigate(R.id.nav_firstAid_fragment_algorithm);
//                break;
//            case (R.id.tv_artificial_respiration):
//                navController.navigate(R.id.nav_firstAid_fragment_artificial_respiration);
//                break;
//            case (R.id.tv_indirect_cardiac_massage):
//                navController.navigate(R.id.nav_firstAid_fragment_indirect_cardiac_massage);
//                break;
//            case (R.id.tv_bleeding):
//                navController.navigate(R.id.nav_firstAid_fragment_bleeding);
//                break;
//            case (R.id.tv_fractures):
//                navController.navigate(R.id.nav_firstAid_fragment_fractures);
//                break;
//            case (R.id.tv_burns):
//                navController.navigate(R.id.nav_firstAid_fragment_burns);
//                break;
//            case (R.id.tv_fainting):
//                navController.navigate(R.id.nav_firstAid_fragment_fainting);
//                break;
//            case (R.id.tv_drowning_and_sunstroke):
//                navController.navigate(R.id.nav_firstAid_fragment_drowning_and_sunstroke);
//                break;
//            case (R.id.tv_hypothermia_and_frostbite):
//                navController.navigate(R.id.nav_firstAid_fragment_hypothermia_and_frostbite);
//                break;
//            case (R.id.tv_poisoning):
//                navController.navigate(R.id.nav_firstAid_fragment_poisoning);
//                break;
//            case (R.id.tv_first_psychological_aid):
//                navController.navigate(R.id.nav_firstAid_fragment_first_psychological_aid);
//                break;
//        }
    }
}

