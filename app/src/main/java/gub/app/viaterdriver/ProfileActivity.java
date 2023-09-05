package gub.app.viaterdriver;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;

public class ProfileActivity extends AppCompatActivity {


    TextView id,name,mobile,verify;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        name=findViewById(R.id.UserName_ID);
        mobile=findViewById(R.id.phoneNumber);



        SharedPreferences sharedPreferences = getSharedPreferences("meDB", Context.MODE_PRIVATE);
        String Uid = sharedPreferences.getString("id", "");
        String userName = sharedPreferences.getString("UserName", "");
        String U_mobile = sharedPreferences.getString("mobile", "");
        String Vv=sharedPreferences.getString("vv", "");



        name.setText("Name : "+userName);
        mobile.setText("Mobile : " +U_mobile);








    }
}