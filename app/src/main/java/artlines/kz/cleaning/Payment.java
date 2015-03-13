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
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class Payment extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);
        getSupportActionBar().hide();
        TextView call=(TextView)findViewById(R.id.call);
        call.setPaintFlags(call.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse(getResources().getString(R.string.number_to_call)));
                startActivity(intent);
            }
        });
        ImageView back= (ImageView) findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        TextView sum=(TextView)findViewById(R.id.sum);
        SharedPreferences sharedPreferences=PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        sum.setText(sharedPreferences.getString("price","0")+" тг");
        LinearLayout cash=(LinearLayout)findViewById(R.id.cash);
        cash.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("PRESSED","TRUE");
                new CreateOrder().execute();
            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_payment, menu);
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
    private class CreateOrder extends AsyncTask {

        JSONParser jsonParser = new JSONParser();
        JSONObject jsonObject;
        ProgressDialog progressDialog;
        String email, password,api_password,is_user,text,type,price,name,city,address,phone;



        @Override
        protected void onPreExecute() {
            super.onPreExecute();


            progressDialog = new ProgressDialog(Payment.this);
            progressDialog.setMessage("Пожалуйста подождите...");
            progressDialog.setIndeterminate(false);
            progressDialog.setCancelable(true);

        }

        @Override
        protected Object doInBackground(Object[] objects) {
            SharedPreferences sharedPreferences=PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
            Boolean authorized=sharedPreferences.getBoolean("authorized",false);
            email=sharedPreferences.getString("email","");
            password=authorized?sharedPreferences.getString("password",""):"";
            api_password="artlinesAa!";
            is_user=authorized?"yes":"no";
            text=sharedPreferences.getString("text","");
            type=sharedPreferences.getString("type","");
            price=sharedPreferences.getString("price","");
            name=authorized?"":sharedPreferences.getString("name","");
            city=authorized?"":sharedPreferences.getString("city","");
            address=authorized?"":sharedPreferences.getString("address","");
            phone=authorized?"":sharedPreferences.getString("phone","");
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("email", email));
            params.add(new BasicNameValuePair("password", password));
            params.add(new BasicNameValuePair("api_password", api_password));
            params.add(new BasicNameValuePair("is_user", is_user));
            params.add(new BasicNameValuePair("text", text));
            params.add(new BasicNameValuePair("type", type));
            params.add(new BasicNameValuePair("price", price));
            params.add(new BasicNameValuePair("name", name));
            params.add(new BasicNameValuePair("city", city));
            params.add(new BasicNameValuePair("address", address));
            params.add(new BasicNameValuePair("phone", phone));
            Log.e("FOR CREATE ORDER",email+"\n"+password+"\n"+api_password+"\n"+is_user+"\n"+text+"\n"+type+"\n"+price+"\n"+name+"\n"+city+"\n"+address+"\n"+phone);

            jsonObject = jsonParser.makeHttpRequest(getResources().getString(R.string.url)+"order/APICreate", "POST", params);
            Log.d("Order", jsonObject.toString());

            return null;
        }

        @Override
        protected void onPostExecute(Object o) {
            super.onPostExecute(o);
            progressDialog.dismiss();
            try {
                if(jsonObject.getString("result").equals("success")) {
                    startActivity(new Intent(Payment.this, Success.class));

                }else {
                    Toast.makeText(getApplicationContext(), "Ошибка сети, повторите попытку позже", Toast.LENGTH_LONG).show();
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
    }
}
