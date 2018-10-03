package spacexlaunch.com.spacexlaunch.Adapter;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import spacexlaunch.com.spacexlaunch.ApiMapper.Flight;
import spacexlaunch.com.spacexlaunch.R;

/**
 * Created by mohamed Rafeek on 30/9/2018.
 */

public class FlightLaunchAdapter extends RecyclerView.Adapter<FlightLaunchAdapter.MyViewHolder> {
    //Initiate the list to load the data
    private List<Flight> list;
    private Context context;

    private ClickLister clickLister;


    public FlightLaunchAdapter(List<Flight> list, Context context) {

        this.list = list;
        this.context = context;
    }

    public void setClickLister(ClickLister clickLister) {
        this.clickLister = clickLister;
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_launch_list, parent, false);
        return new MyViewHolder(view);

    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        try {
            //Setting the font family to text view
            Typeface custom_font = Typeface.createFromAsset(context.getAssets(), "fonts/roboto.ttf");
            holder.flight_id.setTypeface(custom_font);
            holder.launch_status.setTypeface(custom_font);
            holder.launch_year.setTypeface(custom_font);
            holder.mission_name.setTypeface(custom_font);

            //Set the value to all the text view
            Flight flight = list.get(position);
            holder.flight_id.setText("Flight Number::" + flight.getFlight_number());
            holder.launch_status.setText("Launch Success::" + flight.isLaunch_success());
            holder.launch_year.setText(flight.getLaunch_year());
            holder.mission_name.setText("Mission Name::" + flight.getMission_name());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public interface ClickLister {
        void itemClick(View view, int position, Flight flight);
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView flight_id, launch_status, mission_name, launch_year;

        public MyViewHolder(View itemView) {
            super(itemView);
            flight_id = itemView.findViewById(R.id.id_txt_flightid);
            launch_status = itemView.findViewById(R.id.id_txt_launchstatus);
            launch_year = itemView.findViewById(R.id.id_txt_launch_year);
            mission_name = itemView.findViewById(R.id.id_txt_mission_name);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    clickLister.itemClick(view, getAdapterPosition(), list.get(getAdapterPosition()));
                }
            });
        }


    }

}
