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
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.prefs.Preferences;


public class CleanGen extends ActionBarActivity {
    boolean authorized;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clean_gen);
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
        SharedPreferences myPref= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        authorized=myPref.getBoolean("authorized", false);
        ImageView back= (ImageView) findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        Button next=(Button)findViewById(R.id.next);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(authorized){
                    startActivity(new Intent(CleanGen.this, CalculatorGen.class));
                }else
                startActivity(new Intent(CleanGen.this, CustomerContacts.class));
            }
        });
        TextView fish=(TextView)findViewById(R.id.fish);
        String forfish="•  Протирка пыли с крышек столов, тумбочек, подоконников, шкафов и прочих поверхностей\n\n"+"•  Уборка дверных блоков, лестничных пролетов и площадок\n\n"+"•  Мойка и дезинфекция мусорных корзин, замена полиэтиленовых пакетов в мусорных корзинах, вынос мусора\n\n"+"•  Сухая чистка ковров\n\n"+"•  Очистка зеркальных поверхностей\n\n"+"•  Мытье отопительных труб и батарей\n\n"+"•  Протирка розеток и выключателей\n\n"+"•  Влажная и сухая протирка осветительных приборов";
        fish.setText(forfish);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_clean_gen, menu);
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
