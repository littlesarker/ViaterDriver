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

        id=findViewById(R.id.UserID);
        name=findViewById(R.id.UserName_ID);
        mobile=findViewById(R.id.phoneNumber);
        verify=findViewById(R.id.verifyMeID);


        SharedPreferences sharedPreferences = getSharedPreferences("meDB", Context.MODE_PRIVATE);
        String Uid = sharedPreferences.getString("id", "");
        String userName = sharedPreferences.getString("UserName", "");
        String U_mobile = sharedPreferences.getString("mobile", "");
        String Vv=sharedPreferences.getString("vv", "");


        id.setText("User ID : " +Uid);
        name.setText("Name : "+userName);
        mobile.setText("Mobile : " +U_mobile);
        verify.setText("Verified : "+Vv);







    }
}