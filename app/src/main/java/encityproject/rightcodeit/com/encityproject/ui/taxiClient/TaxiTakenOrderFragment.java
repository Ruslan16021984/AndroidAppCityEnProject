package encityproject.rightcodeit.com.encityproject.ui.taxiClient;


import android.Manifest;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;

import org.osmdroid.bonuspack.BuildConfig;
import org.osmdroid.config.Configuration;
import org.osmdroid.views.overlay.Marker;

import encityproject.rightcodeit.com.encityproject.MainActivityWithNaviDrawer;
import encityproject.rightcodeit.com.encityproject.R;
import encityproject.rightcodeit.com.encityproject.ui.taxiClient.Models.TaxiWorker;

/**
 * A simple {@link Fragment} subclass.
 */
public class TaxiTakenOrderFragment extends Fragment {
    private MainActivityWithNaviDrawer activity;
    private static final int REQUEST_CODE_PERMISSION_READ_CONTACTS = 123;
    private CustomMapView mapView;
    private LocationManager mLocationManager;
    private Button btnCancelTaxi;
    private TextView tvAuto, tvAutoColor, tvAutoNum, tvAutoDriver, tvAutoTime;
    private LinearLayout llBtnCallDriver;
    private Marker hideMarke;
    private TaxiWorker taxiWorker;

    public TaxiTakenOrderFragment() {
        // Required empty public constructor
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Configuration.getInstance().setUserAgentValue(BuildConfig.APPLICATION_ID);
        Configuration.getInstance().load(getContext(), PreferenceManager.getDefaultSharedPreferences(getActivity()));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_taxi_taken_order, container, false);

        tvAuto = view.findViewById(R.id.tvAuto);
        tvAutoColor = view.findViewById(R.id.tvAutoColor);
        tvAutoNum = view.findViewById(R.id.tvAutoNum);
        tvAutoDriver = view.findViewById(R.id.tvAutoDriver);
        tvAutoNum = view.findViewById(R.id.tvAutoNum);
        Bundle bundle=getArguments();
        if(bundle!=null){
            String taxiW=  bundle.getString("worker","");
            Log.e("TAG", "onCreateView: "+ taxiW );
            taxiWorker = new Gson().fromJson(taxiW, TaxiWorker.class);
            tvAuto.setText(taxiWorker.getNameCar());
            tvAutoNum.setText(taxiWorker.getNumberCar());
            tvAutoColor.setText(taxiWorker.getColorCar());
            tvAutoDriver.setText(taxiWorker.getNameDriver());
        }

        btnCancelTaxi = view.findViewById(R.id.btnCancelTaxi);
        llBtnCallDriver = view.findViewById(R.id.llBtnCallDriver);
        mapView = new CustomMapView(view, getContext(), this);
        checkPermition();
        hideMarke = new Marker(mapView.getMapView());
        //bottom handler imitation take order
        activity= (MainActivityWithNaviDrawer) getActivity();
        activity.getmPresenter().getDispTopic().dispose();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            hideMarke.setIcon(getResources().getDrawable(R.drawable.taxi));
            Log.e("TAG", "onCreateView: иконка установилась успешно"  );
        }else {
            Log.e("TAG", "onCreateView: иконка установилась НЕ успешно"  );
        }
        mapView.getMapView().getOverlays().add(hideMarke);
        mapView.getMapView().invalidate();
        activity.getmPresenter().stompTopic(mapView.getMapView(), hideMarke);
        Handler h = new Handler();
//        h.postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                Bundle bundle = new Bundle();
//                //    bundle.putString("place", etAddress.getText().toString());
//                NavController navController = Navigation.findNavController(getActivity(), R.id.nav_host_fragment);
//                navController.navigate(R.id.nav_taxi_finish_order_fragment, bundle);
//            }
//        }, 5000);

        btnCancelTaxi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openDialogCancelTaxi();
            }
        });

        llBtnCallDriver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse
                        ("tel: " + taxiWorker.getPhone()));
                if (intent != null) {
                    startActivity(intent);
                }
            }
        });

        return view;
    }

    private void openDialogCancelTaxi() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = inflater.inflate(R.layout.dialog_cancel_taxi, null);

        Button btnCancelTaxiDialog = v.findViewById(R.id.btnCancelTaxiDialog);
        Button btnTaxiNo = v.findViewById(R.id.btnCancelTaxiNo);

        builder
                .setView(v)
                .setCancelable(false)
        ;
        final AlertDialog alert2 = builder.create();

        btnCancelTaxiDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        btnTaxiNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alert2.dismiss();
            }
        });

        alert2.show();

    }

    private void checkPermition() {
        int ACCESS_FINE_LOCATION = ContextCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION);
        int ACCESS_COARSE_LOCATION = ContextCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_COARSE_LOCATION);
        int WRITE_EXTERNAL_STORAGE = ContextCompat.checkSelfPermission(getContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE);
        Log.d("TAG", +ACCESS_FINE_LOCATION + " checkPermition() " + ACCESS_COARSE_LOCATION + "" + WRITE_EXTERNAL_STORAGE);
        if (ACCESS_FINE_LOCATION == PackageManager.PERMISSION_GRANTED
                && ACCESS_COARSE_LOCATION == PackageManager.PERMISSION_GRANTED
                && WRITE_EXTERNAL_STORAGE == PackageManager.PERMISSION_GRANTED) {

            mapView.createMap();
        } else {
            Log.d("TAG", "" + "DONT " + Manifest.permission.ACCESS_COARSE_LOCATION);
            ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.ACCESS_FINE_LOCATION,
                            Manifest.permission.ACCESS_COARSE_LOCATION,
                            Manifest.permission.WRITE_EXTERNAL_STORAGE},
                    REQUEST_CODE_PERMISSION_READ_CONTACTS);
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        mapView.getMapView().onPause();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            if (ContextCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                Log.d("TAG", "" + "OK " + Manifest.permission.ACCESS_COARSE_LOCATION);
                mapView.createMap();
            }
        }
    }
}
