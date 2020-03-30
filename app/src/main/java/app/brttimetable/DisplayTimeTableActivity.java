package app.brttimetable;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

import app.brttimetable.adapter.DisplayTimeTableListAdapter;
import app.brttimetable.model.TimeTable;

public class DisplayTimeTableActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_time_table);

        Gson gson = new Gson();
        String json = getIntent().getStringExtra("TimeTableList");
        Type type = new TypeToken<List<TimeTable>>(){}.getType();
        List<TimeTable> timeTableList = gson.fromJson(json,type);

        recyclerView = findViewById(R.id.displayTimeTableList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new DisplayTimeTableListAdapter(this,timeTableList));
    }
}
