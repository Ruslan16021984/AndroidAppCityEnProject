package encityproject.rightcodeit.com.encityproject.ui.discount;

import android.Manifest;
import android.app.AlertDialog;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.Icon;
import android.media.Image;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
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
import org.osmdroid.views.overlay.Marker;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import encityproject.rightcodeit.com.encityproject.R;

import static android.text.Layout.JUSTIFICATION_MODE_INTER_WORD;

public class DescriptionFragment extends Fragment {
    private ArrayOfDiscount arrayOfDiscount;
    private DiscountAdapter discountAdapter;
    private int index;
    private TextView tvDescription;
    private TextView tvNameGoodsDescription;
    private TextView tvPriceAndDiscountDescription;
    private ImageView ivItemDiscountDescription, ivInsta;
    private ImageView ivMarkDescription, ivCallSel;
    private TextView tvLastTimeDesc, tvPhoheSel;
    private MapController mMapController;
    private String lan;
    private String lon;

    public DescriptionFragment() {
    }

    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_description, container, false);
        tvDescription = v.findViewById(R.id.tv_description_description);

//        tvDescription.setText(""); // ставите текс щ прийде з DiscountFragment
        ivInsta=v.findViewById(R.id.ivInsta);
        tvNameGoodsDescription = (TextView) v.findViewById(R.id.tv_name_goods_description);
        tvPriceAndDiscountDescription = (TextView) v.findViewById(R.id.tv_price_and_discount_description);
        ivItemDiscountDescription = (ImageView) v.findViewById(R.id.iv_item_discount_description);
        ivMarkDescription = (ImageView) v.findViewById(R.id.iv_mark_description);
        tvLastTimeDesc = v.findViewById(R.id.tv_last_time_desc);
        tvPhoheSel=v.findViewById(R.id.tv_phone_sel);
        ivCallSel=v.findViewById(R.id.iv_call_sel);
        Bundle bundle = this.getArguments();
        tvPhoheSel.setVisibility(View.GONE);


        if (bundle != null) {
            String fromBundle = bundle.getString("singleDescription");
            tvNameGoodsDescription.setText(fromBundle.split("@")[0]);
            tvPriceAndDiscountDescription.setText(fromBundle.split("@")[1]);
            lan = fromBundle.split("@")[2];
            lon = fromBundle.split("@")[3];
            String imgPathDescription = fromBundle.split("@")[4];
            tvDescription.setText(fromBundle.split("@")[5]);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                tvDescription.setJustificationMode(JUSTIFICATION_MODE_INTER_WORD);
            }
            if((Timestamp.valueOf(fromBundle.split("@")[7]).getTime()-System.currentTimeMillis())/(1000*60*60*24)>=1) {
                tvLastTimeDesc.setText("Залишилось " + String.valueOf((Timestamp.valueOf(fromBundle.split("@")[7]).getTime()-System.currentTimeMillis()) / (1000 * 60 * 60 * 24)) + " днів");
            }
            else{
                if((Timestamp.valueOf(fromBundle.split("@")[7]).getTime()-System.currentTimeMillis())/(1000*60*60)>1) {
                    tvLastTimeDesc.setText(("Залишилось " + String.valueOf((Timestamp.valueOf(fromBundle.split("@")[7]).getTime()-System.currentTimeMillis()) / (1000 * 60 * 60)) + " годин"));
                }
                else {
                    tvLastTimeDesc.setText(("Залишилось " + String.valueOf((Timestamp.valueOf(fromBundle.split("@")[7]).getTime()-System.currentTimeMillis()) / (1000 * 60)) + " хвилин"));
                }
            }
            if(fromBundle.split("@")[1].split(",")[0].length()>0){
                tvPriceAndDiscountDescription.setText("Ціна "+fromBundle.split("@")[1].split(",")[0]+" грн");
            }
            else if(fromBundle.split("@")[1].split(",")[1].length()>0){
                tvPriceAndDiscountDescription.setText("Знижка "+fromBundle.split("@")[1].split(",")[1]+" %");
            }
            else {
                tvPriceAndDiscountDescription.setText("");
            }

            if(fromBundle.split("@")[9].length()>10){
                ivInsta.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Uri uri = Uri.parse(fromBundle.split("@")[9]);
                        Intent i= new Intent(Intent.ACTION_VIEW,uri);
                        i.setPackage("com.instagram.android");
                        try {
                            startActivity(i);
                        } catch (ActivityNotFoundException e) {
                            startActivity(new Intent(Intent.ACTION_VIEW,
                                    Uri.parse("http://instagram.com/")));
                        }
                    }
                });
            }
            else{
                ivInsta.setVisibility(View.GONE);
            }

            if(fromBundle.split("@")[8].length()==9) {
                tvPhoheSel.setText("+380" + fromBundle.split("@")[8]);
                ivCallSel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse
                                ("tel: " + "+380" + fromBundle.split("@")[8]));
                        if (intent != null) {
                            startActivity(intent);
                        }
                    }
                });
                tvPhoheSel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse
                                ("tel: " + "+380" + fromBundle.split("@")[8]));
                        if (intent != null) {
                            startActivity(intent);
                        }
                    }
                });
            }
            else {
                tvPhoheSel.setVisibility(View.GONE);
                ivCallSel.setVisibility(View.GONE);
            }

            Picasso.get()
                    .load(imgPathDescription)
                    .resize(800, 600)
                    .centerCrop()
                    .into(ivItemDiscountDescription);

            ivMarkDescription.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(checkAndRequestPermissions()){
                        showOnMap();
                    }
                    else{
                        showOnMap();
                    }

                }
            });
//           tvDescription.setAlpha(0.5f); // Прозрачность ітема

        }

        return v;
    }

    private  boolean checkAndRequestPermissions() {
        boolean status=false;
        int writeExternalPerm = ContextCompat.checkSelfPermission(getContext(),
                Manifest.permission.WRITE_EXTERNAL_STORAGE);
        int fineLocPermition = ContextCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_COARSE_LOCATION);
        // int readPhoneStatePerm = ContextCompat.checkSelfPermission(getContext(), Manifest.permission.READ_PHONE_STATE);
        List<String> listPermissionsNeeded = new ArrayList<>();
        if (writeExternalPerm != PackageManager.PERMISSION_GRANTED) {
            listPermissionsNeeded.add(Manifest.permission.WRITE_EXTERNAL_STORAGE);
        }
       /* if (fineLocPermition != PackageManager.PERMISSION_GRANTED) {
            listPermissionsNeeded.add(Manifest.permission.ACCESS_COARSE_LOCATION);
        }*/
    /*    if (readPhoneStatePerm != PackageManager.PERMISSION_GRANTED) {
            listPermissionsNeeded.add(Manifest.permission.READ_PHONE_STATE);
        }*/
        if (!listPermissionsNeeded.isEmpty()) {
            ActivityCompat.requestPermissions(getActivity(), listPermissionsNeeded.toArray(new String[listPermissionsNeeded.size()]),210);
            return true;
        }

        return status;
    }

    private void showOnMap(){
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        LayoutInflater inflater = LayoutInflater.from(getContext());
        View vOpenStreetMap = inflater.inflate(R.layout.custom_dialog_open_street_map, null);

        MapView mMapView = (MapView) vOpenStreetMap.findViewById(R.id.mapview);
        mMapView.setTileSource(TileSourceFactory.DEFAULT_TILE_SOURCE);
        mMapView.setBuiltInZoomControls(true);
        MapController mMapController = (MapController) mMapView.getController();
        mMapController.setZoom(17);
        GeoPoint gPt = new GeoPoint(Double.parseDouble(lan), Double.parseDouble(lon));
        mMapController.setCenter(gPt);
        Marker startMarker = new Marker(mMapView);
        startMarker.setPosition(gPt);
        startMarker.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM);
        startMarker.setIcon(getResources().getDrawable(R.drawable.mark));
        mMapView.getOverlays().add(startMarker);
        builder
                .setView(vOpenStreetMap)
                .setCancelable(true)
        ;
        final AlertDialog alert = builder.create();
        alert.show();
    }
}
