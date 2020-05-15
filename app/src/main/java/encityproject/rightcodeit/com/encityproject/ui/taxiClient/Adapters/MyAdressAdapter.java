package encityproject.rightcodeit.com.encityproject.ui.taxiClient.Adapters;


import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import java.util.ArrayList;

import encityproject.rightcodeit.com.encityproject.R;
import encityproject.rightcodeit.com.encityproject.ui.market.model.CategoryModel;

public class MyAdressAdapter extends RecyclerView.Adapter<MyAdressAdapter.ViewHolder> {
    private Context context;
    private Activity activity;
    /*private ArrayList<CategoryModel> caList;*/
    private ArrayList<String> caList;

    /*public CloudMarketAdapter(Context mContext, ArrayList<CategoryModel> caList) {
        this.context = mContext;
        this.caList = caList;
    }*/
    /*public CloudMarketAdapter(Context mContext, Activity activity , ArrayList<CategoryModel> caList) {
        this.context = mContext;
        this.caList = caList;
        this.activity = activity;
    }*/
    public MyAdressAdapter(Context mContext, Activity activity , ArrayList<String> caList) {
        this.context = mContext;
        this.caList = caList;
        this.activity = activity;
    }

    public boolean isOnline() {
        ConnectivityManager cm =
                (ConnectivityManager) activity.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        return netInfo != null && netInfo.isConnectedOrConnecting();
    }

    @Override public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.item_my_adress, parent, false);
        return new ViewHolder(v);
    }

    @Override public void onBindViewHolder(ViewHolder holder, int position) {

        /*final CategoryModel catMod = caList.get(position);*/
        final String catMod = caList.get(position);
        holder.tvMyAdress.setText(catMod);

        holder.setClickListener(new MyAdressAdapter.ItemClickListener() {
            @Override public void onClickItem(int pos) {
                if(isOnline()) {
                    //   caList.remove(pos);
                    // notifyItemRemoved(position);
                    Bundle bundle = new Bundle();
                   // bundle.putString("curcat", caList.get(pos));
                    //    Toast.makeText(context, caList.get(pos).split("@.#")[0], Toast.LENGTH_SHORT).show();
                    NavController navController = Navigation.findNavController(activity, R.id.nav_host_fragment);
                    navController.navigate(R.id.nav_cur_cat_companies_fragment, bundle);
                }
                else{
                    Toast.makeText(context, "Перевірте інтернет", Toast.LENGTH_SHORT).show();
                }
            }

            @Override public void onLongClickItem(int pos) {
            }
        });
    }

    @Override public int getItemCount() {
        return caList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener, View.OnLongClickListener {

        private LinearLayout lytContainer;
        private TextView tvMyAdress;
        private ItemClickListener mListener;

        public ViewHolder(View itemView) {

            super(itemView);
            lytContainer = itemView.findViewById(R.id.lytContainer);
            tvMyAdress = (TextView) itemView.findViewById(R.id.tvMyAdress);

            itemView.setOnClickListener(this);
            itemView.setOnLongClickListener(this);
        }

        public void setClickListener(ItemClickListener listener) {
            this.mListener = listener;
        }

        @Override public void onClick(View view) {
            mListener.onClickItem(getLayoutPosition());
        }

        @Override public boolean onLongClick(View view) {
            mListener.onLongClickItem(getLayoutPosition());
            return true;
        }
    }

    public interface ItemClickListener {
        void onClickItem(int pos);
        void onLongClickItem(int pos);
    }
}

