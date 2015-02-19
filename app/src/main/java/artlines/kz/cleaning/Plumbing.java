package artlines.kz.cleaning;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Paint;
import android.net.Uri;
import android.preference.PreferenceManager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


public class Plumbing extends ActionBarActivity {
boolean authorized;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plumbing);
        getSupportActionBar().hide();
        SharedPreferences myPref= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        authorized=myPref.getBoolean("authorized", false);
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
        LinearLayout metal=(LinearLayout)findViewById(R.id.metal);
        metal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(authorized) {
                    startActivity(new Intent(Plumbing.this, CalculatorMetal.class));
                }else
                    startActivity(new Intent(Plumbing.this, CustomerContacts.class).putExtra("type", "metal"));
                }
        });
        LinearLayout pvh=(LinearLayout)findViewById(R.id.pvh);
        pvh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(authorized) {
                    startActivity(new Intent(Plumbing.this, CalculatorPVH.class));
                }else
                    startActivity(new Intent(Plumbing.this, CustomerContacts.class).putExtra("type", "pvh"));
            }
        });
        LinearLayout channel=(LinearLayout)findViewById(R.id.channel);
        channel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(authorized) {
                    startActivity(new Intent(Plumbing.this, CalculatorChannel.class));
                }else
                    startActivity(new Intent(Plumbing.this, CustomerContacts.class).putExtra("type", "channel"));
            }
        });
        LinearLayout fayans=(LinearLayout)findViewById(R.id.fayans);
        fayans.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(authorized) {
                    startActivity(new Intent(Plumbing.this, CalculatorFayans.class));
                }else
                    startActivity(new Intent(Plumbing.this, CustomerContacts.class).putExtra("type", "fayans"));
            }
        });

        LinearLayout warm=(LinearLayout)findViewById(R.id.warm);
        warm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(authorized) {
                    startActivity(new Intent(Plumbing.this, CalculatorWarm.class));
                }else
                    startActivity(new Intent(Plumbing.this, CustomerContacts.class).putExtra("type", "warm"));
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_plumbing, menu);
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
}
