package encityproject.rightcodeit.com.encityproject.ui.discount;

import android.app.AlertDialog;
import android.content.Context;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import org.osmdroid.tileprovider.tilesource.TileSourceFactory;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapController;
import org.osmdroid.views.MapView;

import java.util.ArrayList;

import encityproject.rightcodeit.com.encityproject.R;

public class DiscountAdapter extends BaseAdapter {

    private Context ctx;
    LayoutInflater lInflater;
    ArrayList<Discount> discounts;


    public DiscountAdapter(Context context, ArrayList<Discount> contacts) {
        this.ctx = context;
        this.discounts =contacts;
        lInflater = (LayoutInflater) ctx
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    Discount getDiscount(int position) {
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
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;
        final Discount discount = getDiscount(position);

        if (v == null) {
            LayoutInflater vi;
            vi = LayoutInflater.from(ctx);
            v = vi.inflate(R.layout.item_discount, null);
        }

        TextView tvNameGoods = (TextView) v.findViewById(R.id.tv_name_goods);
        TextView tvPriceAndDiscount = (TextView) v.findViewById(R.id.tv_phone_price_and_discount);
        ImageView ivItemDiscount = (ImageView) v.findViewById(R.id.iv_item_discount);
        ImageView ivMark = (ImageView) v.findViewById(R.id.iv_mark);

        ivMark.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(ctx);
                LayoutInflater inflater = LayoutInflater.from(ctx);
                View vOpenStreetMap =  inflater.inflate(R.layout.custom_dialog_open_street_map,null);

                Toast toast=Toast. makeText(ctx.getApplicationContext(),"Hello!!!!!!!!!!!!!!!",Toast. LENGTH_LONG);
                toast.show();

               // String coordinates = arrayOfDiscount.getArrayOfDiscount().get(position).getCoordinates();
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

        tvNameGoods.setText(discount.getNameGoods());
        tvPriceAndDiscount.setText(discount.getPriceAndDiscount());

        Picasso.get()
                .load(discount.getImgPath())
                .resize(200, 200)
                .centerCrop()
                .into(ivItemDiscount);

        return v;
    }
}