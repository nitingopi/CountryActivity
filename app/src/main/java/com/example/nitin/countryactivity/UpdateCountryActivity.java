package com.example.nitin.countryactivity;

import android.content.Intent;
import android.os.Bundle;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class UpdateCountryActivity extends AppCompatActivity {

    EditText countryField, currencyField;
    Button updateBtn, backBtn;
    String countryName, currency;
    MyDatabaseHandler handler;
    String id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        handler = new MyDatabaseHandler(this);
        setContentView(R.layout.content_update_country);
        id = getIntent().getExtras().getString("id");
        countryName = getIntent().getExtras().getString("name");
        //Log.i(" id and currency  ",id + " " + countryName);
        //currency = handler.getItemById(Integer.parseInt(id));
        currency = getIntent().getExtras().getString("currency");

        //Log.i("update page",id);
        countryField = (EditText) findViewById(R.id.countryField);
        currencyField = (EditText) findViewById(R.id.currencyField);
        //Log.i("currency from db",handler.getItemById(Integer.parseInt(id)));
        countryField.setText(countryName);
        currencyField.setText(currency);

        updateBtn = (Button) findViewById(R.id.updateBtn);
        backBtn = (Button) findViewById(R.id.BackBtn);
        updateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                countryName = String.valueOf(countryField.getText());
                currency = String.valueOf(currencyField.getText());
                Log.i("update page ", "id " +id + " country " + countryName + " currency "+currency);
                String msg = handler.updateCountryByid(Integer.parseInt(id), countryName, currency);
                //Log.i("update page",msg);
            }
        });

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UpdateCountryActivity.this,CountryActivity.class);
                startActivity(intent);
            }
        });




    }

}
