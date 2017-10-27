package paperwrrk.www.darkskyclient;

import android.util.Log;

import org.greenrobot.eventbus.EventBus;

import paperwrrk.www.darkskyclient.events.ErrorEvent;
import paperwrrk.www.darkskyclient.events.WeatherEvent;
import paperwrrk.www.darkskyclient.model.Currently;
import paperwrrk.www.darkskyclient.model.Weather;
import paperwrrk.www.darkskyclient.services.WeatherService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Kartik on 27-10-17.
 */

public class WeatherServiceProvider {
    private Retrofit retrofit;
    private String TAG = WeatherServiceProvider.class.getSimpleName();
    private String BASE_URL = "https://api.darksky.net/forecast/00f58afa01cc6c972d043f938e065b90/";

    private Retrofit getRetrofit(){
        if(this.retrofit == null) {
            this.retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return this.retrofit;
    }


    public void getWeather(double latitude, double longitude){
        WeatherService weatherService = getRetrofit().create(WeatherService.class);
        Call<Weather> weatherData = weatherService.getWeather(latitude,longitude);
        weatherData.enqueue(new Callback<Weather>() {
            @Override
            public void onResponse(Call<Weather> call, Response<Weather> response) {
                Weather weather = response.body();
                Currently currently = weather.getCurrently();
                Log.e(TAG,"Temperature : " + currently.getTemperature());
                //Create an Event when we get the data
                EventBus.getDefault().post(new WeatherEvent(weather));
            }

            @Override
            public void onFailure(Call<Weather> call, Throwable t) {
                Log.e(TAG,"Unable to fetch weather data " );
                EventBus.getDefault().post(new ErrorEvent("Unable to fetch weather data"));
            }
        });
    }


}
