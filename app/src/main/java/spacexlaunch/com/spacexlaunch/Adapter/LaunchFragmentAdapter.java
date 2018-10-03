package spacexlaunch.com.spacexlaunch.Adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.LinkedHashMap;
import java.util.LinkedList;

import spacexlaunch.com.spacexlaunch.R;

/**
 * Created by mohamed Rafeek on 2/10/2018.
 */

public class LaunchFragmentAdapter extends RecyclerView.Adapter<LaunchFragmentAdapter.MyViewHolderFragment> {
    private LinkedHashMap<String, String> mapdetails;
    private LinkedList<String> list;

    public LaunchFragmentAdapter( LinkedHashMap<String, String> mapdetails, LinkedList<String> list) {

        this.mapdetails = mapdetails;
        this.list = list;
    }

    @NonNull
    @Override
    public MyViewHolderFragment onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_launch_fragment_list, parent, false);
        return new MyViewHolderFragment(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolderFragment holder, int position) {
   try{
       //Setting the font family to text view

       String key=list.get(position);
       String value=mapdetails.get(key);

       Log.i("bucky fragadapter",key+"==========="+value);
       holder.txt_ltxt.setText(key+"");
       holder.txt_rtxt.setText(value+"");

   }catch (Exception e){
       e.printStackTrace();
   }


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHolderFragment extends RecyclerView.ViewHolder{
        public TextView txt_ltxt,txt_rtxt;
    public MyViewHolderFragment(View itemView) {
        super(itemView);

        txt_ltxt=itemView.findViewById(R.id.id_txt_fragment_ltxt);
        txt_rtxt=itemView.findViewById(R.id.id_txt_fragment_rtxt);
    }
}
}
