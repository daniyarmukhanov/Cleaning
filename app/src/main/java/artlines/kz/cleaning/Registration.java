package artlines.kz.cleaning;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.preference.PreferenceManager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class Registration extends ActionBarActivity {
    EditText emailET, passwordET, nameET, mobileET, addressET;
    Spinner citySpinner;
    CheckBox agree;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        getSupportActionBar().hide();
        ImageView back = (ImageView) findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        emailET = (EditText) findViewById(R.id.email);
        passwordET = (EditText) findViewById(R.id.password);
        nameET = (EditText) findViewById(R.id.name);
        mobileET = (EditText) findViewById(R.id.mobile);
        citySpinner = (Spinner) findViewById(R.id.city);
        addressET = (EditText) findViewById(R.id.address);


        final Button next = (Button) findViewById(R.id.next);
        agree=(CheckBox)findViewById(R.id.agree);
        agree.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    next.setEnabled(true);
                }else
                    next.setEnabled(false);
            }
        });
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (nameET.getText().toString().isEmpty())
                    nameET.setError("Это поле обязательно для заполнения");
                else if (!isValidEmailAddress(emailET.getText().toString())) {
                    emailET.setError("Введите правильный e-mail");
                } else if (mobileET.getText().toString().isEmpty())
                    mobileET.setError("Это поле обязательно для заполнения");
                else if (passwordET.getText().toString().isEmpty())
                    passwordET.setError("Это поле обязательно для заполнения");
                else if (addressET.getText().toString().isEmpty())
                    addressET.setError("Это поле обязательно для заполнения");
                else{
                    new MakePost().execute();
                }
            }
        });

    }

    private class MakePost extends AsyncTask {

        JSONParser jsonParser = new JSONParser();
        JSONObject jsonObject;
        ProgressDialog progressDialog;


        @Override
        protected void onPreExecute() {
            super.onPreExecute();


            progressDialog = new ProgressDialog(Registration.this);
            progressDialog.setMessage("Пожалуйста подождите...");
            progressDialog.setIndeterminate(false);
            progressDialog.setCancelable(true);

        }

        @Override
        protected Object doInBackground(Object[] objects) {


            String email = emailET.getText().toString();
            String password = passwordET.getText().toString();
            String name = nameET.getText().toString();
            String phone = mobileET.getText().toString();
            String city = citySpinner.getSelectedItem().toString();
            String address = addressET.getText().toString();

            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("email", email));
            params.add(new BasicNameValuePair("password", password));
            params.add(new BasicNameValuePair("api_password", "artlinesAa!"));
            params.add(new BasicNameValuePair("name", name));
            params.add(new BasicNameValuePair("city", city));
            params.add(new BasicNameValuePair("address", address));
            params.add(new BasicNameValuePair("phone", phone));


            jsonObject = jsonParser.makeHttpRequest(getResources().getString(R.string.url)+"user/APICreate", "POST", params);
            Log.d(city, jsonObject.toString());

            return null;

        }

        @Override
        protected void onPostExecute(Object o) {
            super.onPostExecute(o);
            progressDialog.dismiss();
            try {
                if (jsonObject.getString("result").equals("success")) {
                    Toast.makeText(getApplicationContext(), "Регистрация успешна", Toast.LENGTH_LONG).show();
                    startActivity(new Intent(Registration.this, MainActivity.class).putExtra("reg", "bs"));
                } else if (jsonObject.getString("result").equals("email exists")) {
                    Toast.makeText(getApplicationContext(), "Такой e-mail уже существует", Toast.LENGTH_LONG).show();

                } else
                    Toast.makeText(getApplicationContext(), "Ошибка в регистрации. Повторите попытку позже", Toast.LENGTH_LONG).show();

            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_registration, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public static boolean isValidEmailAddress(String email) {
        boolean stricterFilter = true;
        String stricterFilterString = "[A-Z0-9a-z._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,4}";
        String laxString = ".+@.+\\.[A-Za-z]{2}[A-Za-z]*";
        String emailRegex = stricterFilter ? stricterFilterString : laxString;
        java.util.regex.Pattern p = java.util.regex.Pattern.compile(emailRegex);
        java.util.regex.Matcher m = p.matcher(email);
        return m.matches();
    }
}
