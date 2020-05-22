package encityproject.rightcodeit.com.encityproject.ui.discount;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import org.osmdroid.api.IMapController;
import org.osmdroid.config.Configuration;
import org.osmdroid.tileprovider.tilesource.TileSourceFactory;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapController;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.Marker;
import org.osmdroid.views.overlay.Overlay;
import org.osmdroid.views.overlay.OverlayItem;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import encityproject.rightcodeit.com.encityproject.BuildConfig;
import encityproject.rightcodeit.com.encityproject.R;

public class DiscountAdapter extends BaseAdapter {

    private Context ctx;
    private Activity activity;
    LayoutInflater lInflater;
    ArrayList<Discount> discounts;

    public DiscountAdapter(Activity activity, Context context, ArrayList<Discount> contacts) {
        this.activity=activity;
        this.ctx = context;
        this.discounts = contacts;
        lInflater = (LayoutInflater) ctx
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public Discount getDiscount(int position) {
        return ((Discount) getItem(position));
    }

    // кол-во элементов
    @Override
    public int getCount() {
        return discounts.size();
    }

    // элемент по позиции
    @Override
    public Discount getItem(int position) {
        return discounts.get(position);
    }

    // id по позиции
    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View v = convertView;
        Discount discount = getDiscount(position);

        if (v == null) {
            LayoutInflater vi;
            vi = LayoutInflater.from(ctx);
            v = vi.inflate(R.layout.item_discount, null);
        }

        TextView tvLastTime = v.findViewById(R.id.tv_last_time);
        TextView tvNameGoods = (TextView) v.findViewById(R.id.tv_name_goods);
        TextView tvPriceAndDiscount = (TextView) v.findViewById(R.id.tv_price_and_discount);
        ImageView ivItemDiscount = (ImageView) v.findViewById(R.id.iv_item_discount);
        ImageView ivMark = (ImageView) v.findViewById(R.id.iv_mark);

        ivMark.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(checkAndRequestPermissions()){
                    showOnMap(discount);
                }
                else {
                   showOnMap(discount);
                }
            }
        });

        if((Timestamp.valueOf(discount.getEndTime()).getTime()-System.currentTimeMillis())/(1000*60*60*24)>=1) {
            tvLastTime.setText("Залишилось " + String.valueOf((Timestamp.valueOf(discount.getEndTime()).getTime() -System.currentTimeMillis()) / (1000 * 60 * 60 * 24)) + " днів");
        }
        else{
            if((Timestamp.valueOf(discount.getEndTime()).getTime() -System.currentTimeMillis()) / (1000 * 60 * 60)>1){
            tvLastTime.setText("Залишилось " + String.valueOf((Timestamp.valueOf(discount.getEndTime()).getTime() -System.currentTimeMillis()) / (1000 * 60 * 60)) + " годин");
            }
            else {
                tvLastTime.setText("Залишилось " + String.valueOf((Timestamp.valueOf(discount.getEndTime()).getTime() -System.currentTimeMillis()) / (1000 * 60)) + " хвилин");
            }
        }

        tvNameGoods.setText(discount.getNameGoods());
        if(discount.getPriceAndDiscount().split(",")[0].length()>0){
            tvPriceAndDiscount.setText(discount.getPriceAndDiscount().split(",")[0]+" грн");
        }
        else if(discount.getPriceAndDiscount().split(",")[1].length()>0){
            tvPriceAndDiscount.setText(discount.getPriceAndDiscount().split(",")[1]+" %");
        }
        else {
            tvPriceAndDiscount.setText("");
        }

        Picasso.get()
                .load(discount.getImgPath())
                .resize(800, 600)
                .centerCrop()
                .into(ivItemDiscount);

        return v;
    }

    private  boolean checkAndRequestPermissions() {
        boolean status=false;
        int writeExternalPerm = ContextCompat.checkSelfPermission(ctx,
                Manifest.permission.WRITE_EXTERNAL_STORAGE);
        int fineLocPermition = ContextCompat.checkSelfPermission(ctx, Manifest.permission.ACCESS_COARSE_LOCATION);
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
            ActivityCompat.requestPermissions(activity, listPermissionsNeeded.toArray(new String[listPermissionsNeeded.size()]),210);
            return true;
        }

        return status;
    }

    private void showOnMap(Discount discount){
        AlertDialog.Builder builder = new AlertDialog.Builder(ctx);
        LayoutInflater inflater = LayoutInflater.from(ctx);
        View vOpenStreetMap = inflater.inflate(R.layout.custom_dialog_open_street_map, null);

        //      Toast.makeText(ctx.getApplicationContext(), "Hello! " + (position + 1), Toast.LENGTH_LONG).show();

        // String coordinates = arrayOfDiscount.getArrayOfDiscount().get(position).getCoordinates();

        //Координаты можно писать классически
        Configuration.getInstance().setUserAgentValue(BuildConfig.APPLICATION_ID);
        MapView mMapView = vOpenStreetMap.findViewById(R.id.mapview);
        mMapView.setTileSource(TileSourceFactory.DEFAULT_TILE_SOURCE);
        mMapView.setBuiltInZoomControls(true);
        mMapView.setMultiTouchControls(true);
        MapController mMapController = (MapController) mMapView.getController();
        mMapController.setZoom(17);
        GeoPoint gPt = new GeoPoint(Double.parseDouble(discount.getLan()), Double.parseDouble(discount.getLon()));
        mMapController.setCenter(gPt);
        Marker startMarker = new Marker(mMapView);
        startMarker.setPosition(gPt);
        startMarker.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM);
        startMarker.setIcon(ctx.getResources().getDrawable(R.drawable.mark));
        mMapView.getOverlays().add(startMarker);

        builder
                .setView(vOpenStreetMap)
                .setCancelable(true);
        final AlertDialog alert = builder.create();
        alert.show();
    }
}