package com.prakruthi.ui;
import static android.content.ContentValues.TAG;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import com.prakruthi.R;
import com.vishnusivadas.advanced_httpurlconnection.PutData;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.Objects;
public class Login extends AppCompatActivity {
    TextView register,forget_password;
    EditText username,password;
    AppCompatButton login;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Objects.requireNonNull(getSupportActionBar()).hide();
        register = findViewById(R.id.register_an_account_login);
        forget_password = findViewById(R.id.forget_password_login);
        username = findViewById(R.id.edittext_user_name);
        password = findViewById(R.id.edittext_login_password);
        login = findViewById(R.id.login_btn);

        register.setOnClickListener(view -> {
            startActivity(new Intent(Login.this, RegistrationForm.class));
        });
        forget_password.setOnClickListener(view -> {
            startActivity(new Intent(Login.this, ForgetPassword.class));
        });
        login.setOnClickListener(view -> {
            if (username.getText().toString().isEmpty())
            {
                username.setError("Username is Required");
            }
            else if (password.getText().toString().isEmpty())
            {
                password.setError("Password is Required");
            }
            if(!username.getText().toString().isEmpty() && !password.getText().toString().isEmpty())
            {
                Api(username.getText().toString(),password.getText().toString());
            }
        });

    }
    public void Api(String usernameString,String passwordString)
    {
        login.setVisibility(View.INVISIBLE);
        Handler handler = new Handler(Looper.getMainLooper());
        handler.post(new Runnable() {
            @Override
            public void run() {
                //Creating array for parameters
                String[] field = new String[2];
                field[0] = "mobile";
                field[1] = "password";
                //Creating array for data
                String[] data = new String[2];
                data[0] = usernameString;
                data[1] = passwordString;
                PutData putData = new PutData(Variables.BaseUrl+"login", "POST", field, data);
                if (putData.startPut()) {
                    if (putData.onComplete()) {
                        // result = Api Result
                        String result = putData.getResult();
                        try {
                            JSONObject json = new JSONObject(result);
                            boolean statusCode = json.getBoolean("status_code");
                            int loggedIn = json.getInt("loggedIn");
                            String message = json.getString("message");
                            if (statusCode)
                            {
                                Toast.makeText(Login.this, message, Toast.LENGTH_SHORT).show();
                                getUserData(json);
                            }
                            else
                            {
                                Toast.makeText(Login.this, message, Toast.LENGTH_SHORT).show();
                                username.setError("Invalid");
                                password.setError("Invalid");
                                login.setVisibility(View.VISIBLE);
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                            login.setVisibility(View.VISIBLE);
                            Toast.makeText(Login.this, "Network Error", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
                else {
                    login.setVisibility(View.VISIBLE);
                }
            }
        });
    }
    public void getUserData(JSONObject ResultJson)
    {
        try {
            String token = ResultJson.getString("token");
            JSONArray userDetailsArray = ResultJson.getJSONArray("user_details");
            JSONObject userDetails = userDetailsArray.getJSONObject(0);
            int id = userDetails.getInt("id");
            int departmentId = userDetails.getInt("department_id");
            String userCode = userDetails.optString("user_code", "");
            String name = userDetails.optString("name", "");
            String lastName = userDetails.optString("last_name", "");
            String email = userDetails.optString("email", "");
            String password = userDetails.optString("password", "");
            String mobile = userDetails.optString("mobile", "");
            String gender = userDetails.optString("gender", "");
            String dob = userDetails.optString("dob", "");
            String attachment = userDetails.optString("attachment", "");
            String city = userDetails.optString("city", "");
            String postCode = userDetails.optString("postCode", "");
            String address = userDetails.optString("address", "");
            String state = userDetails.optString("state", "");
            String country = userDetails.optString("country", "");
            String district = userDetails.optString("district", "");
            String street = userDetails.optString("street", "");
            String about = userDetails.optString("about", "");
            String status = userDetails.optString("status", "");
            String mobileVerified = userDetails.optString("mobile_verified", "");
            String isVerified = userDetails.optString("is_verified", "");
            String logDateCreated = userDetails.optString("log_date_created", "");
            String createdBy = userDetails.optString("created_by", "");
            String logDateModified = userDetails.optString("log_date_modified", "");
            String modifiedBy = userDetails.optString("modified_by", "");
            String fcmToken = userDetails.optString("fcm_token", "");
            String deviceId = userDetails.optString("device_id", "");
            String allowEmail = userDetails.optString("allow_email", "");
            String allowSms = userDetails.optString("allow_sms", "");
            String allowPush = userDetails.optString("allow_push", "");

            // Store values in static variables
            Variables.token = token;
            Variables.id = id;
            Variables.departmentId = departmentId;
            Variables.userCode = userCode;
            Variables.name = name;
            Variables.lastName = lastName;
            Variables.email = email;
            Variables.password = password;
            Variables.mobile = mobile;
            Variables.gender = gender;
            Variables.dob = dob;
            Variables.attachment = attachment;
            Variables.city = city;
            Variables.postCode = postCode;
            Variables.address = address;
            Variables.state = state;
            Variables.country = country;
            Variables.district = district;
            Variables.street = street;
            Variables.about = about;
            Variables.status = status;
            Variables.mobileVerified = mobileVerified;
            Variables.isVerified = isVerified;
            Variables.logDateCreated = logDateCreated;
            Variables.createdBy = createdBy;
            Variables.logDateModified = logDateModified;
            Variables.modifiedBy = modifiedBy;
            Variables.fcmToken = fcmToken;
            Variables.deviceId = deviceId;
            Variables.allowEmail = allowEmail;
            Variables.allowSms = allowSms;
            Variables.allowPush = allowPush;

            login.setVisibility(View.VISIBLE);
        }
        catch (JSONException e) {
            Log.e(TAG, e.toString() );
            Toast.makeText(this, "System Error", Toast.LENGTH_SHORT).show();
            login.setVisibility(View.VISIBLE);
        }

    }

}