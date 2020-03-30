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
import app.brttimetable.model.Response;
import app.brttimetable.model.TimeTable;

public class DisplayBusListAdapter extends RecyclerView.Adapter<DisplayBusListAdapter.DisplayListViewHolder>{


    public interface OnItemClickListener {
        void onItemClick(List<TimeTable> timeTableList);
    }

    private Context context;
    // private FetchTimeTableResponse response;
    private List<Response> response;
    private OnItemClickListener listener;

    public DisplayBusListAdapter(Context context, List<Response> response, OnItemClickListener listener) {
        this.context = context;
        this.response = response;
        this.listener=listener;
    }

    @NonNull
    @Override
    public DisplayListViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater= LayoutInflater.from(viewGroup.getContext()  );
        View view= inflater.inflate(R.layout.bus_list_view,viewGroup,false);
        return new DisplayListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DisplayListViewHolder holder, int i) {
        Response item = response.get(i);
        holder.bind(item,listener);

    }

    @Override
    public int getItemCount() {
        return response.size();
    }

    public class DisplayListViewHolder extends RecyclerView.ViewHolder {
        TextView busId,fromTime,toTime;
        public DisplayListViewHolder(@NonNull View itemView) {
            super(itemView);

            busId= itemView.findViewById(R.id.busId);
            fromTime= itemView.findViewById(R.id.fromTime);
            toTime= itemView.findViewById(R.id.toTime);

        }
        public  void bind(final Response item, final OnItemClickListener listener)
        {
            final List<TimeTable> timeTableList= item.getTimeTable();
            busId.setText(item.getBusName());
            fromTime.setText(timeTableList.get(0).getTime());
            toTime.setText(timeTableList.get(timeTableList.size()-1).getTime());

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClick(timeTableList);
                }
            });

        }
    }
}
