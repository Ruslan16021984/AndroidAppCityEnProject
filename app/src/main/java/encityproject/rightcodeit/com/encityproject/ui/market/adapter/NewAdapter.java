package encityproject.rightcodeit.com.encityproject.ui.market.adapter;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
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

import java.util.ArrayList;

import encityproject.rightcodeit.com.encityproject.R;
import encityproject.rightcodeit.com.encityproject.ui.market.model.CategoryModel;

public class NewAdapter extends RecyclerView.Adapter<NewAdapter.ViewHolder> {
    private Context context;
    private Activity activity;
    private ArrayList<CategoryModel> caList;

    public NewAdapter(Context mContext, ArrayList<CategoryModel> caList) {
        this.context = mContext;
        this.caList = caList;
    }
    public NewAdapter(Context mContext, Activity activity , ArrayList<CategoryModel> caList) {
        this.context = mContext;
        this.caList = caList;
        this.activity = activity;
    }

    @Override public NewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.item_newandorder_recycler, parent, false);
        return new NewAdapter.ViewHolder(v);
    }

    public boolean isOnline() {
        ConnectivityManager cm =
                (ConnectivityManager) activity.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        return netInfo != null && netInfo.isConnectedOrConnecting();
    }

    @Override public void onBindViewHolder(NewAdapter.ViewHolder holder, int position) {

        final CategoryModel catMod = caList.get(position);
        holder.iv.setBackground(ContextCompat.getDrawable(context, catMod.getPicDraw()));
        holder.text.setText(catMod.getCategoryName());

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
        }


        holder.setClickListener(new NewAdapter.ItemClickListener() {
            @Override public void onClickItem(int pos) {
                if(isOnline()) {
                    caList.remove(pos);
                    notifyItemRemoved(position);
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
        private NewAdapter.ItemClickListener mListener;

        public ViewHolder(View itemView) {

            super(itemView);
            lytContainer = itemView.findViewById(R.id.lytContainer);
            text = (TextView) itemView.findViewById(R.id.titleText);
            iv = itemView.findViewById(R.id.ivLogoCat);

            itemView.setOnClickListener(this);
            itemView.setOnLongClickListener(this);
        }

        public void setClickListener(NewAdapter.ItemClickListener listener) {
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
