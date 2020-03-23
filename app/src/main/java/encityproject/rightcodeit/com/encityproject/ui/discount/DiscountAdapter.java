package encityproject.rightcodeit.com.encityproject.ui.discount;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Build;
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

import encityproject.rightcodeit.com.encityproject.BuildConfig;
import encityproject.rightcodeit.com.encityproject.R;

public class DiscountAdapter extends BaseAdapter {

    private Context ctx;
    LayoutInflater lInflater;
    ArrayList<Discount> discounts;

    public DiscountAdapter(Context context, ArrayList<Discount> contacts) {
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
        final Discount discount = getDiscount(position);

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
        });

        if((Timestamp.valueOf(discount.getEndTime()).getTime()-System.currentTimeMillis())/(1000*60*60*24)>1) {
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
                .resize(200, 200)
                .centerCrop()
                .into(ivItemDiscount);

        return v;
    }
}