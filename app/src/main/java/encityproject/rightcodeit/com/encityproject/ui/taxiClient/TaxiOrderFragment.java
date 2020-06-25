
package encityproject.rightcodeit.com.encityproject.ui.taxiClient;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
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
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;

import org.osmdroid.bonuspack.BuildConfig;
import org.osmdroid.config.Configuration;
import org.osmdroid.util.GeoPoint;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import encityproject.rightcodeit.com.encityproject.MainActivityWithNaviDrawer;
import encityproject.rightcodeit.com.encityproject.R;
import encityproject.rightcodeit.com.encityproject.ui.busTracker.GetAddressTask;
import encityproject.rightcodeit.com.encityproject.ui.taxiClient.Adapters.AnyAddressAdapter;
import encityproject.rightcodeit.com.encityproject.ui.taxiClient.Adapters.MyAdressAdapter;
import encityproject.rightcodeit.com.encityproject.ui.taxiClient.Models.AddressOrder;
import encityproject.rightcodeit.com.encityproject.ui.taxiClient.Models.mvp.GpsTracker;

import static android.content.Context.LOCATION_SERVICE;
import static android.support.constraint.Constraints.TAG;


public class TaxiOrderFragment extends Fragment {
    private Location l;
    private static final String TAG1 = "TAG";
    private MainActivityWithNaviDrawer activity;
    private static final int REQUEST_CODE_PERMISSION_READ_CONTACTS = 123;
    private CustomMapView mapView;
    private EditText etAddress;
    private ImageView ivAdressNext, ivMyPlace;
    private RecyclerView rvMyAdress;
    private MyAdressAdapter myAdressAdapter;
    private ArrayList<AddressOrder> listAdress;
    private GeoPoint myPosition;
    private LocationManager mLocationManager;
    public TaxiOrderFragment() {

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
        activity = (MainActivityWithNaviDrawer) getActivity();
        listAdress=new ArrayList<>();
        listAdress.add(new AddressOrder("Молодіжна 12","2"));
        listAdress.add(new AddressOrder("Будівкельників 10","4"));
        listAdress.add(new AddressOrder("Не використана", ""));
        etAddress = v.findViewById(R.id.et_address);
        ivAdressNext=v.findViewById(R.id.ivAdressNext);
        ivMyPlace=v.findViewById(R.id.ivMyPlace);
        ivAdressNext.setColorFilter(Color.rgb(0,230,19));
        rvMyAdress=v.findViewById(R.id.rvMyAdress);
        rvMyAdress.setLayoutManager(new GridLayoutManager(getActivity(), 3, GridLayoutManager.VERTICAL, false));
        myAdressAdapter = new MyAdressAdapter(getContext(),getActivity() ,listAdress);
        rvMyAdress.setAdapter(myAdressAdapter);
        mapView = new CustomMapView(v, getContext(), this);
        Float latit = new BigDecimal(47.490459).setScale(4, BigDecimal.ROUND_HALF_UP).floatValue();
        Float longi = new BigDecimal(34.660989).setScale(4, BigDecimal.ROUND_HALF_UP).floatValue();
        new GetAddressTask(this).execute(String.valueOf(latit), String.valueOf(longi));

        checkPermition();

        //запустить gps и показать на карте маркер клиента
        ivMyPlace.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!isGeoDisabled()) {
                    findNameHouse();
                }
                else{
                    startActivity(new Intent(android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS));
                }
            }
        });


        //переход к выбору подъезда и затем подтверждение заказа в TaxiConfirmFragment
        ivAdressNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(etAddress.getText().length()>5) {
                    Bundle bundle = new Bundle();
                    bundle.putString("place", etAddress.getText().toString());
                    bundle.putDouble("Latit",  l.getLatitude());
                    bundle.putDouble("Longit",  l.getLongitude());
                    openDialogNumberDoor(etAddress.getText().toString(), bundle);
                }

            }
        });
        return v;
    }

    public boolean isGeoDisabled() {
        LocationManager mLocationManager = (LocationManager) getContext().getSystemService(LOCATION_SERVICE);
        boolean mIsGPSEnabled = mLocationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
        boolean mIsNetworkEnabled = mLocationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
        boolean mIsGeoDisabled = !mIsGPSEnabled && !mIsNetworkEnabled;
        return mIsGeoDisabled;
    }

    private void openDialogNumberDoor(String toString, Bundle bundle) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService( Context.LAYOUT_INFLATER_SERVICE );
        View v = inflater.inflate( R.layout.dialog_door_house_fragment, null );
        ArrayList<String> list = new ArrayList<>();
        list.add("1");
        list.add("2");
        list.add("3");
        list.add("4");
        list.add("5");
        list.add("6");

        builder
                .setView(v)
                .setCancelable(true)
        ;
        final AlertDialog alert2 = builder.create();

        AnyAddressAdapter mAdapter=new AnyAddressAdapter(getContext(),getActivity(),list, bundle, alert2);
        RecyclerView rvDoor= v.findViewById(R.id.rvDoor);
        rvDoor.setLayoutManager(new GridLayoutManager(getActivity(), 3, GridLayoutManager.VERTICAL, false));
        rvDoor.setAdapter(mAdapter);

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

    public void findNameHouse() {
        mLocationManager = (LocationManager)
                getActivity().getSystemService(LOCATION_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION)
                    != PackageManager.PERMISSION_GRANTED
                    && ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_COARSE_LOCATION)
                    != PackageManager.PERMISSION_GRANTED) {
                Log.d("TAG", "проверку не прошел");
                return;
            }
        }
        Log.d("TAG", "прошел");
        //Location locationGPS = mLocationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        Location locationGPS = getLastKnownLocation();
        if(locationGPS!=null) {
        //    mLocationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 5000, 10, locationListener);
            myPosition = new GeoPoint(locationGPS.getLatitude(), locationGPS.getLongitude());
            Float latit = new BigDecimal(locationGPS.getLatitude()).setScale(4, BigDecimal.ROUND_HALF_UP).floatValue();
            Float longi = new BigDecimal(locationGPS.getLongitude()).setScale(4, BigDecimal.ROUND_HALF_UP).floatValue();

            Log.d(TAG, locationGPS.getLatitude() + " " + locationGPS.getLongitude());
            new GetAddressTask(this).execute(String.valueOf(latit), String.valueOf(longi));
        }
    }

    public void callBackDataFromAsyncTask(String address) {
   //     Toast.makeText(getActivity(), "" + address, Toast.LENGTH_LONG).show();
        etAddress.setText(address.split(",")[0]+" "+address.split(",")[1]);
    }

    private Location getLastKnownLocation() {
//        mLocationManager = (LocationManager) getContext().getSystemService(LOCATION_SERVICE);
//        List<String> providers = mLocationManager.getProviders(true);
//        Location bestLocation = null;
//        for (String provider : providers) {
//            @SuppressLint("MissingPermission")
//            Location l = mLocationManager.getLastKnownLocation(provider);
//            if (l == null) {
//                continue;
//            }
//            if (bestLocation == null || l.getAccuracy() < bestLocation.getAccuracy()) {
//                // Found best last known location: %s", l);
//                bestLocation = l;
//            }
//        }
        GpsTracker gt = new GpsTracker(getContext());
        l = gt.getLocation();
        if( l == null){
            //  Toast.makeText(getApplicationContext(),"GPS unable to get Value",Toast.LENGTH_SHORT).show();
        }else {
            return l;
            // Toast.makeText(getApplicationContext(),"GPS Lat = "+latit+"\n lon = "+longi,Toast.LENGTH_SHORT).show();
        }
        return null;
    }

    private final LocationListener locationListener = new LocationListener() {
        @Override
        public void onLocationChanged(Location location) {
            myPosition = new GeoPoint(location.getLatitude(), location.getLongitude());
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
