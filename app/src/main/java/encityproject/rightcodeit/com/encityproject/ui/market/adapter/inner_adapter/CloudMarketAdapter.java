package encityproject.rightcodeit.com.encityproject.ui.market.adapter.inner_adapter;

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

public class CloudMarketAdapter extends RecyclerView.Adapter<CloudMarketAdapter.ViewHolder> {
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
    public CloudMarketAdapter(Context mContext, Activity activity , ArrayList<String> caList) {
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

    @Override public CloudMarketAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.item_entrance_goods_recycler, parent, false);
        return new CloudMarketAdapter.ViewHolder(v);
    }

    @Override public void onBindViewHolder(CloudMarketAdapter.ViewHolder holder, int position) {

        /*final CategoryModel catMod = caList.get(position);*/
        final String catMod = caList.get(position);
        holder.iv.setBackground(ContextCompat.getDrawable(context,
                activity.getResources().getIdentifier(catMod.split("@.#")[2],"drawable", activity.getPackageName())));
        //holder.text.setText(catMod.getCategoryName());
        holder.text.setText(catMod.split("@.#")[1]);

        switch (position) {
            case 0:
                holder.lytContainer.setBackground(ContextCompat.getDrawable(context, R.drawable.rectangle_yellow));
                break;
            case 1:
                holder.lytContainer.setBackground(ContextCompat.getDrawable(context, R.drawable.rectangle_green));
                break;
            case 2:
                holder.lytContainer.setBackground(ContextCompat.getDrawable(context, R.drawable.rectangle_blue));
                break;
            case 3:
                holder.lytContainer.setBackground(ContextCompat.getDrawable(context, R.drawable.rectangle_orange));
                break;
            case 4:
                holder.lytContainer.setBackground(ContextCompat.getDrawable(context, R.drawable.rectangle_red));
                break;
            case 5:
                holder.lytContainer.setBackground(ContextCompat.getDrawable(context, R.drawable.rectangle_purple));
                break;
            case 6:
                holder.lytContainer.setBackground(ContextCompat.getDrawable(context, R.drawable.rectangle_yellow));
                break;
            case 7:
                holder.lytContainer.setBackground(ContextCompat.getDrawable(context, R.drawable.rectangle_green));
                break;
            case 8:
                holder.lytContainer.setBackground(ContextCompat.getDrawable(context, R.drawable.rectangle_blue));
                break;
            case 9:
                holder.lytContainer.setBackground(ContextCompat.getDrawable(context, R.drawable.rectangle_orange));
                break;
            case 10:
                holder.lytContainer.setBackground(ContextCompat.getDrawable(context, R.drawable.rectangle_red));
                break;
            case 11:
                holder.lytContainer.setBackground(ContextCompat.getDrawable(context, R.drawable.rectangle_purple));
                break;
            case 12:
                holder.lytContainer.setBackground(ContextCompat.getDrawable(context, R.drawable.rectangle_yellow));
                break;
            case 13:
                holder.lytContainer.setBackground(ContextCompat.getDrawable(context, R.drawable.rectangle_green));
                break;
            case 14:
                holder.lytContainer.setBackground(ContextCompat.getDrawable(context, R.drawable.rectangle_blue));
                break;
            case 15:
                holder.lytContainer.setBackground(ContextCompat.getDrawable(context, R.drawable.rectangle_orange));
                break;
            case 16:
                holder.lytContainer.setBackground(ContextCompat.getDrawable(context, R.drawable.rectangle_red));
                break;
            case 17:
                holder.lytContainer.setBackground(ContextCompat.getDrawable(context, R.drawable.rectangle_purple));
                break;
        }


        holder.setClickListener(new CloudMarketAdapter.ItemClickListener() {
            @Override public void onClickItem(int pos) {
               if(isOnline()) {
                   //   caList.remove(pos);
                   // notifyItemRemoved(position);
                   Bundle bundle = new Bundle();
                   bundle.putString("curcat", caList.get(pos));
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
        private ImageView iv;
        private TextView text;
        private CloudMarketAdapter.ItemClickListener mListener;

        public ViewHolder(View itemView) {

            super(itemView);
            lytContainer = itemView.findViewById(R.id.lytContainer);
            text = (TextView) itemView.findViewById(R.id.titleText);
            iv = itemView.findViewById(R.id.ivLogoCat);

            itemView.setOnClickListener(this);
            itemView.setOnLongClickListener(this);
        }

        public void setClickListener(CloudMarketAdapter.ItemClickListener listener) {
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
