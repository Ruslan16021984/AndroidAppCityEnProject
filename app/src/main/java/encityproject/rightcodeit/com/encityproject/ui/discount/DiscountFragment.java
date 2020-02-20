package encityproject.rightcodeit.com.encityproject.ui.discount;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import org.osmdroid.api.IMapController;
import org.osmdroid.tileprovider.tilesource.TileSourceFactory;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapController;
import org.osmdroid.views.MapView;

import encityproject.rightcodeit.com.encityproject.R;

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

      //  mMapView = (MapView) v.findViewById(R.id.mapview);

      //  MapView mMapView = v.findViewById(R.id.mv);
        //mMapView.setTileSource(TileSourceFactory.DEFAULT_TILE_SOURCE);
      //  mMapView.setTileSource(TileSourceFactory.MAPNIK);
        //mMapView.setBuiltInZoomControls(true);
       /* mMapView.setBuiltInZoomControls(true);
        mMapView.setMultiTouchControls(true);
        final IMapController mapController = mMapView.getController();
        //   mapController.setZoom(9.5);
        GeoPoint startPoint = new GeoPoint(47.490461, 34.659276);
        mapController.setZoom(16);
        mapController.setCenter(startPoint);
*/
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //создать диалог
//                Toast toast = Toast.makeText(getActivity(), "Click on item position: " + (position + 1), Toast.LENGTH_LONG);
//                toast.show();
                String description = arrayOfDiscount.getArrayOfDiscount().get(position).getDescription();

                Bundle bundle = new Bundle();
//                bundle.putInt("numberDescription", position);
                bundle.putString("singleDescription",
                        arrayOfDiscount.getArrayOfDiscount().get(position).getNameGoods() + "@" +
                                arrayOfDiscount.getArrayOfDiscount().get(position).getPriceAndDiscount() + "@" +
                                arrayOfDiscount.getArrayOfDiscount().get(position).getCoordinates() + "@" +
                                arrayOfDiscount.getArrayOfDiscount().get(position).getImgPath() + "@" +
                                arrayOfDiscount.getArrayOfDiscount().get(position).getDescription());

                Toast.makeText(getActivity(), arrayOfDiscount.getArrayOfDiscount().get(position).getDescription() + " " + (position + 1), Toast.LENGTH_LONG).show();


                NavController navController = Navigation.findNavController(getActivity(), R.id.nav_host_fragment);
                navController.navigate(R.id.nav_description, bundle);

//  Написати інтент на DescriptionFragment и сховати цей фрагмент.
//  В інтенті передати String description
            }
        });

        return v;
    }

}
