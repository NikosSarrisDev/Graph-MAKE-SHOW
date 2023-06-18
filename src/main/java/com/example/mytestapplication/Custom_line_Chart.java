package com.example.mytestapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;

import java.util.ArrayList;

public class Custom_line_Chart extends AppCompatActivity {

    private LineChart lineChart;
    private EditText xEditText, yEditText;
    private Button btnInsert, btnShow;

    private MyHelper myHelper;
    private SQLiteDatabase sqLineDatabase;
    private LineDataSet lineDataSet = new LineDataSet(null, null);
    private ArrayList<ILineDataSet> dataSets = new ArrayList<>();
    private LineData lineData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_line_chart);

        lineChart = findViewById(R.id.mpChart);
        xEditText = findViewById(R.id.editTextX);
        yEditText = findViewById(R.id.editTextY);
        btnInsert = findViewById(R.id.btnInsert);
        btnShow = findViewById(R.id.btnShow);
        myHelper = new MyHelper(this);
        sqLineDatabase = myHelper.getWritableDatabase();

        exqInsertBtn();
        exqShowBtn();

        lineDataSet.setLineWidth(4);
    }

    private void exqShowBtn() {
        btnShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lineDataSet.setValues(getDataValues());
                lineDataSet.setLabel("DataSet");
//                lineDataSet = new BarDataSet(getDataValues(), "DataSet");
                dataSets.clear();
                dataSets.add(lineDataSet);
                lineData = new LineData(dataSets);
                lineChart.clear();
                lineChart.setData(lineData);
                lineChart.invalidate();
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

    private ArrayList<Entry> getDataValues() {
        ArrayList<Entry> dataVals = new ArrayList<>();
        String[] columns = {"xValues", "yValues"};
        Cursor cursor = sqLineDatabase.query("myTable",columns, null , null, null, null , null);

        for(int i=0; i<cursor.getCount(); i++) {
            cursor.moveToNext();
            dataVals.add(new Entry(cursor.getFloat(0), cursor.getFloat(1)));
        }
        return dataVals;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        // Truncate the table when the activity is destroyed
        myHelper.truncateTable("myTable");
    }
}