package encityproject.rightcodeit.com.encityproject.ui.discount;

import android.app.AlertDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;


import org.osmdroid.tileprovider.tilesource.TileSourceFactory;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapController;
import org.osmdroid.views.MapView;

import java.util.ArrayList;

import encityproject.rightcodeit.com.encityproject.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class DiscountFragment extends Fragment {
    private ArrayOfDiscount arrayOfDiscount;
    private DiscountAdapter discountAdapter;
    private int index;
    private MapView mMapView;
    private MapController mMapController;

    public DiscountFragment() {
    }

/*    private void initData(View view) {
        arrayOfDiscount = new ArrayOfDiscount();
        discountAdapter = new DiscountAdapter(getActivity(), arrayOfDiscount.getArrayOfDiscount());
        ListView listView = view.findViewById(R.id.lv_discounts);
        ArrayList<Discount> ac = new ArrayList<>();
        listView.setAdapter(new DiscountAdapter(getActivity(), ac));
    }*/


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_discount, container, false);
        arrayOfDiscount = new ArrayOfDiscount();
        discountAdapter = new DiscountAdapter(getActivity(), arrayOfDiscount.getArrayOfDiscount());
        ListView listView = v.findViewById(R.id.lv_discounts);
        listView.setAdapter(discountAdapter);

        mMapView = (MapView) v.findViewById(R.id.mapview);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //создать диалог


//                String coordinates = arrayOfDiscount.getArrayOfDiscount().get(position).getCoordinates();
////                mMapView = (MapView) findViewById(R.id.mapview);
//                mMapView.setTileSource(TileSourceFactory.DEFAULT_TILE_SOURCE);
//                mMapView.setBuiltInZoomControls(true);
//                mMapController = (MapController) mMapView.getController();
//                mMapController.setZoom(13);
//                GeoPoint gPt = new GeoPoint(51500000, -150000);
//                mMapController.setCenter(gPt);
            }
        });

        return v;
    }

}
