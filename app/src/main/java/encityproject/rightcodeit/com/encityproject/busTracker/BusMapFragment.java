package encityproject.rightcodeit.com.encityproject.busTracker;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.Point;
import android.location.Location;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
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
import android.widget.Toast;

import com.getbase.floatingactionbutton.FloatingActionButton;

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
import org.osmdroid.views.overlay.Polyline;
import org.osmdroid.views.overlay.ScaleBarOverlay;
import org.osmdroid.views.overlay.compass.CompassOverlay;
import org.osmdroid.views.overlay.compass.InternalCompassOrientationProvider;
import org.osmdroid.views.overlay.gestures.RotationGestureOverlay;
import org.osmdroid.views.overlay.mylocation.GpsMyLocationProvider;
import org.osmdroid.views.overlay.mylocation.MyLocationNewOverlay;

import java.util.ArrayList;
import java.util.List;

import encityproject.rightcodeit.com.encityproject.R;

public class BusMapFragment extends Fragment implements View.OnClickListener{
    private static final int REQUEST_CODE_PERMISSION_READ_CONTACTS = 123;
    private ScaleBarOverlay mScaleBarOverlay;
    private RotationGestureOverlay mRotationGestureOverlay;
    private MapView map = null;
    private MapController mapController = null;
    private MyLocationNewOverlay locationOverlay;
    private CompassOverlay compassOverlay;
    private RoadManager roadManager;
    private Marker hideMarke;
    private Interpolator interpolator;
    private FloatingActionButton fubBusTrack1;
    private FloatingActionButton fubBusTrack2;
    private FloatingActionButton fubBusTrack3;
    private ArrayList<GeoPoint> geoPoints;
    private long start;
    private long duration;
    private Handler h;
    Handler.Callback hc = new Handler.Callback() {
        public boolean handleMessage(Message msg) {
            Log.d("TAG", "loadRoad" + msg);
            animateMarker(map, hideMarke, geoPoints.get(msg.what));
            return false;
        }
    };

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Configuration.getInstance().setUserAgentValue(BuildConfig.APPLICATION_ID);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_bus_map, container, false);
        fubBusTrack1 = view.findViewById(R.id.fab_action1);
        fubBusTrack1.setOnClickListener(this);
        fubBusTrack2 = view.findViewById(R.id.fab_action2);
        fubBusTrack2.setOnClickListener(this);
        fubBusTrack3 = view.findViewById(R.id.fab_action3);
        fubBusTrack3.setOnClickListener(this);
        h = new Handler();
        h = new Handler(hc);
        geoPoints = new ArrayList<>();
        loadGeo();
        duration = 2500;
        start = SystemClock.uptimeMillis();
        interpolator = new LinearInterpolator();
        map = view.findViewById(R.id.map);
        map.setTileSource(TileSourceFactory.MAPNIK);
        Configuration.getInstance().load(getActivity(), PreferenceManager.getDefaultSharedPreferences(getContext()));
        checkPermition();

        return view;
    }

    private void checkPermition() {
        int ACCESS_FINE_LOCATION = ContextCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION);
        int ACCESS_COARSE_LOCATION = ContextCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_COARSE_LOCATION);
        int WRITE_EXTERNAL_STORAGE = ContextCompat.checkSelfPermission(getContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE);
        Log.d("TAG", +ACCESS_FINE_LOCATION + " checkPermition() " + ACCESS_COARSE_LOCATION + "" + WRITE_EXTERNAL_STORAGE);
        if (ACCESS_FINE_LOCATION == PackageManager.PERMISSION_GRANTED
                && ACCESS_COARSE_LOCATION == PackageManager.PERMISSION_GRANTED
                && WRITE_EXTERNAL_STORAGE == PackageManager.PERMISSION_GRANTED) {
//            mapFragment.getMapAsync(this);
            createMap();
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
        locationOverlay.enableFollowLocation();
        locationOverlay.setDrawAccuracyEnabled(true);
        locationOverlay.setOptionsMenuEnabled(true);

        roadManager = new OSRMRoadManager(getContext());
        final DisplayMetrics dm = this.getResources().getDisplayMetrics();
        mScaleBarOverlay = new ScaleBarOverlay(map);
        mScaleBarOverlay.setCentred(true);
        mScaleBarOverlay.setScaleBarOffset(dm.widthPixels / 2, 10);
        mRotationGestureOverlay = new RotationGestureOverlay(getContext(), map);
        mRotationGestureOverlay.setEnabled(true);
        hideMarke = new Marker(map);
        compassOverlay = new CompassOverlay(getContext(),
                new InternalCompassOrientationProvider(getContext()), map);
        compassOverlay.enableCompass();
        map.getOverlays().add(mScaleBarOverlay);
        map.getOverlays().add(compassOverlay);
        map.getOverlays().add(locationOverlay);

        mapController = (MapController) map.getController();
        mapController.setZoom(16);
        final ArrayList<OverlayItem> items = new ArrayList<OverlayItem>();

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
        final long duration = 500;

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
//
//    public void onPause() {
//        super.onPause();
//        map.onPause();  //needed for compass, my location overlays, v6.0.0 and up
//    }

    private void loadRoad() {
        for (int i = 1; i <= 25; i++) {
            Log.d("TAG", "loadRoad" + i);
            h.sendEmptyMessageDelayed(i, (i * 1000) + 1000);
        }


    }

    private ArrayList<GeoPoint> loadGeo() {
        geoPoints.add(new GeoPoint(47.49686, 34.64888));
        geoPoints.add(new GeoPoint(47.49485, 34.65054));
        geoPoints.add(new GeoPoint(47.49315, 34.65174));
        geoPoints.add(new GeoPoint(47.4907, 34.6535));
        geoPoints.add(new GeoPoint(47.48854, 34.65502));
        geoPoints.add(new GeoPoint(47.48689, 34.65611));
        geoPoints.add(new GeoPoint(47.48664, 34.65616));
        geoPoints.add(new GeoPoint(47.48424, 34.6552));
        geoPoints.add(new GeoPoint(47.48347, 34.65922));
        geoPoints.add(new GeoPoint(47.48295, 34.66191));
        geoPoints.add(new GeoPoint(47.48572, 34.66308));
        geoPoints.add(new GeoPoint(47.48703, 34.6636));
        geoPoints.add(new GeoPoint(47.49016, 34.66105));
        geoPoints.add(new GeoPoint(47.4907, 34.66069));
        geoPoints.add(new GeoPoint(47.49144, 34.66254));
        geoPoints.add(new GeoPoint(47.49351, 34.6608));
        geoPoints.add(new GeoPoint(47.4958, 34.65889));
        geoPoints.add(new GeoPoint(47.49764, 34.65737));
        geoPoints.add(new GeoPoint(47.49896, 34.6563));
        geoPoints.add(new GeoPoint(47.49984, 34.65561));
        geoPoints.add(new GeoPoint(47.49879, 34.65283));
        geoPoints.add(new GeoPoint(47.49719, 34.64867));
        geoPoints.add(new GeoPoint(47.49703, 34.64846));
        geoPoints.add(new GeoPoint(47.49692, 34.64862));
        geoPoints.add(new GeoPoint(47.49694, 34.64879));
        geoPoints.add(new GeoPoint(47.49669, 34.64897));
        return geoPoints;
    }

    private void lineRedDirection1() {
        List<GeoPoint> geoPoints = new ArrayList<>();
        geoPoints.add(new GeoPoint(47.49686, 34.64888));
        geoPoints.add(new GeoPoint(47.49485, 34.65054));
        geoPoints.add(new GeoPoint(47.49315, 34.65174));
        geoPoints.add(new GeoPoint(47.4907, 34.6535));
        geoPoints.add(new GeoPoint(47.48854, 34.65502));
        geoPoints.add(new GeoPoint(47.48689, 34.65611));
        geoPoints.add(new GeoPoint(47.48664, 34.65616));
        geoPoints.add(new GeoPoint(47.48424, 34.6552));
        geoPoints.add(new GeoPoint(47.48347, 34.65922));
        geoPoints.add(new GeoPoint(47.48295, 34.66191));
        geoPoints.add(new GeoPoint(47.48572, 34.66308));
        geoPoints.add(new GeoPoint(47.48703, 34.6636));
        geoPoints.add(new GeoPoint(47.49016, 34.66105));
        geoPoints.add(new GeoPoint(47.4907, 34.66069));
        geoPoints.add(new GeoPoint(47.49144, 34.66254));
        geoPoints.add(new GeoPoint(47.49351, 34.6608));
        geoPoints.add(new GeoPoint(47.4958, 34.65889));
        geoPoints.add(new GeoPoint(47.49764, 34.65737));
        geoPoints.add(new GeoPoint(47.49896, 34.6563));
        geoPoints.add(new GeoPoint(47.49984, 34.65561));
        geoPoints.add(new GeoPoint(47.49879, 34.65283));
        geoPoints.add(new GeoPoint(47.49719, 34.64867));
        geoPoints.add(new GeoPoint(47.49703, 34.64846));
        geoPoints.add(new GeoPoint(47.49692, 34.64862));
        geoPoints.add(new GeoPoint(47.49694, 34.64879));
        geoPoints.add(new GeoPoint(47.49669, 34.64897));
        Polyline line = new Polyline();   //see note below!
        line.setPoints(geoPoints);
        line.setOnClickListener((polyline, mapView, eventPos) -> {
            Toast.makeText(mapView.getContext(), "polyline with " + polyline.getPoints().size() + "pts was tapped", Toast.LENGTH_LONG).show();
            return false;
        });
        line.setColor(Color.YELLOW);
        map.getOverlayManager().add(line);
        map.invalidate();
    }

    private void lineRedDirection2() {
        List<GeoPoint> geoPoints = new ArrayList<>();
        geoPoints.add(new GeoPoint(47.49686, 34.64888));
        geoPoints.add(new GeoPoint(47.49485, 34.65054));
        geoPoints.add(new GeoPoint(47.49315, 34.65174));
        geoPoints.add(new GeoPoint(47.4907, 34.6535));
        geoPoints.add(new GeoPoint(47.48854, 34.65502));
        geoPoints.add(new GeoPoint(47.48689, 34.65611));
        geoPoints.add(new GeoPoint(47.48664, 34.65616));
        geoPoints.add(new GeoPoint(47.48424, 34.6552));
        geoPoints.add(new GeoPoint(47.48347, 34.65922));
        geoPoints.add(new GeoPoint(47.48295, 34.66191));
        geoPoints.add(new GeoPoint(47.48572, 34.66308));
        geoPoints.add(new GeoPoint(47.48703, 34.6636));
        geoPoints.add(new GeoPoint(47.49016, 34.66105));
        geoPoints.add(new GeoPoint(47.4907, 34.66069));
        geoPoints.add(new GeoPoint(47.49144, 34.66254));
        geoPoints.add(new GeoPoint(47.49351, 34.6608));
        geoPoints.add(new GeoPoint(47.4958, 34.65889));
        geoPoints.add(new GeoPoint(47.49764, 34.65737));
        geoPoints.add(new GeoPoint(47.49896, 34.6563));
        geoPoints.add(new GeoPoint(47.49984, 34.65561));
        geoPoints.add(new GeoPoint(47.49879, 34.65283));
        geoPoints.add(new GeoPoint(47.49719, 34.64867));
        geoPoints.add(new GeoPoint(47.49703, 34.64846));
        geoPoints.add(new GeoPoint(47.49692, 34.64862));
        geoPoints.add(new GeoPoint(47.49694, 34.64879));
        geoPoints.add(new GeoPoint(47.49669, 34.64897));
        ;
        Polyline line = new Polyline();   //see note below!
        line.setPoints(geoPoints);
        line.setOnClickListener((polyline, mapView, eventPos) -> {
            Toast.makeText(mapView.getContext(), "polyline with " + polyline.getPoints().size() + " pts was tapped", Toast.LENGTH_LONG).show();
            return false;
        });
        line.setColor(Color.RED);
        map.getOverlayManager().add(line);
        map.invalidate();
    }



    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.fab_action1:
                Log.d("TAG", "ic_center_map");
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
                map.getController().animateTo(myPosition);
                break;
            case R.id.fab_action2:
                Log.d("TAG", "ic_follow_me");
                if (!locationOverlay.isFollowLocationEnabled()) {
                    locationOverlay.enableFollowLocation();
//                    btFollowMe.setImageResource(R.drawable.ic_follow_me_on);
                } else {
                    locationOverlay.disableFollowLocation();
//                    btFollowMe.setImageResource(R.drawable.ic_follow_me);
                }
                break;
        }
    }
}
