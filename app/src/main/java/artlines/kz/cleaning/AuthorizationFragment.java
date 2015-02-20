package artlines.kz.cleaning;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Paint;
import android.media.Image;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
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


public class AuthorizationFragment extends Fragment {
    private boolean authorized;
    View view;
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.auth_fragment, container, false);
        ImageView back= (ImageView) view.findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), MainActivity.class));
            }
        });
        TextView registration= (TextView) view.findViewById(R.id.register);
        TextView call= (TextView) view.findViewById(R.id.call);
        call.setPaintFlags(call.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        registration.setPaintFlags(registration.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        registration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), Registration.class));
            }
        });
        Button next = (Button) view.findViewById(R.id.loginbutton);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Authorize().execute();
            }
        });


        return view;
    }
    private class Authorize extends AsyncTask {

        JSONParser jsonParser = new JSONParser();
        JSONObject jsonObject;
        ProgressDialog progressDialog;
        String email, password;



        @Override
        protected void onPreExecute() {
            super.onPreExecute();


            progressDialog = new ProgressDialog(getActivity());
            progressDialog.setMessage("Пожалуйста подождите...");
            progressDialog.setIndeterminate(false);
            progressDialog.setCancelable(true);

        }

        @Override
        protected Object doInBackground(Object[] objects) {
            EditText field;
            field=(EditText)view.findViewById(R.id.email);
            email = field.getText().toString();
            field=(EditText)view.findViewById(R.id.password);
            password =field.getText().toString();


            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("email", email));
            params.add(new BasicNameValuePair("password", password));
            params.add(new BasicNameValuePair("api_password", "artlinesAa!"));

            jsonObject = jsonParser.makeHttpRequest(getResources().getString(R.string.url)+"user/APIAuth", "POST", params);
            Log.d("Auth", jsonObject.toString());

            return null;
        }

        @Override
        protected void onPostExecute(Object o) {
            super.onPostExecute(o);
            progressDialog.dismiss();
            try {
                if(jsonObject.getString("result").equals("success")) {
                    authorized=true;

                }else {
                    authorized = false;
                }
                if(authorized){
                    SharedPreferences myPref = PreferenceManager.getDefaultSharedPreferences(getActivity().getApplicationContext());
                    myPref.edit().putBoolean("authorized", true).commit();
                    myPref.edit().putString("mail", email).commit();
                    myPref.edit().putString("password", password).commit();
                    Toast.makeText(getActivity().getApplicationContext(), "Вы успешно авторизованы", Toast.LENGTH_LONG).show();
                    startActivity(new Intent(getActivity(), MainActivity.class));
                }else{
                    Toast.makeText(getActivity().getApplicationContext(), "Неправильный e-mail или пароль", Toast.LENGTH_LONG).show();
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
    }

}
