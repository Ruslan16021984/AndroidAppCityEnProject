package encityproject.rightcodeit.com.encityproject.ui.weatherData.wetherApi.interfaces;
import encityproject.rightcodeit.com.encityproject.ui.weatherData.wetherApi.entity.Example;
import encityproject.rightcodeit.com.encityproject.ui.weatherData.wetherApi.entity.ListAll;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;

public interface ApiWeatherService {
    String BASE_URL = "http://api.openweathermap.org/";
    @GET("data/2.5/forecast?id=709276&appid=8b74b289dcce4dabda34734197b8deb5")
    Call<Example> listAll();

    Retrofit RETROFIT = new Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();
}
