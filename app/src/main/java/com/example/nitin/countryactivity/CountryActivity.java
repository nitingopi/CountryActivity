package com.example.nitin.countryactivity;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class CountryActivity extends Activity {

    ListView list;
    MyDatabaseHandler handler;
    String[] country ={
            "India",
            "Pakistan",
            "SriLanka",
            "China",
            "Bangladesh",
            "Nepal",
            "Russia",
            "Israel"
    };
    String[] currency ={
            "Indian Rupee",
            "Pakistani Rupee",
            "SriLanka Rupee",
            "Yuan",
            "Bangladesh Rupee",
            "Nepal Rupee",
            "Russian Ruble",
            "New Shekel"
    };
    Integer[] imgid={
            R.drawable.india,
            R.drawable.pakistan,
            R.drawable.srilanka,
            R.drawable.china,
            R.drawable.bangladesh,
            R.drawable.nepal,
            R.drawable.russia,
            R.drawable.israel
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main );
        System.out.println("Started activity");

        handler = new MyDatabaseHandler(this);
        List ls = handler.getAllCountryDetails();
        if(ls != null)
        {
            String[] country = new String[ls.size()];
            String[] currency = new String[ls.size()];
            String[] countryid = new String[ls.size()];
            for(int i = 0; i < ls.size() ; i++)
            {
                String [] countryls = (String[])ls.get(i);
                country[i] = countryls[1];
                currency[i] = countryls[2];
                countryid[i] = countryls[0];
                Log.i("country from db ", countryls[0] + "  " + countryls[1] + "  " + countryls[2]);
            }
            CustomListAdapter adapter = new CustomListAdapter(this, country,currency,countryid, imgid);
            list = (ListView) findViewById(R.id.list);
            list.setAdapter(adapter);

            list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) { // id and position give value in the view.

                    String countryId = ((TextView)view.findViewById(R.id.idd)).getText().toString();
                    String country = ((TextView)view.findViewById(R.id.item)).getText().toString();
                    String currency = ((TextView)view.findViewById(R.id.textView1)).getText().toString();

                    Intent intent = new Intent(CountryActivity.this,UpdateCountryActivity.class);
                    intent.putExtra("id",countryId );
                    intent.putExtra("name",country );
                    intent.putExtra("currency", currency );
                    Log.i("country activity page ", "id "+countryId+" name "+country+" currency "+currency);
                    startActivity(intent);
                }


            });
        }else
        {
            //function to add countries
            // addCountries();
        }

        showAllCountries();




        //function to delete all countries
        //handler.deleteAllCountries();


        /*list.setOnItemClickListener(new OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                // TODO Auto-generated method stub
                String Slecteditem= itemname[+position];
                Toast.makeText(getApplicationContext(), Slecteditem, Toast.LENGTH_SHORT).show();

            }
        });*/

    }

    public void addCountries()
    {
        for (int i = 0 ; i < 7 ; i++)
        {
            String received =  handler.addCountryDetails(country[i], currency[i]);
            Log.i("Countries", country[i] + " " + currency[i]);
        }
    }
    public void showAllCountries()
    {
        List ls = handler.getAllCountryDetails();
        System.out.println(ls);
    }


}
