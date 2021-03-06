package encityproject.rightcodeit.com.encityproject.ui.taxiClient.Adapters;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import java.util.ArrayList;

import encityproject.rightcodeit.com.encityproject.R;

public class AnyAddressAdapter extends RecyclerView.Adapter<AnyAddressAdapter.ViewHolder> {
    private Context context;
    private Activity activity;
    private ArrayList<String> addList;
    private Bundle bundle;
    private AlertDialog alertDialog;

    public AnyAddressAdapter(Context mContext, Activity activity , ArrayList<String> addList, Bundle bundle, AlertDialog alertDialog) {
        this.context = mContext;
        this.addList = addList;
        this.activity = activity;
        this.bundle = bundle;
        this.alertDialog=alertDialog;
    }

    public boolean isOnline() {
        ConnectivityManager cm =
                (ConnectivityManager) activity.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        return netInfo != null && netInfo.isConnectedOrConnecting();
    }

    @Override public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.item_my_adress_door, parent, false);
        return new ViewHolder(v);
    }

    @Override public void onBindViewHolder(ViewHolder holder, int position) {

        final String address = addList.get(position);
        holder.tvMyAdress.setText(address);

        holder.setClickListener(new AnyAddressAdapter.ItemClickListener() {
            @Override public void onClickItem(int pos) {
                if(isOnline()) {
                    if(bundle!=null) {
                        alertDialog.dismiss();
                        bundle.putString("door", addList.get(position));
                        NavController navController = Navigation.findNavController(activity, R.id.nav_host_fragment);
                        navController.navigate(R.id.nav_taxi_confirm_order_fragment, bundle);
                    }
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
        return addList.size();
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


