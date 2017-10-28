package paperwrrk.www.darkskyclient.services;

import android.util.Log;

import org.greenrobot.eventbus.EventBus;

import paperwrrk.www.darkskyclient.events.ErrorEvent;
import paperwrrk.www.darkskyclient.events.WeatherEvent;
import paperwrrk.www.darkskyclient.model.Currently;
import paperwrrk.www.darkskyclient.model.Weather;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Zakaria Chowdhury on 8/19/17.
 */

public class WeatherServiceProvider {
    private static final String BASE_URL = "https://api.darksky.net/forecast/7e87d17a004526d5f1ff090ae5eb689e/";
    private static final String TAG = WeatherServiceProvider.class.getSimpleName();
    private Retrofit retrofit;

    private Retrofit getRetrofit() {
        if (this.retrofit == null) {
            this.retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return this.retrofit;
    }

    public void getWeather(double lat, double lng) {
        WeatherService weatherService = getRetrofit().create(WeatherService.class);
        Call<Weather> weatherData =  weatherService.getWeather(lat, lng);
        weatherData.enqueue(new Callback<Weather>() {
            @Override
            public void onResponse(Call<Weather> call, Response<Weather> response) {
                Weather weather = response.body();
                if (weather != null) {
                    Currently currently = weather.getCurrently();
                    Log.e(TAG, "Temperature = " + currently.getTemperature());
                    EventBus.getDefault().post(new WeatherEvent(weather));
                } else {
                    Log.e(TAG, "No Response: check secret key");
                    EventBus.getDefault().post(new ErrorEvent("No weather data available"));
                }
            }

            @Override
            public void onFailure(Call<Weather> call, Throwable t) {
                Log.e(TAG, "onFailure: Unable to get weather data");
                EventBus.getDefault().post(new ErrorEvent("Unable to connect weather server"));
            }
        });
    }
}
