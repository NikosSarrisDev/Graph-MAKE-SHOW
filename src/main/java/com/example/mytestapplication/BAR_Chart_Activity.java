package com.example.mytestapplication;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.ColorMatrix;
import android.graphics.drawable.BitmapDrawable;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.provider.Settings;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Map;

public class BAR_Chart_Activity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private static int REQUEST_CODE = 100;
    private Context mContext = BAR_Chart_Activity.this;

    public Context getContext()
    {
        return mContext;
    }
    public String getDirname()
    {

        return mContext.getExternalFilesDir("").getAbsolutePath()+File.separator+ "./Images_for_Bar_Chart";
    }
    public String getFilename()
    {
        String directoryPath = "./Images_for_Bar_Chart";
        File directory = new File(directoryPath);
        if(!directory.exists()){
            Toast.makeText(this, "Fuck", Toast.LENGTH_SHORT).show();
            directory.mkdirs();
        }
        return getDirname()+ "image.jpg";
    }

    private BarChart barChart;

    private Spinner spinner;

//    String fileName = "example.txt";
//    String content = "Hello, world!";
//
//    // Get the Downloads directory
//    String downloadsDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).getAbsolutePath();
//
//    // Create a file object with the desired file name
//    File file = new File(downloadsDir, fileName);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bar_chart);
        // Request permission if needed
        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{android.Manifest.permission.WRITE_EXTERNAL_STORAGE},
                    REQUEST_CODE);

        }
//        //USER PERMISSION TO WRITE TO EXTERNAL STORAGE
//        ////////////////////////////////

        spinner = findViewById(R.id.spinner_for_options);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.options, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);

        barChart = findViewById(R.id.bar_chart);

        ArrayList<BarEntry> list = new ArrayList<>();

        list.add(new BarEntry(100f, 100f));
        list.add(new BarEntry(101f , 101f));
        list.add(new BarEntry(102f , 102f));
        list.add(new BarEntry(103f , 103f));
        list.add(new BarEntry(104f , 104f));

        BarDataSet barDataSet = new BarDataSet(list,"List");

        barDataSet.setColors(ColorTemplate.MATERIAL_COLORS,255);

        barDataSet.setValueTextColor(Color.BLACK);

        BarData barData = new BarData(barDataSet);

        barChart.setFitBars(true);

        barChart.setData(barData);

        barChart.getDescription().setText("Bar Chart");

        barChart.animateY(2000);

//        YAxis yAxis = barChart.getAxisLeft();
//        yAxis.setAxisMaximum(0f);
//        yAxis.setAxisMaximum(100f);
//        yAxis.setAxisLineWidth(2f);
//        yAxis.setAxisLineColor(Color.BLACK);
//        yAxis.setLabelCount(10);

    }



    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

        switch (i){
            case 0:
//                Toast.makeText(this, "Nothing to show", Toast.LENGTH_SHORT).show();
                break;
            case 1:
//                Toast.makeText(this, "Not CSV Yet", Toast.LENGTH_SHORT).show();
                Intent intent_csv = new Intent(BAR_Chart_Activity.this , BAR_CSV_Chart.class);
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
                Toast.makeText(this, "Saving ...", Toast.LENGTH_SHORT).show();
                barChart.saveToGallery(getFilename() , getDirname() , "this file", Bitmap.CompressFormat.JPEG , 100);
                Toast.makeText(this, "Success", Toast.LENGTH_SHORT).show();
                break;
            case 4:
                Intent intent = new Intent(BAR_Chart_Activity.this, Custom_bar_Chart.class);
                startActivity(intent);
            default:
                System.out.println("No such Choise");
                break;

        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NotNull String[] permissions, @NotNull int[] grantResults) {

        if (requestCode == REQUEST_CODE)
        {

            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
//                barChart.saveToGallery("./Download","image.jpg", "file" , Bitmap.CompressFormat.JPEG, 50);
                System.out.println("fwgrtrdytsnrjrs");
            }else {

                Toast.makeText(this,"Please provide the required permissions",Toast.LENGTH_SHORT).show();

            }

        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }
}