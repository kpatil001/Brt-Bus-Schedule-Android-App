package app.brttimetable.retrofit;

import app.brttimetable.model.FetchTimeTableResponse;
import app.brttimetable.model.FromTo;
import app.brttimetable.model.LoginDtls;
import app.brttimetable.model.LoginResponse;
import app.brttimetable.model.UserDtls;
import retrofit2.Call;
//import retrofit2.http.Body;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface RestApiClient {
    @GET("get-record/{id}")
    Call<UserDtls> gerUsers(@Path("id") int id);

    @POST("check-login")
    Call<LoginResponse> checkLogin(@Body LoginDtls loginDtls);

    @POST("register")
    Call<LoginResponse> registerUser(@Body UserDtls userDtls);

    @POST("fetch-time-table")
    Call<FetchTimeTableResponse> fetchTimeTable(@Body FromTo fromTo);
}
