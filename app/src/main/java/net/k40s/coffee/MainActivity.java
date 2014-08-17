package net.k40s.coffee;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.Toast;


public class MainActivity extends Activity {

    Button buttonSingle;
    Button buttonDouble;
    SeekBar seekBarMilk;
    SeekBar seekBarSugar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        buttonSingle = (Button) findViewById(R.id.buttonSingle);
        buttonDouble = (Button) findViewById(R.id.buttonDouble);
        buttonSingle.setOnClickListener(singleBrew);
        buttonDouble.setOnClickListener(doubleBrew);
        seekBarMilk = (SeekBar) findViewById(R.id.seekBarMilk);
        seekBarSugar = (SeekBar) findViewById(R.id.seekBarSugar);
        seekBarMilk.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            int progressChanged = 0;

            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser){
                progressChanged = progress;
            }

            public void onStartTrackingTouch(SeekBar seekBar) {
                // TODO Auto-generated method stub
            }

            public void onStopTrackingTouch(SeekBar seekBar) {
                Toast.makeText(MainActivity.this, getString(R.string.milk_toast) + progressChanged,
                        Toast.LENGTH_SHORT).show();
            }
        });
        seekBarSugar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            int progressChanged = 0;

            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser){
                progressChanged = progress;
            }

            public void onStartTrackingTouch(SeekBar seekBar) {
                // TODO Auto-generated method stub
            }

            public void onStopTrackingTouch(SeekBar seekBar) {
                Toast.makeText(MainActivity.this, getString(R.string.sugar_toast) + progressChanged,
                        Toast.LENGTH_SHORT).show();
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            Intent startActivity = new Intent(this, SettingsActivity.class);
            startActivity(startActivity);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public static class SettingsFragment extends PreferenceFragment {
        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);

            // Load the preferences from an XML resource
            addPreferencesFromResource(R.xml.preferences);
        }
    }

    public static class SettingsActivity extends Activity {

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);

            // Display the fragment as the main content.
            getFragmentManager().beginTransaction()
                    .replace(android.R.id.content, new SettingsFragment())
                    .commit();
        }
    }

    public View.OnClickListener singleBrew = new View.OnClickListener() {
        public void onClick(View v) {
            SharedPreferences sp1 = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
            String pref_machine_lib = sp1.getString("pref_machine_lib", "toast");
            if(pref_machine_lib.equals("toast")){
                brewtoast("single", seekBarMilk, seekBarSugar);
            }
        }
    };

    public View.OnClickListener doubleBrew = new View.OnClickListener() {
        public void onClick(View v) {
            SharedPreferences sp1 = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
            String pref_machine_lib = sp1.getString("pref_machine_lib", "toast");
            if(pref_machine_lib.equals("toast")){
                brewtoast("double", seekBarMilk, seekBarSugar);
            }
        }
    };

    public int brewtoast(String type, SeekBar milk, SeekBar sugar){
        if (type.equals("single")){
            int milkValue = milk.getProgress();
            int sugarValue = sugar.getProgress();
            Toast.makeText(getApplicationContext(), getString(R.string.output) + type + ", " + milkValue + ", " + sugarValue,
                    Toast.LENGTH_SHORT).show();
        }
        if (type.equals("double")){
            int milkValue = milk.getProgress();
            int sugarValue = sugar.getProgress();
            Toast.makeText(MainActivity.this, getString(R.string.output) + type + ", " + milkValue + ", " + sugarValue,
                    Toast.LENGTH_SHORT).show();
        }
        return 0;
    }

}
