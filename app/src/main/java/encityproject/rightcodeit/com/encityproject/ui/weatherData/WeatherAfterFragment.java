package encityproject.rightcodeit.com.encityproject.ui.weatherData;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.zip.Inflater;

import encityproject.rightcodeit.com.encityproject.R;
import encityproject.rightcodeit.com.encityproject.ui.weatherData.wetherApi.entity.Example;
import encityproject.rightcodeit.com.encityproject.ui.weatherData.wetherApi.entity.ListAll;
import encityproject.rightcodeit.com.encityproject.ui.weatherData.wetherApi.interfaces.ApiWeatherService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class WeatherAfterFragment extends Fragment {
    private static final String TAG = "TAG";
    private TextView textView;

    public WeatherAfterFragment() {
        // Required empty public constructor
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.example_weather_fragment, container, false);
        textView = view.findViewById(R.id.text);
        ApiWeatherService apiWeatherService = ApiWeatherService.RETROFIT.create(ApiWeatherService.class);
        apiWeatherService.listAll().enqueue(new Callback<Example>() {
            @Override
            public void onResponse(Call<Example> call, Response<Example> response) {
                textView.setText(response.body().getList().get(2).getMain().toString());
              //  textView.setText(s);
            }

            @Override
            public void onFailure(Call<Example> call, Throwable t) {
                Log.d(TAG, "onFailure: не загрузил====>>>>>>>>");
                t.printStackTrace();
            }
        });

        return view;
    }
}
