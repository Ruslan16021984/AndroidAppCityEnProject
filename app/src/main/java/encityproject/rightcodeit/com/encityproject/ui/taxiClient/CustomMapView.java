
package encityproject.rightcodeit.com.encityproject.ui.taxiClient;

import android.content.Context;
import android.location.Location;
import android.location.LocationManager;
import android.support.v4.app.Fragment;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Interpolator;
import android.widget.Toast;

import org.osmdroid.bonuspack.location.GeocoderNominatim;
import org.osmdroid.bonuspack.location.NominatimPOIProvider;
import org.osmdroid.bonuspack.location.POI;
import org.osmdroid.bonuspack.routing.OSRMRoadManager;
import org.osmdroid.bonuspack.routing.RoadManager;
import org.osmdroid.tileprovider.tilesource.TileSourceFactory;
import org.osmdroid.views.MapController;
import org.osmdroid.views.MapView;
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

public class CustomMapView {
    private MapView mapView;
    private View view;
    private Context context;
    private Fragment fragment;
    private static final int REQUEST_CODE_PERMISSION_READ_CONTACTS = 123;
    private ScaleBarOverlay mScaleBarOverlay;
    private RotationGestureOverlay mRotationGestureOverlay;
    private MapController mapController = null;
    private MyLocationNewOverlay locationOverlay;
    private CompassOverlay compassOverlay;
    private RoadManager roadManager;
    private Marker hideMarke;
    private Interpolator interpolator;
    private NominatimPOIProvider poiProvider;

    public CustomMapView(View view, Context context, Fragment fragment) {
        this.view = view;
        this.context = context;
        this.fragment = fragment;
        mapView = view.findViewById(R.id.mapClient);
        mapView.setTileSource(TileSourceFactory.MAPNIK);

    }
    public void createMap(){
        mapView.setMultiTouchControls(true);
        mapView.setTilesScaledToDpi(true);
        mapView.setBuiltInZoomControls(true);
        mapView.setFlingEnabled(true);

        locationOverlay = new MyLocationNewOverlay(new GpsMyLocationProvider(context), mapView);
        locationOverlay.enableMyLocation();
        locationOverlay.enableFollowLocation();
        locationOverlay.setDrawAccuracyEnabled(true);
        locationOverlay.setOptionsMenuEnabled(true);

        roadManager = new OSRMRoadManager(context);
        final DisplayMetrics dm = fragment.getResources().getDisplayMetrics();
        mScaleBarOverlay = new ScaleBarOverlay(mapView);
        mScaleBarOverlay.setCentred(true);
        mScaleBarOverlay.setScaleBarOffset(dm.widthPixels / 2, 10);
        mRotationGestureOverlay = new RotationGestureOverlay(context, mapView);
        mRotationGestureOverlay.setEnabled(true);
        hideMarke = new Marker(mapView);
        compassOverlay = new CompassOverlay(context,
                new InternalCompassOrientationProvider(context), mapView);
        compassOverlay.enableCompass();
        mapView.getOverlays().add(mScaleBarOverlay);
        mapView.getOverlays().add(compassOverlay);
        mapView.getOverlays().add(locationOverlay);

        mapController = (MapController) mapView.getController();
        mapController.setZoom(16);

        final ArrayList<OverlayItem> items = new ArrayList<OverlayItem>();
    }


    public MapView getMapView() {
        return mapView;
    }

    public void setMapView(MapView mapView) {
        this.mapView = mapView;
    }
}
