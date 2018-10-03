package spacexlaunch.com.spacexlaunch.Activity;


import android.app.DialogFragment;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.text.util.Linkify;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;

import spacexlaunch.com.spacexlaunch.ApiMapper.Flight;
import spacexlaunch.com.spacexlaunch.Fragment.LaunchdetailsFragment;
import spacexlaunch.com.spacexlaunch.R;
import spacexlaunch.com.spacexlaunch.Utils.FlightParcel;
import spacexlaunch.com.spacexlaunch.Utils.Util;

public class LaunchDetailActivity extends AppCompatActivity {
    //Initiate text view
    TextView txt_flightno;
    TextView txt_missonname;
    TextView txt_launchdate, txt_customerInfo, txt_rocketName, txt_details, txt_wikilink, txt_youtubelink, txt_launchsiteid, txt_launchsitename, txt_launchsitelogs, txt_reusestatus, txt_launchstatus;

    Button btn_coredetails, btn_payloads;
    //Instantiate the Arraylist in core and paylods
    LinkedList<String> listcore;
    LinkedList<String> listpayloads;
    //Inistaiate the Hash map in core and paylods details
    private LinkedHashMap<String, String> mapCore;
    private LinkedHashMap<String, String> mapPaylods;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launch_detail);


        //noinspection ConstantConditions
        getSupportActionBar().setTitle("Launch Details");



        Intent intent = getIntent();
        FlightParcel flight = intent.getParcelableExtra("sample");

        Flight f = flight.getFlight();
        Log.i("bucky second Activity", flight + "---" + f.getMission_name());

        //set the view
        initializeView(f);


        //Button listener
        btn_coredetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                initializeDialogFragment(mapCore, listcore);
            }
        });

        btn_payloads.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                initializeDialogFragment(mapPaylods, listpayloads);
            }
        });


    }


    //Appending the view
    private void initializeView(Flight f) {

        txt_flightno = findViewById(R.id.id_txt_detail_flightno);
        txt_missonname = findViewById(R.id.id_txt_detail_missionname);
        txt_launchdate = findViewById(R.id.id_txt_detail_launchdate);
        txt_customerInfo = findViewById(R.id.id_txt_detail_customerinfo);
        txt_rocketName = findViewById(R.id.id_txt_detail_rocketname);
        txt_details = findViewById(R.id.id_txt_detail_details);
        txt_wikilink = findViewById(R.id.id_txt_detail_wikilink);
        txt_youtubelink = findViewById(R.id.id_txt_detail_youtubelink);
        txt_launchstatus = findViewById(R.id.id_txt_detail_launchstatus);
        txt_launchsiteid = findViewById(R.id.id_txt_detail_launchsiteid);
        txt_launchsitename = findViewById(R.id.id_txt_detail_launchsitename);
        txt_launchsitelogs = findViewById(R.id.id_txt_detail_launchsitelogs);
        txt_reusestatus = findViewById(R.id.id_txt_detail_reusestatus);

        btn_coredetails = findViewById(R.id.id_btn_coredetails);
        btn_payloads = findViewById(R.id.id_btn_payloaddetails);
        //Load the data
        appendingData(f);
    }

    //setValue for all the text box
    private void appendingData(Flight flight) {
        try {

            txt_flightno.setText(flight.getFlight_number() + "");
            txt_missonname.setText(flight.getMission_name() + "");

            //converting the utc time formate to local time
            String utcdate = flight.getLaunch_date_utc();
            String localDate = Util.getDate(utcdate);
            txt_launchdate.setText(localDate);


            //updating the rocket name
            String rocketName = flight.getRocket().getRocket_name();
            if (!Util.isStringNullOrNot(rocketName)) {
                txt_rocketName.setText(rocketName);
            } else
                txt_rocketName.setText("Null");

            //updating the Flight details
            String details = flight.getDetails();
            if (!Util.isStringNullOrNot(details)) {
                txt_details.setText(details);
            } else
                txt_details.setText("Null");

            //Update the wiki link and youtube link
            String wikipediaLink = flight.getLinks().getWikipedia();
            if (!Util.isStringNullOrNot(wikipediaLink)) {
                updateLinkTextview(txt_wikilink, wikipediaLink);
            } else
                txt_wikilink.setText("Null");

            //Update the youtube link and youtube link
            String youtubeLink = flight.getLinks().getVideo_link();
            if (!Util.isStringNullOrNot(youtubeLink)) {
                updateLinkTextview(txt_youtubelink, youtubeLink);
            } else
                txt_youtubelink.setText("Null");

            //update the value in launch site
            String launchsiteid = flight.getLaunch_site().getSite_id();
            if (!Util.isStringNullOrNot(launchsiteid)) {
                txt_launchsiteid.setText(launchsiteid);
            } else
                txt_launchsiteid.setText("Null");

            //update the value in launchname
            String launchname = flight.getLaunch_site().getSite_name();
            if (!Util.isStringNullOrNot(launchname)) {
                txt_launchsitename.setText(launchname);
            } else
                txt_launchsitename.setText("Null");


            //update the value in launch logs
            String launchlogs = flight.getLaunch_site().getSite_name_long();
            if (!Util.isStringNullOrNot(launchlogs)) {
                txt_launchsitelogs.setText(launchlogs);
            } else
                txt_launchsitelogs.setText("Null");

            boolean reused = flight.getRocket().getFairings().isReused();
            txt_reusestatus.setText(reused + "");

//Getting the core details and form into hash map
            List<Object> list = flight.getRocket().getFirst_stage().getCores();
            String jsonvalueForCoreDetail = new Gson().toJson(list);
            jsonParsingCore(jsonvalueForCoreDetail);

        //getting payload and form in hash map
            List<Object> listpay = flight.getRocket().getSecond_stage().getPayloads();
            String jsonvaluePayloadDetails = new Gson().toJson(listpay);
            jsonParsingPayloads(jsonvaluePayloadDetails);

            //Updating launch status
            txt_launchstatus.setText(flight.isLaunch_success() + "");
            //Updating the customer name
            String customerName = mapPaylods.get("customers");
            if (!Util.isStringNullOrNot(customerName))
                txt_customerInfo.setText(customerName);
            else
                txt_customerInfo.setText("Null");


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void jsonParsingPayloads(String jsonvaluePayloadDetails) {
        try {
            mapPaylods = new LinkedHashMap<>();
            listpayloads = new LinkedList<>();
            JSONArray jsonArray = new JSONArray(jsonvaluePayloadDetails);
            JSONObject jsonObject = (JSONObject) jsonArray.get(0);
            Iterator<String> iterator = jsonObject.keys();
            while (iterator.hasNext()) {
                String key = iterator.next();
                listpayloads.add(key);
                //Getting the customer details
                if (key.equalsIgnoreCase("customers")) {
                    JSONArray jsonArray1 = (JSONArray) jsonObject.get(key);
                    mapPaylods.put(key, appendStringValue(jsonArray1));
                } else if (key.equalsIgnoreCase("norad_id")) {   //Getting the norad_id
                    JSONArray jsonArray1 = (JSONArray) jsonObject.get(key);
                    mapPaylods.put(key, appendStringValue(jsonArray1));
                } else if (key.equalsIgnoreCase("orbit_params")) {        //Getting the orbit_params detils
                    JSONObject jsonObject1 = (JSONObject) jsonObject.get(key);
                    Iterator<String> iterator1 = jsonObject1.keys();
                    while (iterator1.hasNext()) {
                        String key1 = iterator1.next();
                        mapPaylods.put(key1, jsonObject1.optString(key));
                    }
                } else
                    mapPaylods.put(key, jsonObject.optString(key));


                Log.i("bucky jsonParsing", "key:" + key + "--Value::" + jsonObject.optString(key));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


    private String appendStringValue(JSONArray jsonArray) throws JSONException {
        StringBuilder sb = new StringBuilder();
        String prefix = "";
        for (int i = 0; i < jsonArray.length(); i++) {
            sb.append(prefix);
            prefix = ",";
            sb.append(jsonArray.get(i));
        }
        return String.valueOf(sb);
    }

    //setting the link to the text view
    private void updateLinkTextview(TextView txtview, String link) {
        txtview.setText(link);
        txtview.setLinkTextColor(ContextCompat.getColor(this, R.color.brand_linkcolor));
        Linkify.addLinks(txtview, Linkify.WEB_URLS | Linkify.PHONE_NUMBERS);
        Linkify.addLinks(txtview, Linkify.ALL);
    }


    //Json paesing and added in map
    private void jsonParsingCore(String jsonvalue) {
        try {
            mapCore = new LinkedHashMap<>();
            listcore = new LinkedList<>();
            JSONArray jsonArray = new JSONArray(jsonvalue);
            JSONObject jsonObject = (JSONObject) jsonArray.get(0);
            Iterator<String> iterator = jsonObject.keys();
            while (iterator.hasNext()) {
                String key = iterator.next();
                mapCore.put(key, jsonObject.optString(key));
                listcore.add(key);
                Log.i("bucky jsonParsing", "key:" + key + "--Value::" + jsonObject.optString(key));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    //Initaialize the dialog Fragment
    private void initializeDialogFragment(LinkedHashMap<String, String> map, LinkedList<String> list) {
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        Fragment prev = getFragmentManager().findFragmentByTag("dialog");
        if (prev != null) {
            DialogFragment df = (DialogFragment) prev;
            df.dismiss();
        }
        ft.addToBackStack(null);
        DialogFragment dialogFragment = new LaunchdetailsFragment();
        dialogFragment.setStyle(DialogFragment.STYLE_NORMAL, R.style.CustomDialog);
        Bundle bundle = new Bundle();
        bundle.putSerializable("details", map);
        bundle.putSerializable("listdetails", list);
        dialogFragment.setArguments(bundle);
        dialogFragment.show(ft, "dialog");
    }
}
