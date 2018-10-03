package spacexlaunch.com.spacexlaunch.Application;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * Created by mohamed Rafeek on 29/9/2018.
 */

public class VolleySingleton {

    public static VolleySingleton mInstance;

    private RequestQueue mRequestQueue;

    private VolleySingleton(){
        mRequestQueue= Volley.newRequestQueue(SpaceXApplication.getAppContext());
    }

    public static VolleySingleton getInstance(){
        if (mInstance==null){
            mInstance=new VolleySingleton();
        }
        return mInstance;
    }

    public  RequestQueue getRequestQueue(){
        return mRequestQueue;
    }


}
