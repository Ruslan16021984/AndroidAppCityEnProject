package encityproject.rightcodeit.com.encityproject.ui.weatherData;

import android.content.Context;
import android.support.constraint.ConstraintLayout;
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
        tvDateToday.setText(textDayOfWeek(dayOfWeek) +", " + day +"."+ textMouthofYear(month) + "\n"+ timeHHMM);
    }

    private String textMouthofYear(String monthOfYear)
    {
        int monthYear = Integer.parseInt(monthOfYear);
        String monthString = "";
        switch (monthYear) {
            case 1:  monthString = "01";
                break;
            case 2:  monthString = "02";
                break;
            case 3:  monthString = "03";
                break;
            case 4:  monthString = "04";
                break;
            case 5:  monthString = "05";
                break;
            case 6:  monthString = "06";
                break;
            case 7:  monthString = "07";
                break;
            case 8:  monthString = "08";
                break;
            case 9:  monthString = "09";
                break;
            case 10: monthString = "10";
                break;
            case 11: monthString = "11";
                break;
            case 12: monthString = "12";
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
            case 1:  dayName = "Нд";
                break;
            case 2:  dayName = "Пн";
                break;
            case 3:  dayName = "Вт";
                break;
            case 4:  dayName = "Ср";
                break;
            case 5:  dayName = "Чт";
                break;
            case 6:  dayName = "Пт";
                break;
            case 7:  dayName = "Сб";
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
//        holder.cv.setCardBackgroundColor(caList.get(position).getColor());
        holder.clForecast.setBackground(ContextCompat.getDrawable(context, caList.get(position).getColor()));
        holder.tvWind.setText(String.valueOf(caList.get(position).getWindSpeed())+" м/с");


    }

    @Override public int getItemCount() {
        return caList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener, View.OnLongClickListener {

        private ItemClickListener mListener;
        private TextView tvTempFC, tvHumFC, tvPresFC, tvDateFC, tvWind;
        private ImageView ivFC;
        private CardView cv;
        private ConstraintLayout clForecast;

        public ViewHolder(View itemView) {
            super(itemView);

            tvWind=itemView.findViewById(R.id.tvWind);
            ivFC=itemView.findViewById(R.id.ivFC);
            tvTempFC=itemView.findViewById(R.id.tvTempFC);
            tvHumFC=itemView.findViewById(R.id.tvHumFC);
            tvPresFC=itemView.findViewById(R.id.tvPresFC);
            tvDateFC=itemView.findViewById(R.id.tvDateFC);
            cv=itemView.findViewById(R.id.cvForecast);
            clForecast=itemView.findViewById(R.id.clForecast);
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
