package com.example.mytestapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;

import java.util.ArrayList;

public class Custom_bar_Chart extends AppCompatActivity {

    private BarChart barChart;
    private EditText xEditText, yEditText;
    private Button btnInsert, btnShow;
    private MyHelper myHelper;
    private SQLiteDatabase sqLineDatabase;
    private BarDataSet barDataSet;
    private ArrayList<IBarDataSet> dataSets = new ArrayList<>();
    private BarData barData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_bar_chart);

        barChart = findViewById(R.id.mpChart);
        xEditText = findViewById(R.id.editTextX);
        yEditText = findViewById(R.id.editTextY);
        btnInsert = findViewById(R.id.btnInsert);
        btnShow = findViewById(R.id.btnShow);
        myHelper = new MyHelper(this);
        sqLineDatabase = myHelper.getWritableDatabase();

        exqInsertBtn();
        exqShowBtn();
//        lineDataSet.setLineWidth();

    }

    private void exqShowBtn() {
        btnShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                barDataSet.setValues(getDataValues());
//                barDataSet.setLabel("DataSet 1");
                barDataSet = new BarDataSet(getDataValues(), "DataSet");
                dataSets.clear();
                dataSets.add(barDataSet);
                barData = new BarData(dataSets);
                barChart.clear();
                barChart.setData(barData);
                barChart.invalidate();
            }
        });
    }

    private void exqInsertBtn() {
        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                float xVal = Float.parseFloat(String.valueOf(xEditText.getText()));
                float yVal = Float.parseFloat(String.valueOf(yEditText.getText()));
                myHelper.insertData(xVal, yVal);

            }
        });
    }

    private ArrayList<BarEntry> getDataValues() {
        ArrayList<BarEntry> dataVals = new ArrayList<>();
        String[] columns = {"xValues", "yValues"};
        Cursor cursor = sqLineDatabase.query("myTable", columns ,null, null, null, null, null);

        for(int i=0; i<cursor.getCount(); i++) {
            cursor.moveToNext();
            dataVals.add(new BarEntry(cursor.getFloat(0), cursor.getFloat(1)));
        }
        return dataVals;
    }

    //When I change activity the table gets empty
    @Override
    protected void onDestroy() {
        super.onDestroy();

        // Truncate the table when the activity is destroyed
//        myHelper.truncateTable("your_table_name");
        myHelper.truncateTable("myTable");
    }

}