package artlines.kz.cleaning;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Paint;
import android.net.Uri;
import android.preference.PreferenceManager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


public class OtherServicesGen extends ActionBarActivity {
    boolean authorized;
    int sum, sumfinal;
    String sumstring, tempstring;
    int calcConst[];
    TextView text[];
    EditText editText[];
    LinearLayout inputLayout;
    TextView sumTV;
    String stringfinal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_other_services_gen);
        getSupportActionBar().hide();
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
        sum = 0;
        sumstring = "";
        sumTV = (TextView) findViewById(R.id.sum);
        SharedPreferences myPref = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        authorized = myPref.getBoolean("authorized", false);
        sumfinal = Integer.parseInt(myPref.getString("sum", "0"));
        stringfinal = myPref.getString("sumstring", "");
        sumTV.setText(sumfinal + " тг");
        ImageView back = (ImageView) findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        Button next = (Button) findViewById(R.id.next);
        inputLayout = (LinearLayout) findViewById(R.id.calc_channel);
        editText = new EditText[inputLayout.getChildCount()];
        text = new TextView[inputLayout.getChildCount()];
        calcConst = getResources().getIntArray(R.array.CalcMetal);
        for (int i = 0; i < inputLayout.getChildCount(); i++) {
            LinearLayout ll = (LinearLayout) inputLayout.getChildAt(i);
            if (i == 1) {
                //TODO here
            } else {
                editText[i] = (EditText) ll.getChildAt(1);
                text[i] = (TextView) ll.getChildAt(0);

                editText[i].addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {

                    }

                    @Override
                    public void afterTextChanged(Editable s) {
                        calcSum();
                    }
                });
            }


        }
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sumstring = stringfinal;
                for (int i = 0; i < inputLayout.getChildCount(); i++) {
                    LinearLayout ll = (LinearLayout) inputLayout.getChildAt(i);
                    //TODO here
                    editText[i] = (EditText) ll.getChildAt(1);
                    if (editText[i].getText().toString().length() > 0) {
                        text[i] = (TextView) ll.getChildAt(0);
                        tempstring = text[i].getText().toString();
                        sumstring += tempstring + " " + editText[i].getText().toString() + "\n";
                    }
                }
                SharedPreferences myPref = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                myPref.edit().putString("text", sumstring).commit();
                myPref.edit().putString("price", sum + "").commit();
                if (authorized) {
                    startActivity(new Intent(OtherServicesGen.this, Payment.class));
                } else
                    startActivity(new Intent(OtherServicesGen.this, Address.class));
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_other_services_gen, menu);
        return true;
    }

    private void calcSum() {
        sum = sumfinal;
        for (int i = 0; i < inputLayout.getChildCount(); i++) {
            LinearLayout ll = (LinearLayout) inputLayout.getChildAt(i);
            //TODO here
            editText[i] = (EditText) ll.getChildAt(1);
            if (editText[i].getText().toString().length() > 0) {
                sum += Integer.parseInt(editText[i].getText().toString()) * calcConst[i];
            }
        }
        sumTV.setText(sum + " тг");
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
}
