package com.example.mytestapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.charts.ScatterChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.data.ScatterData;
import com.github.mikephil.charting.data.ScatterDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.interfaces.datasets.IScatterDataSet;

import java.util.ArrayList;

public class Custom_scatter_Chart extends AppCompatActivity {

    private ScatterChart scatterChart;
    private EditText xEditText, yEditText;
    private Button btnInsert, btnShow;

    private MyHelper myHelper;
    private SQLiteDatabase sqLineDatabase;
    private ScatterDataSet scatterDataSet = new ScatterDataSet(null, null);
    private ArrayList<IScatterDataSet> dataSets = new ArrayList<>();
    private ScatterData scatterData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_scatter_chart);

        scatterChart = findViewById(R.id.mpChart);
        xEditText = findViewById(R.id.editTextX);
        yEditText = findViewById(R.id.editTextY);
        btnInsert = findViewById(R.id.btnInsert);
        btnShow = findViewById(R.id.btnShow);
        myHelper = new MyHelper(this);
        sqLineDatabase = myHelper.getWritableDatabase();

        exqInsertBtn();
        exqShowBtn();
    }

    private void exqShowBtn() {
        btnShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                scatterDataSet.setValues(getDataValues());
                scatterDataSet.setLabel("DataSet");
//                lineDataSet = new BarDataSet(getDataValues(), "DataSet");
                dataSets.clear();
                dataSets.add(scatterDataSet);
                scatterData = new ScatterData(dataSets);
                scatterChart.clear();
                scatterChart.setData(scatterData);
                scatterChart.invalidate();
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