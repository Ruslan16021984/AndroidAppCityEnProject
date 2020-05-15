package encityproject.rightcodeit.com.encityproject.ui.taxiClient;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import encityproject.rightcodeit.com.encityproject.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class TaxiConfirmOrderFragment extends Fragment {
    private TextView tvInfoOrder;
    private Button btnConfirmTaxi;
    private LinearLayout llConfirmTaxi, llBlack;
    private FrameLayout flSearch;
    private CustomMapView mapView;

    public TaxiConfirmOrderFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_taxi_confirm_order, container, false);
        llConfirmTaxi=view.findViewById(R.id.llComfirmTaxi);
        flSearch=view.findViewById(R.id.flSearch);
        flSearch.setVisibility(View.INVISIBLE);
        llBlack=view.findViewById(R.id.llBlack);
        tvInfoOrder= view.findViewById(R.id.tvInfoOrder);
        btnConfirmTaxi=view.findViewById(R.id.btnConfirmTaxi);
        mapView = new CustomMapView(view, getContext(), this);

        Bundle bundle = getArguments();
        if(bundle!=null){
            tvInfoOrder.setText("Ваше Замовлення\n"+bundle.getString("place","")+"\nРекомендуєма ціна по місту 40 грн");
        }

        btnConfirmTaxi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                llConfirmTaxi.setVisibility(View.INVISIBLE);
                flSearch.setVisibility(View.VISIBLE);
                llBlack.setBackgroundColor(getResources().getColor(R.color.black));
                llBlack.setAlpha(0.5F);

            }
        });
        return view;
    }

}
