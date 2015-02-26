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
import android.util.Log;
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
    TextView sumTV;
    LinearLayout chairhidden, chairsimplehidden, kreslohidden, hiddenkover, hiddensofa, hiddenkresla, hiddentul, hiddenportier, hiddenlambriken, hiddenmatras;

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
        sumstring = "";
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (authorized) {
                    LinearLayout tmp;
                    EditText editText[];
                    TextView textView[];
                    calcConst = getResources().getIntArray(R.array.chairback);
                    tmp = chairhidden;
                    if (tmp.getVisibility() == View.VISIBLE) {
                        editText = new EditText[tmp.getChildCount()];
                        textView=new TextView[tmp.getChildCount()];

                        for (int i = 1; i < tmp.getChildCount(); i++) {
                            LinearLayout ll = (LinearLayout) tmp.getChildAt(i);
                            editText[i] = (EditText) ll.getChildAt(1);
                            textView[i]=(TextView)ll.getChildAt(0);
                            if (editText[i].getText().toString().length() > 0) {
                                sumstring+="Стулья со спинками ["+textView[i].getText().toString()+"]: "+editText[i].getText().toString()+"\n";
                            }
                        }
                         
                    }
                    calcConst = getResources().getIntArray(R.array.chairsimple);
                    tmp = chairsimplehidden;
                    if (tmp.getVisibility() == View.VISIBLE) {
                        editText = new EditText[tmp.getChildCount()];
                        textView=new TextView[tmp.getChildCount()];

                        for (int i = 1; i < tmp.getChildCount(); i++) {
                            LinearLayout ll = (LinearLayout) tmp.getChildAt(i);
                            editText[i] = (EditText) ll.getChildAt(1);
                            textView[i]=(TextView)ll.getChildAt(0);
                            if (editText[i].getText().toString().length() > 0) {
                                sumstring+="Стулья без спинки ["+textView[i].getText().toString()+"]: "+editText[i].getText().toString()+"\n";
                            }
                        }
                        
                    }
                    calcConst = getResources().getIntArray(R.array.kreslo);
                    tmp = kreslohidden;
                    if (tmp.getVisibility() == View.VISIBLE) {
                        editText = new EditText[tmp.getChildCount()];
                        textView=new TextView[tmp.getChildCount()];

                        for (int i = 1; i < tmp.getChildCount(); i++) {
                            LinearLayout ll = (LinearLayout) tmp.getChildAt(i);
                            editText[i] = (EditText) ll.getChildAt(1);
                            textView[i]=(TextView)ll.getChildAt(0);
                            if (editText[i].getText().toString().length() > 0) {
                                sumstring+="Стулья-кресла ["+textView[i].getText().toString()+"]: "+editText[i].getText().toString()+"\n";
                            }
                        }
                         
                    }
                    calcConst = getResources().getIntArray(R.array.kover);
                    tmp = hiddenkover;
                    if (tmp.getVisibility() == View.VISIBLE) {
                        editText = new EditText[tmp.getChildCount()];
                        textView=new TextView[tmp.getChildCount()];

                        for (int i = 1; i < tmp.getChildCount(); i++) {
                            LinearLayout ll = (LinearLayout) tmp.getChildAt(i);
                            editText[i] = (EditText) ll.getChildAt(1);
                            textView[i]=(TextView)ll.getChildAt(0);
                            if (editText[i].getText().toString().length() > 0) {
                                sumstring+="Ковры ["+textView[i].getText().toString()+"]: "+editText[i].getText().toString()+"\n";
                            }
                        }
                         
                    }
                    calcConst = getResources().getIntArray(R.array.sofa);
                    tmp = hiddensofa;
                    if (tmp.getVisibility() == View.VISIBLE) {
                        editText = new EditText[tmp.getChildCount()];
                        textView=new TextView[tmp.getChildCount()];

                        for (int i = 1; i < tmp.getChildCount(); i++) {
                            LinearLayout ll = (LinearLayout) tmp.getChildAt(i);
                            editText[i] = (EditText) ll.getChildAt(1);
                            textView[i]=(TextView)ll.getChildAt(0);
                            if (editText[i].getText().toString().length() > 0) {
                                sumstring+="Диваны ["+textView[i].getText().toString()+"]: "+editText[i].getText().toString()+"\n";
                            }
                        }
                         
                    }
                    calcConst = getResources().getIntArray(R.array.kresla);
                    tmp = hiddenkresla;
                    if (tmp.getVisibility() == View.VISIBLE) {
                        editText = new EditText[tmp.getChildCount()];
                        textView=new TextView[tmp.getChildCount()];

                        for (int i = 1; i < tmp.getChildCount(); i++) {
                            LinearLayout ll = (LinearLayout) tmp.getChildAt(i);
                            editText[i] = (EditText) ll.getChildAt(1);
                            textView[i]=(TextView)ll.getChildAt(0);
                            if (editText[i].getText().toString().length() > 0) {
                                sumstring+="Кресла ["+textView[i].getText().toString()+"]: "+editText[i].getText().toString()+"\n";
                            }
                        }
                         
                    }
                    calcConst = getResources().getIntArray(R.array.tul);
                    tmp = hiddentul;
                    if (tmp.getVisibility() == View.VISIBLE) {
                        editText = new EditText[tmp.getChildCount()];
                        textView=new TextView[tmp.getChildCount()];

                        for (int i = 1; i < tmp.getChildCount(); i++) {
                            LinearLayout ll = (LinearLayout) tmp.getChildAt(i);
                            editText[i] = (EditText) ll.getChildAt(1);
                            textView[i]=(TextView)ll.getChildAt(0);
                            if (editText[i].getText().toString().length() > 0) {
                                sumstring+="Тюли ["+textView[i].getText().toString()+"]: "+editText[i].getText().toString()+"\n";
                            }
                        }
                         
                    }
                    calcConst = getResources().getIntArray(R.array.portier);
                    tmp = hiddenportier;
                    if (tmp.getVisibility() == View.VISIBLE) {
                        editText = new EditText[tmp.getChildCount()];
                        textView=new TextView[tmp.getChildCount()];

                        for (int i = 1; i < tmp.getChildCount(); i++) {
                            LinearLayout ll = (LinearLayout) tmp.getChildAt(i);
                            editText[i] = (EditText) ll.getChildAt(1);
                            textView[i]=(TextView)ll.getChildAt(0);
                            if (editText[i].getText().toString().length() > 0) {
                                sumstring+="Портьеры ["+textView[i].getText().toString()+"]: "+editText[ i].getText().toString()+"\n";
                            }
                        }
                         
                    }
                    calcConst = getResources().getIntArray(R.array.lambriken);
                    tmp = hiddenlambriken;
                    if (tmp.getVisibility() == View.VISIBLE) {
                        editText = new EditText[tmp.getChildCount()];
                        textView=new TextView[tmp.getChildCount()];

                        for (int i = 1; i < tmp.getChildCount(); i++) {
                            LinearLayout ll = (LinearLayout) tmp.getChildAt(i);
                            editText[i] = (EditText) ll.getChildAt(1);
                            textView[i]=(TextView)ll.getChildAt(0);
                            if (editText[i].getText().toString().length() > 0) {
                                sumstring+="Ламбрикены ["+textView[i].getText().toString()+"]: "+editText[i].getText().toString()+"\n";
                            }
                        }
                        
                    }
                    calcConst = getResources().getIntArray(R.array.matras);
                    tmp = hiddenmatras;
                    if (tmp.getVisibility() == View.VISIBLE) {
                        editText = new EditText[tmp.getChildCount()];
                        textView=new TextView[tmp.getChildCount()];
                        for (int i = 1; i < tmp.getChildCount(); i++) {
                            LinearLayout ll = (LinearLayout) tmp.getChildAt(i);
                            editText[i] = (EditText) ll.getChildAt(1);
                            textView[i]=(TextView)ll.getChildAt(0);
                            if (editText[i].getText().toString().length() > 0) {
                                sumstring+="Матрасы ["+textView[i].getText().toString()+"]: "+editText[i].getText().toString()+"\n";
                            }
                        }
                         
                    }
                    Log.d("startString",sumstring);
                    SharedPreferences myPref = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                    myPref.edit().putBoolean("authorized", true).commit();
                    myPref.edit().putString("text", sumstring).commit();
                    myPref.edit().putString("type", "Химчистка").commit();
                    myPref.edit().putString("price", sum+"").commit();


                    startActivity(new Intent(CalculatorSuper.this, Payment.class));
                } else
                    startActivity(new Intent(CalculatorSuper.this, Address.class));
            }
        });
        chairhidden = (LinearLayout) findViewById(R.id.hidden);

        CheckBox chair = (CheckBox) findViewById(R.id.chair);
        chair.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if (isChecked) {
                    chairhidden.setVisibility(View.VISIBLE);

                    LinearLayout temp = chairhidden;
                    calcSum();
                    calcConst = getResources().getIntArray(R.array.chairback);
                    editText = new EditText[temp.getChildCount()];
                    for (int i = 1; i < temp.getChildCount(); i++) {
                        LinearLayout item = (LinearLayout) temp.getChildAt(i);
                        editText[i] = (EditText) item.getChildAt(1);
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
                } else {

                    chairhidden.setVisibility(View.GONE);                     calcSum();


                }

            }
        });
        ///vtupyu

        chairsimplehidden = (LinearLayout) findViewById(R.id.hiddenchairsimple);

        CheckBox chairsimple = (CheckBox) findViewById(R.id.chair2);
        chairsimple.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if (isChecked) {
                    chairsimplehidden.setVisibility(View.VISIBLE);

                    LinearLayout temp = chairsimplehidden;
                    calcSum();
                    calcConst = getResources().getIntArray(R.array.chairsimple);
                    editText = new EditText[temp.getChildCount()];
                    for (int i = 1; i < temp.getChildCount(); i++) {
                        LinearLayout item = (LinearLayout) temp.getChildAt(i);
                        editText[i] = (EditText) item.getChildAt(1);
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
                } else {


                    chairsimplehidden.setVisibility(View.GONE);                     calcSum();

                }

            }
        });
        kreslohidden = (LinearLayout) findViewById(R.id.hiddenkreslo);

        CheckBox kreslocheck = (CheckBox) findViewById(R.id.kreslocheckbox);
        kreslocheck.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if (isChecked) {
                    kreslohidden.setVisibility(View.VISIBLE);

                    LinearLayout temp = kreslohidden;
                    calcSum();
                    calcConst = getResources().getIntArray(R.array.kreslo);
                    editText = new EditText[temp.getChildCount()];
                    for (int i = 1; i < temp.getChildCount(); i++) {
                        LinearLayout item = (LinearLayout) temp.getChildAt(i);
                        editText[i] = (EditText) item.getChildAt(1);
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
                } else {


                    kreslohidden.setVisibility(View.GONE);                     calcSum();

                }

            }
        });
        hiddenkover = (LinearLayout) findViewById(R.id.hiddenkover);

        CheckBox kovercheck = (CheckBox) findViewById(R.id.kovercheckbox);
        kovercheck.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if (isChecked) {
                    hiddenkover.setVisibility(View.VISIBLE);

                    LinearLayout temp = hiddenkover;
                    calcSum();
                    calcConst = getResources().getIntArray(R.array.kover);
                    editText = new EditText[temp.getChildCount()];
                    for (int i = 1; i < temp.getChildCount(); i++) {
                        LinearLayout item = (LinearLayout) temp.getChildAt(i);
                        editText[i] = (EditText) item.getChildAt(1);
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
                } else {


                    hiddenkover.setVisibility(View.GONE);                     calcSum();

                }

            }
        });
        hiddensofa = (LinearLayout) findViewById(R.id.hiddensofa);

        CheckBox sofacheck = (CheckBox) findViewById(R.id.sofacheckbox);
        sofacheck.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if (isChecked) {
                    hiddensofa.setVisibility(View.VISIBLE);

                    LinearLayout temp = hiddensofa;
                    calcSum();
                    calcConst = getResources().getIntArray(R.array.sofa);
                    editText = new EditText[temp.getChildCount()];
                    for (int i = 1; i < temp.getChildCount(); i++) {
                        LinearLayout item = (LinearLayout) temp.getChildAt(i);
                        editText[i] = (EditText) item.getChildAt(1);
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
                } else {


                    hiddensofa.setVisibility(View.GONE);                     calcSum();

                }

            }
        });
        hiddenkresla = (LinearLayout) findViewById(R.id.hiddenkresla);

        CheckBox kreslacheck = (CheckBox) findViewById(R.id.kreslacheckbox);
        kreslacheck.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if (isChecked) {
                    hiddenkresla.setVisibility(View.VISIBLE);

                    LinearLayout temp = hiddenkresla;
                    calcSum();
                    calcConst = getResources().getIntArray(R.array.kresla);
                    editText = new EditText[temp.getChildCount()];
                    for (int i = 1; i < temp.getChildCount(); i++) {
                        LinearLayout item = (LinearLayout) temp.getChildAt(i);
                        editText[i] = (EditText) item.getChildAt(1);
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
                } else {


                    hiddenkresla.setVisibility(View.GONE);                     calcSum();

                }

            }
        });
        hiddentul = (LinearLayout) findViewById(R.id.hiddentul);

        CheckBox tulcheck = (CheckBox) findViewById(R.id.tulcheckbox);
        tulcheck.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if (isChecked) {
                    hiddentul.setVisibility(View.VISIBLE);

                    LinearLayout temp = hiddentul;
                    calcSum();
                    calcConst = getResources().getIntArray(R.array.tul);
                    editText = new EditText[temp.getChildCount()];
                    for (int i = 1; i < temp.getChildCount(); i++) {
                        LinearLayout item = (LinearLayout) temp.getChildAt(i);
                        editText[i] = (EditText) item.getChildAt(1);
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
                } else {


                    hiddentul.setVisibility(View.GONE);                     calcSum();

                }

            }
        });
        hiddenportier = (LinearLayout) findViewById(R.id.hiddenportier);

        CheckBox portiercheck = (CheckBox) findViewById(R.id.portiercheckbox);
        portiercheck.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if (isChecked) {
                    hiddenportier.setVisibility(View.VISIBLE);

                    LinearLayout temp = hiddenportier;
                    calcSum();
                    calcConst = getResources().getIntArray(R.array.portier);
                    editText = new EditText[temp.getChildCount()];
                    for (int i = 1; i < temp.getChildCount(); i++) {
                        LinearLayout item = (LinearLayout) temp.getChildAt(i);
                        editText[i] = (EditText) item.getChildAt(1);
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
                } else {


                    hiddenportier.setVisibility(View.GONE);                     calcSum();

                }

            }
        });
        hiddenlambriken = (LinearLayout) findViewById(R.id.hiddenlambriken);

        CheckBox lambrikencheck = (CheckBox) findViewById(R.id.lambrikencheckbox);
        lambrikencheck.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if (isChecked) {
                    hiddenlambriken.setVisibility(View.VISIBLE);

                    LinearLayout temp = hiddenlambriken;
                    calcSum();
                    calcConst = getResources().getIntArray(R.array.lambriken);
                    editText = new EditText[temp.getChildCount()];
                    for (int i = 1; i < temp.getChildCount(); i++) {
                        LinearLayout item = (LinearLayout) temp.getChildAt(i);
                        editText[i] = (EditText) item.getChildAt(1);
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
                } else {
                    

                    hiddenlambriken.setVisibility(View.GONE);
                    calcSum();

                }

            }
        });
        hiddenmatras = (LinearLayout) findViewById(R.id.hiddenmatras);

        CheckBox matrascheck = (CheckBox) findViewById(R.id.matrascheckbox);
        matrascheck.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if (isChecked) {
                    hiddenmatras.setVisibility(View.VISIBLE);

                    LinearLayout temp = hiddenmatras;
                    calcSum();
                    calcConst = getResources().getIntArray(R.array.matras);
                    editText = new EditText[temp.getChildCount()];
                    for (int i = 1; i < temp.getChildCount(); i++) {
                        LinearLayout item = (LinearLayout) temp.getChildAt(i);
                        editText[i] = (EditText) item.getChildAt(1);
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
                } else {


                    hiddenmatras.setVisibility(View.GONE);                     calcSum();

                }

            }
        });
        sumTV = (TextView) findViewById(R.id.sum);


    }

    public void calcSum() {
        LinearLayout tmp;
        EditText editText[];
        sum = 0;
        calcConst = getResources().getIntArray(R.array.chairback);
        tmp = chairhidden;
        if (tmp.getVisibility() == View.VISIBLE) {
            editText = new EditText[tmp.getChildCount()];
            for (int i = 1; i < tmp.getChildCount(); i++) {
                LinearLayout ll = (LinearLayout) tmp.getChildAt(i);
                editText[i] = (EditText) ll.getChildAt(1);
                if (editText[i].getText().toString().length() > 0) {
                    sum += Integer.parseInt(editText[i].getText().toString()) * calcConst[i - 1];
                }
            }
        }
        calcConst = getResources().getIntArray(R.array.chairsimple);
        tmp = chairsimplehidden;
        if (tmp.getVisibility() == View.VISIBLE) {
            editText = new EditText[tmp.getChildCount()];
            for (int i = 1; i < tmp.getChildCount(); i++) {
                LinearLayout ll = (LinearLayout) tmp.getChildAt(i);
                editText[i] = (EditText) ll.getChildAt(1);
                if (editText[i].getText().toString().length() > 0) {
                    sum += Integer.parseInt(editText[i].getText().toString()) * calcConst[i - 1];
                }
            }
        }
        calcConst = getResources().getIntArray(R.array.kreslo);
        tmp = kreslohidden;
        if (tmp.getVisibility() == View.VISIBLE) {
            editText = new EditText[tmp.getChildCount()];
            for (int i = 1; i < tmp.getChildCount(); i++) {
                LinearLayout ll = (LinearLayout) tmp.getChildAt(i);
                editText[i] = (EditText) ll.getChildAt(1);
                if (editText[i].getText().toString().length() > 0) {
                    sum += Integer.parseInt(editText[i].getText().toString()) * calcConst[i - 1];
                }
            }
        }
        calcConst = getResources().getIntArray(R.array.kover);
        tmp = hiddenkover;
        if (tmp.getVisibility() == View.VISIBLE) {
            editText = new EditText[tmp.getChildCount()];
            for (int i = 1; i < tmp.getChildCount(); i++) {
                LinearLayout ll = (LinearLayout) tmp.getChildAt(i);
                editText[i] = (EditText) ll.getChildAt(1);
                if (editText[i].getText().toString().length() > 0) {
                    sum += Integer.parseInt(editText[i].getText().toString()) * calcConst[i - 1];
                }
            }
        }
        calcConst = getResources().getIntArray(R.array.sofa);
        tmp = hiddensofa;
        if (tmp.getVisibility() == View.VISIBLE) {
            editText = new EditText[tmp.getChildCount()];
            for (int i = 1; i < tmp.getChildCount(); i++) {
                LinearLayout ll = (LinearLayout) tmp.getChildAt(i);
                editText[i] = (EditText) ll.getChildAt(1);
                if (editText[i].getText().toString().length() > 0) {
                    sum += Integer.parseInt(editText[i].getText().toString()) * calcConst[i - 1];
                }
            }
        }
        calcConst = getResources().getIntArray(R.array.kresla);
        tmp = hiddenkresla;
        if (tmp.getVisibility() == View.VISIBLE) {
            editText = new EditText[tmp.getChildCount()];
            for (int i = 1; i < tmp.getChildCount(); i++) {
                LinearLayout ll = (LinearLayout) tmp.getChildAt(i);
                editText[i] = (EditText) ll.getChildAt(1);
                if (editText[i].getText().toString().length() > 0) {
                    sum += Integer.parseInt(editText[i].getText().toString()) * calcConst[i - 1];
                }
            }
        }
        calcConst = getResources().getIntArray(R.array.tul);
        tmp = hiddentul;
        if (tmp.getVisibility() == View.VISIBLE) {
            editText = new EditText[tmp.getChildCount()];
            for (int i = 1; i < tmp.getChildCount(); i++) {
                LinearLayout ll = (LinearLayout) tmp.getChildAt(i);
                editText[i] = (EditText) ll.getChildAt(1);
                if (editText[i].getText().toString().length() > 0) {
                    sum += Integer.parseInt(editText[i].getText().toString()) * calcConst[i - 1];
                }
            }
        }
        calcConst = getResources().getIntArray(R.array.portier);
        tmp = hiddenportier;
        if (tmp.getVisibility() == View.VISIBLE) {
            editText = new EditText[tmp.getChildCount()];
            for (int i = 1; i < tmp.getChildCount(); i++) {
                LinearLayout ll = (LinearLayout) tmp.getChildAt(i);
                editText[i] = (EditText) ll.getChildAt(1);
                if (editText[i].getText().toString().length() > 0) {
                    sum += Integer.parseInt(editText[i].getText().toString()) * calcConst[i - 1];
                }
            }
        }
        calcConst = getResources().getIntArray(R.array.lambriken);
        tmp = hiddenlambriken;
        if (tmp.getVisibility() == View.VISIBLE) {
            editText = new EditText[tmp.getChildCount()];
            for (int i = 1; i < tmp.getChildCount(); i++) {
                LinearLayout ll = (LinearLayout) tmp.getChildAt(i);
                editText[i] = (EditText) ll.getChildAt(1);
                if (editText[i].getText().toString().length() > 0) {
                    sum += Integer.parseInt(editText[i].getText().toString()) * calcConst[i - 1];
                }
            }
        }
        calcConst = getResources().getIntArray(R.array.matras);
        tmp = hiddenmatras;
        if (tmp.getVisibility() == View.VISIBLE) {
            editText = new EditText[tmp.getChildCount()];
            for (int i = 1; i < tmp.getChildCount(); i++) {
                LinearLayout ll = (LinearLayout) tmp.getChildAt(i);
                editText[i] = (EditText) ll.getChildAt(1);
                if (editText[i].getText().toString().length() > 0) {
                    sum += Integer.parseInt(editText[i].getText().toString()) * calcConst[i - 1];
                }
            }
        }

        sumTV.setText(sum + " тг");
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
