package encityproject.rightcodeit.com.encityproject.ui.firstAid;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import encityproject.rightcodeit.com.encityproject.R;

public class firstAidArtificialRespirationFragment extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_first_aid_artificial_respiration, container, false);

        return v;
    }
}
