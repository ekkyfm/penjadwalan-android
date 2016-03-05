package id.derohimat.baseapp.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

/**
 * Created on : 05-03-2016
 * Author     : derohimat
 * Name       : Deni Rohimat
 * Email      : rohimatdeni@gmail.com
 * GitHub     : https://github.com/derohimat
 * LinkedIn   : https://www.linkedin.com/in/derohimat
 */
public class BasePreferenceUtils {

    private static SharedPreferences getSharedPreference(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context);
    }

    public static void putString(Context context, String key, String isi) {
        getSharedPreference(context).edit().putString(key, isi).apply();
    }

    public static String getString(Context context, String key) {
        return getSharedPreference(context).getString(key, null);
    }

    public static void putDouble(Context context, String key, double isi) {
        getSharedPreference(context).edit().putFloat(key, (float) isi).apply();
    }

    public static Double getDouble(Context context, String key) {
        double isi;
        switch (key) {
            case "lat":
                isi = getSharedPreference(context).getFloat(key, -7.7521492f);
                break;
            case "lon":
                isi = getSharedPreference(context).getFloat(key, 110.377659f);
                break;
            default:
                isi = getSharedPreference(context).getFloat(key, 0);
        }

        return isi;
    }

    public static void putBoolean(Context context, String key, boolean isi) {
        getSharedPreference(context).edit().putBoolean(key, isi).apply();
    }

    public static boolean getBoolean(Context context, String key) {
        return getSharedPreference(context).getBoolean(key, false);
    }
}
