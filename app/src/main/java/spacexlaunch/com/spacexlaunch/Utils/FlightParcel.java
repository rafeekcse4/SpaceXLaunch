package spacexlaunch.com.spacexlaunch.Utils;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.Gson;

import spacexlaunch.com.spacexlaunch.ApiMapper.Flight;

/**
 * Created by mohamed Rafeek on 30/9/2018.
 */

public class FlightParcel implements Parcelable {

    private Flight flight;

    public FlightParcel(Parcel in) {
        flight=new Gson().fromJson(in.readString(),Flight.class);
    }

    public  Flight getFlight(){
        return flight;
    }

    public FlightParcel(Flight flight) {

        this.flight = flight;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(new Gson().toJson(flight));
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<FlightParcel> CREATOR = new Creator<FlightParcel>() {
        @Override
        public FlightParcel createFromParcel(Parcel in) {
            return new FlightParcel(in);
        }

        @Override
        public FlightParcel[] newArray(int size) {
            return new FlightParcel[size];
        }
    };
}
