package encityproject.rightcodeit.com.encityproject.benchMap;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;



import com.google.gson.Gson;


import org.osmdroid.api.IMapController;
import org.osmdroid.config.Configuration;
import org.osmdroid.tileprovider.tilesource.TileSourceFactory;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.Marker;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Objects;

import encityproject.rightcodeit.com.encityproject.R;

public class MainFragment extends Fragment {
    MapView map = null;

    public MainFragment() {
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Context ctx = getActivity();
        Configuration.getInstance().load(ctx, PreferenceManager.getDefaultSharedPreferences(ctx));

        View v = inflater.inflate(R.layout.fragment_main, container, false);




        map =v.findViewById(R.id.map);
        map.setTileSource(TileSourceFactory.MAPNIK);

        IMapController mapController = map.getController();
        mapController.setZoom(15.5);
        GeoPoint startPoint = new GeoPoint(47.4998, 34.6560);
        mapController.setCenter(startPoint);

        Marker startMarker = new Marker(map);
        GeoPoint geoLavochka1 = new GeoPoint(47.505077, 34.648643);
        startMarker.setPosition(geoLavochka1);
        startMarker.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM);
        startMarker.setTitle("СмартЛавочка 1");
        startMarker.setOnMarkerClickListener(new Marker.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker, MapView mapView) {

                FragmentTransaction ft;
                ft= getFragmentManager().beginTransaction();

                //ft.replace(R.id.frame, new BeanchFragment());
                ft.addToBackStack(null);
                ft.commit();



                return false;
            }
        });
        map.getOverlays().add(startMarker);

        return v;
    }

}