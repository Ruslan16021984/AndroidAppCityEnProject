package encityproject.rightcodeit.com.encityproject.ui.registration;


import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import encityproject.rightcodeit.com.encityproject.R;
import encityproject.rightcodeit.com.encityproject.ui.market.model.CategoryModel;

public class CatChoiceAdapter extends RecyclerView.Adapter<CatChoiceAdapter.ViewHolder> {

    private Context context;
    private ArrayList<String> caList;
    private ArrayList<Integer> itemsForReturn=new ArrayList<>();

    public ArrayList<Integer> getItemsForReturn() {
        return itemsForReturn;
    }

    public CatChoiceAdapter(Context mContext, ArrayList<String> caList) {
        this.context = mContext;
        this.caList = caList;
    }

    @Override public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.item_cat_choice_seller, parent, false);
        return new ViewHolder(v);
    }

    @Override public void onBindViewHolder(ViewHolder holder, int position) {

        final String catMod = caList.get(position).split("@.#")[1];

        holder.cbCatSeller.setText(catMod);

        switch (position) {
            case 0:
                holder.cv.setBackground(ContextCompat.getDrawable(context, R.drawable.rectangle_yellow));
                break;
            case 1:
                holder.cv.setBackground(ContextCompat.getDrawable(context, R.drawable.rectangle_green));
                break;
            case 2:
                holder.cv.setBackground(ContextCompat.getDrawable(context, R.drawable.rectangle_blue));
                break;
            case 3:
                holder.cv.setBackground(ContextCompat.getDrawable(context, R.drawable.rectangle_orange));
                break;
            case 4:
                holder.cv.setBackground(ContextCompat.getDrawable(context, R.drawable.rectangle_red));
                break;
            case 5:
                holder.cv.setBackground(ContextCompat.getDrawable(context, R.drawable.rectangle_purple));
                break;
            case 6:
                holder.cv.setBackground(ContextCompat.getDrawable(context, R.drawable.rectangle_yellow));
                break;
            case 7:
                holder.cv.setBackground(ContextCompat.getDrawable(context, R.drawable.rectangle_green));
                break;
            case 8:
                holder.cv.setBackground(ContextCompat.getDrawable(context, R.drawable.rectangle_blue));
                break;
            case 9:
                holder.cv.setBackground(ContextCompat.getDrawable(context, R.drawable.rectangle_orange));
                break;
            case 10:
                holder.cv.setBackground(ContextCompat.getDrawable(context, R.drawable.rectangle_red));
                break;
            case 11:
                holder.cv.setBackground(ContextCompat.getDrawable(context, R.drawable.rectangle_purple));
                break;
            case 12:
                holder.cv.setBackground(ContextCompat.getDrawable(context, R.drawable.rectangle_yellow));
                break;
            case 13:
                holder.cv.setBackground(ContextCompat.getDrawable(context, R.drawable.rectangle_green));
                break;
            case 14:
                holder.cv.setBackground(ContextCompat.getDrawable(context, R.drawable.rectangle_blue));
                break;
            case 15:
                holder.cv.setBackground(ContextCompat.getDrawable(context, R.drawable.rectangle_orange));
                break;
            case 16:
                holder.cv.setBackground(ContextCompat.getDrawable(context, R.drawable.rectangle_red));
                break;
            case 17:
                holder.cv.setBackground(ContextCompat.getDrawable(context, R.drawable.rectangle_purple));
                break;
        }

        holder.cbCatSeller.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(holder.cbCatSeller.isChecked()){
                    if(itemsForReturn.size()==0){
                        itemsForReturn.add(position);

                    }
                    else {
                        int ch = 0;
                        for (int k = 0; k < itemsForReturn.size(); k++) {
                            if (itemsForReturn.get(k) == position) {
                                ch = 0;
                                break;
                            } else {
                                ch = 1;
                            }
                        }
                        if (ch == 1) {
                            itemsForReturn.add(position);
                        }
                    }
                }
                else {
                    for(int k=0;k<itemsForReturn.size();k++){
                        if(itemsForReturn.get(k)==position){
                            itemsForReturn.remove(k);
                        }
                    }
                }
            }
        });

        holder.setClickListener(new ItemClickListener() {
            @Override public void onClickItem(int pos) {
                if(holder.cbCatSeller.isChecked()){
                    holder.cbCatSeller.setChecked(false);
                    for(int k=0;k<itemsForReturn.size();k++){
                        if(itemsForReturn.get(k)==pos){
                            itemsForReturn.remove(k);
                        }
                    }
                }
                else {
                    holder.cbCatSeller.setChecked(true);
                    if(itemsForReturn.size()==0){
                        itemsForReturn.add(pos);
                    }
                    else {
                        int ch = 0;
                        for (int k = 0; k < itemsForReturn.size(); k++) {
                            if (itemsForReturn.get(k) == position) {
                                ch = 0;
                                break;
                            } else {
                                ch = 1;
                            }
                        }
                        if (ch == 1) {
                            itemsForReturn.add(pos);
                        }
                    }
                }
             //   Toast.makeText(context, "clck: " + catMod, Toast.LENGTH_SHORT).show();
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

        private CardView cv;
        private CheckBox cbCatSeller;
        private ItemClickListener mListener;

        public ViewHolder(View itemView) {
            super(itemView);
            cv = itemView.findViewById(R.id.cv);
            cbCatSeller =  itemView.findViewById(R.id.cbCatSeller);

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
