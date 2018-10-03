package spacexlaunch.com.spacexlaunch.Interface;

/**
 * Created by mohamed Rafeek on 30/9/2018.
 */

public interface ServiceRequestListener {
    void onErrorListener();
    void onCompleteListener(String response);

}
