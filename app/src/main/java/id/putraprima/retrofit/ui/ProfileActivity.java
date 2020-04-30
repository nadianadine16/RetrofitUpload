package id.putraprima.retrofit.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import id.putraprima.retrofit.R;
import id.putraprima.retrofit.api.helper.ServiceGenerator;
import id.putraprima.retrofit.api.models.Data;
import id.putraprima.retrofit.api.models.Profile;
import id.putraprima.retrofit.api.models.RegisterRequest;
import id.putraprima.retrofit.api.models.RegisterResponse;
import id.putraprima.retrofit.api.services.ApiInterface;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfileActivity extends AppCompatActivity {

    TextView name,email;
    String token,tk;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        name = findViewById(R.id.name);
        email = findViewById(R.id.email);


        Bundle extras = getIntent().getExtras();
        token = extras.getString("TOKEN");
        tk = extras.getString("TOKEN_TYPE");

        viewProfile();
    }

    private void viewProfile() {
        ApiInterface service = ServiceGenerator.createService(ApiInterface.class);
        Call<Data<Profile>> call = service.showProfile(tk+" "+token);
        call.enqueue(new Callback<Data<Profile>>() {
            @Override
            public void onResponse(Call<Data<Profile>> call, Response<Data<Profile>> response) {
                if (response.body()!=null){
                    Toast.makeText(ProfileActivity.this, "Halo", Toast.LENGTH_SHORT).show();
                    name.setText(response.body().data.getName());
                    email.setText(response.body().data.getEmail());
                }else{
                    Toast.makeText(ProfileActivity.this, "something wrong", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Data<Profile>> call, Throwable t) {
                Toast.makeText(ProfileActivity.this, "Error Connection", Toast.LENGTH_SHORT).show();
            }
        });
    }


    public void tombolRecipe(View view) {
        Intent itn = new Intent(this , RecipeActivity.class);
        startActivity(itn);
    }
}
