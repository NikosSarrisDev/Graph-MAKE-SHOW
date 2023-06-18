package com.example.mytestapplication;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class PIE_CSV_Chart extends AppCompatActivity {

    private final int CHOOSE_CSV_FILES = 1001;

    private Button button_for_Choosing_csv_file;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pie_csv_chart);

        button_for_Choosing_csv_file = findViewById(R.id.Button_For_CSV);

        button_for_Choosing_csv_file.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callChooseFileFromDevice();
            }
        });
    }

    private void callChooseFileFromDevice() {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        intent.setType("text/csv");
        startActivityForResult(intent, CHOOSE_CSV_FILES);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == CHOOSE_CSV_FILES && resultCode == RESULT_OK) {
            if (data != null) {
                try {
                    InputStream inputStream = getContentResolver().openInputStream(data.getData());
                    CSVReader reader = new CSVReader(new InputStreamReader(inputStream));
                    String[] nextLine;

                    try {
                        // Skip the header line
                        reader.readNext();

                        while ((nextLine = reader.readNext()) != null) {
                            for (String value : nextLine) {
                                System.out.print(value + " ");
                            }
                            System.out.println();
                        }
                    } catch (CsvValidationException e) {
                        e.printStackTrace();
                    }

                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}