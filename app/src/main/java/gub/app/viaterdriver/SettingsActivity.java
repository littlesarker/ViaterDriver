package gub.app.viaterdriver;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class SettingsActivity extends AppCompatActivity {


    private Button resetPass,upDateProfile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);


        resetPass=findViewById(R.id.changePassID);
        upDateProfile=findViewById(R.id.UpdateProfileID);



        resetPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(SettingsActivity.this,ResetPasswordActivity.class);
                startActivity(intent);
            }
        });

        upDateProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(SettingsActivity.this,UpdateProfileActivity.class);
                startActivity(intent);

            }
        });





        BottomNavigationView bottomNavigationView=findViewById(R.id.bottom_navigation3);

        // Set Home selected
        bottomNavigationView.setSelectedItemId(R.id.settings);

        // Perform item selected listener
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch(item.getItemId())
                {
                    case R.id.home:
                        startActivity(new Intent(getApplicationContext(),HomeActivity.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.settings:
                        return true;
                    case R.id.biding:
                        startActivity(new Intent(getApplicationContext(),BidingActivity.class));
                        overridePendingTransition(0,0);
                        return true;
                }
                return false;
            }
        });



    }
}