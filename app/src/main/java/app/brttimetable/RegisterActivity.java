package app.brttimetable;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import app.brttimetable.model.LoginDtls;
import app.brttimetable.model.LoginResponse;
import app.brttimetable.model.ResponseMessage;
import app.brttimetable.model.UserDtls;
import app.brttimetable.retrofit.RestApiClient;
import app.brttimetable.retrofit.RetrofitClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity{

    private EditText firstName,lastName,phoneNumber,address,email,pwd;
    private Button registerBut2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_activity);

        firstName= findViewById(R.id.firstNameET);
        lastName= findViewById(R.id.lastNameET);
        phoneNumber=findViewById(R.id.phoneNumberEt);
        address= findViewById(R.id.addressET);
        email=findViewById(R.id.emailET);
        pwd=findViewById(R.id.pwdET);
        registerBut2= findViewById(R.id.registerBut2);
        registerBut2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                UserDtls user = new UserDtls();
                LoginDtls loginDtls= new LoginDtls(email.getText().toString(),pwd.getText().toString());
                user.setFirstName(firstName.getText().toString());
                user.setLastName(lastName.getText().toString());
                user.setPhoneNo(phoneNumber.getText().toString());
                user.setAddress(address.getText().toString());
                user.setLoginDtls(loginDtls);

                registerService(user);

            }
        });

    }

    private void registerService(UserDtls userDtls) {
        RestApiClient client = RetrofitClient.getClient().create(RestApiClient.class);

        Call<LoginResponse> registerResponse = client.registerUser(userDtls);
        registerResponse.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                if(response.isSuccessful()) {
                    if(response.body()!= null) {
                        ResponseMessage responseMessage = response.body().getResponseMessage();

                        if(responseMessage.getStatus()==200) {
                            Toast.makeText(RegisterActivity.this, "Successfully Registered", Toast.LENGTH_SHORT).show();
                        }
                        else{
                            Toast.makeText(RegisterActivity.this, responseMessage.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                Toast.makeText(RegisterActivity.this, "Oops! Unable to connect", Toast.LENGTH_SHORT).show();

            }
        });
    }
}
