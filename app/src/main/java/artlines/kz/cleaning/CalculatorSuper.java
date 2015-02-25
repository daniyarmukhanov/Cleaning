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
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;


public class CalculatorSuper extends ActionBarActivity {
    boolean authorized;
    int sum;
    String sumstring;
    int calcConst[];
    TextView textView[];
    EditText editText[];
    RelativeLayout chairtitle, chairsimmpletitle,kreslotitle,kovertitle, sofatitle,kreslatitle,tultitle,portiertitle,lambrikentitle,matrastitle;
    TextView sumTV;
    LinearLayout chairhidden,chairsimplehidden,kreslohidden,koverhidden,sofahidden,kreslahidden,tulhidden,portierhidden,lambrikenhidden,matrashidden;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator_super);
        getSupportActionBar().hide();
        SharedPreferences myPref = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        authorized = myPref.getBoolean("authorized", false);
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
        ImageView back = (ImageView) findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        Button next = (Button) findViewById(R.id.next);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (authorized) {
                    startActivity(new Intent(CalculatorSuper.this, Payment.class));
                } else
                    startActivity(new Intent(CalculatorSuper.this, Address.class));
            }
        });
        final LinearLayout hidden = (LinearLayout) findViewById(R.id.hidden);

        CheckBox chair = (CheckBox) findViewById(R.id.chair);
        chair.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                Animation slideDown = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_down);
                if (isChecked) {
                    hidden.setVisibility(View.VISIBLE);
                } else {

                    hidden.setVisibility(View.GONE);

                }

            }
        });
        ///vtupyu
dfsfsd
        final LinearLayout hiddensimple = (LinearLayout) findViewById(R.id.hiddenchairsimple);

        CheckBox chairsimple = (CheckBox) findViewById(R.id.chair2);
        chairsimple.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                Animation slideDown = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_down);
                if (isChecked) {
                    //hiddensimple.setAnimation(slideDown);
                    hiddensimple.setVisibility(View.VISIBLE);
                } else {

                    hiddensimple.setVisibility(View.GONE);

                }

            }
        });
        final LinearLayout hiddenkreslo = (LinearLayout) findViewById(R.id.hiddenkreslo);

        CheckBox kreslocheck = (CheckBox) findViewById(R.id.kreslocheckbox);
        kreslocheck.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if (isChecked) {
                    hiddenkreslo.setVisibility(View.VISIBLE);
                } else {

                    hiddenkreslo.setVisibility(View.GONE);

                }

            }
        });
        final LinearLayout hiddenkover = (LinearLayout) findViewById(R.id.hiddenkover);

        CheckBox kovercheck = (CheckBox) findViewById(R.id.kovercheckbox);
        kovercheck.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if (isChecked) {
                    hiddenkover.setVisibility(View.VISIBLE);
                } else {

                    hiddenkover.setVisibility(View.GONE);

                }

            }
        });
        final LinearLayout hiddensofa = (LinearLayout) findViewById(R.id.hiddensofa);

        CheckBox sofacheck = (CheckBox) findViewById(R.id.sofacheckbox);
        sofacheck.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if (isChecked) {
                    hiddensofa.setVisibility(View.VISIBLE);
                } else {

                    hiddensofa.setVisibility(View.GONE);

                }

            }
        });
        final LinearLayout hiddenkresla = (LinearLayout) findViewById(R.id.hiddenkresla);

        CheckBox kreslacheck = (CheckBox) findViewById(R.id.kreslacheckbox);
        kreslacheck.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if (isChecked) {
                    hiddenkresla.setVisibility(View.VISIBLE);
                } else {

                    hiddenkresla.setVisibility(View.GONE);

                }

            }
        });
        final LinearLayout hiddentul = (LinearLayout) findViewById(R.id.hiddentul);

        CheckBox tulcheck = (CheckBox) findViewById(R.id.tulcheckbox);
        tulcheck.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if (isChecked) {
                    hiddentul.setVisibility(View.VISIBLE);
                } else {

                    hiddentul.setVisibility(View.GONE);

                }

            }
        });
        final LinearLayout hiddenportier = (LinearLayout) findViewById(R.id.hiddenportier);

        CheckBox portiercheck = (CheckBox) findViewById(R.id.portiercheckbox);
        portiercheck.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if (isChecked) {
                    hiddenportier.setVisibility(View.VISIBLE);
                } else {

                    hiddenportier.setVisibility(View.GONE);

                }

            }
        });
        final LinearLayout hiddenlambriken = (LinearLayout) findViewById(R.id.hiddenlambriken);

        CheckBox lambrikencheck = (CheckBox) findViewById(R.id.lambrikencheckbox);
        lambrikencheck.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if (isChecked) {
                    hiddenlambriken.setVisibility(View.VISIBLE);
                } else {

                    hiddenlambriken.setVisibility(View.GONE);

                }

            }
        });
        final LinearLayout hiddenmatras = (LinearLayout) findViewById(R.id.hiddenmatras);

        CheckBox matrascheck = (CheckBox) findViewById(R.id.matrascheckbox);
        matrascheck.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if (isChecked) {
                    hiddenmatras.setVisibility(View.VISIBLE);
                } else {

                    hiddenmatras.setVisibility(View.GONE);

                }

            }
        });
        sum = 0;
        sumstring = "";
        sumTV = (TextView) findViewById(R.id.sum);



    }





    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_calculator_super, menu);
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
