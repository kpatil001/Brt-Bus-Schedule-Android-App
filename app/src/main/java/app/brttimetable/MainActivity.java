package app.brttimetable;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

import app.brttimetable.model.LoginDtls;
import app.brttimetable.model.LoginResponse;
import app.brttimetable.model.ResponseMessage;
import app.brttimetable.model.UserDtls;
import app.brttimetable.retrofit.RestApiClient;
import app.brttimetable.retrofit.RetrofitClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private EditText emailET,pwdET;
    private Button loginBut,registerBut;
    private RestApiClient client;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        emailET= findViewById(R.id.emailEditText);
        pwdET = findViewById(R.id.pwdEditText);
        loginBut=findViewById(R.id.loginButton);
        registerBut=findViewById(R.id.registerButton);

        //When Login Is Cicked
        loginBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoginDtls loginDtls= new LoginDtls(emailET.getText().toString(),pwdET.getText().toString());
                loginTest(loginDtls);
                Log.d("onCLickevent","button clicked");
            }
        });

        //When Register Button is clicked
        registerBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,RegisterActivity.class);
                startActivity(intent);
            }
        });

    }

    //TO get a specific record
    private void getRecord()
    {
        Call<UserDtls> getCall = client.gerUsers(7);

        getCall.enqueue(new Callback<UserDtls>() {
            @Override
            public void onResponse(Call<UserDtls> call, Response<UserDtls> response) {
                if(response.body()!=null) {
                    UserDtls userDtls = response.body();
                    Log.d("onResponse","Response is First Name::"+userDtls.getFirstName()+" Last Name::"+ userDtls.getLastName());
                }
                else{
                    Log.d("response Empty","Response is empty");
                }

            }

            @Override
            public void onFailure(Call<UserDtls> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    //TO check login
    private void loginTest(final LoginDtls loginDtls)
    {
        RestApiClient client = RetrofitClient.getClient().create(RestApiClient.class);
       Call<LoginResponse> loginCall = client.checkLogin(loginDtls);
       loginCall.enqueue(new Callback<LoginResponse>() {
           @Override
           public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {

               if(response.isSuccessful()) {
                   if (response.body() != null) {
                       ResponseMessage responseMessage = response.body().getResponseMessage();
                       int status = responseMessage.getStatus();
                       if (status == 200) {
                           Toast.makeText(MainActivity.this, responseMessage.getMessage(), Toast.LENGTH_SHORT).show();

                           Gson gson =new Gson();
                           Type type = new TypeToken<LoginDtls>(){}.getType();
                           String json = gson.toJson(loginDtls,type);
                           Intent intent= new Intent(MainActivity.this,FromToActivity.class);
                           intent.putExtra("LoginDtls",json);
                           startActivity(intent);

                       } else {
                           Toast.makeText(MainActivity.this, responseMessage.getMessage(), Toast.LENGTH_SHORT).show();
                       }
                   } else {
                       Toast.makeText(MainActivity.this, "Empty Response", Toast.LENGTH_SHORT).show();
                   }
               }
               else {
                   Toast.makeText(MainActivity.this, "Unsucessful Login", Toast.LENGTH_SHORT).show();
               }
           }

           @Override
           public void onFailure(Call<LoginResponse> call, Throwable t) {
               Toast.makeText(MainActivity.this, "Oops! Unable to connect", Toast.LENGTH_SHORT).show();
           }
       });
    }
}
