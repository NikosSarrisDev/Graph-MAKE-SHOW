package com.example.mytestapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.TextView;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.GridLabelRenderer;
import com.jjoe64.graphview.LegendRenderer;
import com.jjoe64.graphview.helper.GraphViewXML;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

public class MainActivity extends AppCompatActivity {

    //Add a button to move to the next activity

    private Button button_for_line_chart;

    private Button button_for_bar_chart;

    private Button button_for_point_chart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Button_for_line_chart connected with the id in the xml file
        button_for_line_chart =(Button) findViewById(R.id.button_for_line_chart);

        //Set an event listener on button
        button_for_line_chart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openLineChartAcivity();
            }
        });

        //button_for_bar_chart connnected with the id in the xml file
        button_for_bar_chart = (Button) findViewById(R.id.button_for_bar_chart);

        button_for_bar_chart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openBarChartAcivity();
            }
        });

        button_for_point_chart = (Button) findViewById(R.id.button_for_point_chart);

        button_for_point_chart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openPointChartActivity();
            }
        });


    }

    //Create Method openLineChartActivity
    public void openLineChartAcivity() {
        Intent intent = new Intent(this, LineChartActivity.class);

        startActivity(intent);
    }

    public void openBarChartAcivity() {
        Intent intent = new Intent(this,BarChartActivity.class);

        startActivity(intent);
    }

    public void openPointChartActivity() {
        Intent intent = new Intent(this,PointChartActivity.class);

        startActivity(intent);
    }


}