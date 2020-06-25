package encityproject.rightcodeit.com.encityproject.ui.busTracker;

import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.Point;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
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
import android.view.animation.LinearInterpolator;

import com.getbase.floatingactionbutton.FloatingActionButton;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallbackExtended;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.osmdroid.api.IGeoPoint;
import org.osmdroid.bonuspack.BuildConfig;
import org.osmdroid.bonuspack.routing.OSRMRoadManager;
import org.osmdroid.bonuspack.routing.RoadManager;
import org.osmdroid.config.Configuration;
import org.osmdroid.events.MapEventsReceiver;
import org.osmdroid.tileprovider.tilesource.TileSourceFactory;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapController;
import org.osmdroid.views.MapView;
import org.osmdroid.views.Projection;
import org.osmdroid.views.overlay.MapEventsOverlay;
import org.osmdroid.views.overlay.Marker;
import org.osmdroid.views.overlay.OverlayItem;
import org.osmdroid.views.overlay.ScaleBarOverlay;
import org.osmdroid.views.overlay.compass.CompassOverlay;
import org.osmdroid.views.overlay.compass.InternalCompassOrientationProvider;
import org.osmdroid.views.overlay.gestures.RotationGestureOverlay;
import org.osmdroid.views.overlay.mylocation.GpsMyLocationProvider;
import org.osmdroid.views.overlay.mylocation.MyLocationNewOverlay;

import java.math.BigDecimal;
import java.util.ArrayList;

import encityproject.rightcodeit.com.encityproject.R;
import encityproject.rightcodeit.com.encityproject.ui.busTracker.model.DataRouteBusAndBusStop;

import static android.content.Context.LOCATION_SERVICE;

public class BusMapFragment extends Fragment implements View.OnClickListener{
    private MqttHelper mqttHelper;
    private static final int REQUEST_CODE_PERMISSION_READ_CONTACTS = 123;
    private ScaleBarOverlay mScaleBarOverlay;
    private RotationGestureOverlay mRotationGestureOverlay;
    private MapView map = null;
    private MapController mapController = null;
    private MyLocationNewOverlay locationOverlay;
    private CompassOverlay compassOverlay;
    private RoadManager roadManager;
    private Marker busMarker_1;
    private Interpolator interpolator;
    private FloatingActionButton fubBusTrack1;
    private FloatingActionButton fubBusTrack2;
    private FloatingActionButton fubBusTrack3;
    private FloatingActionButton fubBusTrack4;
    private FloatingActionButton fubBusTrack5;
    private ArrayList<GeoPoint> geoPoints;
    private long start;
    private long duration;
    private GeoPoint myPosition;
    private LocationManager mLocationManager;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Configuration.getInstance().setUserAgentValue(BuildConfig.APPLICATION_ID);
}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_bus_map, container, false);
        fubBusTrack1 = view.findViewById(R.id.fab_action1);
        fubBusTrack1.setOnClickListener(this);
        fubBusTrack2 = view.findViewById(R.id.fab_action2);
        fubBusTrack2.setOnClickListener(this);
        fubBusTrack3 = view.findViewById(R.id.fab_action3);
        fubBusTrack3.setOnClickListener(this);
        fubBusTrack4 = view.findViewById(R.id.fab_action4);
        fubBusTrack4.setOnClickListener(this);
        fubBusTrack5 = view.findViewById(R.id.fab_action5);
        fubBusTrack5.setOnClickListener(this);
        geoPoints = new ArrayList<>();
        duration = 2500;
        start = SystemClock.uptimeMillis();
        interpolator = new LinearInterpolator();
        map = view.findViewById(R.id.map);
        map.setTileSource(TileSourceFactory.MAPNIK);
        Configuration.getInstance().load(getActivity(), PreferenceManager.getDefaultSharedPreferences(getContext()));
        checkPermition();
        startMqtt();

        return view;
    }

    public void findGPS(double lat, double lon) {
        mLocationManager = (LocationManager)
                getActivity().getSystemService(LOCATION_SERVICE);
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
        //Location locationGPS = mLocationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
      //  Location locationGPS = getLastKnownLocation();

            //    mLocationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 5000, 10, locationListener);
       //     myPosition = new GeoPoint(locationGPS.getLatitude(), locationGPS.getLongitude());
            Float latit = new BigDecimal(lat).setScale(4, BigDecimal.ROUND_HALF_UP).floatValue();
            Float longi = new BigDecimal(lon).setScale(4, BigDecimal.ROUND_HALF_UP).floatValue();



    }

    private void startMqtt() {
        mqttHelper = new MqttHelper(getContext());
        mqttHelper.setCallback(new MqttCallbackExtended() {
            @Override
            public void connectComplete(boolean b, String s) {

            }

            @Override
            public void connectionLost(Throwable throwable) {

            }

            @Override
            public void messageArrived(String topic, MqttMessage mqttMessage) throws Exception {
              //  Log.w("Debug",mqttMessage.toString());
                Log.d("bus", mqttMessage.toString());
                JsonObject jsonObject = new JsonParser().parse(mqttMessage.toString()).getAsJsonObject();
             //   hideMarke.setPosition(new GeoPoint(47.496618, 34.649008));
   /*            hideMarke.setPosition(new GeoPoint(
                        Double.parseDouble(jsonObject.get("position.latitude").getAsString()),
                        Double.parseDouble(jsonObject.get("position.longitude").getAsString())));*/
               /* findGPS(Double.parseDouble(jsonObject.get("position.latitude").getAsString()),
                        Double.parseDouble(jsonObject.get("position.longitude").getAsString()));*/
                map.getOverlays().add(busMarker_1);
                map.invalidate();
                //TODO здесь приходят координаты автобуса
                animateMarker(map, busMarker_1, new GeoPoint(Double.parseDouble(jsonObject.get("position.latitude").getAsString()),
                        Double.parseDouble(jsonObject.get("position.longitude").getAsString())));

            }

            @Override
            public void deliveryComplete(IMqttDeliveryToken iMqttDeliveryToken) {

            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.fab_action1:
                DataRouteBusAndBusStop.routeBusTwo(map);
                break;
            case R.id.fab_action2:
                DataRouteBusAndBusStop.routeBusThree(map);
                break;
            case R.id.fab_action3:
                DataRouteBusAndBusStop.routeBusFour(map);
                break;
            case R.id.fab_action4:
                DataRouteBusAndBusStop.routeBusFive(map);
                break;
            case R.id.fab_action5:
                DataRouteBusAndBusStop.routeBusSeven(map);
                break;
        }
    }

    private void checkPermition() {
        int ACCESS_FINE_LOCATION = ContextCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION);
        int ACCESS_COARSE_LOCATION = ContextCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_COARSE_LOCATION);
        int WRITE_EXTERNAL_STORAGE = ContextCompat.checkSelfPermission(getContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE);
        Log.d("TAG", +ACCESS_FINE_LOCATION + " checkPermition() " + ACCESS_COARSE_LOCATION + "" + WRITE_EXTERNAL_STORAGE);
        if (ACCESS_FINE_LOCATION == PackageManager.PERMISSION_GRANTED
                && ACCESS_COARSE_LOCATION == PackageManager.PERMISSION_GRANTED
                && WRITE_EXTERNAL_STORAGE == PackageManager.PERMISSION_GRANTED) {
            createMap();
            Float latit = new BigDecimal(47.490459).setScale(4, BigDecimal.ROUND_HALF_UP).floatValue();
            Float longi = new BigDecimal(34.660989).setScale(4, BigDecimal.ROUND_HALF_UP).floatValue();
        } else {
            Log.d("TAG", "" + "DONT " + Manifest.permission.ACCESS_COARSE_LOCATION);
            ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.WRITE_EXTERNAL_STORAGE},
                    REQUEST_CODE_PERMISSION_READ_CONTACTS);
        }
    }

    private void createMap() {
        Log.d("TAG", "createMap()");

        map.setTileSource(TileSourceFactory.MAPNIK);
        map.setMultiTouchControls(true);
        map.setTilesScaledToDpi(true);
        map.setBuiltInZoomControls(true);
        map.setFlingEnabled(true);

        locationOverlay = new MyLocationNewOverlay(new GpsMyLocationProvider(getContext()), map);
        locationOverlay.enableMyLocation();
//        locationOverlay.enableFollowLocation();
        locationOverlay.setDrawAccuracyEnabled(true);
        locationOverlay.setOptionsMenuEnabled(true);
//47.4886911,34.6576792 centre city
        roadManager = new OSRMRoadManager(getContext());
        final DisplayMetrics dm = this.getResources().getDisplayMetrics();
        mScaleBarOverlay = new ScaleBarOverlay(map);
        mScaleBarOverlay.setCentred(true);
        mScaleBarOverlay.setScaleBarOffset(dm.widthPixels / 2, 10);
        mRotationGestureOverlay = new RotationGestureOverlay(getContext(), map);
        mRotationGestureOverlay.setEnabled(true);
        busMarker_1 = new Marker(map);
        compassOverlay = new CompassOverlay(getContext(),
                new InternalCompassOrientationProvider(getContext()), map);
        compassOverlay.enableCompass();
        map.getOverlays().add(mScaleBarOverlay);
        map.getOverlays().add(compassOverlay);
        map.getOverlays().add(locationOverlay);

        mapController = (MapController) map.getController();
        mapController.setZoom(15);
        final ArrayList<OverlayItem> items = new ArrayList<OverlayItem>();
        map.getController().animateTo(new GeoPoint(47.4886911,34.6576792));
        items.add(new OverlayItem("Title", "Description", new GeoPoint(0.0d, 0.0d))); // Lat/Lon decimal degrees
        map.getOverlays().add(new MapEventsOverlay(new MapEventsReceiver() {
            @Override
            public boolean singleTapConfirmedHelper(GeoPoint p) {
                return true;
            }

            @Override
            public boolean longPressHelper(GeoPoint p) {
                return false;
            }
        }));

    }

    public static void animateMarker(final MapView map, final Marker marker, final GeoPoint toPosition) {
        Log.d("TAG", "animateMarker " + toPosition.getLatitude() + " =---" + toPosition.getLongitude());
        final Handler handler = new Handler();
        final long start = SystemClock.uptimeMillis();
        Projection proj = map.getProjection();
        Point startPoint = proj.toPixels(marker.getPosition(), null);
        final IGeoPoint startLatLng = proj.fromPixels(startPoint.x, startPoint.y);
        final long duration = 1000;

        final Interpolator interpolator = new LinearInterpolator();

        handler.post(new Runnable() {
            @Override
            public void run() {
                long elapsed = SystemClock.uptimeMillis() - start;
                float t = interpolator.getInterpolation((float) elapsed / duration);
                double lng = t * toPosition.getLongitude() + (1 - t) * startLatLng.getLongitude();
                double lat = t * toPosition.getLatitude() + (1 - t) * startLatLng.getLatitude();

                marker.setPosition(new GeoPoint(lat, lng));

                if (t < 1.0) {
                    // Post again 16ms later.
                    handler.postDelayed(this, 16);
                }
                map.postInvalidate();
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            if (ContextCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                Log.d("TAG", "" + "OK " + Manifest.permission.ACCESS_COARSE_LOCATION);
                createMap();
            }
        }
    }
    public void onResume() {
        super.onResume();
        Configuration.getInstance().load(getContext(), PreferenceManager.getDefaultSharedPreferences(getActivity())); //needed for compass, my location overlays, v6.0.0 and up
    }
}
