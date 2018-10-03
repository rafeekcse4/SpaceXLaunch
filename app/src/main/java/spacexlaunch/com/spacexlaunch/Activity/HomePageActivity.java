package spacexlaunch.com.spacexlaunch.Activity;

import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;

import com.android.volley.Request;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import spacexlaunch.com.spacexlaunch.Adapter.FlightLaunchAdapter;
import spacexlaunch.com.spacexlaunch.ApiMapper.Flight;
import spacexlaunch.com.spacexlaunch.Interface.ServiceRequestListener;
import spacexlaunch.com.spacexlaunch.Network.ServiceRequest;
import spacexlaunch.com.spacexlaunch.R;
import spacexlaunch.com.spacexlaunch.Utils.Constant;
import spacexlaunch.com.spacexlaunch.Utils.FlightParcel;
import spacexlaunch.com.spacexlaunch.Utils.Util;

public class HomePageActivity extends AppCompatActivity implements FlightLaunchAdapter.ClickLister {


    //Initiate the Service Request
    ServiceRequest  mServiceRequest;

    //Initiate the Dialog to show loader
    private Dialog dialog;

    //Initiate the recyclerview id
    RecyclerView recyclerView;

    //Initaiate the adapter class
    FlightLaunchAdapter flightLaunchAdapter;

    boolean doubleBackToExitPressedOnce = false;

    Snackbar snackbar;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);


        //initialise the view
        initializeView();

        //Initiate the request
        initiatePostRequest();

    }




    //Set the view to all the view componenet in Home page
    private void initializeView() {
        recyclerView=findViewById(R.id.id_home_recyclerview);
        CoordinatorLayout coordinatelayout=  findViewById(R.id.coordinatorLayout);

        snackbar = Snackbar.make(coordinatelayout, "Press Again to Exit", Snackbar.LENGTH_LONG);


    }


    //Initiate the Request using volley
    private void initiatePostRequest() {
        showDialog();
        mServiceRequest=new ServiceRequest(this);
        mServiceRequest.makeServiceRequest(Request.Method.GET, Constant.HOMOPAGEURL,null, new ServiceRequestListener() {
            @Override
            public void onErrorListener() {
                try{
                    dialog.dismiss();
                }catch (Exception e){
                    e.printStackTrace();
                    dialog.dismiss();
                }
            }

            @Override
            public void onCompleteListener(String response) {
                try{
                    iterateJsonRespnse(response);
                    dialog.dismiss();

                }catch (Exception e){
                    e.printStackTrace();
                    dialog.dismiss();
                }

            }
        });

    }


    //Iterate the JsonResponse to pojoclass
    private void iterateJsonRespnse(String response) {
        Log.i("bucky response",response);

        Flight[] flight= (Flight[])Util.getResponseFromJsonToObject(String.valueOf(response));
        List<Flight> list=(Arrays.asList(flight));
        System.out.println("flight::"+new ArrayList<>(Arrays.asList(flight)).get(0).getRocket().getRocket_id()+"=="+list.size());
        loadDataInRecyclerView(list);
    }

    //To show the loader during the service call
    public void showDialog(){
        dialog=new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.layout_loading_dialog);
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();

    }

    //Initiated to load the data in recycler view
    public void loadDataInRecyclerView(List<Flight> list){
        try{

            flightLaunchAdapter=new FlightLaunchAdapter(list,this);
            RecyclerView.LayoutManager mLayoutmanager=new LinearLayoutManager(this);
            recyclerView.setLayoutManager(mLayoutmanager);

            recyclerView.setItemAnimator(new DefaultItemAnimator());
            recyclerView.setAdapter(flightLaunchAdapter);
            flightLaunchAdapter.setClickLister(this);
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    @Override
    public void itemClick(View view, int position, Flight flight) {
        FlightParcel  flightParcel=new FlightParcel(flight);
        Intent intent=new Intent(this,LaunchDetailActivity.class);
        intent.putExtra("sample",flightParcel);
        startActivity(intent);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return  true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId()==R.id.menu_logout){
            SharedPreferences.Editor editor = getSharedPreferences(Constant.MY_PREFS_NAME, MODE_PRIVATE).edit();
            editor.putString("username", "");
            editor.putString("pwd","" );
            editor.apply();
            startActivity(new Intent(HomePageActivity.this,LoginActivity.class));
            finish();
        }
        if (item.getItemId()==R.id.menu_profile){
            startActivity(new Intent(HomePageActivity.this,ProfilePage.class));
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {

        if (doubleBackToExitPressedOnce) {
            System.exit(0);
            finish();
        }
        this.doubleBackToExitPressedOnce = true;
        snackbar.show();
        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                doubleBackToExitPressedOnce = false;
            }
        }, 2000);

    }
}
