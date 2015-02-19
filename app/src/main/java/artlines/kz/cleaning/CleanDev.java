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


public class CleanDev extends ActionBarActivity {
    boolean authorized;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clean_dev);
        SharedPreferences myPref= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        authorized=myPref.getBoolean("authorized", false);
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
        Button next=(Button)findViewById(R.id.next);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(authorized){
                    startActivity(new Intent(CleanDev.this, CalculatorDev.class));
                }else
                startActivity(new Intent(CleanDev.this, CustomerContacts.class).putExtra("type", "dev"));
            }
        });
        TextView fish=(TextView)findViewById(R.id.fish);
        String forfish="•  Удаление трудно выводимых пятен краски, штукатурки, клея и цемента со стен, и пола\n\n"+"•  Мытьё пола, стен, потолка, мебели, дверей и прочее\n\n"+"•  Мытьё окон с обеих сторон"+"•  Гигиеническая обработка санитарных и ванных комнат\n\n"+"•  Внутренности шкафов\n\n"+"•  Внутренности холодильника\n\n"+"•  Мытье стен";
        fish.setText(forfish);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_clean_dev, menu);
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
