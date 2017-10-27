package paperwrrk.www.darkskyclient;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.widget.Toast;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.ButterKnife;
import paperwrrk.www.darkskyclient.events.ErrorEvent;
import paperwrrk.www.darkskyclient.events.WeatherEvent;
import paperwrrk.www.darkskyclient.model.Currently;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    @BindView(R.id.temperature)
    private TextView temperature;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        requestCurrentWeather(37.8267,-122.4233);

        ButterKnife.bind(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onWeatherEvent(WeatherEvent weatherEvent) {
        /* Listen to the weather data in Main Activity*/
        Currently currently = weatherEvent.getWeather().getCurrently();
        temperature.setText(String.valueOf(Math.round(currently.getTemperature())));
    };

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onErrorEvent(ErrorEvent errorEvent) {
        /* Listen to the weather data in Main Activity*/
        String error = errorEvent.getErrorMessage();
        Toast.makeText(this,error,Toast.LENGTH_SHORT).show();
    };

    private void requestCurrentWeather(double latitude, double longitude) {
        WeatherServiceProvider weatherServiceProvider = new WeatherServiceProvider();
        weatherServiceProvider.getWeather(latitude,longitude);
    }

    @Override
    protected void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }
}
