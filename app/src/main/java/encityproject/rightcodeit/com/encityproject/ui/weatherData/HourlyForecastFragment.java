package encityproject.rightcodeit.com.encityproject.ui.weatherData;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import encityproject.rightcodeit.com.encityproject.R;
import encityproject.rightcodeit.com.encityproject.ui.weatherData.wetherApi.entity.Example;
import encityproject.rightcodeit.com.encityproject.ui.weatherData.wetherApi.entity.ListAll;
import encityproject.rightcodeit.com.encityproject.ui.weatherData.wetherApi.interfaces.ApiWeatherService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class HourlyForecastFragment extends Fragment {
    private RecyclerView recyclerView;
    private HourlyForecastAdapter hourlyForecastAdapter;

    public HourlyForecastFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_hourly_forecast, container, false);

        recyclerView=view.findViewById(R.id.rvForecast);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 1, GridLayoutManager.VERTICAL, false));

        ApiWeatherService apiWeatherService = ApiWeatherService.RETROFIT.create(ApiWeatherService.class);
        apiWeatherService.listAll().enqueue(new Callback<Example>() {
            @Override
            public void onResponse(Call<Example> call, Response<Example> response) {
                response.body().getList().get(0).getMain().getTemp();
                ArrayList<HourlyParametr> HPlist= new ArrayList<>();
                HPlist=createForecast(response.body().getList());
                hourlyForecastAdapter=new HourlyForecastAdapter(getContext(), HPlist);
                recyclerView.setAdapter(hourlyForecastAdapter);

               // Toast.makeText(getContext(), String.valueOf(response.body().getList().get(1).getWind().getSpeed()), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<Example> call, Throwable t) {
                Log.d("FailForecast", "onFailure: forecast");
                t.printStackTrace();
            }
        });



        return view;
    }

    private int currentDateAndTime(Calendar calendar, SimpleDateFormat sdf)
    {
      int day =calendar.get(Calendar.DAY_OF_MONTH);
      return day;
    }

    private ArrayList<HourlyParametr> createForecast(List<ListAll> response){
        ArrayList<HourlyParametr> reList=new ArrayList<>();
        int day=0;
        int color=0;
        for(int i=0; i<response.size();i++){
            Calendar calendar = Calendar.getInstance();
            calendar.setTimeInMillis((response.get(i).getDt())*1000);
            Calendar calendarLast = Calendar.getInstance();
            if(i==0) {
                day = currentDateAndTime(calendar, new SimpleDateFormat("HH:mm"));
                color=R.color.colorLightGreen;
            }
            if(i>0){
                calendarLast.setTimeInMillis((response.get(i-1).getDt())*1000);
                if(currentDateAndTime(calendar,new SimpleDateFormat("HH:mm"))==currentDateAndTime(calendarLast,new SimpleDateFormat("HH:mm"))){
                    color=color;
                }
                else{
                    if(color==R.color.colorLightGreen){
                        color=R.color.colorLightGrey;
                    }
                    else if(color==R.color.colorLightGrey){
                        color=R.color.colorLightGreen;
                    }
                }
            }
            HourlyParametr hourlyParametr = new HourlyParametr(response.get(i).getWeather().get(0).getIcon(),
                    response.get(i).getMain().getTemp(),
                    response.get(i).getMain().getHumidity(),
                    response.get(i).getMain().getPressure(),
                    response.get(i).getDt(),
                    color,
                    response.get(i).getWind().getSpeed());
            reList.add(hourlyParametr);
        }
        return reList;
    }


}
