package gub.app.viaterdriver;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class HomeActivity extends AppCompatActivity {


    ImageView notify, profile;
    private TextView MyName;


    private String URLstring = "https://demonuts.com/Demonuts/JsonTest/Tennis/json_parsing.php";
    private static ProgressDialog mProgressDialog;
    private ListView listView;
    ArrayList<DataModel> dataModelArrayList;
    private ListAdapter listAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);


        profile = findViewById(R.id.img);
        notify = findViewById(R.id.notifyID);
        MyName=findViewById(R.id.driverNameID);
        ListView listView = findViewById(R.id.lv);
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation1);


        db();
        retrieveJSON();


        notify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Notifiaction.class);
                startActivity(intent);
            }
        });


        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), ProfileActivity.class);
                startActivity(intent);
            }
        });


        bottomNavigationView.setSelectedItemId(R.id.home);
        // Perform item selected listener
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()) {
                    case R.id.biding:
                        startActivity(new Intent(getApplicationContext(), BidingActivity.class));
                        overridePendingTransition(0, 0);
                        return true;
                    case R.id.home:

                        return true;
                    case R.id.settings:
                        startActivity(new Intent(getApplicationContext(), SettingsActivity.class));
                        overridePendingTransition(0, 0);
                        return true;
                }
                return false;
            }
        });




    }
    public void db() {
        SharedPreferences sharedPreferences = getSharedPreferences("meDB", Context.MODE_PRIVATE);
        String id = sharedPreferences.getString("id", "");
        String userName = sharedPreferences.getString("UserName", "");

        MyName.setText(userName);

        if (!id.isEmpty() && !userName.isEmpty()) {
            // The user has already logged in. Go to the home activity.
            Toast.makeText(getApplicationContext(), "Welcome", Toast.LENGTH_SHORT).show();
        } else {
            // The user has not logged in yet. Go to the login activity.
            GetMe();
        }

    }
    public void GetMe() {


        RequestQueue requestQueue = Volley.newRequestQueue(this);

        String myUrl = "https://viater.vercel.app/api/user/me";

        StringRequest stringRequest = new StringRequest(Request.Method.GET, myUrl,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Display the first 500 characters of the response string.


                        try {
                            //Create a JSON object containing information from the API.
                            JSONObject myJsonObject = new JSONObject(response);

                            String ID = myJsonObject.getJSONObject("data").getString("id");
                            String NAME = myJsonObject.getJSONObject("data").getString("full_name");
                            String MOBILE = myJsonObject.getJSONObject("data").getString("mobile_number");
                            String VERIFY = myJsonObject.getJSONObject("data").getString("verified");

                            SharedPreferences sharedPreferences = getSharedPreferences("meDB", Context.MODE_PRIVATE);
                            SharedPreferences.Editor editor = sharedPreferences.edit();
                            editor.putString("id", ID);
                            editor.putString("UserName", NAME);
                            editor.putString("mobile", MOBILE);
                            editor.putString("vv", VERIFY);
                            editor.apply();



                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), "Don't get Data", Toast.LENGTH_LONG).show();
            }

        }) {
            public Map<String, String> getHeaders() {
                SharedPreferences sharedPreferences = getSharedPreferences("my_preferences", Context.MODE_PRIVATE);
                String ck = sharedPreferences.getString("csk", "");
                Map<String, String> params = new HashMap<String, String>();
                params.put("Content-Type", "application/json; charset=utf-8");
                params.put("Cookie", ck);
                System.out.println(ck);
                return params;
            }
        };

        requestQueue.add(stringRequest);

    }
    private void retrieveJSON() {

        showSimpleProgressDialog(this, "Loading...","Fetching Request",false);

        StringRequest stringRequest = new StringRequest(Request.Method.GET, URLstring,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        Log.d("strrrrr", ">>" + response);

                        try {

                            JSONObject obj = new JSONObject(response);


                                dataModelArrayList = new ArrayList<>();
                                JSONArray dataArray  = obj.getJSONArray("data");

                                for (int i = 0; i < dataArray.length(); i++) {

                                    DataModel playerModel = new DataModel();
                                    JSONObject dataobj = dataArray.getJSONObject(i);

                                    playerModel.setmID(dataobj.getString("id"));
                                    playerModel.setmFrom_lat(dataobj.getString("from_lat"));
                                    playerModel.setmFrom_lng(dataobj.getString("from_lng"));
                                    playerModel.setMto_lat(dataobj.getString("to_lat"));
                                    playerModel.setMto_lng(dataobj.getString("to_lng"));
                                    playerModel.setmBudget(Integer.parseInt(dataobj.getString("budget")));
                                    playerModel.setmAdditionalInfo(dataobj.getString("additional_requirements"));
                                    playerModel.setmCreatedAT(dataobj.getString("created_at"));
                                    playerModel.setmDeparture_time(dataobj.getString("departure_time"));
                                    playerModel.setmStatus(dataobj.getString("status"));


                                    dataModelArrayList.add(playerModel);

                                }

                                setupListview();



                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //displaying the error in toast if occurrs
                        Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });

        // request queue
        RequestQueue requestQueue = Volley.newRequestQueue(this);

        requestQueue.add(stringRequest);


    }
    private void setupListview(){
        removeSimpleProgressDialog();  //will remove progress dialog
        listAdapter = new ListAdapter(this, dataModelArrayList);
        listView.setAdapter(listAdapter);
    }

    public static void removeSimpleProgressDialog() {
        try {
            if (mProgressDialog != null) {
                if (mProgressDialog.isShowing()) {
                    mProgressDialog.dismiss();
                    mProgressDialog = null;
                }
            }
        } catch (IllegalArgumentException ie) {
            ie.printStackTrace();

        } catch (RuntimeException re) {
            re.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void showSimpleProgressDialog(Context context, String title,
                                                String msg, boolean isCancelable) {
        try {
            if (mProgressDialog == null) {
                mProgressDialog = ProgressDialog.show(context, title, msg);
                mProgressDialog.setCancelable(isCancelable);
            }

            if (!mProgressDialog.isShowing()) {
                mProgressDialog.show();
            }

        } catch (IllegalArgumentException ie) {
            ie.printStackTrace();
        } catch (RuntimeException re) {
            re.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}