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

    private Button goBubbleChart;

    private Button goCandleStickChart;

//    private Button goCombinedChart;

    private Button goLineChart;

    private Button goScatterChart;



    private Button goBarChart;
    private Button goPieChart;
    private Button goRadarChart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        goBarChart = (Button) findViewById(R.id.go_bar_chart);

        goBarChart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this , BAR_Chart_Activity.class);
                startActivity(intent);
            }
        });

        goPieChart = (Button) findViewById(R.id.go_pie_chart);

        goPieChart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this , PIE_Chart_Activity.class);
                startActivity(intent);
            }
        });

        goRadarChart = (Button) findViewById(R.id.go_radar_chart);

        goRadarChart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this , RADAR_Chart_Activity.class);
                startActivity(intent);
            }
        });

        goBubbleChart = (Button) findViewById(R.id.go_bubble_chart);

        goBubbleChart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this , BUBBLE_Chart_Activity.class);
                startActivity(intent);
            }
        });

        goCandleStickChart = (Button) findViewById(R.id.go_candle_stick_chart);

        goCandleStickChart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this , CANDLESTICK_Chart_Activity.class);
                startActivity(intent);
            }
        });

//        goCombinedChart = (Button) findViewById(R.id.go_combined_chart);
//
//        goCombinedChart.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(MainActivity.this , COMBINED_Chart_Activity.class);
//                startActivity(intent);
//            }
//        });

        goLineChart = (Button) findViewById(R.id.go_line_chart);

        goLineChart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this , LINE_Chart_Activity.class);
                startActivity(intent);
            }
        });

        goScatterChart = (Button) findViewById(R.id.go_scatter_chart);

        goScatterChart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this , SCATTER_Chart_Activity.class);
                startActivity(intent);
            }
        });
    }


}