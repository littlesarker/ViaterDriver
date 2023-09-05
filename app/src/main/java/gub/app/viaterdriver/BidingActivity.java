package gub.app.viaterdriver;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class BidingActivity extends AppCompatActivity {


    private TextView Flat,Flng,Tlat,Tlng,Pbuget,Pinfo;
    private Button bid,accept,start;
    private String bidId;
    EditText offer,msg;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_biding);

        Flat=findViewById(R.id.flatID);
        Flng=findViewById(R.id.flngID);
        Tlat=findViewById(R.id.tlatID);
        Tlng=findViewById(R.id.tlngID);
        Pbuget=findViewById(R.id.pbudgetID);
        Pinfo=findViewById(R.id.pInfotID);
        offer=findViewById(R.id.offerID);
        msg=findViewById(R.id.messageID);
        start=findViewById(R.id.StartRideID);
        bid=findViewById(R.id.bidSubmitID);
        accept=findViewById(R.id.acceptID);




        Intent intent = getIntent();
        String strI = intent.getStringExtra("message_key");
        Toast.makeText(getApplicationContext(),strI,Toast.LENGTH_LONG).show();

        getSOrderRequest(strI);

        accept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                start.setVisibility(View.VISIBLE);
            }
        });

        RideStartButton();


        bid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!offer.getText().toString().isEmpty() && !msg.getText().toString().isEmpty()){
                    CreateBid();
                    offer.setText("");
                    msg.setText("");
                }else{
                    Toast.makeText(getApplicationContext(),"Inputs are  are Empty",Toast.LENGTH_LONG).show();
                }

            }
        });




        BottomNavigationView bottomNavigationView=findViewById(R.id.bottom_navigation2);

        // Set Home selected
        bottomNavigationView.setSelectedItemId(R.id.biding);

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
                    case R.id.biding:
                        return true;
                    case R.id.settings:
                        startActivity(new Intent(getApplicationContext(),SettingsActivity.class));
                        overridePendingTransition(0,0);
                        return true;
                }
                return false;
            }
        });


    }
    public void getSOrderRequest(String str){


        String myUrl = "https://viater.vercel.app/api/order/get-order";
        RequestQueue requestQueue = Volley.newRequestQueue(this);

        JSONObject postData = new JSONObject();
        try {
            postData.put("id", str);


        } catch (JSONException e) {
            e.printStackTrace();
        }

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, myUrl, postData, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Toast.makeText(getApplicationContext(), "Response: "+response, Toast.LENGTH_LONG).show();

                try {
                    //Create a JSON object containing information from the API.
                    JSONObject myJsonObject = new JSONObject();

                    bidId = response.getJSONObject("data").getString("id");
                    String flat = response.getJSONObject("data").getString("from_lat");
                    String flng = response.getJSONObject("data").getString("from_lng");
                    String tlat = response.getJSONObject("data").getString("to_lat");
                    String tlng = response.getJSONObject("data").getString("to_lng");
                    String Sbudget = response.getJSONObject("data").getString("budget");
                    String pinfo = response.getJSONObject("data").getString("additional_requirements");


                    Flat.setText("From_Lat  :"+flat);
                    Flng.setText("From_lng :"+flng);
                    Tlat.setText("To_lat :"+tlat);
                    Tlng.setText("To_lng :"+tlng);
                    Pbuget.setText("Budget : "+Sbudget);
                    Pinfo.setText("Info : "+pinfo);






                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        } ){
            @Override
            public Map<String, String> getHeaders()  {
                SharedPreferences sharedPreferences = getSharedPreferences("my_preferences", Context.MODE_PRIVATE);
                String ck = sharedPreferences.getString("csk", "");
                Map<String, String> pp = new HashMap<String, String>();
                pp.put("Cookie", ck);

                return pp;
            }
        };


        Volley.newRequestQueue(this).add(jsonObjectRequest);



    }
    public void CreateBid(){


        String offerS=offer.getText().toString();
        String message=msg.getText().toString();

        String myUrl = "https://viater.vercel.app/api/bid/create";
        RequestQueue requestQueue = Volley.newRequestQueue(this);

        JSONObject postData = new JSONObject();
        try {
            postData.put("id", bidId);
            postData.put("amount", offerS);
            postData.put("additional_message", message);



        } catch (JSONException e) {
            e.printStackTrace();
        }

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, myUrl, postData, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Toast.makeText(getApplicationContext(), "Response: "+response, Toast.LENGTH_LONG).show();


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        } ){
            @Override
            public Map<String, String> getHeaders()  {
                SharedPreferences sharedPreferences = getSharedPreferences("my_preferences", Context.MODE_PRIVATE);
                String ck = sharedPreferences.getString("csk", "");
                Map<String, String> pp = new HashMap<String, String>();
                pp.put("Cookie", ck);

                return pp;
            }
        };


        Volley.newRequestQueue(this).add(jsonObjectRequest);




    }
    public void RideStartButton(){
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent=new Intent(Intent.ACTION_VIEW,
                        Uri.parse("google.navigation:q="+Tlat.getText().toString()+","+Tlng.getText().toString()+"&mode=d"));
                intent.setPackage("com.google.android.apps.maps");


                if(intent.resolveActivity(getPackageManager())!=null ){
                    startActivity(intent);
                }

            }
        });
    }

}