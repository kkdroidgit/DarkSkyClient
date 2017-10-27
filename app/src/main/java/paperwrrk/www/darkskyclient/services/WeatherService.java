package paperwrrk.www.darkskyclient.services;

import paperwrrk.www.darkskyclient.model.Weather;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by Kartik on 27-10-17.
 */

public interface WeatherService {
    @GET("{latitude},{longitude}")
    Call<Weather> getWeather(@Path("latitude") double latitude,@Path("longitude") double longitude);

}
