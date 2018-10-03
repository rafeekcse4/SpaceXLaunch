package spacexlaunch.com.spacexlaunch.Application;

import android.app.Application;
import android.content.Context;

/**
 * Created by mohamed Rafeek on 29/9/2018.
 */

public class SpaceXApplication extends Application {

    public static SpaceXApplication mInstance;

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance=this;
    }

    public static SpaceXApplication getInstance(){
        return mInstance;
    }

    public static Context getAppContext(){
        return mInstance.getApplicationContext();
    }
}
