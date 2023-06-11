package com.example.mytestapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.github.mikephil.charting.charts.CandleStickChart;
import com.github.mikephil.charting.charts.CombinedChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

public class COMBINED_Chart_Activity extends AppCompatActivity {

//    private CombinedChart combinedChart;

//    private Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_combined_chart);

//        spinner = findViewById(R.id.spinner_for_options);
//        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.options, android.R.layout.simple_spinner_item);
//        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        spinner.setAdapter(adapter);
//        spinner.setOnItemSelectedListener(this);

//        combinedChart = findViewById(R.id.combined_chart);
//
//        ArrayList<Entry> list = new ArrayList<>();
//
//        list.add(new Entry(100f, 100f));
//        list.add(new Entry(101f , 101f));
//        list.add(new Entry(102f , 102f));
//        list.add(new Entry(103f , 103f));
//        list.add(new Entry(104f , 104f));
//
//        BarDataSet barDataSet = new BarDataSet(list,"List");
//
//        barDataSet.setColors(ColorTemplate.MATERIAL_COLORS,255);
//
//        barDataSet.setValueTextColor(Color.BLACK);
//
//        BarData barData = new BarData(barDataSet);
//
////        combinedChart.setFitBars(true);
//
//        combinedChart.setData(barData);
//
//        combinedChart.getDescription().setText("Bar Chart");
//
//        combinedChart.animateY(2000);
    }
}