package encityproject.rightcodeit.com.encityproject.ui.registration.cloudMarket;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;

import encityproject.rightcodeit.com.encityproject.R;
import encityproject.rightcodeit.com.encityproject.ui.market.adapter.inner_adapter.CloudMarketAdapter;

public class OrdersInBasketAdapter extends RecyclerView.Adapter<OrdersInBasketAdapter.ViewHolder> {
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
    private ArrayList<String> listSms;



    public OrdersInBasketAdapter(Context mContext, Activity activity , ArrayList<String> caList, String newOrClosed, ArrayList<String> listSms) {
        this.context = mContext;
        this.caList = caList;
        this.activity = activity;
        this.newOrClosed=newOrClosed;
        this.listSms=listSms;
        prefer=context.getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);

    }

    @Override public OrdersInBasketAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.item_order_in_basket, parent, false);
        return new OrdersInBasketAdapter.ViewHolder(v);
    }

    public boolean isOnline() {
        ConnectivityManager cm =
                (ConnectivityManager) activity.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        return netInfo != null && netInfo.isConnectedOrConnecting();
    }


    @Override public void onBindViewHolder(OrdersInBasketAdapter.ViewHolder holder, int position) {
//numorder, description, namecompany, telseller, status, createdate, enddate, ratefromclient, ratefromseller
        //numorder0, description1, namecompany2, telseller3, status4, createdate5, enddate6,ratefromclient7, ratefromseller8
        /*final CategoryModel catMod = caList.get(position);*/
        final String catMod = caList.get(position);
        int count=0;
        for(int t=0;t<listSms.size();t++){
            Log.d("countSms", catMod.split("@.#")[0]);
            if(catMod.split("@.#")[0].equals(listSms.get(t))){
                Log.d("countSmYES", listSms.get(t));
                count=count+1;
            }
        }
        if(count>0){
            holder.ivSms.setVisibility(View.VISIBLE);
            holder.tvSms.setText("+"+String.valueOf(count));
            //holder.tvNumOrder.setTextColor(Color.BLACK);
        }
        else{
            holder.tvSms.setVisibility(View.INVISIBLE);
            holder.ivSms.setVisibility(View.INVISIBLE);
        }
        if(catMod.split("@.#")[0].length()>8) {
            holder.tvNumOrder.setText(catMod.split("@.#")[0].substring(5, 13));
        }
        else{
            holder.tvNumOrder.setText(catMod.split("@.#")[0]);
        }
        holder.tvAboutOrder.setText(catMod.split("@.#")[1]);
        if(catMod.split("@.#")[4].equals("wait")){
            holder.tvStatus.setText("обробляється");
        }
        else if(catMod.split("@.#")[4].equals("open")){
            holder.tvStatus.setText("в роботі");
        }
        else if(catMod.split("@.#")[4].equals("canceled")){
            holder.tvStatus.setText("вiдмінено");
        }
        else if(catMod.split("@.#")[4].equals("completed")){
            holder.tvStatus.setText("виконано");
        }
        holder.tvStartOrder.setText(catMod.split("@.#")[5].substring(0,16));
        holder.tvEndOrder.setText(catMod.split("@.#")[6].substring(0,16));

        /*switch (position) {
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
        }*/


        holder.setClickListener(new OrdersInBasketAdapter.ItemClickListener() {
            @Override
            public void onClickItem(int pos) {

                //numorder0, description1, namecompany2, telseller3, status4, createdate5, enddate6

                //   caList.remove(pos);
                // notifyItemRemoved(position);
                /*Bundle bundle =new Bundle();
                bundle.putString("curcat", caList.get(pos));
                Toast.makeText(context, caList.get(pos).split("@.#")[0], Toast.LENGTH_SHORT).show();
                NavController navController = Navigation.findNavController(activity, R.id.nav_host_fragment);
                navController.navigate(R.id.nav_cur_cat_companies_fragment, bundle);*/
                //numorder0, description1, namecompany2, telseller3, status4, createdate5, enddate6
                final AlertDialog.Builder builder = new AlertDialog.Builder(context);
                LayoutInflater inflater = (LayoutInflater) context.getSystemService( Context.LAYOUT_INFLATER_SERVICE );
                View ve = inflater.inflate( R.layout.dialog_my_order, null );
                //View ve = (LinearLayout) getLayoutInflater().inflate(R.layout.dialog_to_order_company, null);
                TextView tvMyAsk= ve.findViewById(R.id.tvMyAsk);
                Button btnQuickConfirm = ve.findViewById(R.id.btnQuickConfirm);
                Button btnQuickCancel = ve.findViewById(R.id.btnQuickCancel);
                Button btnCancalByClient=ve.findViewById(R.id.btnCancalByClient);
              /*  if(newOrClosed.equals("completed")){
                    btnQuickConfirm.setVisibility(View.INVISIBLE);
                }*/
                ImageView ivMessage = ve.findViewById(R.id.ivMessage);
                TextView tvMyAbout= ve.findViewById(R.id.tvMyAbout);
                TextView tvMyComp=ve.findViewById(R.id.tvMyComp);
                TextView tvMyDate1=ve.findViewById(R.id.tvMyDate1);
                TextView tvMyDate2=ve.findViewById(R.id.tvMyDate2);
                TextView tvMyStatus=ve.findViewById(R.id.tvMyStatus);
                TextView tvMyTel=ve.findViewById(R.id.tvMyTel);
                ImageView ivCall = ve.findViewById(R.id.imageViewCall);

                if(catMod.split("@.#")[4].equals("wait")){
                    btnCancalByClient.setVisibility(View.VISIBLE);
                    btnQuickConfirm.setVisibility(View.GONE);
                    tvMyStatus.setText("обробляється");
                    tvMyComp.setText(catMod.split("@.#")[2]);
                }
                else if(catMod.split("@.#")[4].equals("open")){
                    btnCancalByClient.setVisibility(View.GONE);
                    btnQuickConfirm.setVisibility(View.VISIBLE);
                    tvMyStatus.setText("в роботі");
                    tvMyComp.setText(catMod.split("@.#")[2]);
                }
                else if(catMod.split("@.#")[4].equals("canceled")){
                    btnCancalByClient.setVisibility(View.GONE);
                    btnQuickConfirm.setVisibility(View.GONE);
                    tvMyStatus.setText("відмінено");
                    tvMyComp.setText(catMod.split("@.#")[2]);
                }
                else if(catMod.split("@.#")[4].equals("completed")){
                    btnCancalByClient.setVisibility(View.GONE);
                    btnQuickConfirm.setVisibility(View.GONE);
                    tvMyStatus.setText("виконано");
                    tvMyComp.setText(catMod.split("@.#")[2]);
                }

                if(catMod.split("@.#")[0].length()>8) {
                    tvMyAsk.setText(tvMyAsk.getText()+catMod.split("@.#")[0].substring(5, 13));
                }
                else{
                    tvMyAsk.setText(tvMyAsk.getText()+catMod.split("@.#")[0]);
                }
           //     tvMyAsk.setText(tvMyAsk.getText()+catMod.split("@.#")[0]);
                /*if(catMod.split("@.#")[4].equals("wait") ){
                    tvMyStatus.setText("обробляється");
                    tvMyComp.setText(catMod.split("@.#")[2]);
                }else if{
                    tvMyStatus.setText("в роботі");
                    tvMyComp.setText(catMod.split("@.#")[2]);
                }*/
                //tvMyStatus.setText(tvMyStatus.getText()+": "+catMod.split("@.#")[4]);
                tvMyAbout.setText(catMod.split("@.#")[1]);
            //    tvMyComp.setText(tvMyComp.getText()+": "+catMod.split("@.#")[2]);
                tvMyDate1.setText(catMod.split("@.#")[5].substring(0,16));
                tvMyDate2.setText(catMod.split("@.#")[6].substring(0,16));
                tvMyTel.setText("+380"+catMod.split("@.#")[3]);


                builder
                        .setView(ve)
                        .setCancelable(false)
                ;
                final AlertDialog alert2 = builder.create();

                ivMessage.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if(isOnline()) {
                            alert2.dismiss();
                            Bundle bundle = new Bundle();
                            bundle.putString("numorder", catMod.split("@.#")[0]);
                            bundle.putString("aboutorder", catMod.split("@.#")[1]);
                            NavController navController = Navigation.findNavController(activity, R.id.nav_host_fragment);
                            navController.navigate(R.id.nav_message_fragment, bundle);
                        }
                        else{
                            Toast.makeText(context, "Перевірте інтернет", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

                ivCall.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse
                                ("tel: " + "0"+catMod.split("@.#")[3]));
                        if (intent != null) {
                            context.startActivity(intent);
                        }
                    }
                });

                btnCancalByClient.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if(isOnline()) {
                            alert2.dismiss();
                            ConfirmByClient confirmByClient = new ConfirmByClient("Ви відмінили замовлення");
                            confirmByClient.execute("cancelordercli" + "@.#" + prefer.getString("auth", "") +
                                    "@.#" + catMod.split("@.#")[0] + "@.#" + "5");
                        }
                        else{
                            Toast.makeText(context, "Перевірте інтернет", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

                btnQuickConfirm.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if(isOnline()) {
                            alert2.dismiss();
                            ConfirmByClient confirmByClient = new ConfirmByClient("Ви підтвердили виконання замовлення");
                            confirmByClient.execute("confbyclientnorate" + "@.#" + prefer.getString("auth", "") +
                                    "@.#" + catMod.split("@.#")[0] + "@.#" + "5");
                        }
                        else{
                            Toast.makeText(context, "Перевірте інтернет", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

                btnQuickCancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        alert2.dismiss();
                    }
                });


                alert2.show();
                alert2.setCancelable(false);
            }

            @Override
            public void onLongClickItem(int pos) {

            }
        });
    }

    @Override public int getItemCount() {
        return caList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener, View.OnLongClickListener {

        private LinearLayout lytContainer;
        private ImageView iv, ivSms;
        private TextView tvNumOrder, tvStatus, tvAboutOrder, tvStartOrder, tvEndOrder, tvSms;
        private OrdersInBasketAdapter.ItemClickListener mListener;

        public ViewHolder(View itemView) {

            super(itemView);
            lytContainer = itemView.findViewById(R.id.lytContainer);
            tvNumOrder = (TextView) itemView.findViewById(R.id.tvNumOrder);
            tvAboutOrder = itemView.findViewById(R.id.tvAboutOrder);
            tvStatus=itemView.findViewById(R.id.tvStatus);
            tvStartOrder=itemView.findViewById(R.id.tvStartOrder);
            tvEndOrder=itemView.findViewById(R.id.tvEndOrder);
        //    tvSms=itemView.findViewById(R.id.tvSms);
          //  ivSms=itemView.findViewById(R.id.ivSms);

            itemView.setOnClickListener(this);
            itemView.setOnLongClickListener(this);
        }

        public void setClickListener(OrdersInBasketAdapter.ItemClickListener listener) {
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

    class ConfirmByClient extends AsyncTask<String, Void, Socket> {
        private String linkCheckVApp = "myNull";
        private Socket socket;
        private PrintWriter pw = null;
        private InputStream is = null;
        private String fromServer="";
        private String strText;

        public ConfirmByClient(String strText) {
            this.strText=strText;
        }

        @Override
        protected Socket doInBackground(String... params) {

            PrintWriter pw;
            try {
                socket = new Socket(ip, port);

                pw = new PrintWriter(new BufferedOutputStream(socket.getOutputStream()));
                pw.write(params[0] + "@.#" + linkCheckVApp + "\n");
                pw.flush();

                is = socket.getInputStream();
                Scanner sc = new Scanner(is);
                fromServer = sc.nextLine();

                is.close();
            } catch (IOException e) {
                //e.printStackTrace();
                Log.d("send order to conf cli", e.getMessage());
            }
            finally {

            }

            return null;
        }

        @Override
        protected void onPostExecute(Socket socket) {
            super.onPostExecute(socket);
            //pbListPhones.setVisibility(View.INVISIBLE);
            //fromServer="ok";
            Log.d("1804 From: ", fromServer);
            if(fromServer.equals("ok")){
                final AlertDialog.Builder builder = new AlertDialog.Builder(context);
                LayoutInflater inflater = (LayoutInflater) context.getSystemService( Context.LAYOUT_INFLATER_SERVICE );
                View ve = inflater.inflate( R.layout.dialog_ordered_to_comp, null );

                TextView tv=ve.findViewById(R.id.tvConfClient);
                tv.setText(strText);
                Button btn = ve.findViewById(R.id.btnQuickConfirmOK);

                builder
                        .setView(ve)
                        .setCancelable(false)
                ;
                final AlertDialog alert2 = builder.create();

                btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        alert2.dismiss();
                        NavController navController = Navigation.findNavController(activity, R.id.nav_host_fragment);
                        navController.navigate(R.id.nav_basket_fragment);
                    }
                });

                alert2.show();
                alert2.setCancelable(false);

            }
            else{
                Toast.makeText(context, "Виникли технічні помилки. Вже вирішуемо", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
