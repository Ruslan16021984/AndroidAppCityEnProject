package encityproject.rightcodeit.com.encityproject.ui.weatherData;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import encityproject.rightcodeit.com.encityproject.R;

public class HourlyForecastAdapter extends RecyclerView.Adapter<HourlyForecastAdapter.ViewHolder> {

    private String timeHHMM, day, month, dayOfWeek;
    private Context context;
    private ArrayList<HourlyParametr> caList;
    private ArrayList<Integer> itemsForReturn=new ArrayList<>();


    public ArrayList<Integer> getItemsForReturn() {
        return itemsForReturn;
    }

    public HourlyForecastAdapter(Context mContext, ArrayList<HourlyParametr> caList) {
        this.context = mContext;
        this.caList = caList;
    }

    @Override public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.item_hourly_forecast, parent, false);
        return new ViewHolder(v);
    }

    public static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();
        long factor = (long) Math.pow(10, places);
        value = value * factor;
        long tmp = Math.round(value);
        return (double) tmp / factor;
    }

    private void currentDateAndTime(Calendar calendar, SimpleDateFormat sdf, TextView tvDateToday)
    {
        timeHHMM = sdf.format(calendar.getTime());
        day = String.valueOf(calendar.get(Calendar.DAY_OF_MONTH));
        month = String.valueOf(calendar.get(Calendar.MONTH)+1);
        dayOfWeek = String.valueOf(calendar.get(Calendar.DAY_OF_WEEK));
        tvDateToday.setText(textDayOfWeek(dayOfWeek) +"\n" + day + " " + textMouthofYear(month) + " "+ timeHHMM);
    }

    private String textMouthofYear(String monthOfYear)
    {
        int monthYear = Integer.parseInt(monthOfYear);
        String monthString = "";
        switch (monthYear) {
            case 1:  monthString = "Січня";
                break;
            case 2:  monthString = "Лютого";
                break;
            case 3:  monthString = "Березня";
                break;
            case 4:  monthString = "Квітня";
                break;
            case 5:  monthString = "Травня";
                break;
            case 6:  monthString = "Червня";
                break;
            case 7:  monthString = "Липня";
                break;
            case 8:  monthString = "Серпня";
                break;
            case 9:  monthString = "Вересня";
                break;
            case 10: monthString = "Жовтня";
                break;
            case 11: monthString = "Листопада";
                break;
            case 12: monthString = "Грудня";
                break;
        }
        month = monthString;
        return month;
    }

    private String textDayOfWeek(String dayOfWeek)
    {
        int dayWeek = Integer.parseInt(dayOfWeek);
        String dayName = "";
        switch (dayWeek) {
            case 1:  dayName = "Неділя";
                break;
            case 2:  dayName = "Понеділок";
                break;
            case 3:  dayName = "Вівторок";
                break;
            case 4:  dayName = "Середа";
                break;
            case 5:  dayName = "Четвер";
                break;
            case 6:  dayName = "П'ятниця";
                break;
            case 7:  dayName = "Субота";
                break;
        }
        dayOfWeek = dayName;
        return dayOfWeek;
    }


    @Override public void onBindViewHolder(ViewHolder holder, int position) {

        Picasso.get()
                .load("http://openweathermap.org/img/wn/"+caList.get(position).getIcon()+"@2x.png")
                .resize(600, 800)
                .centerCrop()
                .into(holder.ivFC);

        Log.d("imageICON","http://openweathermap.org/img/wn/"+caList.get(position).getIcon()+"@2x.png" );
        holder.tvTempFC.setText(String.valueOf(round((caList.get(position).getTemp()-273.15),1))+(char) 0x00B0);
        holder.tvHumFC.setText(String.valueOf(caList.get(position).getHum())+" %");
        //holder.tvPresFC.setText(String.valueOf(caList.get(position).getPres())+ " мм.рт.ст");
        holder.tvPresFC.setText(String.valueOf(round(((caList.get(position).getPres()*100) / 133.322)-1, 1))+" мм.рт.ст");
      //  holder.tvDateFC.setText(String.valueOf(new Date(((caList.get(position).getTime()+10800000)*1000))));
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis((caList.get(position).getTime()+10800)*1000);
        currentDateAndTime(calendar,new SimpleDateFormat("HH:mm"), holder.tvDateFC);
    }

    @Override public int getItemCount() {
        return caList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener, View.OnLongClickListener {

        private ItemClickListener mListener;
        private TextView tvTempFC, tvHumFC, tvPresFC, tvDateFC;
        private ImageView ivFC;

        public ViewHolder(View itemView) {
            super(itemView);

            ivFC=itemView.findViewById(R.id.ivFC);
            tvTempFC=itemView.findViewById(R.id.tvTempFC);
            tvHumFC=itemView.findViewById(R.id.tvHumFC);
            tvPresFC=itemView.findViewById(R.id.tvPresFC);
            tvDateFC=itemView.findViewById(R.id.tvDateFC);

         /*   itemView.setOnClickListener(this);
            itemView.setOnLongClickListener(this);*/
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
