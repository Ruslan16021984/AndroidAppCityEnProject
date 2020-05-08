package encityproject.rightcodeit.com.encityproject.ui.taxiClient;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.preference.PreferenceManager;
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

import org.osmdroid.bonuspack.BuildConfig;
import org.osmdroid.bonuspack.routing.OSRMRoadManager;
import org.osmdroid.bonuspack.routing.RoadManager;
import org.osmdroid.config.Configuration;
import org.osmdroid.events.MapEventsReceiver;
import org.osmdroid.tileprovider.tilesource.TileSourceFactory;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapController;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.MapEventsOverlay;
import org.osmdroid.views.overlay.Marker;
import org.osmdroid.views.overlay.OverlayItem;
import org.osmdroid.views.overlay.ScaleBarOverlay;
import org.osmdroid.views.overlay.compass.CompassOverlay;
import org.osmdroid.views.overlay.compass.InternalCompassOrientationProvider;
import org.osmdroid.views.overlay.gestures.RotationGestureOverlay;
import org.osmdroid.views.overlay.mylocation.GpsMyLocationProvider;
import org.osmdroid.views.overlay.mylocation.MyLocationNewOverlay;

import java.util.ArrayList;

import encityproject.rightcodeit.com.encityproject.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class TaxiOrderFragment extends Fragment {
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
    public TaxiOrderFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Configuration.getInstance().setUserAgentValue(BuildConfig.APPLICATION_ID);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_taxi_order, container, false);
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
    private void checkPermition() {
        int ACCESS_FINE_LOCATION = ContextCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION);
        int ACCESS_COARSE_LOCATION = ContextCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_COARSE_LOCATION);
        int WRITE_EXTERNAL_STORAGE = ContextCompat.checkSelfPermission(getContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE);
        Log.d("TAG", +ACCESS_FINE_LOCATION + " checkPermition() " + ACCESS_COARSE_LOCATION + "" + WRITE_EXTERNAL_STORAGE);
        if (ACCESS_FINE_LOCATION == PackageManager.PERMISSION_GRANTED
                && ACCESS_COARSE_LOCATION == PackageManager.PERMISSION_GRANTED
                && WRITE_EXTERNAL_STORAGE == PackageManager.PERMISSION_GRANTED) {
            createMap();
        } else {
            Log.d("TAG", "" + "DONT " + Manifest.permission.ACCESS_COARSE_LOCATION);
            ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.WRITE_EXTERNAL_STORAGE},
                    REQUEST_CODE_PERMISSION_READ_CONTACTS);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        Configuration.getInstance().load(getContext(), PreferenceManager.getDefaultSharedPreferences(getActivity()));
    }
}
