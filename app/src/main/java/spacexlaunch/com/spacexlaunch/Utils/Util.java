package spacexlaunch.com.spacexlaunch.Utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.Reader;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

import spacexlaunch.com.spacexlaunch.ApiMapper.Flight;

/**
 * Created by mohamed Rafeek on 29/9/2018.
 */

public class Util {

    public static String toJsonString(Object request) {

        return getJsonBuilder().toJson(request);

    }

    public static Object jsonToObject(String json, Class<?> objectClass) {

        return getJsonBuilder().fromJson(json, objectClass);
    }

    public static Object jsonToObject(Reader reader, Class<?> objectClass) {

        return new Gson().fromJson(reader, objectClass);
    }



    private static Gson getJsonBuilder() {
        GsonBuilder gsonBuilder = new GsonBuilder();

        gsonBuilder.setDateFormat("yyyyMMdd");
        return gsonBuilder.create();
    }

    public static Object[] getResponseFromJsonToObject(String responseString) {

        return (Object[]) Util.jsonToObject(responseString, Flight[].class);

    }

    public static boolean isStringNullOrNot(String value){
        return !(value != null && !value.isEmpty());
    }

    public static String getDate(String string)
    {
        String v;
        try
        {
            // String string = "2016-12-02T00:00:00.000Z";
            // String defaultTimezone = TimeZone.getDefault().getID();
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ", Locale.ENGLISH);
            df.setTimeZone(TimeZone.getDefault());
            Date date = df.parse(string.replaceAll("Z$", "+0000"));
            v=(new SimpleDateFormat("dd-MM-yyyy HH:mm:ss")).format(date);
        }
        catch (Exception e)
        {
            v = "00-00-0000 00:00";
        }
        return v;
    }


}
