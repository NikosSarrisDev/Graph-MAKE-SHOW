package com.example.mytestapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.github.mikephil.charting.charts.CandleStickChart;
import com.github.mikephil.charting.data.CandleData;
import com.github.mikephil.charting.data.CandleDataSet;
import com.github.mikephil.charting.data.CandleEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.util.ArrayList;

public class CANDLESTICK_Chart_Activity extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    private static final int REQUEST_WRITE_STORAGE = 1;

    //Here we have the name of the file and the name of directory that we are saving in the
    private final String filename = "chart_image.jpg";
    private final String foldername = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).getAbsolutePath();

    private CandleStickChart candleStickChart;

    private Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_candlestick_chart);

        // Request permission if needed
        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{android.Manifest.permission.WRITE_EXTERNAL_STORAGE},
                    REQUEST_WRITE_STORAGE);

        }
        //USER PERMISSION TO WRITE TO EXTERNAL STORAGE
        ////////////////////////////////

        spinner = findViewById(R.id.spinner_for_options);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.options, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);

        candleStickChart = findViewById(R.id.candleStick_chart);

        ArrayList<CandleEntry> list = new ArrayList<>();

        list.add(new CandleEntry(100f , 100f , 100f , 100f , 100f));
        list.add(new CandleEntry(101f , 101f , 100f , 100f , 100f));
        list.add(new CandleEntry(102f , 102f , 100f , 100f , 100f));
        list.add(new CandleEntry(103f , 103f , 100f , 100f , 100f));
        list.add(new CandleEntry(104f , 104f , 100f , 100f , 100f));

        CandleDataSet candleStickDataSet = new CandleDataSet(list,"List");

        candleStickDataSet.setColors(ColorTemplate.MATERIAL_COLORS,255);

        candleStickDataSet.setValueTextColor(Color.BLACK);

        CandleData candleStickData = new CandleData(candleStickDataSet);

//        candleStickChart.setFitBars(true);

        candleStickChart.setData(candleStickData);

        candleStickChart.getDescription().setText("CandleStick Chart");

        candleStickChart.animateY(2000);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NotNull String[] permissions, @NotNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == REQUEST_WRITE_STORAGE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Permission granted, save the image
//                System.out.println("Now it is saved");
                candleStickChart.saveToGallery(filename , foldername , "" , Bitmap.CompressFormat.JPEG , 0);
//                barChart.saveToGallery(filename , 0);
            } else {
                // Permission denied, handle accordingly
                Toast.makeText(this, "Permission denied to write to external storage", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        switch (i){
            case 0:
//                Toast.makeText(this, "Nothing to show", Toast.LENGTH_SHORT).show();
                break;
            case 1:
//                Toast.makeText(this, "Not CSV Yet", Toast.LENGTH_SHORT).show();
                Intent intent_csv = new Intent(CANDLESTICK_Chart_Activity.this, CANDLESTICK_CSV_Chart.class);
                startActivity(intent_csv);
                break;
            case 2:
                Toast.makeText(this, "Initialing... email", Toast.LENGTH_SHORT).show();
                Intent emailIntent = new Intent(Intent.ACTION_SENDTO);
                emailIntent.setData(Uri.parse("mailto:")); // Set the data for the intent as "mailto:"
                emailIntent.putExtra(Intent.EXTRA_EMAIL, new String[]{"recipient@example.com"}); // Set the recipient email address
                emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Email subject"); // Set the email subject
                emailIntent.putExtra(Intent.EXTRA_TEXT, "Email body"); // Set the email body

                // Check if there's an email client available to handle the intent
                if (emailIntent.resolveActivity(getPackageManager()) != null) {
                    startActivity(emailIntent); // Start the email client activity
                } else {
                    Toast.makeText(this, "No email client found", Toast.LENGTH_SHORT).show();
                }
                break;
            case 3:
                Toast.makeText(this, "Saving...", Toast.LENGTH_SHORT).show();
                File folder = new File(foldername);
                candleStickChart.saveToGallery(filename , foldername , "" , Bitmap.CompressFormat.JPEG , 0);
//                barChart.saveToGallery(filename , 0);
                Toast.makeText(this, "Success", Toast.LENGTH_SHORT).show();

                break;
            case 4:
                Intent intent = new Intent(CANDLESTICK_Chart_Activity.this, Custom_candlestick_Chart.class);
                startActivity(intent);
            default:
                System.out.println("No such Choise");
                break;

        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}