package paperwrrk.www.darkskyclient.utils;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import paperwrrk.www.darkskyclient.R;

/**
 * Created by Kartik on 28-10-17.
 */

public final class WeatherIconUtil {

    public static final Map<String,Integer> ICONS;

    static{
        Map<String, Integer> iconMap = new HashMap<>();
        iconMap.put("clear-day", R.drawable.ic_clear_day);

        ICONS = Collections.unmodifiableMap(iconMap);
    }

}
