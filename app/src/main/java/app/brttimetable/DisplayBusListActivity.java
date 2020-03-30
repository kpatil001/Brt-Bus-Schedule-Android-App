package app.brttimetable;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

import app.brttimetable.adapter.DisplayBusListAdapter;
import app.brttimetable.model.Response;
import app.brttimetable.model.TimeTable;

public class DisplayBusListActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bus_display_list);

        final Gson gson = new Gson();
        String json=  getIntent().getStringExtra("BusList");
        final Type type = new TypeToken<List<Response>>(){}.getType();
        final List<Response> response  =  gson.fromJson(json,type);

        recyclerView= findViewById(R.id.displayList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new DisplayBusListAdapter(this, response, new DisplayBusListAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(List<TimeTable> timeTableList) {
                final Type type2 = new TypeToken<List<TimeTable>>(){}.getType();
                String json2= gson.toJson(timeTableList,type2);
                Intent intent = new Intent(DisplayBusListActivity.this,DisplayTimeTableActivity.class);
                intent.putExtra("TimeTableList",json2);
                startActivity(intent);
            }
        }));


    }
}
