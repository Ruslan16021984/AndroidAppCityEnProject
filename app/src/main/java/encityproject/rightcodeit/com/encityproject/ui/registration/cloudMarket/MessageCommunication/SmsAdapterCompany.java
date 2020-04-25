package encityproject.rightcodeit.com.encityproject.ui.registration.cloudMarket.MessageCommunication;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import encityproject.rightcodeit.com.encityproject.R;

public class SmsAdapterCompany extends  RecyclerView.Adapter<RecyclerView.ViewHolder>{
    public static final int INTRODUCTION_VIEW_TYPE = 0;

    public static final int IMPLEMENTATION_VIEW_TYPE = 1;
 //   private ArrayList<MultiEmployee> employees;

    private static int TYPE_CALL = 1;
    private static int TYPE_EMAIL = 2;
    private int currentViewType;
    private Context context;
    private Activity activity;
    /*private ArrayList<CategoryModel> caList;*/
    private ArrayList<String> caList;
    private String newOrClosed;
    private int port = 4656;
    //private String ip = "192.168.1.46";
    //private String ip = "192.168.1.103";
    private String ip = "35.232.178.112";
    private SharedPreferences prefer;
    private SharedPreferences.Editor editor;
    private static final String APP_PREFERENCES = "ensettings";


    public SmsAdapterCompany(Context mContext, Activity activity , ArrayList<String> caList, String newOrClosed) {
        this.context = mContext;
        this.caList = caList;
        this.activity = activity;
        this.newOrClosed=newOrClosed;
        prefer=context.getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view;
        if (viewType == TYPE_CALL) { // for call layout
            view = LayoutInflater.from(context).inflate(R.layout.item_message_i_am, viewGroup, false);
            return new CallViewHolder(view);

        } else { // for email layout
            view = LayoutInflater.from(context).inflate(R.layout.item_message_from, viewGroup, false);
            return new EmailViewHolder(view);
        }
    }

    @Override
    public int getItemViewType(int position) {
        if(caList.get(position).split("@.#")[1].equals("seller")){
            return  TYPE_CALL;
        }
        else {
            return TYPE_EMAIL;
        }
    }

    public void setCaList(ArrayList<String> caList) {
        this.caList = caList;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {
        if (getItemViewType(position) == TYPE_CALL) {
            ((CallViewHolder) viewHolder).setCallDetails(caList.get(position));
        } else {
            ((EmailViewHolder) viewHolder).setEmailDetails(caList.get(position));
        }
    }

    @Override
    public int getItemCount() {
        return caList.size();
    }

    class CallViewHolder extends RecyclerView.ViewHolder {

        private TextView tvMessageIam;
        private TextView txtAddress;

        CallViewHolder(@NonNull View itemView) {
            super(itemView);
            tvMessageIam = itemView.findViewById(R.id.tvMessageIam);
        }

        void setCallDetails(String catMod) {
                tvMessageIam.setText(catMod.split("@.#")[2]);
        }
    }

    class EmailViewHolder extends RecyclerView.ViewHolder {

        private TextView tvMessageFrom;
        private ImageView ivFrom;

        EmailViewHolder(@NonNull View itemView) {
            super(itemView);
            tvMessageFrom = itemView.findViewById(R.id.tvMessageFrom);
            ivFrom = itemView.findViewById(R.id.ivMessageFrom);
            ivFrom.setImageResource(R.drawable.person);
        }

        void setEmailDetails(String catMod) {
            tvMessageFrom.setText(catMod.split("@.#")[2]);
        }
    }
}
