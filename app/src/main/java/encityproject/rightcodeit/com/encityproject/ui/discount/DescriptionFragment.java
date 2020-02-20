package encityproject.rightcodeit.com.encityproject.ui.discount;

import android.app.AlertDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.squareup.picasso.Picasso;
import org.osmdroid.tileprovider.tilesource.TileSourceFactory;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapController;
import org.osmdroid.views.MapView;

import encityproject.rightcodeit.com.encityproject.R;

public class DescriptionFragment extends Fragment {
    private ArrayOfDiscount arrayOfDiscount;
    private DiscountAdapter discountAdapter;
    private int index;
    private TextView tvDescription;
    private TextView tvNameGoodsDescription;
    private TextView tvPriceAndDiscountDescription;
    private ImageView ivItemDiscountDescription;
    private ImageView ivMarkDescription;
    private MapController mMapController;

    public DescriptionFragment() {
    }

    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_description, container, false);
        tvDescription = v.findViewById(R.id.tv_description_description);

//        tvDescription.setText(""); // ставите текс щ прийде з DiscountFragment

        tvNameGoodsDescription = (TextView) v.findViewById(R.id.tv_name_goods_description);
        tvPriceAndDiscountDescription = (TextView) v.findViewById(R.id.tv_price_and_discount_description);
        ivItemDiscountDescription = (ImageView) v.findViewById(R.id.iv_item_discount_description);
        ivMarkDescription = (ImageView) v.findViewById(R.id.iv_mark_description);

        Bundle bundle = this.getArguments();

        if (bundle != null) {
            String fromBundle = bundle.getString("singleDescription");
            tvNameGoodsDescription.setText(fromBundle.split("@")[0]);
            tvPriceAndDiscountDescription.setText(fromBundle.split("@")[1]);
            String coordinates = fromBundle.split("@")[2];
            String imgPathDescription = fromBundle.split("@")[3];
            tvDescription.setText(fromBundle.split("@")[4]);

            Picasso.get()
                    .load(imgPathDescription)
                    .resize(200, 200)
                    .centerCrop()
                    .into(ivItemDiscountDescription);

            ivMarkDescription.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                    LayoutInflater inflater = LayoutInflater.from(getContext());
                    View vOpenStreetMap = inflater.inflate(R.layout.custom_dialog_open_street_map, null);

                    MapView mMapView = (MapView) vOpenStreetMap.findViewById(R.id.mapview);
                    mMapView.setTileSource(TileSourceFactory.DEFAULT_TILE_SOURCE);
                    mMapView.setBuiltInZoomControls(true);
                    MapController mMapController = (MapController) mMapView.getController();
                    mMapController.setZoom(13);
                    GeoPoint gPt = new GeoPoint(51500000, -150000);
                    mMapController.setCenter(gPt);
                    builder
                            .setView(vOpenStreetMap)
                            .setCancelable(true)
                    ;
                    final AlertDialog alert = builder.create();
                    alert.show();
                }
            });
//           tvDescription.setAlpha(0.5f); // Прозрачность ітема

        }

        return v;
    }

}
