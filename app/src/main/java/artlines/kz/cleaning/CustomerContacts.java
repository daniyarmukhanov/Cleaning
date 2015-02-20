package artlines.kz.cleaning;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Paint;
import android.net.Uri;
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
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class CustomerContacts extends ActionBarActivity {
    EditText emailET, nameET, mobileET;
    CheckBox agree;
    String recieved;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_contacts);
        getSupportActionBar().hide();
        recieved = "";
        if (getIntent().hasExtra("type")) {
            recieved = getIntent().getStringExtra("type");
        }
        TextView call = (TextView) findViewById(R.id.call);
        call.setPaintFlags(call.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse(getResources().getString(R.string.number_to_call)));
                startActivity(intent);
            }
        });
        TextView reg = (TextView) findViewById(R.id.registertext);
        reg.setPaintFlags(reg.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CustomerContacts.this, Registration.class));
            }
        });
        ImageView back = (ImageView) findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        final Button next = (Button) findViewById(R.id.next);
        emailET = (EditText) findViewById(R.id.mail);
        nameET = (EditText) findViewById(R.id.name);
        mobileET = (EditText) findViewById(R.id.mobile);
        agree = (CheckBox) findViewById(R.id.agree);
        agree.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    next.setEnabled(true);
                } else
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
                else {
                    new RegWithoutAuth().execute();
                }

            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_customer_contacts, menu);
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

    private class RegWithoutAuth extends AsyncTask {

        JSONParser jsonParser = new JSONParser();
        JSONObject jsonObject;
        ProgressDialog progressDialog;
        String email, name, phone;


        @Override
        protected void onPreExecute() {
            super.onPreExecute();


            progressDialog = new ProgressDialog(CustomerContacts.this);
            progressDialog.setMessage("Пожалуйста подождите...");
            progressDialog.setIndeterminate(false);
            progressDialog.setCancelable(true);

        }

        @Override
        protected Object doInBackground(Object[] objects) {


            email = emailET.getText().toString();
            name = nameET.getText().toString();
            phone = mobileET.getText().toString();

            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("email", email));
            params.add(new BasicNameValuePair("api_password", "artlinesAa!"));
            params.add(new BasicNameValuePair("name", name));
            params.add(new BasicNameValuePair("phone", phone));


            jsonObject = jsonParser.makeHttpRequest(getResources().getString(R.string.url) + "user/APICreateWithoutContact", "POST", params);
            Log.d("REG STATUS", jsonObject.toString());

            return null;

        }

        @Override
        protected void onPostExecute(Object o) {
            super.onPostExecute(o);
            progressDialog.dismiss();
            try {
                if (jsonObject.getString("result").equals("success")) {
                    SharedPreferences myPref = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                    myPref.edit().putBoolean("authorized", true).commit();
                    myPref.edit().putString("mail", email).commit();
                    myPref.edit().putString("phone", phone).commit();
                    if (!recieved.equals("")) {
                        if (recieved.equals("chemistry"))
                            startActivity(new Intent(CustomerContacts.this, CalculatorSuper.class));
                        if (recieved.equals("dev"))
                            startActivity(new Intent(CustomerContacts.this, CalculatorDev.class));
                        if (recieved.equals("metal"))
                            startActivity(new Intent(CustomerContacts.this, CalculatorMetal.class));
                        if (recieved.equals("pvh"))
                            startActivity(new Intent(CustomerContacts.this, CalculatorPVH.class));
                        if (recieved.equals("channel"))
                            startActivity(new Intent(CustomerContacts.this, CalculatorChannel.class));
                        if (recieved.equals("fayans"))
                            startActivity(new Intent(CustomerContacts.this, CalculatorFayans.class));
                        if (recieved.equals("warm"))
                            startActivity(new Intent(CustomerContacts.this, CalculatorWarm.class));
                    } else
                        startActivity(new Intent(CustomerContacts.this, CalculatorGen.class));
                } else if (jsonObject.getString("result").equals("email exists")) {
                    Toast.makeText(getApplicationContext(), "Такой e-mail уже существует", Toast.LENGTH_LONG).show();

                } else
                    Toast.makeText(getApplicationContext(), "Ошибка. Повторите попытку позже", Toast.LENGTH_LONG).show();

            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
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
