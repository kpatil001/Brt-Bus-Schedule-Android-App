package app.brttimetable.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import app.brttimetable.R;
import app.brttimetable.model.TimeTable;

public class DisplayTimeTableListAdapter extends RecyclerView.Adapter<DisplayTimeTableListAdapter.TimeTableViewHolder>{

    private Context context;
    private List<TimeTable> timeTableList;

    public DisplayTimeTableListAdapter(Context context, List<TimeTable> timeTableList) {
        this.context = context;
        this.timeTableList = timeTableList;
    }

    @NonNull
    @Override
    public TimeTableViewHolder
    onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater= LayoutInflater.from(viewGroup.getContext());
        View view = inflater.inflate(R.layout.time_table_view,viewGroup,false);

        return new TimeTableViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TimeTableViewHolder holder, int i) {
            TimeTable item = timeTableList.get(i);
            holder.stationName.setText(item.getStationName());
            holder.stationTime.setText(item.getTime());
    }

    @Override
    public int getItemCount() {
        return timeTableList.size();
    }

    public class TimeTableViewHolder extends RecyclerView.ViewHolder{
        private TextView stationName,stationTime;

        public TimeTableViewHolder(@NonNull View itemView) {
            super(itemView);
            stationName= itemView.findViewById(R.id.stationName);
            stationTime = itemView.findViewById(R.id.stationTime);
        }
    }
}
