package paperwrrk.www.darkskyclient.services;

import paperwrrk.www.darkskyclient.model.Weather;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;


/**
 * Created by Zakaria Chowdhury on 8/19/17.
 */

public interface WeatherService {
    @GET("{lat},{lng}")
    Call<Weather> getWeather(@Path("lat") double lat, @Path("lng") double lng);
}
