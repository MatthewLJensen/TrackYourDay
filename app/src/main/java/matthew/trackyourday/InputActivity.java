package matthew.trackyourday;

import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Environment;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Calendar;

public class InputActivity extends AppCompatActivity{

    String TAG = "InputActivity.java";
    LocationManager mLocationManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input);

        final Button savebtn = (Button) findViewById(R.id.savebtn);


        mLocationManager = (LocationManager) getSystemService(LOCATION_SERVICE);

        mLocationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, LOCATION_REFRESH_TIME,
                LOCATION_REFRESH_DISTANCE, mLocationListener);

        savebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                writetofile();

            }
        });
    }



    public void writetofile(){

        Calendar c = Calendar.getInstance();
        int seconds = c.get(Calendar.SECOND);

try {

    EditText titleEditText = (EditText) findViewById(R.id.titleEditText);
    File temptxt = new File(getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS), "datapoints");
    temptxt.mkdirs();

if (temptxt.exists()) {
    FileOutputStream fos = new FileOutputStream(getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS) + "/datapoints" + "/" + titleEditText.getText().toString().replaceAll(":", "<semicolon>") + ".txt");
    String test = "test";
    fos.write(test.getBytes());
    fos.close();
}else{Log.i(TAG, "Directory doesn't exist");}
    }catch (IOException e) {
    Log.e("Exception", "File write failed: " + e.toString());
    Log.i(TAG, "Write to Text File Failed");
}
    }

    private final LocationListener mLocationListener = new LocationListener() {
        @Override
        public void onLocationChanged(final Location location) {
            //your code here
        }
    };


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_input, menu);
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
