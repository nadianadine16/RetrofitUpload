package id.putraprima.retrofit.api.services;


import java.util.List;
import java.util.Map;

import id.putraprima.retrofit.api.models.AppVersion;
import id.putraprima.retrofit.api.models.Data;
import id.putraprima.retrofit.api.models.Envelope;
import id.putraprima.retrofit.api.models.LoginRequest;
import id.putraprima.retrofit.api.models.LoginResponse;
import id.putraprima.retrofit.api.models.Profile;
import id.putraprima.retrofit.api.models.Recipe;
import id.putraprima.retrofit.api.models.RegisterRequest;
import id.putraprima.retrofit.api.models.RegisterResponse;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.PartMap;
import retrofit2.http.Query;

public interface ApiInterface{
    @GET("/")
    Call<AppVersion> getAppVersion();

    @POST("/api/auth/login")
    Call<LoginResponse> login(@Body LoginRequest loginRequest);

    @POST("/api/auth/register")
    Call<RegisterResponse> register(@Body RegisterRequest registerRequest);

    @GET("/api/auth/me")
    Call<Data<Profile>> showProfile(@Header("Authorization") String token);

    @GET("/api/recipe")
    Call<Envelope<List<Recipe>>> doRecipe();

    @GET("/api/recipe")
    Call<Envelope<List<Recipe>>> doLoadMore(@Query("page") int page);

    @Multipart
    @POST("/api/recipe")
    Call<ResponseBody> doUpload(@Part MultipartBody.Part photo, @PartMap Map<String, RequestBody> text);
}
