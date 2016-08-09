package matthew.trackyourday;

import android.content.Intent;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;

public class DateSelection extends AppCompatActivity {
    ArrayList dates;

    String[] dateslistview;

    String TAG = "DateSelection.java";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_date_selection);

        listview();

final ListView lv = (ListView) findViewById(R.id.datelistView);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> myAdapter, final View view,
                                    final int position, long mylng) {
                final String selectedFromList = (lv.getItemAtPosition(position).toString());// this is your selected item


                Intent myIntent = new Intent(view.getContext(), DisplayData.class);
                myIntent.putExtra("title", selectedFromList);
                startActivityForResult(myIntent, 0);



            }
        });



            }

    public void listview(){



            File temptxt = new File(getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS), "datapoints");
            temptxt.mkdirs();

            if (temptxt.exists()) {


                File directory = new File(getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS) + "/" + "datapoints");

                dates = new ArrayList<String>(Arrays.asList(directory.list()));



                }else{Log.i(TAG, "Directory doesn't exist");}



        Log.i(TAG, "dates: " + dates);

        String namesstring = dates.toString();
        String namestringnotag = namesstring.replaceAll(".txt", "");
        String namestringnobrackets = namestringnotag.substring(1, namestringnotag.length() - 1);


        dateslistview = namestringnobrackets.split(", ");
        Log.i(TAG, Arrays.toString(dateslistview));




        ListView lv = (ListView) findViewById(R.id.datelistView);
        lv.setAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1 , dateslistview));



    }




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_date_selection, menu);
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
