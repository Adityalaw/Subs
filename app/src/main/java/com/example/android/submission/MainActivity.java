package com.example.android.submission;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import java.util.Collections;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
        Button background, textStyle, setAlarm, bit;
        TextView textColor;
        TextView tdate;
        ConstraintLayout Entire;
        ImageButton next;
        int check = 0, count = 1, back=0;
        EditText textSize;
        SimpleDateFormat sdf;
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);
            tdate = (TextView) findViewById(R.id.textView);
            Thread t = new Thread() {
                @Override
                public void run() {
                    try {
                        while (!isInterrupted()) {
                            Thread.sleep(1000);
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    long date = System.currentTimeMillis();
                                    if(count%2 == 1) {
                                      sdf = new SimpleDateFormat("MMM dd yyyy\nhh:mm:ss a");
                                    }else{
                                        sdf = new SimpleDateFormat("MMM dd yyyy\nHH:mm:ss");
                                    }
                                    String dateString = sdf.format(date);
                                    tdate.setText(dateString);
                                }
                            });
                        }
                    } catch (InterruptedException e) {
                    }
                }
            };
            t.start();
            final Spinner spinner = (Spinner) findViewById(R.id.spinner);

            spinner.setOnItemSelectedListener(this);

            List<String> categories = new ArrayList<String>();
            Collections.addAll(categories,"Blue","Red","Yellow","Orange","Green");
            ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories);

            dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

            spinner.setAdapter(dataAdapter);
            textSize = (EditText) findViewById(R.id.textSize);
            next = (ImageButton) findViewById(R.id.submit);
            next.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String textsize = textSize.getText().toString();
                    if(textsize.length()==0){
                        return;
                    } else {
                        int fontsize = Integer.parseInt(textsize);
                        tdate.setTextSize(fontsize);
                    }
                }
            });

            textStyle = (Button) findViewById(R.id.textStyle);
            textStyle.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(check%2==0){
                        tdate.setTypeface(tdate.getTypeface(), Typeface.BOLD);
                    } else {
                        tdate.setTypeface(tdate.getTypeface(), Typeface.ITALIC);
                    }
                    check++;
                }
            });
            Entire = (ConstraintLayout) findViewById(R.id.entire);
            background = (Button) findViewById(R.id.background);
            background.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    back++;
                    if(back%2==0){
                        Entire.setBackgroundColor(getResources().getColor(R.color.colorWhite));
                    } else {
                        Entire.setBackgroundColor(getResources().getColor(R.color.colorAccent));
                    }
                }
            });
            setAlarm = (Button) findViewById(R.id.SetAlarm);
            bit = (Button) findViewById(R.id.bit);
            bit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    count++;
                }
            });
            setAlarm.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    startActivity(new Intent(MainActivity.this, AlarmActivity.class));
                }
            });
        }

    @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        // On selecting a spinner item
        String item = parent.getItemAtPosition(position).toString();
        switch (item.toLowerCase()){
            case "red":
                tdate.setTextColor(Color.parseColor("#FF5722"));
                break;
            case "blue":
                tdate.setTextColor(Color.parseColor("#82B1FF"));
                break;
            case "green":
                tdate.setTextColor(Color.parseColor("#A5D6A7"));
                break;
            case "yellow":
                tdate.setTextColor(Color.parseColor("#FFEB3B"));
                break;
            case "orange":
                tdate.setTextColor(Color.parseColor("#FFB74D"));
                break;
        }
    }
    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {}
}
