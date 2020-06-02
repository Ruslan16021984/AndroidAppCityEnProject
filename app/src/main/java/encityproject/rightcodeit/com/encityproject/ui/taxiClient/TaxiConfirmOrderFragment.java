package encityproject.rightcodeit.com.encityproject.ui.taxiClient;


import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import encityproject.rightcodeit.com.encityproject.MainActivityWithNaviDrawer;
import encityproject.rightcodeit.com.encityproject.R;
import encityproject.rightcodeit.com.encityproject.ui.taxiClient.Models.AddressOrder;
import encityproject.rightcodeit.com.encityproject.ui.taxiClient.Models.mvp.MainPresenter;
import encityproject.rightcodeit.com.encityproject.ui.taxiClient.Models.mvp.SocketContruct;

import static android.support.constraint.Constraints.TAG;

/**
 * A simple {@link Fragment} subclass.
 */
public class TaxiConfirmOrderFragment extends Fragment {
    private MainActivityWithNaviDrawer activity;
    private TextView tvInfoOrder, tvTimerWait, tvInfoOrder1, tvInfoOrder2;
    private Button btnConfirmTaxi;
    private LinearLayout llConfirmTaxi, llBlack;
    private FrameLayout flSearch;
    private CustomMapView mapView;
    private int time;
    private String place;
    private String door;

    public TaxiConfirmOrderFragment() {
        // Required empty public constructor
    }

    @SuppressLint("SetTextI18n")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_taxi_confirm_order, container, false);
        activity = (MainActivityWithNaviDrawer) getActivity();
        llConfirmTaxi = view.findViewById(R.id.llComfirmTaxi);
        flSearch = view.findViewById(R.id.flSearch);
        flSearch.setVisibility(View.INVISIBLE);
        llBlack = view.findViewById(R.id.llBlack);
        tvInfoOrder = view.findViewById(R.id.tvInfoOrder);
        tvInfoOrder1 = view.findViewById(R.id.tvInfoOrder1);
        tvInfoOrder2 = view.findViewById(R.id.tvInfoOrder2);
        tvTimerWait = view.findViewById(R.id.tvTimeWait);
        tvTimerWait.setVisibility(View.INVISIBLE);
        btnConfirmTaxi = view.findViewById(R.id.btnConfirmTaxi);
        mapView = new CustomMapView(view, getContext(), this);
        time = 60;

        Bundle bundle = getArguments();
        if (bundle != null) {
            place = bundle.getString("place", "");
            door = bundle.getString("door", "");
            tvInfoOrder1.setText(place + ", під'їзд " + door);
            tvInfoOrder2.setText("Рекомендуєма ціна по місту 40 грн");
        }

        //Подтверждаем поездку. Высылаем на сервер. И ждем взятие заказ таксистом
        //Хендлером эмуляция взятия заказа. Нужно удалить ее
        btnConfirmTaxi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                llConfirmTaxi.setVisibility(View.INVISIBLE);
                flSearch.setVisibility(View.VISIBLE);
                tvTimerWait.setVisibility(View.VISIBLE);
                llBlack.setBackgroundColor(getResources().getColor(R.color.black));
                llBlack.setAlpha(0.5F);
                //bottom handler imitation take order
//                Handler h = new Handler();
//                h.post(new Runnable() {
//                    @Override
//                    public void run() {
//                        time=time-1;
//                        tvTimerWait.setText(time+" сек");
//                        h.postDelayed(this, 1000);
//                        //imitation time for take order by driver
//                        if(time==55) {
//                            Bundle bundle = new Bundle();
//                            //    bundle.putString("place", etAddress.getText().toString());
//                            NavController navController = Navigation.findNavController(getActivity(), R.id.nav_host_fragment);
//                            navController.navigate(R.id.nav_taxi_taken_order_fragment, bundle);
//                            h.removeCallbacksAndMessages(null);
//                        }
//                    }
//                });
               activity.getmPresenter().connectStomp();
                activity.getmPresenter().sendRequestClient(new AddressOrder(place, door));
                Log.e(TAG, "onClick: " + place + " - " + door);
            }
        });
        return view;
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy()");
    }
}
