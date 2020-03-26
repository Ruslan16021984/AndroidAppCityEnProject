package encityproject.rightcodeit.com.encityproject.ui.market;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Vibrator;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.File;
import java.util.ArrayList;

import encityproject.rightcodeit.com.encityproject.R;

public class GVAdapter extends BaseAdapter {

    private Context context;
    private int custom_grid;
    private int openClick;
    private int idKey;
    private ArrayList<String> al;

    public GVAdapter(Context context, int custom_grid, int openClick, int idKey, ArrayList<String> al) {
        this.context = context;
        this.custom_grid=custom_grid;
        this.openClick=openClick;
        this.idKey=idKey;
        this.al=al;
    }

    public ArrayList<String> getAl() {
        return al;
    }

    public void setAl(ArrayList<String> al) {
        this.al = al;
    }

    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        final View gridView;

        if(view==null) {
            // gridView = new View(context);
            gridView = inflater.inflate(custom_grid, null);

        }
        else{
            gridView=view;
        }

        TextView tvNameKat;
        ImageView ivOneKat = gridView.findViewById(R.id.ivOneKat);
        tvNameKat = gridView.findViewById(R.id.tvNameKat);

            ivOneKat.setImageResource(R.drawable.city);

            tvNameKat.setText(al.get(i));




        return gridView;
    }
}


