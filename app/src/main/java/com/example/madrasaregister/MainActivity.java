package com.example.madrasaregister;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    Button btn_add_std, btn_show_reocrds, btn_add_record;
    DBHandler dbHandler;
    ImageView git_link;
    EditText rec_date, rec_sabak, rec_sabki, rec_manzil;
    Spinner stdSpinner;
    ArrayAdapter<Student> spinnerAdapter;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn_add_std=findViewById(R.id.btn_add_std);
        btn_show_reocrds= findViewById(R.id.btn_see_records);
        btn_add_record= findViewById(R.id.btn_add_record);
        rec_date = findViewById(R.id.txt_date);
        rec_sabak = findViewById(R.id.txt_sabak);
        rec_sabki = findViewById(R.id.txt_sabki);
        rec_manzil = findViewById(R.id.txt_manzil);
        git_link = findViewById(R.id.git_link);
        dbHandler=new DBHandler(this);
        List<Student> students = dbHandler.selectAllStudents();
        spinnerAdapter = new ArrayAdapter<Student>(this, android.R.layout.simple_spinner_item, students);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        stdSpinner = findViewById(R.id.std_spinner);
        stdSpinner.setAdapter(spinnerAdapter);
        btn_add_std.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                LayoutInflater inflater = getLayoutInflater();
                final View dialogView = inflater.inflate(R.layout.add_std_layout, null);
                builder.setView(dialogView);
                builder.setPositiveButton("Add", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        EditText name = dialogView.findViewById(R.id.std_name);
                        EditText rollno = dialogView.findViewById(R.id.std_roll);
                        EditText joining = dialogView.findViewById(R.id.std_date);
                        Student std=new Student(0,name.getText().toString(),rollno.getText().toString(),joining.getText().toString());
                        dbHandler.insertStudent(std);
                        List<Student> students = dbHandler.selectAllStudents();
                        spinnerAdapter = new ArrayAdapter<Student>(MainActivity.this, android.R.layout.simple_spinner_item, students);
                        spinnerAdapter.notifyDataSetChanged();
                        stdSpinner.setAdapter(spinnerAdapter);
                        Toast.makeText(MainActivity.this, "Student Added Successfully!!", Toast.LENGTH_LONG).show();
                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                            dialog.cancel();
                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();

            }
        });

        btn_show_reocrds.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                Integer id = Integer.parseInt(stdSpinner.getSelectedItem().toString().split(":")[0]);
                ArrayList<Record> records = dbHandler.selectAllRecords(id);
                RecordAdapter dataAdapter = new RecordAdapter(MainActivity.this, records);
                builder.setAdapter(dataAdapter, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(MainActivity.this,"You have selected " + records.get(which),Toast.LENGTH_LONG).show();
                    }
                });
                builder.setNegativeButton("Close", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });

        btn_add_record.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String date = rec_date.getText().toString();
                Integer sabak =  Integer.parseInt(rec_sabak.getText().toString());
                Integer sabki =  Integer.parseInt(rec_sabki.getText().toString());
                Integer manzil =  Integer.parseInt(rec_manzil.getText().toString());
                Integer stdId =  Integer.parseInt(stdSpinner.getSelectedItem().toString().split(":")[0]);
                if (date.isEmpty() || sabak<1 || sabki<1 || sabki>30 || manzil<1 || manzil>30)
                {
                    Toast.makeText(MainActivity.this, "Record Not Added (Invalid Data)!!", Toast.LENGTH_LONG).show();
                }
                else
                {
                    Record record = new Record(0,stdId,date,sabak,sabki,manzil);
                    dbHandler.insertRecord(record);
                    Toast.makeText(MainActivity.this, "Record Added Successfully!!", Toast.LENGTH_LONG).show();
                }
            }
        });

        git_link.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri webpage = Uri.parse("");
                Intent intent = new Intent(Intent.ACTION_VIEW, webpage);
                startActivity(intent);
            }
        });
    }


}