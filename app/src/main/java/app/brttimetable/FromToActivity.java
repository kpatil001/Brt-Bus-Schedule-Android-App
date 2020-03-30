package app.brttimetable;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

import app.brttimetable.model.FetchTimeTableResponse;
import app.brttimetable.model.FromTo;
import app.brttimetable.model.LoginDtls;
import app.brttimetable.retrofit.RestApiClient;
import app.brttimetable.retrofit.RetrofitClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FromToActivity extends AppCompatActivity {

    private EditText fromET,toET;
    private Button searchBut;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_from_to);

        Gson gson = new Gson();
        String json=  getIntent().getStringExtra("LoginDtls");
        LoginDtls loginDtls = gson.fromJson(json,LoginDtls.class);


        fromET= findViewById(R.id.fromET);
        toET=findViewById(R.id.toET);
        searchBut=findViewById(R.id.searchBut);

        searchBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FromTo fromTo = new FromTo(fromET.getText().toString(),toET.getText().toString());
                displayTimeTable(fromTo);

            }
        });

    }

    private void displayTimeTable(FromTo fromTo)
    {
        RestApiClient client= RetrofitClient.getClient().create(RestApiClient.class);

        Call<FetchTimeTableResponse> call =  client.fetchTimeTable(fromTo);
        call.enqueue(new Callback<FetchTimeTableResponse>() {
            @Override
            public void onResponse(Call<FetchTimeTableResponse> call, Response<FetchTimeTableResponse> response) {
                if(response.isSuccessful()) {
                    if (response.body() != null) {
                        FetchTimeTableResponse fetchTimeTableResponse = response.body();
                        int status = fetchTimeTableResponse.getResponseMessage().getStatus();
                        if (status == 200) {
                            Toast.makeText(FromToActivity.this, fetchTimeTableResponse.getResponseMessage().getMessage(), Toast.LENGTH_SHORT).show();
                            Gson gson =new Gson();
                            Type type =  new TypeToken<List<Response>>(){}.getType();
                            String json = gson.toJson(fetchTimeTableResponse.getResponse(),type);
                            Intent intent = new Intent(FromToActivity.this,DisplayBusListActivity.class);
                            intent.putExtra("BusList",json);
                            startActivity(intent);




                        } else {
                            Toast.makeText(FromToActivity.this, fetchTimeTableResponse.getResponseMessage().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(FromToActivity.this, "Empty Response", Toast.LENGTH_SHORT).show();
                    }
                }
                else {
                    Toast.makeText(FromToActivity.this, "Unsucessful Login", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<FetchTimeTableResponse> call, Throwable t) {

            }
        });

    }
}
