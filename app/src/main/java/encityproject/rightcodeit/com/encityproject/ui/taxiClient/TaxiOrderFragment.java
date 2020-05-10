package encityproject.rightcodeit.com.encityproject.ui.taxiClient;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Interpolator;
import android.widget.EditText;
import android.widget.Toast;

import org.osmdroid.bonuspack.BuildConfig;
import org.osmdroid.bonuspack.routing.OSRMRoadManager;
import org.osmdroid.config.Configuration;
import org.osmdroid.util.GeoPoint;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.List;

import encityproject.rightcodeit.com.encityproject.R;
import encityproject.rightcodeit.com.encityproject.ui.busTracker.GetAddressTask;

import static android.support.constraint.Constraints.TAG;

/**
 * A simple {@link Fragment} subclass.
 */
public class TaxiOrderFragment extends Fragment {
    private static final int REQUEST_CODE_PERMISSION_READ_CONTACTS = 123;
    private CustomMapView mapView;
    private EditText etAddress;

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
        etAddress = v.findViewById(R.id.et_address);
        mapView = new CustomMapView(v, getContext(), this);
        checkPermition();
        findNameHouse();
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
        Location locationGPS = mLocationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        GeoPoint myPosition = new GeoPoint(locationGPS.getLatitude(), locationGPS.getLongitude());
        Float latit = new BigDecimal(locationGPS.getLatitude()).setScale(4, BigDecimal.ROUND_HALF_UP).floatValue();
        Float longi = new BigDecimal(locationGPS.getLongitude()).setScale(4, BigDecimal.ROUND_HALF_UP).floatValue();

        Log.d(TAG, locationGPS.getLatitude() + " " + locationGPS.getLongitude());
        new GetAddressTask(this).execute(String.valueOf(latit), String.valueOf(longi));
    }

    public void callBackDataFromAsyncTask(String address) {
        Toast.makeText(getActivity(), "" + address, Toast.LENGTH_LONG).show();
        etAddress.setText(address);
    }
}
