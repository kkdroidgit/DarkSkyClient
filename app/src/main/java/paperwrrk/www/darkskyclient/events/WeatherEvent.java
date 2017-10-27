package paperwrrk.www.darkskyclient.events;

import paperwrrk.www.darkskyclient.model.Weather;

/**
 * Created by Kartik on 27-10-17.
 */

public class WeatherEvent {

    private final Weather weather;

    public WeatherEvent(Weather weather) {
        this.weather = weather;
    }

    public Weather getWeather() {
        return weather;
    }
}
