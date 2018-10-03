package spacexlaunch.com.spacexlaunch.Network;

import android.content.Context;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NetworkError;
import com.android.volley.NoConnectionError;
import com.android.volley.ParseError;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;

import org.json.JSONArray;

import spacexlaunch.com.spacexlaunch.Application.VolleySingleton;
import spacexlaunch.com.spacexlaunch.Interface.ServiceRequestListener;

/**
 * Created by mohamed Rafeek on 30/9/2018.
 */

public class ServiceRequest {

    private Context context;
    private JsonArrayRequest jsonArrayRequest;
    ServiceRequestListener mServiceRequestListener;

    public ServiceRequest(Context context){

        this.context = context;
    }


    //Create the Json Array Request using Volley
    public void makeServiceRequest(int requestType, String URL, JSONArray jsonRequest,ServiceRequestListener listener){

        this.mServiceRequestListener=listener;

        RequestQueue requestQueue= VolleySingleton.getInstance().getRequestQueue();
        jsonArrayRequest=new JsonArrayRequest(requestType, URL, jsonRequest, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                mServiceRequestListener.onCompleteListener(String.valueOf(response));

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                try{
                if (error instanceof TimeoutError || error instanceof NoConnectionError) {
                    Toast.makeText(context, "Network connection is slow.Please try again.", Toast.LENGTH_SHORT).show();
                } else if (error instanceof AuthFailureError) {
                    Toast.makeText(context, "AuthFailureError", Toast.LENGTH_SHORT).show();
                } else if (error instanceof ServerError) {
                    Toast.makeText(context, "ServerError", Toast.LENGTH_SHORT).show();
                } else if (error instanceof NetworkError) {
                    Toast.makeText(context, "NetworkError", Toast.LENGTH_SHORT).show();
                } else if (error instanceof ParseError) {
                    Toast.makeText(context, "ParseError", Toast.LENGTH_SHORT).show();
                }
            } catch (Exception e) {
                    e.printStackTrace();
            }
                mServiceRequestListener.onErrorListener();
            }
        });
        //to avoid repeat request Multiple Time
        DefaultRetryPolicy retryPolicy = new DefaultRetryPolicy(0, -1, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
        jsonArrayRequest.setRetryPolicy(retryPolicy);

        jsonArrayRequest.setRetryPolicy(new DefaultRetryPolicy(60000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        requestQueue.add(jsonArrayRequest);

    }


    //Cancel the Request
    public void cancelRequest(){
        if (jsonArrayRequest!=null){
            jsonArrayRequest.cancel();
        }
    }



}
