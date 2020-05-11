package encityproject.rightcodeit.com.encityproject.ui.weatherData;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
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

            }

            @Override
            public void onFailure(Call<Example> call, Throwable t) {
                Log.d("FailForecast", "onFailure: forecast");
                t.printStackTrace();
            }
        });



        return view;
    }

    private ArrayList<HourlyParametr> createForecast(List<ListAll> response){
        ArrayList<HourlyParametr> reList=new ArrayList<>();
        for(int i=0; i<response.size();i++){
            HourlyParametr hourlyParametr = new HourlyParametr(response.get(i).getWeather().get(0).getIcon(),
                    response.get(i).getMain().getTemp(),
                    response.get(i).getMain().getHumidity(),
                    response.get(i).getMain().getPressure(),
                    response.get(i).getDt());
            reList.add(hourlyParametr);
        }
        return reList;
    }

}
