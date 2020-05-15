package encityproject.rightcodeit.com.encityproject.ui.taxiClient;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Interpolator;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import org.osmdroid.bonuspack.BuildConfig;
import org.osmdroid.bonuspack.routing.OSRMRoadManager;
import org.osmdroid.config.Configuration;
import org.osmdroid.util.GeoPoint;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import encityproject.rightcodeit.com.encityproject.R;
import encityproject.rightcodeit.com.encityproject.ui.busTracker.GetAddressTask;
import encityproject.rightcodeit.com.encityproject.ui.market.adapter.inner_adapter.CloudMarketAdapter;
import encityproject.rightcodeit.com.encityproject.ui.taxiClient.Adapters.MyAdressAdapter;

import static android.support.constraint.Constraints.TAG;

/**
 * A simple {@link Fragment} subclass.
 */
public class TaxiOrderFragment extends Fragment {
    private static final int REQUEST_CODE_PERMISSION_READ_CONTACTS = 123;
    private CustomMapView mapView;
    private EditText etAddress;
    private ImageView ivAdressNext, ivMyPlace;
    private RecyclerView rvMyAdress;
    private MyAdressAdapter myAdressAdapter;
    private ArrayList<String> listAdress;

    public TaxiOrderFragment() {
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
        View v = inflater.inflate(R.layout.fragment_taxi_order, container, false);
        listAdress=new ArrayList<>();
        listAdress.add("Будівельників 14, под'їзд 3");
        listAdress.add("Молодіжна 6, под'їзд 1");
        listAdress.add("Не використана");
        etAddress = v.findViewById(R.id.et_address);
        ivAdressNext=v.findViewById(R.id.ivAdressNext);
        ivMyPlace=v.findViewById(R.id.ivMyPlace);
        ivAdressNext.setColorFilter(Color.rgb(0,230,19));
        rvMyAdress=v.findViewById(R.id.rvMyAdress);
        rvMyAdress.setLayoutManager(new GridLayoutManager(getActivity(), 3, GridLayoutManager.VERTICAL, false));
        myAdressAdapter = new MyAdressAdapter(getContext(),getActivity() ,listAdress);
        rvMyAdress.setAdapter(myAdressAdapter);
        mapView = new CustomMapView(v, getContext(), this);
        checkPermition();
        findNameHouse();

        ivAdressNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                 bundle.putString("place", etAddress.getText().toString());
                //    Toast.makeText(context, caList.get(pos).split("@.#")[0], Toast.LENGTH_SHORT).show();
                NavController navController = Navigation.findNavController(getActivity(), R.id.nav_host_fragment);
                navController.navigate(R.id.nav_taxi_confirm_order_fragment, bundle);
            }
        });
        return v;
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

    public void findNameHouse() {
        LocationManager mLocationManager = (LocationManager)
                getActivity().getSystemService(Context.LOCATION_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION)
                    != PackageManager.PERMISSION_GRANTED
                    && ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_COARSE_LOCATION)
                    != PackageManager.PERMISSION_GRANTED) {
                Log.d("TAG", "проверку не прошел");
                return;
            }
        }
        Log.d("TAG", "прошел");
        mLocationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 5000, 10,locationListener);
        Location locationGPS = mLocationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        GeoPoint myPosition = new GeoPoint(locationGPS.getLatitude(), locationGPS.getLongitude());
        Float latit = new BigDecimal(locationGPS.getLatitude()).setScale(4, BigDecimal.ROUND_HALF_UP).floatValue();
        Float longi = new BigDecimal(locationGPS.getLongitude()).setScale(4, BigDecimal.ROUND_HALF_UP).floatValue();

        Log.d(TAG, locationGPS.getLatitude() + " " + locationGPS.getLongitude());
        new GetAddressTask(this).execute(String.valueOf(latit), String.valueOf(longi));
    }

    public void callBackDataFromAsyncTask(String address) {
   //     Toast.makeText(getActivity(), "" + address, Toast.LENGTH_LONG).show();
        etAddress.setText(address);
    }

    private final LocationListener locationListener = new LocationListener() {
        @Override
        public void onLocationChanged(Location location) {

        }

        @Override
        public void onStatusChanged(String s, int i, Bundle bundle) {

        }

        @Override
        public void onProviderEnabled(String s) {

        }

        @Override
        public void onProviderDisabled(String s) {

        }
    };
}
