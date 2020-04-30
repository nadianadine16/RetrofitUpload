package id.putraprima.retrofit.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import id.putraprima.retrofit.R;
import id.putraprima.retrofit.api.helper.ServiceGenerator;
import id.putraprima.retrofit.api.models.ApiError;
import id.putraprima.retrofit.api.models.ErrorUtils;
import id.putraprima.retrofit.api.models.RegisterRequest;
import id.putraprima.retrofit.api.models.RegisterResponse;
import id.putraprima.retrofit.api.services.ApiInterface;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity {

    EditText name,email,password,password_confirmation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        name = findViewById(R.id.name);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        password_confirmation = findViewById(R.id.password_confirmation);




    }

    public void handleRegister(View view) {
        if(password.length()<8){
            Toast.makeText(this, "Password need 8 Character", Toast.LENGTH_SHORT).show();
        }else{
            register();
        }

    }

    private void register() {
        ApiInterface service = ServiceGenerator.createService(ApiInterface.class);
        Call<RegisterResponse> call = service.register(new RegisterRequest(name.getText().toString(),email.getText().toString(), password.getText().toString(),password_confirmation.getText().toString()));
        call.enqueue(new Callback<RegisterResponse>() {
            @Override
            public void onResponse(Call<RegisterResponse> call, Response<RegisterResponse> response) {
                if(response.isSuccessful()){//[body()!=null] ki asline
                    Toast.makeText(RegisterActivity.this, "Data added", Toast.LENGTH_SHORT).show();
                }else{
                    //Toast.makeText(RegisterActivity.this, "Something Wrong", Toast.LENGTH_SHORT).show();

                    ApiError error = ErrorUtils.parseError(response);

                    if(error.getError().getName() !=null){
                        Toast.makeText(RegisterActivity.this, error.getError().getName().get(0), Toast.LENGTH_SHORT).show();
                    }

                    if(error.getError().getEmail() !=null){
                        Toast.makeText(RegisterActivity.this, error.getError().getEmail().get(0), Toast.LENGTH_SHORT).show();
                    }

                    if(error.getError().getPassword() !=null){
                        for(int i=0; i<error.getError().getPassword().size();i++){
                            Toast.makeText(RegisterActivity.this, error.getError().getPassword().get(i), Toast.LENGTH_SHORT).show();
                        }
                    }

                }
            }

            @Override
            public void onFailure(Call<RegisterResponse> call, Throwable t) {

            }
        });
    }

}
